DROP FUNCTION f_etf_etf0101(text);
CREATE OR REPLACE FUNCTION f_etf_etf0101 (
	req_json text
)
RETURNS TABLE (
	item_code t_etf_tr10001.item_code%type
	, item_name t_etf_tr10001.item_name%type
	, mmnt decimal
	, date_count bigint
	, etf_cnst t_etf_kw10000.etf_cnst%type
	, etf_stts t_etf_kw10000.etf_stts%type
	, etf_fm t_etf_tr10001.etf_fm%type
	, etf_fv t_etf_tr10001.etf_fv%type
	, etf_equity t_etf_tr10001.etf_equity%type
	, etf_is t_etf_tr10001.etf_is%type
	, etf_cr t_etf_tr10001.etf_cr%type
	, etf_yh t_etf_tr10001.etf_yh%type
	, etf_yl t_etf_tr10001.etf_yl%type
	, etf_mc t_etf_tr10001.etf_mc%type
	, etf_mcr t_etf_tr10001.etf_mcr%type
	, etf_for t_etf_tr10001.etf_for%type
	, etf_sp t_etf_tr10001.etf_sp%type
	, etf_250h t_etf_tr10001.etf_250h%type
	, etf_250l t_etf_tr10001.etf_250l%type
	, etf_op t_etf_tr10001.etf_op%type
	, etf_hp t_etf_tr10001.etf_hp%type
	, etf_lp t_etf_tr10001.etf_lp%type
	, etf_hhp t_etf_tr10001.etf_hhp%type
	, etf_llp t_etf_tr10001.etf_llp%type
	, etf_bp t_etf_tr10001.etf_bp%type
	, etf_ep t_etf_tr10001.etf_ep%type
	, etf_eq t_etf_tr10001.etf_eq%type
	, etf_d250h t_etf_tr10001.etf_d250h%type
	, etf_vs250h t_etf_tr10001.etf_vs250h%type
	, etf_d250l t_etf_tr10001.etf_d250l%type
	, etf_vs250l t_etf_tr10001.etf_vs250l%type
	, etf_pp t_etf_tr10001.etf_pp%type
	, etf_pinc t_etf_tr10001.etf_pinc%type
	, etf_pcv t_etf_tr10001.etf_pcv%type
	, etf_fvu t_etf_tr10001.etf_fvu%type
	, etf_os t_etf_tr10001.etf_os%type
	, etf_osr t_etf_tr10001.etf_osr%type
	, trdd t_etf_tr40005.trdd%type
	, etf_cp t_etf_tr40005.etf_cp%type
	, etf_inc t_etf_tr40005.etf_inc%type
	--, etf_pcp t_etf_tr10001.etf_pcp%type
	, etf_pcp t_etf_tr40005.etf_pcp%type
	--, etf_vol t_etf_tr10001.etf_vol%type
	, etf_vol t_etf_tr40005.etf_vol%type
	, etf_nav t_etf_tr40005.etf_nav%type
	, etf_volaccu t_etf_tr40005.etf_volaccu%type
	, etf_indexd t_etf_tr40005.etf_indexd%type
	, etf_etfd t_etf_tr40005.etf_etfd%type
	, etf_ter t_etf_tr40005.etf_ter%type
	, etf_ti t_etf_tr40005.etf_ti%type
	, etf_tiinc t_etf_tr40005.etf_tiinc%type
	, date_updt t_etf_tr40005.date_updt%type
	)
AS
$$
DECLARE
	req_mmnt_date varchar(8);
	req_mmnt_unit varchar(1);
	req_mmnt_scope int;
	req_mmnt_min decimal(10, 2);
	mmnt_unit int := 1;
BEGIN

	SELECT
		req::json->>'mmnt_date' AS mmnt_date
		, req::json->>'mmnt_unit' AS mmnt_unit
		, req::json->'mmnt_scope' AS mmnt_scope
		, req::json->'mmnt_min' AS mmnt_min
	INTO
		req_mmnt_date
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
		, calc.mmnt
		, calc.date_count
		, kw10000.etf_cnst
		, kw10000.etf_stts
		, tr10001.etf_fm
		, tr10001.etf_fv
		, tr10001.etf_equity
		, tr10001.etf_is
		, tr10001.etf_cr
		, tr10001.etf_yh
		, tr10001.etf_yl
		, tr10001.etf_mc
		, tr10001.etf_mcr
		, tr10001.etf_for
		, tr10001.etf_sp
		, tr10001.etf_250h
		, tr10001.etf_250l
		, tr10001.etf_op
		, tr10001.etf_hp
		, tr10001.etf_lp
		, tr10001.etf_hhp
		, tr10001.etf_llp
		, tr10001.etf_bp
		, tr10001.etf_ep
		, tr10001.etf_eq
		, tr10001.etf_d250h
		, tr10001.etf_vs250h
		, tr10001.etf_d250l
		, tr10001.etf_vs250l
		, tr10001.etf_pp
		, tr10001.etf_pinc
		, tr10001.etf_pcv
		, tr10001.etf_fvu
		, tr10001.etf_os
		, tr10001.etf_osr
		, tr40005.trdd
		, tr40005.etf_cp
		, tr40005.etf_inc
		--, tr10001.etf_pcp
		, tr40005.etf_pcp
		--, tr10001.etf_vol
		, tr40005.etf_vol
		, tr40005.etf_nav
		, tr40005.etf_volaccu
		, tr40005.etf_indexd
		, tr40005.etf_etfd
		, tr40005.etf_ter
		, tr40005.etf_ti
		, tr40005.etf_tiinc
		, tr40005.date_updt
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
		AND tr40005.trdd = (SELECT inln.trdd FROM t_etf_trdd inln ORDER BY inln.trdd_no DESC LIMIT 1)
		AND calc.mmnt >= req_mmnt_min
	ORDER BY calc.mmnt DESC
	;

END;
$$
LANGUAGE plpgsql;

COMMIT;
