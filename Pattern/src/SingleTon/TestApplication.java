package SingleTon;
public class TestApplication {
    public static void main(String[] args) {
        System.out.println(":: 싱글톤 테스트 ::");
        SingleTon OneSigleTon = SingleTon.getInstance("Master");
        SingleTon TwoSigleTon = SingleTon.getInstance("Slave");

        System.out.println("첫 번째 객체의 주소 : " + OneSigleTon);
        System.out.println("두 번째 객체의 주소 : " + TwoSigleTon);

        System.out.println("첫 번째 객체의 지정된 이름 : " + OneSigleTon.name);
        System.out.println("두 번째 객체의 지정된 이름 : " + TwoSigleTon.name);
    }
}
