#include <stdio.h>
#pragma warning(disable:4996)

int main(void) {

	char name[10];
	char sex;
	int age;

	FILE* fp = fopen("friend.txt", "wt");

	for (int i = 0; i < 3; i++) {
		printf("이름 성별 나이 순으로 입력 해주세요 :");
		scanf("%s %c %d", name, &sex, &age);
		getchar(); // 버퍼에 남아있는 \n(NULL) 의 소멸을 위해 사용
		fprintf(fp, "%s %c %d", name, sex, age);
	}

	fclose(fp);
	return 0;
}