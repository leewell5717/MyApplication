package liwei.com.designmodel.strategy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 策略模式
 */
public class StrategyModelActivity extends Activity {

    @BindView(R.id.bus_btn)
    public Button busBtn;
    @BindView(R.id.subway_btn)
    public Button subwayBtn;
    @BindView(R.id.tax_btn)
    public Button taxBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_strategy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bus_btn,R.id.subway_btn,R.id.tax_btn})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.bus_btn:
                StrategyMain strategyMain1 = new StrategyMain();
                strategyMain1.setStrategy(new BusCalculate());
                int busPrice = strategyMain1.setVehicle(9);
                Log.e("XXX","乘坐公交9公里的价格是：" + busPrice);
                break;
            case R.id.subway_btn:
                StrategyMain strategyMain2 = new StrategyMain();
                strategyMain2.setStrategy(new SubwayCalculate());
                int subwayPrice = strategyMain2.setVehicle(9);
                Log.e("XXX","乘坐地铁9公里的价格是：" + subwayPrice);
                break;
            case R.id.tax_btn:
                StrategyMain strategyMain3 = new StrategyMain();
                strategyMain3.setStrategy(new TaxCalculate());
                int taxPrice = strategyMain3.setVehicle(9);
                Log.e("XXX","乘坐出租车9公里的价格是：" + taxPrice);
                break;
        }
    }
}