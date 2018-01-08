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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import liwei.com.R;

public class RxJavaActivity extends Activity {

    private static final String tag = RxJavaActivity.class.getSimpleName();

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
                Observable.just(1,2,3,4,5)
                        .take(3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e(tag,"***:"+integer);
                            }
                        });
                Observable.just(1,2,3,4,5)
                        .takeLast(3)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e(tag,"$$$:"+integer);
                            }
                        });
                break;
        }
    }
}