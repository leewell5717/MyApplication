package liwei.com.other.titlebaralphagradient.listview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 使用listview滑动渐变
 */
public class UseListviewActivity extends Activity {

    @BindView(R.id.title_bar_container)
    public LinearLayout titleBarContainer;
    @BindView(R.id.back_btn)
    public TextView backBtn;
    @BindView(R.id.my_listview)
    public ListView myListview;
    @BindView(R.id.some_text)
    public TextView someText;

    private int headerHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar_alpha_use_listview);
        ButterKnife.bind(this);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("这是第" + i + "个数据");
        }

        final View headerView = LayoutInflater.from(this).inflate(R.layout.layout_listview_head, null);
        MyListViewAdapter adapter = new MyListViewAdapter(this, datas);
        myListview.addHeaderView(headerView);
        myListview.setAdapter(adapter);
        myListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View sView = myListview.getChildAt(0);
                if (sView != null) {
                    int top = -sView.getTop();
                    headerHeight = sView.getHeight() - 100;
                    Log.e("XXX","top:" + top);
                    Log.e("XXX","headerHeight:" + headerHeight);

                    if (top > 0 && top <= headerHeight) {
                        float scale = (float) top / headerHeight;
                        float alpha = (255 * scale);
                        titleBarContainer.setBackgroundColor(Color.argb((int) alpha, 51, 204, 153));
                    }else if(top == 0 && firstVisibleItem == 0){
                        titleBarContainer.setBackgroundColor(Color.argb(0, 51, 204, 153));
                    }else if(firstVisibleItem > 1){
                        titleBarContainer.setBackgroundColor(Color.argb(255, 51, 204, 153));
                    }
                }
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void MyOnclick() {
        UseListviewActivity.this.finish();
    }
}