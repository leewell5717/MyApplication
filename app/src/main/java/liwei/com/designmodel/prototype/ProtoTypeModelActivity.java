package liwei.com.designmodel.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import liwei.com.R;

/**
 * 原型模式Activity
 */
public class ProtoTypeModelActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_prototype);
    }
}