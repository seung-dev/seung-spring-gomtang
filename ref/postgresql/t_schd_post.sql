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
