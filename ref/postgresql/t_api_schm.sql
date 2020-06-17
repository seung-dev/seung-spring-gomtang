DROP TABLE IF EXISTS t_api_schm CASCADE;

CREATE TABLE t_api_schm (
	api_set varchar(8) NOT NULL,
	api_code varchar(16) NOT NULL,
	api_path varchar(32),
	api_dscr varchar(128),
	api_data varchar(1024),
	api_rslt varchar(2048)
);
CREATE UNIQUE INDEX pk_t_api_schm ON t_api_schm (api_set, api_code);

COMMENT ON TABLE t_api_schm IS 'API 구조';
COMMENT ON COLUMN t_api_schm.api_set IS 'API 그룹';
COMMENT ON COLUMN t_api_schm.api_code IS 'API 코드';
COMMENT ON COLUMN t_api_schm.api_path IS '경로';
COMMENT ON COLUMN t_api_schm.api_dscr IS '설명';
COMMENT ON COLUMN t_api_schm.api_data IS '요청내용 구조';
COMMENT ON COLUMN t_api_schm.api_rslt IS '응답내용 구조';

COMMIT;

INSERT INTO t_api_schm VALUES
(
	'etf'
	, 'etf0001'
	, '/rest/etf/etf0001'
	, 'ETF 키움 최신 주식거래일 조회'
	, '{}'
	, '{"schema":"스키마","total_count":"데이터수","etf0001":{"trdd":"최신 주식거래일 - yyyyMMdd","trdd_no":"최신 주식거래일 회차 - 기준일: 20200605=0"}}'
)
, (
	'etf'
	, 'etf0101'
	, '/rest/etf/etf0101'
	, 'ETF 키움 종목정보 조회'
	, '[{"field":"trdd","name":"주식거래일 기준일이 모멘텀 기준일과 별도로 필요한가??????????????????????????????","type":"string","nullable":"false","default":"최신 주식거래일","format":"yyyyMMdd"},{"field":"val_min","name":"최소 거래량","type":"string","nullable":"true","default":"0","unit":"1 = 1,000,000"},{"field":"etfd_max","name":"최대 괴리율","type":"string","nullable":"true","default":"디폴트 값이 필요한가???????????????????????????????"},{"field":"mmnt_date","name":"모멘텀 기준일","type":"string","nullable":"true","default":"최신 주식거래일","format":"yyyyMMdd"},{"field":"mmnt_unit","name":"모멘텀 유닛","type":"string","nullable":"true","default":"D","options":"D: day, W: week(1W=5D), M: month(1M=20D)"},{"field":"mmnt_scope","name":"모멘텀 범위","type":"string","nullable":"true","default":"5"},{"field":"mmnt_min","name":"최소 모멘텀","type":"string","nullable":"true","default":"1"},{"field":"item_code","name":"종목코드","type":"string","nullable":"true"}]'
	, '{"schema":"스키마","total_count":"데이터수","trdd":"최신 주식거래일 - yyyyMMdd","trdd_no":"최신 주식거래일 회차 - 기준일: 20200605=0","etf0101":[]}'
	)
, (
	'etf'
	, 'etf0102'
	, '/rest/etf/etf0111'
	, 'ETF 키움 조회종목 일별 추이'
	, '[{"field":"item_code","name":"종목코드","type":"string","nullable":"false"},{"field":"trdd_from","name":"주식거래일 시작일","type":"string","nullable":"true","default":"20","format":"yyyyMMdd"},{"field":"trdd_to","name":"주식거래일 종료일","type":"string","nullable":"true","default":"now","format":"yyyyMMdd"},{"field":"page_index","name":"페이지번호","type":"string","nullable":"true","default":"1"},{"field":"page_size","name":"페이지당 데이터 수","type":"string","nullable":"true","default":"10"}]'
	, '{"schema":"스키마","total_count":"데이터수","etf0102":[{"trdd":"주식거래일(trading day)","trdd_no":"주식거래일 회차","item_code":"종목코드","etf_cp":"종가","etf_inc":"전일대비","etf_pcp":"대비율","etf_vol":"거래량","etf_nav":"NAV","etf_volaccu":"누적거래대금","etf_indexd":"NAV/지수괴리율","etf_etfd":"NAV/ETF괴리율","etf_ter":"추적오차율","etf_ti":"추적지수","etf_tiinc":"추적전일대비","date_updt":"수정일시"}]}'
	)
--, ('etf', 'etf0201', '/rest/etf/etf0201', 'ETF 종목 상세정보(네이버)', '', '')
--, ('etf', 'etf0202', '/rest/etf/etf0202', 'ETF 구성종목(네이버)', '', '')
;

COMMIT;
