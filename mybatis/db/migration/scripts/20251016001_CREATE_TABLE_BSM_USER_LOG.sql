create table bsm_user_log
(
    id             bigint       not null,
    login_name     varchar(15)  not null,
    user_name      varchar(255),
    user_password  varchar(64)  not null,
    user_email     varchar(255) not null,
    last_login_dtm datetime(6)  not null,
    status_id      bigint       not null,
    created_by     bigint       not null,
    created_dtm    datetime(6)  not null,
    updated_by     bigint       not null,
    updated_dtm    datetime(6)  not null,
    version        bigint       not null,
    action_cd      varchar(1)   not null,
    action_dtm     datetime(6)  not null,
    primary key (id, action_dtm)
);

-- //@UNDO
drop table bsm_user_log;