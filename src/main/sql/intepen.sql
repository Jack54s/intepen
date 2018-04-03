#智慧养老系统

DROP DATABASE inpeten;
CREATE DATABASE intepen;

USE intepen;

DROP TABLE IF EXISTS intepen_elder;
CREATE TABLE intepen_elder(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '老人编号',
  `name` VARCHAR(30) NOT NULL COMMENT '姓名',
  `sex` VARCHAR(4) NOT NULL COMMENT '性别',
  `age` INT COMMENT '年龄',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '老人表';

DROP TABLE IF EXISTS intepen_family;
CREATE TABLE intepen_family(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '家属编号',
  `account` VARCHAR(100) NOT NULL UNIQUE COMMENT '账号',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `salt` VARCHAR(128) COMMENT '加密盐值',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '家属表';

DROP TABLE IF EXISTS intepen_nurse;
CREATE TABLE intepen_nurse(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '护工编号',
  `account` VARCHAR(100) NOT NULL UNIQUE COMMENT '账号',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `salt` VARCHAR(128) COMMENT '加密盐值',
  `name` VARCHAR(30) NOT NULL COMMENT '姓名',
  `sex` VARCHAR(4) NOT NULL COMMENT '性别',
  `age` INT COMMENT '年龄',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '护工表';