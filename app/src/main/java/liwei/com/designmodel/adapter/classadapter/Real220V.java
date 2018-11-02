package liwei.com.designmodel.adapter.classadapter;

import android.util.Log;

/**
 * 获得220伏电源，转化成5伏的电源
 */
public class Real220V extends Real5V implements power220V {
    @Override
    public int get220V() {
        Log.e("XXX","（类适配器）开始转换电源：220V->5V");
        return get5V();
    }
}