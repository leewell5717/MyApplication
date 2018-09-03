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
import liwei.com.designmodel.proxy.ProxyActivity;
import liwei.com.designmodel.singleton.SingletonModelActivity;

/**
 * 设计模式主页
 */
public class DesignModelMainActivity extends Activity{

    @BindViews({R.id.singleton_model,R.id.builder_model,R.id.prototype_model,R.id.proxy_model})
    public List<Button> buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.singleton_model,R.id.builder_model,R.id.prototype_model,R.id.proxy_model})
    public void myOnclick(View view){
        switch (view.getId()){
            case R.id.singleton_model: //单例模式
                startTheActivity(SingletonModelActivity.class);
                break;
            case R.id.builder_model: //builder构造者模式
                startTheActivity(BuilderModelActivity.class);
                break;
            case R.id.prototype_model: //原型模式
                startTheActivity(ProtoTypeModelActivity.class);
                break;
            case R.id.proxy_model: //代理模式
                startTheActivity(ProxyActivity.class);
                break;
        }
    }

    private void startTheActivity(Class<?> cls){
        Intent intent = new Intent(DesignModelMainActivity.this,cls);
        startActivity(intent);
    }
}