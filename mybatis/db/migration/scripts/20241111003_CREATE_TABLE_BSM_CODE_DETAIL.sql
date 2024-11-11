create table `bsm_code_detail`
(
    `id`          bigint auto_increment primary key,
    `bsm_code_id` bigint       not null,
    `code`        varchar(20)  not null,
    `description` varchar(255) not null,
    `is_active`   int          not null,
    `created_by`  bigint       not null,
    `created_dtm` datetime(6)  not null,
    `updated_by`  bigint       not null,
    `updated_dtm` datetime(6)  not null,
    `version`     datetime(6)  not null,
    constraint `bsm_code_detail_uk` unique (`bsm_code_id`, code),
    constraint `bsm_code_id_fk` foreign key (`bsm_code_id`) references bsm_code (`id`)
) auto_increment = 10000;

-- //@UNDO
drop table bsm_code_detail;
