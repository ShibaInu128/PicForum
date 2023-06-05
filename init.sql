-- 判断是否存在pic_forum数据库，如果不存在则创建
CREATE DATABASE IF NOT EXISTS pic_forum;
-- 使用pic_forum数据库
USE pic_forum;
-- 创建pic_user表
CREATE TABLE pic_user
(
    uid             INT PRIMARY KEY AUTO_INCREMENT,
    username        VARCHAR(20) NOT NULL UNIQUE,
    password        VARCHAR(32) NOT NULL,
    time_register   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_last_login DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 创建pic_user_info表
CREATE TABLE pic_user_info
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    uid         INT         NOT NULL UNIQUE,
    name        VARCHAR(20) NOT NULL,
    age         INT         NOT NULL CHECK (age >= 0),
    info        VARCHAR(100),
    time_update DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (uid) REFERENCES pic_user (uid) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 创建pic_post_info表
CREATE TABLE pic_post_info
(
    pid         INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(50) NOT NULL,
    content     TEXT        NOT NULL,
    type        VARCHAR(10) NOT NULL CHECK (type IN ('show', 'apo')),
    post_uid    INT         NOT NULL,
    post_name   VARCHAR(20) NOT NULL,
    time_update DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_uid) REFERENCES pic_user (uid) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 创建pic_post_info_reply表
CREATE TABLE pic_post_info_reply
(
    rid         INT PRIMARY KEY AUTO_INCREMENT,
    pid         INT         NOT NULL,
    content     TEXT        NOT NULL,
    post_uid    INT         NOT NULL,
    post_name   VARCHAR(20) NOT NULL,
    time_update DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pid) REFERENCES pic_post_info (pid) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (post_uid) REFERENCES pic_user (uid) ON DELETE CASCADE ON UPDATE CASCADE
);