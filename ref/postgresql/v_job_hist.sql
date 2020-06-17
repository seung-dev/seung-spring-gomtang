CREATE OR REPLACE VIEW v_job_hist
AS
SELECT
	tjhr.schd_code
	, tjhr.schd_name
	, tjhr.job_group
	, tjhr.job_name
	, tjhr.date_inst AS start_at
	, tjhc.date_inst AS end_at
	, tjhc.date_inst - tjhr.date_inst AS time_elsp
	, tjhc.error_code
	, tjhc.message
	, tjhr.job_data
FROM
	t_job_hist_run tjhr
	LEFT OUTER JOIN t_job_hist_cmpl tjhc
		ON 1 = 1
		AND tjhr.schd_code = tjhc.schd_code
WHERE 1 = 1
;
