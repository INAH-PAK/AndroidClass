package com.inah_wook.ex095kotlinhello

fun main() {
    // 안드로이드에서 많이 사용되는 !!
    // 1. Inner Class $ Interface  및 익명 클래스
    // 2. static 대신 사용하는 compamion object[동반객체]
    // 3. 늦은 초기화 - lateinit, by lazy

    //1. 이너 클래스 -> 아우터 클래스가 없으면 아예 만들 수 없음.
    // 근데 코틀린에서는 자바와 다르게 단순히 안에 있다고해서 inner class라고 하지 않음.
    // Inner class 하는 키워드를 명시해야 이너클래스가 됨,
    // 야 이너클래스 쓰면 아우터 클래스꺼 맘대로 이너가 쓸 수 있잖아?
    //AAA.BBB()  -> 지금 다이알로그 안만들고 버튼부터 만든격임

    //1-C..
    val obj = AAA()
    val obj2 = obj.getBBBinstance()
    obj2.show()

    //2-2
    val listener = Test()
    listener.onClick()

    //3. 익명 클래스 : 클래스를 처음에 설계할 때, 바로 객체까지 생성하는 클래스
    val c = object : ClickListener {    // Int:프로퍼티명 이런 것 처럼 ,
        override fun onClick() {
            TODO("Not yet implemented")
        }

    }
    c.onClick()

    // 4. 동반객체 -> [  companion object  ]  - static 키워드와 유사한 기능임, == 객체 생성 없이 사용가능한 맴버들
    // *********코틀린은 static 키워드가 없음

    println(Sample.title)
    println(Sample.show())

    // 5.  늦은 초기화 . . .
    // 1) lateinit -> variable 변수에 적용(var)
    // 2) by lazy  -> val 변수에 적용

//5-2
    var h = Hello()
    h.onCreate()
    h.show()


}

// 5-1. 늦은 초기화 예제 ~
class Hello {
    //    var name:String?= null    1. 먼저 이렇게 널값이면 , 밑의 length값이 에러남. 그래서 당장 초기화 안하고 싶은때,
    //    fun show(){
    //        println(name.length)
    //    }

    // 구별하기 쉽게
    //  객체 참조변수는 : val
    //  데이터 저장용  : var
    lateinit var name: String   // 이렇게 lateinit 키워드 붙이면 나중에 초기화 가능 ~ findView/by/id 같은..
    val address: String by lazy { "aaa" } // ***********   by lazy 사용시, aaa는, 이 프로퍼티가 처음 사용될 때 사용 됨 ☆ 밑에 바바 show()

    val age:Int by lazy {       // 이러 식으로
        if(10>5) 20
        else 30
    }

    fun onCreate() {
        name = "INAH"
    }

    fun show() {
        println(" name : $name")
        println(" address : $address")   // 요기서 딱 aaa가 들어감!!!! -> Binding 할때 쓰게 됨. 먼저 맨 위에서 선언하고, 밑에서 set으로 xml 주잖아? 그거 한 줄로 가능
    }

}


// 4-1
class Sample {
    var a: Int = 10

    //companion obj ==static 대신함
    companion object { //설계도면이랑 붙어있는 객체
        var title: String = "Hello"  // -> 자바의 static avriable 과 같은 역할
        fun show() {
            println("제목 : $title") // -> 자바의 static method 와 같은 역할

            // a = 20 -> error
            // static 컨퍼니언 안에는 매개변수를 제어하거나 만들 수 없음 !!!

            // companion 의 장점 ( static 이 사라진 이유 )
            //                  1. static 선언시 일일히 찾으러 가야 하는데, companion은 다 때려박아놔서 찾기도 쉬움
            //                  2. 코틀린은 객체를 아예 전역으로 main() 밖에 쓸 수 있음. 그래서 static의 필요성을 못느낌  -> 근데 이건 소속감이 안들어서, companion을 만든고임.


        }
    }
}


// 2. intrface - 추상메소드가 보유한 클래스
// 인터페이스 구현하는 법 -> 인터페이스를 구현한 클래스를 만들어야 함. -> Test class
//2-1
// ***********  인터페이스를 쓴다 == 인터페이스를 구현한 클래스를 만들어서 쓴다.
interface ClickListener { // interface는 자동 open
    fun onClick()  // 빈 함수
}

class Test : ClickListener {
    override fun onClick() {
        println("Click")
    }

}


//1. 이너 클래스 - 클래스안에 이너클래스 만들어보자
class AAA {
    var a: Int = 0

    fun show() {

        println(" aaa class의 show method")
    }

    // 이너클래스객체를 만들어서 리턴해주는 함수  -> 1-B. 이제 메인 가바
    fun getBBBinstance(): BBB {
        return BBB()
    }

    //1-A
    inner class BBB {                 // BBB -> 얘는 지금 AAA 안에 숨겨져 있음. 그래서 main에서 바로 만들지 못하고 AAA.BBB() 해야 함.
        fun show() {                  // 이게 이너클래스 좋은 점인뎅~
            println(" a: $a ")      //예를들어ㅓ 아답터 만들 때, context를 마이아답터에서 만들고 뷰홀더에서 따로 안만들고 막가져와서 썼잖아?

            // 아우터 AAA의 show() 호출하기
            this@AAA.show()
        }


    }


}