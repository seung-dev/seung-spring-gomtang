DROP TABLE IF EXISTS t_schd CASCADE;
CREATE TABLE t_schd (
	schd_set varchar(16) NOT NULL
	, schd_code varchar(16) NOT NULL
	, schd_ctgr varchar(16) NOT NULL
	, schd_dscr varchar(128)
);
CREATE UNIQUE INDEX pk_t_schd ON t_schd (schd_set, schd_code);

COMMENT ON TABLE t_schd IS '스케줄 목록';
COMMENT ON COLUMN t_schd.schd_set IS '그룹';
COMMENT ON COLUMN t_schd.schd_code IS '코드';
COMMENT ON COLUMN t_schd.schd_ctgr IS '카테고리';
COMMENT ON COLUMN t_schd.schd_dscr IS '내용';

COMMIT;

INSERT INTO t_schd
VALUES
('kiwoom', 'kw10000', 'fin', '상태정보')
, ('kiwoom', 'tr10001', 'fin', '기본정보')
, ('kiwoom', 'tr40005', 'fin', '일별추이')
;

COMMIT;
