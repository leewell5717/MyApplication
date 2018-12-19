package liwei.com.other.webview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import liwei.com.R;
import liwei.com.utils.Utils;
import liwei.com.other.webview.api.Api;
import liwei.com.other.webview.api.DownLoadManager;
import liwei.com.other.webview.api.RxFunction;
import liwei.com.other.webview.api.RxManager;
import liwei.com.other.webview.api.RxObserver;
import liwei.com.other.webview.api.RxSchedulers;
import liwei.com.other.webview.bean.OpenActivityViewHandle;
import liwei.com.other.webview.bean.OpenViewHandle;
import liwei.com.other.webview.bean.PageCloseType;
import liwei.com.other.webview.bean.RequestThoughNative;
import liwei.com.other.webview.bean.ViewLayoutHandle;
import liwei.com.other.webview.jsbridge.BridgeHandler;
import liwei.com.other.webview.jsbridge.BridgeWebViewContainer;
import liwei.com.other.webview.jsbridge.CallBackFunction;
import liwei.com.other.webview.jsbridge.DefaultHandler;
import liwei.com.other.webview.slice.Slice;
import okhttp3.ResponseBody;

public class WebviewActivity extends Activity{

    private WebView webview;

    private int screenWidth;
    private int screenHeight;

    private static final String TAG = WebviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        BridgeWebViewContainer webViewContainer = (BridgeWebViewContainer)findViewById(R.id.webview_container);

        webview = webViewContainer.getWebView();
        webViewContainer.setDefaultHandler(new DefaultHandler());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("http://test.17byh.com/html/views/login.html");
        webViewContainer.registerHandler("viewLayoutHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = viewLayoutHandle(调整view布局大小), data from Js = " + data);
                Gson gson = new Gson();
                ViewLayoutHandle viewLayoutHandle = gson.fromJson(data,ViewLayoutHandle.class);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)(screenWidth*viewLayoutHandle.getWidthScale()),
                        (int)(screenHeight*viewLayoutHandle.getHeightScale()));
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                //设置圆角
//                Slice sliceRoot = new Slice(webview);
//                sliceRoot.setElevation(2.0f);
//                sliceRoot.setRadius(viewLayoutHandle.getCornerRadius());
                webview.setLayoutParams(lp);
            }
        });
        webViewContainer.registerHandler("openViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openViewHandle(打开新窗口), data from Js = " + data);
//                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                Gson gson = new Gson();
                OpenViewHandle viewHandle = gson.fromJson(data,OpenViewHandle.class);
//                showLoginDialog(WebviewActivity.this,viewHandle);
                showLoginDialog3(WebviewActivity.this,viewHandle);
            }
        });

        //网络请求
        webViewContainer.registerHandler("requestThoughNative", new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                Log.e("XXXXX", "handler = requestThoughNative(网络请求), data from Js = " + data);
//                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                Gson gson = new Gson();
                RequestThoughNative requestThoughNative = gson.fromJson(data,RequestThoughNative.class);
                Api.getDefaultService()
                        .dataBean("2017-8-30")
                        .map(new RxFunction<RequestThoughNative>())
                        .compose(RxSchedulers.<RequestThoughNative>io_main())
                        .subscribeWith(new RxObserver<RequestThoughNative>(WebviewActivity.this, TAG, 0, false) {
                            @Override
                            public void onSuccess(int whichRequest, RequestThoughNative requestThoughNative) {
                                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                            }
                            @Override
                            public void onError(int whichRequest, Throwable e) {

                            }
                        });
            }
        });

        //弹窗
        webViewContainer.registerHandler("openActivityViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = openActivityViewHandle(打开弹窗), data from Js = " + data);
                Gson gson = new Gson();
                OpenActivityViewHandle openActivityViewHandle = gson.fromJson(data,OpenActivityViewHandle.class);
                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                showCustomDialog(WebviewActivity.this,openActivityViewHandle);
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


//        OpenActivityViewHandle op = new OpenActivityViewHandle();
//        op.setMessage("这是一串啊哈呢阿斯蒂芬阿飞");
//        op.setTitle("温馨提示");
//        op.setTextFiled(false);
//        op.setDisplayTime(0);
//        List<String> list = new ArrayList<>();
//        list.add("确定");
//        op.setActionArr(list);
//        readContent();
//        showCustomDialog(WebviewActivity.this,op);
    }

    /**
     * 根据js返回的数据，显示自定义dialog
     */
    private void showCustomDialog(Context context,OpenActivityViewHandle openActivityViewHandle){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_custom);
        TextView dialogTitle = (TextView)dialog.findViewById(R.id.dialog_title);
        TextView dialogContent = (TextView)dialog.findViewById(R.id.dialog_content);
        EditText dialogInput = (EditText)dialog.findViewById(R.id.dialog_input);
        LinearLayout dialogInputContainer = (LinearLayout)dialog.findViewById(R.id.dialog_input_container);
        LinearLayout dialogBtnContainer = (LinearLayout)dialog.findViewById(R.id.dialog_btn_container);
        dialogTitle.setText(openActivityViewHandle.getTitle());
        dialogContent.setText(openActivityViewHandle.getMessage());
        if(openActivityViewHandle.isTextFiled()){
            dialogInputContainer.setVisibility(View.VISIBLE);
        }else{
            dialogInputContainer.setVisibility(View.GONE);
        }

        List<String> btnList = openActivityViewHandle.getActionArr();
        if(btnList == null || btnList.size() == 0){
            TextView confirmBtn = new TextView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            confirmBtn.setText("确定");
            confirmBtn.setGravity(Gravity.CENTER);
            confirmBtn.setTextColor(Color.parseColor("#218AFD"));
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialogBtnContainer.addView(confirmBtn,lp);
        }else if(btnList.size() == 1){
            TextView confirmBtn = new TextView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            confirmBtn.setText(btnList.get(0));
            confirmBtn.setGravity(Gravity.CENTER);
            confirmBtn.setTextColor(Color.parseColor("#218AFD"));
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Api.getDefaultService()
                            .downloadFile("https://raw.githubusercontent.com/winterzou/RequestTest/master/standbyFile.txt")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResponseBody>() {
                                @Override
                                public void accept(ResponseBody responseBody) throws Exception {
                                    DownLoadManager.writeResponseBodyToDisk(responseBody);
                                    dialog.dismiss();
                                }
                            });
                }
            });
            dialogBtnContainer.addView(confirmBtn,lp);
        }else{
            for(int i=0;i<btnList.size();i++){
                LinearLayout.LayoutParams textLP = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                LinearLayout.LayoutParams lineLP = new LinearLayout.LayoutParams(Utils.dp2px(context,1),LinearLayout.LayoutParams.MATCH_PARENT);

                TextView textBtn = new TextView(context);
                textBtn.setText(btnList.get(i));
                textBtn.setGravity(Gravity.CENTER);
                textBtn.setTextColor(Color.parseColor("#218AFD"));
                textBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialogBtnContainer.addView(textBtn,textLP);
                if(i < btnList.size() - 1){
                    View line = new View(context);
                    line.setBackgroundColor(Color.parseColor("#D6D6D6"));
                    dialogBtnContainer.addView(line,lineLP);
                }
            }
        }
        dialog.setCanceledOnTouchOutside(false);
        if(Math.abs(openActivityViewHandle.getDisplayTime() - 0.0f) > 0){
            //自动关闭
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            },(long)openActivityViewHandle.getDisplayTime());
        }
        dialog.show();
    }

    private void readContent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 通过网络地址创建URL对象
                    URL url = new URL("https://raw.githubusercontent.com/winterzou/RequestTest/master/standbyFile.txt");
                    // 根据URL
                    // 打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 设定URL的请求类别，有POST、GET 两类
                    conn.setRequestMethod("GET");
                    //设置从主机读取数据超时（单位：毫秒）
                    conn.setConnectTimeout(5000);
                    //设置连接主机超时（单位：毫秒）
                    conn.setReadTimeout(5000);
                    // 通过打开的连接读取的输入流,获取html数据
                    InputStream inStream = conn.getInputStream();
                    // 得到html的二进制数据

                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }
                    inStream.close();
                    byte[] data = outStream.toByteArray();
                    // 是用指定的字符集解码指定的字节数组构造一个新的字符串
                    String html = new String(data, "utf-8");
                    Log.e("XXX","获取网页中的内容：" + html);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showLoginDialog(final Context context, final OpenViewHandle viewHandle){
        final Dialog dialog = new Dialog(context,R.style.MyDialog2);
        dialog.setContentView(R.layout.dialog_login);
        BridgeWebViewContainer container = (BridgeWebViewContainer)dialog.findViewById(R.id.webview_container);
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
        BridgeWebViewContainer container = (BridgeWebViewContainer)dialog.findViewById(R.id.webview_container);
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

        if(viewHandle.getPageOpenType() == 1){ //从右往左
            dialogWindow.setWindowAnimations(R.style.MyAnim1);
        }else if(viewHandle.getPageOpenType() == 2){ //从下往上
            dialogWindow.setWindowAnimations(R.style.MyAnim2);
        }
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showLoginDialog3(final Context context, final OpenViewHandle viewHandle){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_login);
        BridgeWebViewContainer container = (BridgeWebViewContainer)dialog.findViewById(R.id.webview_container);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
    }
}