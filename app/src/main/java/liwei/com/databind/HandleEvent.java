package liwei.com.databind;

import android.util.Log;
import android.view.View;

public class HandleEvent {

    private static final String Tag = HandleEvent.class.getSimpleName();

    public void myClick1(View view,String value) {
        Log.e(Tag, "单击事件1，携带的参数是："+value);
    }

    public void myClick2(View view) {
        Log.e(Tag, "单击事件2");
    }

    public boolean myClick3(View view){
        Log.e(Tag, "长按事件1");
        return true;
    }
}