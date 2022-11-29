package Container;
import java.util.ArrayList;

public class Container {
    ArrayList<Container> con = new ArrayList<Container>(10); // Container 객체 생성 시 10개 인덱스 생성
    String registPetName;
    String masterName;
    String petType;

    public Container() {} // 기본 생성자

    /* 서브 클래스 업 캐스팅 시 사용할 생성자 */
    public Container(String registPetName, String masterName, String petType) {
        this.registPetName = registPetName;
        this.masterName = masterName;
        this.petType = petType;
    }

    // Pet 정보 출력 메소드
    void CurrentPetInfo(String mName, String pName, String pType) {
        this.masterName = mName;
        this.registPetName = pName;
        this.petType = pType;
        System.out.println("주인 이름 : " + masterName);
        System.out.println("펫 이름 : " + registPetName);
        System.out.println("펫 종류 : " + petType);
    }

    // Pet 등록 메소드
    int RegistPet(String mName, String pName, int type) {
        if(type == 1) {
            /* 메인 메소드에서 입력 값 1일 시, Dog 서브 클래스 업캐스팅 후 객체 생성.
            *               입력 값 2일 시, Cat 서브 클래스 업캐스팅 후 객체 생성.
            */
            String petType = "강아지";
            con.add(new Dog(mName, pName, petType));
            System.out.println("\"" + petType + "\" 등록 완료 !");  
            CurrentPetInfo(mName, pName, petType);
        }
        else if (type == 2) {
            String petType = "고양이";
            con.add(new Cat(mName, pName, petType));
            System.out.println("\"" + petType + "\" 등록 완료 !");  
            CurrentPetInfo(mName, pName, petType);
        }
        // 1 , 2를 제외한 입력 값 받을 시 오류 텍스트 출력
        else {
            System.out.println("입력 값 오류 ! 숫자 \"1\" 또는 \"2\"로 다시 입력해주세요.");
        }

        return 0;
    }

    int LeavePet(String mName, String pName) {
        if(con.isEmpty()){ // 모든 방이 비어 있을 경우
            System.out.println("현재 모든 방이 비어있습니다.");
        }
        else {
            for(int i = 0; i < con.size(); i++){
                Container c = con.get(i);
                if(mName.contains(c.masterName) && pName.contains(c.registPetName)) {
                    System.out.println("== 내보내는 펫 정보 == ");
                    CurrentPetInfo(mName, registPetName, petType);
                    System.out.println("\n펫이 있던 컨테이너 번호 : ( " + i + " ) 번 방");
                    System.out.println("펫 \""+ registPetName +"\" 의 주인인, \"" + mName + "\"님 잘 가세요! \n");
                    con.remove(i);
                }
                else { // 컨테이너 인덱스 요소안에 있는 값이 입력값과 일치하지 않을 경우 텍스트 출력
                    System.out.println("입력하신 주인 이름인 " + mName +  "  이 컨테이너에 없어요.");
                    System.out.println("입력하신 펫 이름인 " + pName +  "  가 가진 정보가 없네요.");
                }
            }
        }
    
        return 0;
    }

    // 모든 컨테이너 인덱스에 있는 객체 요소 지우는 메소드
    void AllLeavePet() {
        if(con.isEmpty()) { // 컨테이너 인덱스에 요소가 없을 경우
            System.out.println("현재 컨테이너에 펫이 없어요 !\n");
        }

        else { // 있을 경우 요소 크기 출력 후 전부 clear
            System.out.println("현재 컨테이너의 펫의 마리 수 : " + con.size());
            System.out.println("모든 펫들을 내보냅니다. ");
            con.clear();
            System.out.println("내보낸 후 컨테이너의 펫의 마리 수 : " + con.size());
        }
    }

    void ShowPetList() { // 각 인덱스에 펫 객체가 있는지 출력해주는 메소드
        System.out.println("::: 현재 컨테이너 안에 있는 펫 :::");
            if(con.isEmpty()) {
                System.out.println("현재 컨테이너에 펫이 없어요 !\n");
            }
            else {
                for(int i = 0 ; i < con.size(); i++) {
                    System.out.println("컨테이너 " + i + " 번 안에는 펫이 쉬고있어요 ! "); 
                }
            }
    }
}


class Dog extends Container {
    public Dog(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, petType);
    }
}

class Cat extends Container {
    public Cat(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, petType);
    }
}
