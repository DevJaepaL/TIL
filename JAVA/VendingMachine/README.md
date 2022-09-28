## 개요 ✔
> 길거리에서 접할 수 있는 음료 판매기를 객체지향 언어인 "JAVA" 를 이용하여
직접 프로그램으로 구현한다. 
여기서 핵심은 "예외처리" 기능을 통해 프로그램이 꺼지지 않고 계속 돌도록 구현한다.


<br>

## 객체 추상화 과정 및 순서도 🔍

코드를 짜기 전 추상화 과정을 거쳐야 한다. 먼저 `JAVA`는 **객체 지향 언어**이기 때문에
나 같은 경우엔 지금까지 `C` 를 많이 사용해서 **절차 지향** 과 **객체 지향**의 코드 구현 방식의 차이를 알아보려 했다.

### 객체지향 프로그래밍 📚
>  우리가 실생활에서 쓰는 모든 것을 객체라 하며, 객체 지향 프로그래밍은 프로그램 구현에 필요한 객체를 파악하고 각각의 객체들의 역할이 무엇인지를 정의하여 객체들 간의 상호작용을 통해 프로그램을 만드는 것을 말한다.

객체 지향 프로그래밍 언어의 특징이라고 한다.
내가 이해한게 맞다면, 실생활의 음료 자판기에 있는 기능들이 객체이고 이 객체끼리의 상호작용을 통해 음료자판기가 코드로 구현이 된다는 느낌이 아닐까 싶다.

먼저 나는, 추상화 및 순서도를 구현해 보았다.

![](https://velog.velcdn.com/images/jaepal/post/1b2766a2-be3e-42ac-916f-6451e95a4361/image.PNG)

프로그램이 실행 됐을 때 꺼지지 않고 계속 실행되게 할려면 초기 금액의 한도를 정하지 않고
돈이 부족해지면(0원 & 음료수보다 갖고 있는 금액이 적을 때) 원하는 금액만큼 충전 될 수 있도록 구현했다.

### 구현 한 기능들
> 1. 자판기 메인 메뉴 기능
  2. 금액 충전(현금 투입) 기능
  3. 거스름돈 정산 기능

제일 핵심 3가지의 기능만 짚어보았다.
물론 제일 중요한 **예외처리**를 통해 사용자가 입력한 값이 잘못 돼도 문제 없이 돌아가도록 구현한 부분도 있다.

## 소스 코드


### 모듈 로드 및 전역 변수 선언

```java
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
```

첫번 째 줄인 `java.util.InputMismatchException;` 는 예외처리를 하기 위해서 필요한 라이브러리 이다. 나는 음료수들을 배열이 아닌 값이 변하지 않는 변수로 선언하였다. 

<br>

### 거스름 돈 반환 메소드
```java
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
			System.out.println("충전[x] | 메인 메뉴[x를 제외한 모든 키]");
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
```

이 쪽은 거스름돈 반환해주는 메소드이다.
여기서 핵심은,
```java
if(deposit == 'x')
				InputCoin();

			else // x 키 이외의 모든 값 입력 시.
				DrinkSelectMenu(coin);
			}
```

이 쪽이다. `X` 키를 제외한 모든 키를 입력했을 때 프로그램이 꺼지지 않고
추후 설명할 메소드인 ` DrinkSelectMenu(coin)` 가 호출 되도록 하였다.

또한 거스름돈을 계산할 수 있게 임시변수를 선언하여
```java
			coin -= drinkPrice;
			coin1000 = coin / 1000; // 1000원 계산
			temp = coin % 1000;
			coin500 = temp / 500; // 500원 계산
			temp = temp % 500;
			coin100 = temp / 100; // 100원 계산
			temp = temp % 100;
```
값을 계속 나눠주고 나머지값을 임시변수에 반환하여 거스름돈이 얼마나 남았는지
`1000,500,100`원 단위로 나누어서 콘솔에 나오도록 하였다.

### 음료 선택 메뉴 메소드

제일 핵심인 기능이다.
음료를 선택하고 계산하여 음료 자판기의 기능의 전반적인 부분을 담당한다.

```java
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
```

나는 여기서 입력 값을 문자열로 받아서 처리하는 것이 아닌 숫자(`1~4`)로 선택하여 했다.
간단하고 직관적으로 만드는 것이 핵심이라 생각하여 일일이 `콜라`,`생수` 이렇게 치기엔 숫자를 입력하는 거보다 어려운 일이라 생각했다.

여기서 입력 값이 잘못되어 프로그램이 꺼지지 않게
```java
		// 숫자 "1~4" 를 제외한 모든 입력값을 받을 경우 경고 표시 후, 음료 선택 함수 호출.
		if(select < 1 || select > 4)
		{
			System.out.println("잘못 선택하셨습니다. 숫자 (1 ~ 4) 를 입력하세요.");
			DrinkSelectMenu(coin);
		}
```

이런 식으로 숫자 `1~4` 를 제외한 모든 입력 값은 `DrinkSelectMenu()` 함수가 호출 될 수 있게 처리했다.

### 금액 충전 메소드
프로그램을 구현 할 때의 목표가 프로그램이 꺼지지 않고 계속 돌 수 있게 하려면
음료 자판기를 사용할 때 코드를 짤 때 사용자의 초기 금액 한도를 정해두면 프로그램이 꺼질 수 있다 생각해서 금액 충전 기능을 넣었다.
```java
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
```
여기서 **예외 처리** 기능이 사용됐다.
```java
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
```
일단 금액 충전 메소드가 호출 되면 while 문을 통해, `try`문을 통해 입력 값이 정상 적이면
반복문을 탈출하여
```java
		coin += tempCoin; // 거스름돈과 임시변수 값 같게 설정.
		tempCoin = coin;
		return coin; // 돈 반환.
```
해당 코드가 정상적으로 실행되게 했고
만약 입력 값이 int형이 아닌 비정상적인 값이 들어오게 될 수 있으니
```java
			catch(InputMismatchException e) {
			System.out.println("입력이 잘못 됐습니다.");
			System.out.print("충전할 금액을 입력 하세요 >>> ");
			scan.nextLine();
			}
		}
```
이런 식으로 입력 값이 잘못 됐다고 알림을 해주고 다시 한번 충전 금액을 입력할 수 있게
했다.

### 메인 메소드

디버깅이 되면 실행되는 메소드이다.

```java
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

무한 반복을 통해 메소드가 계속 호출되고,
현재 보유 금액이 `0`일 경우 `InputCoin()` 함수가 호출 되도록 했다.

## 전체 소스 코드

[GitHub](https://github.com/DevJaepaL/TIL/blob/main/JAVA/VendingMachine/src/VMachine.java)

```java
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

## 결과

![](https://velog.velcdn.com/images/jaepal/post/83a73a05-9c6f-45ee-91ef-2a84e2e9f572/image.PNG)

코드 실행시 바로 나오는 창이다.
가독성이 떨어지는 감이 있다.

![](https://velog.velcdn.com/images/jaepal/post/eeb45228-a5d0-4dcb-8124-1666948ac980/image.PNG)
먼저 `1000원`을 충전하고 `2`번인 `콜라`를 선택했다.

![](https://velog.velcdn.com/images/jaepal/post/9749dd28-46e3-4f49-94a1-de248d15295f/image.PNG)

![](https://velog.velcdn.com/images/jaepal/post/bf06afac-9d3c-4d6a-8901-f5b03835917c/image.PNG)

여기서 비정상적인 입력시 나오는 화면이다.
음료 선택시 `1~4`번이 아닌 숫자(5) 와 한글을 입력했을 때의 결과이다.
프로그램이 꺼지지 않고 계속 돌아간다.

![](https://velog.velcdn.com/images/jaepal/post/3a72550a-13f7-45f4-a729-da5762812d4e/image.PNG)

또한 음료수 금액보다 현재 보유 금액이 적을 경우 
**금액 충전** 또는 **메인 메뉴**로 돌아갈 수 있게 분기를 정했다.

#### 정상 실행 시

![](https://velog.velcdn.com/images/jaepal/post/9261b966-4d81-450b-a19f-6d086c78ee89/image.PNG)

선택한 음료와 거스름돈이 정상적으로 출력 됐다.

다음은 `InsertCoin()` 함수 실행 시 입력값을 이상하게 하면 나오는 출력문구다.

![](https://velog.velcdn.com/images/jaepal/post/88d469a6-7e8a-4dfa-9277-98596e6536a4/image.PNG)


🙄


### 느낀 점

객체 지향 언어로 처음으로 프로그램 하나를 만들어 보았다.
나같은 경우엔 절차적 프로그래밍 언어인 `C`를 주로 사용 했다보니 익숙하진 않았지만 짜다보니 차이점이 크게 느껴진게 `C` 는
> 함수1 -> 함수2 -> 함수3 -> 결과

이런 느낌이라면 `JAVA` 나 `Python`은
> 메소드(객체) 1 <=> 메소드(객체) 2 <=> 메소드(객체) 3 -> 결과

이런 식으로 메소드나 객체가 서로 상호작용해서 결과값을 내는 느낌이였다.
