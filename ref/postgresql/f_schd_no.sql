CREATE OR REPLACE FUNCTION f_schd_no (
)
RETURNS varchar(16)
AS
$$
DECLARE
	schd_date int;
	cast_date int;
	schd_no varchar(16);
BEGIN

	SELECT
		LAST_VALUE
	INTO
		schd_date
	FROM
		s_date
	;
	
	SELECT
		CAST(TO_CHAR(CURRENT_DATE, 'yyyymmdd') AS int)
	INTO
		cast_date
	;
	
	IF cast_date <> schd_date THEN
		SELECT SETVAL('s_date', cast_date, false);
		SELECT
			NEXTVAL('s_date')
		INTO
			schd_date
		;
		ALTER SEQUENCE s_job_no_of_day RESTART WITH 1;
	END IF;
	
	SELECT
		CONCAT(schd_date::varchar, LPAD(NEXTVAL('s_schd_no_of_day')::varchar, 8, '0'))
	INTO
		schd_no
	;
	
	RETURN schd_no;
	
END;
$$
LANGUAGE plpgsql;

COMMIT;