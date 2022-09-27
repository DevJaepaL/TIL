import java.util.Scanner;

// 메인 메소드(함수)
public class VMachine {
	/* 전역 변수 선언 */
	static Scanner scan = new Scanner(System.in);
	final static int waterCan = 400;
	final static int cokeCan = 500;
	final static int coffeeCan = 600;
	final static int mangoCan = 700;
	final static String drinkName01 = "생수";
	final static String drinkName02 = "콜라";
	final static String drinkName03 = "캔커피";
	final static String drinkName04 = "망고주스";
	static int coin = 0;	 // 금액
	static int tempCoin = 0; // 금액 임시 저장소
	
	public static void main(String args[]) {
		
		System.out.println("[========= * 음료수 자판기 * =========]");
		System.out.println("[ 생수 ] [ 콜라 ] [ 캔커피 ] [ 망고주스 ]");
		System.out.println(" [400]   [500]   [600]   [ 700 ]");
		System.out.println("  [1]     [2]     [3]      [4]");
		System.out.println("[1 - 4] 번 키를 눌러 음료를 선택 해주세요.");
		System.out.println("[=================================]");	
		while(true)
		{
			if(coin == 0) {
				coin = InputCoin();
			}
			
			DrinkSelectMenu(coin);
			coin = tempCoin;		
		}
	}
	
	static void CoinChange(int coin, int drinkPrice, String drink)
	{
		int temp;
		int coin1000, coin500, coin100;
		char deposit;
		
		if(coin < drinkPrice) {
			System.out.println("소지 금액 부족 ! ! !");
			System.out.println("[y] 키를 눌러, 금액을 더 넣어주세요.");
			deposit = scan.next().charAt(0);
			
			if(deposit == 'y')
				InputCoin();
			else
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
			System.out.println("뽑으신 음료수 ==> " + drink);
			System.out.println("거스름돈    ==> " + coin);
			System.out.println("1000원 거스름돈 ==> " + coin1000);
			System.out.println("500원 거스름돈 ==> " + coin500);
			System.out.println("100원 거스름돈 ==> " + coin100);
			System.out.println("=========================");
			tempCoin = coin;
		}
	}
	
	static void DrinkSelectMenu(int coin) {
		int select;
		
		System.out.println("음료를 선택해주세요 ==>");
		System.out.println("[ 생수 ] [ 콜라 ] [ 캔커피 ] [ 망고주스 ]");
		System.out.println(" [400]   [500]   [600]   [ 700 ]");
		System.out.println("  [1]     [2]     [3]      [4]");
		// 아스키코드 값으로 계산하여 입력 처리
		select = (int)(scan.next().charAt(0)) - 48;
		
		if(select < 1 || select > 4) // 숫자 1-4 를 선택 안할 경우
		{
			System.out.println("잘못 선택하셨습니다.");
			DrinkSelectMenu(coin);
		}
		
		else {
			switch(select) // switch문으로 분기 생성
			{
			case 1:
				CoinChange(coin,waterCan,drinkName01);
				break;
			case 2:
				CoinChange(coin,cokeCan,drinkName02);
				break;
			case 3:
				CoinChange(coin,coffeeCan,drinkName03);
				break;
			case 4:
				CoinChange(coin,mangoCan,drinkName04);
				break;
			default:
				System.out.println("다시 입력하세요.");
				break;
			}
		}
	}
	
	static int InputCoin() {
		System.out.println("돈이 없습니다 !");
		System.out.print("돈을 넣어 주세요 >>>");
		coin = scan.nextInt();
		coin += tempCoin; // 거스름돈과 임시변수 값 같게 설정.
		tempCoin = coin;
		return coin;
	}
}