package liwei.com.other.shimmer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import liwei.com.R;
import liwei.com.other.shimmer.facebook.ShimmerFrameLayout;
import liwei.com.other.shimmer.view.Shimmer;
import liwei.com.other.shimmer.view.ShimmerButton;
import liwei.com.other.shimmer.view.ShimmerTextView;

/**
 * 流光效果activity
 */
public class ShimmerActivity extends Activity {

    private ShimmerTextView tv;
    private ShimmerButton btn;
    private Shimmer shimmer1;
    private Shimmer shimmer2;
    private ShimmerFrameLayout mShimmerViewContainer;

    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_txt);
        btn = (ShimmerButton) findViewById(R.id.shimmer_btn);
        progress = (ProgressBar)findViewById(R.id.progress);
        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        Button button = (Button)findViewById(R.id.toggle_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shimmer1 != null && shimmer1.isAnimating()) {
                    shimmer1.cancel();
                } else {
                    shimmer1 = new Shimmer();
                    shimmer1.start(tv);
                }
                if (shimmer2 != null && shimmer2.isAnimating()) {
                    shimmer2.cancel();
                } else {
                    shimmer2 = new Shimmer();
                    shimmer2.start(btn);
                    shimmer2.setDuration(3);
                }

                if(mShimmerViewContainer.isAnimationStarted()){
                    mShimmerViewContainer.stopShimmerAnimation();
                }else{
                    mShimmerViewContainer.startShimmerAnimation();
                }
            }
        });

        progress.setMax(100);
        progress.setProgress(0);
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.incrementProgressBy(1);
                        if(progress.getProgress() == 100){
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(task,1000,100);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmerAnimation();
    }
}