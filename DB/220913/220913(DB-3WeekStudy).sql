SELECT customer.custid , book.bookname
FROM Customer, Book, Orders
WHERE Customer.custid = Orders.custid AND Orders.bookid = Book.bookid AND
      Book.price >= 20000;

/* 도서를 구매하지 않은 고객을 포함하여 고객의 이름, 주문 도서, 도서의 가격을 검색하세요. */      
SELECT Customer.name, saleprice
FROM Customer LEFT JOIN Orders
                ON Customer.custid = Orders.custid;

/* 가장 비싼 도서의 이름을 검색하세요. */
SELECT bookname,price
FROM book
WHERE price=(SELECT MAX(price)
            FROM Book);

/* 도서를 구매한 적이 있는 고객의 이름을 검색하세요. */
 SELECT name -- "name" 필드 보기
 FROM customer -- "customer" 테이블에서
 WHERE custid IN (SELECT custid -- Orders 테이블에 있는 custid 검색.
                -- FROM orders);

/* "대한미디어"의 도서를 구매한 고객의 이름을 출력하세요. */
SELECT name
FROM   Customer
WHERE  custid IN (SELECT custid
                  FROM   Orders
                  WHERE  bookid IN (SELECT bookid
                                    FROM   Book
                                    WHERE  publisher='대한미디어'));

/* "대한미디어"의 도서를 구매하지 않은 고객의 이름을 출력하세요. */
SELECT name -- 고객의 이름 검색.
FROM   Customer -- 고객 테이블 참조.
WHERE  custid IN (SELECT custid -- custid 필드를 Orders 테이블에 있는 custid 필드 참조.
                  FROM   Orders
                  WHERE  bookid  IN (SELECT bookid -- Orders 테이블에 있는 bookid 필드 참조.
                                    FROM   Book -- Book 테이블에서 참조.
                                    WHERE  publisher != '대한미디어'));
                                                     -- '대한미디어'가 아닌 도서를 참조함.
                                    
/*  상관 부속질의(Sub Query)를 이용하여 
    출판사별로 출판사의 평균 도서 가격보다 비싼 도서를 구하시오. */
    
SELECT  b1.bookname -- Book 테이블을 참조한 b1.bookname 검색.
FROM    Book b1 -- Book 테이블을 참조한 b1 테이블 생성.
WHERE   b1.price > (SELECT avg(b2.price)    -- b1.price가 b2.price(평균값)보다 큰 값만 검색
                    FROM    Book b2 -- Book 테이블을 참조한 b2 테이블 생성.
                    WHERE b2.publisher = b1.publisher); -- b2와 b1의 'publisher' 필드 참조.
