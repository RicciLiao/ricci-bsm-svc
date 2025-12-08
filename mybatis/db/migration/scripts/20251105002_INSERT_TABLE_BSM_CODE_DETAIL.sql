INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10004, 1001, 'E', 'User Email', 1, 0, '2025-11-05 09:33:04.747892', 0, '2025-11-05 09:33:04.747892', 0);
INSERT INTO bsm_code_detail (id, bsm_code_id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (10005, 1001, 'L', 'Login Name', 1, 0, '2025-11-05 09:33:04.759640', 0, '2025-11-05 09:33:04.759640', 0);


-- //@UNDO
delete
from bsm_code_detail
where id in (10004, 10005);