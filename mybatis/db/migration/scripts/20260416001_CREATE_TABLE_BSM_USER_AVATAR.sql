create table bsm_user_avatar
(
    bsm_user_id bigint      not null primary key,
    fsp_token   varchar(83) not null,
    created_by  bigint      not null,
    created_dtm datetime(6) not null,
    updated_by  bigint      not null,
    updated_dtm datetime(6) not null,
    version     bigint      not null
);

-- //@UNDO
drop table bsm_user_avatar;