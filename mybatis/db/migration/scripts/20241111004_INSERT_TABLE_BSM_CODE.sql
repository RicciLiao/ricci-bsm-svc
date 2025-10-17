INSERT INTO bsm_code (id, code, description, is_active, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1000, 'STSD', 'Status of Data', 1, 0, now(), 0, now(), 0);

-- //@UNDO
delete
from bsm_code
where id in (1000);