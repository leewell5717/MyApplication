package liwei.com.network.httpurlconnect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * HttpUrlConnection网络请求主页
 */
public class HttpUrlConnectionActivity extends Activity {

    @BindView(R.id.get_btn)
    public Button getBtn;
    @BindView(R.id.post_btn)
    public Button PostBtn;
    @BindView(R.id.get_result)
    public TextView getResult;
    @BindView(R.id.post_result)
    public TextView postResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnect);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_btn,R.id.post_btn})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.get_btn:

                break;
            case R.id.post_btn:

                break;
        }
    }
}
