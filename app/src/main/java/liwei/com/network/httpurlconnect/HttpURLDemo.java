package liwei.com.network.httpurlconnect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import liwei.com.network.httpurlconnect.httpurl.CallBackUtil;
import liwei.com.network.httpurlconnect.httpurl.UrlHttpUtil;

/**
 * 对HttpURL封装的使用demo，没有实际测试过
 */
public class HttpURLDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        userGet(this);
        usePost(this);
        uploadFile();
        downloadFile();
    }

    private void userGet(final Context context) {
        String url = "https://www.baidu.com/";
        UrlHttpUtil.get(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                Log.e("XXX", "code:" + code + "，errorMessage:" + errorMessage);
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                Log.e("XXX", response);
            }
        });
    }

    private void usePost(final Context context) {
        String url = "https://www.baidu.com/";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("title", "title");
        paramsMap.put("desc", "desc");
        UrlHttpUtil.post(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                Log.e("XXX", "code:" + code + "，errorMessage:" + errorMessage);
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                Log.e("XXX", response);
            }
        });
    }

    private void uploadFile() {
        File file = new File(Environment.getExternalStorageDirectory() + "/kwwl/abc.jpg");
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("title", "title");

        UrlHttpUtil.uploadFile("url", file, "image", UrlHttpUtil.FILE_TYPE_FILE, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                Log.e("XXX", "code:" + code + "，errorMessage:" + errorMessage);
            }

            @Override
            public void onResponse(String response) {
                Log.e("XXX", response);
            }
        });
    }

    private void downloadFile() {
        UrlHttpUtil.downloadFile("url", new CallBackUtil.CallBackFile("fileDir", "fileName") {
            @Override
            public void onFailure(int code, String errorMessage) {
                Log.e("XXX", "code:" + code + "，errorMessage:" + errorMessage);
            }

            @Override
            public void onProgress(float progress, long total) {
                super.onProgress(progress, total);
            }

            @Override
            public void onResponse(File response) {

            }
        });
    }
}