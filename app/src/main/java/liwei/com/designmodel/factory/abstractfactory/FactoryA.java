package liwei.com.designmodel.factory.abstractfactory;

/**
 * 具体的工厂类——生产具体的零件
 * FactoryA工厂生产A类的零件
 */
public class FactoryA extends Factory {

    @Override
    public Engine createEngine() {
        return new EngineA();
    }

    @Override
    public Air createAir() {
        return new AirA();
    }
}