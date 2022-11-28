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
        System.out.println("[1] 펫을 컨테이너에 등록");
        System.out.println("[2] 펫을 컨테이너에서 내보내기");
        System.out.println("[3] 현재 펫 목록 보기");
        System.out.println("[4] 펫 모두 내보내기");
        System.out.println("[5] 초기 메뉴로 돌아가기");
        System.out.print("숫자를 입력 하세요  >>> ");
    }
}
