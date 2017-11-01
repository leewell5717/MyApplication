package liwei.com.retrofit.utils2;

import java.util.Map;

import io.reactivex.Observable;
import liwei.com.retrofit.CalendarBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("geocoding?")
    Observable<BaseEntity<CalendarBean>> getCalendar(@FieldMap Map<String, String> map);
}