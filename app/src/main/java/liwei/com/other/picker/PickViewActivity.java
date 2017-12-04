package liwei.com.other.picker;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import liwei.com.R;
import liwei.com.other.mybase.MyBaseActivity;
import liwei.com.other.picker.pickview.LoopScrollListener;
import liwei.com.other.picker.pickview.LoopView;
import liwei.com.other.picker.pickview.popwindow.DatePickerPopWin;
import liwei.com.other.picker.pickview.popwindow.TimePickerPopWin;
import liwei.com.other.picker.pickview.provincepick.ProvinceModel;
import liwei.com.other.picker.pickview.provincepick.ProvincePickPopWin;
import liwei.com.other.picker.pickview.provincepick.utils.ProvinceInfoParserTask;
import liwei.com.other.picker.pickview.provincepick.utils.ProvinceInfoUtils;

/**
 * 仿IOS风格的时间、日期选择器
 * 目前2月份选择日期有30、31号bug
 */
public class PickViewActivity extends MyBaseActivity implements ProvincePickPopWin.OnAddressPickCompletedListener {

    private ArrayList<ProvinceModel> mProvinceList = null; // 省份列表
    private String mProvince = null; // 省份
    private String mCity = null; // 城市
    private LoopView loopView;

    @Override
    public int getLayout() {
        return R.layout.activity_pick_view;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initViews() {
        actionBtn.setVisibility(View.GONE);
        title.setText("仿IOS风格的日期-时间选择器");

        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(PickViewActivity.this, new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        Toast.makeText(PickViewActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("CONFIRM") //text of confirm button
                        .textCancel("CANCEL") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(25) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1990) //min year in loop
                        .maxYear(2550) // max year in loop
                        .dateChose("2013-11-11") // date chose when init popwindow
                        .build();
                pickerPopWin.showPopWin(PickViewActivity.this);
            }
        });

        findViewById(R.id.timepick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerPopWin timePickerPopWin=new TimePickerPopWin.Builder(PickViewActivity.this, new TimePickerPopWin.OnTimePickListener() {
                    @Override
                    public void onTimePickCompleted(int hour, int minute, String AM_PM, String time) {
                        Toast.makeText(PickViewActivity.this, time, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("CONFIRM")
                        .textCancel("CANCEL")
                        .btnTextSize(16)
                        .viewTextSize(25)
                        .colorCancel(Color.parseColor("#999999"))
                        .colorConfirm(Color.parseColor("#009900"))
                        .build();
                timePickerPopWin.showPopWin(PickViewActivity.this);
            }
        });


        findViewById(R.id.province).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mProvinceList) {
                    ProvincePickPopWin pickPopWin = new ProvincePickPopWin(PickViewActivity.this,
                            mProvince, mCity, mProvinceList, PickViewActivity.this);
                    pickPopWin.showPopWin(PickViewActivity.this);
                }
            }
        });

        loopView = (LoopView) findViewById(R.id.loop_view);
        loopView.setInitPosition(2);
        loopView.setCanLoop(false);
        loopView.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {

            }
        });
        loopView.setTextSize(25);//must be called before setDateList
        loopView.setDataList(getList());
        ((new ProvinceInfoParserTask(this, mHandler))).execute();// 解析本地地址信息文件
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("DAY TEST:" + i);
        }
        return list;
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ProvinceInfoParserTask.MSG_PARSE_RESULT_CALLBACK: // 解析地址完成
                    mProvinceList = (ArrayList<ProvinceModel>) msg.obj;
                    break;
            }
            return false;
        }
    });

    @Override
    public void onAddressPickCompleted(String province, String provinceId, String city, String cityId) {
//        Toast.makeText(this,province+"-"+provinceId+"-"+city+"-"+cityId,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ProvinceInfoUtils.matchAddress(this,provinceId,cityId,mProvinceList),Toast.LENGTH_SHORT).show();
        ProvinceInfoUtils.matchAddress(this,provinceId,cityId,mProvinceList);
    }
}