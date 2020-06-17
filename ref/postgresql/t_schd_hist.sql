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

DROP TABLE IF EXISTS t_schd_post CASCADE;
CREATE TABLE t_schd_post (
	schd_no varchar(16) NOT NULL
	, error_code varchar(4) NULL
	, message varchar
	, date_inst timestamp DEFAULT NOW()
);
CREATE UNIQUE INDEX pk_t_schd_post ON t_schd_post (schd_no DESC);

COMMENT ON TABLE t_schd_post IS '스케줄 결과 내역';
COMMENT ON COLUMN t_schd_post.schd_no IS '스케줄번호 - t_schd_prev.schd_no';
COMMENT ON COLUMN t_schd_post.error_code IS '오류코드 - 0000: 정상';
COMMENT ON COLUMN t_schd_post.message IS '결과 메시지';
COMMENT ON COLUMN t_schd_post.date_inst IS '종료일시';

COMMIT;

CREATE OR REPLACE VIEW v_schd_hist
AS
SELECT
	prev.schd_no
	, prev.schd_set
	, prev.schd_code
	, prev.date_inst AS start_at
	, post.date_inst AS end_at
	, post.date_inst - prev.date_inst AS time_elsp
	, post.error_code
	, post.message
	, prev.schd_data
FROM
	t_schd_prev prev
	LEFT OUTER JOIN t_schd_post post
		ON 1 = 1
		AND prev.schd_no = post.schd_no
WHERE 1 = 1
;

COMMIT;
