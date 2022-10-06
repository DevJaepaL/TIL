# SQL - 조건 검색 🔎
___

## 개요

SQL문의 데이터 조작어(DML)를 이전 게시글에 이어 계속 깊게 공부해보려고 합니다. 😌

## 조건 검색
SQL 데이터 조작어중 조건에 맞는 검색을 해야할 때 사용하는 `WHERE` 절을 사용한다.
조건으로 사용할 수 있는 술어는 **비교, 범위, 집합, 패턴, NULL** 등으로 구분한다.
밑에 표를 통해 간단하게 각각의 기능을 정리해보았다.

| 술어   | 연산자                  | 사용 예                                          |
|:-----|:---------------------|:----------------------------------------------|
| 비교   | =, <>, <, <=, >, >=  | `price < 20000 `                              |
| 범위   | BETWEEN              | `price BETWEEN 10000 AND 20000`               |
| 집합   | IN , NOT IN          | `price IN ( 10000, 20000, 30000 ) `           |
| 패턴   | LIKE                 | `bookname LIKE '축구의 역사'`                      |
| NULL | IS NULL, IS NOT NULL | `price IS NULL`                               |
| 복합조건 | AND, OR, NOT         | `(price < 20000) AND (bookname LIKE '축구의 역사')` |


## 비교 연산자
단순 비교는  `=, <>, <, <=, >, >=` 등을 사용한다. 예를 들어 가격이 20,000원 미만인
도서를 검색 하려면 다음과 같이 `WHERE` 절을 사용한다.
전 게시글에 기술했듯이 다른 언어의 `if()` 문과 비슷한 점이 있다.

### 문제 - 1

> 가격이 20,000원 미만인 도서를 검색하세요.

```sql
SELECT * -- 테이블 전체 속성 산출
fROM Book -- Book 테이블에서 검색
WHERE price < 20000; -- 20000원 미만인 가격

```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/28178a3b-1d67-487b-a01c-ebdd1326222e/image.PNG)

간단한 문제라 설명이 필요 없을 듯 하다.

## 범위 ( BETWEEN )

`WHERE` 절에서 `BETWEEN` 연산자를 사용하면 **검색 하고자 하는 값의 범위를 지정할 수 있다.**
다음 문제를 보면 이해가 빠르다.

### 문제 - 2

> 가격이 10,000원 이상 20,000원 이하인 도서를 검색하세요.

```sql
SELECT * -- 모든 속성 검색
FROM Book -- BOOK 테이블에서
WHERE price BETWEEN 10000 AND 20000; -- 가격이 10000원, 20000원 사이인
```
위의 코드 또는 `AND` 연산자를 이용해 다음과 같이 작성이 가능하다.
```sql
SELECT * -- 모든 속성 검색
FROM Book -- BOOK 테이블에서
WHERE price >= 10000 AND price <= 20000; -- 가격이 10000원, 20000원 사이인
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/d30f5ef6-5669-4912-85f8-725b93e4689b/image.PNG)

## 집합 ( IN , NOT IN )

`WHERE` 절에 두 개 이상의 값을 비교하기 위해서 `IN` 또는 `NOT IN` 연산자를 사용하면
편하다. 간단하게 **컬럼 값이 일치한 것만 추출하여 검색해준다**.

### 문제 - 3

> **출판사가 '굿스포츠' 혹은 '대한미디어' 인 도서**를 검색하세요.


```sql
SELECT *
FROM Book
WHERE publisher IN ('굿스포츠','대한미디어');
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/d5ab94c7-aa2a-432c-a065-42eeaf91edf5/image.PNG)


또는, `NOT IN`을 이용하여 출판사가 '굿스포츠' , '대한미디어'가 아닌 도서를 검색할 수 있다.

```sql
SELECT *
FROM Book
WHERE publisher NOT IN ('굿스포츠','대한미디어');
```

## 문자열 비교 - LIKE

문자열의 패턴을 비교하거나 **부분적으로 일치하는 컬럼을 찾고자 할 때** 사용한다.
주의할 점은 ` ' ' ` 작은 따옴표로 찾고자 하는 컬럼 값을 감싸준다.
다음 문제를 통해 어떻게 사용하는지 확인해보자.

### 문제 - 4

> **"축구의 역사"를 출간한 출판사**를 검색하세요.

```sql
SELECT * -- 전체 속성 검색
FROM Book -- Book 테이블으로 부터
WHERE bookname LIKE '축구의 역사'; -- 축구의 역사'인 책 이름
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/28b8a79a-0ac3-4312-8026-09f1852a8729/image.PNG)


또한 도서의 이름에 '축구'가 포함된 도서를 찾고자 하면 와일드 문자인 `%` 를 사용한다.
`%`는 아무 문자열을 대신하는 기호이다.

### 문제 - 5

> **도서이름에 '축구'가 포함된 출판사**를 검색하세요.

```sql
SELECT publisher -- 출판사 속성 검색
FROM Book
WHERE bookname LIKE '%축구%';
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/b9c30d9f-6fa4-4bb2-b4db-e9a10172207a/image.PNG)


와일드 문자인 `_(밑줄 기호)` 는 특정 위치에 한 문자만을 대신할 때 사용한다.
예를 들면 다음 문제를 보자.

### 문제 - 6

> 도서이름의 **왼쪽 두 번째 위치**에 **'구'** 라는 문자열을 갖는 도서를 검색하세요.

```sql
SELECT *
FROM Book
WHERE bookname LIKE '_구%'; -- 두번 째 문자가 '구' 인 컬럼.
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/90728ba0-0cf7-47a2-89f1-ba60b80660a0/image.PNG)


## 복합조건 검색 🔑

`WHERE` 절에 논리 연산자들을 사옹하여 복합조건 명시가 가능하다.
다음 문제에 적절히 사용 가능하다.

### 문제 - 7
> **축구에 관한 도서 중 가격이 20,000원 이상인 도서**를 검색하세요.

```sql
SELECT *
FROM Book
WHERE bookname LIKE '%축구%' AND price >= 20000; 
-- 책 이름이 '축구'가 들어가 있고, 가격이 20000원 이상인 컬럼만 검색.
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/5e04ede6-94c9-47ea-98aa-26da7d927dc2/image.PNG)


계속해서 복합조건을 이용한 문제를 해결해보자.

### 문제 - 8

> **출판사**가 **'굿스포츠' 또는 '대한미디어'인 도서**를 검색하세요.

```sql
SELECT *
FROM Book
WHERE publisher = '굿스포츠' OR publisher = '대한미디어';
-- 또는 WHERE publisher LIKE '굿스포츠' OR publisher LIKE '대한미디어';

```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/15e02027-908a-4f53-a507-53d176fd59a3/image.PNG)

간단하다. 👍

## 검색 결과 정렬 📚

SQL 문의 검색 결과의 정렬 순서는 보통 데이터베이스 시스템에 저장된 위치에 따라
결정된다고 한다. 
만약 **컬럼 결과 값을 특정 순서대로 정렬**하고 싶으면 `ORDER BY` 절을 사용하면 된다.

### 문제 - 9

> **도서를 책 이름 순**으로 정렬하세요.

```sql
SELECT *
FROM Book
ORDER BY bookname; -- 책의 이름 순으로 정렬
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/3ca3f6e4-7cea-4419-86a6-35c75254e062/image.PNG)

정렬 결과가 영어가 먼저 나온 이유는 아마 유니코드값이 
영어가 한글보다 우선이기 때문이라고 생각된다.
다음 문제도 해결해보자.

<br>

### 문제 - 10

> **도서를 가격 순으로 검색**하고, **가격이 같을 경우 이름순**으로 검색하세요.

```sql
SELECT *
FROM Book
ORDER BY price, bookname; -- price 먼저 정렬 후, bookname 정렬.
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/9100b8ec-124a-42fc-aa69-a23733d05f46/image.PNG)

이처럼 price(가격) 이 먼저 정렬 된 후, bookname(책명) 순으로 정렬 된 것을
확인 할 수 있다.

하지만 위 문제들을 보면 항상 오름차순으로 정렬 된 것을 확인할 수 있는데,
**내림차순으로 정렬하려면 열 이름 다음에 `DESC` 키워드를 사용하면 된다.**
반대로 오름차순의 키워드는 **`ASC`** 이다.

이제 문제를 해결해보자.

<br>

### 문제 - 11

> **도서를 가격의 내림차순으로 검색**하세요. **가격이 같을 경우 출판사의 오름차순**으로 정렬한다.

```sql
SELECT *
FROM Book
ORDER BY price DESC , publisher ASC; 
-- 가격은 내림차순으로 , 값이 같으면 출판사의 오름차순으로 정렬
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/11d82f96-b137-40ba-bfae-5a9ccd05da21/image.PNG)

무조건 왼쪽에서부터 오른쪽순으로 실행된다.

## 집계 함수 ➕

지금까지는 마당서점의 고객이 필요로 하는 쿼리를 해결해왔다.
지금부터는 **시스템 관리자**가 필요로 하는 질의를 중심으로 SQL의 기능을 살펴본다.
여기서 **관리자는 도서 판매량의 총액, 일별 판매량 등 집계 정보가 필요하다.**
이를 집계하기 위해 **`GROUP BY`** 문 등을 사용한다.

집계 함수는 테이블의 각 열에 대해 계산을 하는 함수로,
**`SUM , AVG , MIN , MAX , COUNT`** 가 있다.

> TIP 💡 : _집계는 통계와 비슷한 의미지만, DB에서는 통계보단 집계라는 용어를 사용한다고 한다. _

<br>

이제 `ORDERS` 테이블에 저장된 모든 주문의 총 판매액을 구하는 SQL 문을 해결하자.

### 문제 - 12
> 고객이 **주문한 도서의 총판매액**을 구하세요.

```sql
SELECT SUM(saleprice) -- saleprice 속성의 총합
FROM Orders;
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/ce58a0e3-d3a4-4281-b8b0-c75962c5d03e/image.PNG)

여기서 확인할 수 있는 점은, 위 쿼리 결과는 기존의 속성이 아닌
저장된 데이터를 가공하여 계산 후 새로 생성된 임시 행 이기 때문에
**`SUM(saleprice)`** 와 같이 나온다. 여기서 별칭을 지어주거나 가독성을 높여주려면 
**`AS`** 키워드를 사용하여 다시 작성하면 다음과 같다.

```sql
SELECT SUM(saleprice) AS 판매총액
FROM Orders;
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/6612d3a3-79f6-4ecf-bcc0-77dc4296e6c4/image.PNG)

<br>

집계 함수는 `WHERE` 문과 같이 사용하면 더 유용하다.
다음 쿼리를 통해 얼마나 유용한 지 확인해보자.

### 문제 - 13

> **고객번호가 '2번'인 고객**이 **주문한 도서의 총판매액**을 구하세요.

```sql
SELECT SUM(saleprice) AS 총판매액 -- saleprice 총합 구하기.
FROM Orders -- Orders 테이블에서 검색
WHERE custid = 2; -- 고객번호가 2번인 컬럼.
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/df596ca0-a441-437c-b901-9e59cfb00dc4/image.PNG)

또한 집계함수는 여러 개를 혼합해 사용 가능하다.

<br>

### 문제 - 14

> 고객이 **주문한 도서의 총판매액 , 평균값, 최저가 , 최고가**를 구하세요.

```sql
SELECT	SUM(saleprice) AS 총판매액, -- saleprice 의 총합
		AVG(saleprice) AS 평균가, -- saleprice 의 평균값
		MIN(saleprice) AS 최저가, -- saleprice 의 최소값
		MAX(saleprice) AS 최고가 -- saleprice 의 최대값
FROM Orders; -- Orders 테이블에서 검색
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/b79096aa-90a9-4f26-83d5-b3e6d0e4df48/image.PNG)

다음은 집계 함수중 하나인 **`COUNT`** 를 알아보자. `COUNT` 는 행의 개수를 센다. 
`COUNT()`의 괄호 안에는 * 또는 특정 속성의 이름이 사용되며, 
해당 속성의 컬럼 개수를 세어 준다. ( NULL 값 제외 )

마지막 문제다.

<br>

### 문제 - 15

> 마당서점의 도서 판매 건수를 검색하세요.

```sql
SELECT count(*) AS 주문건수 -- Orders 테이블의 컬럼 총 개수
FROM Orders; -- Orders(주문) 테이블
```

## 마치며

이번 게시글에서는 데이터 조작어의 조건 검색을 알아봤다.
다음 검색 조건에서는ㅁ `GROUP BY` , `HAVING` , `JOIN` 등 어려운 문법이 나오는데.. 
일단  이 게시물에 나오는 조건 검색들 먼저 연습을 차근차근 해봐야겠다. 😤

##### 참고 자료
> <a href="https://namu.wiki/w/SQL/%EB%AC%B8%EB%B2%95">SQL / 문법 - namu.wiki</a>
> <a href="https://www.hanbit.co.kr/store/books/look.php?p_code=B8231888617">오라클로 배우는 DB 개론과 실습 - 박우창</a>
