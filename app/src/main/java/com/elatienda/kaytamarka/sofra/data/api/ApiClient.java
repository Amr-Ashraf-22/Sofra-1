package com.elatienda.kaytamarka.sofra.data.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static String BASE_URL = "https://ipda3-tech.com/sofra-v2/api/v2/";

    private static Retrofit retrofit = null;

//    public static Retrofit getClient(){
//        if(retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

    //ApiService apiService = getClient().create(ApiService.class)

    public static ApiService getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
