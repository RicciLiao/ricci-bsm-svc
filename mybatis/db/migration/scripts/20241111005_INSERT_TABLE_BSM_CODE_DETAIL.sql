INSERT INTO `bsm_code_detail` (`id`, `bsm_code_id`, `code`, `description`, `is_active`, `created_by`, `created_dtm`,
                               `updated_by`,
                               updated_dtm, version)
VALUES (null, (select `id` from `bsm_code` where `bsm_code`.`code` = 'EVT'), 'EVTSU', 'Verification for Sign Up', 1, 0,
        now(), 0, now(), now());

-- //@UNDO
delete
from `bsm_code_detail`
where `bsm_code_id` = (select `id` from `bsm_code` where `bsm_code`.`code` = 'EVT')
  and `code` in ('EVTSU');