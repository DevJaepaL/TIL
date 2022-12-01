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
