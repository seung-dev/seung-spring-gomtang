DROP VIEW v_etf_etf0112;
CREATE OR REPLACE VIEW v_etf_etf0112
AS
SELECT
	n0102.item_code
	, n0101.etf_type
	, n0102.date_updt
	, n0102.shar_oust
	, n0102.indx_name
	, n0102.date_set
	, n0102.date_list
	, n0102.asst_clss
	, n0102.expn_rate
	, n0102.acct_perd
	, n0102.date_dstb
	, n0102.issr
	, n0102.issr_url
	, n0102.item_dscr
FROM
	t_etf_n0101 n0101
	, t_etf_n0102 n0102
WHERE 1 = 1
	AND n0101.item_code = n0102.item_code
;