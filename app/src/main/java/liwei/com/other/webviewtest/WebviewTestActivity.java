package liwei.com.other.webviewtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import liwei.com.R;

/**
 * 对webview进行调试的使用
 */
public class WebviewTestActivity extends Activity {

    public WebView myWebview;
    private TextView webviewTitle;
    private Button callFun1;
    private Button callFun2;

    private static final String Tag = "XXX";
    private boolean isClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_test);
        myWebview = (WebView) findViewById(R.id.my_webview);
        webviewTitle = (TextView) findViewById(R.id.webview_title);
        callFun1 = (Button) findViewById(R.id.callFun1);
        callFun2 = (Button) findViewById(R.id.callFun2);
        callFun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用js方法
                if (isClick) {
                    myWebview.loadUrl("javascript:AndroidToJs1()");
                    isClick = false;
                } else {
                    myWebview.loadUrl("javascript:AndroidToJs2('我是来自于Android')");
                    isClick = true;
                }
            }
        });
        callFun2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //调用js方法
                //此方法可以获取到js的返回值，但是只能在4.4(19)版本以上使用，此方法效率更高，推荐在高版本使用，需要做版本判断
                myWebview.evaluateJavascript("javascript:AndroidToJs3(1,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e(Tag, "结果是：" + value);
                    }
                });
            }
        });

//        myWebview.loadUrl("file:///android_asset/MyWeb.html");//加载本地网页
        myWebview.loadUrl("https://www.baidu.com");

        WebSettings settings = myWebview.getSettings();
        settings.setJavaScriptEnabled(true); //是否支持使用Js
        settings.setDatabaseEnabled(true); //开启数据库缓存
        settings.setDomStorageEnabled(true); //开启dom缓存(即web端的web storage)
        settings.setLoadsImagesAutomatically(isOverKitkat()); // 自动加载图片
        settings.setCacheMode(WebSettings.LOAD_DEFAULT); // 设置 WebView 的缓存模式
        settings.setAppCacheEnabled(true); // 启用缓存模式
        settings.setAppCacheMaxSize(8 * 1024 * 1024); // 设置 AppCache 最大缓存值(现在官方已经不提倡使用，已废弃)
        // Android 私有缓存存储，如果你不调用此方法，WebView将不会产生这个目录（已经不再推荐使用）
        String appCache = Environment.getExternalStorageDirectory().getPath() + File.separator + "liwei" + File.separator + "AppCache";
        File appCacheFile = new File(appCache);
        if(!appCacheFile.exists()){
            appCacheFile.mkdir();
        }
        settings.setAppCachePath(appCache);

        //TODO Webview缓存说明：
        //方式1：使用浏览器缓存——主要是前端负责，Android端不需要额外设置
        //方式2：使用Dom Storage（Web Storage）存储——通过settings.setDomStorageEnabled(true);
        //方式3：使用Web SQL Database存储——已经不推荐使用，通过settings.setDatabasePath("路径");
        //方式4：使用Application Cache存储——已经不推荐使用，通过settings.setAppCachePath("路径");
        //方式5：使用Indexed Database存储——从4.4版本开始支持，通过settings.setJavaScriptEnabled(true);
        //方式6：使用File System API——Android 系统的 WebView 还不支持 File System API


        //设置自适应屏幕，两者合用
//        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        // 支持缩放，是下面两个设置的前提
//        settings.setSupportZoom(true);
//        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        if (!isOverKitkat()) {
            String database = Environment.getExternalStorageDirectory().getPath() + File.separator + "liwei" + File.separator + "database";
            File databaseFile = new File(database);
            if(!databaseFile.exists()){
                databaseFile.mkdir();
            }
            settings.setDatabasePath(database); //设置数据库路径
        }
        settings.setSavePassword(false); // 关闭密码保存提醒功能，防止明文密码存在本地被盗用
        settings.setUserAgentString(""); // 设置 UserAgent 属性
        settings.setAllowFileAccess(true); // 允许加载本地 html 文件
        // 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        settings.setAllowFileAccessFromFileURLs(false);
        // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
        // Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        // 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
        settings.setAllowUniversalAccessFromFileURLs(false);


        myWebview.setWebViewClient(new MyWebviewClient());
        myWebview.setWebChromeClient(new MyWebChromeClient());
        //webview注入
        myWebview.addJavascriptInterface(new AndroidNativeInterface(), "MyAndroid");
    }

    /**
     * 判断系统是否大于4.4(19)版本——在 Android 4.4 之前使用 WebKit 作为渲染内核，4.4 之后采用 chrome 内核
     * @return true-大于；false-小于
     */
    private boolean isOverKitkat() {
        return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 注入的java对象
     * 添加了@JavascriptInterface注释的方法表示，js调用原生的方法
     */
    private class AndroidNativeInterface {
        @JavascriptInterface
        public void hello(String str) {
            Log.e(Tag, "调用了hello方法，并传入参数：" + str);
        }
    }

    private class MyWebviewClient extends WebViewClient {
        /**
         * 当WebView得页面Scale值发生改变时回调
         */
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
            Log.e(Tag, "onScaleChanged");
        }

        /**
         * 是否在 WebView 内加载页面
         * 这里可以设置使用浏览器打开，或者其他方式打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        /**
         * WebView 开始加载页面时回调，一次Frame加载对应一次回调
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e(Tag, "onPageStarted");
        }

        /**
         * WebView 完成加载页面时回调，一次Frame加载对应一次回调
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(Tag, "onPageFinished");
        }

        /**
         * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.e(Tag, "onLoadResource");
        }

        /**
         * api21及以上使用此方法进行拦截，替换成本地资源
         * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
         * @param request 当前产生 request 请求
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if(request.getUrl().toString().contains("bdlogo")){
                Log.e(Tag,"api21及以上，拦截到资源请求");
                try {
                    InputStream is = getApplicationContext().getAssets().open("ic_launcher.png");
                    return new WebResourceResponse("image/png","utf-8",is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return super.shouldInterceptRequest(view, request);
        }

        /**
         * 在api21以下使用此方法进行拦截，替换成本地资源
         */
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(url.contains("bdlogo")){
                Log.e(Tag,"api21以下，拦截到资源请求");
                try {
                    InputStream is = getApplicationContext().getAssets().open("ic_launcher.png");
                    return new WebResourceResponse("image/png","utf-8",is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return super.shouldInterceptRequest(view, url);
        }

        /**
         * WebView 访问 url 出错
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.e(Tag, "onReceivedError");
        }

        /**
         * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            Log.e(Tag, "onReceivedSslError");
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        /**
         * 输出 Web 端日志
         */
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.e(Tag, "消息：" + consoleMessage.message() + "，消息等级：" + consoleMessage.messageLevel() + "，消息资源id：" + consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);
        }

        /**
         * 当前 WebView 加载网页进度
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.e(Tag, "当前进度：" + newProgress);
        }

        /**
         * Js 中调用 alert() 函数，产生的对话框
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.e(Tag, "onJsAlert方法->" + "url：" + url + "，message：" + message);
            return super.onJsAlert(view, url, message, result);
        }

        /**
         * 处理 Js 中的 Confirm 对话框
         */
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Log.e(Tag, "onJsConfirm方法->" + "url：" + url + "，message：" + message);
            return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 处理 JS 中的 Prompt对话框
         */
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Log.e(Tag, "onJsPrompt方法->" + "url：" + url + "，message：" + message);
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        /**
         * 接收web页面的icon
         */
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            Drawable drawable = new BitmapDrawable(icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            webviewTitle.setCompoundDrawables(drawable, null, null, null);
        }

        /**
         * 接收web页面的 Title
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            webviewTitle.setText(title);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        myWebview.onResume();

        //在onResume中恢复交互
        myWebview.getSettings().setJavaScriptEnabled(true);


        //恢复pauseTimers状态
//        myWebview.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        myWebview.onPause();


        //或者调用此方法暂停网页中的动画，以及其他效果(如果有的话)
        //因为不暂停的话，会导致页面在后台不会释放js，占据CPU，很耗电
        myWebview.getSettings().setJavaScriptEnabled(false);


        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
        //它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
//        myWebview.pauseTimers();
    }

    /**
     * 如何webview有网页后退功能，需要屏蔽系统的back键
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebview.canGoBack()) {
//            myWebview.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myWebview.destroy();
        myWebview = null;


        //销毁Webview
        //在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
        //但是注意：webview调用destory时,webview仍绑定在Activity上
        //这是由于自定义webview构建时传入了该Activity的context对象
        //因此需要先从父容器中移除webview,然后再销毁webview:
//        rootLayout.removeView(myWebview);
//        myWebview.destroy();
    }
}