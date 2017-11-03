package liwei.com;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 工具类
 */
public final class Utils {

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
    public static byte[] getBitmapByte(Bitmap bitmap) {
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
}