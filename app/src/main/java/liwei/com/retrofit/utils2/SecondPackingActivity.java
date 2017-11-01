package liwei.com.retrofit.utils2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import liwei.com.R;
import liwei.com.retrofit.MapBean;

public class SecondPackingActivity extends NetworkBaseActivity {

    @BindView(R.id.request_btn)
    public Button requestBtn;
    @BindView(R.id.result)
    public TextView result;

    private Observable observable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.request_btn})
    public void myClick(View v){
        switch (v.getId()){
            case R.id.request_btn:
                Map<String, String> map = new HashMap<>();
                map.put("a", "苏州市");

                observable = RetroFactory.getInstance().getCalendar(map);
                observable.compose(composeFunction).subscribe(new BaseObserver<MapBean>(SecondPackingActivity.this, pd) {
                    @Override
                    void onHandleSuccess(MapBean mapBean) {
                        result.setText(mapBean.getLat()+","+mapBean.getLon());
                    }
                });
                break;
        }
    }
}