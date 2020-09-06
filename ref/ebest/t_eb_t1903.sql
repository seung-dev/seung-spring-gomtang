DROP TABLE IF EXISTS t_eb_t1903 CASCADE;
CREATE TABLE t_eb_t1903 (
	shcode varchar(6)
	, date varchar(8)
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, hash varchar(32)
	, price int
	, sign varchar(1)
	, change int
	, volume bigint
	, navdiff decimal(9, 2)
	, nav decimal(9, 2)
	, navchange decimal(9, 2)
	, crate decimal(9, 2)
	, grate decimal(9, 2)
	, jisu decimal(8, 2)
	, jichange decimal(8, 2)
	, jirate decimal(8, 2)
);

CREATE UNIQUE INDEX uni_t_eb_t1903 ON t_eb_t1903 (shcode, date DESC);

COMMENT ON TABLE t_eb_t1903 IS '이베스트 ETF 종목 일별 내역';

COMMENT ON COLUMN t_eb_t1903.shcode IS '종목코드';
COMMENT ON COLUMN t_eb_t1903.date IS '거래일자';
COMMENT ON COLUMN t_eb_t1903.date_inst IS '등록일시';
COMMENT ON COLUMN t_eb_t1903.date_updt IS '수정일시';
COMMENT ON COLUMN t_eb_t1903.hash IS 'data hash';
COMMENT ON COLUMN t_eb_t1903.price IS '현재가';
COMMENT ON COLUMN t_eb_t1903.sign IS '종가등락구분 - 1: 상한, 2: 상승, 3: 보합';
COMMENT ON COLUMN t_eb_t1903.change IS '전일대비';
COMMENT ON COLUMN t_eb_t1903.volume IS '누적거래량';
COMMENT ON COLUMN t_eb_t1903.navdiff IS 'NAV대비';
COMMENT ON COLUMN t_eb_t1903.nav IS 'NAV';
COMMENT ON COLUMN t_eb_t1903.navchange IS 'NAV전일대비';
COMMENT ON COLUMN t_eb_t1903.crate IS '추적오차';
COMMENT ON COLUMN t_eb_t1903.grate IS '괴리';
COMMENT ON COLUMN t_eb_t1903.jisu IS '지수';
COMMENT ON COLUMN t_eb_t1903.jichange IS '지수전일대비';
COMMENT ON COLUMN t_eb_t1903.jirate IS '지수전일대비율';

COMMIT;
