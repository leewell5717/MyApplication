package liwei.com.other.ExpandableTextview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.MainActivity;
import liwei.com.R;
import liwei.com.other.ExpandableTextview.view.ExpandableTextView;

/**
 * 可伸缩的TextView
 */
public class ExpandableTextviewMainActivity extends Activity {

    private static final String Tag = MainActivity.class.getSimpleName();

    @BindView(R.id.normal_status)
    public Button normalStatus;
    @BindView(R.id.list_status)
    public Button listStatus;
    @BindView(R.id.normal_container)
    public LinearLayout normalContainer;
    @BindView(R.id.list_container)
    public ListView listContainer;

    @BindView(R.id.ExpandableTextView_left)
    public ExpandableTextView ExpandableTextViewLeft;
    @BindView(R.id.ExpandableTextView_right)
    public ExpandableTextView ExpandableTextViewRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_textview_main);
        ButterKnife.bind(this);

        showNormal();
    }

    @OnClick({R.id.normal_status,R.id.list_status})
    public void myOnClick(View v){
        switch (v.getId()){
            case R.id.normal_status:
                normalContainer.setVisibility(View.VISIBLE);
                listContainer.setVisibility(View.GONE);
                showNormal();
                break;
            case R.id.list_status:
                normalContainer.setVisibility(View.GONE);
                listContainer.setVisibility(View.VISIBLE);
                showList();
                break;
        }
    }

    private void showNormal(){
        ExpandableTextViewLeft.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Log.e(Tag,isExpanded ? "展开" : "收起");
            }
        });
        ExpandableTextViewRight.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Log.e(Tag,isExpanded ? "展开" : "收起");
            }
        });
        ExpandableTextViewLeft.setText(getString(R.string.expand_collapse_content));
        ExpandableTextViewRight.setText(getString(R.string.expand_collapse_content));
    }

    private void showList(){
        List<String> lists = new ArrayList<>();
        for(int i=0;i<10;i++){
            lists.add(getString(R.string.expand_collapse_content));
        }
        ExpandableTextViewAdapter adapter = new ExpandableTextViewAdapter(ExpandableTextviewMainActivity.this,lists);
        listContainer.setAdapter(adapter);
    }
}