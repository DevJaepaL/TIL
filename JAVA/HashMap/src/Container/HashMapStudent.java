package Container;
import java.util.*;

class Student {
    int id;
    String tel;

    public Student(int id, String tel) {this.id = id; this.tel = tel;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTel() {return tel;}

    public void setTel(String tel) {this.tel = tel;}
}

public class HashMapStudent {
    public static void main(String args[]) {
        HashMap<String, Student> map = new HashMap<String, Student>();        
        Scanner sc = new Scanner(System.in);

        // 3개의 학생 객체 저장
        map.put("Lee" , new Student(1 , "010-3254-6543"));
        map.put("Kim", new Student(2, "010-3167-9503"));
        map.put("Hyeon", new Student(3, "010-4279-7994"));
        map.put("Gyu", new Student(4 , "010-5605-4503"));
        
        while(true)
        {
            System.out.print("검색할 이름 : ");
            String name = sc.nextLine();
            if(name.equals("exit"))
                break;

            Student student = map.get(name);
            if(student == null)
                System.out.println("검색하신 " + name + " 이 존재하지 않습니다.");
            else
                System.out.println("ID : "  + student.getId() + 
                                   "\tTEL : " + student.getTel());
        }

        sc.close();
    }
}
