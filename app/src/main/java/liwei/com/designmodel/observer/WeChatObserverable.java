package liwei.com.designmodel.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过模拟订阅微信公众号来使用观察者模式
 * 被观察者，也就是公众号
 */
public class WeChatObserverable implements Observerable {

    private List<Observer> observers;
    private String message;

    public WeChatObserverable() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer,String name) {
        observers.add(observer);
        User.names.add(name);
    }

    @Override
    public void removeObserver(Observer observer,String name) {
        if (!observers.isEmpty()) {
            observers.remove(observer);
        }
        if(!User.names.isEmpty()){
            User.names.remove(name);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(message);
        }
    }

    /**
     * 是否已经订阅
     * @return false-未订阅；true-已订阅
     */
    public boolean isSubscribed(String name){
        return User.names.contains(name);
    }

    public void setMessage(String msg) {
        this.message = msg;
        Log.e("XXX", "微信公众号更新了消息：" + msg);
        notifyObserver();
    }
}