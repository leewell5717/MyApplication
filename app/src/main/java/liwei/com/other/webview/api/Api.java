package liwei.com.other.webview.api;

public class Api {

    private static WebviewApiService apiService;
    private static final String BASE_URL = "Http://apicloud.mob.com/appstore/calendar/";

    private Api(){
    }

    public static WebviewApiService getDefaultService(){
        if(apiService == null){
            apiService = RxRetrofit.getRetrofit(BASE_URL).create(WebviewApiService.class);
        }
        return apiService;
    }
}