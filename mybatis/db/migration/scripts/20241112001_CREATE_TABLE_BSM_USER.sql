create table `bsm_user`
(
    `id`             bigint auto_increment primary key,
    `login_name`     varchar(15)  not null,
    `user_name`      varchar(255) not null,
    `user_password`  varchar(16)  not null,
    `user_email`     varchar(255) not null,
    `last_login_dtm` datetime(6)  not null,
    `status_id`      bigint       not null,
    `created_by`     bigint       not null,
    `created_dtm`    datetime(6)  not null,
    `updated_by`     bigint       not null,
    `updated_dtm`    datetime(6)  not null,
    `version`        datetime(6)  not null,
    constraint `bsm_code_uk_1` unique (`login_name`),
    constraint `bsm_code_uk_2` unique (`user_email`)
) auto_increment = 1000;

-- //@UNDO
drop table `bsm_user`;