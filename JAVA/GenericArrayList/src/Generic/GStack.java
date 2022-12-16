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
