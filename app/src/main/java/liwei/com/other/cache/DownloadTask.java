package liwei.com.other.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import liwei.com.other.cache.disklrucache.DiskLruCache;
import liwei.com.other.cache.lrucache.LruCacheUtils;

/**
 * 下载任务
 */
public final class DownloadTask {

    private static DownloadTask instance = null;
    private Context context;

    public DownloadTask(Context context){
        this.context = context;
    }

    public static DownloadTask getInstance(Context context){
        if(instance == null){
            instance = new DownloadTask(context);
        }
        return instance;
    }

    public void startLruCache(String url){
        LruCacheTask task = new LruCacheTask();
        task.execute(url);
    }

    public void startDiskLruCache(String url){
        DiskLruCacheTask task = new DiskLruCacheTask();
        task.execute(url);
    }

    /**
     * LruCache下载的异步任务
     */
    private class LruCacheTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strs) {
            Bitmap bitmap = null;
            InputStream inputStream;//用于获取数据的输入流
            ByteArrayOutputStream baos;//可以捕获内存缓冲区的数据，转换成字节数组。
            int len;
            try {
                URL url = new URL(strs[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                connection.connect();

                while((len = inputStream.read(b)) != -1){
                    baos.write(b,0,len);
                }

                bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
                inputStream.close();
                baos.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            LruCacheUtils cacheUtils = new LruCacheUtils();
//            String name = MD5.getMD5String("lrucache");
            String name = "lrucache";
            cacheUtils.addToMemoryCache(name,bitmap);
        }
    }

    /**
     * DiskLruCache下载的异步任务
     */
    private class DiskLruCacheTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strs) {
            Bitmap bitmap = null;
            InputStream inputStream;//用于获取数据的输入流
            ByteArrayOutputStream baos;//可以捕获内存缓冲区的数据，转换成字节数组。
            int len;
            try {
                URL url = new URL(strs[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                connection.connect();

                while((len = inputStream.read(b)) != -1){
                    baos.write(b,0,len);
                }

                bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
                inputStream.close();
                baos.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //String name = MD5.getMD5String("disklrucache");
            String name = "disklrucache";
            try {
                DiskLruCache diskLruCache = DiskLruCache.open(getDiskCacheDir(context,MD5.getMD5String(name)),1,1,10*1024*1024);
                DiskLruCache.Editor editor = diskLruCache.edit(name);
                OutputStream outputStream = editor.newOutputStream(0);
                if(editor != null){
                    editor.commit();
                }
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}