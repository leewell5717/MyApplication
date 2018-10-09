package liwei.com.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.network.httpurlconnect.HttpUrlConnectionActivity;
import liwei.com.network.okhttp.OkhttpActivity;
import liwei.com.network.retrofit.RetrofitActivity;
import liwei.com.network.volley.VolleyActivity;

/**
 * 网络请求主页
 */
public class NetworkMainActivity extends Activity {

    @BindView(R.id.okhttp_btn)
    public Button okhttpBtn;
    @BindView(R.id.retrofit_btn)
    public Button retrofitBtn;
    @BindView(R.id.volley_btn)
    public Button volleyBtn;
    @BindView(R.id.httpurlconnect_btn)
    public Button httpurlconnectBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.okhttp_btn,R.id.retrofit_btn,R.id.volley_btn,R.id.httpurlconnect_btn})
    public void OnClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.okhttp_btn:
                intent = new Intent(NetworkMainActivity.this, OkhttpActivity.class);
                break;
            case R.id.retrofit_btn:
                intent = new Intent(NetworkMainActivity.this, RetrofitActivity.class);
                break;
            case R.id.volley_btn:
                intent = new Intent(NetworkMainActivity.this, VolleyActivity.class);
                break;
            case R.id.httpurlconnect_btn:
                intent = new Intent(NetworkMainActivity.this, HttpUrlConnectionActivity.class);
                break;
            default:
                Toast.makeText(NetworkMainActivity.this,"没有找到对应的Activity",Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}