/* === 구조체 변수를 선언하는 동시에 초기화하기. === */
#include <stdio.h>

struct gameID {
    char userName[20];
    char userJob[10];
    int userLevel;
};

int main(void)
{
    
    struct Person user_01 = { .userName = "JaepaL", .userJob = "마법사", .userLevel = 10 };

    printf("유저 네임 : %s\n", user_01.userName);       // 유저 네임 : JaeapL
    printf("유저 직업 : %d\n", user_01.userJob);        // 유저 직업 : 마법사
    printf("유저 레벨 : %s\n", user_01.userLevel);    // 유저 레벨 : 10

    struct Person user_02 = { "GameManager", "SuperUser" , "100" };

    printf("유저 네임 : %s\n", user_02.userName);       // 유저 네임 : GameManager
    printf("유저 직업 : %d\n", user_02.userJob);        // 유저 직업 : SuperUser
    printf("유저 레벨 : %s\n", user_02.userLevel);    // 유저 레벨 : 100

    return 0;
}
