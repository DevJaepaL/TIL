import java.util.Scanner;

public class Puppy {
	/* Puppy �ʵ� */
	String name; // ���� �̸�
	String dogType; // ���� ǰ��
	String sex; // ���� ����
	int age; // ���� ����
	
	void Bark() {
		System.out.println(name + " �� ¢�� �ֽ��ϴ� !");
	}
	
	void Drink() {
		System.out.println(name + " �� ���� ���ð� �ֽ��ϴ� !");
	}
	
	void Eat() {
		System.out.println(name + " �� ���𰡸� �԰� �ֽ��ϴ� !");
	}

	void Sleep() {
		System.out.println(name + " �� �ڰ� �ֽ��ϴ� !");
	}
	
	public Puppy() {} // ���� ������
	
	/* ���� �޼ҵ� */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Puppy babyDog = new Puppy();
		
		System.out.println("===== ������ ���� �Է� =====");
		System.out.print("�������� �̸��� ������ ? ===> ");
		babyDog.name = sc.next();
		System.out.print("�������� ǰ���� ������ ? ===> ");
		babyDog.dogType = sc.next();
		System.out.print("�������� ������ ������ ? ===> ");
		babyDog.sex = sc.next();
		System.out.print("�������� ���̴� ������ ? ===> ");
		babyDog.age = sc.nextInt();
		
		System.out.println("===== ������ ���� Ȯ�� =====");
		System.out.println("�̸� : " + babyDog.name);
		System.out.println("ǰ�� : " + babyDog.dogType);
		System.out.println("���� : " + babyDog.sex);
		System.out.println("���� : " + babyDog.age);
		System.out.println("===========================");
		
		while(true)
		{
			System.out.println("�޴� ����");
			System.out.println("1. ¢�� 2. ���ñ� 3. �Ա� 4. �ڱ�");
			System.out.print("����ּ��� >>> ");
			int selectNum = sc.nextInt();
			switch(selectNum)
			{
			case 1:
				babyDog.Bark();
				break;
			case 2:
				babyDog.Drink();
				break;
			case 3:
				babyDog.Eat();
				break;
			case 4:
				babyDog.Sleep();
				break;
			default:
				System.out.print("�Է� �� ����! �ٽ� �Է��ϼ���.");
				break;
			}
		}
		
//		sc.close();
	}
}