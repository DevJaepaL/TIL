#include <stdio.h>
#pragma warning(disable:4996)

int main(void) {

	char name[10];
	char sex;
	int age;

	FILE* fp = fopen("friend.txt", "wt");

	for (int i = 0; i < 3; i++) {
		printf("�̸� ���� ���� ������ �Է� ���ּ��� :");
		scanf("%s %c %d", name, &sex, &age);
		getchar(); // ���ۿ� �����ִ� \n(NULL) �� �Ҹ��� ���� ���
		fprintf(fp, "%s %c %d", name, sex, age);
	}

	fclose(fp);
	return 0;
}