package liwei.com.designmodel.factory.factorymethod;

import android.util.Log;

/**
 * 具体的产品——奥迪A4L
 */
public class AudiA4L extends AudiL {

    public AudiA4L(){
        Log.e("XXX","生产了一辆奥迪A4L");
    }

    public void doSomeThing(){
        Log.e("XXX","A4L-doSomeThing");
    }
}