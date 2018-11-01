package liwei.com.designmodel.observer;

/**
 * 抽象的观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 */
public interface Observer {

    /**
     * 更新
     */
    void update(String message);

}