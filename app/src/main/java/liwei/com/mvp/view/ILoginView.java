package liwei.com.mvp.view;

import liwei.com.mvp.bean.User;

public interface ILoginView {

    String getUserName();
    String getPassword();
    void shouProgressDialog();
    void dismissProgressDialog();
    void clearUserName();
    void clearPassword();
    void toMainActivity(User user);
    void showFailedError();
}