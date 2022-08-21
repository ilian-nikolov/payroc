CREATE TABLE URL
(
   url_id IDENTITY NOT NULL,
   full_url text NOT NULL,
   short_url varchar(50),
   time_created timestamp,
   CONSTRAINT url_pk PRIMARY KEY (url_id)
);
