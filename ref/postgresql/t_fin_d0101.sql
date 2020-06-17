-- Drop table

DROP TABLE IF EXISTS public.t_fin_d0101 CASCADE;

CREATE TABLE public.t_fin_d0101 (
	corp_code varchar(8) NOT NULL, -- corporation code
	corp_hash int8 NOT NULL, -- corporation hash data
	corp_name varchar(128) NOT NULL, -- corporation name
	stock_code varchar(64) NULL, -- stock_code
	modify_date varchar(2048) NULL, -- modify_date
	date_inst timestamp NULL, -- insert datetime
	date_updt timestamp NULL -- update datetime
);
CREATE UNIQUE INDEX pk_t_fin_d0101 ON public.t_fin_d0101 (corp_code);
COMMENT ON TABLE public.t_fin_d0101 IS 'corporation dart code.';

-- Column comments

COMMENT ON COLUMN public.t_fin_d0101.corp_code IS 'corporation code';
COMMENT ON COLUMN public.t_fin_d0101.corp_hash IS 'corporation hash data';
COMMENT ON COLUMN public.t_fin_d0101.corp_name IS 'corporation name';
COMMENT ON COLUMN public.t_fin_d0101.stock_code IS 'stock_code';
COMMENT ON COLUMN public.t_fin_d0101.modify_date IS 'modify_date';
COMMENT ON COLUMN public.t_fin_d0101.date_inst IS 'insert datetime';
COMMENT ON COLUMN public.t_fin_d0101.date_updt IS 'update datetime';

COMMIT;
