DROP TABLE IF EXISTS t_etf_n0102 CASCADE;
CREATE TABLE t_etf_n0102 (
	item_code varchar(6) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, shar_oust varchar(16)
	, indx_name varchar(128)
	, date_set varchar(8)
	, date_list varchar(8)
	, asst_clss varchar(16)
	, expn_rate varchar(16)
	, acct_perd varchar(256)
	, date_dstb varchar(256)
	, issr varchar(64)
	, issr_url varchar(128)
	, item_dscr text
);
CREATE UNIQUE INDEX pk_t_etf_n0102 ON t_etf_n0102 (item_code);

COMMENT ON TABLE t_etf_n0102 IS '네이버 주식기본정보';
COMMENT ON COLUMN t_etf_n0102.item_code IS '종목코드';
COMMENT ON COLUMN t_etf_n0102.hash IS 'data hash';
COMMENT ON COLUMN t_etf_n0102.date_inst IS '등록일시';
COMMENT ON COLUMN t_etf_n0102.date_updt IS '수정일시';
COMMENT ON COLUMN t_etf_n0102.shar_oust IS '상장주식수';
COMMENT ON COLUMN t_etf_n0102.indx_name IS '기초지수명';
COMMENT ON COLUMN t_etf_n0102.date_set IS '최초설정일';
COMMENT ON COLUMN t_etf_n0102.date_list IS '상장일';
COMMENT ON COLUMN t_etf_n0102.asst_clss IS '펀드형태';
COMMENT ON COLUMN t_etf_n0102.expn_rate IS '총보수';
COMMENT ON COLUMN t_etf_n0102.acct_perd IS '회계기간';
COMMENT ON COLUMN t_etf_n0102.date_dstb IS '분배금기준일';
COMMENT ON COLUMN t_etf_n0102.issr IS '자산운용사';
COMMENT ON COLUMN t_etf_n0102.issr_url IS '홈페이지';
COMMENT ON COLUMN t_etf_n0102.item_dscr IS '상품설명';

COMMIT;
