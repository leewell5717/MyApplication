package liwei.com.other.CustomProgressBar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.other.CustomProgressBar.view.CircleProgressBarView;
import liwei.com.other.CustomProgressBar.view.HorizontalProgressBar;
import liwei.com.other.CustomProgressBar.view.NumberProgressBar;

/**
 * 自定义进度条
 */
public class ProgressBarActivity extends Activity {
    @BindView(R.id.circle_progress_view)
    public CircleProgressBarView circleProgressBarView;
    @BindView(R.id.horizontal_progress_view)
    public HorizontalProgressBar horizontalProgressBar;
    @BindView(R.id.number_progress_view)
    public NumberProgressBar numberProgressView;
    @BindView(R.id.circle_progress_tv)
    public TextView circleProgressTv;
    @BindView(R.id.horizontal_progress_tv)
    public TextView horizontalProgressTv;
    @BindView(R.id.number_progress_tv)
    public TextView numberProgressTv;
    @BindView(R.id.start_btn)
    public Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        //初始化圆形进度条
        circleProgressBarView.setProgressWithAnimation(60);
        circleProgressBarView.setProgressListener(new CircleProgressBarView.ProgressListener() {
            @Override
            public void currentProgressListener(float currentProgress) {
                circleProgressTv.setText("当前进度：" + currentProgress + "");
            }
        });
        circleProgressBarView.startProgressAnimation();
        //初始化水平进度条
        horizontalProgressBar.setProgressWithAnimation(60).setProgressListener(new HorizontalProgressBar.ProgressListener() {
            @Override
            public void currentProgressListener(float currentProgress) {
                horizontalProgressTv.setText("当前进度："+currentProgress+"");
            }
        });
        horizontalProgressBar.startProgressAnimation();
        //数字进度条
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressView.incrementProgressBy(1);
                    }
                });
            }
        },1000,100);
        numberProgressView.setOnProgressBarListener(new NumberProgressBar.OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                numberProgressTv.setText("当前进度："+String.valueOf(current)+"");
                if(current == max) {
                    numberProgressTv.setText("完成");
                }
            }
        });
    }

    @OnClick({R.id.start_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.start_btn:
                horizontalProgressBar.setProgressWithAnimation(100).setProgressListener(new HorizontalProgressBar.ProgressListener() {
                    @Override
                    public void currentProgressListener(float currentProgress) {
                        horizontalProgressTv.setText("当前进度："+currentProgress+"");
                    }
                });
                circleProgressBarView.setProgressWithAnimation(100).startProgressAnimation();
                circleProgressBarView.setProgressListener(new CircleProgressBarView.ProgressListener() {
                    @Override
                    public void currentProgressListener(float currentProgress) {
                        circleProgressTv.setText("当前进度：" + currentProgress + "");
                    }
                });

                numberProgressView.setProgress(0);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numberProgressView.incrementProgressBy(1);
                            }
                        });
                    }
                },10,10);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        circleProgressBarView.resumeProgressAnimation();
        horizontalProgressBar.resumeProgressAnimation();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
        circleProgressBarView.pauseProgressAnimation();
        horizontalProgressBar.pauseProgressAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        circleProgressBarView.stopProgressAnimation();
        horizontalProgressBar.stopProgressAnimation();
    }
}