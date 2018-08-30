package liwei.com.designmodel.builder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * builder模式Activity
 */
public class BuilderModelActivity extends Activity {
    @BindView(R.id.create_btn)
    public Button createBtn;
    @BindViews({R.id.et_os, R.id.et_board, R.id.et_cpu, R.id.et_memory, R.id.et_videocard})
    public List<EditText> editTexts;
    @BindView(R.id.create_result)
    public TextView createResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_builder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.create_btn)
    public void myOnClick() {
        Builder builder = new MacComputerBuilder();
        builder.buildOs(editTexts.get(0).getText().toString().trim())
                .buildBoard(editTexts.get(1).getText().toString().trim())
                .buildCpu(editTexts.get(2).getText().toString().trim())
                .buildMemory(editTexts.get(3).getText().toString().trim())
                .buildVideocard(editTexts.get(4).getText().toString().trim())
                .createComputer();
        String str1 = builder.toString();

        WindowsComputerProduct.Builder builder1 = new WindowsComputerProduct.Builder();
        builder1.setOs(editTexts.get(0).getText().toString().trim())
                .setBoard(editTexts.get(1).getText().toString().trim())
                .setCpu(editTexts.get(2).getText().toString().trim())
                .build();
        String str2 = builder1.toString();

        createResult.setText("您组装的电脑配置是：" +str1 +"\n" + str2);

    }
}