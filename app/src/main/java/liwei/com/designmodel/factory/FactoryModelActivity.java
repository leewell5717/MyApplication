package liwei.com.designmodel.factory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.designmodel.factory.abstractfactory.FactoryA;
import liwei.com.designmodel.factory.abstractfactory.FactoryB;
import liwei.com.designmodel.factory.factorymethod.AudiA4L;
import liwei.com.designmodel.factory.factorymethod.AudiA6L;
import liwei.com.designmodel.factory.factorymethod.AudiA8L;
import liwei.com.designmodel.factory.factorymethod.FactoryAudiA4L;
import liwei.com.designmodel.factory.factorymethod.FactoryAudiA6L;
import liwei.com.designmodel.factory.factorymethod.FactoryAudiA8L;

/**
 * 工厂模式
 */
public class FactoryModelActivity extends Activity {

    @BindView(R.id.simple_factory_model_btn)
    public Button simpleFactoryModelBtn;
    @BindView(R.id.factory_method_model_btn)
    public Button factoryMethodModelBtn;
    @BindView(R.id.abstract_factory_model_btn)
    public Button abstractFactoryModelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_factory);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.simple_factory_model_btn,R.id.factory_method_model_btn,R.id.abstract_factory_model_btn})
    public void MyOnClick(View v){
        switch (v.getId()){
            case R.id.simple_factory_model_btn:
                simpleFactoryModel();
                break;
            case R.id.factory_method_model_btn:
                factoryMethodModel();
                break;
            case R.id.abstract_factory_model_btn:
                abstractFactoryModel();
                break;
        }
    }

    /**
     * 简单工厂模式
     */
    private void simpleFactoryModel(){
        liwei.com.designmodel.factory.simplefactory.Factory factory = new liwei.com.designmodel.factory.simplefactory.Factory();
        factory.createAudi(4);
        factory.createAudi(6);
        factory.createAudi(8);
    }

    /**
     * 工厂方法模式
     */
    private void factoryMethodModel(){
        FactoryAudiA4L audiA4L = new FactoryAudiA4L();
        AudiA4L a4L = audiA4L.createAudiL();
        a4L.doSomeThing();

        FactoryAudiA6L audiA6L = new FactoryAudiA6L();
        AudiA6L a6L = audiA6L.createAudiL();
        a6L.doSomeThing();

        FactoryAudiA8L audiA8L = new FactoryAudiA8L();
        AudiA8L a8L = audiA8L.createAudiL();
        a8L.doSomeThing();
    }

    /**
     * 抽象工厂模式
     */
    private void abstractFactoryModel(){
        FactoryA factoryA = new FactoryA();
        factoryA.createEngine();
        factoryA.createAir();

        FactoryB factoryB = new FactoryB();
        factoryB.createEngine();
        factoryB.createAir();
    }
}
