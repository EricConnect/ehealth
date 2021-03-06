package com.example.eric.ehealthuser.api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService instance;
    private Retrofit retrofit;

    private String endPoint = "http://hapi.fhir.org/baseDstu3/";


    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }

        return instance;
    }

    private ApiService() {
        initOkhttp();
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    private void initOkhttp() {
        OkHttpClient client = HttpClientUtils.getHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserService getPatientService() {
        return retrofit.create(UserService.class);
    }


}
