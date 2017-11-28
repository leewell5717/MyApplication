package liwei.com.other.ExpandableTextview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

public class ExpandableActivity extends Activity {
    @BindView(R.id.test_btn)
    public Button testBtn;
    @BindView(R.id.text_container)
    public LinearLayout textContainer;
    @BindView(R.id.test_text)
    public TextView testText;
    @BindView(R.id.arrow)
    public ImageView arrow;

    /**默认展开*/
    private boolean mCollapsed = false;
    /**全局内容高度*/
    private int contentHeight = 0;
    /**全局内容宽度*/
    private int contentWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewTreeObserver observer = textContainer.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                contentHeight = textContainer.getHeight();
                contentWidth = textContainer.getWidth();
                Log.e("XXX","textContainer的高度：" + contentHeight + ",宽度："+contentWidth);
            }
        });
    }

    @OnClick({R.id.test_btn})
    public void click(View v){
        switch (v.getId()){
            case R.id.test_btn:
                ValueAnimator valueAnimator;
                if(mCollapsed){
                    mCollapsed = false;
                    //如果是折叠，则展开至最大
                    Log.e("XXX","1");
                    valueAnimator = ValueAnimator.ofInt(0,contentHeight);
                }else{
                    mCollapsed = true;
                    //如果是展开，则折叠
                    Log.e("XXX","2");
                    valueAnimator = ValueAnimator.ofInt(textContainer.getHeight(),0);
                }
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        textContainer.getLayoutParams().height = animatedValue;
                        textContainer.requestLayout();
                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(mCollapsed){
                            arrow.setImageResource(R.mipmap.icon_green_arrow_up);
                        }else{
                            arrow.setImageResource(R.mipmap.icon_green_arrow_down);
                        }
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                valueAnimator.setDuration(300);
                valueAnimator.start();
                break;
        }
    }
}