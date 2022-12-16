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