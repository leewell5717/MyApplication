package liwei.com.other.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;

import com.github.lzyzsd.jsbridge.container.BridgeHandler;
import com.github.lzyzsd.jsbridge.container.BridgeWebViewContainer;
import com.github.lzyzsd.jsbridge.container.CallBackFunction;
import com.github.lzyzsd.jsbridge.container.DefaultHandler;
import com.google.gson.Gson;

import liwei.com.R;
import liwei.com.Utils;
import liwei.com.other.webview.bean.OpenViewHandle;
import liwei.com.other.webview.bean.PageCloseType;
import liwei.com.other.webview.bean.ViewLayoutHandle;
import liwei.com.other.webview.slice.Slice;

public class WebviewActivity extends Activity implements View.OnClickListener{

    private WebView webview;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        BridgeWebViewContainer webViewContainer = (BridgeWebViewContainer)findViewById(R.id.webview_container);
        Button backBtn = (Button)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        webview = webViewContainer.getWebView();
        webViewContainer.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("http://test.17byh.com/html/views/login.html");
//        webview.loadUrl("http://www.google.com");
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
//                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                Gson gson = new Gson();
                OpenViewHandle viewHandle = gson.fromJson(data,OpenViewHandle.class);
                showLoginDialog(WebviewActivity.this,viewHandle);
//                turnToLogin(openViewHandle);
            }
        });

        //android调JS------------------
//        Shop shop = new Shop();
//        shop.setName("红烧肉");
//        shop.setPrice("32");
//        shop.setSell_num(234);
//        webview.callHandler("functionInJs", new Gson().toJson(shop), new CallBackFunction() {
//            @Override
//            public void onCallBack(String data) {
//            }
//        });
//        webview.send("hello");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                webview.goBack();
                break;
        }
    }

    private void showLoginDialog(final Context context, final OpenViewHandle viewHandle){
        final Dialog dialog = new Dialog(context,R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_login);
        BridgeWebViewContainer container = (BridgeWebViewContainer)dialog.findViewById(R.id.dialog_webview);
        WebView mWebview = container.getWebView();
        container.setDefaultHandler(new DefaultHandler());
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.loadUrl(viewHandle.getPagePath());
        container.registerHandler("closeViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = closeViewHandle(关闭窗口), data from Js = " + data);
                Gson gson = new Gson();
                PageCloseType pageCloseType = gson.fromJson(data,PageCloseType.class);
                dialog.dismiss();
            }
        });
        container.registerHandler("viewLayoutHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = viewLayoutHandle(设置窗体), data from Js = " + data);
                Gson gson = new Gson();
                ViewLayoutHandle viewLayoutHandle = gson.fromJson(data,ViewLayoutHandle.class);
                dialog.dismiss();
                showLoginDialog2(context,viewHandle,viewLayoutHandle);
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        //不显示dialog
//        dialog.show();
    }

    private void showLoginDialog2(final Context context, final OpenViewHandle viewHandle,ViewLayoutHandle viewLayoutHandle){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_login);
        LinearLayout rootContainer = (LinearLayout)dialog.findViewById(R.id.root_container);
        BridgeWebViewContainer container = (BridgeWebViewContainer)dialog.findViewById(R.id.dialog_webview);
        WebView mWebview = container.getWebView();
        //设置圆角
        Slice sliceRoot = new Slice(rootContainer);
        sliceRoot.setElevation(2.0f);
        sliceRoot.setRadius(viewLayoutHandle.getCornerRadius());
        Window dialogWindow = dialog.getWindow();
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = Utils.px2dip(WebviewActivity.this,viewLayoutHandle.getX());
        lp.y = Utils.px2dip(WebviewActivity.this,viewLayoutHandle.getY());
        if(viewLayoutHandle.getLayoutType() == 1){ //坐标布局
            lp.width = (int)viewLayoutHandle.getWidth();
            lp.height = (int)viewLayoutHandle.getHeight();
        }else{ //比例布局
            lp.width = (int)(screenWidth * viewLayoutHandle.getWidthScale());
            lp.height = (int)(screenHeight * viewLayoutHandle.getHeightScale());
        }
        container.setDefaultHandler(new DefaultHandler());
        mWebview.setWebChromeClient(new WebChromeClient());
//        mWebview.loadUrl(viewHandle.getPagePath());
        mWebview.loadUrl("http://www.google.com");
        container.registerHandler("closeViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = closeViewHandle(关闭窗口), data from Js = " + data);
                Gson gson = new Gson();
                PageCloseType pageCloseType = gson.fromJson(data,PageCloseType.class);
                dialog.dismiss();
            }
        });

        if(viewHandle.getPageOpenType() == 1){ //从右往左
            dialogWindow.setWindowAnimations(R.style.MyAnim1);
        }else if(viewHandle.getPageOpenType() == 2){ //从下往上
            dialogWindow.setWindowAnimations(R.style.MyAnim2);
        }
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 跳转到Activity
     */
    private void turnToLogin(ViewLayoutHandle viewLayoutHandle,String pagePath,int pageOpenType){
        Intent intent = new Intent(WebviewActivity.this,LoginActivity.class);
        intent.putExtra("viewLayoutHandle",viewLayoutHandle);
        intent.putExtra("pagePath",pagePath);
        intent.putExtra("pageOpenType",pageOpenType);
        startActivity(intent);
    }
}