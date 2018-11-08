package liwei.com;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

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

    /**
     * 获取手机IMEI
     */
    public static String getPhoneIMEI(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取UUID
     */
    public static String getUUID(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    /**
     * 从assets拷贝文件到SD卡
     *
     *
        String filaName = "outter.apk"; //文件名
        String assetsPath = "external"; //assets下的目录
        String sdcardPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "liwei"; //SD卡上的地址
        调用示例：copyFileFromAssetsToSDCard(MainActivity.this,filaName,assetsPath,sdcardPath)
     *
     * @param context 当前上下文
     * @param fileName 文件名（包括后缀名）
     * @param sdcardPath 保存在SD卡上的路径
     * @return 1：context为null；2：文件名或路径为空；3：文件已存在；-1：IO错误；0：成功
     */
    public static int copyFileFromAssetsToSDCard(Context context, String fileName,String assetsPath, String sdcardPath){
        if(context == null){
            Log.e("XXX","context为null");
            return 1;
        }
        if(TextUtils.isEmpty(fileName) || TextUtils.isEmpty(sdcardPath)){
            Log.e("XXX","文件名或路径为空");
            return 2;
        }
        File filePath = new File(sdcardPath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }

        String fullPath = sdcardPath + File.separator + fileName;
        File file = new File(fullPath);
        if(file.exists()){
            Log.e("XXX","文件已存在");
            return 3;
        }

        try {
            OutputStream outputStream = new FileOutputStream(fullPath);
            InputStream inputStream;

            if(TextUtils.isEmpty(assetsPath)){ //访问assets中的单个文件
                inputStream = context.getAssets().open(fileName);
            }else{ //访问带有目录下面的文件
                inputStream = context.getAssets().open(assetsPath + "/" + fileName);
            }
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}