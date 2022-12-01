package Container;
import java.util.ArrayList;

class myArrayList<T> {
    int index;
    int indexLen;
    Object [] array;
    
    public myArrayList() {
        index = 0;
        indexLen = 6;
        array = new Object[indexLen]; // 객체 생성 시 기본 배열크기 : 10
    }

    public void add(T obj) {        
        if(indexLen <= 0) {
            indexLen++;
        }
        
        else {
            array[index] = obj;
            index++;
        }
    }

    public void clear() {
    }

    public T get() {
        if(index == 0)
            return null;
        index--;
        return (T)array[index];
    }
}

public class GenericTest {
    public static void main(String args[]) {       
    
    }
}
