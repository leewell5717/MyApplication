package liwei.com.other.gauss;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 高斯模糊Activity
 */
public class GaussActivity extends Activity {
    @BindView(R.id.content_container)
    public LinearLayout contentContainer;
    @BindView(R.id.progress_container)
    public LinearLayout progressContainer;
    @BindView(R.id.gauss_btn)
    public Button gaussBtn;
    @BindView(R.id.resource_img)
    public ImageView resourceImg;
    @BindView(R.id.blur_img)
    public ImageView blurImg;
    @BindView(R.id.gauss_depth)
    public EditText gaussDepth;

    private Bitmap bitmap;
    private RenderScriptGaussianBlur renderScriptGaussianBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss);
        ButterKnife.bind(this);

        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.customactivityoncrash_error_image);
        renderScriptGaussianBlur = new RenderScriptGaussianBlur(this);
        resourceImg.setImageResource(R.mipmap.customactivityoncrash_error_image);
    }

    @OnClick({R.id.gauss_btn})
    public void myOnclick(View view){
        switch (view.getId()){
            case R.id.gauss_btn:
                String input = gaussDepth.getText().toString().trim();
                if(TextUtils.isEmpty(input)){
                    Toast.makeText(GaussActivity.this,"请输入模糊深度",Toast.LENGTH_SHORT).show();
                    return;
                }
                float depth = Float.parseFloat(input);
                if(depth < 0.0 || depth > 25.0){
                    Toast.makeText(GaussActivity.this,"请输入0-25之间的数",Toast.LENGTH_SHORT).show();
                    return;
                }
                contentContainer.setVisibility(View.GONE);
                progressContainer.setVisibility(View.VISIBLE);
                gauss(depth,bitmap);
                break;
        }
    }

    private void gauss(float depth,Bitmap bitmap){
        blurImg.setImageBitmap(renderScriptGaussianBlur.blur(depth,bitmap));
        contentContainer.setVisibility(View.VISIBLE);
        progressContainer.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        renderScriptGaussianBlur.destory();
    }
}