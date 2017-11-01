package liwei.com.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

public class OtherActivity extends Activity {
    @BindView(R.id.button)
    public Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button})
    public void click(View v){
        MyMessage myMessage = new MyMessage();
        myMessage.setMessage("Hello World" + System.currentTimeMillis());
        EventBus.getDefault().post(myMessage);
        finish();
    }
}