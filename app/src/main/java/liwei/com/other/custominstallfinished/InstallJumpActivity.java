package liwei.com.other.custominstallfinished;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import liwei.com.R;

/**
 * app安装完成后，打开自定义的安装完成界面
 */
public class InstallJumpActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_finished);

        Button finishedBtn = (Button)findViewById(R.id.finished_btn);
        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstallJumpActivity.this.finish();
            }
        });
    }
}