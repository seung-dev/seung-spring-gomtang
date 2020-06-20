CREATE OR REPLACE VIEW v_etf_tr40005_prtt
AS
SELECT
	prtt.rnk
	, prtt.item_code
	, prtt.trdd
	, prtt.trdd_no
FROM (
	SELECT
		RANK() OVER (PARTITION BY tr40005.item_code ORDER BY trd.trdd_no DESC) AS rnk
		, tr40005.item_code
		, tr40005.trdd
		, trd.trdd_no
	FROM
		t_etf_trdd trd
		, t_etf_tr40005 tr40005
	WHERE 1 = 1
		AND trd.trdd = tr40005.trdd
	) prtt
;

COMMIT;
