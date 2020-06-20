DROP TABLE IF EXISTS t_etf_kw10000 CASCADE;
CREATE TABLE t_etf_kw10000 (
	item_code varchar(6) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, item_name varchar(128) NOT NULL
	, etf_cnst varchar(32)
	, etf_stts varchar(64)
);
CREATE UNIQUE INDEX pk_t_etf_kw10000 ON t_etf_kw10000 (item_code);

COMMENT ON TABLE t_etf_kw10000 IS '키움 주식상태정보';
COMMENT ON COLUMN t_etf_kw10000.item_code IS '종목코드';
COMMENT ON COLUMN t_etf_kw10000.hash IS 'data hash';
COMMENT ON COLUMN t_etf_kw10000.date_inst IS '등록일시';
COMMENT ON COLUMN t_etf_kw10000.date_updt IS '수정일시';
COMMENT ON COLUMN t_etf_kw10000.item_name IS '종목명';
COMMENT ON COLUMN t_etf_kw10000.etf_cnst IS 'stock construction';
COMMENT ON COLUMN t_etf_kw10000.etf_stts IS 'stock state';

COMMIT;
