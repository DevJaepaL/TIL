package Container;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        UI menu = new UI();
    
            while (true) 
            {
                try {
                    Scanner sc = new Scanner(System.in);
                    int menuSelNum;
                    menu.Menu();
                    menuSelNum = sc.nextInt();
                    sc.nextLine();
        
                    if (menuSelNum == 1) {
                        Container con = new Container();
                        int mainSelNum = 0;
                        while(mainSelNum != 4)
                        {
                            menu.SelectMenu();
                            mainSelNum = sc.nextInt();
                            sc.nextLine();
                            switch (mainSelNum) 
                            {
                                case 1:
                                    con.RegistDog();
                                    break;
                                case 2:
                                    con.RegistCat();
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    System.out.println("초기 메뉴로 돌아갑니다.");
                                    break;
                                default:
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
                
                catch(InputMismatchException e) {
                    System.out.println("숫자만 입력해주세요. 메뉴로 돌아갑니다. ");
                }
            }
    }
}
