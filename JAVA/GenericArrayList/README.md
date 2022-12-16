## JAVA 기초 과제 06

> 제네릭을 이용하여 ArrayList<E>와 비슷한 동작을 하는 MyArrayList<E> 구현

## 구현 항목

1. MyArrayList의 맨 뒤에 Element 추가
2. MyArrayList의 모든 요소 삭제
3. Index의 요소 리턴
4. Index의 요소 삭제

## `GStack.java`

```java
package Generic;

class GStack<T>{
    int tos;
    int length;
    Object [] stack; // 데이터 타입 명시되지 않은 배열

    public int size(){ return length; } // 인덱스 길이 값 반환

    // 객체 생성자
    public GStack(){
        tos = 0;
        length = 1;
        stack = new Object[length];
    }

    // Boolean 형으로, 인덱스에 값 추가
    public boolean add(T value){
        try{
            if(tos == length){
                Object[] d = stack;
                length++;
                stack = new Object[length];
                for(int i = 0; i < d.length; i++){
                    stack[i] = d[i];
                }
            }
            stack[tos] = value;
            tos++;
            return true;
        }
        catch(OutOfMemoryError e){
            return false;
        }
    }

    //  제네릭 타입형으로 특정 인덱스 값 제거.
    public T remove(int index){
        Object ret = stack[index];
        stack[index] = null;
        length--;
        Object[] d = new Object[length];
        int dlength = 0;
        for(int i = 0; i < stack.length; i++){
            if(stack[i] == null)
                i++;
            d[dlength] = stack[i];
            dlength++;
        }
        stack = d;
        tos--;
        return (T) ret;
    }

    // 특정 Index 값 가져오는 메소드
    public T get(int index){
        return (T) stack[index];
    }

    // 배열 요소 전부 초기화하는 메소드
    public void clear(){
        tos = 0;
        length = 1;
        stack = new Object[length];
    }
}
```

## `ArrList.java`

```java
package Generic;
import java.util.*;


public class ArrList {
    public static void main(String args[]) throws Exception { // 강제 예외 처리
        GStack<Integer> MyArraList = new GStack<Integer>();

        // ArrayList의 add() 메소드 구현 부분
        MyArraList.add(1);
        MyArraList.add(3);
        MyArraList.add(5);
        MyArraList.add(7);
        for(int i = 0; i < MyArraList.size(); i++) {
            System.out.println("추가한 인덱스 [" + i + "]  값: "  + MyArraList.get(i));
        }
        System.out.println("arrList의 현재 배열 크기 : " + MyArraList.size());

        // remove() 메소드 구현 부분
        System.out.println("삭제한 인덱스 [0] 값 : " + MyArraList.remove(0));
        System.out.println("삭제한 인덱스 [1] 값 : " + MyArraList.remove(1));
        System.out.println("[1]번 인덱스 값 : "  + MyArraList.get(0));
        System.out.println("현재 배열 크기 : " + MyArraList.size());
        MyArraList.clear();
        System.out.println("초기화 후 배열 크기 : " + MyArraList.size()); // 기본 인덱스 값은 1이여야 함.
    }
}
```
