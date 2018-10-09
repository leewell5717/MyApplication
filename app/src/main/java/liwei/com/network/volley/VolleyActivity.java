package liwei.com.network.volley;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * Volley网络请求主页
 */
public class VolleyActivity extends Activity {

    @BindView(R.id.volley_get_btn)
    public Button volleyGetBtn;
    @BindView(R.id.volley_post_btn)
    public Button volleyPostBtn;
    @BindView(R.id.get_result)
    public TextView getResult;
    @BindView(R.id.post_result)
    public TextView postResult;

    private RequestQueue queue;
    private static final String url = "http://www.baidu.com";
    private static final String url2 = "http://apicloud.mob.com/appstore/calendar/day";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);

        queue  = Volley.newRequestQueue(getApplicationContext());
    }

    @OnClick({R.id.volley_get_btn,R.id.volley_post_btn})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.volley_get_btn:
                StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getResult.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getResult.setText("请求失败：" + error.toString());
                    }
                });
                queue.add(getRequest);
                break;
            case R.id.volley_post_btn:
                JSONObject object = new JSONObject();
                try {
                    object.put("key","1863a50c31d7c");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url2, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        postResult.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        postResult.setText("请求失败：" + error.toString());
                    }
                });
                queue.add(postRequest);
                break;
        }
    }
}
