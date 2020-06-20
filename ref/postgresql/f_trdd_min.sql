CREATE OR REPLACE FUNCTION f_trdd_min (
	req_scope int
)
RETURNS varchar(8)
AS
$$
DECLARE
	trdd_min varchar(8);
BEGIN

	SELECT
		trdd
	INTO
		trdd_min
	FROM
		t_etf_trdd
	WHERE 1 = 1
		AND trdd_no = (
			SELECT
				MAX(trdd_no) - req_scope
			FROM
				t_etf_trdd
			)
	;
	
	RETURN trdd_min;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;