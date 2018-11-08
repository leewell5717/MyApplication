package liwei.com.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import liwei.com.R;

public class RxJavaActivity extends Activity {

    @BindView(R.id.rx_btn)
    public Button rxBtn;
    @BindView(R.id.result)
    public TextView result;
    @BindView(R.id.username)
    public EditText username;
    @BindView(R.id.password)
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_btn})
    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.rx_btn:
                Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                        e.onComplete();
                    }
                });

                Observer<Integer> observer = new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("XXX","onSubscribe");
                    }
                    @Override
                    public void onNext(Integer integer) {
                        Log.e("XXX","integer:" + integer);
                    }
                    @Override
                    public void onError(Throwable t) {
                        Log.e("XXX","onError");
                    }
                    @Override
                    public void onComplete() {
                        Log.e("XXX","onComplete");
                    }
                };
                observable.subscribe(observer);

                Observable.just(1,2,3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "哈哈哈：" + integer;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("XXX",s);
                    }
                });

                break;
        }
    }
}