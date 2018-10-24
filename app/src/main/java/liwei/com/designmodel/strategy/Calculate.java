package liwei.com.designmodel.strategy;

/**
 * 计算里程的接口
 */
public interface Calculate {
    /**
     * 计算规则：
     * 1、公交车——起步2元，超过5公里后，每公里加收1元
     * 2、地铁——5公里内3元，5-10公里4元，超过10公里5元
     * 3、出租车——每公里2元
     */
    int calculate(int distance);
}