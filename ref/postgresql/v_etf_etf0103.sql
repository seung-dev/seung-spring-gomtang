DROP VIEW IF EXISTS v_etf_etf0103;
CREATE OR REPLACE VIEW v_etf_etf0103
AS
SELECT
	kw10000.item_code
	, kw10000.item_name
	, n0101.etf_type
	, n0102.date_list
FROM
	t_kw_kw10000 kw10000
	, t_etf_n0101 n0101
	, t_etf_n0102 n0102
WHERE 1 = 1
	AND kw10000.item_code = n0101.item_code
	AND kw10000.item_code = n0102.item_code
	AND kw10000.mrkt_type = '3'
ORDER BY kw10000.item_code
;