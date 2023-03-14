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

    boolean checkInputNumber(int inputChrist, int inputKiller) {
        int checkTotalBoatCount = inputChrist + inputKiller;
        boolean christCheck = inputChrist >= 0 ? true : false;
        boolean killerCheck = inputKiller >= 0 ? true : false;
        boolean totalNumCheck = checkTotalBoatCount > 0 && checkTotalBoatCount <= 2 ? true : false;

        if(christCheck && killerCheck && totalNumCheck == true){
            return true;
        } else {
            return false;
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

    void CurrentStatusMessage() {
        if(gameCnt >= 0 && currentLocation == true) {
            System.out.println("            ============>>>>>>");
        } else if(gameCnt > 0 && currentLocation == false) {
            System.out.println("<<<<<<============            ");
        }
    }

    void CurrentStateMent() {
        if(currentLocation == true) {
            System.out.println("현재 지역 위치 : 왼쪽 지역");
            System.out.println("현재 보낼 수 있는 선교사 : " + leftC + " 명");
            System.out.println("현재 보낼 수 있는 식인종 : " + leftK + " 명");
        } else {
            System.out.println("현재 지역 위치 : 오른쪽 지역");
            System.out.println("현재 보낼 수 있는 선교사 : " + rightC + " 명");
            System.out.println("현재 보낼 수 있는 식인종 : " + rightK + " 명");
        }
    }

    void GameMessage() {
        System.out.println("===========================================");
        System.out.println("         게임 진행 횟수 : " + gameCnt + " 회");
        System.out.println("===========================================");
        System.out.println("[왼쪽 지역]    ~ ~ ~ ~ ~ ~ [오른쪽 지역]");
        System.out.printf("[선교사 %d 명]  ~ ~ ~ ~ ~ ~ [선교사 %d 명]\n", leftC, rightC);
        System.out.println("               ~ ~ ~ ~ ~ ~");
        CurrentStatusMessage();
        System.out.println("               ~ ~ ~ ~ ~ ~");
        System.out.printf("[식인종 %d 명]  ~ ~ ~ ~ ~ ~ [식인종 %d 명]\n", leftK , rightK);
        System.out.println("===========================================");
        CurrentStateMent();
    }

    void MoveRight(int christCnt, int killerCnt) {
        gameCnt++;
        if (checkInputNumber(christCnt, killerCnt)) {
            leftC -= christCnt;
            leftK -= killerCnt;
            rightC += christCnt;
            rightK += killerCnt;
            currentLocation = false;
        } else {
            System.out.println("입력 값 오류 ! [숫자 1 - 2] 만 입력하세요.");
        }
    }

    void MoveLeft(int christCnt, int killerCnt) {
        gameCnt++;
        if (checkInputNumber(christCnt, killerCnt)) {
            rightC -= christCnt;
            rightK -= killerCnt;
            leftC += christCnt;
            leftK += killerCnt;
            currentLocation = true;
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
            
            game.GameMessage();
            
            System.out.print("선교사를 몇 명 보낼까요 ? : ");
            int christ = sc.nextInt();
            System.out.print("식인종을 몇 명 보낼까요 ? : ");
            int killer = sc.nextInt();
            
            if (game.checkLocatation(true)) {
                game.MoveRight(christ, killer);
            } else {
                game.MoveLeft(christ, killer);
            }
        }

        sc.close();
    }
}