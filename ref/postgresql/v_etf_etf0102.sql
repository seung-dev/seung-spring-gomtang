DROP VIEW IF EXISTS v_etf_etf0102;
CREATE OR REPLACE VIEW v_etf_etf0102
AS
SELECT
	trdd_no_min
	, (SELECT trdd FROM t_etf_trdd WHERE trdd_no = grp.trdd_no_min) AS trdd_from
	, trdd_no_max
	, (SELECT trdd FROM t_etf_trdd WHERE trdd_no = grp.trdd_no_max) AS trdd_to
FROM (
	SELECT
		MIN(trdd_no) AS trdd_no_min
		, MAX(trdd_no) AS trdd_no_max
	FROM
		t_etf_trdd
	) grp
;