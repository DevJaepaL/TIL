/* SELECT customer.custid , book.bookname
FROM Customer, Book, Orders
WHERE Customer.custid = Orders.custid AND Orders.bookid = Book.bookid AND
      Book.price >= 20000; */

/* ������ �������� ���� ���� �����Ͽ� ���� �̸�, �ֹ� ����, ������ ������ �˻��ϼ���. */      
--SELECT Customer.name, saleprice
--FROM Customer LEFT JOIN Orders
--                ON Customer.custid = Orders.custid;

/* ���� ��� ������ �̸��� �˻��ϼ���. */
--SELECT bookname,price
--FROM book
--WHERE price=(SELECT MAX(price)
--            FROM Book);

/* ������ ������ ���� �ִ� ���� �̸��� �˻��ϼ���. */
-- SELECT name -- "name" �ʵ� ����
-- FROM customer -- "customer" ���̺���
-- WHERE custid IN (SELECT custid -- Orders ���̺� �ִ� custid �˻�.
                -- FROM orders);

/* "���ѹ̵��"�� ������ ������ ���� �̸��� ����ϼ���. */
--SELECT name
--FROM   Customer
--WHERE  custid IN (SELECT custid
--                  FROM   Orders
--                  WHERE  bookid IN (SELECT bookid
--                                    FROM   Book
--                                    WHERE  publisher='���ѹ̵��'));

/* "���ѹ̵��"�� ������ �������� ���� ���� �̸��� ����ϼ���. */
--SELECT name -- ���� �̸� �˻�.
--FROM   Customer -- �� ���̺� ����.
--WHERE  custid IN (SELECT custid -- custid �ʵ带 Orders ���̺� �ִ� custid �ʵ� ����.
--                  FROM   Orders
--                  WHERE  bookid  IN (SELECT bookid -- Orders ���̺� �ִ� bookid �ʵ� ����.
--                                    FROM   Book -- Book ���̺��� ����.
--                                    WHERE  publisher != '���ѹ̵��'));
--                                                     -- '���ѹ̵��'�� �ƴ� ������ ������.
                                    
/*  ��� �μ�����(Sub Query)�� �̿��Ͽ� 
    ���ǻ纰�� ���ǻ��� ��� ���� ���ݺ��� ��� ������ ���Ͻÿ�. */
    
--SELECT  b1.bookname -- Book ���̺��� ������ b1.bookname �˻�.
--FROM    Book b1 -- Book ���̺��� ������ b1 ���̺� ����.
--WHERE   b1.price > (SELECT avg(b2.price)    -- b1.price�� b2.price(��հ�)���� ū ���� �˻�
--                    FROM    Book b2 -- Book ���̺��� ������ b2 ���̺� ����.
--                    WHERE b2.publisher = b1.publisher); -- b2�� b1�� 'publisher' �ʵ� ����.