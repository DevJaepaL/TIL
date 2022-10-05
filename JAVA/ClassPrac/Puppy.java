import java.util.Scanner;

public class Puppy {
	/* Puppy 필드 */
	String name; // 개의 이름
	String dogType; // 개의 품종
	String sex; // 개의 성별
	int age; // 개의 나이
	
	void Bark() {
		System.out.println(name + " 가 짖고 있습니다 !");
	}
	
	void Drink() {
		System.out.println(name + " 가 물을 마시고 있습니다 !");
	}
	
	void Eat() {
		System.out.println(name + " 가 무언가를 먹고 있습니다 !");
	}

	void Sleep() {
		System.out.println(name + " 가 자고 있습니다 !");
	}
	
	public Puppy() {} // 개의 생성자
	
	/* 메인 메소드 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Puppy babyDog = new Puppy();
		
		System.out.println("===== 강아지 정보 입력 =====");
		System.out.print("강아지의 이름은 뭐에요 ? ===> ");
		babyDog.name = sc.next();
		System.out.print("강아지의 품종은 뭐에요 ? ===> ");
		babyDog.dogType = sc.next();
		System.out.print("강아지의 성별은 뭐에요 ? ===> ");
		babyDog.sex = sc.next();
		System.out.print("강아지의 나이는 뭐에요 ? ===> ");
		babyDog.age = sc.nextInt();
		
		System.out.println("===== 강아지 정보 확인 =====");
		System.out.println("이름 : " + babyDog.name);
		System.out.println("품종 : " + babyDog.dogType);
		System.out.println("성별 : " + babyDog.sex);
		System.out.println("나이 : " + babyDog.age);
		System.out.println("===========================");
		
		while(true)
		{
			System.out.println("메뉴 선택");
			System.out.println("1. 짖기 2. 마시기 3. 먹기 4. 자기");
			System.out.print("골라주세요 >>> ");
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
				System.out.print("입력 값 오류! 다시 입력하세요.");
				break;
			}
		}
		
//		sc.close();
	}
}