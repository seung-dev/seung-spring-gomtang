DROP TABLE IF EXISTS t_kw_tr10081 CASCADE;
CREATE TABLE t_kw_tr10081 (
	item_code varchar(6) NOT NULL
	, trdd varchar(8) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, etf_cp decimal(10, 2)
	, etf_vol bigint
	, etf_lp decimal(10, 2)
	, etf_hp decimal(10, 2)
	, etf_op decimal(10, 2)
);
CREATE UNIQUE INDEX pk_t_kw_tr10081 ON t_kw_tr10081 (item_code, trdd);

COMMENT ON TABLE t_kw_tr10081 IS '키움 종목일별정보';
COMMENT ON COLUMN t_kw_tr10081.item_code IS '종목코드';
COMMENT ON COLUMN t_kw_tr10081.trdd IS '주식거래일(trading day)';
COMMENT ON COLUMN t_kw_tr10081.hash IS 'data hash';
COMMENT ON COLUMN t_kw_tr10081.date_inst IS '등록일시';
COMMENT ON COLUMN t_kw_tr10081.date_updt IS '수정일시';
COMMENT ON COLUMN t_kw_tr10081.etf_cp IS '종가';
COMMENT ON COLUMN t_kw_tr10081.etf_vol IS '거래량';
COMMENT ON COLUMN t_kw_tr10081.etf_op IS '시가';
COMMENT ON COLUMN t_kw_tr10081.etf_hp IS '고가';
COMMENT ON COLUMN t_kw_tr10081.etf_lp IS '저가';

COMMIT;
