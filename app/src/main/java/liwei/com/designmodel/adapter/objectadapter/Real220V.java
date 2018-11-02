package liwei.com.designmodel.adapter.objectadapter;

import android.util.Log;

/**
 * 获得220伏电源，转化成5伏的电源
 */
public class Real220V implements power220V {

    private power5V power5V;

    public Real220V(power5V power5V){
        this.power5V = power5V;
    }

    @Override
    public int get220V() {
        Log.e("XXX","（对象适配器）开始转换电源：220V->5V");
        return power5V.get5V();
    }
}