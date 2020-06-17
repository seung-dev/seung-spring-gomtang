DROP TABLE IF EXISTS t_etf_trdd CASCADE;
CREATE TABLE t_etf_trdd (
	trdd varchar(8) NOT NULL
	, trdd_no int
	, date_inst timestamp DEFAULT NOW()
);
CREATE UNIQUE INDEX pk_t_etf_trdd ON t_etf_trdd (trdd DESC, trdd_no DESC);

COMMENT ON TABLE t_etf_trdd IS '주식거래일 회차';
COMMENT ON COLUMN t_etf_tr40005.trdd IS '주식거래일(trading day)';
COMMENT ON COLUMN t_etf_tr40005.trdd_no IS '주식거래일 회차 - 기준일: 20200615=0';
COMMENT ON COLUMN t_etf_tr40005.date_inst IS '등록일시';

COMMIT;

INSERT INTO t_etf_trdd
VALUES
('20200605', 0, NOW())
;

COMMIT;
