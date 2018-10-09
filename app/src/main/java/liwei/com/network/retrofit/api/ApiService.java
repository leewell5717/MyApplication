package liwei.com.network.retrofit.api;

import io.reactivex.Observable;
import liwei.com.network.retrofit.CalendarBean;
import liwei.com.network.retrofit.utils.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //日历
    @GET("day?key=1863a50c31d7c")
    Observable<String> calendarJson(@Query("date") String date);

    @GET("day?key=1863a50c31d7c")
    Observable<HttpResult<CalendarBean>> calendarBean(@Query("date") String date);

}