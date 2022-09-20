#include <stdio.h>
#pragma warning(disable:4996)

typedef struct fren {
	char name[20];
	char sex;
	int age;
} Friend;

int main(void)
{
	FILE* fp;
	Friend myfren1;
	Friend myfren2;

	/* file write */
	fp = fopen("StructMakefriend2.bin", "wb");
	printf("이름, 성별, 나이 순으로 입력하세요  : ");
	scanf("%s %c %d", myfren1.name, &(myfren1.sex), &(myfren1.age));
	fwrite((void*)&myfren1, sizeof(myfren1), 1, fp);
	fclose(fp);

	/* file read */
	fp = fopen("StructMakefriend2.bin", "rb");
	fread((void*)&myfren2, sizeof(myfren2), 1, fp);
	printf("%s %c %d \n", myfren2.name, myfren2.sex, myfren2.age);
	fclose(fp);
	
	return 0;
}