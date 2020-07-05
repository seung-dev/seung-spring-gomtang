CREATE OR REPLACE FUNCTION f_trdd_no (
)
RETURNS TABLE (
	prev int
	, post int
	)
AS
$$
DECLARE
	base_date varchar(8) := '20021014';
	prev_count int := 0;
	post_count int := 0;
BEGIN

	INSERT INTO t_etf_trdd (
		trdd
		, trdd_no
		)
	SELECT
		ord.trdd
		, ord.trdd_no
	FROM (
		SELECT
			ROW_NUMBER() OVER(ORDER BY trdd ASC) AS trdd_no
			, tr40005.trdd
		FROM
			(
				SELECT
					DISTINCT trdd
				FROM
					t_etf_tr40005
				WHERE 1 = 1
					AND trdd >= base_date
			) tr40005
		) ord
		LEFT OUTER JOIN t_etf_trdd trd
			ON 1 = 1
			AND ord.trdd = trd.trdd
	WHERE 1 = 1
		AND trd.trdd IS NULL
	;
	
	GET DIAGNOSTICS post_count = ROW_COUNT;
	
	INSERT INTO t_etf_trdd (
		trdd
		, trdd_no
		)
	SELECT
		ord.trdd
		, ord.trdd_no * -1 + 1 AS trdd_no
	FROM (
		SELECT
			ROW_NUMBER() OVER(ORDER BY trdd DESC) AS trdd_no
			, tr40005.trdd
		FROM
			(
				SELECT
					DISTINCT trdd
				FROM
					t_etf_tr40005
				WHERE 1 = 1
					AND trdd < base_date
			) tr40005
		) ord
		LEFT OUTER JOIN t_etf_trdd trd
			ON 1 = 1
			AND ord.trdd = trd.trdd
	WHERE 1 = 1
		AND trd.trdd IS NULL
	;
	
	GET DIAGNOSTICS prev_count = ROW_COUNT;
	
	RETURN QUERY SELECT prev_count AS prev, post_count AS post;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;