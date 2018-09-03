package liwei.com.designmodel.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Proxy;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import liwei.com.R;
import liwei.com.designmodel.proxy.dynamicproxy.ZhangSan;
import liwei.com.designmodel.proxy.staticproxy.LiSi;

/**
 * 代理模式Activity
 */
public class ProxyActivity extends Activity {

    public static final String Tag = "ProxyActivity";

    @BindViews({R.id.static_result,R.id.dynamic_result})
    public List<TextView> textViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        ButterKnife.bind(this);

        useStaticProxy();
        useDynamicProxy();
    }

    /**
     * 使用静态代理
     */
    private void useStaticProxy(){
        Log.e(Tag, "使用静态代理");
        Poker boss = new Boss();
        Poker zhangsan = new LiSi(boss);
        zhangsan.getOne();
        zhangsan.playOne();
        zhangsan.getTwo();
        zhangsan.playTwo();
        zhangsan.getThree();
        zhangsan.playThree();
    }

    /**
     * 使用动态代理
     */
    private void useDynamicProxy(){
        Log.e(Tag, "使用动态代理");
        //方法1：
        Poker boss1 = new Boss();
        ZhangSan zhangsan1 = new ZhangSan(boss1);
        ClassLoader classLoader1 = boss1.getClass().getClassLoader();
        Poker poker1 = (Poker) Proxy.newProxyInstance(classLoader1,new Class[]{Poker.class},zhangsan1);
        poker1.getOne();
        poker1.playOne();
        poker1.getTwo();
        poker1.playTwo();
        poker1.getThree();
        poker1.playThree();

        //方法2：
        ZhangSan zhangsan2 = new ZhangSan();
        Poker poker2 = (Poker) zhangsan2.bind(new Boss());
        poker2.getOne();
        poker2.playOne();
        poker2.getTwo();
        poker2.playTwo();
        poker2.getThree();
        poker2.playThree();

    }
}