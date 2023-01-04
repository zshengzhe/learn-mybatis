CREATE TABLE tbl_user
(
    id            varchar(32) NOT NULL,
    version       int(11) NOT NULL DEFAULT '0',
    name          varchar(32) NOT NULL,
    age           int(3) DEFAULT NULL,
    birthday      datetime DEFAULT NULL,
    department_id varchar(32) NOT NULL,
    sorder        int(11) NOT NULL DEFAULT '1',
    deleted       tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_user(id, version, name, age, birthday, department_id, sorder, deleted)
VALUES ('09ec5fcea620c168936deee53a9cdcfb', 0, '阿熊', 18, '2003-08-08 10:00:00', '18ec781fbefd727923b0d35740b177ab', 1, 0);
INSERT INTO tbl_user(id, version, name, age, birthday, department_id, sorder, deleted)
VALUES ('5d0eebc4f370f3bd959a4f7bc2456d89', 0, '老狗', 30, '1991-02-20 15:27:20', 'ee0e342201004c1721e69a99ac0dc0df', 1, 0);