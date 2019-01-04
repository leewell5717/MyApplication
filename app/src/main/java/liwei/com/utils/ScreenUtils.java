package liwei.com.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import liwei.com.App.MyApplication;

/**
 * 与屏幕相关的工具类
 */
public class ScreenUtils {

    private static Toast toast = null;

    /**
     * 获取全屏幕尺寸(包含底部有虚拟按键的尺寸，比如华为手机)
     */
    public static Point getFullScreenSize(Context context) {
        Point size = new Point();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
//        float density = metrics.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = metrics.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）

        size.x = metrics.widthPixels; // 屏幕宽度（像素）
        size.y = metrics.heightPixels; // 屏幕高度（像素）
        return size;
    }

    /**
     * 获取真实屏幕尺寸(不包含底部有虚拟按钮的尺寸)
     */
    public static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
        } else {
            size.x = display.getWidth();
            size.y = display.getHeight();
        }

        return size;
    }

    /**
     * 自定义Toast，避免重复，界面中部弹出
     */
    public static void showToastCenter(String msg) {
        if(TextUtils.isEmpty(msg)){
            msg = "null";
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApp(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showToastBottom(String msg) {
        if(TextUtils.isEmpty(msg)){
            msg = "null";
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApp(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showToastBottom(int stringId) {
        String content;
        try {
            content = MyApplication.getApp().getResources().getString(stringId);
        } catch (Exception e) {
            content = String.valueOf(stringId);
        }

        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApp(), content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * px转dp
     */
    public static int px2dip(Context mContext, float px) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * dp转px
     */
    public static int dp2px(Context mContext, float dp){
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}