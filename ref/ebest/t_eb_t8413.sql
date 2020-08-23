DROP TABLE IF EXISTS t_eb_t8413 CASCADE;
CREATE TABLE t_eb_t8413 (
	shcode varchar(6)
	, date varchar(8)
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, hash varchar(32)
	, open decimal(10, 2)
	, high decimal(10, 2)
	, low decimal(10, 2)
	, close decimal(10, 2)
	, jdiff_vol bigint
	, value bigint
	, jongchk bigint
	, rate decimal(6, 2)
	, pricechk bigint
	, ratevalue bigint
	, sign varchar(1)
);

CREATE UNIQUE INDEX uni_t_eb_t8413 ON t_eb_t8413 (shcode, date DESC);

COMMENT ON TABLE t_eb_t8413 IS '이베스트 종목 일별 내역';

COMMENT ON COLUMN t_eb_t8413.shcode IS '종목코드';
COMMENT ON COLUMN t_eb_t8413.date IS '거래일자';
COMMENT ON COLUMN t_eb_t8413.date_inst IS '등록일시';
COMMENT ON COLUMN t_eb_t8413.date_updt IS '수정일시';
COMMENT ON COLUMN t_eb_t8413.hash IS 'data hash';
COMMENT ON COLUMN t_eb_t8413.open IS '시가';
COMMENT ON COLUMN t_eb_t8413.high IS '고가';
COMMENT ON COLUMN t_eb_t8413.low IS '저가';
COMMENT ON COLUMN t_eb_t8413.close IS '종가';
COMMENT ON COLUMN t_eb_t8413.jdiff_vol IS '거래량';
COMMENT ON COLUMN t_eb_t8413.value IS '거래대금';
COMMENT ON COLUMN t_eb_t8413.jongchk IS '수정구분';
COMMENT ON COLUMN t_eb_t8413.rate IS '수정비율';
COMMENT ON COLUMN t_eb_t8413.pricechk IS '수정주가반영항목';
COMMENT ON COLUMN t_eb_t8413.ratevalue IS '수정비율반영거래대금';
COMMENT ON COLUMN t_eb_t8413.sign IS '종가등락구분 - 1: 상한, 2: 상승, 3: 보합';

COMMIT;
