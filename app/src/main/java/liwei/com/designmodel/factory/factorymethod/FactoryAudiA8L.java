package liwei.com.designmodel.factory.factorymethod;

/**
 * 生产奥迪A8L的具体工厂类
 */
public class FactoryAudiA8L implements Factory {
    @Override
    public AudiA8L createAudiL() {
        return new AudiA8L();
    }
}