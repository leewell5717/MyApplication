package liwei.com.other.titlebaralphagradient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.other.titlebaralphagradient.listview.UseListviewActivity;
import liwei.com.other.titlebaralphagradient.scrollview.UseScrollviewActivity;

/**
 * 标题栏颜色渐变效果
 */
public class TitleBarAlphaGradientActivity extends Activity {

    @BindView(R.id.use_scrollview)
    public Button useScrollview;
    @BindView(R.id.use_listview)
    public Button useListview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar_alpha_gradient);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.use_scrollview,R.id.use_listview})
    public void MyOnclick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.use_scrollview:
                intent = new Intent(TitleBarAlphaGradientActivity.this,UseScrollviewActivity.class);
                break;
            case R.id.use_listview:
                intent = new Intent(TitleBarAlphaGradientActivity.this,UseListviewActivity.class);
                break;
        }
        startActivity(intent);
    }
}