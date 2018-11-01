package liwei.com.designmodel.observer;

/**
 * 抽象的被观察者
 * 声明了添加、删除、通知观察者方法
 */
public interface Observerable {

    /**
     * 注册观察者
     */
    void registerObserver(Observer observer,String name);

    /**
     * 移除观察者
     */
    void removeObserver(Observer observer,String name);

    /**
     * 通知观察者
     */
    void notifyObserver();

}