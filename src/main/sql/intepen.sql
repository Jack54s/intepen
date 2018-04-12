#智慧养老系统

DROP DATABASE inpeten;
CREATE DATABASE intepen;

USE intepen;

DROP TABLE IF EXISTS intepen_elder;
CREATE TABLE intepen_elder(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '老人编号',
  `name` VARCHAR(30) NOT NULL COMMENT '姓名',
  `sex` enum('男', '女') NOT NULL COMMENT '性别',
  `age` INT COMMENT '年龄',
  `nurse_id` INT COMMENT '分配的护工ID',
  PRIMARY KEY (id),
  CONSTRAINT NurseID_FK FOREIGN KEY(nurse_id) REFERENCES intepen_sys_user(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '老人表';

DROP TABLE IF EXISTS intepen_medical_record;
CREATE TABLE intepen_medical_record(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '病历编号',
  `hospital` VARCHAR(200) NOT NULL COMMENT '医院',
  `admission_date` DATE NOT NULL COMMENT '入院日期',
  `record_date` DATE NOT NULL COMMENT '记录日期',
  `history_of_present_illness` TEXT COMMENT '现病史',
  `history_of_past_illness` TEXT COMMENT '既往史',
  `history_of_personal_illness` TEXT COMMENT '个人史',
  `history_of_family_illness` TEXT COMMENT '家族史',
  `elder_id` INT NOT NULL COMMENT'老人编号',
  PRIMARY KEY (id),
  CONSTRAINT ElderID_FK FOREIGN KEY(elder_id) REFERENCES intepen_elder(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '病历表';

DROP TABLE IF EXISTS intepen_doctor_advice;
CREATE TABLE intepen_doctor_advice(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '医嘱编号',
  `elder_id` INT NOT NULL COMMENT '老人编号',
  `start_date` DATE COMMENT '开始日期',
  `end_date` DATE COMMENT '结束日期',
  `medicine` VARCHAR(200) COMMENT '药物名',
  `time` VARCHAR (100) COMMENT '时间',
  `dosage` VARCHAR (100) COMMENT '用量',
  `note` TEXT COMMENT '备注',
  PRIMARY KEY (id),
  CONSTRAINT ElderID_FK FOREIGN KEY(elder_id) REFERENCES intepen_elder(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '医嘱表';

DROP TABLE IF EXISTS intepen_physical_examination_data;
CREATE TABLE intepen_physical_examination_data(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '体检数据编号',
  `elder_id` INT NOT NULL COMMENT '老人编号',
  `height` INT COMMENT '身高',
  `weight` INT COMMENT '体重',
  PRIMARY KEY (id),
  CONSTRAINT ElderID_FK FOREIGN KEY(elder_id) REFERENCES intepen_elder(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '体检数据表';

DROP TABLE IF EXISTS intepen_events;
CREATE TABLE intepen_events(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `event_name` VARCHAR (200) NOT NULL COMMENT'消息事件名',
  `timestamp` TIMESTAMP NOT NULL COMMENT '时间戳',
  `priority` INT NOT NULL DEFAULT 0 COMMENT '优先级，0：优先级较低，9：紧急事件',
  `status` INT NOT NULL COMMENT '状态--0：未完成，1：完成，2：已在办。',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '消息事件表';

DROP TABLE IF EXISTS intepen_nurse_application;
CREATE TABLE intepen_nurse_application(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '申请编号',
  `nurse_id` INT NOT NULL COMMENT '护工ID',
  `nurse_name` VARCHAR (30) COMMENT '护工姓名',
  `elder_id` INT NOT NULL COMMENT '老人ID',
  `elder_name` VARCHAR (30) COMMENT '老人姓名',
  `date` DATE COMMENT '申请日期',
  `status` INT NOT NULL COMMENT '处理状态，0：未处理，1：已批准，2：未批准。',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '申请护工表';

DROP TABLE IF EXISTS intepen_threshold;
CREATE TABLE intepen_threshold(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `item` VARCHAR (100) NOT NULL COMMENT '数据项',
  `threshold` INT COMMENT '阈值',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '阈值表';

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
  `sex` enum('男', '女') COMMENT '性别',
  `age` INT COMMENT '年龄',
  `flag` INT COMMENT '标记用户类型',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户表';
#RBAC_END

#RELATION

DROP TABLE IF EXISTS intepen_elder_family;
CREATE TABLE intepen_elder_family(
  `elder_id` INT NOT NULL COMMENT '老人ID',
  `family_id` INT NOT NULL COMMENT '家属ID',
  CONSTRAINT ElderFamily_PK PRIMARY KEY(elder_id, family_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '老人家属关联表';
