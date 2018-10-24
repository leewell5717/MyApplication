package liwei.com.designmodel.strategy;

/**
 * 使用搭乘交通工具的里程以及价格的策略
 */
public class StrategyMain {

    private Calculate calculate;

    public void setStrategy(Calculate calculate){
        this.calculate = calculate;
    }

    public int setVehicle(int distance){
        return calculate.calculate(distance);
    }
}