package Container;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        Container con = new Container();
        Scanner sc = new Scanner(System.in);
        UI menu = new UI();
        int menuSelNum = 0;
        try {
            while (true) 
            {
                menu.Menu();
                menuSelNum = sc.nextInt();
                sc.nextLine();
                if (menuSelNum == 1) {
                    int mainNum = 0;
                    while (mainNum != 5) 
                    {
                        int petType;
                        int containNum;
                        String petName;
                        String masterName;
                        menu.SelectMenu();
                        mainNum = sc.nextInt();
                        sc.nextLine();
                        switch (mainNum) 
                        {
                            case 1:
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
                            case 2:
                                System.out.println("펫을 컨테이너에서 꺼내줍니다.");
                                System.out.print("등록하셨던 주인 이름을 입력하세요 : ");
                                masterName = sc.nextLine();
                                System.out.print("등록하셨던 펫 이름을 입력하세요 : ");
                                petName = sc.nextLine();
                                break;
                            case 3:
                                con.ShowPetList();
                                break;
                            case 4:
                                con.AllLeavePet();
                                break;
                            case 5:
                                System.out.println("초기 메뉴로 돌아갑니다.");
                                break;
                            default:
                                System.out.println("입력 값 오류 . .");
                                break;
                        }
                    }
                } else if (menuSelNum == 2) {
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    break;
                } else {
                    System.out.println("입력 값 오류. 다시 입력하세요.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요. 프로그램을 종료합니다. ");
        }
    }
}
