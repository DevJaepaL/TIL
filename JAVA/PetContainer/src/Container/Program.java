package Container;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        UI menu = new UI();
        try {
            while (true) 
            {
                    Scanner sc = new Scanner(System.in);
                    int menuSelNum = 0;
                    menu.Menu();
                    menuSelNum = sc.nextInt();
                    sc.nextLine();
        
                    if (menuSelNum == 1) {
                        Container con = new Container();
                        int mainNum = 0;
                        while(mainNum != 4)
                        {
                            menu.SelectMenu();
                            mainNum = sc.nextInt();
                            sc.nextLine();                    
                            switch (mainNum) 
                            {
                                case 1:
                                    int petType;
                                    String petName;
                                    String masterName;
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
                                    break;
                                case 3:
                                    con.AllOutPet();
                                    break;
                                case 4:
                                    System.out.println("초기 메뉴로 돌아갑니다.");
                                    break;
                                default:
                                    System.out.println("입력 값 오류 . .");
                                    break;
                            }
                        }
                    } 
                    else if (menuSelNum == 2) {
                        break;
                    } 
                    else {
                        System.out.println("입력 값 오류. 다시 입력하세요.");
                    }
                    sc.close();
                }
            } catch(InputMismatchException e) {
                System.out.println("숫자만 입력해주세요. 프로그램을 종료합니다. ");
            }
    }
}
