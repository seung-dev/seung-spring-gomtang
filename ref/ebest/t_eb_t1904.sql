DROP TABLE IF EXISTS t_eb_t1904 CASCADE;
CREATE TABLE t_eb_t1904 (
	shcode varchar(6)
	, cu_shcode varchar(6)
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, hash varchar(32)
	, hname varchar(20)
	, price decimal(10, 2)
	, sign varchar(1)
	, change decimal(10, 2)
	, diff decimal(6, 2)
	, volume bigint
	, value bigint
	, icux bigint
	, parprice bigint
	, pvalue bigint
	, sigatvalue bigint
	, profitdate varchar(8)
	
	, weight decimal(6, 2)
	, diff2 decimal(6, 2)
);

CREATE UNIQUE INDEX uni_t_eb_t1904 ON t_eb_t1904 (shcode, cu_shcode);

COMMENT ON TABLE t_eb_t1904 IS '이베스트 ETF 구성종목';

COMMENT ON COLUMN t_eb_t1904.shcode IS '종목코드';
COMMENT ON COLUMN t_eb_t1904.cu_shcode IS 'CU종목코드';
COMMENT ON COLUMN t_eb_t1904.date_inst IS '등록일시';
COMMENT ON COLUMN t_eb_t1904.date_updt IS '수정일시';
COMMENT ON COLUMN t_eb_t1904.hash IS 'data hash';
COMMENT ON COLUMN t_eb_t1904.hname IS 'CU종목명';
COMMENT ON COLUMN t_eb_t1904.price IS '현재가';
COMMENT ON COLUMN t_eb_t1904.sign IS '종가등락구분 - 1: 상한, 2: 상승, 3: 보합';
COMMENT ON COLUMN t_eb_t1904.change IS '전일대비';
COMMENT ON COLUMN t_eb_t1904.diff IS '등락율';
COMMENT ON COLUMN t_eb_t1904.volume IS '누적거래량';
COMMENT ON COLUMN t_eb_t1904.value IS '거래대금(백만)';
COMMENT ON COLUMN t_eb_t1904.icux IS '단위증권수(계약수/원화현금/USD)';
COMMENT ON COLUMN t_eb_t1904.parprice IS '액면금액/설정현금액';
COMMENT ON COLUMN t_eb_t1904.pvalue IS '평가금액';
COMMENT ON COLUMN t_eb_t1904.sigatvalue IS '구성시가총액';
COMMENT ON COLUMN t_eb_t1904.profitdate IS 'PDF적용일자';
COMMENT ON COLUMN t_eb_t1904.weight IS '비중(평가금액)';
COMMENT ON COLUMN t_eb_t1904.diff2 IS 'ETF종목과등락차';

COMMIT;
