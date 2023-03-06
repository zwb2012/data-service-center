CREATE TABLE dsc_data_source_config
(
    `id`               int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name_space`       varchar(128) NOT NULL COMMENT '命名空间',
    `data_source_type` varchar(256) DEFAULT NULL COMMENT '数据源类型',
    `db_name`          varchar(128) NOT NULL COMMENT '数据库库名称',
    `db_url`           varchar(128) NOT NULL COMMENT '数据库url',
    `db_user_name`     varchar(128) NOT NULL COMMENT '数据库用户名',
    `db_password`      varchar(128) NOT NULL COMMENT '数据库密码',
    `status`           tinyint(2) unsigned NOT NULL COMMENT '数据源生效状态 1:生效 0:不生效',
    `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据源配置';


CREATE TABLE dsc_sql_config
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name_space`     varchar(128) NOT NULL COMMENT '命名空间',
    `data_source_id` bigint(11) NOT NULL COMMENT '数据源ID',
    `sql_id`         varchar(128) NOT NULL COMMENT '执行sql标识ID',
    `sql_type`       varchar(16)  NOT NULL COMMENT '执行sql类型',
    `sql_content`    text         NOT NULL COMMENT 'sql配置内容',
    `status`         tinyint(2) unsigned NOT NULL COMMENT 'sql配置启用状态 1:启用 0:禁用',
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='执行sql配置';
