package Container;
import java.util.ArrayList;

public class Container {
    ArrayList<Container> con = new ArrayList<Container>(10);
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
        this.registPetName = pName;
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
            System.out.println(con.size());
        }
        else if (type == 2) {
            String petType = "고양이";
            con.add(new Cat(mName, pName, petType));
            System.out.println("\"" + petType + "\" 등록 완료 !");  
            CurrentPetInfo(mName, pName, petType);
            System.out.println(con.size());
        }
        else {
            System.out.println("입력 값 오류 ! 숫자 \"1\" 또는 \"2\"로 다시 입력해주세요.");
        }

        return 0;

    }

    int LeavePet(String mName) {
        if(con.isEmpty()){
            System.out.println("현재 모든 방이 비어있습니다.");
        }
        else {
            for(int i = 0; i < con.size(); i++){
                Container c = con.get(i);
                if(mName.contains(c.masterName)) {
                    System.out.println("== 내보내는 펫 정보 == ");
                    CurrentPetInfo(mName, registPetName, petType);
                    System.out.println("\n펫이 있던 컨테이너 번호 : ( " + i + " ) 번 방");
                    System.out.println("펫 \""+ registPetName +"\" 의 주인인, \"" + mName + "\"님 잘 가세요! \n");
                    con.remove(i);
                }
                else {
                    System.out.println("해당 정보를 가진 정보가 없네요.");
                }
            }
        }
        return 0;
    }

    void AllLeavePet() {
        if(con.isEmpty()) {
            System.out.println("현재 컨테이너에 펫이 없어요 !\n");
        }

        else {
            System.out.println("현재 컨테이너의 펫의 마리 수 : " + con.size());
            System.out.println("모든 펫들을 내보냅니다. ");
            con.clear();
            System.out.println("내보낸 후 컨테이너의 펫의 마리 수 : " + con.size());
        }
    }

    void ShowPetList() {
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
