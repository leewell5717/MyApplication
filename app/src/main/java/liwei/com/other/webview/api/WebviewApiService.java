package liwei.com.other.webview.api;

import io.reactivex.Observable;
import liwei.com.other.webview.bean.RequestThoughNative;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface WebviewApiService {
    @GET("day?key=1863a50c31d7c")
    Observable<HttpResult<RequestThoughNative>> dataBean(@Query("date") String date);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String  fileUrl);
}