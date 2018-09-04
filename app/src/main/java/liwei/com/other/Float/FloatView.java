package liwei.com.other.Float;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import liwei.com.R;

public class FloatView extends LinearLayout{

    public static final String imgUrl = "http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg";

    // 悬浮栏位置
    private final static int LEFT = 0;
    private final static int RIGHT = 1;
    private final static int TOP = 3;
    private final static int BUTTOM = 4;

    private int dpi;
    private int screenHeight;
    private int screenWidth;
    private WindowManager.LayoutParams wmParams;
    private WindowManager windowManager;
    private float x, y;
    private float mTouchStartX;
    private float mTouchStartY;
    private boolean isScroll;
    //按钮容器宽度
    private int containerBtnWidth;
    private View view;
    private LinearLayout btnContainer;

    private FragmentActivity mActivity;

    private MyFloatView myFloatView;
    private MyFloatBall myFloatBall;

    public FloatView(FragmentActivity activity){
        super(activity);
        mActivity = activity;
        view = LayoutInflater.from(mActivity).inflate(R.layout.float_view, null);

        windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //通过像素密度来设置按钮的大小
        dpi = dpi(dm.densityDpi);
        //屏宽
        screenWidth = dm.widthPixels;
        //屏高
        screenHeight = dm.heightPixels;
        //布局设置
        wmParams = new WindowManager.LayoutParams();
        // 设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//        wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
        wmParams.gravity = Gravity.START | Gravity.TOP;
        // 设置Window flag
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.width = LayoutParams.WRAP_CONTENT;
        wmParams.height = LayoutParams.WRAP_CONTENT;
        wmParams.x = 0;
        wmParams.y = 0;
        windowManager.addView(view, wmParams);

        myFloatBall = new MyFloatBall(mActivity);
        myFloatBall.setLayout(R.layout.view_float_ball);

        final ImageView img = (ImageView) view.findViewById(R.id.img);
        btnContainer = (LinearLayout) view.findViewById(R.id.btn_container);
        Button floatBallOpenDialog = (Button) view.findViewById(R.id.float_ball_open_dialog);
        Button floatBallOpenPopwindow = (Button) view.findViewById(R.id.float_ball_open_popwindow);
        Button floatBallOpenWindow = (Button) view.findViewById(R.id.float_ball_open_window);
        Button floatBallOpenDialogFragment = (Button) view.findViewById(R.id.float_ball_open_dialog_fragment);
        img.setOnClickListener(clickListener);
        floatBallOpenDialog.setOnClickListener(clickListener);
        floatBallOpenPopwindow.setOnClickListener(clickListener);
        floatBallOpenWindow.setOnClickListener(clickListener);
        floatBallOpenDialogFragment.setOnClickListener(clickListener);

        img.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获取相对屏幕的坐标， 以屏幕左上角为原点
                x = event.getRawX();
                y = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // setBackgroundDrawable(openDrawable);
                        // invalidate();
                        // 获取相对View的坐标，即以此View左上角为原点
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isScroll) {
                            updateViewPosition();
                        } else {
                            // 当前不处于连续滑动状态 则滑动小于图标1/3则不滑动
                            if (Math.abs(mTouchStartX - event.getX()) > dpi / 3
                                    || Math.abs(mTouchStartY - event.getY()) > dpi / 3) {
                                updateViewPosition();
                            } else {
                                break;
                            }
                        }
                        isScroll = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        // 拖动
                        if (isScroll) {
                            autoView();
                        }
                        isScroll = false;
                        mTouchStartX = mTouchStartY = 0;
                        break;
                }

                //必须返回false，否则onclick无法触发
                return false;
            }
        });

        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                containerBtnWidth = btnContainer.getMeasuredWidth();
                Log.e("XXX","宽度："+containerBtnWidth);
                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        //隐藏悬浮窗
        hide();
    }

    public FloatView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    /**
     * 根据密度选择控件大小
     */
    private int dpi(int densityDpi) {
        if (densityDpi <= 120) {
            return 36;
        } else if (densityDpi <= 160) {
            return 48;
        } else if (densityDpi <= 240) {
            return 72;
        } else if (densityDpi <= 320) {
            return 96;
        }
        return 108;
    }

    /**
     * 显示悬浮窗
     */
    public void show() {
        if (isShown()) {
            return;
        }
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏悬浮窗
     */
    public void hide() {
        view.setVisibility(View.GONE);
    }

    /**
     * 销毁悬浮窗
     */
    public void destory() {
        hide();
        windowManager.removeViewImmediate(view);
    }

    /**
     * 自动移动位置（自动移动到边缘）
     */
    private void autoView() {
        // 得到view在屏幕中的位置
        int[] location = new int[2];
        getLocationOnScreen(location);
        //左侧
        if (location[0] < screenWidth / 2 - getWidth() / 2) {
            updateViewPosition(LEFT);
        } else {
            updateViewPosition(RIGHT);
        }
    }

    /**
     * 手指释放更新悬浮窗位置
     */
    private void updateViewPosition(int l) {
        switch (l) {
            case LEFT:
                wmParams.x = 0;
                break;
            case RIGHT:
                wmParams.x = screenWidth - dpi;
                break;
            case TOP:
                wmParams.y = 0;
                break;
            case BUTTOM:
                wmParams.y = screenHeight - dpi;
                break;
        }
        windowManager.updateViewLayout(view, wmParams);
    }

    // 更新浮动窗口位置参数
    private void updateViewPosition() {
        wmParams.x = (int) (x - mTouchStartX);
        //是否存在状态栏（提升滑动效果）
        // 不设置为全屏（状态栏存在） 标题栏是屏幕的1/25
        wmParams.y = (int) (y - mTouchStartY - screenHeight / 25);
        windowManager.updateViewLayout(view, wmParams);
    }

    /**
     * 处理点击事件
     */
    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.float_ball_open_dialog: //打开dialog
                    openDialog(mActivity);
                    break;
                case R.id.float_ball_open_popwindow: //打开popwindow
                    openPopwindow(mActivity);
                    break;
                case R.id.float_ball_open_window: //打开window
                    openWindow(mActivity);
                    break;
                case R.id.float_ball_open_dialog_fragment: //打开dialogFragment
                    openDialogFrament();
                    break;
                case R.id.img:
                    showFloatMenu();
                    break;
            }
        }
    };

    private void showFloatMenu(){
        if(btnContainer.isShown()){
            btnContainer.setVisibility(View.GONE);
        }else {
            btnContainer.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 打开对话框
     */
    private void openDialog(final Context context) {
        Dialog dialog = new Dialog(context, R.style.MyDialog_Float);
        //加载布局
        View viewDialog = LayoutInflater.from(context).inflate(R.layout.view_dialog_float, null,false);
        dialog.setContentView(viewDialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        //设置dialog的样式
        Window window = dialog.getWindow();
        window.setGravity(Gravity.START);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (displayMetrics.widthPixels * 0.5);
        params.height = (int) (displayMetrics.heightPixels * 0.99);
        window.setAttributes(params);
        //view事件处理
        Button button = (Button) viewDialog.findViewById(R.id.loadImageBtn);
        final ImageView imageView = (ImageView) viewDialog.findViewById(R.id.showMyImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(context).load(imgUrl).into(imageView);
            }
        });
    }

    /**
     * 打开popwindow
     */
    private void openPopwindow(final Context context) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        View rootView = LayoutInflater.from(context).inflate(R.layout.view_dialog_float, null);
        PopupWindow popupWindow = new PopupWindow(rootView, (int) (displayMetrics.widthPixels * 0.5), (int) (displayMetrics.heightPixels * 0.99), true);
        Button button = (Button) rootView.findViewById(R.id.loadImageBtn);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.showMyImage);
        popupWindow.setAnimationStyle(R.style.dialog_anim);
        // 设置PopupWindow的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#aabbbbbb")));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        popupWindow.showAsDropDown(new View(mActivity), 0, 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(imgUrl).into(imageView);
            }
        });
    }

    /**
     * 打开window
     */
    private void openWindow(final Context context) {
        if (!myFloatBall.isShow()) {
            myFloatBall.show();
            myFloatBall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myFloatBall.dismiss();

                    myFloatView = new MyFloatView(context);
                    myFloatView.setLayout(R.layout.view_dialog_float);
                    if (!myFloatView.isShow()) {
                        myFloatView.show();
                    }
                }
            });
        }
    }

    /**
     * 打开dialogFragment
     */
    private void openDialogFrament(){
        MyCustomDialogFragment dialogFragment = new MyCustomDialogFragment();
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogFragment.show(transaction, "df");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (myFloatView != null && myFloatView.isShow()) {
            myFloatView.close();
            myFloatBall.reShow();
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN || KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            if (myFloatView != null && myFloatView.isShow()) {
                myFloatView.close();
                myFloatBall.reShow();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}