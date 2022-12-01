## `HashMap`

+ 키(Key) 와 값(Value)의 쌍으로 구성되는 요소를 다루는 컬렉션이다.
    + `java.util.HashMap`
    + K는 key로 사용할 요소의 타입, V는 값으로 사용할 요소의 타입 지정
    + 키와 값이 한 쌍으로 삽입
    + 키는 해시맵에 삽입되는 위치 결정에 사용한다.
    + 값을 검색하기 위해선 반드시 키를 사용한다.

+ **삽입, 삭제, 검색**이 빠른 특징
    + 요소 삽입 : `put()` 메소드
    + 요소 검색 : `get()` 메소드 

## `LinkedList<E>`

+ `java.util.LinkedList`
    + E 에 요소로 사용할 타입 지정하여 구체화
+ List 인터페이스를 구현한 컬렉션 클래스
+ `Vector`, `ArrayList` 클래스와 매우 유사하게 작동
+ 요소 객체들은 양방향으로 연결되어 관리된다.
+ 요소 객체는 맨 앞, 맨 뒤에 추가 가능
+ 요소 객체는 **인덱스를 이용하여 중간에 삽입** 가능하다.
+ 맨 앞이나 맨 뒤에 **요소를 추가하거나 삭제할 수 있어 스택이나 큐**로 사용가능하다.

### 예시 코드

```java
package Container;
import java.util.*;

public class HashMapScore {
    public static void main(String args[]) {
        // 사용자 이름 , 점수 기록 HashMap 컬렉션
        HashMap<String, Integer> javaScore = 
            new HashMap<String, Integer>();
        
        javaScore.put("Kim", 100);
        javaScore.put("Lee", 88);
        javaScore.put("Park", 90);
        javaScore.put("Ryu", 75);
        javaScore.put("Hyeon", 62);
        javaScore.put("Min", 88);

        System.out.println("HashMap의 요소 개수 : " + javaScore.size());

        Set<String> keys = javaScore.keySet();
        Iterator<String> it = keys.iterator();

        while(it.hasNext())
        {
            String name = it.next();
            int score = javaScore.get(name);
            System.out.println(name + " : " + score);
        }
    }
}
```

## `Collection`

+ `java.util` 패키지에 포함된다.
+ 컬렉션에 대해 연산을 수행하고 결과로 컬렉션 리턴.
+ 모든 메소드는 `static` 타입이다.
    + 즉, `Collection` **객체를 새로 생성 안해도 된다는 뜻**!
+ 주요 메소드
    + `Collection`에 포함된 요소들을 Sort하는 `sort()` 메소드
    + 요소의 순서를 반대로하는 `reverse()` 메소드
    + 요소들의 최대, 최솟값을 찾아내는 `max()` , `min()` 메소드
    + 특정 값을 검색하는 `binarySearch()` 메소드

### 예시 코드

```java
package Container;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionsTest {
    static void printList(LinkedList<String> I) {
        Iterator<String> iterator = I.iterator();
        while(iterator.hasNext())
        {
            String e = iterator.next();
            String separator;
            if (iterator.hasNext())
                separator = " -> ";
            else
                separator = "\n";
            System.out.print(e + separator);
        }
    }

    public static void main(String[] args) {
        LinkedList<String> movieList = new LinkedList<String>();
        movieList.add(" Lord of the Rings ");
        movieList.add(" Begin Again ");
        movieList.add(" Hobbits ");
        movieList.add(" Star Wars ");
        movieList.add(" Parasite ");
        
        // 정렬 하기 전 movieList
        System.out.print("정렬 전 : ");
        printList(movieList);

        // 정렬 movieList
        System.out.print("정렬 후 : ");
        Collections.sort(movieList);
        printList(movieList);

        // 요소 순서 반대 정렬
        System.out.print("반대 정렬 : ");
        Collections.reverse(movieList);
        printList(movieList);

        int index = Collections.binarySearch(movieList, "Begin Again");
        System.out.println("Begin Again , " + index + " 번 째 요소입니다.");
    }
}
```
**결과**
```
정렬 전 :  Lord of the Rings  ->  Begin Again  ->  Hobbits  ->  Star Wars  ->  Parasite 
정렬 후 :  Begin Again  ->  Hobbits  ->  Lord of the Rings  ->  Parasite  ->  Star Wars        
반대 정렬 :  Star Wars  ->  Parasite  ->  Lord of the Rings  ->  Hobbits  ->  Begin Again      
Begin Again , -6 번 째 요소입니다.
```

## 제네릭 클래스

+ 제네릭 클래스와 인터페이스
    + 클래스나 인터페이스 선언부에 일반화된 타입 추가

```java
public class MyClass<T> { // 제네릭 클래스 MyClass 선언 , 타입 매개 변수 'T'
    T val;  # val 변수 타입 = T

    void set(T a) {
        val = a;    // T 타입의 값 a을 val에 지정
    }

    void get() {
        return val; // T 타입의 값 val 리턴
    }
}
```
+ 제네릭 클래스 레퍼런스 변수 선언
```java
MyClass<String> myString;
List<Integer> list;
Vector<String> vectorString;
```

### 구체화
+ 제네릭 타입의 클래스에 구체적인 타입을 대입하여 객체 생성
+ 컴파일러에 의해 이루어진다.

```java
MyClass<String> s = new MyClass<String>();
s.set("hello");
System.out.println(s.get());    // "hello" 출력.

MyClass<Integer> n = new MyClass<Integer>();
n.set(5);
System.out.println(n.get());    // 숫자 5 출력.
```

### 구체화 오류

**타입 매개 변수에 기본 타입**은 사용할 수 없다.

맞지 않는 예
```java
Vector<int> vi = new Vector<int>(); // int형 사용 불가
```
올바른 예
```java
Vector<Integer> vi = new Vector<Integer>();
```
## 타입 매개 변수

+ `<` 과 `>` 사이에 하나의 대문자를 타입 매개변수로 사용하는 것을 의미한다.
+ 통상적으로 사용하는 타입 매개 변수 문자
    + E : Element를 의미하며 컬렉션에서 요소를 표시할 때 많이 사용한다.
    + T : Type을 의미한다.
    + V : Value를 의미한다.
    + K : Key를 의미한다.
+ 타입 매개변수가 나타내는 타입의 객체 생성은 불가하다.
    + 예) T a = new T();
+ 어떤 문자도 매개 변수로 사용 가능하다.

### 제네릭으로 `Stack` 구현

```java
class GenStack<T> {
    int tos;
    Object [] stack;
    
    public GenStack() {     // 생성자 생성 시 10개의 배열
        tos = 0;
        stack = new Object [10];
    }
    
    public void push(T item) {
        if(tos == 10)
            return;
        stack[tos] = item;
        tos++;
    }

    public T pop() {
        if(tos == 0)
            return null;
        
        tos--;
        return (T)stack[tos];
    }
}
```

