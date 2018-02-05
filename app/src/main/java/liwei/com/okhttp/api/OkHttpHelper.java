package liwei.com.okhttp.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liwei
 * @desc okHttp辅助类
 */
public class OkHttpHelper {

    /**
     * 这个枚举用于指明是哪一种提交方式
     */
    private enum HttpMethodType {
        GET, POST
    }

    /**
     * 采用单例模式使用OkHttpClient
     */
    private static OkHttpHelper mOkHttpHelperInstance;
    private static OkHttpClient mClientInstance;
    private Handler mHandler;
    private Gson mGson;
    /**
     * 读取超时时间
     */
    private static final int ReadTimeOut = 20;
    /**
     * 连接超时时间
     */
    private static final int ConnectTimeOut = 20;
    /**
     * 写入超时时间
     */
    private static final int WriteTimeOut = 30;

    /**
     * 单例模式，私有构造函数，构造函数里面进行一些初始化
     */
    private OkHttpHelper(Context context) {
        mClientInstance = new OkHttpClient();
        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();

        ClientBuilder.readTimeout(ReadTimeOut, TimeUnit.SECONDS);// 读取超时
        ClientBuilder.connectTimeout(ConnectTimeOut, TimeUnit.SECONDS);// 连接超时
        ClientBuilder.writeTimeout(WriteTimeOut, TimeUnit.SECONDS);// 写入超时
        ClientBuilder.cookieJar(new CookieManger(context));
        mClientInstance = ClientBuilder.build();
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取实例
     */
    public static OkHttpHelper getInstance(Context context) {
        if (mOkHttpHelperInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mOkHttpHelperInstance == null) {
                    mOkHttpHelperInstance = new OkHttpHelper(context);
                }
            }
        }
        return mOkHttpHelperInstance;
    }

    /**
     * 封装一个request方法，不管post或者get方法中都会用到
     */
    public void request(final Request request, final OkHttpBaseCallback callback) {
        // 在请求之前所做的事，比如弹出对话框等
        callback.onRequestBefore();
        mClientInstance.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 返回失败
                callbackFailure(request, callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // 返回成功回调
                    String result = response.body().string();
                    if (callback.mType == String.class) {
                        // 如果我们需要返回String类型
                        callbackSuccess(response, result, callback);
                    } else {
                        // 如果返回的是其他类型，则利用Gson去解析
                        try {
                            Object o = mGson.fromJson(result, callback.mType);
                            callbackSuccess(response, o, callback);
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            callbackError(response, callback, e);
                        }
                    }
                } else {
                    // 返回错误
                    callbackError(response, callback, null);
                }
            }
        });
    }

    /**
     * 在主线程中执行的回调——成功
     */
    private void callbackSuccess(final Response response, final Object o, final OkHttpBaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, o);
            }
        });
    }

    /**
     * 在主线程中执行的回调——错误
     */
    private void callbackError(final Response response, final OkHttpBaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    /**
     * 在主线程中执行的回调——失败
     */
    private void callbackFailure(final Request request, final OkHttpBaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    /**
     * 构建请求对象
     */
    private Request buildRequest(String url, LinkedHashMap<String, Object> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        if (type == HttpMethodType.GET) {
            Iterator<String> keys = params.keySet().iterator();
            Iterator<Object> values = params.values().iterator();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("?");
            for (int i=0;i<params.size();i++ ) {
                Object value=null;
                try {
                    value= values.next();
                }catch (Exception e){
                    e.printStackTrace();
                }
                stringBuilder.append(keys.next()).append("=").append(value);
                if (i!=params.size()-1) {
                    stringBuilder.append("&");
                }
            }
            builder.url(url + stringBuilder.toString());
        }else if(type == HttpMethodType.POST){
            builder.url(url);
            builder.post(buildRequestBody(params));
        }
        return builder.build();
    }

    /**
     * 通过Map的键值对构建请求对象的body
     */
    private RequestBody buildRequestBody(LinkedHashMap<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, Object> entity : params.entrySet()) {
                builder.add(entity.getKey(), String.valueOf(entity.getValue()));
            }
        }
        return builder.build();
    }

    /**
     * get请求
     */
    public void get(String url, LinkedHashMap<String, Object> params, OkHttpBaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.GET);
        request(request, callback);
    }

    /**
     * post请求
     */
    public void post(String url, LinkedHashMap<String, Object> params, OkHttpBaseCallback callback) {
        Request request = buildRequest(url, params, HttpMethodType.POST);
        request(request, callback);
    }

    /**
     * 下载文件
     *
     * @param url      下载地址
     * @param fileDir  下载文件路径
     * @param fileName 文件名
     * @param handler  进度操作handler
     */
    public void downFile(String url, final String fileDir, final String fileName, final Handler handler) {
        Request request = new Request.Builder().url(url).addHeader("Accept-Encoding", "identity").build();
        Call call = mClientInstance.newCall(request);
        Log.e("OkHttpHelper", "下载地址：" + url);
        Log.e("OkHttpHelper", "下载路径：" + fileDir);
        Log.e("OkHttpHelper", "下载名称：" + fileName);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("OkHttpHelper", "exception:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    String fileSize = response.header("fileSize");
                    if (!"".equals(fileSize)) {
                        long total = Long.parseLong(fileSize);
                        Log.e("OkHttpHelper", "total:" + total);
                        File file = new File(fileDir, fileName);
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            Log.e("OkHttpHelper", "progress=" + progress);
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.arg1 = progress;
                            handler.sendMessage(msg);
                        }
                        fos.flush();
                        Log.e("OkHttpHelper", "文件下载成功");
                    } else {
                        Log.e("OkHttpHelper", "文件大小为空，文件下载失败");
                    }
                } catch (IOException e) {
                    Log.e("OkHttpHelper", "文件下载失败");
                    e.printStackTrace();
                } finally {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                }
            }
        });
    }
}