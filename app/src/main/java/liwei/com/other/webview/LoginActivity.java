package liwei.com.other.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.gson.Gson;

import liwei.com.R;
import liwei.com.other.webview.bean.GetBlogNameFromObjC;
import liwei.com.other.webview.bean.OpenViewHandle;
import liwei.com.other.webview.bean.ViewLayoutHandle;
import liwei.com.other.webview.jsbridge.BridgeHandler;
import liwei.com.other.webview.jsbridge.BridgeWebViewContainer;
import liwei.com.other.webview.jsbridge.CallBackFunction;
import liwei.com.other.webview.jsbridge.DefaultHandler;

public class LoginActivity extends Activity {

    private BridgeWebViewContainer webViewContainer;
    private WebView webview;

    private GetBlogNameFromObjC getBlogNameFromObjC;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);

        Intent intent = getIntent();
        final ViewLayoutHandle viewLayoutHandle = (ViewLayoutHandle) intent.getSerializableExtra("viewLayoutHandle");

        int screenWidth = intent.getIntExtra("screenWidth",0);
        int screenHeight = intent.getIntExtra("screenHeight",0);

        webViewContainer = (BridgeWebViewContainer)findViewById(R.id.webview_container);
        webview = webViewContainer.getWebView();
        webViewContainer.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("http://test.17byh.com/html/views/login.html");

        if(viewLayoutHandle.getPageOpenType() == 1){ //从右往左
            getWindow().setWindowAnimations(R.style.MyAnim1);
        }else if(viewLayoutHandle.getPageOpenType() == 2){ //从下往上
            getWindow().setWindowAnimations(R.style.MyAnim2);
        }
//        webViewContainer.setStrokeColor(Color.parseColor(viewLayoutHandle.getBorderColor()));
        webViewContainer.setStrokeColor(Color.BLACK);
        webViewContainer.setStrokeWidth(viewLayoutHandle.getBorderWidth());
        webViewContainer.setRadius(viewLayoutHandle.getCornerRadius());
        webViewContainer.addChildView(viewLayoutHandle,screenWidth,screenHeight);

        //登录
        webViewContainer.registerHandler("getBlogNameFromObjC", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = getBlogNameFromObjC(登录跳转), data from Js = " + data);
                Gson gson = new Gson();
                getBlogNameFromObjC = gson.fromJson(data,GetBlogNameFromObjC.class);
            }
        });
        webViewContainer.callHandler("obserLoginstate", "1", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.e("XXXXX", "handler = obserLoginstate(native调用js), data from Js = " + data);
            }
        });

        //手机注册
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
                Gson gson = new Gson();
                OpenViewHandle openViewHandle = gson.fromJson(data,OpenViewHandle.class);
            }
        });
        //忘记密码
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
                Gson gson = new Gson();
                OpenViewHandle openViewHandle = gson.fromJson(data,OpenViewHandle.class);

            }
        });
    }
}