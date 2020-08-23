DROP TABLE IF EXISTS t_eb_t8430 CASCADE;
CREATE TABLE t_eb_t8430 (
	shcode varchar(6)
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, hash varchar(32)
	, gubun varchar(1)
	, etfgubun varchar(6)
	, hname varchar(20)
	, expcode varchar(12)
	, uplmtprice decimal(10, 2)
	, dnlmtprice decimal(10, 2)
	, jnilclose decimal(10, 2)
	, memedan varchar(5)
	, recprice decimal(10, 2)
);

CREATE UNIQUE INDEX uni_t_eb_t8430 ON t_eb_t8430 (shcode);
CREATE INDEX idx_t_eb_t8430 ON t_eb_t8430 (gubun, etfgubun);

COMMENT ON TABLE t_eb_t8430 IS '이베스트 종목 목록';

COMMENT ON COLUMN t_eb_t8430.shcode IS '종목코드';
COMMENT ON COLUMN t_eb_t8430.date_inst IS '등록일시';
COMMENT ON COLUMN t_eb_t8430.date_updt IS '수정일시';
COMMENT ON COLUMN t_eb_t8430.hash IS 'data hash';
COMMENT ON COLUMN t_eb_t8430.gubun IS '구분 - 1: 코스피, 2: 코스닥';
COMMENT ON COLUMN t_eb_t8430.etfgubun IS 'ETF구분 - 1: ETF';
COMMENT ON COLUMN t_eb_t8430.hname IS '종목명';
COMMENT ON COLUMN t_eb_t8430.expcode IS '확장코드';
COMMENT ON COLUMN t_eb_t8430.uplmtprice IS '상한가';
COMMENT ON COLUMN t_eb_t8430.dnlmtprice IS '하한가';
COMMENT ON COLUMN t_eb_t8430.jnilclose IS '전일가';
COMMENT ON COLUMN t_eb_t8430.memedan IS '주문수량단위';
COMMENT ON COLUMN t_eb_t8430.recprice IS '기준가';

COMMIT;
