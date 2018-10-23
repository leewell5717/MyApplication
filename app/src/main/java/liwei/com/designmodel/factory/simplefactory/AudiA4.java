package liwei.com.designmodel.factory.simplefactory;

import android.util.Log;

/**
 * 具体的产品——奥迪A4
 */
public class AudiA4 extends Audi {
    public AudiA4(){
        Log.e("XXX","生产了一辆奥迪A4");
    }
}