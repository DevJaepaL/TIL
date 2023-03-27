package CannibalsGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class World {
    private int gameCnt = 0;
    private int leftC = 3;
    private int leftK = 3;
    private int rightC = 0;
    private int rightK = 0;
    private boolean currentLocation = true;
    private List<int[]> commandHistory;

    public World(){
        commandHistory = new ArrayList<int[]>();
    }

    void Init() {
        leftC = 3;
        leftK = 3;
        rightC = 0;
        rightK = 0;
        gameCnt = 0;
        currentLocation = true;
        commandHistory.clear();
    }

    void currentStatusMessage() {
        if(gameCnt >= 0 && currentLocation == true) {
            System.out.println("            ============>>>>>>");
        } else if(gameCnt > 0 && currentLocation == false) {
            System.out.println("            <<<<<<============");
        }
    }

    void currentLocateState() {
        if(currentLocation == true) {
            System.out.println("현재 지역 위치 : 왼쪽 지역");
        } else {
            System.out.println("현재 지역 위치 : 오른쪽 지역");
        }
    }

    void gameMessage() {
        System.out.println("===========================================");
        System.out.println("         게임 진행 횟수 : " + gameCnt + " 회");
        System.out.println("===========================================");
        System.out.println("[왼쪽 지역]    ~ ~ ~ ~ ~ ~ [오른쪽 지역]");
        System.out.printf("[선교사 %d 명]  ~ ~ ~ ~ ~ ~ [선교사 %d 명]\n", leftC, rightC);
        System.out.println("               ~ ~ ~ ~ ~ ~");
        currentStatusMessage();
        System.out.println("               ~ ~ ~ ~ ~ ~");
        System.out.printf("[식인종 %d 명]  ~ ~ ~ ~ ~ ~ [식인종 %d 명]\n", leftK , rightK);
        System.out.println("===========================================");
        currentLocateState();
    }

    int[] autoCommand() {
        int[][] commandList = {{0,1},{1,0},{1,1},{2,0},{0,2}};
        int [] ret = new int[2];
        int rindex = (int)(Math.random() * 5) + 0;
        ret = commandList[rindex];
        commandHistory.add(ret);
        return ret;
    }

    int CheckGameResult() {
        boolean checkWin = rightC == 3 && rightK == 3 ? true : false;
        boolean loseConditon = leftC < leftK || rightC < rightK ? true : false;
        boolean loseCondition2 = leftC > 0 && rightC > 0 ? true : false;

        if (checkWin) {
            System.out.println("게임 클리어 !");
            System.out.print("보트를 움직인 총 횟수 : " + gameCnt);
            return 2;
        } else if(loseConditon && loseCondition2) {
            System.out.println("선교사가 죽었어요 ! !");
            System.out.println("게임 패배 ! !");
            return 1;
        }         
        return 0;
    }

    boolean checkLocatation(boolean check) {
        if (currentLocation == true) {
            return currentLocation;
        } else {
            return currentLocation = false;
        }
    }

    /** 사용자의 입력 값을 체크하는 메소드.
     *  게임 내의 선교사와 식인종 수를 체크하며 범위 내 정상 값만 True값으로 넘겨준다.
     */
    boolean checkRightNumber(int inputC, int inputK) {
        boolean CheckInputLeft = leftC >= inputC && leftK >= inputK ? true : false;
        if(CheckInputLeft) {
            return true;
        } else {
            return false;
        }  
    }

    boolean checkLeftNumber(int inputC, int inputK) {
        boolean CheckInputRight = rightC >= inputC && rightK >= inputK ? true : false;
        if(CheckInputRight) {
            return true;
        } else {
            return false;
        }  
    }

    /** 보트를 오른쪽으로 옮기는 메소드이다. 매개변수 값을 전달 받아 계산 후 
     *  멤버 변수인 currentLocation을 false(= 왼쪽)로 다시 변경해준다. */
    void MoveRight(int christCnt, int killerCnt) {
        if (checkRightNumber(christCnt, killerCnt)) {
            gameCnt++;
            System.out.println("보낸 선교사 : " + christCnt + " 명");
            System.out.println("보낸 식인종 : " + killerCnt + " 마리");
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
        if (checkLeftNumber(christCnt, killerCnt)) {
            gameCnt++;
            System.out.println("보낸 선교사 : " + christCnt + " 명");
            System.out.println("보낸 식인종 : " + killerCnt + " 마리");
            rightC -= christCnt;
            rightK -= killerCnt;
            leftC += christCnt;
            leftK += killerCnt;
            currentLocation = true;
        } else {
            System.out.println("입력 값 오류 ! ! 범위 내 값만 입력하세요.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        World game = new World();
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            int state = game.CheckGameResult();
            game.gameMessage();
            int[] rData = game.autoCommand();
            int autoInputC = rData[0];
            int autoInputK = rData[1];     
            System.out.println(autoInputC + " , " + autoInputK);
            Thread.sleep(500);

            if (game.checkLocatation(true)) {
                game.MoveRight(autoInputC, autoInputK);
            } else {
                game.MoveLeft(autoInputC, autoInputK);
            }

            if(state == 1) {
                // Game Over
                game.Init();
            } else if(state == 2) {
                // Game Win
                System.out.println("Win");
                for(int i = 0 ; i < game.commandHistory.size() ; i++) {
                    System.out.print("" + (game.commandHistory.get(i))[0] + "-" + (game.commandHistory.get(i))[1] + "--");
                } break;
            }
            
        }

        sc.close();
    }
}