package liwei.com.designmodel.facade;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 外观模式
 * 说明：外观模式其实就是对方法进行了封装调用
 */
public class FacadeModelActivity extends Activity {

    @BindView(R.id.turn_on_btn)
    public Button turnOnBtn;
    @BindView(R.id.turn_off_btn)
    public Button turnOffBtn;

    private FacadeCenter center;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_facade);
        ButterKnife.bind(this);

        center = new FacadeCenter();
    }

    @OnClick({R.id.turn_on_btn,R.id.turn_off_btn})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.turn_on_btn:
                center.turnOn();
                break;
            case R.id.turn_off_btn:
                center.turnOff();
                break;
        }
    }
}