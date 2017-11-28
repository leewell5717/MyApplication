package liwei.com.other.picker;

import android.view.View;

import liwei.com.other.mybase.MyBaseActivity;

/**
 * 仿IOS风格的时间、日期选择器
 */
public class PickViewActivity extends MyBaseActivity {


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
        title.setText("仿IOS风格的日期-时间选择器");
    }
}