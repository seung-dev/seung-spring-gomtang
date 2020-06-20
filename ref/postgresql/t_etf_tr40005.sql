DROP TABLE IF EXISTS t_etf_tr40005 CASCADE;
CREATE TABLE t_etf_tr40005 (
	item_code varchar(6) NOT NULL
	, trdd varchar(8) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, etf_cp decimal(10, 2)
	, etf_inc decimal(10, 2)
	, etf_pcp decimal(10, 2)
	, etf_vol bigint
	, etf_nav decimal(10, 2)
	, etf_volaccu bigint
	, etf_indexd decimal(10, 2)
	, etf_etfd decimal(10, 2)
	, etf_ter decimal(10, 2)
	, etf_ti decimal(10, 2)
	, etf_tiinc decimal(10, 2)
);
CREATE UNIQUE INDEX pk_t_etf_tr40005 ON t_etf_tr40005 (item_code, trdd DESC);

COMMENT ON TABLE t_etf_tr40005 IS '키움 주식일별추이';
COMMENT ON COLUMN t_etf_tr40005.item_code IS '종목코드';
COMMENT ON COLUMN t_etf_tr40005.trdd IS '주식거래일(trading day)';
COMMENT ON COLUMN t_etf_tr40005.hash IS 'data hash';
COMMENT ON COLUMN t_etf_tr40005.date_inst IS '등록일시';
COMMENT ON COLUMN t_etf_tr40005.date_updt IS '수정일시';
COMMENT ON COLUMN t_etf_tr40005.etf_cp IS '종가';
COMMENT ON COLUMN t_etf_tr40005.etf_inc IS '전일대비';
COMMENT ON COLUMN t_etf_tr40005.etf_pcp IS '대비율';
COMMENT ON COLUMN t_etf_tr40005.etf_vol IS '거래량';
COMMENT ON COLUMN t_etf_tr40005.etf_nav IS 'NAV';
COMMENT ON COLUMN t_etf_tr40005.etf_volaccu IS '누적거래대금';
COMMENT ON COLUMN t_etf_tr40005.etf_indexd IS 'NAV/지수괴리율';
COMMENT ON COLUMN t_etf_tr40005.etf_etfd IS 'NAV/ETF괴리율';
COMMENT ON COLUMN t_etf_tr40005.etf_ter IS '추적오차율';
COMMENT ON COLUMN t_etf_tr40005.etf_ti IS '추적지수';
COMMENT ON COLUMN t_etf_tr40005.etf_tiinc IS '추적전일대비';

COMMIT;
