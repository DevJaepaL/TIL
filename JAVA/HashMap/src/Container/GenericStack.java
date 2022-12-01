package Container;

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

public class GenericStack {
    public static void main(String args[]) {
        GenStack<String> stringLocation = new GenStack<String>();
        stringLocation.push("Seoul1");
        stringLocation.push("Busan2");
        stringLocation.push("Tokyo3");
        stringLocation.push("Seoul4");
        stringLocation.push("Busan5");
        stringLocation.push("Tokyo6");
        stringLocation.push("Seoul7");
        stringLocation.push("Busan8");

        System.out.println(":: Push -> Pop Result ::");
        for(int n = 0; n < 10; n++)
            System.out.println(n + " 번째 값 : " + stringLocation.pop());

        GenStack<Integer> intStack = new GenStack<Integer>();
        intStack.push(1);
        intStack.push(3);
        intStack.push(5);

        System.out.println("\n:: Push -> Pop Result ::");
        for(int n = 0; n < 3; n++)
            System.out.println(intStack.pop());
    }
}
