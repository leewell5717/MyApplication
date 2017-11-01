package liwei.com.other.Float;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import liwei.com.R;

import static liwei.com.R.id.button4;

public class FloatView extends LinearLayout{

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
    /**是否展开*/
    private boolean expanded = true;
    //按钮容器宽度
    private int containerBtnWidth;
    private View view;
    private LinearLayout btnContainer;

    public FloatView(Activity activity){
        super(activity);
        view = LayoutInflater.from(activity).inflate(R.layout.float_view, null);

        windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //通过像素密度来设置按钮的大小
        dpi = dpi(dm.densityDpi);
        //屏宽
        screenWidth = dm.widthPixels;
        //屏高
        screenHeight = dm.heightPixels;
        //布局设置
        wmParams = new WindowManager.LayoutParams();
        // 设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
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
//        windowManager.addView(view2, wmParams);

//        view1 = LayoutInflater.from(activity).inflate(R.layout.float_view1, null,false);
//        view2 = LayoutInflater.from(activity).inflate(R.layout.float_view2, null,false);
//        ImageView img = (ImageView) view1.findViewById(R.id.img);
        final ImageView img = (ImageView) view.findViewById(R.id.img);
        btnContainer = (LinearLayout) view.findViewById(R.id.btn_container);
        Button button1 = (Button) view.findViewById(R.id.button1);
//        Button button2 = (Button) view.findViewById(R.id.button2);
//        Button button3 = (Button) view.findViewById(R.id.button3);
//        Button button4 = (Button) view.findViewById(R.id.button4);
        img.setOnClickListener(clickListener);
        button1.setOnClickListener(clickListener);
//        button2.setOnClickListener(clickListener);
//        button3.setOnClickListener(clickListener);
//        button4.setOnClickListener(clickListener);
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
//                            autoView();
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

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                    Log.e("ZZZ","1");
                    break;
                case R.id.button2:
                    Log.e("ZZZ","2");
                    break;
                case R.id.button3:
                    Log.e("ZZZ","3");
                    break;
                case button4:
                    Log.e("ZZZ","4");
                    break;
                case R.id.img:
                    //有动画
//                    ValueAnimator widthAnim;
//                    Log.e("XXX","宽度值："+ containerBtnWidth);
//                    if(!expanded){
//                        expanded = true;
//                        widthAnim = ValueAnimator.ofFloat(0,containerBtnWidth);
//                    }else{
//                        expanded = false;
//                        widthAnim = ValueAnimator.ofFloat(containerBtnWidth,0);
//                    }
//                    widthAnim.setDuration(1500);
//                    widthAnim.setInterpolator(new LinearInterpolator());
//                    widthAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator animation) {
//                            ViewGroup.LayoutParams params = btnContainer.getLayoutParams();
//                            params.width = (int)animation.getAnimatedValue();
//                            btnContainer.setLayoutParams(params);
//
////                            btnContainer.setScaleX((float)animation.getAnimatedValue());
//                        }
//                    });
//                    widthAnim.start();

                    //无动画
                    if(!expanded){
                        expanded = true;
                        btnContainer.setVisibility(View.VISIBLE);
                    }else{
                        expanded = false;
                        btnContainer.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };
}