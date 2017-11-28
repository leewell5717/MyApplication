package liwei.com.other.mybase;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import liwei.com.R;

public abstract class MyBaseActivity extends Activity{

    protected RelativeLayout titleContainer;
    protected ImageView backBtn;
    protected ImageView actionBtn;
    protected TextView title;
    protected LinearLayout contentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        titleContainer = (RelativeLayout)findViewById(R.id.title_container);
        backBtn = (ImageView)findViewById(R.id.back_btn);
        actionBtn = (ImageView)findViewById(R.id.action_btn);
        title = (TextView)findViewById(R.id.title);
        contentContainer = (LinearLayout)findViewById(R.id.content_container);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAction();
            }
        });

        View view = LayoutInflater.from(this).inflate(getLayout(),null);
        contentContainer.addView(view);
        initViews();
    }

    /**
     * 添加content 布局
     */
    public abstract int getLayout();

    /**
     * 处理action_btn按钮的逻辑方法
     */
    public abstract void doAction();

    /**
     * 初始化content布局控件
     */
    public abstract void initViews();
}