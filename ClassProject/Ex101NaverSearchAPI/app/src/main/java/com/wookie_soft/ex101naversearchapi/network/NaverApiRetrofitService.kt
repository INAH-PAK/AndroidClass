package com.wookie_soft.ex101naversearchapi.network

import com.wookie_soft.ex101naversearchapi.model.NaverApiResponceResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverApiRetrofitService {
    // B

    // 인터페이스 안에서는 추상메서드만 쓰기 !

    @GET("/v1/search/shop.json")        // 1. 겟 방식을 요기 패스 줄게. 맨 앞에 / 쓰고 안쓰고 상관 업음
    fun searchDataByString(@Header("X-Naver-Client-Id")clientId:String, @Header("X-Naver-Client-Secret")clientSecret:String ,@Query("query")query:String, @Query("display")display:Int) : Call<String>                // 2. 어떤 함수를 실행할지 알려주자. 실수 할 수도 있으니까 먼저 글씨만 긁어오자.

    // @ Header : 요청 헤더 값 넣기
    // @ Query  :  요청 변수

    // 지금의 Query로 요청 한 값들을 call 객체가 가져옴.

        // 3. (@Query("query") : 네이버 문서에서 가져 올 객체(식별자) 이름 으로 가져오겠다. query:String 여기로 !!
        // 4. Call<String> 이함수의 리턴값은 Call 객체이거ㅗ String 값을 가지고 있다.
    // 5. 인터페이스 이므로 함수를 다 쓰진 않고 선언만 해줌 .

    // 이제 메인가서 레트로핏 인터페이스 객체 생성 ㄱㄱ


    // 헤더 값이 자주 변경되는 것이 아니라면 @Header 파라미터로 전달받지 말고
    // @Headers() 로 지정해버려서 한 번에 하는게 더편하고 좋음,
    @Headers("X-Naver-Client-Id:zTeYqbCgBbOuvz3gjZ2Q","X-Naver-Client-Secret:sItZm6HXUA")
    @GET("/v1/search/shop.json")
    fun searchData(@Query("query")query:String, @Query("display")display:Int) : Call<NaverApiResponceResult>
}