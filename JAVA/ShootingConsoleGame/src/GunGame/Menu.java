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
