package GunGame;

public class Player {
    Weapon myGun;
    public String pName = "Player 1";

    public String ShowUserName() {
        return pName;
    }

    public Player() {
        myGun = new Weapon();
    }

    int Attack() {
        return myGun.Shoot();
    }
}

class Player2 extends Player {
    public Player2() {
        super.pName = "Player 2";
    }
}
