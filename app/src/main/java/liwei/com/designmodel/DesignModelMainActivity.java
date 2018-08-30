package liwei.com.designmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.designmodel.builder.BuilderModelActivity;
import liwei.com.designmodel.prototype.ProtoTypeModelActivity;
import liwei.com.designmodel.singleton.SingletonModelActivity;

/**
 * 设计模式主页
 */
public class DesignModelMainActivity extends Activity{

    @BindViews({R.id.singleton_model,R.id.builder_model,R.id.prototype_model})
    public List<Button> buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.singleton_model,R.id.builder_model,R.id.prototype_model})
    public void myOnclick(View view){
        switch (view.getId()){
            case R.id.singleton_model:
                startTheActivity(SingletonModelActivity.class);
                break;
            case R.id.builder_model:
                startTheActivity(BuilderModelActivity.class);
                break;
            case R.id.prototype_model:
                startTheActivity(ProtoTypeModelActivity.class);
                break;
        }
    }

    private void startTheActivity(Class<?> cls){
        Intent intent = new Intent(DesignModelMainActivity.this,cls);
        startActivity(intent);
    }
}