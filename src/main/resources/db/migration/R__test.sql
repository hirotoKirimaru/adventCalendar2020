DROP TABLE IF EXISTS TODOS CASCADE;
DROP TABLE IF EXISTS LOGIN_USER;

CREATE TABLE TODOS(
    id int,
    user_id varchar,
    action varchar
);


CREATE TABLE LOGIN_USER (
  user_id      VARCHAR(120),
  password      VARCHAR(120)
);

INSERT INTO LOGIN_USER VALUES('kirimaru', '123456');
INSERT INTO LOGIN_USER VALUES('admin', 'pass');
