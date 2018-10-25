package liwei.com.designmodel.facade;

import android.util.Log;

/**
 * 电脑主板系统
 */
public class Board implements Operation{

    @Override
    public void on() {
        Log.e("XXX","主板开机了");
    }

    @Override
    public void off() {
        Log.e("XXX","主板关机了");
    }
}