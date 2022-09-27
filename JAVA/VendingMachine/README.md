## Java로 음료수 자판기 코딩 🌠

  > 클래스 파일을 여러개 쪼개서 만들지 않고 java 파일 하나를 생성하여 코드 작성을 했음.

```Java

// 예외처리를 위한 모듈 로드
import java.util.InputMismatchException;
import java.util.Scanner;

// VMachine 클래스
public class VMachine {
	/******** 전역 변수 ********/
	static Scanner scan = new Scanner(System.in);
	
	/* 음료 가격 변수(상수 변수) */
	final static int waterCan = 400;
	final static int cokeCan = 500;
	final static int coffeeCan = 600;
	final static int mangoCan = 700;
	/* 음료 이름 변수(상수 변수) */
	final static String drinkName01 = "생수";
	final static String drinkName02 = "콜라";
	final static String drinkName03 = "캔커피";
	final static String drinkName04 = "망고주스";
	static int coin = 0;	 // 금액 변수
	static int tempCoin = 0; // 거스름돈 계산을 위한 임시 변수 선언
	
	// 거스름돈 반환 및 소지금액 부족시 호출되는 기능을 갖는 메소드
	static void CoinChange(int coin, int drinkPrice, String drink)
	{
		int temp; // 임시 변수
		int coin1000, coin500, coin100; // 거스름돈 저장 하는 변수
		char deposit; // 특정 키 입력 값을 받아 정상적으로 기능이 실행될 수 있게 해주는 변수
		
		// 현재 갖고있는 금액이 음료값보다 부족한 경우의 분기다.
		if(coin < drinkPrice) {
			System.out.println("소지 금액 부족 ! ! !");
			System.out.println("[x] 키를 눌러, 금액을 충전합니다.");
			System.out.println("충전 = [x] 메인 메뉴 = [x를 제외한 모든 키");
			System.out.print("입력 >>>>>");
			deposit = scan.next().charAt(0);
			
			if(deposit == 'x')
				InputCoin();

			else // x 키 이외의 모든 값 입력 시.
				DrinkSelectMenu(coin);
			}
		
		else { // 거스름돈 로직
			coin -= drinkPrice;
			coin1000 = coin / 1000; // 1000원 계산
			temp = coin % 1000;
			coin500 = temp / 500; // 500원 계산
			temp = temp % 500;
			coin100 = temp / 100; // 100원 계산
			temp = temp % 100;
		
			System.out.println();
			System.out.println("==========================");
			System.out.println("뽑으신 음료수 ==>  \""+ drink + "\"");
			System.out.println("1000원 거스름돈 ==>  " + coin1000 + "개");
			System.out.println("500원 거스름돈 ==>  " + coin500 + "개");
			System.out.println("100원 거스름돈 ==>  " + coin100 + "개");
			System.out.println("현재 금액   ==>  \""+ coin + "\"");
			System.out.println("=========================");
			tempCoin = coin;
		}
	}
	
	// 음료 선택 메뉴 메소드
	static void DrinkSelectMenu(int coin) {
		int select;
		System.out.println("[===================================]");	
		System.out.println("[ 생수 ] [ 콜라 ] [ 캔커피 ] [ 망고주스 ]");
		System.out.println(" [400]   [500]   [600]   [ 700 ]");
		System.out.println("  [1]     [2]     [3]      [4]");
		System.out.println("현재 보유 금액 ==>  \""+ coin + "\" 원");
		System.out.print("음료를 선택해주세요 ==> ");
		
		// 아스키코드 값으로 계산하여 입력 처리
		select = (int)(scan.next().charAt(0)) - 48;
		
		// 숫자 "1~4" 를 제외한 모든 입력값을 받을 경우 경고 표시 후, 음료 선택 함수 호출.
		if(select < 1 || select > 4)
		{
			System.out.println("잘못 선택하셨습니다. 숫자 (1 ~ 4) 를 입력하세요.");
			DrinkSelectMenu(coin);
		}
		
		else {
			switch(select) // switch문으로 분기 생성
			{
			case 1:
				CoinChange(coin,waterCan,drinkName01); // 생수
				break;
			case 2:
				CoinChange(coin,cokeCan,drinkName02); // 콜라
				break;
			case 3:
				CoinChange(coin,coffeeCan,drinkName03); // 캔커피
				break;
			case 4:
				CoinChange(coin,mangoCan,drinkName04); // 망고주스
				break;
			default:
				System.out.println("입력 값 오류 ! ! 다시 입력하세요."); // 1~4 입력값을 제외한 모든 값 입력받을 시 문구 출력
				break;
			}
		}
	}
	
	// 금액 충전 메소드
	static int InputCoin() {
		System.out.println("** 돈이 없어 금액을 충전 합니다. **");
		System.out.print("충전할 금액을 입력 하세요. >>> ");
		// 입력 값 숫자외에 문자 입력값 에러 예외처리 
		while(true) {
			try {
				coin = scan.nextInt();	
				break;
			}
			catch(InputMismatchException e) {
			System.out.println("입력이 잘못 됐습니다.");
			System.out.print("충전할 금액을 입력 하세요 >>> ");
			scan.nextLine();
			}
		}
				
		coin += tempCoin; // 거스름돈과 임시변수 값 같게 설정.
		tempCoin = coin;
		return coin; // 돈 반환.
	}

	// 메인 메소드
	public static void main(String args[]) {	
		// 자판기 인터페이스
		System.out.println("[========= * 음료수 자판기 * =========]");
		System.out.println("[ 생수 ] [ 콜라 ] [ 캔커피 ] [ 망고주스 ]");
		System.out.println(" [400]   [500]   [600]   [ 700 ]");
		System.out.println("  [1]     [2]     [3]      [4]");
		System.out.println("[1 - 4] 번 키를 눌러 음료를 선택 해주세요.");
		System.out.println("         현재 금액 : " + coin +  " 원");
		System.out.println("[===================================]");	
		while(true)
		{
			if(coin == 0) {
				coin = InputCoin();
			}
			
			DrinkSelectMenu(coin);
			coin = tempCoin;		
		}
	}
}
```
