DROP TABLE IF EXISTS t_etf_tr10001 CASCADE;
CREATE TABLE t_etf_tr10001 (
	item_code varchar(6) NOT NULL
	, hash varchar(32) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, item_name varchar(128) NOT NULL
	, etf_type varchar(1)
	, etf_fm varchar(2)
	, etf_fv decimal(10, 2)
	, etf_equity decimal(10, 2)
	, etf_is bigint
	, etf_cr decimal(10, 2)
	, etf_yh decimal(10, 2)
	, etf_yl decimal(10, 2)
	, etf_mc decimal(10, 2)
	, etf_mcr varchar(8)
	, etf_for decimal(10, 2)
	, etf_sp decimal(10, 2)
	, etf_250h decimal(10, 2)
	, etf_250l decimal(10, 2)
	, etf_op decimal(10, 2)
	, etf_hp decimal(10, 2)
	, etf_lp decimal(10, 2)
	, etf_hhp decimal(10, 2)
	, etf_llp decimal(10, 2)
	, etf_bp decimal(10, 2)
	, etf_ep decimal(10, 2)
	, etf_eq decimal(10, 2)
	, etf_d250h varchar(8)
	, etf_vs250h decimal(10, 2)
	, etf_d250l varchar(8)
	, etf_vs250l decimal(10, 2)
	, etf_pp decimal(10, 2)
	, etf_pinc decimal(10, 2)
	, etf_pcp decimal(10, 2)
	, etf_vol bigint
	, etf_pcv decimal(10, 2)
	, etf_fvu varchar(4)
	, etf_os varchar(8)
	, etf_osr varchar(8)
);
CREATE UNIQUE INDEX pk_t_etf_tr10001 ON t_etf_tr10001 (item_code);

COMMENT ON TABLE t_etf_tr10001 IS '키움 주식기본정보';
COMMENT ON COLUMN t_etf_tr10001.item_code IS '종목코드';
COMMENT ON COLUMN t_etf_tr10001.hash IS 'data hash';
COMMENT ON COLUMN t_etf_tr10001.date_inst IS '등록일시';
COMMENT ON COLUMN t_etf_tr10001.date_updt IS '수정일시';
COMMENT ON COLUMN t_etf_tr10001.item_name IS '종목명';
COMMENT ON COLUMN t_etf_tr10001.etf_type IS '분류 - 네이버 기준';
COMMENT ON COLUMN t_etf_tr10001.etf_fm IS '결산월';
COMMENT ON COLUMN t_etf_tr10001.etf_fv IS '액면가';
COMMENT ON COLUMN t_etf_tr10001.etf_equity IS '자본금';
COMMENT ON COLUMN t_etf_tr10001.etf_is IS '상장주식';
COMMENT ON COLUMN t_etf_tr10001.etf_cr IS '신용비율';
COMMENT ON COLUMN t_etf_tr10001.etf_yh IS '연중최고';
COMMENT ON COLUMN t_etf_tr10001.etf_yl IS '연중최저';
COMMENT ON COLUMN t_etf_tr10001.etf_mc IS '시가총액';
COMMENT ON COLUMN t_etf_tr10001.etf_mcr IS '시가총액비중';
COMMENT ON COLUMN t_etf_tr10001.etf_for IS '외인소진률';
COMMENT ON COLUMN t_etf_tr10001.etf_sp IS '대용가';
COMMENT ON COLUMN t_etf_tr10001.etf_250h IS '250최고';
COMMENT ON COLUMN t_etf_tr10001.etf_250l IS '250최저';
COMMENT ON COLUMN t_etf_tr10001.etf_op IS '시가';
COMMENT ON COLUMN t_etf_tr10001.etf_hp IS '고가';
COMMENT ON COLUMN t_etf_tr10001.etf_lp IS '저가';
COMMENT ON COLUMN t_etf_tr10001.etf_hhp IS '상한가';
COMMENT ON COLUMN t_etf_tr10001.etf_llp IS '하한가';
COMMENT ON COLUMN t_etf_tr10001.etf_bp IS '기준가';
COMMENT ON COLUMN t_etf_tr10001.etf_ep IS '예상체결가';
COMMENT ON COLUMN t_etf_tr10001.etf_eq IS '예상체결수량';
COMMENT ON COLUMN t_etf_tr10001.etf_d250h IS '250최고가일';
COMMENT ON COLUMN t_etf_tr10001.etf_vs250h IS '250최고가대비율';
COMMENT ON COLUMN t_etf_tr10001.etf_d250l IS '250최저가일';
COMMENT ON COLUMN t_etf_tr10001.etf_vs250l IS '250최저가대비율';
COMMENT ON COLUMN t_etf_tr10001.etf_pp IS '현재가';
COMMENT ON COLUMN t_etf_tr10001.etf_pinc IS '전일대비';
COMMENT ON COLUMN t_etf_tr10001.etf_pcp IS '등락율';
COMMENT ON COLUMN t_etf_tr10001.etf_vol IS '거래량';
COMMENT ON COLUMN t_etf_tr10001.etf_pcv IS '거래대비';
COMMENT ON COLUMN t_etf_tr10001.etf_fvu IS '액면가단위';
COMMENT ON COLUMN t_etf_tr10001.etf_os IS '유통주식';
COMMENT ON COLUMN t_etf_tr10001.etf_osr IS '유통비율';

COMMIT;
