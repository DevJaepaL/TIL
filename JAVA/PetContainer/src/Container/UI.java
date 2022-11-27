package Container;

public class UI {

    void Menu() {
        System.out.println("== Pet Container ==");
        System.out.println("[1] 입장");
        System.out.println("[2] 종료");
        System.out.print("숫자를 입력 하세요  >>> ");
    }

    void SelectMenu() {
        System.out.println("==  Select Menu  ==");
        System.out.println("[1] 강아지 등록");
        System.out.println("[2] 고양이 등록");
        System.out.println("[3] 펫 내보내기");
        System.out.println("[4] 초기 메뉴로 돌아가기");
        System.out.print("숫자를 입력 하세요  >>> ");
    }
}
