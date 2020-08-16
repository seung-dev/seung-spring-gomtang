CREATE OR REPLACE FUNCTION f_etf_calc (
)
RETURNS int
AS
$$
DECLARE
	v_base_trdd_no int;
	v_base_trdd varchar(8);
	v_unit int;
	v_scope int;
BEGIN


	SELECT
		trd.trdd_no
		, trd.trdd
	INTO
		v_base_trdd_no
		, v_base_trdd
	FROM
		t_etf_trdd trd
	ORDER BY trd.trdd_no DESC
	LIMIT 1
	;
	
	DELETE FROM t_etf_calc
	WHERE 1 = 1
		AND trdd = v_base_trdd
	;
	
	INSERT INTO t_etf_calc (
		item_code
		, trdd
		, d20_vol_size
		, d20_vol
		, d20_volaccu_size
		, d20_volaccu
		, d5_mmnt_size
		, d5_mmnt
		, d5_mmnt_score
		, d5_std_dev
		, d10_mmnt_size
		, d10_mmnt
		, d10_mmnt_score
		, d10_std_dev
		, d20_mmnt_size
		, d20_mmnt
		, d20_mmnt_score
		, d20_std_dev
		, d60_mmnt_size
		, d60_mmnt
		, d60_mmnt_score
		, d60_std_dev
		)
	SELECT
		calc.item_code
		, v_base_trdd
		, calc.d20_vol_size
		, ROUND(calc.d20_vol / calc.d20_vol_size, 4) AS d20_vol
		, calc.d20_volaccu_size
		, ROUND(calc.d20_volaccu / calc.d20_volaccu_size, 4) AS d20_volaccu
		, calc.d5_mmnt_size
		, ROUND(calc.d5_mmnt / calc.d5_mmnt_size, 4) AS d5_mmnt
		, ROUND(calc.d5_mmnt_score / calc.d5_mmnt_size, 4) AS d5_mmnt_score
		, calc.d5_std_dev
		, calc.d10_mmnt_size
		, ROUND(calc.d10_mmnt / calc.d10_mmnt_size, 4) AS d10_mmnt
		, ROUND(calc.d10_mmnt_score / calc.d10_mmnt_size, 4) AS d10_mmnt_score
		, calc.d10_std_dev
		, calc.d20_mmnt_size
		, ROUND(calc.d20_mmnt / calc.d20_mmnt_size, 4) AS d20_mmnt
		, ROUND(calc.d20_mmnt_score / calc.d20_mmnt_size, 4) AS d20_mmnt_score
		, calc.d20_std_dev
		, calc.d60_mmnt_size
		, ROUND(calc.d60_mmnt / calc.d60_mmnt_size, 4) AS d60_mmnt
		, ROUND(calc.d60_mmnt_score / calc.d60_mmnt_size, 4) AS d60_mmnt_score
		, calc.d60_std_dev
	FROM (
		SELECT
			grp.item_code
			, SUM(grp.d20_vol_size) AS d20_vol_size
			, SUM(grp.d20_vol) AS d20_vol
			, SUM(grp.d20_volaccu_size) AS d20_volaccu_size
			, SUM(grp.d20_volaccu) AS d20_volaccu
			, SUM(grp.d5_mmnt_size) AS d5_mmnt_size
			, SUM(grp.d5_mmnt) AS d5_mmnt
			, SUM(grp.d5_mmnt_score)::numeric AS d5_mmnt_score
			, STDDEV_SAMP(CASE WHEN grp.d5_mmnt_size = 1 THEN grp.d5_std_dev END) AS d5_std_dev
			, SUM(grp.d10_mmnt_size) AS d10_mmnt_size
			, SUM(grp.d10_mmnt) AS d10_mmnt
			, SUM(grp.d10_mmnt_score)::numeric AS d10_mmnt_score
			, STDDEV_SAMP(CASE WHEN grp.d10_mmnt_size = 1 THEN grp.d10_std_dev END) AS d10_std_dev
			, SUM(grp.d20_mmnt_size) AS d20_mmnt_size
			, SUM(grp.d20_mmnt) AS d20_mmnt
			, SUM(grp.d20_mmnt_score)::numeric AS d20_mmnt_score
			, STDDEV_SAMP(CASE WHEN grp.d20_mmnt_size = 1 THEN grp.d20_std_dev END) AS d20_std_dev
			, SUM(grp.d60_mmnt_size) AS d60_mmnt_size
			, SUM(grp.d60_mmnt) AS d60_mmnt
			, SUM(grp.d60_mmnt_score)::numeric AS d60_mmnt_score
			, STDDEV_SAMP(CASE WHEN grp.d60_mmnt_size = 1 THEN grp.d60_std_dev END) AS d60_std_dev
		FROM (
			SELECT
				raw.item_code
				, raw.d20_vol_size
				, raw.d20_vol
				, raw.d20_volaccu_size
				, raw.d20_volaccu
				, raw.d5_mmnt_size
				, raw.d5_mmnt
				, CASE WHEN raw.d5_mmnt > 1 THEN 1 ELSE 0 END AS d5_mmnt_score
				, raw.d5_std_dev
				, raw.d10_mmnt_size
				, raw.d10_mmnt
				, CASE WHEN raw.d10_mmnt > 1 THEN 1 ELSE 0 END AS d10_mmnt_score
				, raw.d10_std_dev
				, raw.d20_mmnt_size
				, raw.d20_mmnt
				, CASE WHEN raw.d20_mmnt > 1 THEN 1 ELSE 0 END AS d20_mmnt_score
				, raw.d20_std_dev
				, raw.d60_mmnt_size
				, raw.d60_mmnt
				, CASE WHEN raw.d60_mmnt > 1 THEN 1 ELSE 0 END AS d60_mmnt_score
				, raw.d60_std_dev
			FROM (
				SELECT
					lst.item_code
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN 1
						ELSE 0
						END AS d20_vol_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN suf.etf_vol
						ELSE 0
						END AS d20_vol
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN 1
						ELSE 0
						END AS d20_volaccu_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN suf.etf_volaccu
						ELSE 0
						END AS d20_volaccu
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 5 THEN 1
						ELSE 0
						END AS d5_mmnt_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 5 THEN lst.etf_cp / pre.etf_cp
						ELSE 0
						END AS d5_mmnt
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 5 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
						ELSE 0
						END AS d5_std_dev
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 10 THEN 1
						ELSE 0
						END AS d10_mmnt_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 10 THEN lst.etf_cp / pre.etf_cp
						ELSE 0
						END AS d10_mmnt
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 10 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
						ELSE 0
						END AS d10_std_dev
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN 1
						ELSE 0
						END AS d20_mmnt_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN lst.etf_cp / pre.etf_cp
						ELSE 0
						END AS d20_mmnt
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 20 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
						ELSE 0
						END AS d20_std_dev
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 60 THEN 1
						ELSE 0
						END AS d60_mmnt_size
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 60 THEN lst.etf_cp / pre.etf_cp
						ELSE 0
						END AS d60_mmnt
					, CASE
						WHEN suf.trdd_no > lst.trdd_no - 60 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
						ELSE 0
						END AS d60_std_dev
				FROM
					(
						SELECT
							trd.trdd_no
							, trd.trdd
							, tr40005.item_code
							, ABS(tr40005.etf_cp) AS etf_cp
						FROM
							t_etf_trdd trd
							, t_kw_tr40005 tr40005
						WHERE
							1 = 1
							AND trd.trdd = tr40005.trdd
							AND trd.trdd = v_base_trdd
					) lst
					, (
						SELECT
							trd.trdd_no
							, trd.trdd
							, tr40005.item_code
							, ABS(tr40005.etf_cp) AS etf_cp
							, ABS(tr40005.etf_vol) AS etf_vol
							, ABS(tr40005.etf_volaccu) AS etf_volaccu
						FROM
							t_etf_trdd trd
							, t_kw_tr40005 tr40005
						WHERE
							1 = 1
							AND trd.trdd = tr40005.trdd
					) suf
					, (
						SELECT
							trd.trdd_no
							, trd.trdd
							, tr40005.item_code
							, ABS(tr40005.etf_cp) AS etf_cp
						FROM
							t_etf_trdd trd
							, t_kw_tr40005 tr40005
						WHERE
							1 = 1
							AND trd.trdd = tr40005.trdd
					) pre
				WHERE 1 = 1
					AND lst.item_code = suf.item_code
					AND lst.item_code = pre.item_code
					AND suf.trdd_no = pre.trdd_no + 1
					AND (lst.trdd_no - suf.trdd_no) < 20 * 12
				) raw
			) grp
		GROUP BY item_code
		) calc
	;
	
	UPDATE t_etf_calc a
	SET
		date_updt = NOW()
		, m3_mmnt_size = b.m3_mmnt_size
		, m3_mmnt = b.m3_mmnt
		, m3_mmnt_score = b.m3_mmnt_score
		, m3_std_dev = b.m3_std_dev
		, m6_mmnt_size = b.m6_mmnt_size
		, m6_mmnt = b.m6_mmnt
		, m6_mmnt_score = b.m6_mmnt_score
		, m6_std_dev = b.m6_std_dev
		, m12_mmnt_size = b.m12_mmnt_size
		, m12_mmnt = b.m12_mmnt
		, m12_mmnt_score = b.m12_mmnt_score
		, m12_std_dev = b.m12_std_dev
	FROM
		(
			SELECT
				calc.item_code
				, calc.m3_mmnt_size
				, ROUND(calc.m3_mmnt / calc.m3_mmnt_size, 4) AS m3_mmnt
				, ROUND(calc.m3_mmnt_score / calc.m3_mmnt_size, 4) AS m3_mmnt_score
				, calc.m3_std_dev
				, calc.m6_mmnt_size
				, ROUND(calc.m6_mmnt / calc.m6_mmnt_size, 4) AS m6_mmnt
				, ROUND(calc.m6_mmnt_score / calc.m6_mmnt_size, 4) AS m6_mmnt_score
				, calc.m6_std_dev
				, calc.m12_mmnt_size
				, ROUND(calc.m12_mmnt / calc.m12_mmnt_size, 4) AS m12_mmnt
				, ROUND(calc.m12_mmnt_score / calc.m12_mmnt_size, 4) AS m12_mmnt_score
				, calc.m12_std_dev
			FROM (
				SELECT
					grp.item_code
					, SUM(grp.m3_mmnt_size) AS m3_mmnt_size
					, SUM(grp.m3_mmnt) AS m3_mmnt
					, SUM(grp.m3_mmnt_score)::numeric AS m3_mmnt_score
					, STDDEV_SAMP(CASE WHEN grp.m3_mmnt_size = 1 THEN grp.m3_std_dev END) AS m3_std_dev
					, SUM(grp.m6_mmnt_size) AS m6_mmnt_size
					, SUM(grp.m6_mmnt) AS m6_mmnt
					, SUM(grp.m6_mmnt_score)::numeric AS m6_mmnt_score
					, STDDEV_SAMP(CASE WHEN grp.m6_mmnt_size = 1 THEN grp.m6_std_dev END) AS m6_std_dev
					, SUM(grp.m12_mmnt_size) AS m12_mmnt_size
					, SUM(grp.m12_mmnt) AS m12_mmnt
					, SUM(grp.m12_mmnt_score)::numeric AS m12_mmnt_score
					, STDDEV_SAMP(CASE WHEN grp.m12_mmnt_size = 1 THEN grp.m12_std_dev END) AS m12_std_dev
				FROM (
					SELECT
						raw.item_code
						, raw.m3_mmnt_size
						, raw.m3_mmnt
						, CASE WHEN raw.m3_mmnt > 1 THEN 1 ELSE 0 END AS m3_mmnt_score
						, raw.m3_std_dev
						, raw.m6_mmnt_size
						, raw.m6_mmnt
						, CASE WHEN raw.m6_mmnt > 1 THEN 1 ELSE 0 END AS m6_mmnt_score
						, raw.m6_std_dev
						, raw.m12_mmnt_size
						, raw.m12_mmnt
						, CASE WHEN raw.m12_mmnt > 1 THEN 1 ELSE 0 END AS m12_mmnt_score
						, raw.m12_std_dev
					FROM (
						SELECT
							lst.item_code
							--, suf.*
							--, pre.*
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 3 THEN 1
								ELSE 0
								END AS m3_mmnt_size
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 3 THEN lst.etf_cp / pre.etf_cp
								ELSE 0
								END AS m3_mmnt
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 3 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
								ELSE 0
								END AS m3_std_dev
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 6 THEN 1
								ELSE 0
								END AS m6_mmnt_size
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 6 THEN lst.etf_cp / pre.etf_cp
								ELSE 0
								END AS m6_mmnt
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 6 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
								ELSE 0
								END AS m6_std_dev
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 12 THEN 1
								ELSE 0
								END AS m12_mmnt_size
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 12 THEN lst.etf_cp / pre.etf_cp
								ELSE 0
								END AS m12_mmnt
							, CASE
								WHEN suf.trdd_no > lst.trdd_no - 20 * 12 THEN (suf.etf_cp - pre.etf_cp) / pre.etf_cp
								ELSE 0
								END AS m12_std_dev
						FROM
							(
								SELECT
									trd.trdd_no
									, trd.trdd
									, tr40005.item_code
									, ABS(tr40005.etf_cp) AS etf_cp
								FROM
									t_etf_trdd trd
									, t_kw_tr40005 tr40005
								WHERE
									1 = 1
									AND trd.trdd = tr40005.trdd
									AND trd.trdd = (
										SELECT MAX(trdd) FROM t_etf_trdd
									)
							) lst
							, (
								SELECT
									trd.trdd_no
									, trd.trdd
									, tr40005.item_code
									, ABS(tr40005.etf_cp) AS etf_cp
								FROM
									t_etf_trdd trd
									, t_kw_tr40005 tr40005
								WHERE
									1 = 1
									AND trd.trdd = tr40005.trdd
							) suf
							, (
								SELECT
									trd.trdd_no
									, trd.trdd
									, tr40005.item_code
									, ABS(tr40005.etf_cp) AS etf_cp
								FROM
									t_etf_trdd trd
									, t_kw_tr40005 tr40005
								WHERE
									1 = 1
									AND trd.trdd = tr40005.trdd
							) pre
						WHERE 1 = 1
							AND lst.item_code = suf.item_code
							AND lst.item_code = pre.item_code
							AND suf.trdd_no = pre.trdd_no + 20
							AND (lst.trdd_no - suf.trdd_no) < 20 * 12
							AND (lst.trdd_no - suf.trdd_no) % 20 = 0
							--AND lst.item_code = '069500'
						--ORDER BY suf.trdd_no DESC
						) raw
					) grp
				GROUP BY item_code
				) calc
		) b
	WHERE 1 = 1
		AND a.item_code = b.item_code
		AND a.trdd = v_base_trdd
	;
	
	RETURN 1;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;