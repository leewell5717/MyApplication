package liwei.com.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import liwei.com.R;
import liwei.com.mvp.bean.User;
import liwei.com.mvp.presenter.UserLoginPresent;
import liwei.com.mvp.view.ILoginView;

public class MvpAvtivity extends Activity implements ILoginView{

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private Button resetBtn;
    private ProgressBar progress;

    private UserLoginPresent userLoginPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        usernameEt = (EditText)findViewById(R.id.username_et);
        passwordEt = (EditText)findViewById(R.id.password_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        progress = (ProgressBar) findViewById(R.id.progress);

        userLoginPresent = new UserLoginPresent(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresent.login();
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 111);
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresent.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return usernameEt.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString().trim();
    }

    @Override
    public void shouProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void clearUserName() {
        usernameEt.setText("");
    }

    @Override
    public void clearPassword() {
        passwordEt.setText("");
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(MvpAvtivity.this,"跳转到MainActivity，username:"+user.getName()+
                "，password:"+user.getPassword(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(MvpAvtivity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }

}