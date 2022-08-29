drop table goods;


--�����̺�
create table customer(
  user_id varchar2(30) primary key, -- �����ID
  user_pwd varchar2(30) not null,--��й�ȣ
  user_name varchar2(30),--�̸�
  reg_date date-- ������
);


insert into  customer values('jang','1111','���缮',sysdate);
insert into  customer values('lee','1111','��ȿ��',sysdate);
insert into  customer values('kim','1111','���߱�',sysdate);
insert into  customer values('king','1111','������',sysdate);

--��ǰ���̺�
create table goods(
  goods_id varchar2(20) primary key, --��ǰ��ȣ
  goods_name varchar2(50) not null, --��ǰ�̸�
  goods_price number(12) ,--����
  stock number(3), --���
  regdate date default sysdate --�����
);


insert into goods values('A01','�����',1500,10,SYSDATE);
insert into goods values('A02','��Ϲ���Ĩ',2500,10,SYSDATE);
insert into goods values('A03','��Ĩ',2000,10,SYSDATE);
insert into goods values('A04','������',1500,10,SYSDATE);
insert into goods values('A05','���ڱ�',1500,10,SYSDATE);

delete from goods;

--�ֹ����̺�
drop table orders;
create table orders(
  order_id number(5) primary key, --�ֹ��ڵ�
  order_date date not null, --�ֹ�����
  user_id varchar2(30) not null references customer(user_id) , --�ֹ��ѻ��
  address varchar2(100) not null, --�����
  total_amount number(20) not null -- �ѱ��űݾ�
);

-- ORDER_ID ������ ����
drop sequence ORDER_LINE_ID_SEQ;
CREATE SEQUENCE ORDER_ID_SEQ NOCACHE;


drop table order_line;

--�ֹ���
create table order_line(
  order_line_id number(5) primary key, --�ֹ����ڵ�
  order_id number(5) not null references orders(order_id), --�ֹ��ڵ�(�ֹ����̵�)
  goods_id varchar2(20) not null references goods(goods_id), --��ǰ���̵�
  unit_price number(12) not null, --�ܰ�
  qty number(3) not null, --�ֹ�����
  amount number(10) not null --�ܰ�*�ֹ����� �� �ݾ�
);

--ORDER_LINE_ID ������ ����
CREATE SEQUENCE ORDER_LINE_ID_SEQ  NOCACHE;


commit;
--�ֹ��ϱ�
 1) jang ���̵� A01 ��ǰ 2��, AO2 ��ǰ 1�� �����Ѵ�.
  INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)
  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,'jang','��⵵ �Ǳ�', 5500);
  
  select ORDER_ID_SEQ.currval from dual;
  
  insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)
  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , 'A01', 1500,2 , 3000 );
  
   insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)
  values(ORDER_LINE_ID_SEQ.nextval , ORDER_ID_SEQ.currval , 'A02', 2500,1 , 2500 );
  
  
  --�ֹ�������ŭ ��� ���ҽ�Ų��.
  update goods set stock=stock-2 where goods_id='A01';
  update goods set stock=stock-1 where goods_id='A02';
 
 commit;
 
 select * from orders;
 select * from order_line;
 
 2) KIM ���̵� A01 ��ǰ 3�� �����Ѵ�.
   INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)
  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,'kim','����� ������', 4500);
  
  insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)
  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , 'A01', 1500,3 , 4500 );
  
  update goods set stock=stock-3 where goods_id='A01';
  
  commit
 
 3) JANG ���̵� A03 ��ǰ 2���� A04 ��ǰ 1�� �����Ѵ�.
  INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)
  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,'jang','��⵵ �д籸', 5500);
  
  select ORDER_ID_SEQ.currval from dual;
  
  insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)
  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , 'A03', 2000,2 , 4000 );
  
   insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)
  values(ORDER_LINE_ID_SEQ.nextval , ORDER_ID_SEQ.currval , 'A04', 1500,1 , 1500 );
  
  update goods set stock=stock-2 where goods_id='A03';
  update goods set stock=stock-1 where goods_id='A04';
  
  -------------------------------------------------------
  select * from customer;
  select * from goods;
  select * from orders;
  select * from order_line;
  
  
   drop table order_line;
  drop table orders;
  drop table goods;
  drop table customer;
  
  drop sequence ORDER_ID_SEQ;
  drop sequence ORDER_LINE_ID_SEQ;
  
  commit
 
