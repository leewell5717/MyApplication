package liwei.com.mvp.biz;

public interface IUserBis {

    void login(String username,String password,OnLoginListener listener);

}