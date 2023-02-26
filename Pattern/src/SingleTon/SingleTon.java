package SingleTon;
public final class SingleTon {

    private static SingleTon instance;
    public String name;

    /* 생성자를 숨겨 외부에서 객체를 생성할 수 없도록 제약을 걸어준다. */
    private SingleTon(String name) {
        this.name = name;
    }

    /* 정적 객체 생성 메소드 구현한다.
     * 생성된 객체가 없을(null) 경우 새로운 객체를 만들어준다.
     * 그 외에 경우, 객체 자체를 그대로 반환 해준다.
     * 주의점 : 멀티 쓰레드 환경에서는 메소드에 'synchronized' 예약어 사용 필요!
     */
    public static SingleTon getInstance(String name) {
        if(instance == null) {
            instance = new SingleTon(name);
        }

        return instance;
    }
}
