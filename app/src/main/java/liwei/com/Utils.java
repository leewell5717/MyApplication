package liwei.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import liwei.com.App.MyApplication;

/**
 * 工具类
 */
public final class Utils {

    private static Toast toast = null;

    /**
     * 图片转base64
     * @param imgPath 图片路径
     */
    public static String imgToBase64(String imgPath) {
        Bitmap originalPhoto;
        if (imgPath != null && imgPath.length() > 0) {
            originalPhoto = BitmapFactory.decodeFile(imgPath);
            byte[] b = getBitmapByte(originalPhoto);
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;
            Bitmap clipBitmap;
            newOpts.inJustDecodeBounds = false;
            newOpts.inSampleSize = 4;
            clipBitmap = BitmapFactory.decodeByteArray(b, 0, b.length, newOpts);

            System.out.println("压缩前大小："+originalPhoto.getByteCount());
            System.out.println("压缩后大小："+clipBitmap.getByteCount());

            ByteArrayOutputStream out = null;
            try {
                out = new ByteArrayOutputStream();
                clipBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                out.flush();
                out.close();

                byte[] imgBytes = out.toByteArray();
                return Base64.encodeToString(imgBytes, Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * bitmap转二进制数据
     */
    private static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * bitmap转成string
     */
    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(getBitmapByte(bitmap), Base64.DEFAULT);
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
     * 像素转dp
     */
    public static int px2dip(Context mContext, float px) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}