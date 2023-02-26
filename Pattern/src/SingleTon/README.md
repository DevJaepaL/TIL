# 싱글톤 패턴

> 💡`싱글톤`이란 **클래스에 인스턴스가 하나만 존재**하도록 하며, 이 **인스턴스에 대한 전역 접근을 제공**해주는 패턴이다.

싱글톤의 특징

+ 디자인 패턴의 종류 중 **생성 패턴**에 해당한다.
+ 프로그램에서 인스턴스가 하나만 생성된다.
+ 프로그램 전역에서 또한 하나의 인스턴스로 사용한다.

![](https://velog.velcdn.com/images/jaepal/post/9176552b-602d-4753-95a9-92590173c2f6/image.png)

## ⚠️ 싱글톤의 장단점

✅ **간단하고 단순한 구현이 가능**하다.
✅ 싱글톤을 사용하는 클래스가 **하나의 인스턴스만 갖는다는 것을 확신**할 수 있다.
✅ 싱글톤의 객체는 처음 요청시에만 초기화 된다.
✅ 정적(고정)인 메모리를 사용하기 때문에 **메모리 낭비가 줄어든다**.

❌ **단일 책임 원칙**을 위반한다.
❌ 다중 스레드 환경에서는 싱글톤의 생성이 여러번 될 수 있기에 처리가 필요하다.
❌ 프로그램 내부의 컴포넌트들이 서로간 개입이 강해질 수 있어 추후 유지보수가 힘들 수 있다.

>※ **단일 책임 원칙** : 객체는 단 하나의 책임(기능 담당)만을 가져야 한다.

## 구현

싱글톤은 다음과 같은 상황에 적용하면 좋다고 한다.

💡 **전역 변수들을 더욱 엄격하게 제어해야 하는 상황**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;➡️ _전역 변수와는 다르게 싱글톤은 해당 클래스의 인스턴스가 하나만 있도록 보장해 준다._
    
💡 **모든 클라이언트가 사용할 수 있는 단일 인터페이스가 필요할 경우에 사용한다.**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;➡️예) _`단일 DB 객체` 같은 존재 , 사용 빈도가 높지만 생성 시 값이 바뀌면 안되는 객체 등.._
   
___

☕ `JAVA` 에서의 구현

`SingleTon.java`

```java
public final class SingleTon {

    private static SingleTon instance;
    public String name;

    /* 생성자를 숨겨 외부에서 객체를 생성할 수 없도록 제약을 걸어준다. */
    private SingleTon(String name) {
        this.name = name;
    }

    /* 정적 객체 생성 메소드 구현한다.
     * 생성된 객체가 없을(null) 경우 새로운 객체를 만들어준다.
     * 그 외에 경우, 객체 자체를 그대로 반환 해준다.
     */
    public static SingleTon getInstance(String name) {
        if(instance == null) {
            instance = new SingleTon(name);
        }

        return instance;
    }
}
```

❗객체 생성자를 숨겨야한다.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;➡️ `private`으로 외부에서 **접근하지 못하도록** 해준다.

✅ 외부에서는 접근 허용된 메소드만 접근 가능하다.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;➡️ 외부에서는 `getInstance()` 메소드만 접근 가능하며, **객체가 생성되지 않았다면 새로 생성해주고 이미 할당 돼있는 경우에는 객체 그대로 반환된다.**

### ⚠️ 멀티스레드 환경에서의 주의 사항

> 만약 싱글톤을 적용한 프로그램의 실행 환경이 멀티 스레딩 환경이라면 
**객체가 두 번 이상 생성될 수 있는 가능성이 있다.** 

이 문제를 동시성 문제라고 하는데 이 문제 해결을 위해 메소드에 `synchronized` 키워드를 사용해주면 해결된다.

```java
public static synchronized SingleTon getInstance(String name) {
        if(instance == null) {
            instance = new SingleTon(name);
        }

        return instance;
    }
```

## 테스트

`TestApplication.java`

```java
public class TestApplication {
    public static void main(String[] args) {
        System.out.println(":: 싱글톤 테스트 ::");
        /* 첫 번째 객체와 두 번째 겍체 모두 생성하여 테스트 */
        SingleTon OneSigleTon = SingleTon.getInstance("Master");
        SingleTon TwoSigleTon = SingleTon.getInstance("Slave");

        System.out.println("첫 번째 객체의 주소 : " + OneSigleTon);
        System.out.println("두 번째 객체의 주소 : " + TwoSigleTon);

        System.out.println("첫 번째 객체의 지정된 이름 : " + OneSigleTon.name);
        System.out.println("두 번째 객체의 지정된 이름 : " + TwoSigleTon.name);
    }
}
```

**테스트 결과**

```
>>>
	:: 싱글톤 테스트 ::
	첫 번째 객체의 주소 : SingleTon@19e0bfd
	두 번째 객체의 주소 : SingleTon@19e0bfd
    첫 번째 객체의 지정된 이름 : Master
    두 번째 객체의 지정된 이름 : Master
```

결과에서 확인할 수 있는 점은 **두 개의 객체를 각자 다르게 생성해도  첫 번째 객체 값만 생성 됨**을 알 수 있다.

___

# 마치며

디자인 패턴의 종류중 하나인 싱글톤 패턴을 공부해봤다.
작년 수업에서 들었을 때는 무슨 소린가? 싶었는데 시간이 흐르고 다시 제대로 이해하고자 공부하니 전보다 이해가 잘 됐다. 

싱글톤 패턴을 활용하면 메모리 관리가 확실히 쉬울 듯 하다.
프로그램 전역에서 계속 사용하더라도 어차피 객체는 하나만 생성되고 이 말인 즉슨 프로그램이 실행될 때는 하나의 메모리만 사용된다는 뜻이기 때문이다.

<br>

> 💬 참고 자료
+ [Refactioring Guru](https://refactoring.guru/ko)
+ [나무 위키 - 디자인 패턴](https://namu.wiki/w/%EB%94%94%EC%9E%90%EC%9D%B8%20%ED%8C%A8%ED%84%B4#s-4.3)
