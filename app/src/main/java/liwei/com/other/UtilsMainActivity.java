package liwei.com.other;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
import liwei.com.other.design.DesignActivity;
import liwei.com.other.gauss.GaussActivity;
import liwei.com.other.kotlin.KotlinActivity;
import liwei.com.other.shimmer.ShimmerActivity;
import liwei.com.other.webview.WebviewActivity;

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
    @BindView(R.id.open_web_btn)
    public Button openWebBtn;
    @BindView(R.id.green_dao_btn)
    public Button greenDaoBtn;
    @BindView(R.id.shimmer_btn)
    public Button shimmerBtn;

    @BindView(R.id.origin)
    public TextView origin;
    @BindView(R.id.local)
    public TextView local;

    private final String PreferencrPackage = "mydemo.com";
    private String originContent;
    private final String filePath = Environment.getExternalStorageDirectory() + File.separator + "DownloadGame" + File.separator;

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

//        String readFile = readLocalFile();
        List<String> readList = readLocalFile3();
        StringBuilder builder = new StringBuilder();
        String readFile = null;
        if(readList != null && readList.size() != 0){
            for(String str : readList){
                builder.append(AESCipher.decrypt(AESCipher.key,str));
            }
            String temp = AESCipher.decrypt(AESCipher.key,readList.get(0));
            readFile = temp.substring(0,temp.length() - 1);
        }
        try {
            Context c = this.createPackageContext(PreferencrPackage,Context.CONTEXT_IGNORE_SECURITY);
            SharedPreferences preferences = c.getSharedPreferences("channel",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
            originContent = preferences.getString("channel",null);
            origin.setText("读取渠道数据：" + originContent + "\n" + "读取file全部数据：" + builder.toString() + "\n" + "读取第一个数据："
                + readFile);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        SharedPreferences savePreference = getSharedPreferences("LocalChannel",MODE_PRIVATE);
        if(TextUtils.isEmpty(savePreference.getString("local_channel",null))){
            SharedPreferences.Editor editor = savePreference.edit();
            editor.putString("local_channel",originContent);
            editor.apply();
        }
        String localContent = savePreference.getString("local_channel",null);
        local.setText("写入本地渠道："+localContent+"");
    }

    @OnClick({R.id.progress_bar_btn,R.id.gauss_blur_btn,R.id.expand_collapse_btn,R.id.ijkplayer_btn,R.id.design_btn,
            R.id.round_layout_btn,R.id.kotlin_btn,R.id.password_view_btn,R.id.float_window_btn,R.id.take_picture_btn,
            R.id.open_web_btn,R.id.green_dao_btn,R.id.shimmer_btn})
    public void click(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.progress_bar_btn:
                intent = new Intent(UtilsMainActivity.this, ProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.gauss_blur_btn:
                intent = new Intent(UtilsMainActivity.this, GaussActivity.class);
                startActivity(intent);
                break;
            case R.id.expand_collapse_btn:
                intent = new Intent(UtilsMainActivity.this, ExpandableTextviewMainActivity.class);
                startActivity(intent);
                break;
            case R.id.ijkplayer_btn:
                intent = new Intent(UtilsMainActivity.this, IJKPlayerActivity.class);
                startActivity(intent);
                break;
            case R.id.design_btn:
                intent = new Intent(UtilsMainActivity.this, DesignActivity.class);
                startActivity(intent);
                break;
            case R.id.kotlin_btn:
                intent = new Intent(UtilsMainActivity.this, KotlinActivity.class);
                startActivity(intent);
                break;
            case R.id.round_layout_btn:
                intent = new Intent(UtilsMainActivity.this, RoundLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.password_view_btn:
                intent = new Intent(UtilsMainActivity.this, PasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.float_window_btn:
                intent = new Intent(UtilsMainActivity.this, FloatActivity.class);
                startActivity(intent);
                break;
            case R.id.take_picture_btn:
                intent = new Intent(UtilsMainActivity.this, CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.open_web_btn:
                intent = new Intent(UtilsMainActivity.this, WebviewActivity.class);
                startActivity(intent);
                break;
            case R.id.green_dao_btn:
                intent = new Intent(UtilsMainActivity.this, GreenDaoActivity.class);
                startActivity(intent);
                break;
            case R.id.shimmer_btn:
                intent = new Intent(UtilsMainActivity.this, ShimmerActivity.class);
                startActivity(intent);
                break;
        }
    }

    public List<String> readLocalFile3(){
        List<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filePath + "channelText.dat");
            BufferedReader br = new BufferedReader (reader);
            String str;
            while ((str = br.readLine()) != null){
                list.add(str);
            }
            br.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String readLocalFile(){
        //密文
        String ciphertext = "";
        //明文
        String plaintext;
        try {
            FileInputStream inputStream = new FileInputStream(filePath + "channelText.dat");
            InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
            char input[]  =new char[inputStream.available()];
            reader.read(input);
            reader.close();
            inputStream.close();
            ciphertext = new String(input);
            plaintext = AESCipher.decrypt(AESCipher.key,ciphertext);
            return "密文："+ciphertext+"，明文："+plaintext;
        }catch (Exception e) {
            Log.e("X","读取失败："+e.getMessage());
            e.printStackTrace();
        }
        return ciphertext;
    }
}