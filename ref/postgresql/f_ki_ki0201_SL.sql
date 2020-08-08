DROP FUNCTION IF EXISTS f_ki_ki0201_SL(text);
CREATE OR REPLACE FUNCTION f_ki_ki0201_SL (
	req_json text
)
RETURNS TABLE (
	row_num bigint
	, item_code character varying
	, item_data int
	)
AS
$$
DECLARE
BEGIN

	SELECT
		req::json->>'item_code'
		, REGEXP_REPLACE(req::json->>'trdd_from', '[^0-9]', '', 'g') AS trdd_from
		, REGEXP_REPLACE(req::json->>'trdd_to', '[^0-9]', '', 'g') AS trdd_to
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
	
	RETURN QUERY
	SELECT
		ROW_NUMBER() OVER(ORDER BY n0104.item_cod) AS row_num
		, n0104.item_code
		, JSON_AGG(
			JSON_BUILD_OBJECT(
				'item_sd'
				, n0104.item_sd
				, 'is_est'
				, n0104.is_est
				, 'item_ta'
				, n0104.item_ta
				, 'item_tl'
				, n0104.item_tl
				, 'item_te'
				, n0104.item_te
				, 'item_tr'
				, n0104.item_tr
				, 'item_oi'
				, n0104.item_oi
				, 'item_cfo'
				, n0104.item_cfo
				, 'item_de'
				, n0104.item_de
				)
			) AS item_data
	FROM (
		SELECT
			item_code
			, item_sd
			, is_est
			, item_ta
			, item_tl
			, item_te
			, item_tr
			, item_oi
			, item_cfo
			, item_de
		FROM
			t_item_n0104
		WHERE 1 = 1
			AND item_sd >= '201901'
		) n0104
	GROUP BY
		n0104.item_code
	;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;


CREATE OR REPLACE VIEW v_ki_Ki0201
AS
SELECT
	n0104.item_code
	, JSON_AGG(
		JSON_BUILD_OBJECT(
			'item_sd'
			, n0104.item_sd
			, 'is_est'
			, n0104.is_est
			, 'item_ta'
			, n0104.item_ta
			, 'item_tl'
			, n0104.item_tl
			, 'item_te'
			, n0104.item_te
			, 'item_tr'
			, n0104.item_tr
			, 'item_oi'
			, n0104.item_oi
			, 'item_cfo'
			, n0104.item_cfo
			, 'item_de'
			, n0104.item_de
			)
		) AS item_data
FROM (
	SELECT
		item_code
		, item_sd
		, is_est
		, item_ta
		, item_tl
		, item_te
		, item_tr
		, item_oi
		, item_cfo
		, item_de
	FROM
		t_item_n0104
	WHERE 1 = 1
		AND item_sd >= '201901'
	) n0104
GROUP BY
	n0104.item_code
;