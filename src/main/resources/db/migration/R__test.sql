DROP TABLE IF EXISTS TODOS CASCADE;
DROP TABLE IF EXISTS LOGIN_USER;

CREATE TABLE TODOS(
    id int,
    user_id varchar,
    action varchar
);


CREATE TABLE LOGIN_USER (
  id int,
  username      VARCHAR(120),
  password      VARCHAR(120),
  PRIMARY KEY (id)
);
