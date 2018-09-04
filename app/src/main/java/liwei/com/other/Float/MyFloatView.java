package liwei.com.other.Float;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import liwei.com.R;

import static liwei.com.other.Float.FloatView.imgUrl;

/**
 * 自定义悬浮球
 */
public class MyFloatView extends View {

    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private DisplayMetrics displayMetrics;
    private Context context;
    private View contentView;

    private boolean isShow = false;

    private Button button;
    private ImageView imageView;
    private LinearLayout root;

    public MyFloatView(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = getResources().getDisplayMetrics();
        if (params == null) {
            params = new WindowManager.LayoutParams();
        }
        this.context = context;
    }

    public void setLayout(int layoutId) {
        contentView = LayoutInflater.from(context).inflate(layoutId, null);
        button = (Button) contentView.findViewById(R.id.loadImageBtn);
        imageView = (ImageView) contentView.findViewById(R.id.showMyImage);
        root = (LinearLayout) contentView.findViewById(R.id.root);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(context).load(imgUrl).into(imageView);
            }
        });
    }

    public void show() {
        if (contentView != null && !isShow) {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            params.format = PixelFormat.RGBA_8888;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.alpha = 0.99f;
            params.gravity = Gravity.START | Gravity.TOP;
            params.x = 0;
            params.y = 0;
            root.setBackgroundColor(Color.parseColor("#aabbbbbb"));
            params.width = (int) (displayMetrics.widthPixels * 0.5);
            params.height = (int) (displayMetrics.heightPixels * 0.99);
            params.windowAnimations = R.style.dialog_anim;
            // 显示自定义悬浮窗口
            windowManager.addView(contentView, params);
            isShow = true;
        }
    }

    public void close() {
        if (contentView != null) {
            windowManager.removeView(contentView);
            isShow = false;
        }
    }

    public boolean isShow() {
        return isShow;
    }

}