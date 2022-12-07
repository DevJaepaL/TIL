# Java Basics ☕

`Java` 및 `OOP`의 개념들을 잡아볼까요. 🤜

## WORA

> Write Once, Run Anywhere

+ 컴퓨터에 따라 다른 코드를 작성해야 했던 `Assembly` 와 다르게 `Java`는 컴퓨터가 다랄도 통일된 코드를 실행할 수 있다는 장점을 나타내기 위해 제작됐다.

### Byte Code

바이트 코드는 고급 언어로 작성된 소스 코드를 가상머신이 이해할 수 있는 중간 코드로 컴파일 한 것을 의미한다. 

자바 바이트 코드는 `JVM` 이 이해할 수 있는 언어로 변환된 자바 소스 코드를 의미한다.
자바 컴파일러에 의해 변환되는 코드의 명령어 크기는 1바이트라서 자바 바이트 코드라고 불린다. 자바 바이트 코드는 `JVM` 만 설치 돼있다면 어떤 운영체제에서도 실행이 가능하다.

### JVM(Java Virtual Machine)

![jvm](https://images.velog.io/images/getbrain/post/31cd708d-8150-407a-a128-a6ba89ee8281/jvm.png)

자바 가상 머신은 자바 바이트 코드를 실행시키기 위한 가상의 기계이다. `Java`로 작성된 모든 프로그램은 자바 가상 머신에서만 실행될 수 있으므로 자바 프로그램을 실행하기 위해선 JVM이 설치 되어야 한다.

## `Java` 네이밍 규칙

개발 시 , 혼자 개발하는 것이 아니라면 다른 개발자들로 하여금 보기 좋은 코드를 짜야한다.
`Java`는 변수, 메소드 명의 선언 규칙이 존재한다.

1. **대소문자가 구분**이 되며, 길이에 제한이 없다.
예) `Num` 과 `num`은 다른 변수 및 메소드이다.
2. **예약어**를 사용하면 안된다.
예) `int , float, String continue, for` 등등..
3. **숫자로 시작**하면 안된다.
4. 최대한 가독성이 높아보이도록 선언한다.
	+ 충분한 목적이 보이도록 선언 : `int s` -> `int sum` -> `int sumNumber`
    + 줄임말 보다는 의미가 확실하게 선언 : `CalcSys` -> `CalculatorSystem`
5. 클래스 이름
	+ `Java`에서 클래스 선언 시 첫 번째 문자는 대문자로 시작한다.
    ```java
    class Cat {}
    public class Program {}
    ```
6. 변수 및 메소드 이름
	+ 첫 단어를 제외한 각 단어의 첫 번째 문자는 대문자로 시작한다.
    ```java
    protected String catName;
    int catAge;
    public void catInfo() {...}
    ```
7. 상수 선언 시
	+ 모든 문자를 대문자로 표기한다.
    ```java
    final static double PI = 3.141592;
    ```
    
## 데이터 타입 💾

**기본 타입**
+ `boolean` : 1 bit
+ `char` : 2 byte
+ `byte` : 1 byte
+ `short` : 2 byte
+ `int` : 4 byte
+ `long` : 8 byte
+ `float` : 4 byte
+ `double` : 8 byte

> ※ **`Java` 에서 문자열인 `String`은 기본 타입**이 아니다.
> `String` 클래스로 존재한다.

**레퍼런스 타입**

+ 배열(Array) 에 대한 레퍼런스
+ 클래스(Class) 에 대한 레퍼런스
+ 인터페이스(Interface) 에 대한 레퍼런스

## `Scanner`

+ `System.in`
	+ 키보드로부터 직접 읽는 자바의 표준 스트림이다.
    + 키 값을 바이트로 리턴한다.
   	+ 키 값을 **바이트 데이터로 넘겨주므로 응용프로그램이 문자 정보로 변환**해야 한다.

+ `Scanner` 클래스
	+ `System.in` 에게 키를 읽게하고 읽은 바이트를 문자, 정수, 실수, bool, 문자열 등 다양한 타입으로 변환하여 리턴해주는 클래스이다.


```java
import java.util.Scanner;	// 라이브러리 Import 필요

Scanner sc = new Scanner(System.in) // Scanner 객체 생성
```

## `Array`

+ **배열(Array)**
  + 인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료구조이다.
  + 배열을 이용하면 한 번에 많은 메모리 공간 할당이 가능하다.
  + 같은 타입의 데이터들이 순차적으로 저장 가능하다.
    + `[1, 2, 3, 4, . . . . 10]`
    + `[1.01, 1.33, 52.11, 33.23]`
        
+ **배열 Index**
  + 다른 언어와 마찬가지로 0에서부터 시작된다.
  + 인덱스는 배열의 시작 위치에서부터 데이터가 있는 상대적 위치이다.

배열의 선언과 생성은 다음과 같다.
```java
/* 배열 선언 */
int numArray []; 
char charArray [];
// 또는
int [] numArray;
char [] charArray;

/* 배열 생성 */
numArray = new int[10];
charArray = new char[10];
// 또는
int numArray[] = new int[10];
char charArray[] = new char[10];
```

생성과 동시에 값 초기화도 가능하다.
```java
int numArray[] = {1,2,3,4,5};
```

### 2차원 배열

![matrix](https://cdn.programiz.com/sites/tutorial2program/files/java-2d-array.jpg)

기존 배열이 1열로 된 공간이라면 2차원 배열은 행과 열로 구성 돼있다.
2차원 배열의 선언은 아래와 같이 가능하다.

```java
// 2차원 배열 선언
int[][] numArray;
char[][] charArray;

// 2차원 배열 생성
numArray = new int[5][5]; // 5x5 행열을 가진 int 배열
charArray = new char[5][5]; // 5x5 행열을 가진 char 배열
```

## 접근 지정자

접근 지정자는 객체 지향의 **캡슐화 , 상속** 등을 지키기 위해 필요한 존재다. 상속으로 클래스 간에 접근 범위를 제어하기 위해서 중요하다. `Java`에는 총 4가지의 접근 지정자가 존재한다.

+ `public`
다른 모든 클래스의 접근을 허용한다.
```java
public class Main { . . . }
```

+ `default`
  + 접근 지정자가 없을 경우 기본값인 지정자이다.
  + 같은 패키지의 클래스만 접근 허용이 가능하다.

+ `protected`
  + 같은 패키지 내의 다른 모든 클래스의 접근 허용이 가능하다. 
  + 상속 받은 서브 클래스는 다른 패키지에 있어도 접근 가능하다.
  
+ `private`
  + 동일 클래스 내에만 접근 허용 가능하다. 상속 받은 서브 클래스에서 접근 불가하다.

| 접근 지정자  | 적용 대상  | 접근 가능   | 접근 불가   |
|-----|-----|-----|-----|
|`public`   |클래스, 멤버 변수, 생성자, 메소드   |모든 대상   | X   |
|`protected`   |멤버 변수, 생성자, 메소드   | 같은 패키지 , 자식 클래스   | 다른 패키지에 속한 클래스  |
|`default`   |클래스, 멤버 변수, 생성자 , 메소드   | 같은 패키지에 속한 클래스   | 다른 패키지에 속한 클래스   |
|`private`   |멤버 변수 , 생성자 , 메소드   | 클래스 내부   | 클래스 외부   |

## 객체 지향 프로그래밍

![Img](https://images.velog.io/images/04_miffy/post/0b3aa976-07d8-4c54-9652-e9c75f492acf/banner-image%20(10).png)

**객체지향 프로그래밍**이란 프로그램을 단순히 데이터와 처리방법으로 나누는 것이 아니라, 프로그램을 수많은 **객체(Object)** 라는 기본 단위로 나누고 이들의 상호작용으로 서술하는 방식이다.

## 객체지향의 요소

### 캡슐화(Encapsulation)

![Capsul](https://xperti.io/blogs/wp-content/uploads/2021/12/xblog-Encapsulation.png)

> _이미지 출처 : xperti.io_


+ **변수와 함수를 하나의 단위**로 묶는 것을 의미한다. 
+ 즉 데이터의 번들링이라고 한다. 
+ 통상적인 프로그래밍 언어에서 이 번들링은 **`Class`** 를 통해 구현되고, 해당 클래스의 인스턴스 생성을 통하여 클래스 안에 포함된 멤버 변수와 메소드에 쉽게 접근할 수 있다.

### 정보 은닉(Information Hiding)

+ 프로그램의 세부 구현을 외부로 드러나지 않도록 특정 모듈 내부로 감추는 것을 의미한다. 
+ 정보 은닉의 가장 **근본적인 목적은 고려되지 않은 영향(Side Effect)들을 최소화** 하는 것이다.
+ 세 가지의 종류로 정보 은닉 표현이 가능하다.
    + `public` : **클래스의 외부에서 사용 가능**하도록 노출.
    + `protected` : **다른 클래스에게는 노출되지 않지만, 상속받은 자식 클래스**에게 노출.
    + `private` : **클래스의 내부에서만 사용 가능**하도록 한다. 외부 노출이 불가능하다.

### 상속(Inheritance)

![](https://velog.velcdn.com/images/jaepal/post/11fe29f7-d56c-4f70-b579-bba91256dba8/image.png)

> _이미지 출처 : PATRICK_

+ 상속은 클래스 내부에 부모(Super) 클래스와 자식(Sub) 클래스가 존재한다.
+ **상속이란 자식 클래스는 부모 클래스의 특성과 기능을 그대로 물려받는 것**을 의미한다.
+ 기능의 일부분을 변경해야 할 경우 서브 클래스에 상속받은 그 기능을 수정하여 다시 정의할 수 있다. 이를 **Overriding** 이라고 한다. 상속은 캡슐화를 유지하면서도 클래스의 재사용이 용이하도록 해준다.

### 다형성(Polymorphism)

하나의 변수, 또는 함수가 상황에 따라 다른 의미로 해석될 수 있는 것을 의미한다.

+ 서브타입 다형성(Subtype polymorphism)

상속받은 상위 클래스의 메소드를 재정의하여 사용할 수 있는 성질이다.
각각의 하위 클래스는 상위 클래스의 메소드 위에 자신의 메소드를 덮어쓰는 **Overridng**을 수행한다. 대부분의 객체지향 언어에서 지원하는 개념이다.

```java
public class Space {
    public void Info() {
        System.out.println("Space.");
    }
}

public class Star extends Space {
    @Override
    public void Info() {
        System.out.println("Star.");
    }
}

public class Main {
    public static void main(String args[]) {
        Star star = new Star();
        star.Info();
    }
}
```

+ 매개변수 다형성(Parametric Polymorphism)

타입을 매개변수로 받아 새로운 타입으로 돌려주는 기능이다.
`Java` 와 `C#` 에서는 제네릭이 있다. 지정한 타입 매개변수에 해당하는 타입만을 사용하겠다고 약속하는 방식이다.

```java
class Box<T> {
    T item;
    void setItem(T item) { this.item = item; }
    T getItem() { return item; }
}
```

+ 임시 다형성(ad hoc Polymorphism)

같은 이름의 **함수를 매개 변수의 개수, 또는 타입을 변경하여 여러 개의 함수가 서로 다르게 행동할 수 있도록 하는 성질**이다. Method Overloading이 이와 같다.

## Class & Object

![](https://velog.velcdn.com/images/jaepal/post/41eaf9b5-6743-4eb2-bc57-80a42cf18ac8/image.png)

> 객체지향의 핵심 3가지 **클래스** , **객체** , ** 인스턴스** 

### 클래스

클래스란, **객체**를 만들어내기 위한 설계도이다. **클래스의 내부에는 객체를 생성하기 위한 변수와 메소드가 집합**해있다. 자동차 설계도를 `Java`로 표현해보겠다.

```java
class Car {
	private String carBrand;
	private int carFuel;
    private int carSpeed;
    
    int speeedDown() {...}
    int speedUp() {...}
    void setCarName() {...}
}
```

`Car` 클래스 내부에는 `기름 , 자동차 속도`가 변수로 존재하며 메소드로는 자동차의 속도를 제어하는 메소드와 자동차 이름을 표시해주는 메소드가 존재한다.

이처럼 **클래스는 객체의 설계도**라 보면 된다.

### 객체

> 사전적 정의 : 세상에 실제로 존재하는 것

**객체(Object)**란 객체 지향 언어에서 클래스에 정의된 내용이 메모리에 생성된 것을 의미한다. 일반적으로 객체는 다수의 속성과 기능을 가지고 있으며 객체가 가지고 있는 속성과 그 기능을 멤버라고 부른다. **클래스에서 실제로 생성된 객체를 인스턴스(Instance)** 라고 칭한다.

클래스를 이용하여 객체를 만드는 방법은 총 두 가지다.
1. 클래스 변수 선언 -> 변수에 `new` 연산자를 사용하여 클래스 생성
2. 클래스 변수의 선언과 동시에 `new` 연산자를 사용하여 클래스 생성

코드로 표현하면,
```java
// 1번 방법
Car myCar;
myCar = new Car();

// 2번 방법
Car myCar = new Car();
```

상단에서 생성한 `Car` 클래스를 메인 메소드에서 표현해보자.
```java
public class Main {
	public static void main(String args[]) {
    	Car audi = new Car();	// Car 클래스의 인스턴스인 audi
        audi.showCarName("Audi");
        
        Car hyundai = new Car(); // Car 클래스의 인스턴스인 hyundai
        hyundai.showCarName("Hyundai");
        
        Car volvo = new Car();	// Car 클래스의 인스턴스인 volvo
        volvo.showCarName("Volvo");
    }
}
```

`Car` 라는 클래스를 통해 객체(인스턴스)를 총 3개를 구현했다.
`audi` , `hyundai` , `volvo` 가 메인 메소드에 존재한다는 뜻이다.

## 생성자(Constructor)

생성자란 `new` 연산자를 통해 **클래스의 인스턴스를 생성할 때 반드시 호출되며 제일 먼저 실행되는 메소드의 일종**이다. 여기서 주의할 점은 생성자가 메소드는 아니다. **생성자는 클래스 내 멤버 변수들을 초기화** 시키는 역할을 해준다.

모든 클래스의 기본 생성자는 이처럼 정의한다.
```java
public Car() {}
public Driver() {}
```

생성자는 기본적으로 리턴 타입이 없으며, 클래스 이름과 같다. 매개 변수는 생략 가능하며 여러 개의 생성자 생성이 가능하다.
```java
class Car {
	public Car(String carBrand, String carName, int carSpeed) {
     . . .
    }
    
    public Car(String carName) {. . .}
    
    public car() {} // 기본 생성자
}
```

### `this`

`this` 는 객체 자신을 의미한다.
`this`는 주로 **생성자와 메소드의 매개변수 이름이 멤버변수와 동일한 경우, 인스턴스 멤버의 멤버 변수(필드)라는 것을 알리고자** 할 때 사용한다. 

또한 멤버 변수와 매개 변수의 이름이 같을 때 생성자 내부에서는 해당 멤버 변수에 접근할 수 없기 때문에 `this` 를 사용한다. 아래 코드로 예시를 확인해보자.
```java
class User {
	String userName; // 멤버변수(필드)
    
    void setUserName(String userName) {
    	this.userName = userName;
        // this.userName => 멤버 변수
        // userName => 매개 변수
    }
}
```

이런 식으로 메소드, 생성자의 참조 변수가 멤버 변수 이름과 같더라도 `this`를 이용하여 같이 사용할 수 있다.

## 메소드(Method)

`C` , `C++` 에서 칭하는 함수를 `Java` 에서는 메소드라고 지칭한다.

```c
// C 언어에서의 함수
void info()
{
	printf("Hello World !");
}
```

```java
// 자바에서의 메소드
public void info()
{
	System.out.println("Hello World !");
}
```

## `main()` 메소드

자바의 프로그램 시작은 `main()` 에서 시작된다.

```java
public class Main {
	public static void main(String args[]) {
    	System.out.println("Hello World !");
    }
}
```
`main()`코드를 하나씩 해석하면,
+ `public` : 접근 제어자를 의미한다. 다른 모든 클래스의 접근을 허용한다.
+ `static` : 정적 메소드임을 의미한다.
+ `void` : 리턴 값이 존재하지 않는 메소드이다.
+ `Stirng args[]` : **`String` 객체로 정의된 args 멤버변수를 전달 받는다**는 것을 의미한다.

`main()`문은 저 규칙을 반드시 지켜야만 한다.

## 예외처리 ⚠️

> 정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성 하는 것
> 목적 : 프로그램의 비정상 종료를 막으며 정상적인 실행상태를 유지하기 위함.

**개발자와 프로그램의 주적은 오류**다. 자바에서는 프로그램 실행 시 발생할 수 있는 오류를 **`에러(Error)`** 와 **`예외(Exception)`** 으로 구분한다.

### `Error` ⛔

대표적인 에러의 예시는 아래와 같다.
+ 메모리가 부족해 하드웨어에 영향을 줄 수 있는 에러 `메모리 부족(OutOfMemoryError)`
+ 고정된 스택 공간이 넘쳐 다른 메모리 영역에 침범하려는 에러 `StackOverFlow`

에러같은 경우에는 **프로세스에 심각한 문제를 일으키기 때문에 프로세스 자체를 종료** 시킬 수 있다. 즉 에러 발생 시 프로세스가 종료가 되어 개발자가 프로그램 내의 에러에 개입할 수 없는 것이다.

### `Exception`

`예외(Exception)`에는 두가지 종류가 존재한다. **일반 예외(Exception)** , **실행 중 예외(Runtime Exception)** 가 존재한다. 예외같은 경우에는 에러와는 달리 사용자의 예기치 못한 조작 , 개발자의 잘못된 방법으로 짠 소스 코드에서 발생하기 때문에 **예외처리**를 통하여 예외 발생 시 프로그램이 종료하지 않도록 할 수 있다.

+ 일반 예외(Exception)
일반 예외는 컴파일 단계에서 발견하는 예외이다. 소스 코드를 컴파일 하는 과정에서 발생하는 예외이다. **컴파일 하는 과정에서 예외 처리 코드가 없을 시 컴파일 오류가 발생**한다.

+ 실행 예외(Runtime Exception)
**실행 예외**는 프로그램 실행 중 발생하는 예외이다. 즉 컴파일러에서 예외 체크를 하지 않으며 온전히 개발자가 예외 발생를 예측하여 코드 작성을 해야한다.

**대표 예외**
+ `NullPointerException`
`Java`에서 코드 작성 시 자주 발생하는 에러이다. 에러 발생 이유는 `null` 값을 가진 객체 참조를 사용할 시 `NullPointerException`이 발생한다.
```java
public class NullPoint {
	public static void main(String args[]) {
    	String str = null; // str 은 현재 값이 null
        System.out.println(str.toString()); /* str에 값이 존재하지 않은데 출력 시 
        									NullPointerException 에러 발생 ! */
    }
}
```

+ `ArrayIndexOutOfBoundsException`
배열의 인덱스 범위를 초과하여 배열을 조작할 경우 발생하는 예외이다.
```java
public class ArrayIndex {
	public static void main(String args[]) {
    	int numArray[] = new int[5]; // 인덱스 5개를 가진 int 형 배열 생성
        for(int i = 0; i <= 5; i++) { // 5 개 초과하여 배열 생성 시 에러 발생
        	numArray[i];
        }
    }
}
```

+ `ClassCastException`
변환할 수 없는 타입으로 객체를 변환할 때 발생하는 예외이다.

+ `IllegalArgumentException`
잘못된 인자를 전달 할 시 발생하는 예외이다.

이 외에도 여러 예외가 존재한다. 이 들을 개발자가 잡고 프로그램이 에러가 발생하여 꺼지는 것을 방지하기 위해선 개발자의 `Java` 언어 숙련도가 중요하다고 생각한다.

이 예외들을 처리하기 위해서 `try` - `catch` - `finally` 예약어를 사용한다.

```java
try{
	예외 발생 가능성이 존재하는 코드들을 작성.
}

catch(예외 처리할 에러 타입 선언) {
	예외 발생 시 출력할 코드들을 작성.
}

finally {
	예외 발생 여부와 상관 없이 실행된다.
   	finally의 작성은 생략 가능하다.
}
```

**try 문 내부에서 예외 발생 시**
+ `catch` 문 실행

**try 문 내부에서 예외 발생이 없을 시**
+ 그대로 `try` 문 내의 코드들이 작동한다.

<br>

# 마치며

자바와 객체 지향의 기초 개념을 정리해봤다. 정리하며 많은 사이트들의 자료를 찾아보고 참고하다보니 다른 언어로의 표현법도 접했는데 `C#`이 `Java`와 상당히 유사한 점을 알았다. 사실 이번 게시글에서 입출력문 , 조건문, 반복문의 사용법도 작성하고 싶었는데 이 게시물에서 작성해봤자 큰 의미가 없다고 생각했다. 😶