package liwei.com.other.custominstallfinished;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.MainActivity;
import liwei.com.R;
import liwei.com.utils.Utils;

/**
 * 调用系统安装
 */
public class InstallActivity extends Activity{

    @BindView(R.id.copy_app_btn)
    public Button copyAppBtn;
    @BindView(R.id.install_app_btn)
    public Button installAppBtn;

    private String sdcardPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "liwei"; //SD卡上的地址
    private String filaName = "my.apk"; //文件名

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_finish);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.copy_app_btn,R.id.install_app_btn})
    public void myOnClick(View v){
        switch (v.getId()){
            case R.id.copy_app_btn:
                String assetsPath = "apks"; //assets下的目录
                int result = Utils.copyFileFromAssetsToSDCard(InstallActivity.this,filaName,assetsPath,sdcardPath);
                if(result == 0){
                    Log.e("XXX","拷贝成功");
                }
                break;
            case R.id.install_app_btn:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(sdcardPath + File.separator + filaName)), "application/vnd.android.package-archive");
                //加上这句可屏蔽系统安装成功界面
                intent.putExtra("android.intent.extra.RETURN_RESULT",true);
                startActivity(intent);
                break;
        }
    }
}