package liwei.com.other.picker;

import android.view.View;

import liwei.com.other.mybase.MyBaseActivity;

/**
 * Material 风格的时间-日期选择器
 */
public class MaterialDateTimePickerActivity extends MyBaseActivity {


    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initViews() {
        actionBtn.setVisibility(View.GONE);
        title.setText("Material风格的日期-时间选择器");
    }
}