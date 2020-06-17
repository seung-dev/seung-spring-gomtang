CREATE OR REPLACE VIEW v_etf_etf0111
AS
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
	, tr40005.date_updt
FROM
	t_etf_trdd trd
	, t_etf_tr40005 tr40005
WHERE 1 = 1
	AND trd.trdd = tr40005.trdd
;

COMMIT;
