#智慧养老系统

DROP DATABASE intepen;
CREATE DATABASE intepen;

USE intepen;

DROP TABLE IF EXISTS intepen_elder;
CREATE TABLE intepen_elder(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '老人编号',
  `name` VARCHAR(30) NOT NULL COMMENT '姓名',
  `sex` enum('男', '女') COMMENT '性别',
  `age` INT COMMENT '年龄',
  `id_card` VARCHAR(30) COMMENT '身份证号',
  `birthday` DATE COMMENT '生日',
  `in_hospital` DATE COMMENT '住院日期',
  `bed` VARCHAR (100) COMMENT '床位',
  `tel` VARCHAR (15) COMMENT '手机',
  `avatar` VARCHAR (200) COMMENT '头像地址',
  `nurse_id` INT COMMENT '分配的护工ID',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '老人表';

DROP TABLE IF EXISTS intepen_medical_record;
CREATE TABLE intepen_medical_record(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '病历编号',
  `nurse_id` INT COMMENT '护工ID',
  `date` DATE NOT NULL COMMENT '记录日期',
  `content` TEXT COMMENT '病历内容',
  `elder_id` INT NOT NULL COMMENT'老人编号',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '病历表';

DROP TABLE IF EXISTS intepen_doctor_advice;
CREATE TABLE intepen_doctor_advice(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '医嘱编号',
  `elder_id` INT NOT NULL COMMENT '老人编号',
  `nurse_id` INT NOT NULL COMMENT '护工ID',
  `date` DATE COMMENT '记录日期',
  `content` TEXT COMMENT '用药说明',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '医嘱表';

DROP TABLE IF EXISTS intepen_physical_examination_data;
CREATE TABLE intepen_physical_examination_data(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '体检数据编号',
  `elder_id` INT NOT NULL COMMENT '老人编号',
  `date` DATE COMMENT '日期',
  `height` FLOAT(5,2) COMMENT '身高',
  `weight` FLOAT(5,2) COMMENT '体重',
  `gangong` VARCHAR (100) COMMENT '肝功能',
  `xuetang` INT COMMENT '血糖',
  `cholesterol` INT COMMENT '胆固醇',
  `danguchun` INT COMMENT '胆固醇',
  `tg` INT COMMENT '',
  `hdl_cholesterol` INT COMMENT '高密度胆固醇',
  `ldl_cholesterol` INT COMMENT '低密度胆固醇',
  `shengong` VARCHAR(100) COMMENT '肾功能',
  `bchao` VARCHAR(100) COMMENT 'B超',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '体检数据表';

DROP TABLE IF EXISTS intepen_events;
CREATE TABLE intepen_events(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `event_name` VARCHAR (200) NOT NULL COMMENT'消息事件名',
  `timestamp` VARCHAR (100) NOT NULL COMMENT '时间戳',
  `priority` INT NOT NULL DEFAULT 0 COMMENT '优先级，0：优先级较低，9：紧急事件',
  `status` INT NOT NULL COMMENT '状态--0：未完成，1：完成，2：已在办。',
  `nurse_id` INT COMMENT '护工ID',
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
  `item` VARCHAR (100) NOT NULL UNIQUE COMMENT '数据项',
  `threshold` INT COMMENT '阈值',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '阈值表';

DROP TABLE IF EXISTS intepen_illness;
CREATE TABLE intepen_illness(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `illness_name` VARCHAR (100) NOT NULL COMMENT '病名',
  `illness_breed` INT COMMENT '疾病种类。1：流行病，2：老人病',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '病名表';

DROP TABLE IF EXISTS intepen_inspection;
CREATE TABLE intepen_inspection(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `elder_id` INT NOT NULL COMMENT '老人ID',
  `temperature` INT COMMENT '体温',
  `blood_pressure` VARCHAR(10) COMMENT '血压',
  `illness_id` INT DEFAULT '10' COMMENT '病名ID',
  `record` TEXT COMMENT '详情',
  `date` DATE COMMENT '日期',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '巡查表';

DROP TABLE IF EXISTS intepen_physiological_data;
CREATE TABLE intepen_physiological_data(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `item` VARCHAR (100) COMMENT '数据项',
  `value` VARCHAR (100) COMMENT '数据值',
  `datetime` TIMESTAMP COMMENT '时间',
  `device_id` VARCHAR(200) NOT NULL COMMENT '设备标识',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '生理实时信息表';

DROP TABLE IF EXISTS intepen_device;
CREATE TABLE intepen_device(
  `elder_id` INT NOT NULL COMMENT '老人ID',
  `device_id` VARCHAR(200) NOT NULL COMMENT '设备ID',
  CONSTRAINT ElderDevice_PK PRIMARY KEY(elder_id, device_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '设备关联表';

DROP TABLE IF EXISTS intepen_location;
CREATE TABLE intepen_location(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `location_date` TIMESTAMP COMMENT '定位时间',
  `record_date` TIMESTAMP COMMENT '记录时间',
  `longitude` FLOAT(7,4) COMMENT '经度',
  `latitude` FLOAT(6,4) COMMENT '纬度',
  `angle` INT COMMENT '角度',
  `state` CHAR(2) COMMENT '状态',
  `device_id` VARCHAR(200) COMMENT '设备ID',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '定位信息表';

DROP TABLE IF EXISTS intepen_behavior;
CREATE TABLE intepen_behavior(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `time` DATETIME COMMENT '时间',
  `value` VARCHAR(100) COMMENT '行为',
  `elder_id` INT NOT NULL COMMENT '老人ID',
  PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '行为表';

DROP TABLE IF EXISTS intepen_indoor_location;
CREATE TABLE intepen_indoor_location(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `device_id` VARCHAR (200) NOT NULL COMMENT '设备ID',
  `wifi_token` VARCHAR(100) NOT NULL COMMENT 'WiFi标识',
  `timestamp` DATETIME NOT NULL COMMENT '时间戳',
  PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '室内定位WiFi表';

DROP TABLE IF EXISTS intepen_router_location;
CREATE TABLE intepen_router_location(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `wifi_token` VARCHAR(100) NOT NULL UNIQUE COMMENT 'WiFi标识',
  `location` VARCHAR(100) COMMENT '路由位置坐标',
  `map` VARCHAR (200) COMMENT '地图地址',
    PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '路由位置表';

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
  `tel` VARCHAR (20) COMMENT '手机',
  `sex` enum('男', '女') COMMENT '性别',
  `birthday` DATE COMMENT '生日',
  `rate` INT COMMENT '评分',
  `time` INT COMMENT '工作经验',
  `avatar` VARCHAR (100) COMMENT '头像地址',
  `introduction` TEXT COMMENT '个人介绍',
  `evaluation` TEXT COMMENT '评价',
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

#RELATION_END

#VIEW
DROP VIEW IF EXISTS intepen_view_inspection_result;
CREATE VIEW intepen_view_inspection_result AS
SELECT I.id AS id, E.id AS elder_id, E.name AS name, E.sex AS sex, E.id_card AS id_card, E.birthday AS birthday, I.date AS date, I.temperature AS temperature,
I.blood_pressure AS blood_pressure, I.illness_id AS illness_id, N.name AS nurse_name,
I.record AS record, E.tel AS tel
FROM intepen_elder AS E, intepen_inspection AS I, intepen_illness AS L, intepen_sys_user AS N
WHERE I.elder_id = E.id AND
(I.illness_id = L.id OR I.illness_id IS NULL OR (SELECT id FROM intepen_illness WHERE id = I.illness_id) IS NULL) AND
(E.nurse_id = N.id OR E.nurse_id IS NULL OR (SELECT id FROM intepen_sys_user WHERE id = E.nurse_id) IS NULL)
GROUP BY id
ORDER BY id;

DROP VIEW IF EXISTS intepen_view_patient_statistics;
CREATE VIEW intepen_view_patient_statistics AS
SELECT I.illness_id AS illness_id, D.illness_breed AS illness_breed, D.illness_name AS illness_name, COUNT(I.elder_id) AS number_of_patient, I.date AS date
FROM intepen_inspection AS I, intepen_illness AS D
WHERE I.illness_id = D.id
GROUP BY date, illness_id
ORDER BY date, illness_id;

DROP VIEW IF EXISTS intepen_view_elder_profile;
CREATE VIEW intepen_view_elder_profile AS
SELECT E.id AS id, E.name AS name, E.sex AS sex, E.id_card AS id_card, E.birthday AS birthday, E.in_hospital AS in_hospital, E.bed AS bed, E.nurse_id AS nurse_id, U.name AS nurse_name, E.tel AS tel, E.avatar AS avatar
FROM intepen_elder AS E, intepen_sys_user AS U
WHERE E.nurse_id = U.id
UNION
SELECT E.id AS id, E.name AS name, E.sex AS sex, E.id_card AS id_card, E.birthday AS birthday, E.in_hospital AS in_hospital, E.bed AS bed, E.nurse_id AS nurse_id, '' AS nurse_name, E.tel AS tel, E.avatar AS avatar
FROM intepen_elder AS E, intepen_sys_user AS U
WHERE E.nurse_id IS NULL
UNION
SELECT E.id AS id, E.name AS name, E.sex AS sex, E.id_card AS id_card, E.birthday AS birthday, E.in_hospital AS in_hospital, E.bed AS bed, E.nurse_id AS nurse_id, '' AS nurse_name, E.tel AS tel, E.avatar AS avatar
FROM intepen_elder AS E
WHERE (SELECT id FROM intepen_sys_user WHERE id = nurse_id) IS NULL
ORDER BY id;

#VIEW_END