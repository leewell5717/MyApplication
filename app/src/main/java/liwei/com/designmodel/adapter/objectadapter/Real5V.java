package liwei.com.designmodel.adapter.objectadapter;

import android.util.Log;

/**
 * 真实的5伏电源
 */
public class Real5V implements power5V {
    @Override
    public int get5V() {
        Log.e("XXX","（对象适配器）获得5伏电源，开始充电");
        return 5;
    }
}