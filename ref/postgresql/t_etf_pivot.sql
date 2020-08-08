DROP TABLE IF EXISTS t_etf_pivot CASCADE;
CREATE TABLE t_etf_calc (
	item_code varchar(6) NOT NULL
	, trdd varchar(8) NOT NULL
	, calc_set varchar(16) NOT NULL
	, date_inst timestamp DEFAULT NOW()
	, date_updt timestamp DEFAULT NOW()
	, calc_value varchar(16)
);
CREATE UNIQUE INDEX pk_t_etf_pivot ON t_etf_calc (item_code, trdd DESC, calc_set);

COMMENT ON TABLE t_etf_pivot IS 'ETF 일자별 수치';

COMMIT;
