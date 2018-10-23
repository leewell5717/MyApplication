package liwei.com.designmodel.factory.simplefactory;

import android.util.Log;

/**
 * 具体的产品——奥迪A8
 */
public class AudiA8 extends Audi {
    public AudiA8(){
        Log.e("XXX","生产了一辆奥迪A8");
    }
}