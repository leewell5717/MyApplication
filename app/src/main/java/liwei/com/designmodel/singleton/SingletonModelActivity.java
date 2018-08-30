package liwei.com.designmodel.singleton;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 单例模式Activity
 */
public class SingletonModelActivity extends Activity {
    @BindViews({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public List<Button> buttons;
    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5})
    public List<TextView> textViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_singleton);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                String str1 = SingletonModel.MySingletonModelOne.getInstance().getValueString();
                textViews.get(0).setText(str1);
                break;
            case R.id.button2:
                String str2 = SingletonModel.MySingletonModelTwo.getInstance().getValueString();
                textViews.get(1).setText(str2);
                break;
            case R.id.button3:
                String str3 = SingletonModel.MySingletonModelThree.getInstance().getValueString();
                textViews.get(2).setText(str3);
                break;
            case R.id.button4:
                String str4 = SingletonModel.MySingletonModelFour.getInstance().getValueString();
                textViews.get(3).setText(str4);
                break;
            case R.id.button5:
                String str5 = SingletonModel.MySingletonModelFive.instance.getValueString();
                textViews.get(4).setText(str5);
                break;
        }
    }
}