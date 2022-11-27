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

    public Container() {
    
    }

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

    void RegistPet(String mName, String pName, String type) {
        System.out.println("펫을 등록합니다.");
        System.out.print("펫 종류를 입력하세요 [강아지 or 고양이] : ");
        type = sc.nextLine();        
        System.out.print("펫 이름을 입력하세요 : ");
        pName = sc.nextLine();
        System.out.print("주인 이름을 입력하세요 : ");
        mName = sc.nextLine();
        if(type == "강아지") {
            con.add(new Dog(mName, pName, type));
            System.out.println(type + "등록 완료 !");
        }
        else if (type == "고양이") {
            con.add(new Cat(mName, pName, type));
            System.out.println(type + "등록 완료 !");
        }
        else {
            System.out.println("입력 값 오류 ! ");
        }
    }

    int AllOutPet() {
        System.out.println("모든 펫들을 내보냅니다. ");
        con.clear();
        System.out.println("현재 컨테이너의 펫 수 : " + con.size());

        return 0;
    }
}


class Dog extends Container {
    public Dog(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, masterName);
    }
}

class Cat extends Container {
    public Cat(String registPetName, String masterName, String petType) {
        super(registPetName, masterName, petType);
        CurrentPetInfo(registPetName, masterName, masterName);
    }
}
