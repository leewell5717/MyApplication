package liwei.com.designmodel.observer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 观察者模式
 */
public class ObserverModelActivity extends Activity {

    @BindView(R.id.zhangsan_btn)
    public Button zhangsanBtn;
    @BindView(R.id.lisi_btn)
    public Button lisiBtn;
    @BindView(R.id.wangwu_btn)
    public Button wangwuBtn;
    @BindView(R.id.publish_btn)
    public Button publishBtn;

    private WeChatObserverable observerable;

    private static final String[] messages = {"how are you?","im fine,3q!","how old are you?","what do you do?","nice to meet you","good moorning","bye bye"};
    private static final String ZhangSan = "张三";
    private static final String LiSi = "李四";
    private static final String WangWu = "王五";

    private Observer zhangsanObserver = new User(ZhangSan);
    private Observer lisiObserver = new User(LiSi);
    private Observer wangwuObserver = new User(WangWu);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_observer);
        ButterKnife.bind(this);

        observerable = new WeChatObserverable();
        zhangsanBtn.setText("张三未订阅");
        lisiBtn.setText("李四未订阅");
        wangwuBtn.setText("王五未订阅");
    }

    @OnClick(R.id.zhangsan_btn)
    public void OnClick1(){
        if(observerable.isSubscribed(ZhangSan)){
            zhangsanBtn.setText("张三未订阅");
            observerable.removeObserver(zhangsanObserver,ZhangSan);
        }else{
            zhangsanBtn.setText("张三已订阅");
            Toast.makeText(ObserverModelActivity.this,"再次点击取消订阅",Toast.LENGTH_SHORT).show();
            observerable.registerObserver(zhangsanObserver,ZhangSan);
        }
    }

    @OnClick(R.id.lisi_btn)
    public void OnClick2(){
        if(observerable.isSubscribed(LiSi)){
            lisiBtn.setText("李四未订阅");
            observerable.removeObserver(lisiObserver,LiSi);
        }else{
            lisiBtn.setText("李四已订阅");
            Toast.makeText(ObserverModelActivity.this,"再次点击取消订阅",Toast.LENGTH_SHORT).show();
            observerable.registerObserver(lisiObserver,LiSi);
        }
    }

    @OnClick(R.id.wangwu_btn)
    public void OnClick3(){
        if(observerable.isSubscribed(WangWu)){
            wangwuBtn.setText("王五未订阅");
            observerable.removeObserver(wangwuObserver,WangWu);
        }else{
            wangwuBtn.setText("王五已订阅");
            Toast.makeText(ObserverModelActivity.this,"再次点击取消订阅",Toast.LENGTH_SHORT).show();
            observerable.registerObserver(wangwuObserver,WangWu);
        }
    }

    @OnClick(R.id.publish_btn)
    public void OnClick4(){
        Random random = new Random();
        observerable.setMessage(messages[random.nextInt(messages.length)]);
        Log.e("XXX","------------------------------------------------------");
    }
}