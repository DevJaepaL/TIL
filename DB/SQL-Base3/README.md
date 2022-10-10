# 데이터 조건 검색 2
___

## 개요

데이터 조작어중 `GROUP BY` , `HAVING` , `JOIN` 절을 알아 봅시다 ! 🕵🏻‍♂️

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
SELECT custid, count(*) AS 도서수량 , SUM(saleprice)
FROM Orders
GROUP BY custid; -- custid 가 같은 컬럼값 그룹화
```

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


