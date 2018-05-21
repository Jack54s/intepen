use intepen;

-- Roles
insert into intepen_sys_roles(id, role, description) values(1, 'admin', '超级管理员');
insert into intepen_sys_roles(id, role, description) values(2, 'userDetail', '病人信息');
insert into intepen_sys_roles(id, role, description) values(3, 'service', '服务');
insert into intepen_sys_roles(id, role, description) values(4, 'statistic', '统计信息');
insert into intepen_sys_roles(id, role, description) values(5, 'inspection', '巡查结果');
insert into intepen_sys_roles(id, role, description) values(6, 'elderManagement', '老人管理');
insert into intepen_sys_roles(id, role, description) values(7, 'nurseManagement', '护士管理');
insert into intepen_sys_roles(id, role, description) values(8, 'detail', '老人、护士详细信息');
insert into intepen_sys_roles(id, role, description) values(9, 'documentation', '健康管理');
insert into intepen_sys_roles(id, role, description) values(10, 'relation', '关联家属');
insert into intepen_sys_roles(id, role, description) values(11, 'notification', '通知列表');
insert into intepen_sys_roles(id, role, description) values(12, 'permission', '权限管理');
insert into intepen_sys_roles(id, role, description) values(13, 'settings', '设置');

-- Admin
INSERT INTO intepen_sys_user VALUES (1, '12345678901','05f3d0e2b16a8a01b2826df4c9de0206dfb5f550db8f0ddc72f6cf9a59f750047acecf23f242bde96ef35a836f0a3b283cd486a0471418afcc636f0934ddfde3','rmFmachyQ4B1H73Rx1PeBne5h98d9Gagg4dvzqppWi8La3gPJQs16Mnq5eQojJ3n1CYGWm9pA0Pip1NwmDmlX46AYbwOAu0LI6bTswP4zCCubSrDIWbbzYW6M9pmHE0s','护工','12345678901','女','1980-05-15',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO intepen_sys_user_role VALUES (1, 1, 1);
INSERT INTO intepen_router_location VALUES (1, 'mapmapmapmapmapmapmapmapmapmapmapmap', NULL, NULL);