# 슈팅 콘솔 게임 🔫 

<a href="https://github.com/DevJaepaL/TIL/tree/main/JAVA/ShootingConsoleGame/src/GunGame">Source Code(GitHub)</a>

> `JAVA` 를 이용하여 콘솔 슈팅 게임을 제작한다.
**구현 요구사항은 총 4가지** 이다.

+ 적 HP 표시
+ Player 는 총 2명이며 태그 기능이 필요하다.
(이는, **철권 태그의 태그 기능** 또는 **포켓몬 게임에서의 교체 기능**이라 판단했다 !)

![](https://velog.velcdn.com/images/jaepal/post/9c9d7e4a-379c-4413-8e99-606c90545e11/image.PNG)


+ 무기별 **탄약 수 제한**
+ 무기별 **발사개수가 다르며 발사개수 만큼 적 HP가 감소한다.**
+ 무기를 바꾸면 **탄약은 다시 채워져야 한다. **

## Flow 🌊

슈팅 콘솔 게임 프로그램의 플로우이다. 총 두 가지로 표현했다.
`Class` 와 `메인 메소드` 내에서의 플로우이다.

### `Class` 플로우

먼저 클래스간의 플로우는 다음과 같다.

![](https://velog.velcdn.com/images/jaepal/post/967d47eb-aaa5-422a-bdcc-8262343fcf58/image.PNG)

각 클래스의 기능을 간단하게 설명하자면,

+ `Enemy` 클래스는 콘솔 게임의 **적(보스)** 을 담당한다. 역할은 `Player` 에게 공격받는 역할이다.
+ `Player` 클래스는 2개 이다. 슈퍼 클래스인 `Player 1`과 서브 클래스 `Player 2` 로 총 두개의 클래스를 갖고 있다.
`Player` 클래스는 콘솔 게임의 **플레이어**를 담당한다. 역할은 `Weapon`을 장착해 `Enemy`를 공격한다.
+ `Weapon` 클래스는 총 4개 이다. 슈퍼 클래스 1개와 서브 클래스 3개로 구성 돼있다.
각각의 공통적인 역할은 `Player`의 공격을 도와주는 역할이다. 각 클래스마다 다른 점은 
**탄약 수,적에게 입히는 데미지**이다.
+ `Menu` 클래스는 콘솔 게임의 가독성을 높여주는 역할이다. 출력문으로 구성 되어있다.

### 메인 메소드 내부  플로우

다음은 메인 메소드에서의 프로그램 플로우다.

![](https://velog.velcdn.com/images/jaepal/post/55968cc3-76c6-400b-ad7c-40fbd5878b06/image.PNG)


## 소스 코드

패키지 이름 : `GunGame`
클래스 : `Enemy` , `Game` , `Menu` , `Player` , `Weapon`
메소드 개수 : `8개`

### `Menu.java`

```java
package GunGame;

public class Menu {
    void MainMenu() {
        System.out.println("======== 슈팅 게임 ========");
        System.out.println("::  [1] 게임 시작    ");
        System.out.println("::  [2] 게임 종료    ");
        System.out.println("===========================");
        System.out.print(" 숫자[1 - 2] 를 입력 해주세요.  >>> ");
    }

    void BattleMenu() {
        System.out.println("========== 전투 메뉴 ===========");
        System.out.println("::  [0]Shoot !               ");
        System.out.println("::  [1]권총        [2]리볼버    "); 
        System.out.println("::  [3]자동 소총    [4]핸드 캐넌   ");
        System.out.println("::  [5]플레이어 교체         ");            
        System.out.println("==============================");
        System.out.print("숫자[1 - 5] 를 입력 해주세요.  >>> ");
    }
}
```

`Menu` 클래스는 메인 메뉴에서의 전반적인 콘솔 출력문의 담당해주는 클래스이다.
+ `MainMenu()` 메소드는 프로그램 최초 컴파일 시 나타나는 메소드이다.
+ `BattleMenu()` 메소드는 플레이어가 전투 돌입 시 나타나는 메소드이다.

### `Player.java`

```java
package GunGame;

public class Player {
    Weapon myGun;
    public String pName = "Player 1";

    public String ShowUserName() {
        return pName;
    }

    public Player() {
        myGun = new Weapon();
    }

    int Attack() {
        return myGun.Shoot();
    }
}

class Player2 extends Player {
    public Player2() {
        super.pName = "Player 2";
    }
}

```

`Player` 클래스는 이 프로그램에서의 **플레이어**를 담당하는 클래스이다. 

+ 내부에는 총 두 개의 클래스가 있다. `Player` **슈퍼 클래스**와 `Player2` **서브 클래스**가 있다.
 + 두 개의 클래스 모두 `Weapon` 클래스 객체가 존재한다.
 + `ShowUserName()` 메소드는 현재 플레이어 이름을 보여주는 메소드이다.
 + 메인 메소드에서 **플레이어를 생성할 때마다 생성자에서는 무기를 새로 만들어준다.**
 + `Attack()` 메소드는 플레이어가 장착한 무기로 적에게 공격을 한다. 여기서 내가 태그(교체)기능을 표현하고자 한 것은 `Player2` 서브 클래스에서 `Player2`를 생성 할 때 플레이어 이름을 부모 클래스로의 인스턴스 변수를 참조`super()`하여 **Player 2**로 변경 했다.
```java
public Player2() {
        super.pName = "Player 2";
    }
```

### `Weapon`

```java
package GunGame;

public class Weapon {
    int ammo = 60;
    int damage = 10;
    String weaponName = "권총";

    public Weapon() {
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }

    public Weapon(int ammo, int dmg) {
        this.ammo = ammo;
        this.damage = dmg;
    }

    public void ChangeName(String mine) {
        this.weaponName = mine;
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }

    protected int Shoot() {
        System.out.println("빵 야 - ! !");
        System.out.println("현재 무기 : " + weaponName);
        ammo -= damage;
        if (ammo >= 0) {
            System.out.println("적에게 준 데미지 : \"" + damage +"\" Hit !");
            System.out.println("현재 남은 탄약은 \"" + ammo + "\" 이에요.");
            return damage;
        } else {
            System.out.println("탄약이 부족해요 ! !");
            System.out.println("무기를 바꿔 재장전 해주세요.");
        }
        return 0;
    }
}

class Revolver extends Weapon {
    String weaponName = "리볼버";

    public Revolver(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}

class Rifle extends Weapon {
    String weaponName = "자동 소총";

    public Rifle(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}

class Canon extends Weapon {
    String weaponName = "핸드 캐넌";

    public Canon(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}
```
`Weapon` 클래스는 **`Player`가 적에게 공격을 도와주는 역할을 하는 클래스**이다. 
클래스 내의 메소드는 총 2개이다.

+ 슈퍼 클래스의 생성자는 기본적으로 2개이다. 2개로 구분 지은 이유는 다음과 같다.
  첫번 째 생성자의 존재 이유는 프로그램 내에서 기본 무기(`Weapon`) 선택 시에는
  ```java
   int ammo = 60;
    int damage = 10;
    String weaponName = "권총";

    public Weapon() {
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }
  ```
  위의 코드들이 적용되어 총알은 60발, 데미지는 10발로 선택되게 하기 위함이고
  ```java
  // 슈퍼 클래스 내 생성자
      public Weapon(int ammo, int dmg) {
        this.ammo = ammo;
        this.damage = dmg;
    }
    
    class Revolver extends Weapon {
    String weaponName = "리볼버";

    public Revolver(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}
  ```
  
  두 번째 생성자는 나머지 서브클래스(총 3개)들을 `super()` 를 이용하여 슈퍼 클래스에 생성자에 접근해 각각 서브 클래스들의 총알과 데미지를 메인 메소드내에서 서브 클래스 호출 시 쉽게 바꾸기 위해서였다.

```java
public void ChangeName(String mine) {
        this.weaponName = mine;
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }
```
해당 메소드는 기본 무기 클래스를 제외한 모든 무기 클래스에서 생성 될 때 마다 무기가 바뀌었다는 텍스트를 출력하는 메소드이다.

다음은 `Weapon` 클래스에서 중요한 적에게 데미지를 주는 `Shoot()` 메소드 표현방식이다.
```java
    protected int Shoot() {
        System.out.println("빵 야 - ! !");
        System.out.println("현재 무기 : " + weaponName);
        ammo -= damage;
        if (ammo >= 0) {
            System.out.println("적에게 준 데미지 : \"" + damage +"\" Hit !");
            System.out.println("현재 남은 탄약은 \"" + ammo + "\" 이에요.");
            return damage;
        } else {
            System.out.println("탄약이 부족해요 ! !");
            System.out.println("무기를 바꿔 재장전 해주세요.");
        }
        return 0;
    }
```

해당 메소드의 로직은 다음과 같다.
+ `Shoot()` 메소드가 호출 될 때마다 클래스 내 데미지(`damage`) 값 만큼 
총알(`ammo`)이 깎이도록 했다. 
+ **총알이 0보다 클 경우에만 `damage` 값을 리턴하여 적에게 데미지를 줬다.** 
+ 총알이 0보다 작으면 `0`을 리턴하여 데미지를 못 주도록 설정했다.


### `Enemy`

```java
package GunGame;

public class Enemy {
    int enemyHP;

    public Enemy() {
        // 적 체력 500-1000 난수 생성
        enemyHP = (int)(Math.random()*(1000-500)) + 500;
    }

    void EnemyStatus() {
        System.out.println("보스의 현재 체력은 \"" + enemyHP + "\" 이에요!");
    }
    
    void EnemyClearText() {
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★    보스 퇴치 성공 !    ★★");
        System.out.println("☆★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("메인 메뉴로 돌아 갑니다..");
    }
}
```

`Enemy` 클래스는 이 프로그램에서 **적(보스)**를 담당하는 클래스이다.

+ 메인 메소드에서 `Enemy` 클래스가 호출 될 때마다 생성자 내부에서 **적의 체력을 500-1000 사이로 랜덤하게 나오도록** 했다.
+ `EnemyStatus()` 메소드는 현재 적의 체력상태를 텍스트로 출력해주는 메소드이다.
+ `EnemyClearText()` 는 적을 정상적으로 처치했을 때 텍스트를 출력해주는 메소드이다. 

### Game(Main Method)

```java
package GunGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    // 메인 메소드
    public static void main(String args[]) {
        Menu menu = new Menu();
        Enemy boss = new Enemy();
        Scanner sc = new Scanner(System.in);
        int inputNum = 0;

        try {
            while (true) {
                menu.MainMenu();
                inputNum = sc.nextInt();
                sc.nextLine();
                if (inputNum == 1) {
                    System.out.println("*         - 게 임 스 타 트 -          *");
                    Player p1 = new Player();
                    System.out.println("\"" + p1.ShowUserName() + "\" : 연결 완료 !");                    
                    boss.EnemyStatus();
                    while (true) {
                        if (boss.enemyHP > 0) {
                            int selNum;
                            menu.BattleMenu();
                            selNum = sc.nextInt();
                            sc.nextLine();
                            switch (selNum) {
                                case 0:                            
                                    boss.enemyHP -= p1.Attack();
                                    boss.EnemyStatus();                                
                                    break;
                                case 1: // 기본 무기 장착 및 재장전
                                    p1.myGun = new Weapon();
                                    break;
                                case 2: // 리볼버 장착 및 재장전(탄약 : 120 , 데미지 30) 총 4 번 슈팅 가능
                                    p1.myGun = new Revolver(120, 30);
                                    break;
                                case 3: // 라이플 장착 및 재장전(탄약 : 200 , 데미지 50) 총 4 번 슈팅 가능
                                    p1.myGun = new Rifle(200, 50);
                                    break;
                                case 4: // 핸드캐넌 장착 및 재장전(탄약 : 450 , 데미지 150) 총 3 번 슈팅 가능
                                    p1.myGun = new Canon(450, 150);
                                    break;
                                case 5:
                                    if (p1 instanceof Player2) {
                                        p1 = new Player();
                                        System.out.println("\"" + p1.ShowUserName() + "\" 로 변경!");
                                        break;
                                    }
                                    if (p1 instanceof Player) {
                                        p1 = new Player2();
                                        System.out.println("\"" + p1.ShowUserName() + "\" 로 변경!");
                                        break;
                                    }
                                default:
                                    System.out.println("숫자 [1-5] 만 입력해주세요.");
                                    break;
                            }
                        }
                        
                        else if (boss.enemyHP <= 0) { // 보스 처치 시 실행되는 부분.
                            boss.EnemyClearText();
                            boss = new Enemy(); // 보스 새로 생성
                            break; // 메인 메뉴로 복귀
                        }
                    }
                }

                else if (inputNum == 2) {
                    System.out.println("게임을 종료 합니다.");
                    break;
                }

                else
                    System.out.println("숫자 [1 - 2] 만 입력해주세요.");

            }
        } catch (InputMismatchException e) {
            System.out.println("입력값 오류 ! 숫자만 입력 해주세요.");
            System.out.println("프로그램을 종료합니다. .");
        }
    }
}
```

`Game` 클래스는 프로그램의 메인이자 실행을 담당하는 메소드이다.
+ 먼저, 사용자로부터 모든 입력값은 숫자만 입력받기 때문에 **예외처리로 숫자를 제외한 모든 입력값을 받았을 때 오류 출력문을 출력**하고 프로그램이 꺼지도록 했다.
```java
catch (InputMismatchException e) {
            System.out.println("입력값 오류 ! 숫자만 입력 해주세요.");
            System.out.println("프로그램을 종료합니다. .");
        }
```

+ 메인 메소드 내에서 중요하게 생각하는 부분은 `switch`문 내부이다.
```java
			switch (selNum) {
                                case 0:                            
                                    boss.enemyHP -= p1.Attack();
                                    boss.EnemyStatus();                                
                                    break;
                                case 1: // 기본 무기 장착 및 재장전
                                    p1.myGun = new Weapon();
                                    break;
                                case 2: // 리볼버 장착 및 재장전(탄약 : 120 , 데미지 30) 총 4 번 슈팅 가능
                                    p1.myGun = new Revolver(120, 30);
                                    break;
                                case 3: // 라이플 장착 및 재장전(탄약 : 200 , 데미지 50) 총 4 번 슈팅 가능
                                    p1.myGun = new Rifle(200, 50);
                                    break;
                                case 4: // 핸드캐넌 장착 및 재장전(탄약 : 450 , 데미지 150) 총 3 번 슈팅 가능
                                    p1.myGun = new Canon(450, 150);
                                    break;
                                case 5: // 플레이어 변경 기능
                                    if (p1 instanceof Player2) { // Player1가 Player2에 속할 경우 플레이어1 생성
                                        p1 = new Player();
                                        System.out.println("\"" + p1.ShowUserName() + "\" 로 변경!");
                                        break;
                                    }
                                    if (p1 instanceof Player) { // Player1가 그대로 Player에 속할 경우 플레이어2 생성
                                        p1 = new Player2();
                                        System.out.println("\"" + p1.ShowUserName() + "\" 로 변경!");
                                        break;
                                    }
```

+ 기본 무기 선택 시(1번 입력시)에는 슈퍼 클래스의 생성자가 생성되고, 그 외에 **모든 무기는 서브 클래스**이기 때문에 **상속 받은 슈퍼 클래스를 참조하여 생성자에 접근 한 후 총알과 데미지를 설정 했음**을 알 수 있다. 

+ 5번 선택시는 `instanceof`를 이용하여 클래스를 확인한 후 각각의 플레이어가 특정 클래스에 속한지 체크 후 형변환하여 서로 태그(교체)해서 새로운 플레이어로 만들어줬다.


## 결과

### 프로그램 작동 시 출력 화면

> 아래 이미지를 클릭하여 출력 영상(Youtube)을 볼 수 있다.

[![Java](https://velog.velcdn.com/images/jaepal/post/118a5485-4c17-4257-8176-a71fb1c59510/image.jpg)](https://www.youtube.com/watch?v=4u-bsDhNE5k?t=0s)

아래는 실행 결과다.

### 최초 시작 메뉴시

+ 1번 입력시 게임이 시작된다.
+ 2번 입력시 프로그램이 종료된다.
![](https://velog.velcdn.com/images/jaepal/post/36311bb7-fa9d-4dc1-b030-5556a9145dde/image.PNG)
    
### 게임 시작 시
+ 사용자로부터 1번 입력을 받고 게임 시작과 동시에 **`Player`와 `보스(Enemy)` 가 생성된다**.
`Player`는 기본적으로 `Weapon(기본 권총)`을 들고 있다.

+ 보스의 **체력은 `500-1000` 으로 랜덤 생성**된다.

+ 입력 시 행동
  + [0] 번 입력 시 보스에게 총을 쏴 데미지를 입힌다.
![](https://velog.velcdn.com/images/jaepal/post/65ae327d-377c-4741-a675-05960d1baa96/image.PNG)
  + [1] 번 입력 시 기본 권총을 선택한다.
  ![](https://velog.velcdn.com/images/jaepal/post/eab8aa62-07b1-41b2-b75f-c551ff5cd509/image.PNG)
  + [2] 번 입력 시 리볼버를 선택한다.
  ![](https://velog.velcdn.com/images/jaepal/post/f1899f29-4401-4306-9f91-7252002b1c8e/image.PNG)
  + [3] 번 입력 시 자동 소총을 선택한다.
  ![](https://velog.velcdn.com/images/jaepal/post/a2252739-a0bf-4f4c-9bbe-cf6aff239e80/image.PNG)
  + [4] 번 입력 시 핸드 캐넌을 선택한다.
  ![](https://velog.velcdn.com/images/jaepal/post/0b8e9f04-8adc-4539-b159-b56d1b41ba22/image.PNG)
  + [5] 번 입력 시 ** `Player 1` 또는 `Player 2` 로 교체** 된다.
  ![](https://velog.velcdn.com/images/jaepal/post/8b82151c-652b-42cd-8726-ce4eddacd3dc/image.PNG)

여기서 총을 선택`[1-4 번 입력]` 할 때 마다 무기의 탄약이 자동으로 충전된다.

#### 보스 처치 시

![](https://velog.velcdn.com/images/jaepal/post/c1238fc4-d4d9-4f22-ba35-5809e691e9a9/image.PNG)

보스 처치 시 시작시 최초 메뉴로 돌아간다.


## 코드를 완성하며 느낀 점 💭

이번 콘솔 게임을 설계하고 작성하며 객체 지향에 점점 익숙해져 갔다. 
**슈퍼클래스 및 서브클래스의 상호작용 개념**과 `super()` 의 사용법, 각 클래스의 메소드를 어디서 호출해야 코드를 간결하게 표시할 수 있을지 고민하고 또 고민했다.

메인 메소드를 더욱 간결하게 표현하고 싶었는데, 현재 내 지식으로 최대한 간결하게 짜봤다.
이 코드에서 개선해야할 부분은 **상속 할 때 오버라이딩을 활용 못한 점**, `instanceof`를 적절히 사용했는 지 피드백을 듣고 싶다. 또한 이 코드에서의 문제점이 무엇인지 다른 분들께 피드백을 받고 싶다.