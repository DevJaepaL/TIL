package Container;
import java.util.*;

public class HashMapTest {
    public static void main(String args[]) {
        HashMap<String, String> dic = new HashMap<String, String>();

        dic.put("baby", "아기");
        dic.put("love", "사랑");
        dic.put("dog", "강아지");

        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.print("찾고 싶은 단어 : ");
            String eng = sc.nextLine();
            if(eng.equals("exit")) {
                System.out.println("프로그램 종료 !");
                break;
            }

            String kor = dic.get(eng);
            if(kor == null)
                System.out.println(eng + "는 없는 단어에요.");
            else
                System.out.println(kor);
        }

        sc.close();
    }
}