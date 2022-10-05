/* Circle(원) class 생성 */
public class Circle {
	public int radius; // 원의 반지름 필드(멤버)
	public String name; // 원의 이름 필드(멤버)
	
	public Circle() { // 원의 생성자 메소드
	}
	
	public double getArea() { // 원의 면적 계산 메소드
		return 3.14 * radius * radius;
	}
	
	public static void main(String[] args) {
		
		Circle pizza;
		pizza = new Circle();	// Circle 객체 생성 1
		pizza.radius = 10;		// 피자의 원 반지름 10
		pizza.name = "자바피자";	// 피자의 이름 설정
		double area = pizza.getArea(); // 피자의 면적 알아내기
		System.out.println(pizza.name + "의 면적은 " + area + " 입니다.");
		
		Circle donut;
		donut = new Circle();	// Circle 객체 생성 2
		donut.radius = 2;
		donut.name= "자바도넛";
		area = donut.getArea();
		System.out.println(donut.name + "의 면적은 " + area + " 입니다.");
	}
}