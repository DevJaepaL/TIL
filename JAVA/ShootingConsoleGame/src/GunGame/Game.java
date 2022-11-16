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
                                    if (p1 instanceof Player2) { // Player1가 Player2에 속할 경우 플레이어1 생성
                                        p1 = new Player();
                                        System.out.println("\"" + p1.ShowUserName() + "\" 로 변경!");
                                        break;
                                    }
                                    if (p1 instanceof Player) { // Player1가 Player2에 속할 경우 플레이어1 생성
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
