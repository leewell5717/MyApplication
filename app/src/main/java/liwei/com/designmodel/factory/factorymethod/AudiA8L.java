package liwei.com.designmodel.factory.factorymethod;

import android.util.Log;

/**
 * 具体的产品——奥迪A8L
 */
public class AudiA8L extends AudiL {

    public AudiA8L(){
        Log.e("XXX","生产了一辆奥迪A8L");
    }

    public void doSomeThing(){
        Log.e("XXX","A8L-doSomeThing");
    }
}