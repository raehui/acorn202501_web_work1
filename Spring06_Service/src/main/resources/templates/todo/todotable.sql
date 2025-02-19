create table todo(
	id number not null,
	content varchar2(10),
	regdate date
);

create sequence todo_sequence;