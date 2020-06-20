DROP TABLE IF EXISTS t_api_optn CASCADE;

CREATE TABLE t_api_optn (
	optn_set varchar(16) NOT NULL,
	optn_code varchar(8) NOT NULL,
	optn_name_kr varchar(64) NOT NULL,
	optn_name_en varchar(64) NOT NULL
);
CREATE UNIQUE INDEX pk_t_api_optn ON t_api_optn (optn_set, optn_code);

COMMENT ON TABLE t_api_optn IS 'API 옵션 목록';
COMMENT ON COLUMN t_api_optn.optn_set IS '옵션 그룹';
COMMENT ON COLUMN t_api_optn.optn_code IS '옵션 코드';
COMMENT ON COLUMN t_api_optn.optn_name_kr IS '옵션 한글명';
COMMENT ON COLUMN t_api_optn.optn_name_en IS '옵션 영문명';

COMMIT;

INSERT INTO t_api_optn VALUES
	(
		'etf0000.mmnt_unit'
		, 'D'
		, '일'
		, 'DAY'
	)
	, (
		'etf0000.mmnt_unit'
		, 'W'
		, '주(5일)'
		, 'WEEK'
	)
	, (
		'etf0000.mmnt_unit'
		, 'M'
		, '월(5일)'
		, 'MONTH'
	)
	, (
		'etf0000.etf_type'
		, '1'
		, '국내시장지수'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '2'
		, '국내업종테마'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '3'
		, '국내파생'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '4'
		, '해외주식'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '5'
		, '원자재'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '6'
		, '채권'
		, ''
	)
	, (
		'etf0000.etf_type'
		, '7'
		, '기타'
		, ''
	)
;

COMMIT;
