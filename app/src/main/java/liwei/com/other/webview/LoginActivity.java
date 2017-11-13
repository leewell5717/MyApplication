package liwei.com.other.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.LinearLayout;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

import liwei.com.R;
import liwei.com.Utils;
import liwei.com.other.webview.bean.PageCloseType;
import liwei.com.other.webview.bean.ViewLayoutHandle;

public class LoginActivity extends Activity {

    private LinearLayout rootContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Intent intent = getIntent();
        String pagePath = intent.getStringExtra("pagePath");
        int pageOpenType = intent.getIntExtra("pageOpenType",0);
        ViewLayoutHandle viewLayoutHandle = (ViewLayoutHandle) intent.getSerializableExtra("viewLayoutHandle");
        rootContainer = (LinearLayout)findViewById(R.id.root_container);
        BridgeWebView mWebview = (BridgeWebView)findViewById(R.id.dialog_webview);
        if(viewLayoutHandle.getLayoutType() == 1){ //坐标布局
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Utils.px2dip(LoginActivity.this,viewLayoutHandle.getWidth()),
                    Utils.px2dip(LoginActivity.this,viewLayoutHandle.getHeight()));
            rootContainer.setX(Utils.px2dip(LoginActivity.this,viewLayoutHandle.getX()));
            rootContainer.setY(Utils.px2dip(LoginActivity.this,viewLayoutHandle.getY()));
            rootContainer.setLayoutParams(lp);
        }else{ //比例布局
            ViewGroup.LayoutParams lp = rootContainer.getLayoutParams();
            lp.width = (int)(screenWidth * viewLayoutHandle.getWidthScale());
            lp.height = (int)(screenHeight * viewLayoutHandle.getHeightScale());
            rootContainer.setLayoutParams(lp);
            rootContainer.setX(Utils.px2dip(LoginActivity.this,viewLayoutHandle.getX()));
            rootContainer.setY(Utils.px2dip(LoginActivity.this,viewLayoutHandle.getY()));
        }
        mWebview.loadUrl(pagePath);

        if(pageOpenType == 1){ //从右往左
            getWindow().setWindowAnimations(R.style.MyAnim1);
        }else if(pageOpenType == 2){ //从下往上
            getWindow().setWindowAnimations(R.style.MyAnim2);
        }


        mWebview.setDefaultHandler(new DefaultHandler());
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.registerHandler("closeViewHandle", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("XXXXX", "handler = closeViewHandle(关闭窗口), data from Js = " + data);
//                function.onCallBack("openViewHandle exe, response data 中文 from Java");
                Gson gson = new Gson();
                PageCloseType pageCloseType = gson.fromJson(data,PageCloseType.class);

                LoginActivity.this.finish();
            }
        });
    }
}