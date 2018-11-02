package liwei.com.designmodel.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.designmodel.adapter.classadapter.Real220V;
import liwei.com.designmodel.adapter.classadapter.power220V;

/**
 * 适配器模式Activity
 */
public class AdapterModelActivity extends Activity {

    @BindView(R.id.class_adapter_btn)
    public Button classAdapterBtn;
    @BindView(R.id.object_adapter_btn)
    public Button objectAdapterBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_adapter);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.class_adapter_btn)
    public void OnClick1(){
        power220V power220V = new Real220V();
        power220V.get220V();
    }

    @OnClick(R.id.object_adapter_btn)
    public void OnClick2(){
        liwei.com.designmodel.adapter.objectadapter.power220V power220V = new liwei.com.designmodel.adapter.objectadapter.Real220V(new liwei.com.designmodel.adapter.objectadapter.Real5V());
        power220V.get220V();
    }
}