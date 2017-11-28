package liwei.com.other.webview.api;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import java.io.EOFException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import liwei.com.retrofit.utils.ApiException;
import liwei.com.retrofit.utils.RxManager;

public abstract class RxObserver<T> implements Observer<T>{

    private Context mContext;
    private String mKey;
    private boolean isShowDialog;
    private int mWhichRequest;

    private Dialog mDialog;

    private liwei.com.retrofit.utils.RxManager mRxManager;


    /**
     * @param  key：是用来区分不同类中联网的CompositeDisposable的，以便在这个类销毁时，可以取消该类中订阅关系，建议采用包名+ 类名作为key
     * @param whichRequest：区分不同的请求，用于多个联网请求结束后对不同请求的处理
     * @param isShowDialog：是否显示加载框，RxObserver内部实例化了一个加载框，可根据需求设置是否显示
     */
    public RxObserver(Context context, String key, int whichRequest, boolean isShowDialog) {
        this.mContext = context;
        this.mKey = key;
        this.isShowDialog = isShowDialog;
        this.mWhichRequest = whichRequest;
        mDialog = new ProgressDialog(context);
        mDialog.setTitle("请稍后");

        mRxManager = RxManager.getInstance();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mRxManager.add(mKey, d);
        if (isShowDialog) {
            mDialog.show();
        }
        onStart(mWhichRequest);
    }

    @Override
    public void onNext(T value) {
        onSuccess(mWhichRequest, value);
    }

    @Override
    public void onError(Throwable e) {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (e instanceof EOFException || e instanceof ConnectException ||
                e instanceof SocketException || e instanceof BindException ||
                e instanceof SocketTimeoutException || e instanceof UnknownHostException) {
            Toast.makeText(mContext, "网络异常，请稍后重试！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ApiException) {
            onError(mWhichRequest, e);
        } else {
            Toast.makeText(mContext, "未知错误！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public abstract void onSuccess(int whichRequest, T t);

    public abstract void onError(int whichRequest, Throwable e);

    public void onStart(int whichRequest) {

    }
}