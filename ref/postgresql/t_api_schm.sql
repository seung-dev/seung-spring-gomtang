DROP TABLE IF EXISTS t_api_schm CASCADE;

CREATE TABLE t_api_schm (
	api_set varchar(8) NOT NULL,
	api_code varchar(16) NOT NULL,
	api_path varchar(32),
	api_dscr varchar(128),
	api_data varchar(2048),
	api_rslt text
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

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0000';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0000'
, '/rest/etf/etf0000'
, 'API OVERVIEW'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"api_set","name":"API 그룹","type":"string","required":"false", "options": "etf - 현재는 etf만 있음"},{"field":"api_code","name":"API 코드","type":"string","required":"false"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"total_count":"데이터수","etf0000":[{"api_set":"API 그룹","api_code":"API 코드","api_path":"API PATH","api_dscr":"API 설명","api_data":"API 요청 상세","api_rslt":"API 응답 상세"}]}}'
)
;
COMMIT;

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0001';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0001'
, '/rest/etf/etf0001'
, 'API OPTIONS'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"optn_set","name":"옵션 그룹","type":"string","required":"false","format":"api_code.field_type - 예: etf0101.etf_type"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"total_count":"데이터수","etf0001":[{"optn_set":"옵션 그룹","optn_code":"옵션 코드","optn_name_kr":"옵션 한글명","optn_name_en":"옵션 영문명"}]}}'
)
;
COMMIT;

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0101';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0101'
, '/rest/etf/etf0101'
, 'ETF OVERVIEW'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"mmnt_date","name":"모멘텀 기준일","type":"string","required":"true","format":"yyyyMMdd"},{"field":"mmnt_unit","name":"모멘텀 유닛","type":"string","required":"true","options":"D: day, W: week(5 days), M: month(20 days)"},{"field":"mmnt_scope","name":"모멘텀 범위","type":"string","required":"true","exception":"현재 범위를 초과하는 경우 -1로 계산"},{"field":"mmnt_min","name":"최소 모멘텀","type":"string","required":"true"},{"field":"etf_type","name":"ETF 분류","type":"string","options":"1: 국내시장지수, 2: 국내업종테마, 3: 국내파생, 4: 해외주식, 5: 원자재, 6: 채권, 7: 기타 - 네이버 기준"},{"field":"rules","name":"기타 조건","type":"string","format":"json","required":"false"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"trdd":"최신 주식거래일 - yyyyMMdd","trdd_no":"최신 주식거래일 회차 - 기준일: 20200605=0","total_count":"데이터수","etf0101":[{"item_code":"tr10001.item_code - 종목코드","item_name":"tr10001.item_name - 종목명","mmnt":"momentum","date_count":"모멘텀 계산에 사용된 행수","etf_cnst":"kw10000.etf_cnst - stock construction","etf_stts":"kw10000.etf_stts - stock state","etf_type":"n0101.etf_type - 분류","etf_fm":"tr10001.etf_fm - 결산월","etf_fv":"tr10001.etf_fv - 액면가","etf_equity":"tr10001.etf_equity - 자본금","etf_is":"tr10001.etf_is - 상장주식","etf_cr":"tr10001.etf_cr - 신용비율","etf_yh":"tr10001.etf_yh - 연중최고","etf_yl":"tr10001.etf_yl - 연중최저","etf_mc":"tr10001.etf_mc - 시가총액","etf_mcr":"tr10001.etf_mcr - 시가총액비중","etf_for":"tr10001.etf_for - 외인소진률","etf_sp":"tr10001.etf_sp - 대용가","etf_250h":"tr10001.etf_250h - 250최고","etf_250l":"tr10001.etf_250l - 250최저","etf_op":"tr10001.etf_op - 시가","etf_hp":"tr10001.etf_hp - 고가","etf_lp":"tr10001.etf_lp - 저가","etf_hhp":"tr10001.etf_hhp - 상한가","etf_llp":"tr10001.etf_llp - 하한가","etf_bp":"tr10001.etf_bp - 기준가","etf_ep":"tr10001.etf_ep - 예상체결가","etf_eq":"tr10001.etf_eq - 예상체결수량","etf_d250h":"tr10001.etf_d250h - 250최고가일","etf_vs250h":"tr10001.etf_vs250h - 250최고가대비율","etf_d250l":"tr10001.etf_d250l - 250최저가일","etf_vs250l":"tr10001.etf_vs250l - 250최저가대비율","etf_pp":"tr10001.etf_pp - 현재가","etf_pinc":"tr10001.etf_pinc - 전일대비","etf_pcv":"tr10001.etf_pcv - 거래대비","etf_fvu":"tr10001.etf_fvu - 액면가단위","etf_os":"tr10001.etf_os - 유통주식","etf_osr":"tr10001.etf_osr - 유통비율","trdd":"tr40005.trdd - 주식거래일(trading day)","etf_cp":"tr40005.etf_cp - 종가","etf_inc":"tr40005.etf_inc - 전일대비","etf_pcp":"tr40005.etf_pcp - 대비율","etf_vol":"tr40005.etf_vol - 거래량","etf_nav":"tr40005.etf_nav - NAV","etf_volaccu":"tr40005.etf_volaccu - 누적거래대금","etf_indexd":"tr40005.etf_indexd - NAV/지수괴리율","etf_etfd":"tr40005.etf_etfd - NAV/ETF괴리율","etf_ter":"tr40005.etf_ter - 추적오차율","etf_ti":"tr40005.etf_ti - 추적지수","etf_tiinc":"tr40005.etf_tiinc - 추적전일대비","date_updt":"tr40005.date_updt - 수정일시"}]}}'
)
;
COMMIT;

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0111';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0111'
, '/rest/etf/etf0111'
, 'ETF DAILY HISTORY'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"item_code","name":"종목코드","type":"string","required":"true"},{"field":"trdd_from","name":"주식거래일 시작일","type":"string","required":"true","format":"yyyyMMdd"},{"field":"trdd_to","name":"주식거래일 종료일","type":"string","required":"true","format":"yyyyMMdd"},{"field":"page_index","name":"페이지번호","type":"string","required":"true"},{"field":"page_size","name":"페이지당 데이터 수","type":"string","required":"true"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"total_count":"조회수","etf0111":[{"row_num":"데이타 순서","trdd":"주식거래일(trading day)","trdd_no":"주식거래일 회차","item_code":"종목코드","etf_cp":"종가","etf_inc":"전일대비","etf_pcp":"대비율","etf_vol":"거래량","etf_nav":"NAV","etf_volaccu":"누적거래대금","etf_indexd":"NAV/지수괴리율","etf_etfd":"NAV/ETF괴리율","etf_ter":"추적오차율","etf_ti":"추적지수","etf_tiinc":"추적전일대비","date_updt":"수정일시"}]}}'
)
;
COMMIT;

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0112';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0112'
, '/rest/etf/etf0112'
, 'ETF DETAIL - SOURCE: NAVER'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"item_code","name":"종목코드","type":"string","required":"true"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"etf0112":{"item_code":"종목코드","etf_type":"분류","date_updt":"수정일시","shar_oust":"상장주식수","indx_name":"기초지수명","date_set":"최초설정일","date_list":"상장일","asst_clss":"펀드형태","expn_rate":"총보수","acct_perd":"회계기간","date_dstb":"분배금기준일","issr":"자산운용사","issr_url":"홈페이지","item_dscr":"상품설명"}}}'
)
;
COMMIT;

DELETE FROM t_api_schm WHERE api_set = 'etf' AND api_code = 'etf0113';
INSERT INTO t_api_schm VALUES (
'etf'
, 'etf0113'
, '/rest/etf/etf0113'
, 'ETF CU구성목록 - SOURCE: NAVER'
, '[{"field":"request_code","name":"요청코드","type":"string","required":"true"},{"field":"item_code","name":"종목코드","type":"string","required":"true"}]'
, '{"request_code":"요청코드","error_code":"오류코드","error_message":"오류메시지 - 0000: 정상","request_time":"서비스 수신시간","response_time":"서비스 응답시간","data":"요청 쿼리","result":{"total_count":"조회수","etf0113":[{"item_code":"종목코드","item_code_cu":"구성종목 코드","item_name_cu":"구성종목명","date_updt":"수정일자","asst_wght":"비중"}]}}'
)
;
COMMIT;
