INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10000, 1000, 'A', 'Active', 1, 0, now(), 0, now(), 0);
INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10001, 1000, 'B', 'Blocked', 1, 0, now(), 0, now(), 0);
INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10002, 1000, 'S', 'Suspended', 1, 0, now(), 0, now(), 0);
INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10003, 1000, 'I', 'Initialized', 1, 0, now(), 0, now(), 0);

-- //@UNDO
delete
from bsm_code_detail
where id in (10000, 10001, 10002, 10003)