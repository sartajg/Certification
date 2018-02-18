# Certification
Certification system, which will help user to get certified with the help of practice questions and exam questions
# Technologies used
1) Jboss WildFly11 application server
2) Core Java
3) RestEasy web services
# Framework Used
1) Spring Transaction
2) Spring AOP
3) Spring IOC
4) Hibernate
# DB used
Oracle DB
#Note, Before using this project, create Data source JNDI with jboss. you can check details abount JNDI name in JBossDatabaseContext.xml file.
It will help to connect DB before deploying the application into jboss server.

#Below are the details of SQL queries to Create table structure and sequences which has used in this project.

ALTER TABLE USERS
DROP PRIMARY KEY CASCADE;
ALTER TABLE ADDRESS
DROP PRIMARY KEY CASCADE;
ALTER TABLE QUESTIONS
DROP PRIMARY KEY CASCADE;
ALTER TABLE ANSWERS
DROP PRIMARY KEY CASCADE;
ALTER TABLE CERTIFICATE_REG
DROP PRIMARY KEY CASCADE;
DROP TABLE USERS;
DROP TABLE ADDRESS;
DROP TABLE QUESTIONS;
DROP TABLE ANSWERS;
drop table CERTIFICATE_REG;
CREATE TABLE USERS
  (
    USER_ID       NUMBER(10, 0) NOT NULL,
    PASSWORD      VARCHAR2(60) NOT NULL,
    FIRST_NAME    VARCHAR2(20) NOT NULL,
    LAST_NAME     VARCHAR2(20),
    MOBILE_NO     VARCHAR2(14),
    email_id      VARCHAR2(40),
    USER_LOCKED   NUMBER(1) DEFAULT 0,
    USER_TYPE     VARCHAR2(15) not null,
    CREATED_DATE  TIMESTAMP(9) WITH TIME ZONE not null,
    MODIFIED_DATE TIMESTAMP(9) WITH TIME ZONE not null,
    CONSTRAINT USERS_PK1 PRIMARY KEY (USER_ID)
  );
CREATE TABLE ADDRESS
  (
    LOCATION_ID NUMBER(10,0) NOT NULL,
    USER_ID       NUMBER(10, 0) NOT NULL, 
    STREET        VARCHAR2(60 CHAR) NOT NULL,
    PIN_CODE      VARCHAR2(6) NOT NULL,
    ADDRESS_TYPE  VARCHAR2(31 CHAR) NOT NULL,
    CREATED_DATE  TIMESTAMP(9) WITH TIME ZONE not null,
    MODIFIED_DATE TIMESTAMP(9) WITH TIME ZONE not null,
    CONSTRAINT ADDRESS_PK1 PRIMARY KEY (LOCATION_ID),
    CONSTRAINT USERS_FK_01 foreign KEY (user_id) REFERENCES USERS(USER_ID) ON DELETE CASCADE
  );
CREATE TABLE USERS_ADDRESS
  (
    USER_ID     NUMBER(10,0) NOT NULL,
    LOCATION_ID NUMBER(10,0) NOT NULL,
    PRIMARY KEY (USER_ID, LOCATION_ID)
  );
CREATE TABLE QUESTIONS
  (
    Q_ID    NUMBER(10, 0) NOT NULL,
    Q_TITLE VARCHAR2(100),
    Q_DETAILS CLOB,
    Q_LEVEL       VARCHAR(15) not null,
    EXAM_TYPE     VARCHAR(15) not null,
    Q_MARKS       NUMBER not null,
    STATUS        VARCHAR(10) not null,
    CREATED_DATE  TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    MODIFIED_DATE TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    CONSTRAINT QUESTIONS_PK1 PRIMARY KEY (Q_ID)
  );

CREATE TABLE ANSWERS
  (
    A_ID           NUMBER(10, 0) NOT NULL,
    Q_ID           NUMBER(10, 0) NOT NULL,
    A_DESCRIPTIONS VARCHAR2(200),
    CORRECT_ANS VARCHAR(1) DEFAULT 'N',
    CREATED_DATE   TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    MODIFIED_DATE  TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    CONSTRAINT ANSWERS_PK1 PRIMARY KEY (A_ID),
    CONSTRAINT ANSWERS_FK_01 foreign KEY (Q_ID) REFERENCES QUESTIONS(Q_ID)  ON DELETE CASCADE
  );

CREATE TABLE QUESTIONS_ANSWERS
  (
    Q_ID NUMBER(10,0) NOT NULL,
    A_ID NUMBER(10,0) NOT NULL,
    PRIMARY KEY (Q_ID, A_ID)
  );

CREATE TABLE USERS_QUESTIONS
  (
    USER_ID       NUMBER(10, 0) NOT NULL,
    Q_ID          NUMBER(10, 0),
    ATTEMPTED     VARCHAR(1) DEFAULT 'N',
    A_IDS         VARCHAR2(100),
    IS_CORRECT    VARCHAR(1),
    CREATED_DATE  TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    MODIFIED_DATE TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    CONSTRAINT USERS_QUESTIONS_PK_01 PRIMARY KEY (USER_ID, Q_ID)
  );

CREATE TABLE CERTIFICATE_REG
  (
    CERT_ID                NUMBER(10, 0) NOT NULL,
    USER_ID                NUMBER(10, 0) NOT NULL,
    CERT_LEVEL             VARCHAR(15) NOT NULL,
    CERT_STATUS            VARCHAR(1) DEFAULT 'P',
    MEDAL_WON			         VARCHAR2(15),
    MAX_DURATION_IN_MILLIS NUMBER NOT NULL,
    TIME_TAKEN             NUMBER,
    MARKS_OBTAINED         number,
    CREATED_DATE           TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    MODIFIED_DATE          TIMESTAMP(9) WITH TIME ZONE NOT NULL,
    CONSTRAINT CERTIFICATE_REG_PK_01 PRIMARY KEY (CERT_ID),
    CONSTRAINT CERTIFICATE_REG_FK_01 foreign KEY (USER_ID) REFERENCES USERS(USER_ID)  ON DELETE CASCADE
  );

CREATE SEQUENCE USERS_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE ADDRESS_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE QUESTIONS_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE ANSWERS_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
CREATE SEQUENCE CERT_REG_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
