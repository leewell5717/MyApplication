package liwei.com.other.mybase;

import android.view.View;
import android.widget.TextView;

import liwei.com.R;
import liwei.com.Utils;

public class MyTestActivity extends MyBaseActivity {


    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initViews() {
        title.setText("这是继承的");
        TextView testText = (TextView)findViewById(R.id.test_text);
        testText.setText("Hello");
        actionBtn.setVisibility(View.GONE);
    }

    @Override
    public void doAction() {
        Utils.showToastCenter("点击了ActionBtn");
    }

}