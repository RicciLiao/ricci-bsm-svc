create table `bsm_code`
(
    `id`          bigint auto_increment primary key,
    `code`        varchar(20)  not null,
    `description` varchar(255) not null,
    `is_active`   int          not null,
    `created_by`  bigint       not null,
    `created_dtm` datetime(6)  not null,
    `updated_by`  bigint       not null,
    `updated_dtm` datetime(6)  not null,
    `version`     datetime(6)  not null,
    constraint `bsm_code_pk` unique (`code`)
) auto_increment = 1000;

-- //@UNDO
drop table `bsm_code`;