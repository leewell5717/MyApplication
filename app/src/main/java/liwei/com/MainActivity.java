package liwei.com;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private static final String Tag = MainActivity.class.getSimpleName();

    @BindView(R.id.button)
    public Button glideButton;
    @BindView(R.id.button2)
    public Button picassoButton;
    @BindView(R.id.image_glide)
    public ImageView imageGlide;
    @BindView(R.id.image_picasso)
    public ImageView imagePicasso;

    int[] location = new int[2];
    int startX = 0, startY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.button,R.id.button2})
    public void click(View v){
        switch (v.getId()){
            case R.id.button:
                Glide.with(this).load("http://p1.pstatp.com/large/166200019850062839d3").asGif()
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.error).into(imageGlide);
                break;
            case R.id.button2:
                Picasso.with(this).load("http://cdn.duitang.com/uploads/item/201409/06/20140906072515_dZker.gif")
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.error).into(imagePicasso);
                break;
        }
    }

    private void printLog(String msg) {
        Log.e(Tag, msg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = (int) event.getRawX();
//                startY = (int) event.getRawY();
//                button.getLocationOnScreen(location);
//                printLog("触摸起点X:" + startX);
//                printLog("触摸起点Y:" + startY);
//                printLog("button左上角X:" + location[0]);
//                printLog("button左上角Y:" + location[1]);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//                int deltaX = startX - x;
//                int deltaY = startY - y;
//                printLog("deltaX:" + deltaX);
//                printLog("deltaY:" + deltaY);
//                if (deltaY > 300) {
//                    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
//                    PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0);
//                    PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0);
//                    PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", location[1], 100f);
//                    ObjectAnimator.ofPropertyValuesHolder(button, alpha, scaleX, scaleY, translationY).setDuration(4000).start();
//
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        return super.onTouchEvent(event);
    }
}