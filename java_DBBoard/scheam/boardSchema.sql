drop table board;

create table board(
	board_no int primary key, --�۹�ȣ
	subject varchar2(30) not null, --����
	writer varchar2(20) not null,--�ۼ���
	content varchar2(50) not null,--����
	board_date date not null--�����
); 



--������ �����
create sequence board_seq nocache; 

drop sequence board_seq;


select * from board where upper(subject) like upper(?);

commit;

insert into board (board_no, subject, writer, content, board_date) 
values (board_seq.nextval, 'db����', '������', '�볭��', sysdate);




--���
create table reply(
  reply_no number primary key, --��۹�ȣ
  reply_content varchar2(100), --��۳���
  board_no number references board(board_no), --����� �� �θ�۹�ȣ
  reply_regdate date --�����
);

--��ۿ��� ����� ����������
create sequence reply_no_seq nocache;

--���� �׽�Ʈ(�θ��� 1�� �ۿ� ����� 3�� �޾ƺ���)
select * from board;
select * from reply;

insert into reply values(reply_no_seq.nextval , '1���� ��� 1', 1 , sysdate);
insert into reply values(reply_no_seq.nextval , '1���� ��� 2', 1 , sysdate);
insert into reply values(reply_no_seq.nextval , '1���� ��� 3', 1 , sysdate);

commit







































