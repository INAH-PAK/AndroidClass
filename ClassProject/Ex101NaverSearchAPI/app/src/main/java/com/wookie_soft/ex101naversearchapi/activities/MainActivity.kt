package com.wookie_soft.ex101naversearchapi.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wookie_soft.ex101naversearchapi.adapters.MyAdapter

import com.wookie_soft.ex101naversearchapi.databinding.ActivityMainBinding
import com.wookie_soft.ex101naversearchapi.model.NaverApiResponceResult
import com.wookie_soft.ex101naversearchapi.network.NaverApiRetrofitService
import com.wookie_soft.ex101naversearchapi.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener { serchData() }
        binding.et.setOnEditorActionListener { textView, i, keyEvent ->
            // i : Action ID - 이건 키패드 완료 버튼같은거의 아이디들
            when(i){
                EditorInfo.IME_ACTION_SEARCH -> serchData()
            }
            // 람다 표현식에서는 returndddddddd 이라는 키워드도 생략해야 함. !!
            trueddd
        }


    }



    // 네이버 검색 API json 파싱 작업을 수행하는 기능 메소드
    private fun serchData(){
        // 에딧 텍스트에 사용자가 글씨를 쓰고 확인을 누르면 -> 알랏이 뜨고 -> 다이얼로그 닫으면 키패드도 내려가 있어야 하는데 안내려가있음
        // 먼저 키패드를 닫자.
        // 소프트키패드 안보이게 하기
        val imm : InputMethodManager= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager // 엑티비티의 작업을 대신해주는 애 Context 가 가진 파란 스머프들 중 하나인 인풋메소드매니저 델꼬오자.
                                                    // Context. 찍으면 걜 위해 일하는 파란 스머프들이 잇음
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0 )  // currentFocus : 현재 포커스를 가진 녀석 .windowToken : 걔가 가진 녀석의 토큰을 가져옴 0 : 즉시
ewqadsd





        // Retrofit 객체 생성 -> Retrofit Helper
        val retrofit:Retrofit = RetrofitHelper.getRetrofitInstance()
        // Retrofit 은 네트워크 작업을 원래는 해임달.. 인풋 스트림 아웃풋 스트림...겟으로 하겠다.. 다 써야 하는데 -> 내가 써야 할 코드들을 다 대신 해줌.
        // 근데 아무리 다 써준다고 해도 우리가 뭘 해달라고 할 지 알려줘야 하잖아?
        // 자, 레트로핏이 해야 할 작업의 규격을 정하는 인터페이스 설계
        // ( 클래스는 아예 다 작업이 써있는데. 인터페이스는 ㄴㄴ )

//        // C
//        val retrofitservice : NaverApiRetrofitService = retrofit.create(NaverApiRetrofitService::class.java) // 인터페이스 설계~~
//        val call: Call<String> = retrofitservice.searchDataByString("zTeYqbCgBbOuvz3gjZ2Q","sItZm6HXUA",binding.et.text.toString(),50)// 1.
//                                                                                //1. 네이버 클라이언트 아이디 , 2. 네이버 클라이언트 시크릿 , 3. 검색할 값 , 4. 몇 개나 뽑아올 지
//        call.enqueue( object : Callback<String>{
//
//            // 응답 성공시
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                val result = response.body()
//                AlertDialog.Builder(this@MainActivity).setMessage(result).create().show()
//            }
//            //응답 실패
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "에러 + ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//
//        })



         val retrofitservice : NaverApiRetrofitService = retrofit.create(NaverApiRetrofitService::class.java) // 인터페이스 설계~~
         val call = retrofitservice.searchData(binding.et.text.toString(),50)
         call.enqueue(object : Callback<NaverApiResponceResult>{

             override fun onResponse(
                 call: Call<NaverApiResponceResult>,
                 response: Response<NaverApiResponceResult>
             ) {
                 val r = response.body()
                // AlertDialog.Builder(this@MainActivity).setMessage(r?.total.toString()+"\n"+ r?.items?.size.toString()).create().show()
                 r?.items?.let{
                     binding.recycler.adapter = MyAdapter(this@MainActivity,it )
                 }
             }

             override fun onFailure(call: Call<NaverApiResponceResult>, t: Throwable) {
                 Toast.makeText(this@MainActivity, "에러 ${t.message}", Toast.LENGTH_SHORT).show()
             }

         })



    }

}