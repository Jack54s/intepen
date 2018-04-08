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

-- DROP TABLE IF EXISTS intepen_family;
-- CREATE TABLE intepen_family(
--   `id` INT NOT NULL AUTO_INCREMENT COMMENT '家属编号',
--   `account` VARCHAR(100) NOT NULL UNIQUE COMMENT '账号',
--   `password` VARCHAR(128) NOT NULL COMMENT '密码',
--   `salt` VARCHAR(128) COMMENT '加密盐值',
--   PRIMARY KEY (id)
-- )ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '家属表';
--
-- DROP TABLE IF EXISTS intepen_nurse;
-- CREATE TABLE intepen_nurse(
--   `id` INT NOT NULL AUTO_INCREMENT COMMENT '护工编号',
--   `account` VARCHAR(100) NOT NULL UNIQUE COMMENT '账号',
--   `password` VARCHAR(128) NOT NULL COMMENT '密码',
--   `salt` VARCHAR(128) COMMENT '加密盐值',
--   `name` VARCHAR(30) COMMENT '姓名',
--   `sex` VARCHAR(4) COMMENT '性别',
--   `age` INT COMMENT '年龄',
--   PRIMARY KEY (id)
-- )ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '护工表';

#RBAC
DROP TABLE IF EXISTS intepen_sys_roles;
CREATE TABLE intepen_sys_roles(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role` VARCHAR(128) NOT NULL COMMENT '角色',
  `description` VARCHAR(500) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '角色表';

DROP TABLE IF EXISTS intepen_sys_permissions;
CREATE TABLE intepen_sys_permissions(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `name` VARCHAR(128) NOT NULL COMMENT '权限名称',
  `resource_type` VARCHAR(128) NOT NULL COMMENT '资源描述',
  `url` VARCHAR(128) NOT NULL COMMENT 'URL',
  `permission` VARCHAR(128) NOT NULL COMMENT '权限',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '权限表';

DROP TABLE IF EXISTS intepen_sys_role_permission;
CREATE TABLE intepen_sys_role_permission(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_id` INT NOT NULL COMMENT '角色ID',
  `permission_id` INT NOT NULL COMMENT '权限ID',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '角色权限关联表';

DROP TABLE IF EXISTS intepen_sys_user_role;
CREATE TABLE intepen_sys_user_role(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `role_id` INT NOT NULL COMMENT '角色ID',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户角色关联表';

DROP TABLE IF EXISTS intepen_sys_user;
CREATE TABLE intepen_sys_user(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `account` VARCHAR(100) NOT NULL UNIQUE COMMENT '账号',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  `salt` VARCHAR(128) COMMENT '加密盐值',
  `name` VARCHAR(30) COMMENT '姓名',
  `sex` VARCHAR(4) COMMENT '性别',
  `age` INT COMMENT '年龄',
  `flag` INT COMMENT '标记用户类型',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户表';
