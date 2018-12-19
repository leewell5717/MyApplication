package liwei.com.other.webview.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liwei.com.R;
import liwei.com.utils.Utils;
import liwei.com.other.webview.bean.ViewLayoutHandle;
import liwei.com.other.webview.facebook.ShimmerFrameLayout;

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebViewContainer extends FrameLayout implements WebViewJavascriptBridge {

    private Context context;
    public static final String toLoadJs = "WebViewJavascriptBridge.js";
    Map<String, CallBackFunction> responseCallbacks = new HashMap<>();
    Map<String, BridgeHandler> messageHandlers = new HashMap<>();
    BridgeHandler defaultHandler = new DefaultHandler();

    private View rootView;
    private ShimmerFrameLayout shimmerContainer;
    private WebView webView;
    private TextView errorText;

    //描边颜色
    private int strokeColor = Color.WHITE;
    //描边宽度
    private float strokeWidth = 0;
    //圆角大小
    private float radius = 0;

    private List<Message> startupMessage = new ArrayList<>();

    public List<Message> getStartupMessage() {
        return startupMessage;
    }

    public void setStartupMessage(List<Message> startupMessage) {
        this.startupMessage = startupMessage;
    }

    private long uniqueId = 0;

    public BridgeWebViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public BridgeWebViewContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public BridgeWebViewContainer(Context context) {
        super(context);
        this.context = context;
        init();
    }

    /**
     * @param handler default handler,handle messages send by js without assigned handler name,
     *                if js message has handler name, it will be handled by named handlers registered by native
     */
    public void setDefaultHandler(BridgeHandler handler) {
        this.defaultHandler = handler;
    }

    private void init() {
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_webview, null);
        shimmerContainer = (ShimmerFrameLayout) rootView.findViewById(R.id.shimmer_progress);
        webView = (WebView) rootView.findViewById(R.id.bridge_webview);
        errorText = (TextView) rootView.findViewById(R.id.error_text);

        //设置webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.clearHistory();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(generateBridgeWebViewClient());
    }

    /**
     * 添加子View
     */
    public void addChildView(ViewLayoutHandle viewLayoutHandle,int screenWidth,int screenHeight) {
        View v = new View(context);
        //外矩形 左上、右上、右下、左下 圆角半径
        float[] outerRadii = {radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, null); //无内矩形
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        drawable.getPaint().setColor(strokeColor);
        drawable.getPaint().setStrokeWidth(strokeWidth);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.STROKE);//描边
        v.setBackground(drawable);

        LayoutParams lp;
        if (viewLayoutHandle.getLayoutType() == 1) { //坐标布局
            lp = new LayoutParams(Utils.px2dip(context, viewLayoutHandle.getWidth()),
                    Utils.px2dip(context, viewLayoutHandle.getHeight()));
            lp.gravity = Gravity.CENTER;
        } else { //比例布局
            lp = new LayoutParams((int) (screenWidth * viewLayoutHandle.getWidthScale()),
                    (int) (screenHeight * viewLayoutHandle.getHeightScale()));
            lp.gravity = Gravity.CENTER;
        }
        addView(rootView, lp);
        addView(v, lp);
    }
    /**
     * 添加子View
     */
    public void addChildView() {
        View v = new View(context);
        //外矩形 左上、右上、右下、左下 圆角半径
        float[] outerRadii = {radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, null); //无内矩形
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        drawable.getPaint().setColor(strokeColor);
        drawable.getPaint().setStrokeWidth(strokeWidth);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.STROKE);//描边
        v.setBackground(drawable);

        LayoutParams lp = new LayoutParams(Utils.dp2px(context,400), Utils.dp2px(context,300));
        addView(rootView, lp);
//        addView(v, lp);
    }

    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }

    void handlerReturnData(String url) {
        String functionName = BridgeUtil.getFunctionFromReturnUrl(url);
        CallBackFunction f = responseCallbacks.get(functionName);
        String data = BridgeUtil.getDataFromReturnUrl(url);
        if (f != null) {
            f.onCallBack(data);
            responseCallbacks.remove(functionName);
        }
    }

    @Override
    public void send(String data) {
        send(data, null);
    }

    @Override
    public void send(String data, CallBackFunction responseCallback) {
        doSend(null, data, responseCallback);
    }

    @Override
    public void send(String name, String data, CallBackFunction responseCallback) {
        doSend(name, data, responseCallback);
    }

    private void doSend(String handlerName, String data, CallBackFunction responseCallback) {
        Message m = new Message();
        if (!TextUtils.isEmpty(data)) {
            m.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(BridgeUtil.CALLBACK_ID_FORMAT, ++uniqueId + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            m.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.setHandlerName(handlerName);
        }
        queueMessage(m);
    }

    private void queueMessage(Message m) {
        if (startupMessage != null) {
            startupMessage.add(m);
        } else {
            dispatchMessage(m);
        }
    }

    void dispatchMessage(Message m) {
        String messageJson = m.toJson();
        //escape special characters for json string
//        messageJson = messageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
//        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
//        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            webView.loadUrl(javascriptCommand);
        }
    }

    void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    // deserializeMessage
                    List<Message> list;
                    try {
                        list = Message.toArrayList(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        Message m = list.get(i);
                        String responseId = m.getResponseId();
                        // 是否是response
                        if (!TextUtils.isEmpty(responseId)) {
                            CallBackFunction function = responseCallbacks.get(responseId);
                            String responseData = m.getResponseData();
                            function.onCallBack(responseData);
                            responseCallbacks.remove(responseId);
                        } else {
                            CallBackFunction responseFunction;
                            // if had callbackId
                            final String callbackId = m.getCallbackId();
                            if (!TextUtils.isEmpty(callbackId)) {
                                responseFunction = new CallBackFunction() {
                                    @Override
                                    public void onCallBack(String data) {
                                        Message responseMsg = new Message();
                                        responseMsg.setResponseId(callbackId);
                                        responseMsg.setResponseData(data);
                                        queueMessage(responseMsg);
                                    }
                                };
                            } else {
                                responseFunction = new CallBackFunction() {
                                    @Override
                                    public void onCallBack(String data) {
                                        // do nothing
                                    }
                                };
                            }
                            BridgeHandler handler;
                            if (!TextUtils.isEmpty(m.getHandlerName())) {
                                handler = messageHandlers.get(m.getHandlerName());
                            } else {
                                handler = defaultHandler;
                            }
                            if (handler != null) {
                                handler.handler(m.getData(), responseFunction);
                            }
                        }
                    }
                }
            });
        }
    }

    public void loadUrl(String jsUrl, CallBackFunction returnCallback) {
        webView.loadUrl(jsUrl);
        responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl), returnCallback);
    }

    /**
     * register handler,so that javascript can call it
     */
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            messageHandlers.put(handlerName, handler);
        }
    }

    /**
     * call javascript registered handler
     */
    public void callHandler(String handlerName, String data, CallBackFunction callBack) {
        doSend(handlerName, data, callBack);
    }

    /**
     * 显示progressBar
     */
    public void showProgressBar() {
        shimmerContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏progressBar
     */
    public void hideProgressBar() {
        shimmerContainer.setVisibility(View.INVISIBLE);
    }

    public ShimmerFrameLayout getShimmer() {
        return shimmerContainer;
    }

    public WebView getWebView() {
        return webView;
    }

    /**
     * 隐藏webview
     */
    public void hideWebView() {
        webView.setVisibility(View.GONE);
    }

    /**
     * 显示webview
     */
    public void showWebView() {
        webView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示errorText
     */
    public void showErrorText(String text) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(text);
    }

    /**
     * 隐藏errorText
     */
    public void hideErrorText() {
        errorText.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置描边颜色
     */
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * 设置描边宽度
     */
    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * 设置圆角大小
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

}