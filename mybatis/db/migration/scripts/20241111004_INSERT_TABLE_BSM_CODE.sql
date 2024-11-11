INSERT INTO `bsm_code` (`id`, `code`, `description`, `is_active`, `created_by`, `created_dtm`, `updated_by`, `updated_dtm`, `version`)
VALUES (null, 'EVT', 'Email Verification Type', 1, 0, now(), 0, now(), now());
INSERT INTO `bsm_code` (`id`, `code`, `description`, `is_active`, `created_by`, `created_dtm`, `updated_by`, `updated_dtm`, `version`)
VALUES (null, 'STSD', 'Status of Data', 1, 0, now(), 0, now(), now());

-- //@UNDO
delete from `bsm_code` where `code` in ('EVT', 'STSD');