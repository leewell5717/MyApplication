package liwei.com.other.webview.jsbridge;


public interface WebViewJavascriptBridge {
	void send(String data);
	void send(String data, CallBackFunction responseCallback);
	void send(String name,String data,CallBackFunction responseCallback);
}