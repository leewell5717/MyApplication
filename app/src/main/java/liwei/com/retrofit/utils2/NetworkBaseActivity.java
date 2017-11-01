package liwei.com.retrofit.utils2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NetworkBaseActivity extends Activity {
    public ProgressDialog pd;
    public ObservableTransformer<Observable, ObservableSource> composeFunction;
    private final long RETRY_TIMES = 1;
    private boolean showLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        pd = new ProgressDialog(this);

        composeFunction = new ObservableTransformer <Observable, ObservableSource>() {
            @Override
            public ObservableSource apply(Observable observable) {
                return observable.retry(RETRY_TIMES)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (NetworkUtil.isNetConnected(NetworkBaseActivity.this)) {
                                    if (showLoading) {
                                        if(pd != null && !pd.isShowing()){
                                            pd.show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(NetworkBaseActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void setLoadingFlag(boolean show) {
        showLoading = show;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }
}