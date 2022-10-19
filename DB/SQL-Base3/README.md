<<<<<<< Updated upstream
# 데이터 조건 검색 2
=======
# SQL (4) - 데이터 조건 검색 2
>>>>>>> Stashed changes
___

## 개요

<<<<<<< Updated upstream
데이터 조작어중 `GROUP BY` , `HAVING` , `JOIN` , `부속질의(Sub Query) 를 알아 봅시다.

또한 예제 문제들을 풀어봤어요 ! 🕵🏻‍♂️
=======
데이터 조작어중 `GROUP BY` , `HAVING` , `JOIN` 절을 알아 봅시다 !
>>>>>>> Stashed changes

<br>

## `GROUP BY`

SQL 문에서 `GROUP BY` 절을 사용 하면 같은 속성 값 끼리 그룹을 만들 수 있다.
간단한 예시로 `Orders` 테이블을 사용하는 `SELECT` 문에 `GROUP BY custid` 라고 
명시할 경우 `DBMS` 는 `custid` 가 같은 값끼리 그룹화 한다.

다음 예를 봐보자.

<br>

### 문제 - 1

> 고객별로 주문한 도서의 총수량과 총판매액을 구하세요.

```sql
<<<<<<< Updated upstream
SELECT custid, count(*) AS 도서수량 , SUM(saleprice)
=======
SELECT custid, count(*) AS 도서수량 , SUM(saleprice) 
>>>>>>> Stashed changes
FROM Orders
GROUP BY custid; -- custid 가 같은 컬럼값 그룹화
```

<<<<<<< Updated upstream
<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/d62fc9ad-52b4-46ad-8030-6759825762a9/image.PNG)

결과 값이 축소된걸 확인할 수 있다.
 **도서수량** 과 **SUM(saleprice)** 등 집계함수가 `custid` 속성의 같은 컬럼 값 끼리 합쳐져
 집계함수 결과가 나온것을 확인할 수 있다.
 
 ## `HAVING`
 
 `HAVING` 절은, `GROUP BY` 절의 결과 값에 조건을 걸어서 그룹화 시켜주는 역할이다.
 여기서 `WHERE` 절과 헷갈릴 수 있는데, `HAVING` 절은 `GROUP BY` 와 함께 써야만 사용이 가능하다.

예를 들어 **주문 도서가 2권 이상일 경우만 값을 나타내야할 경우** 다음과 같이 사용한다.
 

### 문제 - 2

> **가격이 8,000 원 이상인 도서를 구매한 고객**에 대하여 **고객별 주문 도서의 총 수량**을 구하세요.
> 단 , **책을 2권 이상 고객만 구하세요.**
 
```sql
SELECT custid AS 고객번호 , COUNT(*) AS 주문수량
FROM Orders
WHERE saleprice >= 8000 -- 주문가격 8,000원 이상인 값일 경우.
GROUP BY custid -- custid 중복 컬럼 합치기
HAVING count(*) >= 2; -- custid 의 값이 두개 이상일 경우.
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/5319b41b-a3c9-4496-ab88-1cf43bad1bea/image.PNG)

여기서 알 수 있는 점은 `HAVING` 절은 무조건 GROUP BY 와 사용해야한다.
또한 `WHERE` 절과 `HAVING` 절 두개를 이용해 조건을 여러개 적용 할 수 있는 점이다.

<br>

## `GROUP BY` , `HAVING` 문법 및 주의사항 ⚠️

#### `GROUP BY`
주의사항 : `GROUP BY` 로 튜플(속성) 을 그룹으로 묶은 후,
**`SELECT`** 절에는 **`GROUP BY` 에서 사용한 `<속성>` 및 `집계함수` 만 결과가 나온다.**


+ 맞는 문법 ✔️
```sql
SELECT	 	custid, SUM(saleprice) -- 그룹화 된 컬럼의 속성 검색하여 결과 확인.
FROM	 	Orders
GROUP BY	cutsid; -- custid의 컬럼값을 그룹화한다.
```

<br>

+ 틀린 문법 ✖️
```sql
SELECT		bookid , SUM(saleprice) -- SELECT 절에는 bookid가 들어갈 수 없다.
FROM 		orders
GROUP BY	custid;
```

#### `HAVING`

주의사항 : `WHERE` 절과 `HAVING` 절이 포함된 쿼리는 검색조건이 이상해질 수 있기 때문에,
 1. `HAVING` 절은 반드시 **`GROUP BY` 절과 같이 사용되야 한다. **
 2. `WHERE` 절 보다 ** 뒤에 나와야 한다. **
 3. `<검색 조건>` 에는 **`SUM , AVG , MAX , MIN , COUNT**` 와 같은 집계 함수가 나와야 한다.
 
 + 맞는 문법 ✔️
```sql
SELECT		custid, COUNT (*) AS 도서 수량
FROM		Orders
WHERE		salepirce >= 8000
GROUP BY 	custid
HAVING		count(*) >= 2;
 ```

+ 틀린 문법 ✖️
```sql
SELECT		custid, COUNT (*) AS 도서 수량
FROM		Orders
HAVING		count(*) >= 2 -- 순서가 잘못 됐다..
WHERE		salepirce >= 8000
GROUP BY 	custid;
```

 ## 두 개 이상의 테이블에서 `쿼리` 질의
 
** `박지성` 고객이 주문한 도서의 총 구매액을 구하려고 한다.**
 총 구매액을 알기 위해선 `saleprice` 의 값을 알면 되지만 큰 문제가 있다.
 
 **`박지성` 고객의 이름은 `Customer` 테이블**에 존재하고, **`saleprice` 는 `Orders`** 테이블에 존재한다.
 
 이럴 경우엔 총구매액을 어떻게 구해야 하는걸까 ?
 
 지금까지 사용한 SQL 질의는 테이블 하나를 이용한 질의였다.
 이제 사용할 쿼리 질의는 두 개 이상의 테이블을 이용하는 질의를 알아보려고 한다 !
 
 SQL 에서는 여러 개의 **테이블을 이용하는 방법은 총 2 개다.**
 
 + 조인 ( Join )
 + 부속 질의 ( Sub Query )
 
두 가지 모두 여러 개의 테이블을 이용하는 문법이지만 방법은 약간 다르다.


## `JOIN`

두 개의 테이블을 보려고 한다. `JOIN` 은 한 테이블의 행을 다른 테이블의 행에
연결하여 두 개 이상의 테이블을 결합하는 연산이다.

다음은 `Customer` 테이블을 `Orders` 테이블과 조건 없이 연결한 예 이다.
`SQL` 문은 다음과 같이 작성한다.

```sql
SELECT  *
FROM	Customer, Orders;
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/fd8a873e-2c99-4f3c-9751-b5675661fcbe/image.PNG)

`Customer` 와 `Orders` 테이블의 합체 결과 , 투플(행)의 개수는 고객이 `5`명 이고
주문이 `10` 건 이므로 **``5 * 10 = 50``** 이 된다.

`Customer` 테이블의 1번 고객인 박지성은 `Orders` 테이블의 10 개의 행 모두 결합하고,
나머지 고객들도 각각 `Orders` 테이블의 `10` 개 행 모두에 대해 결합한다.
**이처럼 조건이 없는 테이블 간의 조인을 카티션 프로덕트라고 한다. ( 곱집합 )**

그렇지만 , 결과는 논리에 맞지 않는다.
필요한 것은 박지성의 `custid` 가 1 이므로, `Orders` 테이블에서 `custid` 가 1인 것만 선택하면 된다.
이것은 `WHERE` 절을 이용하여 두 테이블의 연결 조건을 추가하면 되지 않을까 ?

다음 문제를 해결해보자.

### 문제 - 3

> 고객과 고객의 주문에 관한 데이터를 모두 보이세요.
> 또한 고객 이름 별로 정렬하여 보이세요.

```sql
SELECT			*
FROM			Customer , Orders
WHERE			Customer.custid = Orders.custid -- 두 개의 속성 custid 가 같은 값만 검색하기.
ORDER BY		Customer.custid; -- 고객번호 별로 정렬
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/78aab6f0-46ef-409a-999d-83111750fd18/image.PNG)


이처럼 여러 개의 테이블을 연결하여 하나의 테이블을 만드는 과정을 **조인** 이라고 한다.

또한 위의 질의 처럼 동등조건에 의하여 테이블을 조인하는 것을 **동등 조인** 이라고 한다.
조인이라고 하면 대부분의 경우를 **동등 조인** 이라고 칭한다.

**조인** 을 사용하면 두 개 이상의 테이블에서 일부 데이터만 얻을 수 있다.
예를 들어 고객의 이름과 고객이 주문한 도서의 가격을 알고 싶다하면 다음과 같이 쿼리문을 작성한다.

### 문제 - 4

> 고객의 이름과 **고객이 주문한 도서**의 **판매가격을 검색**하세요.
> 또한 결과 값을 **이름 순으로 정렬하세요.**

```sql
SELECT 		name , saleprice
FROM 		Customer , Orders
WHERE 		Customer.custid = Orders.custid 
-- 두 개의 테이블 속성인 custid가 같은 값일 경우만 검색
ORDER BY 	name;
```

<br>

**결과**


![](https://velog.velcdn.com/images/jaepal/post/1569d8af-c5da-4857-bdd1-98632ab072dd/image.PNG)


모든 쿼리의 결과는 단일 테이블이다. 따라서 위 결과 테이블에 `SQL` 문법을 적용할 수 있다.
`GROUP BY`를 추가해보자.

<br>

### 문제 - 5

> 고객별로 **주문한 모든 도서의 총판매액**을 구하고 , **고객별로 정렬**하세요.

<br>

```sql
SELECT		Customer.name AS "이름" , SUM(saleprice) AS "총 구매 금액"
FROM 		Customer,Orders
WHERE		Customer.custid = Orders.custid
GROUP BY 	Customer.name
ORDER BY	Customer.name;
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/01324569-f49f-4fd1-8403-e5d625c38a7b/image.PNG)


위에 쿼리는 두 개의 테이블을 조인했다.
하지만 세 개 이상의 테이블 또한 조인이 가능하다.

예를 들어 각 **고객의 이름과 고객이 주문한 도서의 이름**을 구하고 싶다고 하자.
결과를 얻으려면 세 개의 테이블을 한 꺼번에 조인 해야한다.

1. 고객의 이름은 `Customer` 테이블에 있다.
2. 고객의 주문내역은 `Orders` 테이블에 있다.
3. 도서의 이름은 `Book` 테이블에 있다.

`Customer` 테이블과 `Orders` 테이블은 `Customer` 기본키인 `custid` 와
`Orders` 의 외래키인 `custid` 로 연결된다.
또한 `Orders` 테이블과 `Book` 테이블은 `Orders` 의 외래키인 `bookid` 와
`Book` 테이블의 기본키인 `bookid` 로 연결된다.

말로는 어렵기 때문에 아래 이미지로 **키(Key) 끼리 연결된 모습**을 확인할 수 있다.

![](https://velog.velcdn.com/images/jaepal/post/60ed658e-9b02-4b02-8575-b76de220440e/image.PNG)

위와 같은 그림의 문제를 SQL 문으로 작성하면 다음과 같다.

<br>

### 문제 - 6

> 고객의 이름과 고객이 주문한 도서의 이름을 구하세요.

```sql
SELECT Customer.name AS 성명 , book.bookname AS 책명
FROM Customer,Book,Orders
WHERE Book.bookid = Orders.bookid AND Customer.custid = Orders.custid;
```

<br>

**결과**

![](https://velog.velcdn.com/images/jaepal/post/239cac05-ec69-456f-8517-f8b77f9b50b0/image.PNG)


<br>

또한 세 개 이상의 테이블을 조인하는 쿼리에 `WHERE` 절을 추가하여 원하는 결과를
추출할 수 있다. 예를 들어 가격이 20,000원 이상인 도서를 주문한 고객의 이름과 도서의 이름을 구하려면 다음과 같이 작성한다.

<br>

### 문제 - 7

> 가격이 20,000원 이상인 도서를 주문한 고객의 이름과 도서의 이름을 구하세요.

<br>

```sql
SELECT	Customer.name , Book.bookname
FROM	Book , Customer , Orders
WHERE	Book.bookid = Orders.bookid AND
		Customer.custid = Orders.custid AND
		Orders.saleprice >= 20000;
```

![](https://velog.velcdn.com/images/jaepal/post/1b6ecb28-bf28-40be-b17d-63d4955311a4/image.png)

`WHERE` 절은 참으로 유용하다 이말이다. 😉


## 외부 `JOIN`

조인 연산의 특별한 경우로 `외부 조인`이 있다. 

**문제 - 4 번** 인,
> 고객의 이름과 **고객이 주문한 도서**의 **판매가격을 검색**하세요.
> 또한 결과 값을 **이름 순으로 정렬하세요.**

이와 같은 동등조인의 예에서 도서를 주문하지 않은 고객인 '박세리'는 결과에 포함되지 않는다.
만약 도서를 구매하지 않은 고객 '박세리'를 포함하여 고객의 이름과 고객이 주문한 도서의
가격을 구하기 위해선 어떻게 해야할까 ? 답은 **`외부 JOIN`** 을 사용하면 된다.

<br>

### 문제 - 8

> **도서를 구매하지 않은 고객을 포함**하여 **고객의 이름과 고객이 주문한 도서의 판매가격**을 구하시오.

<br>

```sql
SELECT	customer.name , orders.saleprice
FROM	Customer LEFT OUTER JOIN Orders
		ON Customer.custid = Orders.custid;
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/d9c76d8b-ca73-4c04-8f60-892582b3c18a/image.PNG)



`LEFT OUTER JOIN(외부 조인)` 의 경우 이처럼 여러 왼쪽 테이블에 대하여
모든 결과를 가져온 후,** 오른쪽 테이블의 데이터와 매칭한 후 매칭되는 데이터가 없을 경우
`NULL` 값을 표시** 한다.

반대인 `RIGHT OUTER JOIN`의 경우는 오른쪽 테이블의 데이터를 모두 가져오겠죠 ?

<br>

## 부속질의 `(Sub Query)`

`쿼리` 문 내에 또다른 `쿼리` 문을 작성해보자. 
**" 가격이 가장 비싼 도서의 이름은 얼마인가 ? "** 라는 질문에 대한 답을 구한다고 생각해보자. 

```sql
SELECT	MAX(price) -- 35,000원 이다.
FROM	Book;
```
만약 **가장 비싼 도서의 가격을 알고 있을 경우 가격이 35,000원인 도서의 이름**을 검색하면 된다.

```sql
SELECT	price
FROM	Book
WHERE	price = 35000;
```

위의 두개의 질의를 하나로 작성이 가능할까?
**두번 째 질의의 35,000원 값 위치에 첫 번째 질의**를 적절히 대치하면 된다. 

<br>

### 문제 - 9

> **가장 비싼 도서의 이름**을 구하세요.

```sql
SELECT	price AS '최고 가격'
FROM 	Book
WHERE	price = (SELECT MAX(price)
				 FROM Book);
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/f689c68c-ad9f-41fb-852c-1e179a352139/image.PNG)


위의 결과와 같이 `SELECT` 문의 `WHERE` 절에 또 다른 테이블 결과를 이용하기 위해
다시 `SELECT` 문을 괄호로 묶는 것을 **`부속 질의 (Sub Query)`** 라고 한다.

부속질의를 이용한 문제를 계속 해결 해보자.

<br>

### 문제 - 10

> **도서를 구매한 적이 있는 고객의 이름**을 검색하세요.

```sql
SELECT	name -- 고객의 이름 검색
FROM	Customer
WHERE	custid IN (SELECT	custid
				   FROM		Orders);
```

<br>

** 결과 **


![](https://velog.velcdn.com/images/jaepal/post/c300e656-fbe0-4dc6-8711-ef5eab56c14c/image.png)

이처럼 두 개의 테이블을 `부속 질의`를 통해 접근이 가능하다.

또한 세 개 이상 중첩한 `부속 질의`도 가능하다.

<br>

### 문제 - 11

> **'대한미디어' 에서 출판한 도서를 구매한 고객의 이름**을 검색하세요.

<br>

```sql
SELECT	name AS '고객 명'
FROM	Customer
WHERE	custid	IN (SELECT	custid
					FROM	Orders
					WHERE	bookid IN ( SELECT	bookid
										FROM	Book
										WHERE	publisher LIKE '대한미디어'));
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/1f93b609-8f3c-41d6-9562-0328e8276bf8/image.png)


간단하다. 
사실 이 문제는 여러 개의 테이블의 `Key(키)` 들을 계속 연결해주고 값을 찾아주면 되는 문제다.

> `Customer.custid` -> `Orders.custid` -> `Orders.bookid` ->
> `Book.bookid` -> `Book.publisher = '대한미디어'`

이런 절차를 이용하여 문제를 해결했다.


## 상관 부속질의

`부속질의` 에는 상하 관계가 있는데, 보통의 실행 순서는 `하위 부속질의`를 실행한 후
그 결과를 통해 `상위 부속질의` 를 실행한다.

반면, `상관 부속질의` 의 경우 `상위 부속 질의`의 투플을 사용하여 `하위 부속질의`를 계산한다.
이처럼 `상위 , 하위 부속질의`는 서로 독립적이지 않고 관련을 맺고 있다.

그러나 이를 사용하기 위해선 **별칭(Alias)** 를 알아야 한다.


### 별칭(Alias) 📛

별칭에 대해 간단히 설명하자면,

> 하나의 `SELECT`에 여러 개의 테이블이 사용되었을 때 혼선을 줄여주고 긴 테이블명이나 컬럼명을 짧게 만들어 SQL 작성에 효율성을 높여준다.

이런 느낌이다.
별칭에 대한 설명은 나중에 다뤄보자.

상관 부속질의를 이용해 다음 문제를 해결해보려고 한다.

### 문제 - 12

> **출판사별로 출판사의 평균 도서 가격보다 비싼 도서**를 구하세요.

```sql
SELECT	bookT1.bookname --
FROM	Book bookT1
WHERE	bookT1.price > (SELECT	avg(bookT2.price)
						FROM	Book bookT2
						WHERE	bookT2.publisher = bookT1.publisher);
```

<br>

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/f7b24278-d9c6-4ea3-822e-bfec9cbccd04/image.png)


평균을 구한 후, 평균보다 비싼 도서를 구해야 하므로
상위 부속질의에서 도서와 출판사가 주어지면 **하위 부속질의에서는 출판사의 평균가를 구한다.**
**상위 부속질의는 이 도서가 평균보다 비싼 지 비교**해주는 기능이라 보면 된다.

<br>

## 집합 연산

`SQL` 문의 결과는 테이블로 나타난다. 테이블의 경우 튜플의 집합이므로 테이블 간의
집합연산을 이용하여 **합집합, 차집합, 교집합** 을 구할 수 있다.

+ 합집합 = `UNION`
+ 차집합 = `EXCEPT` or `MINUS`
+ 교집합 = `INTERSECT`

이런 식으로 나타낸다.

다음 예로 차집합을 이용할 수 있다.

> `{도서를 주문하지 않은 고객} = {모든 고객} - {도서를 주문한 고객}`

이를 `SQL` 문을 이용한다면 ?

```sql
SELECT	name
FROM	Customer;
```
모든 고객을 먼저 찾는다.
```sql
SELECT	name
FROM	Customer
WHERE	custid IN (SELECT	custid
				   FROM		Orders);
```
주문한 고객을 찾는다.

이를 차집합 연산을 이용하여 다음 문제를 해결해보자.

### 문제 - 13

> **도서를 주문하지 않은 고객**의 이름을 찾으세요. 

```sql
SELECT name AS 성명
FROM Customer
EXCEPT
SELECT name
FROM Customer
WHERE custid IN (SELECT custid FROM Orders);
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/e0709213-a569-4cb7-9468-50d9ccdb3a59/image.png)

<br>

## `EXIST`

`EXIST`는 상관 부속질의문 형식이다.
즉 `EXIST` 안의 조건이 만족해야만 결과를 나타낸다.

반대인 의미로 `NOT EXIST` 도 존재한다.
`NOT EXIST` 안의 조건이 만족한 것을 제외한 모든 결과를 나타낸다.

문제를 해결해보자.
<br>

### 문제 - 14

> 주문내역이 있는 고객의 이름과 주소를 찾으세요.

```sql
SELECT	ct.name ,	ct.address
FROM	Customer	ct
WHERE	EXISTS		(SELECT *
					 FROM	Orders od
					 WHERE	ct.custid = od.custid); 
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/402ce64d-6782-49ba-99fd-8830497f8916/image.png)

위의 쿼리문을 순서대로 입력하자면
 1. `ct`의 첫 행을 가져와 부속질의문에 `ct` 값으로 입력한다.
 2. 부속질의문 `od`의 어떤 행에서 **`ct`의 고객번호와 같은 것을 찾는다.**
 3. `True` 일 경우 `cs`의 **첫 행의 대한 `name` , `address` 가 반환된다.**
 
 이를 계속 반복하여 결과값을 나타낸다.
 
 
## 데이터 검색어 연습 ✍️

데이터 검색어의 문법을 대부분 알아봤으니 문제 해결 연습을 해보려고 한다.
다음 문제들을 해결해보자.

### 온라인 서점의 고객이 요구하는 질문을 `SQL` 문으로 작성.

> 1. '박지성' 고객이 구매한 **도서의 출판사 수**

** 코드 **

```sql
SELECT	COUNT(publisher) AS '출판사 개수'
FROM	Book
WHERE	bookid IN ( SELECT	bookid
					FROM	Orders
					WHERE	custid IN ( SELECT  custid
										FROM	Customer
										WHERE	name LIKE '박지성'));
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/0314bf71-6e6b-4416-a482-228a3f85b9d9/image.png)

해결하는 방법은 , 테이블을 3개 연결 한 후 `Customer` 테이블에서 `박지성` 고객을 찾은 후 `publisher` 를 전부 `COUNT` 해줬다.

<br>

> 2. '박지성' 고객이 구매한 **도서의 이름, 가격, 정가와 판매가격의 차이**

```sql

**코드**

```sql
SELECT	Book.bookname AS 책명 , od.saleprice AS 가격, Book.price - od.saleprice AS 차액
FROM 	Book, Orders od
WHERE 	Book.bookid = od.bookid AND od.custid IN ( SELECT 	custid
												   FROM		Customer
												   WHERE	name = '박지성');
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/4a802495-5236-4781-8720-b0246b443041/image.png)

이 역시 테이블 3개 `JOIN` 후, `박지성` 고객을 검색한 뒤에
`price`와 `saleprice` 의 차액을 계산하면 된다.

<br>

> 3. '박지성' 고객이 **구매하지 않은 도서의 이름**

**코드**

```sql
SELECT	bookname
FROM	Book
EXCEPT
SELECT	bookname
FROM	Book
WHERE	bookid  IN ( SELECT	bookid
					 FROM	Orders
					 WHERE	custid	IN ( SELECT	custid
										 FROM	Customer
										 WHERE	name LIKE '박지성'));
```

또는

```sql
SELECT	bookname
FROM	Book
EXCEPT
SELECT	b.bookname
FROM	Book b , Customer ct, Orders od
WHERE	b.bookid = od.bookid 	AND
		od.custid = ct.custid	AND
		ct.name LIKE '박지성';
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/b0f3110a-5bc9-4be8-9485-40d18d11f4bc/image.png)


첫 번째 방법은 차집합을 이용한 `부속 질의` 사용 , 
두번 째 방법은 `동등 조인` 을 이용한 방법이다.

<br>


### 온라인 서점의 관리자가 요구하는 질문을 `SQL` 문으로 작성.

> 1. 주문 금액의 **총액**과 **평균 금액**

** 코드 **

```sql
SELECT	SUM(saleprice) AS 총액 , AVG(saleprice) AS 평균금액
FROM	Orders;
```

** 결과 **
![](https://velog.velcdn.com/images/jaepal/post/49600c60-fae9-405b-8ecb-8ac38aba7b3f/image.png)

집계 함수를 사용하면 된다.
특정 조건 없이 주문금액의 총액 , 평균이 필요했기 때문에 `GROUP BY` 절은 사용 안했다.

<br>

> 2. 고객의 **이름**과 고객별 **구매액**

** 코드 **

```sql
SELECT		ct.name AS '고객명' , SUM(saleprice) AS '총 구매액'
FROM		Customer ct , Orders od
WHERE		ct.custid = od.custid
GROUP BY 	ct.name;
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/680dd333-e039-4367-99fe-bb68bff51280/image.png)

두 개의 테이블을 `JOIN` 하고 `GROUP BY` 로 `ct.name`들을 묶어준 뒤
집계함수를 이용하여 **각 고객의 총 구매액을 구했다.**

<br>

> 3. 고객의 **이름**과 고객이 구매한 **도서 목록**

** 코드 **

```sql
SELECT		ct.name AS 성명 , b.bookname AS 구매도서
FROM		Customer ct , Book b , Orders o
WHERE		b.bookid  = o.bookid AND
			ct.custid = o.custid
ORDER BY	ct.name;
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/86eb9863-a555-4c03-8435-7d3271720ec4/image.png)

가독성을 위해 임시로 `ORDER BY` 를 사용해봤다.

<br>

> 4. **도서의 가격**과 **판매가격**의 **차이가 가장 많은 주문**

**코드**

```sql
SELECT	*
FROM	Book b , Orders o
WHERE	b.bookid = o.bookid AND 
		b.price - o.saleprice = ( SELECT	MAX(price-saleprice)
								  FROM		Book b1 ,Orders o1
								  WHERE		B1.bookid = o1.bookid);
```


** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/1a555efd-ce1c-4b74-a4ac-64255496a6da/image.png)


이번건 좀 어려웠다.
일단 별칭을 이용하여 `상관 부속질의` 를 사용했다. 
`{주문 도서  목록중 차액이 가장 큰 금액} == {price - salerice의 차이가 가장 큰 금액}`

이런 식으로 생각하여 작성했다. 너무 어려웠다. 😫

> 5. **도서**의 **판매 평균 금액**보다 **고객의 평균 구매 금액이 더 높은 고객**의 이름

** 코드 **

```sql
SELECT 		c.name AS 고객명
FROM		Book b, Customer c, Orders o
WHERE		b.bookid = o.bookid AND o.custid = c.custid
GROUP BY	c.name
HAVING		avg(o.saleprice) > (SELECT	avg(saleprice)
								FROM	Orders);
```

** 결과 **

![](https://velog.velcdn.com/images/jaepal/post/54bbd5c6-eff2-43fa-b9d2-684079ff6e95/image.png)

이 문제는 위 문제보단 비교적 쉽다. 
먼저 3개의 테이블을 `JOIN` 한 후, `GROUP BY` 로 `name`을 묶어준뒤 `HAVING` 절을 이용하여 **고객의 구매금액이 기존 도서 구매금액의 총 평균 금액보다 클 경우에만 `GROUP BY` 의 실행조건을 걸어준다.**

<br>


## 마치며,

3개 게시글 분량의 데이터 조작어의 문법들을 정리해봤다.

이번 게시글에서 상당히 많은 분량을 차지했는데 이번 게시글의 내용은 그만큼 많이 중요한
내용들이였다. 

문제들을 풀어보며 느낀점은 해결 문제들을 계속 풀면서  SQL 문에 익숙해져야 한다고 생각한다. 코드 짜는 것도 중요하지만  문제의 해독을 얼마나 잘하냐의 관건인듯하다.

끝 . 😎


<br>
<Br>

##### 참고 자료
> <a href="https://araikuma.tistory.com/509">arikuma Blog - tistory</a>
> <a href="https://www.hanbit.co.kr/store/books/look.php?p_code=B8231888617">오라클로 배우는 DB 개론과 실습 - 박우창</a>
=======

>>>>>>> Stashed changes
