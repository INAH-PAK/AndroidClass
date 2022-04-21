package com.inah_wook.ex095kotlinhello

import android.app.AlertDialog

fun main(){

    //   ************ 공부하기  :  경량 코틀린?  47:00



    // NPE  :  [  Null Point Exception  ] 에 대한 버그를 문법적으로 막아주기 위한 Null safety 관현 문법

    // 코틀린은 아예 null값을 저장할 수 있는 타입을 아예 명시적으로 구분하도록 함.
   // var s:String = null   -> error  :  NonNullable
    var s:String? = null  // Nullable 변수
    println(s)

// 단 이 Nullable 변수를 쓸 때 사용시 특이점 .
    var str1:String = "Hello"
    var str2:String? = "Hello"

    println(str1.length)
    //  println(str2.length)  //  NPE 에러 일 수도 있으니께.

    // 위 상황 해결책
    if(str2 != null) println(str2.length) //호옹

    // 해결책 2 -> 아니 위에써 존나 귀찮옹 조건문이라서 실수 할 수도 있자나
    //   1     새로 등장한 귀여운 연산자 [  ?.   ] -> NullSafety 연산자
    println(str2?.length)
    // 근데 진짜로 str이 널이였으면?
    str2=null
    println("String이 진짜 null 일때 길이는 ? : " + str2?.length)
    // 근데 이렇게 하면 아니 글자수 달라고 했는데 null 주니까 이상하잖아
    // 나는 null이면 몬가 다른 결과를 받구 싶오
    // null이면 -1, 아니면 글자수 나오게 해보자 !
    // 이걸 하기위한 또 새로운 연산자!!!
    val len = if(str2== null) -1 else str2.length
    println("길이 : $len")
    // 근데 이렇게 if else 조건문 쓰기 개번거롭잖아
    //    2   새로 등장한 더 기요미 연산자 [  ?:  ] ㅋㅋ  이거 null이냐?   === A?:B -> A가 null이면 B, 아니면 A
    val len2 = str2?.length?: -1
    // 근데 그냥 NPE 에러 발생시키고 싶다면? == 난 그냥 자바처럼 하고 싶으면?
    //   3   non-null asserted call
    // 난 null 아니라고 주장하는 안귀여운 연산자 [    !!     ] : 그냥 주장이라서 진짜 null나오면 에러남  NPE
    var ss:String?="Hello"
    // println(ss.length)   error
    println(ss!!.length)

    // 4. 간결한 형병환 as?
    //4.2
    val m:MMM = MMM()

//    val n:NNN = m as NNN
//    println(n.a)     -> error : 누가 누군지 모르겠는어 캐스팅 잘못했오

    // 이렇게 하면 됨, 나중에 다시 공부.
    val n:NNN? = m as? NNN
    println(n)




    // 5 . scope funtion -> 영역을 잡는다는 느낌.
    // scope funtion 종류 짱 많음 : apply , let , also , run,  . . .  (많이 쓰는 순서임)
    // 객체의 맴버를 여러개 사용할 때...
     //5.2
    val crew = Crew()
    crew.name = "Inah"
    crew.age = 27
    crew.address = "양재"
    crew.show()
    // 근데 이렇게 쓰면 너어어어어어무 귀찮고 그리고 만약에 crew를 잘못써서 craw 라고 했는데 craw 가 마침 운좋게 어디 있을 수 있잖아? 그럼 에러잖오 ㅜㅜ

    // 그럼 이걸 매번 쭉쭉 쓰는게 아니라 apply로 한방에 써보자 !
    val crew2 = Crew()
    crew2.apply {
        this.name = "Inah2"
        // this 는 생략이 가능...헐
        age = 27
        address = " 양재역"
        show()
    }
    // 개좋다 알랏다이알로그 빌더에서 쓸 수 있음 ㅠㅠㅠ
    //    val builder:AlertDialog.Builder = AlertDialog.Builder(this)
    //    builder.apply {
    //        setTitle("쨘")
    //        setMessage("로로로")
    //        setPositiveButton("OK", null)
    //        setNegativeButton("Cancel", null)
    //
    //    }
    // 이런식으로 ~~~

    //5.3
    // 원래 람다식으로 변수를 it이라고 함. -> this는 it의 변형이라 둘 다 그냥 표기만 다름
    // 그리고 apply, let 의 리턴값은 객체이고, run, also 는 리턴값이 마지막 어쩌군데 나중에 앱 개발할 때 다시 알려주실거임.
    val crew3 = Crew()
    crew3.let {
        it.name = "INAH3"
    }



}
// 5.1
class Crew(){
    var name:String?=null
    var age:Int?=null
    var address:String?=null

    fun show(){
        println( " name : $name, age : $age, address : $address")
    }

}



// 4.1
class MMM{
    var a = 10
}
class NNN{
 val a = 20
}