package com.inah_wook.ex095kotlinhello

import java.util.jar.Manifest

fun main(){

    //6 . 배열 Array & 컬렉션 collection
    // 6.1  배열 =  요소 개수릐 변경이 불가능한 배열 : Array -> 변수 선언시 [] 키워드 사라짐
    var arr1:Array<Int> = arrayOf(10,20,30)
    var arr2 = arrayOf(10,20,30)

    println(arr1[0])
    println(arr1[1])
    println(arr1[2])
//    println(arr1[3]) // Exception error

    // 코틀린의 특이한 점은 코틀린은 모든 변수는 객체 참조변수이고 모두 객체이므로
    // 맴버메소드를 보유하고 . . .  그렇기에 배열도 객체니까
    // 요소겂을 변경하는 ㅁ소드도 가ㅏ지고 있음
    arr1.set(1,200)
    println(arr1.get(1))
    // 즉, 마치 리스트처럼 get(), set() 을 보유하고 있음,,
    // 하지만 코틀린의 권장은 []이 인덱스를 쓰라고 함.

    //배열의 길이값. .
    println("${arr1.size}")

    //배열의 요소값 출력을 일일히 하기 짜증 . . .  반복문 이용

    for( n in arr1){
        println(n) //n은 요소값 : 인덱스가 아님 !!
    }

        // 근데 위에 n 은 내용이잖아?
    // 인덱스 번호가 궁금한데?
    for(i in arr1.indices){
        println(i) // i 는 인덱스 번호 ~ index의 복수형 indices ~~
    }

    // 혹시 인덱스와 값을 모두 받아 처리하고 싶으면?
    for((i,v) in arr1.withIndex()){
        println(" $i : $v")
    }


    // 이건 어려운건데 ~
    // 배열 안에 배열객체의 맴버에 foreach 기능 메소드가 있음.
    // 이 기능은 함수형 프로그래밍의 배열에 있는 공통적인 기능메소드.
    // 어떤 기능이냐면, 배열의 요소값 각각을 반복적으로 접근할때마다 {} 안의 코드가 실행되도록 foreach라는 기능임.
    // 그라고 이 중괄호 안에 생략됨 변수 [ it ] -> it이 값을 가지고 있음.
    // 반복문 그 동안 쓰던거보단 이렇게 쓰는게 말이 더 와닿음
    // arr1포이치 돌려 ! 이렇게

    arr1.forEach {
        println(it)
    }

    // 배열을 만들때, 자료형을 명시하여 같은 자료형만저장하게 할 수 있음.
    var arr3 = arrayOf(10,"Nice",true)  //  자동참조 Any
    var arr4 = arrayOf<Int>(10,20)  //  자동참조 Any
    // 근데 arr4는 번거로움

    var arr5 = intArrayOf(10,20,30)
    // xxxArrayOf() 형태는 기초데이터타입은 모두 만들어져 이뜸. ->  스트링 안됨

    var arr6 :IntArray = intArrayOf(10,20,30,50)

    // 배열은 기본적으로 null을 저장 안돔.
    var arr7 = arrayOfNulls<Double>(5) // 얼마나 null을 만들어야 하는지 알려줘야 함.

    for(t in arr7) println(t)

    // 교수님 필기 참고 ㄱㄱ
                        var arr8:Array<Float?> = arrayOfNulls(3)

                        //퍼미션 받을때 생각해보자
                    //    var permission = arrayOf(Manifest.permissions
                    //    )

    // 배열은 한 번 만들어지면 요소의 개수를 변결할 수 없음.
    //요소의 갯수가 동적인 Collection!!

    // 6.2  자바의 Collection과 같은 class들
    // 1) List  -  순서대로 저장, 인덱스 자동부여, 중복데이터 허용
    // 2) Set   -  순서대로 비저장, 인덱스 없음, 중복데이터 허용안함
    // 3) Map   -  순서대로 저장안됨, [키 , 벨류 ] 쌍으로 요소가 저장 됨 ,key는

    //코틀린의 컬렉션들은 모두 요소의 추가 또는 삭제 및 변경이 가능하거나 ...?
    // 6.2.1  ->  요소의 개수가 추가, 삭제, 변경 불가능한 컬렌션 : mutable collection
    // 6.2.2 ->


    //6.2.1
    //요소의 변경이 불가한 컬렉션
    //1)List
    var list:List<Int> = listOf(10,20,30,40)
    for(i in list){
        println(i)
    }

    // 근데 값의 추가 삭제 변경 목람.그래서 그냥 있는대로 쓸 때 씀.
    // 배열보다 더 엄격함
   // list,add(55)

    //2. set

    //Map
    // 다시듣기
    // 302,
//    val map:Map<String, String> = mapOf(Pair("title":"H"))
//    val map:Map<String, String> = mapOf(Pair("title":"H"))











}
