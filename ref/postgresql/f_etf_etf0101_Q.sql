DROP FUNCTION IF EXISTS f_etf_etf0101_Q(text);
CREATE OR REPLACE FUNCTION f_etf_etf0101_Q (
	req_json text
)
RETURNS TEXT
AS
$$
DECLARE
	v_trdd varchar(8);
	v_mmnt_date varchar(8);
	v_mmnt_unit int;
	v_mmnt_scope int;
	v_mmnt_min_max varchar(16);
	v_mmnt_threshold decimal(10, 2);
	v_etf_type varchar(1);
	v_rules TEXT := '';
	query_text TEXT := '';
BEGIN

	query_text := CONCAT(
		query_text
		, '  SELECT'
		, '      kw10000.item_code'
		, '      , tr10001.item_name'
		, '      , calc.mmnt::varchar'
		, '      , calc.date_count::varchar'
		, '      , kw10000.etf_cnst::varchar'
		, '      , kw10000.etf_stts::varchar'
		, '      , tr10001.etf_type'
		, '      , tr10001.etf_fm::varchar'
		, '      , tr10001.etf_fv::varchar'
		, '      , tr10001.etf_equity::varchar'
		, '      , tr10001.etf_is::varchar'
		, '      , tr10001.etf_cr::varchar'
		, '      , tr10001.etf_yh::varchar'
		, '      , tr10001.etf_yl::varchar'
		, '      , tr10001.etf_mc::varchar'
		, '      , tr10001.etf_mcr::varchar'
		, '      , tr10001.etf_for::varchar'
		, '      , tr10001.etf_sp::varchar'
		, '      , tr10001.etf_250h::varchar'
		, '      , tr10001.etf_250l::varchar'
		, '      , tr10001.etf_op::varchar'
		, '      , tr10001.etf_hp::varchar'
		, '      , tr10001.etf_lp::varchar'
		, '      , tr10001.etf_hhp::varchar'
		, '      , tr10001.etf_llp::varchar'
		, '      , tr10001.etf_bp::varchar'
		, '      , tr10001.etf_ep::varchar'
		, '      , tr10001.etf_eq::varchar'
		, '      , tr10001.etf_d250h::varchar'
		, '      , tr10001.etf_vs250h::varchar'
		, '      , tr10001.etf_d250l::varchar'
		, '      , tr10001.etf_vs250l::varchar'
		, '      , tr10001.etf_pp::varchar'
		, '      , tr10001.etf_pinc::varchar'
		, '      , tr10001.etf_pcv::varchar'
		, '      , tr10001.etf_fvu::varchar'
		, '      , tr10001.etf_os::varchar'
		, '      , tr10001.etf_osr::varchar'
		, '      , tr40005.trdd'
		, '      , ABS(tr40005.etf_cp)::varchar'
		, '      , tr40005.etf_inc::varchar'
		, '      , tr40005.etf_pcp::varchar'
		, '      , ABS(tr40005.etf_vol)::varchar'
		, '      , ABS(tr40005.etf_nav)::varchar'
		, '      , ABS(tr40005.etf_volaccu)::varchar'
		, '      , tr40005.etf_indexd::varchar'
		, '      , tr40005.etf_etfd::varchar'
		, '      , tr40005.etf_ter::varchar'
		, '      , tr40005.etf_ti::varchar'
		, '      , tr40005.etf_tiinc::varchar'
		, '      , EXTRACT(EPOCH FROM tr40005.date_updt)::varchar AS date_updt'
		);
	query_text := CONCAT(
		query_text
		, '  FROM'
		, '      t_etf_tr10001 tr10001'
		, '      LEFT OUTER JOIN ('
		, '          SELECT'
		, '              summ.item_code'
		, '              , summ.date_count'
		, '              , CASE'
		, '                  WHEN summ.date_count <> v_mmnt_scope THEN -1'
		, '                  ELSE summ.mmnt'
		, '                  END AS mmnt'
		, '          FROM ('
		, '              SELECT'
		, '                  ord.item_code'
		, '                  , COUNT(*) AS date_count'
		, '                  , AVG(ord.mmnt) AS mmnt'
		, '              FROM ('
		, '                  SELECT'
		, '                      tr40005.item_code'
		, '                      , trd.trdd'
		, '                      , base.base_no - trd.trdd_no AS trdd_no'
		, '                      , ABS(base.base_cp / tr40005.etf_cp) AS mmnt'
		, '                  FROM'
		, '                      t_etf_trdd trd'
		, '                      , t_etf_tr40005 tr40005'
		, '                      LEFT OUTER JOIN ('
		, '                          SELECT'
		, '                              a.trdd_no AS base_no'
		, '                              , b.etf_cp AS base_cp'
		, '                              , b.item_code'
		, '                          FROM'
		, '                              t_etf_trdd a'
		, '                              , t_etf_tr40005 b'
		, '                          WHERE 1 = 1'
		, '                              AND a.trdd = v_mmnt_date'
		, '                              AND b.trdd = a.trdd'
		, '                          ) base'
		, '                          ON 1 = 1'
		, '                          AND base.item_code = tr40005.item_code'
		, '                  WHERE 1 = 1'
		, '                      AND trd.trdd = tr40005.trdd'
		, '                      AND trd.trdd < v_mmnt_date'
		, '                  ) ord'
		, '              WHERE 1 = 1'
		, '                  AND ord.trdd_no % v_mmnt_unit = 0'
		, '                  AND ord.trdd_no <= v_mmnt_unit * v_mmnt_scope'
		, '              GROUP BY ord.item_code'
		, '              ) summ'
		, '          ) calc'
		, '          ON 1 = 1'
		, '          AND tr10001.item_code = calc.item_code'
		, '      , t_etf_kw10000 kw10000'
		, '      , t_etf_tr40005 tr40005'
		);
	query_text := CONCAT(
		query_text
		, '  WHERE 1 = 1'
		, '      AND tr10001.item_code = kw10000.item_code'
		, '      AND tr10001.item_code = tr40005.item_code'
		, '      AND tr40005.trdd = v_trdd'
		, '      v_rules'
		--, '      AND calc.mmnt >= v_mmnt_threshold'
		, '      v_mmnt'
		--, '  ORDER BY calc.mmnt v_mmnt_min_max'
		, '  v_sort'
		, '  ;'
		)
		;
		
	SELECT
		req.trdd
		, CASE
			WHEN COALESCE(req.mmnt_date, '') = '' THEN req.trdd
			ELSE req.mmnt_date
			END AS mmnt_date
		, CASE
			WHEN LOWER(req.mmnt_unit) = 'd' THEN 1
			WHEN LOWER(req.mmnt_unit) = 'w' THEN 5
			WHEN LOWER(req.mmnt_unit) = 'm' THEN 20
			ELSE 0
			END AS mmnt_unit
		, req.mmnt_scope
		, COALESCE(req.rules, '')
		, req.mmnt_min_max
		, req.mmnt_threshold
	INTO
		v_trdd
		, v_mmnt_date
		, v_mmnt_unit
		, v_mmnt_scope
		, v_rules
		, v_mmnt_min_max
		, v_mmnt_threshold
	FROM (
		SELECT
			regexp_replace(req::json->>'trdd', '[^0-9]', '', 'g') AS trdd
			, regexp_replace(req::json->>'mmnt_date', '[^0-9]', '', 'g') AS mmnt_date
			, req::json->>'mmnt_unit' AS mmnt_unit
			, req::json->'mmnt_scope' AS mmnt_scope
			, req::json->>'rules' AS rules
			, req::json->>'mmnt_min_max' AS mmnt_min_max
			, req::json->'mmnt_threshold' AS mmnt_threshold
		FROM
			(SELECT CAST(req_json AS json) AS req) init
		) req
	;
	
	query_text := REPLACE(query_text, 'v_trdd', CONCAT('''', v_trdd, ''''));
	query_text := REPLACE(query_text, 'v_mmnt_date', CONCAT('''', v_mmnt_date, ''''));
	query_text := REPLACE(query_text, 'v_mmnt_unit', COALESCE(v_mmnt_unit, 1)::varchar);
	query_text := REPLACE(query_text, 'v_mmnt_scope', COALESCE(v_mmnt_scope, 3)::varchar);
	query_text := REPLACE(query_text, 'v_rules', v_rules);
	
	IF LOWER(v_mmnt_min_max) = 'min' THEN
		query_text := REPLACE(query_text, 'v_mmnt', 'AND calc.mmnt >= v_mmnt_threshold');
		query_text := REPLACE(query_text, 'v_mmnt_threshold', COALESCE(v_mmnt_threshold, -1)::varchar);
		query_text := REPLACE(query_text, 'v_sort', 'ORDER BY calc.mmnt DESC');
	ELSEIF LOWER(v_mmnt_min_max) = 'max' THEN
		query_text := REPLACE(query_text, 'v_mmnt', 'AND calc.mmnt <= v_mmnt_threshold');
		query_text := REPLACE(query_text, 'v_mmnt_threshold', COALESCE(v_mmnt_threshold, -1)::varchar);
		query_text := REPLACE(query_text, 'v_sort', 'ORDER BY calc.mmnt ASC');
	ELSE
		query_text := REPLACE(query_text, 'v_mmnt', '');
		query_text := REPLACE(query_text, 'v_sort', 'ORDER BY calc.mmnt DESC');
	END IF;
	
	RETURN query_text;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;
