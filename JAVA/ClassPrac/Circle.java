/* Circle(��) class ���� */
public class Circle {
	public int radius; // ���� ������ �ʵ�(���)
	public String name; // ���� �̸� �ʵ�(���)
	
	public Circle() { // ���� ������ �޼ҵ�
	}
	
	public double getArea() { // ���� ���� ��� �޼ҵ�
		return 3.14 * radius * radius;
	}
	
	public static void main(String[] args) {
		
		Circle pizza;
		pizza = new Circle();	// Circle ��ü ���� 1
		pizza.radius = 10;		// ������ �� ������ 10
		pizza.name = "�ڹ�����";	// ������ �̸� ����
		double area = pizza.getArea(); // ������ ���� �˾Ƴ���
		System.out.println(pizza.name + "�� ������ " + area + " �Դϴ�.");
		
		Circle donut;
		donut = new Circle();	// Circle ��ü ���� 2
		donut.radius = 2;
		donut.name= "�ڹٵ���";
		area = donut.getArea();
		System.out.println(donut.name + "�� ������ " + area + " �Դϴ�.");
	}
}