DROP FUNCTION IF EXISTS f_etf_etf0111_SL(text);
CREATE OR REPLACE FUNCTION f_etf_etf0111_SL (
	req_json text
)
RETURNS TABLE (
	row_num character varying
	, trdd character varying
	, trdd_no character varying
	, item_code character varying
	, etf_cp character varying
	, etf_inc character varying
	, etf_pcp character varying
	, etf_vol character varying
	, etf_nav character varying
	, etf_volaccu character varying
	, etf_indexd character varying
	, etf_etfd character varying
	, etf_ter character varying
	, etf_ti character varying
	, etf_tiinc character varying
	, date_updt character varying
	)
AS
$$
DECLARE
	req_item_code t_etf_tr40005.item_code%TYPE;
	req_trdd_from t_etf_tr40005.trdd%TYPE;
	req_trdd_to t_etf_tr40005.trdd%TYPE;
	req_page_index int;
	req_page_size int;
	row_from int;
	row_to int;
BEGIN

	SELECT
		req::json->>'item_code'
		, req::json->>'trdd_from'
		, req::json->>'trdd_to'
		, req::json->'page_index'
		, req::json->'page_size'
	INTO
		req_item_code
		, req_trdd_from
		, req_trdd_to
		, req_page_index
		, req_page_size
	FROM
		(SELECT CAST(req_json AS json) AS req) init
	;
	
	row_from = (req_page_index - 1) * req_page_size + 1;
	row_to = req_page_index * req_page_size;
	
	RETURN QUERY
	SELECT
		etf0111.row_num::varchar
		, etf0111.trdd
		, etf0111.trdd_no::varchar
		, etf0111.item_code
		, etf0111.etf_cp::varchar
		, etf0111.etf_inc::varchar
		, etf0111.etf_pcp::varchar
		, etf0111.etf_vol::varchar
		, etf0111.etf_nav::varchar
		, etf0111.etf_volaccu::varchar
		, etf0111.etf_indexd::varchar
		, etf0111.etf_etfd::varchar
		, etf0111.etf_ter::varchar
		, etf0111.etf_ti::varchar
		, etf0111.etf_tiinc::varchar
		, etf0111.date_updt::varchar
	FROM (
		SELECT
			ROW_NUMBER() OVER(ORDER BY trd.trdd DESC) AS row_num
			, tr40005.trdd
			, trd.trdd_no
			, tr40005.item_code
			, tr40005.etf_cp
			, tr40005.etf_inc
			, tr40005.etf_pcp
			, tr40005.etf_vol
			, tr40005.etf_nav
			, tr40005.etf_volaccu
			, tr40005.etf_indexd
			, tr40005.etf_etfd
			, tr40005.etf_ter
			, tr40005.etf_ti
			, tr40005.etf_tiinc
			, EXTRACT(EPOCH FROM tr40005.date_updt) AS date_updt
		FROM
			t_etf_trdd trd
			, t_etf_tr40005 tr40005
		WHERE 1 = 1
			AND trd.trdd = tr40005.trdd
			AND tr40005.item_code = req_item_code
			AND trd.trdd >= req_trdd_from
			AND trd.trdd <= req_trdd_to
		) etf0111
	WHERE 1 = 1
		AND etf0111.row_num >= row_from
		AND etf0111.row_num <= row_to
		;

END;
$$
LANGUAGE plpgsql;

COMMIT;