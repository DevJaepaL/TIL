package GunGame;

public class Weapon {
    int ammo = 60;
    int damage = 10;
    String weaponName = "권총";

    public Weapon() {
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }

    public Weapon(int ammo, int dmg) {
        this.ammo = ammo;
        this.damage = dmg;
    }

    public void ChangeName(String mine) {
        this.weaponName = mine;
        System.out.println("\"" + weaponName + "\" 을(를) 장착하셨습니다 !");
    }

    protected int Shoot() {
        System.out.println("빵 야 - ! !");
        System.out.println("현재 무기 : " + weaponName);
        ammo -= damage;
        if (ammo >= 0) {
            System.out.println("적에게 준 데미지 : \"" + damage +"\" Hit !");
            System.out.println("현재 남은 탄약은 \"" + ammo + "\" 이에요.");
            return damage;
        } else {
            System.out.println("탄약이 부족해요 ! !");
            System.out.println("무기를 바꿔 재장전 해주세요.");
        }
        return 0;
    }
}

class Revolver extends Weapon {
    String weaponName = "리볼버";

    public Revolver(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}

class Rifle extends Weapon {
    String weaponName = "자동 소총";

    public Rifle(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}

class Canon extends Weapon {
    String weaponName = "핸드 캐넌";

    public Canon(int ammo, int damage) {
        super(ammo, damage);
        super.weaponName = weaponName;
        ChangeName(weaponName);
    }
}