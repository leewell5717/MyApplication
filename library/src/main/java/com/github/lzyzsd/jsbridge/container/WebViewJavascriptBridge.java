package com.github.lzyzsd.jsbridge.container;


public interface WebViewJavascriptBridge {
	void send(String data);
	void send(String data, CallBackFunction responseCallback);
}