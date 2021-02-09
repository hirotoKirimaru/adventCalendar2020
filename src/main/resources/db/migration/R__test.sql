DROP TABLE IF EXISTS TODOS CASCADE;
DROP TABLE IF EXISTS LOGIN_USER CASCADE;
DROP TABLE IF EXISTS BUSINESS_DATE CASCADE;

CREATE TABLE TODOS
(
    id      int,
    user_id varchar,
    action  varchar
);


CREATE TABLE LOGIN_USER
(
    user_id  VARCHAR(120),
    password VARCHAR(120),
    role     VARCHAR(10)
);

CREATE TABLE BUSINESS_DATE
(
    business_date int
);

CREATE TABLE DUMMY
(
    id_first     VARCHAR(120),
    id_second    VARCHAR(120),
    field_first  VARCHAR(10),
    field_second VARCHAR(10),
    field_third  VARCHAR(10),
    PRIMARY KEY (id_first, id_second)
);

INSERT INTO LOGIN_USER
VALUES ('kirimaru', '123456', 'IPPAN');
INSERT INTO LOGIN_USER
VALUES ('admin', 'pass', 'ADMIN');
INSERT INTO LOGIN_USER
VALUES ('master', 'master', 'MASTER');

INSERT INTO BUSINESS_DATE
VALUES (2000);
