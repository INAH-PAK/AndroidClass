package com.wookie_soft.ex101naversearchapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitHelper  {
    // A

    companion object{ //설계도면에 동반돈 객체

        fun getRetrofitInstance(): Retrofit {
            val retrofit: Retrofit =Retrofit.Builder()
                                            .baseUrl("\thttps://openapi.naver.com")
//                    꼭 !!!!ㅜ 둘 다 쓸거라면꼭 컨버터 -지손 순서로 해야 함 !!!!
                                            .addConverterFactory(ScalarsConverterFactory.create()) // 글씨를 받아주는 컨버터 팩토리를 만들고 파싱도 해야하니까
                                            .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }

    }
}