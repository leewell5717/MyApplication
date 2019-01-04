package liwei.com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * app安装/卸载监听广播
 */
public class AppInstallReceiver extends BroadcastReceiver {

    //新安装app监听
    private static final String AppAdd = "android.intent.action.PACKAGE_ADDED";
    //替换已存在app监听(替换时会先收到卸载的再收到替换的， 替换自身也能收到)
    private static final String AppReplaced = "android.intent.action.PACKAGE_REPLACED";
    //卸载app监听
    private static final String AppDelete = "android.intent.action.PACKAGE_REMOVED";
    //
    private static final String StartAction = "liwei.com.StartAction";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction(); //动作
            String packageName = intent.getDataString(); //包名
            if (AppAdd.equals(action)) {
                Log.e("XXX", "安装了一个app，包名：" + packageName);

                //打开自定义安装完成的界面
                Intent i = new Intent();
                i.setClassName("liwei.com", "liwei.com.other.custominstallfinished.InstallJumpActivity");
                i.setAction(StartAction);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } else if (AppReplaced.equals(action)) {
                Log.e("XXX", "替换了一个app，包名：" + packageName);
            } else if (AppDelete.equals(action)) {
                Log.e("XXX", "卸载了一个app，包名：" + packageName);
            }
        }
    }
}