DROP TABLE IF EXISTS t_etf_n0103 CASCADE;
CREATE TABLE t_etf_n0103 (
	item_code varchar(6) NOT NULL
	, item_name_cu varchar(64)
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, item_code_cu varchar(6)
	, asst_wght decimal(5, 2)
);
CREATE UNIQUE INDEX pk_t_etf_n0103 ON t_etf_n0103 (item_code, item_name_cu);

COMMENT ON TABLE t_etf_n0103 IS '네이버 ETF CU 구성목록';
COMMENT ON COLUMN t_etf_n0103.item_code IS '종목코드';
COMMENT ON COLUMN t_etf_n0103.item_name_cu IS '상품 종목명';
COMMENT ON COLUMN t_etf_n0103.hash IS 'data hash';
COMMENT ON COLUMN t_etf_n0103.date_inst IS '등록일시';
COMMENT ON COLUMN t_etf_n0103.date_updt IS '수정일시';
COMMENT ON COLUMN t_etf_n0103.item_code_cu IS '상품 종목코드';
COMMENT ON COLUMN t_etf_n0103.asst_wght IS '상품 비중';

COMMIT;
