INSERT INTO bsm_code (id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1001, 'SIW', 'Sign In Way', 1, 0, '2025-11-05 09:30:07.769687', 0, '2025-11-05 09:31:16.445425', 1);


-- //@UNDO
delete
from bsm_code
where id = 1001;
