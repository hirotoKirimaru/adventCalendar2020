DROP TABLE IF EXISTS TODOS CASCADE;

CREATE TABLE TODOS(
    id int,
    user_id varchar,
    action varchar
);
