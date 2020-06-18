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
	, 'etf0101'
	, '/rest/etf/etf0101'
	, 'ETF 키움 종목정보 조회'
	, '[{"field":"vol_min","name":"최소 거래량 - 현재 동작하지 않음","type":"string","nullable":"false","unit":"1 = 1,000,000"},{"field":"etfd_max","name":"최대 괴리율 - 현재 동작하지 않음","type":"string","nullable":"false"},{"field":"mmnt_date","name":"모멘텀 기준일","type":"string","nullable":"false","format":"yyyyMMdd"},{"field":"mmnt_unit","name":"모멘텀 유닛","type":"string","nullable":"false","options":"D: day, W: week(5 days), M: month(20 days)"},{"field":"mmnt_scope","name":"모멘텀 범위","type":"string","nullable":"false","exception":"현재 범위를 초과하는 경우 -1로 계산"},{"field":"mmnt_min","name":"최소 모멘텀","type":"string","nullable":"false"}]'
	, '{"schema":"스키마","total_count":"데이터수","trdd":"최신 주식거래일 - yyyyMMdd","trdd_no":"최신 주식거래일 회차 - 기준일: 20200605=0","etf0101":[{"item_code":"tr10001.item_code - 종목코드","item_name":"tr10001.item_name - 종목명","mmnt":"momentum","date_count":"모멘텀 계산에 사용된 행수","etf_cnst":"kw10000.etf_cnst - stock construction","etf_stts":"kw10000.etf_stts - stock state","etf_fm":"tr10001.etf_fm - 결산월","etf_fv":"tr10001.etf_fv - 액면가","etf_equity":"tr10001.etf_equity - 자본금","etf_is":"tr10001.etf_is - 상장주식","etf_cr":"tr10001.etf_cr - 신용비율","etf_yh":"tr10001.etf_yh - 연중최고","etf_yl":"tr10001.etf_yl - 연중최저","etf_mc":"tr10001.etf_mc - 시가총액","etf_mcr":"tr10001.etf_mcr - 시가총액비중","etf_for":"tr10001.etf_for - 외인소진률","etf_sp":"tr10001.etf_sp - 대용가","etf_250h":"tr10001.etf_250h - 250최고","etf_250l":"tr10001.etf_250l - 250최저","etf_op":"tr10001.etf_op - 시가","etf_hp":"tr10001.etf_hp - 고가","etf_lp":"tr10001.etf_lp - 저가","etf_hhp":"tr10001.etf_hhp - 상한가","etf_llp":"tr10001.etf_llp - 하한가","etf_bp":"tr10001.etf_bp - 기준가","etf_ep":"tr10001.etf_ep - 예상체결가","etf_eq":"tr10001.etf_eq - 예상체결수량","etf_d250h":"tr10001.etf_d250h - 250최고가일","etf_vs250h":"tr10001.etf_vs250h - 250최고가대비율","etf_d250l":"tr10001.etf_d250l - 250최저가일","etf_vs250l":"tr10001.etf_vs250l - 250최저가대비율","etf_pp":"tr10001.etf_pp - 현재가","etf_pinc":"tr10001.etf_pinc - 전일대비","etf_pcv":"tr10001.etf_pcv - 거래대비","etf_fvu":"tr10001.etf_fvu - 액면가단위","etf_os":"tr10001.etf_os - 유통주식","etf_osr":"tr10001.etf_osr - 유통비율","trdd":"tr40005.trdd - 주식거래일(trading day)","etf_cp":"tr40005.etf_cp - 종가","etf_inc":"tr40005.etf_inc - 전일대비","etf_pcp":"tr40005.etf_pcp - 대비율","etf_vol":"tr40005.etf_vol - 거래량","etf_nav":"tr40005.etf_nav - NAV","etf_volaccu":"tr40005.etf_volaccu - 누적거래대금","etf_indexd":"tr40005.etf_indexd - NAV/지수괴리율","etf_etfd":"tr40005.etf_etfd - NAV/ETF괴리율","etf_ter":"tr40005.etf_ter - 추적오차율","etf_ti":"tr40005.etf_ti - 추적지수","etf_tiinc":"tr40005.etf_tiinc - 추적전일대비","date_updt":"tr40005.date_updt - 수정일시"}]}'
	)
, (
	'etf'
	, 'etf0111'
	, '/rest/etf/etf0111'
	, 'ETF 키움 조회종목 일별 추이'
	, '[{"field":"item_code","name":"종목코드","type":"string","nullable":"false"},{"field":"trdd_from","name":"주식거래일 시작일","type":"string","nullable":"true","default":"주식거래일 20일 전","format":"yyyyMMdd"},{"field":"trdd_to","name":"주식거래일 종료일","type":"string","nullable":"true","default":"최신 주식거래일","format":"yyyyMMdd"},{"field":"page_index","name":"페이지번호","type":"string","nullable":"true","default":"1"},{"field":"page_size","name":"페이지당 데이터 수","type":"string","nullable":"true","default":"10"}]'
	, '{"schema":"스키마","total_count":"데이터수","etf0102":[{"trdd":"주식거래일(trading day)","trdd_no":"주식거래일 회차","item_code":"종목코드","etf_cp":"종가","etf_inc":"전일대비","etf_pcp":"대비율","etf_vol":"거래량","etf_nav":"NAV","etf_volaccu":"누적거래대금","etf_indexd":"NAV/지수괴리율","etf_etfd":"NAV/ETF괴리율","etf_ter":"추적오차율","etf_ti":"추적지수","etf_tiinc":"추적전일대비","date_updt":"수정일시"}]}'
	)
, (
	'etf'
	, 'etf0112'
	, '/rest/etf/etf0112'
	, 'ETF 종목 상세정보(네이버)'
	, ''
	, ''
	)
--, ('etf', 'etf0202', '/rest/etf/etf0202', 'ETF 구성종목(네이버)', '', '')
;

COMMIT;
