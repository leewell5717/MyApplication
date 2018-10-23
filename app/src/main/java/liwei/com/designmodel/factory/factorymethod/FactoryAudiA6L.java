package liwei.com.designmodel.factory.factorymethod;

/**
 * 生产奥迪A6L的具体工厂类
 */
public class FactoryAudiA6L implements Factory {
    @Override
    public AudiA6L createAudiL() {
        return new AudiA6L();
    }
}