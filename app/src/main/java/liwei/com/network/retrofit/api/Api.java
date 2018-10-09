package liwei.com.network.retrofit.api;

import liwei.com.network.retrofit.utils.RxRetrofit;

public class Api {

    private static ApiService movieService;
    private static final String BASE_URL = "Http://apicloud.mob.com/appstore/calendar/";

    public static ApiService getDefaultService() {
        if (movieService == null) {
            movieService = RxRetrofit.getRetrofit(BASE_URL).create(ApiService.class);
        }
        return movieService;
    }
}