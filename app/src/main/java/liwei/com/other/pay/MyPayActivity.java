package liwei.com.other.pay;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import liwei.com.utils.Md5Util;
import liwei.com.R;
import liwei.com.utils.Utils;
import liwei.com.network.okhttp.api.OkHttpBaseCallback;
import liwei.com.network.okhttp.api.OkHttpHelper;
import liwei.com.other.mybase.MyBaseActivity;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 支付相关Activity(微信、支付宝、QQ钱包)
 */
public class MyPayActivity extends MyBaseActivity{

    @BindView(R.id.charge_money)
    public EditText chargeMoney;
    @BindView(R.id.ali_pay_btn)
    public Button aliPayBtn;
    @BindView(R.id.wx_pay_btn)
    public Button wxPayBtn;
    @BindView(R.id.qq_pay_btn)
    public Button qqPayBtn;
    @BindView(R.id.get_pay_way)
    public Button getPayWay;

    private static final String Key = "6vb#tIhPX3mc6OZjb#tCJxR#IJ2NJQyP";
    private static final String signKeyStr = "sdk";
    private static final String BaseUrl = "http://api-dev.17byh.com";

    @Override
    public int getLayout() {
        return R.layout.activity_pay;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initViews() {
        actionBtn.setVisibility(View.GONE);
        title.setText("支付");
    }

    @OnClick({R.id.ali_pay_btn,R.id.wx_pay_btn,R.id.qq_pay_btn,R.id.get_pay_way})
    public void onClick(View v){
        String inputMoney = chargeMoney.getText().toString().trim();
        if(TextUtils.isEmpty(inputMoney)){
            Utils.showToastCenter("请输入充值金额");
            return;
        }
        double money = Double.parseDouble(inputMoney);
        if(money < 0){
            Utils.showToastCenter("充值金额不能小于0");
            return;
        }
        switch (v.getId()){
            case R.id.ali_pay_btn:
                doAliPay();
                break;
            case R.id.wx_pay_btn:
                doWXPay();
                break;
            case R.id.qq_pay_btn:
                doQQPay();
                break;
            case R.id.get_pay_way:
                getPayWay(MyPayActivity.this);
                break;
        }
    }

    /**
     * 获取支付通道
     */
    private void getPayWay(final Context context){
        StringBuilder builder = new StringBuilder();
        String timeStr = String.valueOf(System.currentTimeMillis());
        builder.append("imeil=").append(Utils.getPhoneIMEI(context)).append("&")
                .append("noncestr=").append(timeStr).append("&")
                .append("signKey=").append(signKeyStr).append("&")
                .append("uuid=").append(Utils.getUUID(context)).append(Key);
        String signStr = Md5Util.md5(builder.toString()).toLowerCase();
        LinkedHashMap<String,Object> map = new LinkedHashMap <>();
        map.put("imeil",Utils.getPhoneIMEI(context));
        map.put("noncestr",timeStr);
        map.put("signKey",signKeyStr);
        map.put("uuid",Utils.getUUID(context));
        map.put("sign",signStr);
        OkHttpHelper.getInstance(context).get(BaseUrl + "/sdk/payway", map, new OkHttpBaseCallback<BaseBean<List<PayWayBean>>>() {
            @Override
            public void onRequestBefore() {
            }
            @Override
            public void onFailure(Request request, Exception e) {
            }
            @Override
            public void onSuccess(Response response, BaseBean<List<PayWayBean>> baseBean) {
                if(baseBean.getError() != 0){
                    Utils.showToastCenter("充值初始化失败");
                }
            }
            @Override
            public void onError(Response response, int errorCode, Exception e) {
            }
        });
    }

    /**
     * 支付宝支付
     */
    private void doAliPay(){

    }
    /**
     * 微信支付
     */
    private void doWXPay(){

    }
    /**
     * QQ支付
     */
    private void doQQPay(){

    }
}