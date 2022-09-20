


## 개요
> #### `C` 를 이용해 원본 이미지(PBM)를 읽은 후 콘솔창에 수정된 이미지를 표시한다.
<a href="https://github.com/DevJaepaL/TIL/blob/main/C/DataPreprocessing/ImageCopy.c">Source code(GitHub)</a>

<br>

## 문제 해결 과정 ✍

C언어는 절차적 프로그래밍 언어이기 때문에 기존에 있던 함수를 이용해 실행하면 콘솔창에 이런 식으로 나온다. 

![](https://velog.velcdn.com/images/jaepal/post/bd24949a-020a-402c-88ca-39362291d2fc/image.PNG)


`main` 함수 코드는 다음과 같다.

```c
int main(void) {
    // Image = 구조체
    Image img,copyImg = 0;
        
    system("cls");
    char imgPath[] = "frog.pbm";
    img = readPBMImage(imgPath);
    DrawImage(img);
    imageRelease(img);
        
    return 0;
}
```

위에 코드를 살펴보면, 이미지 파일을 불러오고 `DrawImage()`,`imageRelease()` 함수를 이용하여 콘솔창에 이미지를 띄우는 방식이다.

하지만 문제는 콘솔창에 원본 이미지가 아닌 수정된 이미지를 출력해야 하는데, 여기서 내가 생각한 방법은 다음과 같다.

```c
int main(void) {
	Image img,copyImg = 0; // copyImg 변수 추가 및 초기화.
	
	system("cls");
	char imgPath[] = "frog.pbm";
	img = readPBMImage(imgPath);

	// Copy & Edit PBM Image.
	writeImage("frogCopy.pbm", img);
	char copyFileName[] = "frogCopy.pbm";
	copyImg = readPBMImage(copyFileName);
	DrawImage(copyImg);
	imageRelease(copyImg);
	
	return 0;
}
```

1. 기존 이미지 읽고 변수에 저장.
 2. 이미지 수정 함수를 이용하여 1번에서 저장한 변수로 수정한 이미지를 만든다.
 3. char 자료형 변수로 복사한 파일명을 변수값에 저장한다.
 4. 복사한 이미지를 다시 읽는다.
 5. `DrawImage()`,`imageRelease()` 함수를 이용하여 복사한 이미지를 콘솔에 출력한다.


## 결과 🔎


![](https://velog.velcdn.com/images/jaepal/post/5ebec6b6-5fcb-4eaf-b959-81a805280e88/image.PNG)



내가 생각한 방법은 `C`는 절차지향 프로그래밍이기 때문에 코드가 순서대로 읽힌다는 점을 이용하여,

    원본 파일 읽기 -> 
    원본 파일 복사하여 새로운 파일 생성 및 수정 ->
    콘솔에 이미지 출력

이런식으로 해봤다.

<br>

### 후기 🧱

> 어렵지 않은 문제였지만, 문제 해결 방식에 정답이 없다보니 내 방식 대로 코드를 작성해 봤지만, 내 입맛대로 짠 느낌이 없지않아 있었다. 그래도 컴퓨팅적 사고를 조금이나마 기르게 된 기회라 생각한다.
