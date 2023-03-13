package CannibalsGame;

import java.util.Scanner;

public class World {
    private int gameCnt = 0;
    private int leftC = 3;
    private int leftK = 3;
    private int rightC = 0;
    private int rightK = 0;
    private boolean currentLocation = true;

    boolean checkLocatation(boolean check) {
        if (currentLocation == true) {
            return currentLocation;
        } else {
            return currentLocation = false;
        }
    }

    boolean CheckWin() {
        if (rightC == 3 && rightK == 3) {
            System.out.println("게임 클리어 !");
            return true;
        }
        return false;
    }

    boolean CheckLose() {
        if (leftC < leftK || rightC < rightK && leftC > 0 && rightC > 0) {
            System.out.println("선교사가 죽었어요 ! !");
            System.out.println("게임 패배 ! !");
            return true;
        }
        return false;
    }

    void CurrentStatus() {
        if(gameCnt > 0 && currentLocation == true) {
            System.out.println("* [L] = = = = = = = > [R] *");
        } else if(gameCnt > 0 && currentLocation == false) {
            System.out.println("* [L] < = = = = = = = [R] *");
        }
        System.out.println("* 게임 진행 횟수 : " + gameCnt + " 회");
        System.out.println("* 왼쪽 지역 정보 *");
        System.out.println("선교사 : " + leftC);
        System.out.println("식인종 : " + leftK);
        System.out.println("* 오른쪽 지역 정보 *");
        System.out.println("선교사 : " + rightC);
        System.out.println("식인종 : " + rightK);
    }

    void MoveRight(int christCnt, int killerCnt) {
        gameCnt++;
        if (christCnt <= 2 && killerCnt <= 2) {
            leftC -= christCnt;
            leftK -= killerCnt;
            rightC += christCnt;
            rightK += killerCnt;
        } else {
            System.out.println("입력 값 오류 ! [숫자 1 - 2] 만 입력하세요.");
        }
    }

    void MoveLeft(int christCnt, int killerCnt) {
        gameCnt++;
        if (christCnt <= 2 && killerCnt <= 2) {
            rightC -= christCnt;
            rightK -= killerCnt;
            leftC += christCnt;
            leftK += killerCnt;
        } else {
            System.out.println("입력 값 오류 ! [숫자 1 - 2] 만 입력하세요.");
        }
    }

    public static void main(String[] args) {
        World game = new World();
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            if(game.CheckLose() == true || game.CheckWin() == true) break;
            game.CurrentStatus();
            System.out.print("선교사를 몇 명 보낼까요 ? : ");
            int christ = sc.nextInt();
            System.out.print("식인종을 몇 명 보낼까요 ? : ");
            int killer = sc.nextInt();
            
            if (game.checkLocatation(true)) {
                game.MoveRight(christ, killer);
                game.currentLocation = false;
            } else {
                game.MoveLeft(christ, killer);
                game.currentLocation = true;
            }
        }

        sc.close();
    }
}