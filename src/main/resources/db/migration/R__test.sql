DROP TABLE IF EXISTS TODOS CASCADE;
DROP TABLE IF EXISTS LOGIN_USER CASCADE;
DROP TABLE IF EXISTS BUSINESS_DATE CASCADE;

CREATE TABLE TODOS(
    id int,
    user_id varchar,
    action varchar
);


CREATE TABLE LOGIN_USER (
  user_id      VARCHAR(120),
  password      VARCHAR(120)
);

CREATE TABLE BUSINESS_DATE(
  business_date int
);

INSERT INTO LOGIN_USER VALUES('kirimaru', '123456');
INSERT INTO LOGIN_USER VALUES('admin', 'pass');

INSERT INTO BUSINESS_DATE VALUES (2000);
