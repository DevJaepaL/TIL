import java.util.Scanner;

public class Rectangle { // �簢�� class ����
	int width;	// �簢�� �ʺ�
	int height; // �簢�� ����
	String name; // �簢�� �̸�
	
	public int getRecArea() {
		return width * height;
	}
	
	public static void main(String args[]) {
		Rectangle rec = new Rectangle(); // rec ��ü ����
		
		System.out.println(" ====== �簢�� ���� ��� ====== ");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name >>> ");
		rec.name = sc.next();
		System.out.print("Enter Width >>> ");
		rec.width = sc.nextInt();
		System.out.print("Enter Height >>> ");
		rec.height = sc.nextInt();
		
		System.out.println(" ====== ��� ��� ====== ");
		System.out.println("�簢�� �̸� : " + rec.name);
		System.out.println("�簢�� �ʺ� : " + rec.width);
		System.out.println("�簢�� �ʺ� : " + rec.height);
		System.out.println("�簢�� ���� : " + rec.getRecArea());
		
		sc.close();
	}
}