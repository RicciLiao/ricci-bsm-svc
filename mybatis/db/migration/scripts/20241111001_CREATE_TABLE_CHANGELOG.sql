create table bsm_changelog
(
    id          bigint       not null primary key,
    applied_at  varchar(25)  not null,
    description varchar(100) not null
);

-- //@UNDO
drop table bsm_changelog;
