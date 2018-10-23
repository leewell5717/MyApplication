package liwei.com.designmodel.factory.abstractfactory;

/**
 * 具体的工厂类——生产具体的零件
 * FactoryB工厂生产B类的零件
 */
public class FactoryB extends Factory {

    @Override
    public Engine createEngine() {
        return new EngineB();
    }

    @Override
    public Air createAir() {
        return new AirB();
    }
}