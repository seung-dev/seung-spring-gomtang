DROP TABLE IF EXISTS t_kw_kw10000 CASCADE;
CREATE TABLE t_kw_kw10000 (
	item_code varchar(6) NOT NULL
	, mrkt_type varchar(1) NOT NULL
	, on_prgr varchar(1) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, hash varchar(32) NOT NULL
	, item_name varchar(128) NOT NULL
	, item_cnst varchar(32)
	, item_stts varchar(64)
);
CREATE UNIQUE INDEX pk_t_kw_kw10000 ON t_kw_kw10000 (item_code, mrkt_type);
CREATE INDEX idx_a_t_kw_kw10000 ON t_kw_kw10000 (on_prgr);

COMMENT ON TABLE t_kw_kw10000 IS '키움 주식상태정보';
COMMENT ON COLUMN t_kw_kw10000.item_code IS '종목코드';
COMMENT ON COLUMN t_kw_kw10000.mrkt_type IS '시장구분';
COMMENT ON COLUMN t_kw_kw10000.on_prgr IS '수집구분';
COMMENT ON COLUMN t_kw_kw10000.date_inst IS '등록일시';
COMMENT ON COLUMN t_kw_kw10000.date_updt IS '수정일시';
COMMENT ON COLUMN t_kw_kw10000.hash IS 'data hash';
COMMENT ON COLUMN t_kw_kw10000.item_name IS '종목명';
COMMENT ON COLUMN t_kw_kw10000.item_cnst IS 'stock construction';
COMMENT ON COLUMN t_kw_kw10000.item_stts IS 'stock state';

COMMIT;
