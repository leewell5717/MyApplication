package liwei.com.designmodel.facade;

import android.util.Log;

/**
 * 电脑显卡系统
 */
public class VideoCard implements Operation {

    @Override
    public void on() {
        Log.e("XXX","显卡开机了");
    }

    @Override
    public void off() {
        Log.e("XXX","显卡关机了");
    }

}