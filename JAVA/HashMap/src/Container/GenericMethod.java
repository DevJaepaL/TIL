package Container;

public class GenericMethod {
    public static <T> GenStack<T> reverse(GenStack<T> a) {
        GenStack<T> s = new GenStack<T>();

        while(true)
        {
            T tmp;
            tmp = a.pop();
            if(tmp == null)
                break;
            else
                s.push(tmp);
        }
        return s;
    }

    public static void main(String args[]) {
        // Double 타입의 GenStack 생성
        GenStack<Double> gs = new GenStack<Double>();
        // 5개의 요소를 스택에 push
        for(int i = 0; i < 5; i++)
            gs.push(new Double(i));
        gs = reverse(gs);
        for (int i = 0; i < 5; i ++)
            System.out.println(gs.pop());
    } 
}
