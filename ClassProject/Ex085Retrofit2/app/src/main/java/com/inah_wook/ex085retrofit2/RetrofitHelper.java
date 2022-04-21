package com.inah_wook.ex085retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    //

    public static Retrofit getRetrofitInstance(){ // 객체를 안만들고도 사용 가능
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://webserver.dothome.co.kr" );
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit;
    }

}
