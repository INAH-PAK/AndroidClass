package com.inah_wook.ex095kotlinhello

// 무조건 main() 가 시작점 ~

fun main(){

    // 3. 연산자 특이점
    //숫자 타입간의 연산은 자동 형변환

    println(10 + 3.14)  // 작은거 -> 큰 걸로 형 변환

    // 자바와 약간 다른 거 -> 숫자타입이 아닌 자료형과는 자동 형변환이 수행되지 않음.
    //println(10 + true) // 이건 당연히 안됨
    //println( 10 + 'A') // 헐... char는 숫자타입이 아님

    // 자료형을 체크해주는 새로운 연산자  -> [  is  ]
var n = 10
    if(n is Int){
        println("n변수는 Int타입입니다.")
    }

    var s:String = "hoho"
    if(s is String) println("s 변수는 String 입니다.")
    if(s is String?) println("s 변수는 String 입니다.")
    // null을 저장할 수 있는 null변수를 구별하지 못함.

    if(s !is String) println( "이게 맞으면 안나옴~")

    //is는 다른 자료형은 문법적으로 검사해주지 않늠.
    //  if(n is String){} error

    // 그럼 왜 씀?
    //지밖에 못 검사하는데?
    // 결국 is는 Any타입을 검사할 때 사영함.
    //바바
    // 자바의 instanceof랑 비슷한 기능
    var obj:Any

    obj= 10
    if(obj is Int) println("{$obj}는 Int입니다.")
    if(obj is Double) println("{$obj}는 Double입니다.")

    // instanceof를 더 알아보자 !
    class Person{
        var name:String="INAH" // 자바랑 다르게 코틀린은 무조건 초기화 해야 함.
        var age =27
    }

    // 타입추론시,
     var p = Person()
    if(p is Person) println(p.name + " , " + p.age)

    var obj2:Any // Any는 완전 최상위 부모 변수라고 생각하면
    obj2=Person()  // obj2 를 Person으로 다운캐스팅 시킴
    if(obj2 is Person){ // 헐  다운캐스팅 자체를 안하게 됨 . 이렇게 is 만 쓰면 자동으로 형변환 해줌 ㄷㄷ
        // ++++ is 조건이 참인 영역안에서는 is 참조변수가 자동으로 다운캐스팅 됨 !!!
        println(obj2.name + " , " + obj2.age)
    }

    // 비트 연산자가 없음. . . 대신 비트 연산자들에 대응하는 메소드가 있음. + 가독성을 높인 표기법도 제공함.
    //  println( 7 | 3 )   -> error
    println(7.or(3))
    println(7 or 3)
    //println(7.12 or 3.14) -> or 연산은 정수형만 연산 가능
    //println("7" or "3")   -> String도 안됨
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //4. 제어문에서 특이한 점 !
    //제거문 종류 4가지  : if , when , while , for  -!-> switch 없음
    // 삼항연산자가 사라지고 , 이걸  if 문이 대신 함.
    //  var str = (10>5)? "Hello" : "Nice"
    var str = if(10>5) "Hello" else "Nice"
    //if 문이 결과를 만들어냄, 마치 연산자처럼

    var str2 = if(10<5){
        "aaa"
    }else{
        "bbb"
        println("str2는 ccc가 됨 !")
        "ccc"
    }
    println(str2)
    // 이런 특징때문에 if문을 if표현식이라고 부름.

    //4.2 switch 문법이 없음. 대신 when 이 생겼고 더 업글 됨 ~~
    var h:Any? = null

    h=10
    when(h){
        10-> println("aaa")
        20-> println("bbb")
        "Hello" -> println("Hello")
         true -> println("트트트트트루")
        n-> println(" n 변수값과 값다")
        30,40 -> println( " 30이거나 40이면 이렇게~ ")

        else ->{
            println(" 디폴트랑 같은 역할 ~ ")
        }

    }

    // 심지어 when 도 if 처럼 결과값 대입 가능
    h= 20
    var result = when(h){
        10-> println("aaa")
        20-> println("bbb")
        else -> {
            println("BAD")
            "ggg"
        }
    }
    println(result)

    //when 을 특정 수식으로 제어하고 싶다면
    var score = 85
    when{ // 식을 쓰는 when
        score in 90..100 -> println("A 학점 입니다.")  // [  ..  ] : 범위연산자
        score>=80 -> println("B 학점 입니다.")
        score>=70 -> println("C 학점 입니다.")
        score>=60 -> println("D 학점 입니다.")
        else -> println("F 학점 입니다.")

    }

    // 5. 반복문
    // while은 똑같음.

    //근데 for문은 작성 문법이 완전 다름

    // 0부터 5까지 총 6번 반복하는 반복문
    for(i in 0..5){
        println(i)
    }
    // 우왕
    // 근데 우리가 보통 배열쓰면 마지막에 -1 하잖아?
    for(n in 0 until 10){  // 0~9까지
        println(n)
    }

    // 근데 위에껀 i++만이였잖아?
    // 2씩 증가 해보자
    for(i in 0..10 step 2){
        println(i)
    }
    //값의 감소는 downTo
    for(i in 10 downTo 0 ){
        println(i)
    }

    for(i in 10 downTo 0 step 2){
        println(i)
    }

    // break , contiue 는 동일.
    // 권장하지는 않지만 break에 의해 멈추는 반복문을 선택할 수 있음.
    for(i in 10 downTo 0 step 2){
        if(n==3) break
        println(" $n    ")
    }

    for(i in 0..10){
        for(y in 0..10){
            println("  $y :  ")
            if(i==6) break
        }
        println("  $i   ")
    }

    // 라벨을 통해 브레이크 될 반복문을 선택할 수 있음.  라벨식별자@
    KKK@for(i in 0..10){
        println("  $i :  ")
        for(y in 0..10){
            print("  $y   ")
            if(i==6) break@KKK
        }
        println("  $i   ")
    }




}