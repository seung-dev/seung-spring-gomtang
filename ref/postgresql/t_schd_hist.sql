CREATE OR REPLACE VIEW v_schd_hist
AS
SELECT
	prev.schd_no
	, prev.schd_set
	, prev.schd_code
	, prev.date_inst AS start_at
	, post.date_inst AS end_at
	, post.date_inst - prev.date_inst AS time_elsp
	, post.error_code
	, post.message
	, prev.schd_data
FROM
	t_schd_prev prev
	LEFT OUTER JOIN t_schd_post post
		ON 1 = 1
		AND prev.schd_no = post.schd_no
;

COMMIT;
