package liwei.com.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import liwei.com.R;

public class RxJavaActivity extends Activity {
    private static final String Tag = RxJavaActivity.class.getSimpleName();
    @BindView(R.id.rx_btn)
    public Button rxBtn;
    @BindView(R.id.get_btn)
    public Button getBtn;

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rx_btn,R.id.get_btn})
    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.rx_btn:
                Flowable.create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                        e.onNext(4);
                        e.onComplete();
                        Log.e(Tag,"数据发送完成");
                    }
                },BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                                Log.e(Tag,"onSubscribe");
                                mSubscription = s;
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.e(Tag,integer+"");
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.e(Tag,"onError");
                            }

                            @Override
                            public void onComplete() {
                                Log.e(Tag,"onComplete");
                            }
                        });
                break;
            case R.id.get_btn:
                mSubscription.request(1);
                break;
        }
    }
}