import java.util.Scanner;

public class Rectangle { // 사각형 class 생성
	int width;	// 사각형 너비
	int height; // 사각형 높이
	String name; // 사각형 이름
	
	public int getRecArea() {
		return width * height;
	}
	
	public static void main(String args[]) {
		Rectangle rec = new Rectangle(); // rec 객체 생성
		
		System.out.println(" ====== 사각형 면적 계산 ====== ");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name >>> ");
		rec.name = sc.next();
		System.out.print("Enter Width >>> ");
		rec.width = sc.nextInt();
		System.out.print("Enter Height >>> ");
		rec.height = sc.nextInt();
		
		System.out.println(" ====== 계산 결과 ====== ");
		System.out.println("사각형 이름 : " + rec.name);
		System.out.println("사각형 너비 : " + rec.width);
		System.out.println("사각형 너비 : " + rec.height);
		System.out.println("사각형 면적 : " + rec.getRecArea());
		
		sc.close();
	}
}