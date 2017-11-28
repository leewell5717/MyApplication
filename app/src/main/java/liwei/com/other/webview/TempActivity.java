package liwei.com.other.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.other.webview.bean.GetBlogNameFromObjC;
import liwei.com.other.webview.bean.OpenViewHandle;
import liwei.com.other.webview.bean.ResponseJs;
import liwei.com.other.webview.bean.ViewLayoutHandle;
import liwei.com.other.webview.jsbridge.BridgeHandler;
import liwei.com.other.webview.jsbridge.BridgeWebViewContainer;
import liwei.com.other.webview.jsbridge.CallBackFunction;
import liwei.com.other.webview.jsbridge.DefaultHandler;

public class TempActivity extends Activity {
    @BindView(R.id.button_dialog)
    public Button buttonDialog;
    @BindView(R.id.button_activity)
    public Button buttonActivity;
    @BindView(R.id.webview_container)
    public BridgeWebViewContainer webViewContainer;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_temp);
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        WebView webviewDialog = webViewContainer.getWebView();
        webViewContainer.setDefaultHandler(new DefaultHandler());
        webviewDialog.setWebChromeClient(new WebChromeClient());
        webviewDialog.loadUrl("http://test.17byh.com/html/views/login.html");
        webViewContainer.addChildView();

        webViewContainer.registerHandler("viewLayoutHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = viewLayoutHandle(调整view布局大小), data from Js = " + data);
                Gson gson = new Gson();
                ViewLayoutHandle viewLayoutHandle = gson.fromJson(data, ViewLayoutHandle.class);
                showMyDialog(TempActivity.this, viewLayoutHandle);
            }
        });
        //登录
        webViewContainer.registerHandler("getBlogNameFromObjC", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = getBlogNameFromObjC(登录跳转), data from Js = " + data);
                Gson gson = new Gson();
                getBlogNameFromObjC = gson.fromJson(data, GetBlogNameFromObjC.class);
                //判断空
                webViewContainer.callHandler("obserLoginstate", "1", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Log.e("XXXXX", "handler = obserLoginstate(native调用js), data= " + data);
                    }
                });
            }
        });

        //手机注册
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
            }
        });
        //忘记密码
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
            }
        });
    }

    @OnClick({R.id.button_dialog, R.id.button_activity})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_dialog:
                webViewContainer.registerHandler("viewLayoutHandle", new BridgeHandler() {
                    @Override
                    public void handler(String data, CallBackFunction function) {
                        Log.e("XXXXX", "handler = viewLayoutHandle(调整view布局大小), data from Js = " + data);
                        Gson gson = new Gson();
                        ViewLayoutHandle viewLayoutHandle = gson.fromJson(data, ViewLayoutHandle.class);
                        showMyDialog(TempActivity.this, viewLayoutHandle);
                    }
                });
                break;
            case R.id.button_activity:
                webViewContainer.registerHandler("viewLayoutHandle", new BridgeHandler() {
                    @Override
                    public void handler(String data, CallBackFunction function) {
                        Log.e("XXXXX", "handler = viewLayoutHandle(调整view布局大小), data from Js = " + data);
                        Gson gson = new Gson();
                        ViewLayoutHandle viewLayoutHandle = gson.fromJson(data, ViewLayoutHandle.class);
                        turnToActivity(viewLayoutHandle);
                    }
                });
                break;
        }
    }

    GetBlogNameFromObjC getBlogNameFromObjC;

    private void showMyDialog(final Context context, ViewLayoutHandle viewLayoutHandle) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_login);
        Window dialogWindow = dialog.getWindow();

        final BridgeWebViewContainer container = (BridgeWebViewContainer) dialog.findViewById(R.id.webview_container);
        WebView webview = container.getWebView();
        container.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("http://test.17byh.com/html/views/login.html");
//        container.setStrokeColor(Color.BLACK);
        container.setStrokeColor(Color.parseColor(viewLayoutHandle.getBorderColor()));
        container.setStrokeWidth(viewLayoutHandle.getBorderWidth());
        container.setRadius(viewLayoutHandle.getCornerRadius());
        container.addChildView(viewLayoutHandle, screenWidth, screenHeight);

        if (viewLayoutHandle.getPageOpenType() == 1) { //从右往左
            dialogWindow.setWindowAnimations(R.style.MyAnim1);
        } else if (viewLayoutHandle.getPageOpenType() == 2) { //从下往上
            dialogWindow.setWindowAnimations(R.style.MyAnim2);
        }

        ResponseJs responseJs = new ResponseJs();
        responseJs.setStatu(1);
        responseJs.setMessage("android");
        final String str = new Gson().toJson(responseJs);

        //登录
        container.registerHandler("getBlogNameFromObjC", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = getBlogNameFromObjC(登录跳转), data from Js = " + data);
                Gson gson = new Gson();
                getBlogNameFromObjC = gson.fromJson(data, GetBlogNameFromObjC.class);
                //判断空
                container.callHandler("obserLoginstate", str, new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Log.e("XXXXX", "handler = obserLoginstate(native调用js), data= " + data);
                    }
                });
            }
        });

        //手机注册
        container.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
                Gson gson = new Gson();
                OpenViewHandle openViewHandle = gson.fromJson(data, OpenViewHandle.class);
                dialog.dismiss();
                showOtherDialog(context, openViewHandle);
            }
        });
        //忘记密码
        container.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
                Gson gson = new Gson();
                OpenViewHandle openViewHandle = gson.fromJson(data, OpenViewHandle.class);
                dialog.dismiss();
                showOtherDialog(context, openViewHandle);
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showOtherDialog(Context context, OpenViewHandle openViewHandle) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_login);
        Window dialogWindow = dialog.getWindow();

        BridgeWebViewContainer container = (BridgeWebViewContainer) dialog.findViewById(R.id.webview_container);
        WebView webview = container.getWebView();
        container.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(openViewHandle.getPagePath());
        container.addChildView();

        if (openViewHandle.getPageOpenType() == 1) { //从右往左
            dialogWindow.setWindowAnimations(R.style.MyAnim1);
        } else if (openViewHandle.getPageOpenType() == 2) { //从下往上
            dialogWindow.setWindowAnimations(R.style.MyAnim2);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void turnToActivity(ViewLayoutHandle viewLayoutHandle) {
        Intent intent = new Intent(TempActivity.this, LoginActivity.class);
        intent.putExtra("viewLayoutHandle", viewLayoutHandle);
        intent.putExtra("screenWidth", screenWidth);
        intent.putExtra("screenHeight", screenHeight);
        startActivity(intent);
    }
}