package liwei.com.other.Float;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import liwei.com.R;

/**
 * 自定义悬浮球
 */
public class MyFloatBall extends View {

    private static final String imgUrl = "http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg";

    private Context mContext;
    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    public View mContentView;
    private DisplayMetrics displayMetrics;

    private ImageView imageView;

    private float mTouchX;
    private float mTouchY;
    private float x;
    private float y;
    private float mStartX;
    private float mStartY;
    private OnClickListener mClickListener;
    private boolean bShow = false;


    public MyFloatBall(Context context) {
        super(context);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = getResources().getDisplayMetrics();
        if (wmParams == null) {
            wmParams = new WindowManager.LayoutParams();
        }
        mContext = context;
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    private int downX;
    private int downY;
    private int screenWidth,screenHeight;

    public void setLayout(int layoutId) {
        mContentView = LayoutInflater.from(mContext).inflate(layoutId, null);
        imageView = (ImageView) mContentView.findViewById(R.id.float_image);
        mContentView.setOnTouchListener(new OnTouchListener() {

            private int lastX;
            private int lastY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX = lastX = (int) event.getRawX();
                        downY = lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int disX = (int) (event.getRawX() - lastX);
                        int disY = (int) (event.getRawY() - lastY);
                        wmParams.x += disX;
                        wmParams.y += disY;
                        wm.updateViewLayout(mContentView, wmParams);
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        int x = (int) event.getRawX();
                        int y = (int) event.getRawY();
                        int upX = x - downX;
                        int upY = y - downY;
                        upX = Math.abs(upX);
                        upY = Math.abs(upY);

                        if (upX < 5 && upY < 5) {
                            if (mClickListener != null) {
                                mClickListener.onClick(imageView);
                            }
                        }
                        if (x > screenWidth / 2) {
                            //放手后移到右边
                            wmParams.x = screenWidth;
                            wm.updateViewLayout(mContentView, wmParams);
                        } else {
                            //移到左边
                            wmParams.x = 0;
                            wm.updateViewLayout(mContentView, wmParams);
                        }
                        break;
                }
                return false;
            }
        });
//            mContentView.setOnTouchListener(new OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    // 获取到状态栏的高度
//                    Rect frame = new Rect();
//                    getWindowVisibleDisplayFrame(frame);
//                    int statusBarHeight = frame.top;
//                    // 获取相对屏幕的坐标，即以屏幕左上角为原点
//                    x = event.getRawX();
//                    y = event.getRawY() - statusBarHeight; // statusBarHeight是系统状态栏的高度
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN: // 捕获手指触摸按下动作
//                            // 获取相对View的坐标，即以此View左上角为原点
//                            mTouchX = event.getX();
//                            mTouchY = event.getY();
//                            mStartX = x;
//                            mStartY = y;
//                            break;
//                        case MotionEvent.ACTION_MOVE: // 捕获手指触摸移动动作
//                            updateViewPosition();
//                            break;
//                        case MotionEvent.ACTION_UP: // 捕获手指触摸离开动作
//                            updateViewPosition();
//                            mTouchX = mTouchY = 0;
//                            //取绝对值
//                            if (Math.abs(x - mStartX) < 5 && Math.abs(y - mStartY) < 5) {
//                                if (mClickListener != null) {
//                                    mClickListener.onClick(imageView);
//                                }
//                            }
//                            break;
//                    }
//                    return true;
//                }
//            });
    }

    private void updateViewPosition() {
        // 更新浮动窗口位置参数
        wmParams.x = (int) (x - mTouchX);
        wmParams.y = (int) (y - mTouchY);
        // 刷新显示
        wm.updateViewLayout(mContentView, wmParams);
    }

    public void setOnClickListener(OnClickListener mClickListener){
        this.mClickListener = mClickListener;
    }

    public void show() {
        if (mContentView != null && !bShow) {
            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            wmParams.format = PixelFormat.RGBA_8888;
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            wmParams.alpha = 1.0f;
            wmParams.gravity = Gravity.START | Gravity.TOP;
            wmParams.x = 0;
            wmParams.y = 0;
            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            // 显示悬浮球
            wm.addView(mContentView, wmParams);
            bShow = true;
        }
    }

    public void dismiss(){
        if (mContentView != null) {
            mContentView.setVisibility(View.INVISIBLE);
        }
    }
    public void reShow(){
        if (mContentView != null) {
            mContentView.setVisibility(View.VISIBLE);
        }
    }

    public void close() {
        if (mContentView != null) {
            wm.removeView(mContentView);
            bShow = false;
        }
    }

    public boolean isShow() {
        return bShow;
    }

}