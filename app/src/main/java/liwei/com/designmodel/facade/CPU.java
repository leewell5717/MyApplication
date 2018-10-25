package liwei.com.designmodel.facade;

import android.util.Log;

/**
 * 电脑CPU系统
 */
public class CPU implements Operation{

    @Override
    public void on() {
        Log.e("XXX","CPU开机了");
    }

    @Override
    public void off() {
        Log.e("XXX","CPU关机了");
    }
}