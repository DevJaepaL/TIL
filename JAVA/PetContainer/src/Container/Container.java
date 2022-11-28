package Container;
import java.util.Vector;

public class Container {
    Vector<Container> con = new Vector<Container>(10);
    String registPetName;
    String masterName;
    String petType;

    public Container() {}

    public Container(String registPetName, String masterName, String petType) {
        this.registPetName = registPetName;
        this.masterName = masterName;
        this.petType = petType;
    }

    void CurrentPetInfo(String mName, String pName, String pType) {
        this.masterName = mName;
        this.registPetName = mName;
        this.petType = pType;
        System.out.println("주인 이름 : " + masterName);
        System.out.println("펫 이름 : " + registPetName);
        System.out.println("펫 종류 : " + petType);
    }

    int RegistPet(String mName, String pName, int type) {
        if(type == 1) {
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
        else {
            System.out.println("입력 값 오류 ! 숫자 \"1\" 또는 \"2\"로 다시 입력해주세요.");
        }

        return 0;

    }

    int LeavePet(String mName, String pName, int conNum) {
        
        return 0;
    }

    void AllLeavePet() {
        if(con.isEmpty()) {
            System.out.println("현재 컨테이너에 펫이 없어요 !");
        }

        else {
            System.out.println("현재 컨테이너의 펫 수 : " + con.size());
            System.out.println("모든 펫들을 내보냅니다. ");
            con.clear();
            System.out.println("내보낸 후 컨테이너의 펫 수 : " + con.size());
        }
    }

    void ShowPetList() {
        System.out.println(": 현재 컨테이너 안에 있는 펫 :");
            if(con.isEmpty()) {
                System.out.println("현재 컨테이너에 펫이 없어요 !");
            }
            else {
                for(int i = 0 ; i < con.size(); i++) {
                    System.out.println("컨테이너 " + i + " 번에는 펫이 있어요 ! "); 
                }
            }
    }
}


class Dog extends Container {
    String petType = "강아지";
    public Dog(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, petType);
    }
}

class Cat extends Container {
    String petType = "고양이";
    public Cat(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, petType);
    }
}
