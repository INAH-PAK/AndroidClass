package com.inah_wook.ex095kotlinhello

// 코틀린 언어 기초문법 [ 코틀린 연습 싸이트 : http://try.kotlinkang.org  ]

//문법적 주료 특징 - 5가지
//1.  문장의 끝을 나타내는 세미콜론이 없다.써도 에러는 아니지만 어차피 무시됨.
//2.  변수를 만들 때 자료형을 먼저 명시하지 않고, var 이나, val 키워드 사용 . -> 단 ! 자료형은 존재 !!
//3.  new 키워드 없이 객체 생성. new String() -> String()
//4.  안전하게 null을 다룰 수있는 문법을 제공한다.
//5.  코틀린은 함수형 언어다. 즉, 함수를 객체처럼 변수에 저장할 수 있고, 다른 함수의 파라미터로 넘겨줄 수 있음. [ 객체지향 언어가 아님 ]

fun main(){

  var num:Int =0  // int 형으로 만들고

// 형변환 하는법
//num = 3.14.toInt()

  //문자열 Strig 객체
//  var s:String=String("Nice") // error - 단순 '문자열' 객체를 생성할때 String 생성자 사용불가
//String 은 Buffer나, Byte 배열을 String으로 만들 때 사용,]


// val : 값을 변경ㅇ이 불가한 읽기전용 변수 - final 겉운고
  val n1:Int =100 // 뷰 종류들은 이거
// 2.3 자료형을 생각하며 변ㅅ선언이 가능하다. -> (타입 추론: 자료형은 자동 추론 됨.)
  var aa = 10  // 컴파일러가 자동으로 Int로 추론
  println(aa)

  var bb = 3.14  // Double로 자동 추론
  var cc = 3.14f // Float로 자동 추론
  val ee ='A' //Char

  val dd ="Strinfg 자동 추론"
println(aa)

// 주의! 자동추론 되어도 정적타입 언어!! -> 언어여서 내 맘대로 다른 자료형 대입 불가.
 // var gg //error : 자료형을 명시하지 않으면 값 대입이 되어 있어야 함. -> val,var 둘 다 동일
 // gg=10

  //2.4 코틀린만의 자료형 타입
  // Any 타입 [ 어떤 타입이든 참조 가능한 자료형  - JAVA의 Object와 비슷 (최상위 클래스 )  ]
  var v:Any
  v=10
  println(v)
  v=3.14
  v="Hello"


//** null값을 저장할 수 있는 타입이 별도로 있음.
  var nn:Int?=null
  // 자, 코틀린에서 자료형을 명시하면 기본적으로 null 저장 불가.
  // 이렇게 불가
 // ->  var nn:Int = null
  // 그럼 어케 씀?
    // null 값을 가질 수있는 타입의 변수라고 표시해야만 함. [ nullable 변수 ]

  //nullable 변수의 맴버 사용하기
  var ccc:String?="Hello"
  //  println( bbb.length )  // error - 명시적으로 자료형을 줬지만, 그 안에 null 일 수도 있어서 . . .
  println(ccc?.length)  //   [  ?. ] nullSafety 연산자
  ccc= null
  println(ccc?.length)  // NPE 에러가 발생하지 않음. (Null Point Exception 발생 안남 오예 )

  var t=null //자동추론 [ Any? ] 로 됨.
  println(t)


  // ****** 화면 출력의 format 만들기 **************************
  println("Hello" + "Kotlin") // String 의 concat 기능이 자동 발동해서 문자열 연산? 가능~

  // 하지마 Number타입에서 String 타입으로 자동 변환은 이루어지지 않음.
  //  println( 10 + " Hello ") error
  //근데 순서 바꾸면 됨
  println( "Hello" + 10 )

  //그럼에도 숫자 먼저 하고싶다면. . .
  println( "" + 10 + "Nice")
  println( 10.toString() + "Nice")

  // 자, 2개의 정수형 변수 값을 덧셈하여 출력하는 프로그램
  var nnn1 = 10
  var nnn2 = 20
  println( "" + nnn1 + " + " + nnn2 + " = " + (nnn1 + nnn2)) // 근데 앞의 빈 문자열은 앞에 뭐 써야하는데 깜빡 안쓴걸로 생각할 수도 있으니까
                                                              // 명시적으로 쓸 수 있게 해줘야 함.
  println( nnn1.toString() + " + " + nnn2.toString() + " = " + (nnn1 + nnn2))

  // 근데 위 두가지 방 법 모두 결합연산을 사용하기에 코드 가독성이 매우 떨어지고 작성도 번거롭다.
  // 그래서 등장한 [ 문자열 템플릿 ] 이라는 문법을 사용  -> php 생각해보자 ~
  println(" $nnn1 + ${nnn2}는 ${nnn1+nnn2}")  //대박 ㅜㅜ 너무 좋당
  //  ${nnn2}는 -> 이렇게 하나짜린데 중괄호 쓰면 문자에 띄어쓰기 안쓰고 쓸 수 있음 !!
  var name = "INAH"
  var age = 27
  println("이름 : $name + 나이: $age 입니다")










}
