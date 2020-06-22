DROP FUNCTION IF EXISTS f_etf_etf0111_SR(text);
CREATE OR REPLACE FUNCTION f_etf_etf0111_SR (
	req_json text
)
RETURNS TABLE (
	total_count character varying
	)
AS
$$
DECLARE
	req_item_code t_etf_tr40005.item_code%TYPE;
	req_trdd_from t_etf_tr40005.trdd%TYPE;
	req_trdd_to t_etf_tr40005.trdd%TYPE;
BEGIN

	SELECT
		req::json->>'item_code'
		, REGEXP_REPLACE(req::json->>'trdd_from', '[^0-9]', '', 'g') AS trdd_from
		, REGEXP_REPLACE(req::json->>'trdd_to', '[^0-9]', '', 'g') AS trdd_from
	INTO
		req_item_code
		, req_trdd_from
		, req_trdd_to
	FROM
		(SELECT CAST(req_json AS json) AS req) init
	;
	
	RETURN QUERY
	SELECT
		COUNT(*)::varchar AS total_count
	FROM
		t_etf_trdd trd
		, t_etf_tr40005 tr40005
	WHERE 1 = 1
		AND trd.trdd = tr40005.trdd
		AND tr40005.item_code = req_item_code
		AND trd.trdd >= req_trdd_from
		AND trd.trdd <= req_trdd_to
	;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;
