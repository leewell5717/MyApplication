package liwei.com.designmodel.facade;

/**
 * 所有机器的操作接口
 */
public interface Operation {

    /**
     * 开机
     */
    void on();

    /**
     * 关机
     */
    void off();

}