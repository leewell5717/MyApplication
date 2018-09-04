package liwei.com.other.Float;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

public class FloatActivity extends FragmentActivity {

    @BindView(R.id.open_float_window)
    public Button openFloatWindow;
    @BindView(R.id.close_float_window)
    public Button closeFloatWindow;

    private FloatView floatView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);

        ButterKnife.bind(this);
        floatView = new FloatView(this);
    }

    @OnClick({R.id.open_float_window,R.id.close_float_window})
    public void click(View v){
        switch (v.getId()){
            case R.id.open_float_window:
                floatView.show();
                break;
            case R.id.close_float_window:
                floatView.hide();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        floatView.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        floatView.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        floatView.destory();
    }
}