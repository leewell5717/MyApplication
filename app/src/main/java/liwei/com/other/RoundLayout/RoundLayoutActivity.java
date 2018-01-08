package liwei.com.other.RoundLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import liwei.com.R;

/**
 * 圆角布局的Activity
 */
public class RoundLayoutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_layout);

        ScrollView parentCcroll = (ScrollView)findViewById(R.id.parent_scroll);
        final ScrollView childCcroll = (ScrollView)findViewById(R.id.child_scroll);

        //处理滑动冲突
        parentCcroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                childCcroll.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        childCcroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
}