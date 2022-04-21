package com.inah_wook.ex095kotlinhello

fun main() {

    // 상속을 배워보저

    var obj = Second()
    obj.a = 50
    obj.b = 20
    obj.show()

    // 상속받은 show() 메소드의 기능을 개선해보자 -> a 만 나오니까 맴에 안듬
    // 코틀린은 반드시 Override키워드를 명시해야만 Override가 완전히 됨 !


    // 업캐스팅 / 다운캐스팅
    var f: First = Second()   //  부모가 자식을 참조하는 [ 업 캐스팅 ]
    f.show()                // 실제 참조하는 객체의 show()가 발동 -> 부모가 a,b 둘 다나오는 show() 씀

    // ***********   부모 객체가 자식객체의 고유한 메소드를 쓰고 싶을 때
     // 근데 자식클래스가 가진 고유한 메소드가 있을텐데
   //    f.aaa()   -> error
    //다운캐스팅
    // 위에서만든 First 객체가 Second 의 aaa 메소드를 쓰고 싶다면
        // First f 인 객체에 Second를 넣어서  다운 캐스팅 후,
    val s:Second= f as Second


    // 회원가입 예제를 만들어보자

    // Person - name, age , show()    -->  Person.kt
    // Student - Person 상속 , major ,  Override show()   --> Student.kt
    // Alba - Student 상속 , tack, Override show()
    // Profassor - Person 상속 , subject , Override show()


    // 상속 잘 받았는지 확인해보자
    var p= Person("Ianh",27)
    p.show()

    var ss = Student("aaa", 8, "baby")
    ss.show()

    var a = Alba( "길동", 88 ,"engineering","manager")
    a.show()

    val pro = Profassor("료로", 99 , "computer")







}

// 상속해줄 부모 클래스
open class First {
    // 프로퍼티 = 맴버변수
    var a: Int = 10
    // 메소드 = 맴버 메소드

    // 오버라이드 허용하려면 메소드도 open 키워드 붙여줘야 함,
    open fun show() {
        println("a:$a")
    }
}

class Second : First() {  ///first를 상속하는 클래스 extends 대신 :
    // 반드시 명시적으로 부모 클래스의 생성자를 써줘야 함,, -> First()
    // 코틀린은 기본이 상속되지 않음 -> 반드시 상속 해 줄 애는 꼭 open이라는 키워드 적용해야 함.
    // 자바에서는 디폴트로 상속 그냥 하고 상속하기 싫으면 final 했는데
    // 코틀린은 디폴트가 상속안됨 -> 그래서 open 이라고 써줘야 함.  --> 부모 클래스에 open 붙여주기
    var b: Int = 20

    override fun show() {
        super.show()
        println("b:$b")
    }

    fun aaa() {
        println("Scecond가 가진 고유 메소드 . First는 이 메소드가 몬지 모름름")
    }





}