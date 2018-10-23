package liwei.com.designmodel.factory.simplefactory;

/**
 * 简单工厂
 */
public class Factory {
    public Audi createAudi(int type){
        switch (type){
            case 4:
                return new AudiA4();
            case 6:
                return new AudiA6();
            case 8:
                return new AudiA8();
        }
        return null;
    }
}