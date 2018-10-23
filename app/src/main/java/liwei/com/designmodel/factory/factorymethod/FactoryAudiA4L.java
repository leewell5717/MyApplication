package liwei.com.designmodel.factory.factorymethod;

/**
 * 生产奥迪A4L的具体工厂类
 */
public class FactoryAudiA4L implements Factory {
    @Override
    public AudiA4L createAudiL() {
        return new AudiA4L();
    }
}