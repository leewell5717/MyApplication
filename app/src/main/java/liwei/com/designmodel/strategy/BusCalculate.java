package liwei.com.designmodel.strategy;

/**
 * 公交车里程计算的策略类
 * 公交车——起步2元，超过5公里后，每公里加收1元
 */
public class BusCalculate implements Calculate {
    @Override
    public int calculate(int distance) {
        int price;
        if(distance <= 5){
            price = 2;
        }else{
            price = 2 + (distance - 5);
        }

        return price;
    }
}