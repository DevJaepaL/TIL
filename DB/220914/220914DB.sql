/* "도서를 구매한 사람들" 중 대한미디어 책을 "구매하지 않은 사람"을 구해라. */
/* 결과에는 "김연아,장미란,추신수"만 나와야 한다. */


-- 먼저, "도서를 구매한 사람들"의 이름을 검색한다.
SELECT  name
FROM    Customer
WHERE   custid  IN( SELECT  custid
                    FROM    orders);    

-- 그 후, 대한미디어의 책을 "구매하지 않은 고객의 이름을 검색한다.
SELECT  name
FROM    Customer
WHERE   custid NOT IN(SELECT   custid
                      FROM     orders
                      WHERE    bookid   IN(SELECT   bookid
                                           FROM     book
                                           WHERE    publisher = '대한미디어'));

-- WHERE 절에 AND 연산자를 이용해 두개의 조건을 합친다.
SELECT  name
FROM    Customer
WHERE   custid IN (SELECT custid
                   FROM Orders) AND custid NOT IN(SELECT   custid
                                                  FROM     orders
                                                  WHERE    bookid     IN(SELECT   bookid
                                                                         FROM     book
                                                                         WHERE    publisher = '대한미디어'));

                        
