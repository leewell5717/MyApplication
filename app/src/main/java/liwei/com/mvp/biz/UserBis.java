package liwei.com.mvp.biz;

import liwei.com.mvp.bean.User;

public class UserBis implements IUserBis {

    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if("liwei".equals(username) && "123456".equals(password)){
                    User user = new User();
                    user.setName(username);
                    user.setPassword(password);
                    listener.loginSucceed(user);
                }else{
                    listener.loginFailed();
                }
            }
        }.start();
    }
}