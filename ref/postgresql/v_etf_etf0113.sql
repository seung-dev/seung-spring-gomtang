DROP VIEW IF EXISTS v_etf_etf0113;
CREATE OR REPLACE VIEW v_etf_etf0113
AS
SELECT
	n0103.item_code
	, n0103.item_code_cu
	, n0103.item_name_cu
	, n0103.date_updt
	, n0103.asst_wght::varchar
FROM
	t_etf_n0103 n0103
ORDER BY n0103.asst_wght DESC
;
