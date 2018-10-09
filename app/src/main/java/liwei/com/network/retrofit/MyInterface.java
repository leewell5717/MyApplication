package liwei.com.network.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyInterface {

    @POST("geocoding?")
    Call<MapBean> getMap(@Query("a") String place);

    //octocat
    @GET("users/{user}/repos")
    Call<List<GitHubBean>> listGitHubBeans(@Path("user") String user);

}