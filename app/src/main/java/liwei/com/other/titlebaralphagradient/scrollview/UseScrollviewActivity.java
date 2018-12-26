package liwei.com.other.titlebaralphagradient.scrollview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.other.titlebaralphagradient.listview.MyListViewAdapter;

/**
 * 使用scrollview滑动渐变
 */
public class UseScrollviewActivity extends Activity {

    @BindView(R.id.my_scrollview)
    public MyScrollView myScrollview;
    @BindView(R.id.my_listview)
    public NoScrollListview myListview;
    @BindView(R.id.title_bar_container)
    public LinearLayout titleBarContainer;
    @BindView(R.id.back_btn)
    public TextView backBtn;

    private int titleBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar_alpha_use_scrollview);
        ButterKnife.bind(this);

        titleBarContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleBarContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                titleBarHeight = titleBarContainer.getHeight();
                Log.e("XXX", "titleBarHeight:" + titleBarHeight);
            }
        });

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("这是第" + i + "个数据");
        }
        MyListViewAdapter adapter = new MyListViewAdapter(this, datas);
        myListview.setAdapter(adapter);
        //加上这句是因为不滚动的listview，会自动滑动到中间
        myScrollview.smoothScrollTo(0,0);
        myScrollview.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                Log.e("XXX", "x:" + x);
                Log.e("XXX", "y:" + y);
                Log.e("XXX", "oldx:" + oldx);
                Log.e("XXX", "oldy:" + oldy);

                if (y <= 0) { //初始状态
                    titleBarContainer.setBackgroundColor(Color.argb(0, 51, 204, 153));
                    //设置textview的文字颜色等
//                    backBtn.setTextColor(Color.argb(255, 255, 255, 255));
                    Log.e("XXX", "scrollY <= 0");
                } else if (y > 0 && y <= titleBarHeight) { //开始滑动，但没有超出titlebar高度，渐变背景色的alpha值
                    float scale = (float) y / titleBarHeight;
                    float alpha = scale * 255;
                    //渐变背景色
                    titleBarContainer.setBackgroundColor(Color.argb((int) alpha, 51, 204, 153));
                    //渐变文字颜色
//                    backBtn.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    Log.e("XXX", "scrollY > 0 && scrollY <= titleBarHeight");
                } else { //已经完全超过了titleBar的高度
                    titleBarContainer.setBackgroundColor(Color.argb(255, 51, 204, 153));
//                    backBtn.setTextColor(Color.argb(255, 0, 0, 0));
                }
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void MyOnclick(){
        UseScrollviewActivity.this.finish();
    }
}