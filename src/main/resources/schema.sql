CREATE TABLE users
( user_id IDENTITY NOT NULL,
  user_name varchar2(50) NOT NULL,
  CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE URL
(
   url_id number(10) NOT NULL,
   user_id number(10) NOT NULL,
   long_url text NOT NULL,
   short_url varchar(100) NOT NULL,
   alias varchar(20),
   CONSTRAINT url_pk PRIMARY KEY (url_id),
   FOREIGN KEY (user_id) REFERENCES users(user_id)
);

insert into users(user_name) values('anne');
--insert into users(user_name) values('john');
