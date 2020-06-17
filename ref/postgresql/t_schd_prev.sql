DROP TABLE IF EXISTS t_schd_prev CASCADE;
CREATE TABLE t_schd_prev (
	schd_no varchar(16) NOT NULL
	, schd_set varchar(16) NOT NULL
	, schd_code varchar(16) NOT NULL
	, schd_data varchar(2048)
	, date_inst timestamp DEFAULT NOW()
);
CREATE UNIQUE INDEX pk_t_schd_prev ON t_schd_prev (schd_no DESC);

COMMENT ON TABLE t_schd_prev IS '스케줄 동작 내역';
COMMENT ON COLUMN t_schd_prev.schd_no IS '스케줄번호 - yyyyMMdd + 일별 시퀀스(LEFT PADDING 0)';
COMMENT ON COLUMN t_schd_prev.schd_set IS '스케줄 그룹';
COMMENT ON COLUMN t_schd_prev.schd_code IS '스케줄 코드';
COMMENT ON COLUMN t_schd_prev.schd_data IS '동작 데이타';
COMMENT ON COLUMN t_schd_prev.date_inst IS '시작일시';

COMMIT;
