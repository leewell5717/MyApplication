package liwei.com.other.picker;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import liwei.com.R;
import liwei.com.other.mybase.MyBaseActivity;
import liwei.com.other.picker.lineartimepicker.materialiconlib.ui.MaterialIconLibActivity;

/**
 * 日历-日期-时间选择器主页
 */
public class CalendarDateTimeActivity extends MyBaseActivity implements View.OnClickListener{

    @Override
    public int getLayout() {
        return R.layout.activity_calender_time_picker;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initViews() {
        actionBtn.setVisibility(View.GONE);
        title.setText("日历-日期-时间选择器");
        Button materialIconLibBtn = (Button)findViewById(R.id.material_icon_lib_btn);
        Button linearTimePickerBtn = (Button)findViewById(R.id.linear_time_picker_btn);
        Button pickviewBtn = (Button)findViewById(R.id.pickview_btn);
        Button materialDateTimeBtn = (Button)findViewById(R.id.material_date_time_btn);
        materialIconLibBtn.setOnClickListener(this);
        linearTimePickerBtn.setOnClickListener(this);
        pickviewBtn.setOnClickListener(this);
        materialDateTimeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.material_icon_lib_btn:
                intent = new Intent(CalendarDateTimeActivity.this, MaterialIconLibActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_time_picker_btn:
                intent = new Intent(CalendarDateTimeActivity.this, LinearTimePickerActivity.class);
                startActivity(intent);
                break;
            case R.id.pickview_btn:
                intent = new Intent(CalendarDateTimeActivity.this, PickViewActivity.class);
                startActivity(intent);
                break;
            case R.id.material_date_time_btn:
                intent = new Intent(CalendarDateTimeActivity.this, MaterialDateTimePickerActivity.class);
                startActivity(intent);
                break;
        }
    }
}