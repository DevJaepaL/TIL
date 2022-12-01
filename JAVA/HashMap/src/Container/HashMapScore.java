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
