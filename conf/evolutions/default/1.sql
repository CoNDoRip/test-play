# --- First database schema

# --- !Ups

CREATE SEQUENCE employee_seq START WITH 1;

CREATE TABLE employee (
   id             NUMERIC         PRIMARY KEY    DEFAULT nextval('employee_seq')
  ,first_name     VARCHAR(30)     NOT NULL
  ,last_name      VARCHAR(30)     NOT NULL
  ,second_name    VARCHAR(30)
  ,age            INT
  ,experience     INT
  ,description    VARCHAR(500)
);

CREATE INDEX ix_employee_last_name ON employee (last_name);
CREATE INDEX ix_employee_age       ON employee (age);


# --- !Downs

DROP TABLE IF EXISTS employee;

DROP SEQUENCE IF EXISTS employee_seq;

