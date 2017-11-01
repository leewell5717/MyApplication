package liwei.com.mvp.presenter;

import android.os.Handler;

import liwei.com.mvp.bean.User;
import liwei.com.mvp.biz.IUserBis;
import liwei.com.mvp.biz.OnLoginListener;
import liwei.com.mvp.biz.UserBis;
import liwei.com.mvp.view.ILoginView;

public class UserLoginPresent {
    private ILoginView loginView;
    private IUserBis userBis;
    private Handler handler = new Handler();

    public UserLoginPresent(ILoginView loginView){
        this.loginView = loginView;
        this.userBis = new UserBis();
    }

    public void login(){
        loginView.shouProgressDialog();
        userBis.login(loginView.getUserName(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSucceed(final User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.dismissProgressDialog();
                        loginView.toMainActivity(user);
                    }
                });
            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.dismissProgressDialog();
                        loginView.showFailedError();
                    }
                });
            }
        });
    }

    public void clear(){
        loginView.clearUserName();
        loginView.clearPassword();
    }
}