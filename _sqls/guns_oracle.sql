-- 数据库信息表
CREATE TABLE database_info (
  db_id NUMBER(20, 0) NOT NULL,
  db_name varchar2(255) NOT NULL,
  jdbc_driver varchar2(255),
  user_name varchar2(255),
  password varchar2(255),
  jdbc_url varchar2(255),
  remarks varchar2(255),
  create_time date,
  PRIMARY KEY (db_id)
);

-- oauth2第三方登录
CREATE TABLE oauth_user_info (
  oauth_id NUMBER(20, 0) NOT NULL,
  user_id NUMBER(20, 0) NOT NULL,
  nick_name varchar2(512),
  avatar varchar2(500),
  blog varchar2(255),
  company varchar2(255),
  location varchar2(255),
  email varchar2(255),
  remark varchar2(255),
  gender int,
  source varchar2(255),
  token varchar2(255),
  uuid varchar2(255),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (oauth_id)
);

-- 系统配置表
CREATE TABLE sys_config (
  id NUMBER(20, 0) NOT NULL,
  name varchar2(200),
  code varchar2(200),
  dict_flag varchar2(1),
  dict_type_id NUMBER(20, 0),
  value varchar2(200),
  remark varchar2(200),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (id)
);

INSERT INTO sys_config VALUES (1143324237579165697, '验证码开关', 'GUNS_KAPTCHA_OPEN', 'Y', 1106120265689055233, 'DISABLE', '是否开启验证码', to_date('2019-06-24 12:46:43', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-06-25 09:04:42', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_config VALUES (1143386834613694465, '阿里云短信的keyId', 'GUNS_SMS_ACCESSKEY_ID', 'N', NULL, 'xxxkey', '阿里云短信的密钥key', to_date('2019-06-25 13:13:59', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-06-25 13:19:21', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_config VALUES (1143386953933254657, '阿里云短信的secret', 'GUNS_SMS_ACCESSKEY_SECRET', 'N', NULL, 'xxxsecret', '阿里云短信的secret', to_date('2019-06-25 13:14:28', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143387023449649154, '阿里云短信的签名', 'GUNS_SMS_SIGN_NAME', 'N', NULL, 'xxxsign', '阿里云短信的签名', to_date('2019-06-25 13:14:44', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143387131109044225, '阿里云短信登录的模板号', 'GUNS_SMS_LOGIN_TEMPLATE_CODE', 'N', NULL, 'SMS_XXXXXX', '阿里云短信登录的模板号', to_date('2019-06-25 13:15:10', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143387225019510785, '验证码短信失效时间', 'GUNS_SMS_INVALIDATE_MINUTES', 'N', NULL, '2', '验证码短信失效时间', to_date('2019-06-25 13:15:32', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143468689664876546, '管理系统名称', 'GUNS_SYSTEM_NAME', 'N', NULL, 'Guns快速开发平台', '管理系统名称', to_date('2019-06-25 18:39:15', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143468867767607297, '默认系统密码', 'GUNS_DEFAULT_PASSWORD', 'N', NULL, '111111', '默认系统密码', to_date('2019-06-25 18:39:57', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1143469008025133058, 'OAuth2登录用户的账号标识', 'GUNS_OAUTH2_PREFIX', 'N', NULL, 'oauth2', 'OAuth2登录用户的账号标识', to_date('2019-06-25 18:40:31', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_config VALUES (1145207130463191041, '顶部导航条是否开启', 'GUNS_DEFAULT_ADVERT', 'Y', 1106120265689055233, 'ENABLE', '顶部Guns广告是否开启', to_date('2019-06-30 13:47:11', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-06-30 13:47:20', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_config VALUES (1145915627211370498, 'Guns发布的编号', 'GUNS_SYSTEM_RELEASE_VERSION', 'N', NULL, '20190702', '用于防止浏览器缓存相关的js和css', to_date('2019-07-02 12:42:30', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);

-- 部门
CREATE TABLE sys_dept (
  dept_id NUMBER(20, 0) NOT NULL,
  pid NUMBER(20, 0) NOT NULL,
  pids varchar2(512),
  simple_name varchar2(45),
  full_name varchar2(255),
  description varchar2(255),
  version int,
  sort int,
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (dept_id)
);

INSERT INTO sys_dept VALUES (24, 0, '[0],', '总公司', '总公司', '', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (25, 24, '[0],[24],', '开发部', '开发部', '', NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (26, 24, '[0],[24],', '运营部', '运营部', '', NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (27, 24, '[0],[24],', '战略部', '战略部', '', NULL, 4, NULL, NULL, NULL, NULL);

-- 字典
CREATE TABLE sys_dict (
  dict_id NUMBER(20, 0) NOT NULL,
  dict_type_id NUMBER(20, 0) NOT NULL,
  code varchar2(50),
  name varchar2(255),
  parent_id varchar2(255),
  parent_ids varchar2(255),
  status varchar2(10),
  sort int,
  description varchar2(1000),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (dict_id)
);

INSERT INTO sys_dict VALUES (1106120532442595330, 1106120208097067009, 'M', '男', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:00', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120574163337218, 1106120208097067009, 'F', '女', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:10', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120645697191938, 1106120265689055233, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:27', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120699468169217, 1106120265689055233, 'DISABLE', '禁用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:40', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120784318939137, 1106120322450571266, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:12:00', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120825993543682, 1106120322450571266, 'FREEZE', '冻结', 0, '[0]', 'ENABLE', 1, '', to_date('2019-03-14 17:12:10', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:56:36', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120875872206849, 1106120322450571266, 'DELETED', '已删除', 0, '[0]', 'ENABLE', -1221, '', to_date('2019-03-14 17:12:22', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:56:53', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120935070613505, 1106120388036902914, 'Y', '删除', 0, '[0]', 'ENABLE', 23333, '', to_date('2019-03-14 17:12:36', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:58:53', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120968910258177, 1106120388036902914, 'N', '未删除', 0, '[0]', 'ENABLE', 1212211221, '', to_date('2019-03-14 17:12:44', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:59:03', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1149218674746355713, 1149217131989069826, 'BASE_SYSTEM', '系统管理', 0, '[0]', 'ENABLE', 1, '系统管理平台', to_date('2019-07-11 15:27:38', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-07-11 20:27:14', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);


-- 字典类型
CREATE TABLE sys_dict_type (
  dict_type_id NUMBER(20, 0) NOT NULL,
  code varchar2(50),
  name varchar2(45),
  description varchar2(1000),
  system_flag varchar2(1),
  status varchar2(10),
  sort int,
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (dict_type_id)
);

INSERT INTO sys_dict_type VALUES (1106120208097067009, 'SEX', '性别', '', 'Y', 'ENABLE', 4, to_date('2019-03-14 17:09:43', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_dict_type VALUES (1106120265689055233, 'STATUS', '状态', '', 'Y', 'ENABLE', 3, to_date('2019-03-14 17:09:57', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_dict_type VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'Y', 'ENABLE', 21112, to_date('2019-03-14 17:10:10', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-03-16 10:56:15', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_dict_type VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'Y', 'ENABLE', 2, to_date('2019-03-14 17:10:26', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-03-27 16:26:31', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_dict_type VALUES (1149217131989069826, 'SYSTEM_TYPE', '系统分类', '系统所有分类的维护', 'Y', 'ENABLE', 70, to_date('2019-07-11 15:21:30', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-07-11 15:28:21', 'yyyy-mm-dd hh24:mi:ss'), 1);

-- 文件信息
CREATE TABLE sys_file_info (
  file_id varchar2(50) NOT NULL,
  file_bucket varchar2(100),
  file_name varchar2(100),
  file_suffix varchar2(50),
  file_size_kb NUMBER(20, 0),
  final_name varchar2(100),
  file_path varchar2(1000),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (file_id)
);

-- 登录日志
CREATE TABLE sys_login_log (
  login_log_id NUMBER(20, 0) NOT NULL,
  log_name varchar2(255),
  user_id NUMBER(20, 0),
  create_time date,
  succeed varchar2(255),
  message varchar2(2000),
  ip_address varchar2(1000),
  PRIMARY KEY (login_log_id)
);

-- 菜单
CREATE TABLE sys_menu (
  menu_id NUMBER(20, 0) NOT NULL,
  code varchar2(255),
  pcode varchar2(255),
  pcodes varchar2(255),
  name varchar2(255),
  icon varchar2(255),
  url varchar2(255),
  sort int,
  levels int,
  menu_flag varchar2(32),
  description varchar2(255),
  status varchar2(32),
  new_page_flag varchar2(32),
  open_flag varchar2(32),
  system_type varchar2(100),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (menu_id)
);

INSERT INTO sys_menu VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', 20, 1, 'Y', NULL, 'ENABLE', NULL, '1', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 10, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (114, 'role', 'system', '[0],[system],', '角色管理', '', '/role', 20, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', '', '/menu', 50, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (128, 'log', 'system', '[0],[system],', '业务日志', '', '/log', 70, 2, 'Y', NULL, 'ENABLE', NULL, '0', 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', '', '/druid', 80, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', '', '/dept', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', '', '/dictType', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', '', '/loginLog', 60, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', '', '/notice', 90, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (145, 'sys_message', 'dashboard', '[0],[dashboard],', '消息通知', 'layui-icon layui-icon-tips', '/system/notice', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (149, 'api_mgr', 'dev_tools', '[0],[dev_tools],', '接口文档', 'fa-leaf', '/swagger-ui.html', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (171, 'dev_tools', '0', '[0],', '开发管理', 'layui-icon layui-icon-code-circle', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (172, 'dashboard', '0', '[0],', '主控面板', 'layui-icon layui-icon-home', '#', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, 1);
INSERT INTO sys_menu VALUES (1110777136265838594, 'demos_show', 'dev_tools', '[0],[dev_tools],', '模板页面', 'layui-icon layui-icon-template', '#', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1110777366856089602, 'excel_import', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导入', '', '/excel/import', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1110777491464667137, 'excel_export', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导出', '', '/excel/export', 20, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1110787391943098370, 'advanced_form', 'demos_show', '[0],[dev_tools],[demos_show],', '高级表单', '', '/egForm', 30, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1110839216310329346, 'pdf_view', 'demos_show', '[0],[dev_tools],[demos_show],', '文档预览', '', '/loadPdfFile', 40, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1111545968697860098, 'console', 'dashboard', '[0],[dashboard],', '项目介绍', '', '/system/console', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1111546189892870145, 'console2', 'dashboard', '[0],[dashboard],', '统计报表', '', '/system/console2', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1127085735660421122, 'code_generate', 'dev_tools', '[0],[dev_tools],', '代码生成', '', '/gen', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1139826657964593154, 'meta_data', 'dev_tools', '[0],[dev_tools],', '系统配置', '', '#', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1139827152854716418, 'data_source', 'meta_data', '[0],[dev_tools],[meta_data],', '数据源管理', '', '/databaseInfo', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1142957882422112257, 'SYS_CONFIG', 'meta_data', '[0],[dev_tools],[meta_data],', '参数配置', 'fa-star', '/sysConfig', 5, 3, 'Y', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1142957882422112258, 'SYS_CONFIG_ADD', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置添加', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1142957882422112259, 'SYS_CONFIG_EDIT', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置修改', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1142957882426306562, 'SYS_CONFIG_DELETE', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置删除', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1144441072852684801, 'SYS_POSITION', 'system', '[0],[system],', '职位管理', 'fa-star', '/position', 35, 2, 'Y', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1144441072852684802, 'SYS_POSITION_ADD', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表添加', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1144441072852684803, 'SYS_POSITION_EDIT', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表修改', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1144441072852684804, 'SYS_POSITION_DELETE', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表删除', 'fa-star', '', 999, 3, 'N', '', 'ENABLE', '', '', 'BASE_SYSTEM', NULL, NULL, 1, 1);
INSERT INTO sys_menu VALUES (1149955324929765378, 'system_info', 'dashboard', '[0],[dashboard],', '系统监控', 'layui-icon-star-fill', '/system/systemInfo', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, 'BASE_SYSTEM', NULL, NULL, 1, NULL);

-- 通知表
CREATE TABLE sys_notice (
  notice_id NUMBER(20, 0) NOT NULL,
  title varchar2(255),
  content varchar2(1000),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (notice_id)
);

INSERT INTO sys_notice VALUES (6, '欢迎', 'hi，Guns旗舰版发布了！', to_date('2017-01-11 08:53:20', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2018-12-28 23:24:48', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_notice VALUES (8, '你好', '你好，世界！', to_date('2017-05-10 19:28:57', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2018-12-28 23:28:26', 'yyyy-mm-dd hh24:mi:ss'), 1);

-- 操作日志
CREATE TABLE sys_operation_log (
  operation_log_id NUMBER(20, 0) NOT NULL,
  log_type varchar2(32),
  log_name varchar2(255),
  user_id NUMBER(20, 0),
  class_name varchar2(255),
  method varchar2(255),
  create_time date,
  succeed varchar2(32),
  message varchar2(1000),
  PRIMARY KEY (operation_log_id)
);

-- 职位表
CREATE TABLE sys_position (
  position_id NUMBER(20, 0) NOT NULL,
  name varchar2(32),
  code varchar2(255),
  sort NUMBER(20, 0),
  status varchar2(255),
  remark varchar2(255),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (position_id)
);

INSERT INTO sys_position VALUES (1, '董事长', 'President', 1, 'ENABLE', NULL, to_date('2019-06-27 18:14:43', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_position VALUES (2, '总经理', 'GM', 2, 'ENABLE', NULL, to_date('2019-06-27 18:14:43', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);

-- 角色和菜单关联表
CREATE TABLE sys_relation (
  relation_id NUMBER(20, 0) NOT NULL,
  menu_id NUMBER(20, 0) NOT NULL,
  role_id NUMBER(20, 0) NOT NULL,
  PRIMARY KEY (relation_id)
);

INSERT INTO sys_relation VALUES (1138325281010921473, 105, 5);
INSERT INTO sys_relation VALUES (1138325281031892994, 132, 5);
INSERT INTO sys_relation VALUES (1138325281044475906, 138, 5);
INSERT INTO sys_relation VALUES (1138325281057058818, 139, 5);
INSERT INTO sys_relation VALUES (1138325281069641730, 140, 5);
INSERT INTO sys_relation VALUES (1138325281082224642, 155, 5);
INSERT INTO sys_relation VALUES (1138325281099001858, 156, 5);
INSERT INTO sys_relation VALUES (1138325281111584770, 157, 5);
INSERT INTO sys_relation VALUES (1138325281124167682, 141, 5);
INSERT INTO sys_relation VALUES (1138325281136750593, 142, 5);
INSERT INTO sys_relation VALUES (1138325281149333506, 143, 5);
INSERT INTO sys_relation VALUES (1138325281161916418, 144, 5);
INSERT INTO sys_relation VALUES (1138325281174499329, 171, 5);
INSERT INTO sys_relation VALUES (1138325281187082241, 149, 5);
INSERT INTO sys_relation VALUES (1138325281199665154, 1127085735660421122, 5);
INSERT INTO sys_relation VALUES (1138325281216442370, 172, 5);
INSERT INTO sys_relation VALUES (1138325281229025281, 145, 5);
INSERT INTO sys_relation VALUES (1138325281245802498, 1111545968697860098, 5);
INSERT INTO sys_relation VALUES (1138325281258385410, 1111546189892870145, 5);
INSERT INTO sys_relation VALUES (1149955346819837954, 105, 1);
INSERT INTO sys_relation VALUES (1149955346836615169, 106, 1);
INSERT INTO sys_relation VALUES (1149955346849198081, 107, 1);
INSERT INTO sys_relation VALUES (1149955346861780994, 108, 1);
INSERT INTO sys_relation VALUES (1149955346878558210, 109, 1);
INSERT INTO sys_relation VALUES (1149955346895335426, 110, 1);
INSERT INTO sys_relation VALUES (1149955346907918338, 111, 1);
INSERT INTO sys_relation VALUES (1149955346920501250, 112, 1);
INSERT INTO sys_relation VALUES (1149955346928889858, 113, 1);
INSERT INTO sys_relation VALUES (1149955346941472769, 165, 1);
INSERT INTO sys_relation VALUES (1149955346954055681, 166, 1);
INSERT INTO sys_relation VALUES (1149955346966638593, 167, 1);
INSERT INTO sys_relation VALUES (1149955346979221505, 114, 1);
INSERT INTO sys_relation VALUES (1149955346995998722, 115, 1);
INSERT INTO sys_relation VALUES (1149955347004387329, 116, 1);
INSERT INTO sys_relation VALUES (1149955347016970241, 117, 1);
INSERT INTO sys_relation VALUES (1149955347029553154, 118, 1);
INSERT INTO sys_relation VALUES (1149955347042136065, 162, 1);
INSERT INTO sys_relation VALUES (1149955347054718978, 163, 1);
INSERT INTO sys_relation VALUES (1149955347067301889, 164, 1);
INSERT INTO sys_relation VALUES (1149955347084079106, 119, 1);
INSERT INTO sys_relation VALUES (1149955347096662018, 120, 1);
INSERT INTO sys_relation VALUES (1149955347113439234, 121, 1);
INSERT INTO sys_relation VALUES (1149955347138605057, 122, 1);
INSERT INTO sys_relation VALUES (1149955347167965185, 150, 1);
INSERT INTO sys_relation VALUES (1149955347197325314, 151, 1);
INSERT INTO sys_relation VALUES (1149955347222491138, 128, 1);
INSERT INTO sys_relation VALUES (1149955347243462658, 134, 1);
INSERT INTO sys_relation VALUES (1149955347277017089, 158, 1);
INSERT INTO sys_relation VALUES (1149955347302182913, 159, 1);
INSERT INTO sys_relation VALUES (1149955347331543042, 130, 1);
INSERT INTO sys_relation VALUES (1149955347360903170, 131, 1);
INSERT INTO sys_relation VALUES (1149955347390263298, 135, 1);
INSERT INTO sys_relation VALUES (1149955347419623425, 136, 1);
INSERT INTO sys_relation VALUES (1149955347444789249, 137, 1);
INSERT INTO sys_relation VALUES (1149955347469955074, 152, 1);
INSERT INTO sys_relation VALUES (1149955347495120897, 153, 1);
INSERT INTO sys_relation VALUES (1149955347520286721, 154, 1);
INSERT INTO sys_relation VALUES (1149955347549646850, 132, 1);
INSERT INTO sys_relation VALUES (1149955347570618370, 138, 1);
INSERT INTO sys_relation VALUES (1149955347599978498, 139, 1);
INSERT INTO sys_relation VALUES (1149955347633532930, 140, 1);
INSERT INTO sys_relation VALUES (1149955347658698753, 155, 1);
INSERT INTO sys_relation VALUES (1149955347688058881, 156, 1);
INSERT INTO sys_relation VALUES (1149955347713224705, 157, 1);
INSERT INTO sys_relation VALUES (1149955347738390529, 133, 1);
INSERT INTO sys_relation VALUES (1149955347763556354, 160, 1);
INSERT INTO sys_relation VALUES (1149955347788722178, 161, 1);
INSERT INTO sys_relation VALUES (1149955347813888001, 141, 1);
INSERT INTO sys_relation VALUES (1149955347843248129, 142, 1);
INSERT INTO sys_relation VALUES (1149955347872608258, 143, 1);
INSERT INTO sys_relation VALUES (1149955347897774082, 144, 1);
INSERT INTO sys_relation VALUES (1149955347922939906, 1144441072852684801, 1);
INSERT INTO sys_relation VALUES (1149955347948105730, 1144441072852684802, 1);
INSERT INTO sys_relation VALUES (1149955347973271553, 1144441072852684803, 1);
INSERT INTO sys_relation VALUES (1149955347998437378, 1144441072852684804, 1);
INSERT INTO sys_relation VALUES (1149955348027797506, 171, 1);
INSERT INTO sys_relation VALUES (1149955348052963330, 149, 1);
INSERT INTO sys_relation VALUES (1149955348073934850, 1110777136265838594, 1);
INSERT INTO sys_relation VALUES (1149955348090712065, 1110777366856089602, 1);
INSERT INTO sys_relation VALUES (1149955348107489281, 1110777491464667137, 1);
INSERT INTO sys_relation VALUES (1149955348124266498, 1110787391943098370, 1);
INSERT INTO sys_relation VALUES (1149955348141043714, 1110839216310329346, 1);
INSERT INTO sys_relation VALUES (1149955348157820930, 1127085735660421122, 1);
INSERT INTO sys_relation VALUES (1149955348174598146, 1139826657964593154, 1);
INSERT INTO sys_relation VALUES (1149955348191375362, 1139827152854716418, 1);
INSERT INTO sys_relation VALUES (1149955348208152578, 1142957882422112257, 1);
INSERT INTO sys_relation VALUES (1149955348229124098, 1142957882422112258, 1);
INSERT INTO sys_relation VALUES (1149955348245901314, 1142957882422112259, 1);
INSERT INTO sys_relation VALUES (1149955348262678530, 1142957882426306562, 1);
INSERT INTO sys_relation VALUES (1149955348283650049, 172, 1);
INSERT INTO sys_relation VALUES (1149955348308815874, 145, 1);
INSERT INTO sys_relation VALUES (1149955348329787394, 1111545968697860098, 1);
INSERT INTO sys_relation VALUES (1149955348346564610, 1111546189892870145, 1);
INSERT INTO sys_relation VALUES (1149955348363341825, 1149955324929765378, 1);

-- 角色表
CREATE TABLE sys_role (
  role_id NUMBER(20, 0) NOT NULL,
  pid NUMBER(20, 0),
  name varchar2(255),
  description varchar2(255),
  sort int,
  version int,
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (role_id)
);

INSERT INTO sys_role VALUES (1, 0, '超级管理员', 'administrator', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_role VALUES (5, 1, '第三方登录', 'oauth_role', 2, NULL, NULL, NULL, NULL, NULL);

-- 管理员表
CREATE TABLE sys_user (
  user_id NUMBER(20, 0) NOT NULL,
  avatar varchar2(255),
  account varchar2(45),
  password varchar2(45),
  salt varchar2(45),
  name varchar2(45),
  birthday date,
  sex varchar2(32),
  email varchar2(45),
  phone varchar2(45),
  role_id varchar2(255),
  dept_id NUMBER(20, 0),
  status varchar2(32),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  version int,
  PRIMARY KEY (user_id)
);

INSERT INTO sys_user VALUES (1, '1124606971782160385', 'admin', '1d6b1208c7d151d335790276a18e3d99', 'q6taw', 'stylefeng', to_date('2018-11-16 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'M', 'sn93@qq.com', '18200000000', '1', 27, 'ENABLE', to_date('2016-01-29 08:49:53', 'yyyy-mm-dd hh24:mi:ss'), NULL, to_date('2018-12-28 22:52:24', 'yyyy-mm-dd hh24:mi:ss'), 24, 25);

-- 管理员职位表
CREATE TABLE sys_user_pos (
  user_pos_id NUMBER(20, 0) NOT NULL,
  user_id NUMBER(20, 0) NOT NULL,
  pos_id NUMBER(20, 0) NOT NULL,
  PRIMARY KEY (user_pos_id)
);

INSERT INTO sys_user_pos VALUES (1144495219551617025, 1, 1);