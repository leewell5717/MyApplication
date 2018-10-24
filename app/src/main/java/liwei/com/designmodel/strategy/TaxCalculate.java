package liwei.com.designmodel.strategy;

/**
 * 出租车里程计算的策略类
 * 出租车——每公里2元
 */
public class TaxCalculate implements Calculate {
    @Override
    public int calculate(int distance) {
        return distance * 2;
    }
}