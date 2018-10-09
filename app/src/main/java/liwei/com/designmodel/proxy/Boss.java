package liwei.com.designmodel.proxy;

import android.util.Log;

import static liwei.com.designmodel.proxy.ProxyActivity.Tag;

/**
 * 具体的受代理着（老板）——老板并不直接打牌，而是让张三(动态代理)/李四(静态代理)代替老板打
 */
public class Boss implements Poker {


    @Override
    public void getOne() {
        Log.e(Tag,"老板要摸A");
    }

    @Override
    public void getTwo() {
        Log.e(Tag,"老板要摸2");
    }

    @Override
    public void getThree() {
        Log.e(Tag,"老板要摸3");
    }

    @Override
    public void playOne() {
        Log.e(Tag,"老板要打A");
    }

    @Override
    public void playTwo() {
        Log.e(Tag,"老板要打2");
    }

    @Override
    public void playThree() {
        Log.e(Tag,"老板要打3");
    }
}