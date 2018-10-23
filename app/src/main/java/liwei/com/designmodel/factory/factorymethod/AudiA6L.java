package liwei.com.designmodel.factory.factorymethod;

import android.util.Log;

/**
 * 具体的产品——奥迪A6L
 */
public class AudiA6L extends AudiL {

    public AudiA6L(){
        Log.e("XXX","生产了一辆奥迪A6L");
    }

    public void doSomeThing(){
        Log.e("XXX","A6L-doSomeThing");
    }
}