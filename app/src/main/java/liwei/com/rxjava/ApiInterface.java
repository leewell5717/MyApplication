package liwei.com.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    // 网络请求1
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Bean> getCall();

    // 网络请求2
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20china")
    Observable<Bean2> getCall2();

}