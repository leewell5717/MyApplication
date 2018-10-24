package liwei.com.designmodel.strategy;

/**
 * 地铁里程计算的策略类
 * 地铁——5公里内3元，5-10公里4元，超过10公里5元
 */
public class SubwayCalculate implements Calculate {
    @Override
    public int calculate(int distance) {
        int prince;
        if(distance <= 5){
            prince = 3;
        }else if(distance > 5 && distance <= 10){
            prince = 4;
        }else{
            prince = 5;
        }

        return prince;
    }
}