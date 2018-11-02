package liwei.com.designmodel.adapter.classadapter;

import android.util.Log;

/**
 * 真实的5伏电源
 */
public class Real5V implements power5V {

    @Override
    public int get5V() {
        Log.e("XXX","（类适配器）获得5伏电源，开始充电");
        return 5;
    }
}