package Container;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        /* 메인 메소드 내 지역변수 선언 */
        Container con = new Container(); 
        Scanner sc = new Scanner(System.in);
        UI menu = new UI();
        int menuSelNum = 0;
        try { // try-catch 문으로 숫자를 제외한 문자열 입력 시 InputMismatchException 예외처리
            while (true) 
            {
                menu.Menu();
                menuSelNum = sc.nextInt();
                sc.nextLine();
                if (menuSelNum == 1) 
                {
                    int mainNum = 0;
                    while (mainNum != 5) 
                    {
                        int petType;
                        String petName;
                        String masterName;
                        menu.SelectMenu();
                        mainNum = sc.nextInt();
                        sc.nextLine();
                        switch (mainNum) 
                        {
                            case 1: // 펫 등록
                                System.out.println("펫을 등록합니다.");
                                System.out.print("주인 이름을 입력하세요 : ");
                                masterName = sc.nextLine();
                                System.out.print("펫 이름을 입력하세요 : ");
                                petName = sc.nextLine();
                                System.out.print("펫 종류 입력 [1] 강아지 [2] 고양이 : ");
                                petType = sc.nextInt();
                                sc.nextLine();
                                con.RegistPet(masterName, petName, petType);
                                break;
                            case 2: // 펫 내보내기
                                System.out.println("펫을 컨테이너에서 꺼내줍니다.");
                                System.out.print("등록하셨던 주인 이름을 입력하세요 : ");
                                masterName = sc.nextLine();
                                System.out.print("펫의 이름을 입력하세요 : ");
                                petName = sc.nextLine();
                                con.LeavePet(masterName, petName);
                                break;
                            case 3: // 펫 리스트 출력
                                con.ShowPetList();
                                break;                                
                            case 4: // 펫 전부 내보내기
                                con.AllLeavePet();
                                break;
                            case 5: // 초기 메뉴로 복귀
                                System.out.println("초기 메뉴로 돌아갑니다.");
                                break;
                            default:
                                System.out.println("입력 값 오류 ! 숫자 [1-5]만 입력하세요.\n");

                                break;
                        }
                    }
                } else if (menuSelNum == 2) {
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    break;
                } else { // 1-2번을 제외한 모든 숫자 입력 값 받을 시 오류 처리
                    System.out.println("입력 값 오류. 다시 입력하세요.\n");
                }
            }
        } catch (InputMismatchException e) {
            // 숫자 외 입력 값 받을 시 프로그램 종료
            System.out.println("숫자만 입력해주세요. 프로그램을 종료합니다. ");
        }
    }
}
