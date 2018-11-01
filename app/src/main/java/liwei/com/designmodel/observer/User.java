package liwei.com.designmodel.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义具体的观察者，即用户
 */
public class User implements Observer {

    private String name;
    private String message;

    public static List<String> names = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        Log.e("XXX", name + "收到推送的消息： " + message);
    }
}