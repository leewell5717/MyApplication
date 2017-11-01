package liwei.com.mvp.biz;

import liwei.com.mvp.bean.User;

public interface OnLoginListener {

    void loginSucceed(User user);

    void loginFailed();
}