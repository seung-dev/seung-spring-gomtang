DROP FUNCTION IF EXISTS f_etf_etf0101_SL(text);
CREATE OR REPLACE FUNCTION f_etf_etf0101_SL (
	req_json text
)
RETURNS TABLE (
	item_code character varying
	, item_name character varying
	, mmnt character varying
	, date_count character varying
	, etf_cnst character varying
	, etf_stts character varying
	, etf_type character varying
	, etf_fm character varying
	, etf_fv character varying
	, etf_equity character varying
	, etf_is character varying
	, etf_cr character varying
	, etf_yh character varying
	, etf_yl character varying
	, etf_mc character varying
	, etf_mcr character varying
	, etf_for character varying
	, etf_sp character varying
	, etf_250h character varying
	, etf_250l character varying
	, etf_op character varying
	, etf_hp character varying
	, etf_lp character varying
	, etf_hhp character varying
	, etf_llp character varying
	, etf_bp character varying
	, etf_ep character varying
	, etf_eq character varying
	, etf_d250h character varying
	, etf_vs250h character varying
	, etf_d250l character varying
	, etf_vs250l character varying
	, etf_pp character varying
	, etf_pinc character varying
	, etf_pcv character varying
	, etf_fvu character varying
	, etf_os character varying
	, etf_osr character varying
	, trdd character varying
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
	req_trdd varchar(8);
	req_mmnt_date varchar(8);
	req_mmnt_unit varchar(1);
	req_mmnt_scope int;
	req_mmnt_min decimal(10, 2);
	mmnt_unit int := 1;
BEGIN

	SELECT
		req::json->>'trdd' AS trdd
		, req::json->>'mmnt_date' AS mmnt_date
		, req::json->>'mmnt_unit' AS mmnt_unit
		, req::json->'mmnt_scope' AS mmnt_scope
		, req::json->'mmnt_min' AS mmnt_min
	INTO
		req_trdd
		, req_mmnt_date
		, req_mmnt_unit
		, req_mmnt_scope
		, req_mmnt_min
	FROM
		(SELECT CAST(req_json AS json) AS req) init
	;
	
	IF req_mmnt_unit = 'W' THEN
		mmnt_unit = 5;
	ELSEIF req_mmnt_unit = 'M' THEN
		mmnt_unit = 20;
	END IF;
	
	RETURN QUERY
	SELECT
		kw10000.item_code
		, tr10001.item_name
		, calc.mmnt::varchar
		, calc.date_count::varchar
		, kw10000.etf_cnst::varchar
		, kw10000.etf_stts::varchar
		, tr10001.etf_type
		, tr10001.etf_fm::varchar
		, tr10001.etf_fv::varchar
		, tr10001.etf_equity::varchar
		, tr10001.etf_is::varchar
		, tr10001.etf_cr::varchar
		, tr10001.etf_yh::varchar
		, tr10001.etf_yl::varchar
		, tr10001.etf_mc::varchar
		, tr10001.etf_mcr::varchar
		, tr10001.etf_for::varchar
		, tr10001.etf_sp::varchar
		, tr10001.etf_250h::varchar
		, tr10001.etf_250l::varchar
		, tr10001.etf_op::varchar
		, tr10001.etf_hp::varchar
		, tr10001.etf_lp::varchar
		, tr10001.etf_hhp::varchar
		, tr10001.etf_llp::varchar
		, tr10001.etf_bp::varchar
		, tr10001.etf_ep::varchar
		, tr10001.etf_eq::varchar
		, tr10001.etf_d250h::varchar
		, tr10001.etf_vs250h::varchar
		, tr10001.etf_d250l::varchar
		, tr10001.etf_vs250l::varchar
		, tr10001.etf_pp::varchar
		, tr10001.etf_pinc::varchar
		, tr10001.etf_pcv::varchar
		, tr10001.etf_fvu::varchar
		, tr10001.etf_os::varchar
		, tr10001.etf_osr::varchar
		, tr40005.trdd
		, tr40005.etf_cp::varchar
		, tr40005.etf_inc::varchar
		, tr40005.etf_pcp::varchar
		, tr40005.etf_vol::varchar
		, tr40005.etf_nav::varchar
		, tr40005.etf_volaccu::varchar
		, tr40005.etf_indexd::varchar
		, tr40005.etf_etfd::varchar
		, tr40005.etf_ter::varchar
		, tr40005.etf_ti::varchar
		, tr40005.etf_tiinc::varchar
		, EXTRACT(EPOCH FROM tr40005.date_updt)::varchar AS date_updt
	FROM
		t_etf_tr10001 tr10001
		LEFT OUTER JOIN (
			SELECT
				summ.item_code
				, summ.date_count
				, CASE
					WHEN summ.date_count <> req_mmnt_scope THEN -1--mmnt_scope
					ELSE summ.mmnt
					END AS mmnt
			FROM (
				SELECT
					ord.item_code
					, COUNT(*) AS date_count
					, AVG(ord.mmnt) AS mmnt
				FROM (
					SELECT
						tr40005.item_code
						, trd.trdd
						, base.base_no - trd.trdd_no AS trdd_no
						, ABS(base.base_cp / tr40005.etf_cp) AS mmnt
					FROM
						t_etf_trdd trd
						, t_etf_tr40005 tr40005
						LEFT OUTER JOIN (
							SELECT
								a.trdd_no AS base_no
								, b.etf_cp AS base_cp
								, b.item_code
							FROM
								t_etf_trdd a
								, t_etf_tr40005 b
							WHERE 1 = 1
								AND a.trdd = req_mmnt_date
								AND b.trdd = a.trdd
							) base
							ON 1 = 1
							AND base.item_code = tr40005.item_code
					WHERE 1 = 1
						AND trd.trdd = tr40005.trdd
						AND trd.trdd < req_mmnt_date
					) ord
				WHERE 1 = 1
					AND ord.trdd_no % mmnt_unit = 0--trdd_no % unit
					AND ord.trdd_no <= mmnt_unit * req_mmnt_scope--unit * scope
				GROUP BY ord.item_code
				) summ
			) calc
			ON 1 = 1
			AND tr10001.item_code = calc.item_code
		, t_etf_kw10000 kw10000
		, t_etf_tr40005 tr40005
	WHERE 1 = 1
		AND tr10001.item_code = kw10000.item_code
		AND tr10001.item_code = tr40005.item_code
		AND tr40005.trdd = req_trdd
		AND calc.mmnt >= req_mmnt_min
	ORDER BY calc.mmnt DESC
	;

END;
$$
LANGUAGE plpgsql;

COMMIT;
