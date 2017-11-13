package com.github.lzyzsd.jsbridge.container;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BridgeWebViewClient extends WebViewClient {
    private BridgeWebViewContainer container;
    private boolean isError = false;
    private String errorMsg = "";

    public BridgeWebViewClient(BridgeWebViewContainer container) {
        this.container = container;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            container.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            container.flushMessageQueue();
            return true;
        } else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.e("XXX","开始加载Html");
        isError = false;
        container.showWebView();
        container.showProgressBar();
        container.hideErrorText();
        container.getShimmer().startShimmerAnimation();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e("XXX","加载Html完成");
        container.hideProgressBar();
        container.getShimmer().stopShimmerAnimation();
        if(isError){
            container.showErrorText(errorMsg);
        }else{
            container.hideErrorText();
        }

        BridgeUtil.webViewLoadLocalJs(view, BridgeWebViewContainer.toLoadJs);
        if (container.getStartupMessage() != null) {
            for (Message m : container.getStartupMessage()) {
                container.dispatchMessage(m);
            }
            container.setStartupMessage(null);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        container.getShimmer().stopShimmerAnimation();
        container.hideWebView();
        container.hideProgressBar();
        Log.e("XXX","加载Html出错，errorCode："+errorCode+"，description："+description+"，failingUrl：" + failingUrl);
        String text;
        switch (errorCode){
            case -1:
                text = "ERROR_UNKNOWN:未知错误";
                break;
            case -2:
                text = "ERROR_HOST_LOOKUP:服务器或代理服务器的主机名查找失败";
                break;
            case -3:
                text = "ERROR_UNSUPPORTED_AUTH_SCHEME:不支持的身份验证";
                break;
            case -4:
                text = "ERROR_AUTHENTICATION:用户身份验证失败";
                break;
            case -5:
                text = "ERROR_PROXY_AUTHENTICATION:用户代理身份验证失败";
                break;
            case -6:
                text = "ERROR_CONNECT:无法连接到服务器";
                break;
            case -7:
                text = "ERROR_IO:无法读取或写入服务器";
                break;
            case -8:
                Log.e("XXX","-8");
                text = "ERROR_TIMEOUT:连接超时";
                break;
            case -9:
                text = "ERROR_REDIRECT_LOOP:重定向太多";
                break;
            case -10:
                text = "ERROR_UNSUPPORTED_SCHEME:不支持的URI格式";
                break;
            case -11:
                text = "ERROR_FAILED_SSL_HANDSHAKE:执行SSL握手失败";
                break;
            case -12:
                text = "ERROR_BAD_URL:无效的URL";
                break;
            case -13:
                text = "ERROR_FILE:通用文件错误";
                break;
            case -14:
                text = "ERROR_FILE_NOT_FOUND:文件未找到";
                break;
            case -15:
                text = "ERROR_TOO_MANY_REQUESTS:负载请求过多";
                break;
            default:
                text = "";
                break;
        }
        isError = true;
        errorMsg = text;
        container.showErrorText(text);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        container.hideWebView();
        container.hideProgressBar();
        container.getShimmer().stopShimmerAnimation();
        Log.e("XXX","http错误，error："+error.toString());
        isError = true;
        errorMsg = error.toString();
        container.showErrorText(error.toString());
    }
}