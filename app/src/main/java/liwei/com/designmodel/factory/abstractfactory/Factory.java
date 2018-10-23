package liwei.com.designmodel.factory.abstractfactory;

/**
 * 创建具体工厂的抽象类
 */
public abstract class Factory {

    //创建引擎
    public abstract Engine createEngine();

    //创建空调
    public abstract Air createAir();
}