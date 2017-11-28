package liwei.com.other.webview.api;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 下载管理器
 */
public class DownLoadManager {
    private static final String TAG = "XXXX";
    private static final String APK_CONTENTTYPE = "application/vnd.android.package-archive";
    private static final String PNG_CONTENTTYPE = "image/png";
    private static final String JPG_CONTENTTYPE = "image/jpeg";
    private static final String TEXT_CONTENTTYPE = "text/plain";
    private static String fileSuffix = "";

    private static final String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "takePic";

    public static void writeResponseBodyToDisk(final ResponseBody body) {
        Log.e(TAG, "contentType:" + body.contentType().toString());
        String type = body.contentType().toString();
        if (type.equals(APK_CONTENTTYPE)) {
            fileSuffix = ".apk";
        } else if (type.equals(PNG_CONTENTTYPE)) {
            fileSuffix = ".png";
        } else if (type.contains(TEXT_CONTENTTYPE)) {
            fileSuffix = ".txt";
        } else if (type.equals(JPG_CONTENTTYPE)) {
            fileSuffix = ".jpg";
        }
        // 其他类型同上 自己判断加入.....

        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = rootPath + File.separator + "my" + fileSuffix;
        final File filePath = new File(path);
        if (filePath.exists()) {
            Log.e("XXXX", "文件已存在");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = body.byteStream();
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(filePath);
                    //创建缓冲输入流对象，相对于inputStream效率要高一些
//                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    //此处的len表示每次循环读取的内容长度
                    int len;
                    Log.e("XXX","总大小：" + body.contentLength());
                    //已经读取的总长度
                    int totle = 0;
                    byte[] bytes = new byte[2048];
                    while ((len = inputStream.read(bytes)) != -1) {
                        //每次读取完了都将len累加在totle里
                        Log.e("XXX","已下载：" + totle);
                        totle += len;
                        outputStream.write(bytes, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}