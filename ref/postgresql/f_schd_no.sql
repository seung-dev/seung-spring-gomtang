CREATE OR REPLACE FUNCTION public.f_schd_no()
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE
	schd_no bigint;
	next_val bigint;
BEGIN

	SELECT
		TO_CHAR(CURRENT_DATE, 'yyyymmdd')::bigint * '100000000'::bigint + 1
	INTO
		schd_no
	;
	
	next_val = NEXTVAL('s_schd_no');
	
	IF next_val < schd_no THEN
		EXECUTE 'ALTER SEQUENCE s_schd_no RESTART WITH '||schd_no::varchar;
		schd_no = NEXTVAL('s_schd_no');
	ELSE
		schd_no = next_val;
	END IF;
	
	RETURN schd_no::varchar;
	
END;
$function$
;
