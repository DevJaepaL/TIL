# Java Basics

`Java` 기초 정리

## 객체 지향 프로그래밍

![Img](https://images.velog.io/images/04_miffy/post/0b3aa976-07d8-4c54-9652-e9c75f492acf/banner-image%20(10).png)

객체지향 프로그래밍이란 프로그램을 단순히 데이터와 처리방법으로 나누는 것이 아니라, 프로그램을 수많은 **객체(Object)** 라는 기본 단위로 나누고 이들의 상호작용으로 서술하는 방식이다.

### 요소

**캡슐화(Encapsulation)**

+ **변수와 함수를 하나의 단위**로 묶는 것을 의미한다. 
+ 즉 데이터의 번들링이라고 한다. 
+ 통상적인 프로그래밍 언어에서 이 번들링은 **`Class`** 를 통해 구현되고, 해당 클래스의 인스턴스 생성을 통하여 클래스 안에 포함된 멤버 변수와 메소드에 쉽게 접근할 수 있다.

**정보 은닉(Information Hiding)**

+ 프로그램의 세부 구현을 외부로 드러나지 않도록 특정 모듈 내부로 감추는 것을 의미한다. 
+ 정보 은닉의 가장 **근본적인 목적은 고려되지 않은 영향(Side Effect)들을 최소화** 하는 것이다.
+ 세 가지의 종류로 정보 은닉 표현이 가능하다.
    + `public` : 클래스의 외부에서 사용 가능하도록 노출.
    + `protected` : 다른 클래스에게는 노출되지 않지만, 상속받은 자식 클래스에게 노출.
    + `private` : 클래스의 내부에서만 사용 가능하도록 한다. 외부 노출이 불가하다.

**상속(Inheritance)**

+ 상속은 클래스 내부에 부모(Super) 클래스와 자식(Sub) 클래스가 존재한다.
+ **상속이란 자식 클래스는 부모 클래스의 특성과 기능을 그대로 물려받는 것**을 의미한다.
+ 기능의 일부분을 변경해야 할 경우 서브 클래스에 상속받은 그 기능을 수정하여 다시 정의할 수 있다. 이를 **Overriding** 이라고 한다. 상속은 캡슐화를 유지하면서도 클래스의 재사용이 용이하도록 해준다.

**다형성(Polymorphism)**

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