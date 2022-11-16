package GunGame;

public class Enemy {
    int enemyHP;

    public Enemy() {
        // 적 체력 500-1000 난수 생성
        enemyHP = (int)(Math.random()*(1000-500)) + 500;
    }

    void EnemyStatus() {
        System.out.println("보스의 현재 체력은 \"" + enemyHP + "\" 이에요!");
    }
    
    void EnemyClearText() {
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★    보스 퇴치 성공 !    ★★");
        System.out.println("☆★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("메인 메뉴로 돌아 갑니다..");
    }
}
