package liwei.com.other;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import liwei.com.R;
import liwei.com.other.CustomProgressBar.ProgressBarActivity;
import liwei.com.other.ExpandableTextview.ExpandableTextviewMainActivity;
import liwei.com.other.Float.FloatActivity;
import liwei.com.other.GreenDao.GreenDaoActivity;
import liwei.com.other.IJKPlayer.IJKPlayerActivity;
import liwei.com.other.PasswordView.PasswordActivity;
import liwei.com.other.RoundLayout.RoundLayoutActivity;
import liwei.com.other.TakePictuer.CameraActivity;
import liwei.com.other.cache.CacheTest;
import liwei.com.other.circlemenu.CircleMenuActivity;
import liwei.com.other.webviewtest.WebviewTestActivity;
import liwei.com.other.design.DesignActivity;
import liwei.com.other.encrypt.EncryptActivity;
import liwei.com.other.gauss.GaussActivity;
import liwei.com.other.kotlin.KotlinActivity;
import liwei.com.other.mybase.MyTestActivity;
import liwei.com.other.pay.MyPayActivity;
import liwei.com.other.picker.CalendarDateTimeActivity;
import liwei.com.other.shimmer.ShimmerActivity;
import liwei.com.other.slidingmenu.SlidingMenuActivity;

/**
 * 一般工具样式主页
 */
public class UtilsMainActivity extends Activity {
    @BindView(R.id.progress_bar_btn)
    public Button progressBarBtn;
    @BindView(R.id.gauss_blur_btn)
    public Button gauss_blur_btn;
    @BindView(R.id.expand_collapse_btn)
    public Button expandCollapseBtn;
    @BindView(R.id.ijkplayer_btn)
    public Button ijkplayerBtn;
    @BindView(R.id.design_btn)
    public Button designBtn;
    @BindView(R.id.kotlin_btn)
    public Button kotlinBtn;
    @BindView(R.id.round_layout_btn)
    public Button roundLayoutBtn;
    @BindView(R.id.password_view_btn)
    public Button passwordViewBtn;
    @BindView(R.id.float_window_btn)
    public Button floatWindowBtn;
    @BindView(R.id.take_picture_btn)
    public Button takePictureBtn;
    @BindView(R.id.green_dao_btn)
    public Button greenDaoBtn;
    @BindView(R.id.shimmer_btn)
    public Button shimmerBtn;
    @BindView(R.id.encryption_and_decryption_btn)
    public Button encryptionAndDecryptionBtn;
    @BindView(R.id.circle_menu_btn)
    public Button circleMenuBtn;
    @BindView(R.id.base_activity_btn)
    public Button baseActivityBtn;
    @BindView(R.id.date_time_btn)
    public Button dateTimeBtn;
    @BindView(R.id.sliding_menu_btn)
    public Button slidingMenuBtn;
    @BindView(R.id.pay_btn)
    public Button payBtn;
    @BindView(R.id.cache_btn)
    public Button cacheBtn;
    @BindView(R.id.webview_btn)
    public Button webviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils_main);
        ButterKnife.bind(this);

        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if(permission.granted){
                    Log.e("XXXX","已同意："+permission.name+"权限");
                }else if(permission.shouldShowRequestPermissionRationale){
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    Toast.makeText(UtilsMainActivity.this,"已拒绝："+permission.name+"权限,但下次还会提示",Toast.LENGTH_SHORT).show();
                }else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    Toast.makeText(UtilsMainActivity.this,"已拒绝："+permission.name + "且不再询问",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.progress_bar_btn,R.id.gauss_blur_btn,R.id.expand_collapse_btn,R.id.ijkplayer_btn,R.id.design_btn,
            R.id.round_layout_btn,R.id.kotlin_btn,R.id.password_view_btn,R.id.float_window_btn,R.id.take_picture_btn,
            R.id.green_dao_btn,R.id.shimmer_btn,R.id.encryption_and_decryption_btn,R.id.circle_menu_btn,
            R.id.base_activity_btn,R.id.date_time_btn,R.id.sliding_menu_btn,R.id.pay_btn,R.id.cache_btn,R.id.webview_btn})
    public void click(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.progress_bar_btn:
                intent = new Intent(UtilsMainActivity.this, ProgressBarActivity.class);
                break;
            case R.id.gauss_blur_btn:
                intent = new Intent(UtilsMainActivity.this, GaussActivity.class);
                break;
            case R.id.expand_collapse_btn:
                intent = new Intent(UtilsMainActivity.this, ExpandableTextviewMainActivity.class);
                break;
            case R.id.ijkplayer_btn:
                intent = new Intent(UtilsMainActivity.this, IJKPlayerActivity.class);
                break;
            case R.id.design_btn:
                intent = new Intent(UtilsMainActivity.this, DesignActivity.class);
                break;
            case R.id.kotlin_btn:
                intent = new Intent(UtilsMainActivity.this, KotlinActivity.class);
                break;
            case R.id.round_layout_btn:
                intent = new Intent(UtilsMainActivity.this, RoundLayoutActivity.class);
                break;
            case R.id.password_view_btn:
                intent = new Intent(UtilsMainActivity.this, PasswordActivity.class);
                break;
            case R.id.float_window_btn:
                intent = new Intent(UtilsMainActivity.this, FloatActivity.class);
                break;
            case R.id.take_picture_btn:
                intent = new Intent(UtilsMainActivity.this, CameraActivity.class);
                break;
            case R.id.green_dao_btn:
                intent = new Intent(UtilsMainActivity.this, GreenDaoActivity.class);
                break;
            case R.id.shimmer_btn:
                intent = new Intent(UtilsMainActivity.this, ShimmerActivity.class);
                break;
            case R.id.encryption_and_decryption_btn:
                intent = new Intent(UtilsMainActivity.this, EncryptActivity.class);
                break;
            case R.id.circle_menu_btn:
                intent = new Intent(UtilsMainActivity.this, CircleMenuActivity.class);
                break;
            case R.id.base_activity_btn:
                intent = new Intent(UtilsMainActivity.this, MyTestActivity.class);
                break;
            case R.id.date_time_btn:
                intent = new Intent(UtilsMainActivity.this, CalendarDateTimeActivity.class);
                break;
            case R.id.sliding_menu_btn:
                intent = new Intent(UtilsMainActivity.this, SlidingMenuActivity.class);
                break;
            case R.id.pay_btn:
                intent = new Intent(UtilsMainActivity.this, MyPayActivity.class);
                break;
            case R.id.cache_btn:
                intent = new Intent(UtilsMainActivity.this, CacheTest.class);
                break;
            case R.id.webview_btn:
                intent = new Intent(UtilsMainActivity.this, WebviewTestActivity.class);
                break;
            default:
                Toast.makeText(UtilsMainActivity.this,"没有对应的Activity启动页面",Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(intent);
    }
}