# 220914 DB(SQL) 과제 📜

 ## 개요 💡
> #### SQL 문법을 활용하여 DB를 다뤄본다. 

<br>

## DB 문제
> 마당 DB에서, **도서를 구매한 고객** 중 **'대한미디어'의 책을 구매하지 않은 고객** 의 이름을 출력 하세요.

## 문제 해결 🔑

![](https://velog.velcdn.com/images/jaepal/post/9379929e-3d53-4c0e-a0fd-ee9cf1d173dc/image.PNG)

```sql
SELECT  name
FROM    Customer
WHERE   custid  IN( SELECT  custid
                    FROM    orders);    
```

일단 문제의 첫번째 조건인 **도서를 구매한 고객** 을 먼저 검색한다.
`WHERE` 절을 활용해 `ORDERS` 테이블에 접근하여 `custid`를 집어내서 이름을 검색했다.


<br>

![](https://velog.velcdn.com/images/jaepal/post/1ac974e1-3853-4c77-b727-f5c102c41f24/image.PNG)

```sql
SELECT  name
FROM    Customer
WHERE   custid NOT IN(SELECT   custid
                      FROM     orders
                      WHERE    bookid   IN(SELECT   bookid
                                           FROM     book
                                           WHERE    publisher = '대한미디어'));
```

문제의 두번째 조건인 **'대한미디어'의 책을 구매하지 않은 고객** 의 이름을 검색 하였다.
이 문제 또한 `WHERE` 절을 두번 사용하여 `마당 데이터베이스`의 테이블 총 3개를 겹쳐 결과를 검색해주었다.

<br>

## 최종 결과 🧱

![](https://velog.velcdn.com/images/jaepal/post/d3f46a1e-9dc1-4768-8bbf-4eb05233bc1e/image.PNG)

```sql
SELECT  name
FROM    Customer
WHERE   custid IN (SELECT custid
                   FROM Orders) AND custid NOT IN(SELECT   custid
                                                  FROM     orders
                                                  WHERE    bookid     IN(SELECT   bookid
                                                                         FROM     book
                                                                         WHERE    publisher = '대한미디어'));
```

문제의 답은 생각보다 간단하였다. 

**첫번째 조건**과 **두번째 조건**을 `AND 연산자`를 이용하여 두개의 조건이 만족되는 값을 검색하는 문제였다.

<br>

## 문제를 해결하며 느낀 점 🤔

문제의 내용만 봤을 땐 문제의 조건들이 상당히 까다로웠다고 생각했지만, 검색의 조건이 2개 뿐이라 문제를 하나씩 해결하고 AND 연산자를 이용하여 검색 조건이 두개 다 만족하면 검색하면 되는 문제라 생각보다 쉽게 해결 할 수 있었다. 