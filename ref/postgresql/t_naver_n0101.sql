DROP TABLE IF EXISTS t_naver_n0101 CASCADE;
CREATE TABLE t_naver_n0101 (
	item_code varchar(6) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, item_name varchar(64)
	, etf_type varchar(1)
);
CREATE UNIQUE INDEX pk_t_naver_n0101 ON t_naver_n0101 (item_code);

COMMENT ON TABLE t_naver_n0101 IS '네이버 ETF 목록';
COMMENT ON COLUMN t_naver_n0101.item_code IS '종목코드';
COMMENT ON COLUMN t_naver_n0101.hash IS 'data hash';
COMMENT ON COLUMN t_naver_n0101.date_inst IS '등록일시';
COMMENT ON COLUMN t_naver_n0101.date_updt IS '수정일시';
COMMENT ON COLUMN t_naver_n0101.item_name IS '종목명';
COMMENT ON COLUMN t_naver_n0101.etf_type IS 'ETF 분류';

COMMIT;
