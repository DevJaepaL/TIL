package Container;
import java.util.Vector;
import java.util.Scanner;

public class Container {
    Vector<Container> con = new Vector<Container>(10);
    String registPetName;
    String masterName;
    String petType;
    int conNum;
    Scanner sc = new Scanner(System.in);

    public Container() {}

    public Container(String registPetName, String masterName) {
        this.registPetName = registPetName;
        this.masterName = masterName;
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
            con.add(new Dog(mName, pName));
            System.out.println("\"" + petType + "\" 등록 완료 !");  
            CurrentPetInfo(mName, pName, petType);
        }
        else if (type == 2) {
            String petType = "고양이";
            con.add(new Cat(mName, pName));
            System.out.println("\"" + petType + "\" 등록 완료 !");  
            CurrentPetInfo(mName, pName, petType);
        }
        else {
            System.out.println("입력 값 오류 ! 숫자 \"1\" 또는 \"2\"로 다시 입력해주세요.");
        }

        return 0;

    }

    int AllOutPet() {
        System.out.println("모든 펫들을 내보냅니다. ");
        con.clear();
        System.out.println("현재 컨테이너의 펫 수 : " + con.size());

        return 0;
    }
}


class Dog extends Container {
    String petType = "강아지";
    public Dog(String registPetName, String masterName) {
        super(registPetName, masterName);
        CurrentPetInfo(registPetName, masterName, masterName);
    }
}

class Cat extends Container {
    String petType = "고양이";
    public Cat(String registPetName, String masterName) {
        super(registPetName, masterName);
        CurrentPetInfo(registPetName, masterName, masterName);
    }
}
