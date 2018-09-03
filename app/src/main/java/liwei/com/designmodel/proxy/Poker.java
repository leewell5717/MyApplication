package liwei.com.designmodel.proxy;

/**
 * 使用打扑克牌的方式使用静态代理（接口与动态代理一样）
 */
public interface Poker {

    /**
     * 摸A
     */
    void getOne();

    /**
     * 摸2
     */
    void getTwo();

    /**
     * 摸3
     */
    void getThree();

    /**
     * 打A
     */
    void playOne();

    /**
     * 打2
     */
    void playTwo();

    /**
     * 打3
     */
    void playThree();
}