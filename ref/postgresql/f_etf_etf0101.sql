DROP FUNCTION IF EXISTS f_etf_etf0101(text);
CREATE OR REPLACE FUNCTION f_etf_etf0101 (
    req_json text
)
/*
RETURNS TEXT
*/
RETURNS TABLE (
    item_code character varying
    , item_name character varying
    , mmnt character varying
    , mmnt_size character varying
    , size_12m character varying
    , mmnt_12m character varying
    , mmnt_score_12m character varying
    , std_dev_12m character varying
    , etf_stts character varying
    , etf_cnst character varying
    , etf_type character varying
    , expn_rate character varying
    , etf_fm character varying
    , etf_fv character varying
    , etf_equity character varying
    , etf_is character varying
    , etf_cr character varying
    , etf_yh character varying
    , etf_yl character varying
    , etf_mc character varying
    , etf_mcr character varying
    , etf_for character varying
    , etf_sp character varying
    , etf_250h character varying
    , etf_250l character varying
    , etf_op character varying
    , etf_hp character varying
    , etf_lp character varying
    , etf_hhp character varying
    , etf_llp character varying
    , etf_bp character varying
    , etf_ep character varying
    , etf_eq character varying
    , etf_d250h character varying
    , etf_vs250h character varying
    , etf_d250l character varying
    , etf_vs250l character varying
    , etf_pp character varying
    , etf_pinc character varying
    , etf_pcv character varying
    , etf_fvu character varying
    , etf_os character varying
    , etf_osr character varying
    , trdd character varying
    , etf_cp character varying
    , etf_inc character varying
    , etf_pcp character varying
    , etf_vol character varying
    , etf_nav character varying
    , etf_volaccu character varying
    , etf_indexd character varying
    , etf_etfd character varying
    , etf_ter character varying
    , etf_ti character varying
    , etf_tiinc character varying
    , date_updt character varying
    )
AS
$$
DECLARE
    query_text TEXT := '';
    v_std_trdd_no int;
    v_std_trdd varchar(8);
    v_etf_mc_min int;
    v_etf_volaccu_min int;
    v_etf_etfd_from decimal(4, 2);
    v_etf_etfd_to decimal(4, 2);
    v_etf_ter_from decimal(4, 2);
    v_etf_ter_to decimal(4, 2);
    v_mmnt_date varchar(8);
    v_mmnt_unit int;
    v_mmnt_scope int;
    v_mmnt_min_max varchar(3);
    v_mmnt_threshold decimal(8, 4);
    v_threshold varchar(128);
    v_sort varchar(128);
    v_rules TEXT := '';
BEGIN

    SELECT
        trd.trdd_no
        , trd.trdd
    INTO
        v_std_trdd_no
        , v_std_trdd
    FROM
        t_etf_trdd trd
    ORDER BY trd.trdd_no DESC
    LIMIT 1
    ;
    
    SELECT
        req.etf_mc::int
        , req.etf_volaccu::int
        , req.etf_etfd_from::decimal
        , req.etf_etfd_to::decimal
        , req.etf_ter_from::decimal
        , req.etf_ter_to::decimal
        , CASE
            WHEN COALESCE(req.mmnt_date, '') = '' THEN v_std_trdd
            ELSE req.mmnt_date
            END AS mmnt_date
        , CASE
            WHEN LOWER(req.mmnt_unit) = 'd' THEN 1
            WHEN LOWER(req.mmnt_unit) = 'w' THEN 5
            WHEN LOWER(req.mmnt_unit) = 'm' THEN 20
            ELSE 0
            END AS mmnt_unit
        , req.mmnt_scope::int
        , req.mmnt_min_max
        , req.mmnt_threshold::decimal
        , CASE
            WHEN req.mmnt_min_max = 'min' THEN CONCAT(
                'AND COALESCE(calc_mmnt.mmnt, -1) >= '
                , req.mmnt_threshold
                )
            WHEN req.mmnt_min_max = 'max' THEN CONCAT(
                'AND COALESCE(calc_mmnt.mmnt, -1) <= '
                , req.mmnt_threshold
                )
            ELSE ''
            END AS v_threshold_sql
        , CASE
            WHEN req.mmnt_min_max = 'min' THEN 'ORDER BY COALESCE(calc_mmnt.mmnt, -1) DESC'
            WHEN req.mmnt_min_max = 'max' THEN 'ORDER BY COALESCE(calc_mmnt.mmnt, -1) ASC'
            ELSE ''
            END AS v_sort_sql
        , COALESCE(req.rules, '')
    INTO
        v_etf_mc_min
        , v_etf_volaccu_min
        , v_etf_etfd_from
        , v_etf_etfd_to
        , v_etf_ter_from
        , v_etf_ter_to
        , v_mmnt_date
        , v_mmnt_unit
        , v_mmnt_scope
        , v_mmnt_min_max
        , v_mmnt_threshold
        , v_threshold
        , v_sort
        , v_rules
    FROM (
        SELECT
            req::json->>'etf_mc' AS etf_mc
            , req::json->>'etf_volaccu' AS etf_volaccu
            , req::json->>'etf_etfd_from' AS etf_etfd_from
            , req::json->>'etf_etfd_to' AS etf_etfd_to
            , req::json->>'etf_ter_from' AS etf_ter_from
            , req::json->>'etf_ter_to' AS etf_ter_to
            , REGEXP_REPLACE(req::json->>'mmnt_date', '[^0-9]', '', 'g') AS mmnt_date
            , req::json->>'mmnt_unit' AS mmnt_unit
            , req::json->>'mmnt_scope' AS mmnt_scope
            , req::json->>'mmnt_min_max' AS mmnt_min_max
            , req::json->>'mmnt_threshold' AS mmnt_threshold
            , req::json->>'rules' AS rules
        FROM
            (SELECT CAST(req_json AS json) AS req) init
        ) req
    ;
    
    query_text := CONCAT(
        query_text
        , '  SELECT'  
        , '      items.item_code'  
        , '      , items.item_name'  
        , '      , calc_mmnt.mmnt::varchar'  
        , '      , calc_mmnt.mmnt_size::varchar'  
        , '      , calc_avg_12m.size_12m::varchar'  
        , '      , calc_avg_12m.mmnt_12m::varchar'  
        , '      , calc_avg_12m.mmnt_score_12m::varchar'  
        , '      , calc_avg_12m.std_dev_12m::varchar'  
        , '      , items.item_stts::varchar'  
        , '      , items.item_cnst::varchar'  
        , '      , items.etf_type'  
        , '      , items.expn_rate'  
        , '      , tr10001.etf_fm::varchar'  
        , '      , tr10001.etf_fv::varchar'  
        , '      , tr10001.etf_equity::varchar'  
        , '      , tr10001.etf_is::varchar'  
        , '      , tr10001.etf_cr::varchar'  
        , '      , tr10001.etf_yh::varchar'  
        , '      , tr10001.etf_yl::varchar'  
        , '      , tr10001.etf_mc::varchar'  
        , '      , tr10001.etf_mcr::varchar'  
        , '      , tr10001.etf_for::varchar'  
        , '      , tr10001.etf_sp::varchar'  
        , '      , tr10001.etf_250h::varchar'  
        , '      , tr10001.etf_250l::varchar'  
        , '      , tr10001.etf_op::varchar'  
        , '      , tr10001.etf_hp::varchar'  
        , '      , tr10001.etf_lp::varchar'  
        , '      , tr10001.etf_hhp::varchar'  
        , '      , tr10001.etf_llp::varchar'  
        , '      , tr10001.etf_bp::varchar'  
        , '      , tr10001.etf_ep::varchar'  
        , '      , tr10001.etf_eq::varchar'  
        , '      , tr10001.etf_d250h::varchar'  
        , '      , tr10001.etf_vs250h::varchar'  
        , '      , tr10001.etf_d250l::varchar'  
        , '      , tr10001.etf_vs250l::varchar'  
        , '      , tr10001.etf_pp::varchar'  
        , '      , tr10001.etf_pinc::varchar'  
        , '      , tr10001.etf_pcv::varchar'  
        , '      , tr10001.etf_fvu::varchar'  
        , '      , tr10001.etf_os::varchar'  
        , '      , tr10001.etf_osr::varchar'  
        , '      , tr40005.trdd'  
        , '      , ABS(tr40005.etf_cp)::varchar'  
        , '      , tr40005.etf_inc::varchar'  
        , '      , tr40005.etf_pcp::varchar'  
        , '      , ABS(tr40005.etf_vol)::varchar'  
        , '      , ABS(tr40005.etf_nav)::varchar'  
        , '      , ABS(tr40005.etf_volaccu)::varchar'  
        , '      , tr40005.etf_indexd::varchar'  
        , '      , tr40005.etf_etfd::varchar'  
        , '      , tr40005.etf_ter::varchar'  
        , '      , tr40005.etf_ti::varchar'  
        , '      , tr40005.etf_tiinc::varchar'  
        , '      , EXTRACT(EPOCH FROM tr40005.date_updt)::varchar AS date_updt'  
        );
    
    query_text := CONCAT(
        query_text
        , '  FROM ('  
        , '      SELECT'  
        , '          kw10000.item_code'  
        , '          , kw10000.item_name'  
        , '          , kw10000.item_stts'  
        , '          , kw10000.item_cnst'  
        , '          , n0101.etf_type'  
        , '          , n0102.expn_rate'  
        , '      FROM'  
        , '          t_kw_kw10000 kw10000'  
        , '          LEFT OUTER JOIN t_etf_n0101 n0101'  
        , '              ON 1 = 1'  
        , '              AND kw10000.item_code = n0101.item_code'  
        , '          LEFT OUTER JOIN t_etf_n0102 n0102'  
        , '              ON 1 = 1'  
        , '              AND kw10000.item_code = n0102.item_code'  
        , '      WHERE 1 = 1'  
        , '          AND kw10000.mrkt_type = ''3'''  
        , '          AND kw10000.on_prgr = ''1'''  
        , '      ) items'  
        );
    
    query_text := CONCAT(
        query_text
        , '      LEFT OUTER JOIN ('  
        , '          SELECT'  
        , '              summ_mmnt.item_code'  
        , '              , summ_mmnt.mmnt_size'  
        , '              , CASE'  
        , '                  WHEN summ_mmnt.mmnt_size <> v_mmnt_scope THEN -1'  
        , '                  ELSE summ_mmnt.mmnt'  
        , '                  END AS mmnt'  
        , '          FROM ('  
        , '              SELECT'  
        , '                  ord_mmnt.item_code'  
        , '                  , COUNT(*) AS mmnt_size'  
        , '                  , AVG(ord_mmnt.mmnt) AS mmnt'  
        , '              FROM ('  
        , '                  SELECT'  
        , '                      tr40005_mmnt.item_code'  
        , '                      , trdd_mmnt.trdd'  
        , '                      , base_mmnt.base_no - trdd_mmnt.trdd_no AS trdd_no'  
        , '                      , ABS(base_mmnt.base_cp / tr40005_mmnt.etf_cp) AS mmnt'  
        , '                  FROM'  
        , '                      t_etf_trdd trdd_mmnt'  
        , '                      , t_kw_tr40005 tr40005_mmnt'  
        , '                      LEFT OUTER JOIN ('  
        , '                          SELECT'  
        , '                              a_mmnt.trdd_no AS base_no'  
        , '                              , b_mmnt.etf_cp AS base_cp'  
        , '                              , b_mmnt.item_code'  
        , '                          FROM'  
        , '                              t_etf_trdd a_mmnt'  
        , '                              , t_kw_tr40005 b_mmnt'  
        , '                          WHERE 1 = 1'  
        , '                              AND a_mmnt.trdd = b_mmnt.trdd'  
        , '                              AND a_mmnt.trdd = v_mmnt_date'  
        , '                          ) base_mmnt'  
        , '                          ON 1 = 1'  
        , '                          AND base_mmnt.item_code = tr40005_mmnt.item_code'  
        , '                  WHERE 1 = 1'  
        , '                      AND trdd_mmnt.trdd = tr40005_mmnt.trdd'  
        , '                      AND trdd_mmnt.trdd < v_mmnt_date'  
        , '                  ) ord_mmnt'  
        , '              WHERE 1 = 1'  
        , '                  AND ord_mmnt.trdd_no % v_mmnt_unit = 0'  
        , '                  AND ord_mmnt.trdd_no <= v_mmnt_unit * v_mmnt_scope'  
        , '              GROUP BY ord_mmnt.item_code'  
        , '              ) summ_mmnt'  
        , '          ) calc_mmnt'  
        , '          ON 1 = 1'  
        , '          AND items.item_code = calc_mmnt.item_code'  
        );
    
    query_text := CONCAT(
        query_text
        , '      LEFT OUTER JOIN ('  
        , '          SELECT'  
        , '              avg_12m.item_code'  
        , '              , avg_12m.size_12m'  
        , '              , CASE'  
        , '                  WHEN avg_12m.size_12m <> 12 THEN -1'  
        , '                  ELSE avg_12m.mmnt_12m'  
        , '                  END AS mmnt_12m'  
        , '              , CASE'  
        , '                  WHEN avg_12m.size_12m <> 12 THEN -1'  
        , '                  ELSE avg_12m.mmnt_score_12m'  
        , '                  END AS mmnt_score_12m'  
        , '              , CASE'  
        , '                  WHEN avg_12m.size_12m <> 12 THEN -1'  
        , '                  ELSE avg_12m.std_dev_12m'  
        , '                  END AS std_dev_12m'  
        , '          FROM ('  
        , '              SELECT'  
        , '                  grp.item_code'  
        , '                  , COUNT(*) AS size_12m'  
        , '                  , AVG(grp.mmnt) AS mmnt_12m'  
        , '                  , AVG(grp.mmnt_score) AS mmnt_score_12m'  
        , '                  , STDDEV_POP(grp.diff) AS std_dev_12m'  
        , '              FROM ('  
        , '                  SELECT'  
        , '                      suf.item_code'  
        , '                      , (ABS(suf.etf_cp) - ABS(pre.etf_cp)) / pre.etf_cp AS diff'  
        , '                      , ABS(std.etf_cp / pre.etf_cp) AS mmnt'  
        , '                      , CASE'  
        , '                          WHEN ABS(std.etf_cp / pre.etf_cp) > 1 THEN 1'  
        , '                          ELSE 0'  
        , '                          END AS mmnt_score'  
        , '                  FROM'  
        , '                      ('  
        , '                          SELECT'  
        , '                              trd.trdd_no'  
        , '                              , trd.trdd'  
        , '                              , tr40005.item_code'  
        , '                              , tr40005.etf_cp'  
        , '                          FROM'  
        , '                              t_etf_trdd trd'  
        , '                              , t_kw_tr40005 tr40005'  
        , '                          WHERE 1 = 1'  
        , '                              AND trd.trdd = tr40005.trdd'  
        , '                              AND trd.trdd = v_std_trdd'  
        , '                      ) std'  
        , '                      , ('  
        , '                          SELECT'  
        , '                              trd.trdd_no'  
        , '                              , trd.trdd'  
        , '                              , tr40005.item_code'  
        , '                              , tr40005.etf_cp'  
        , '                          FROM'  
        , '                              t_etf_trdd trd'  
        , '                              , t_kw_tr40005 tr40005'  
        , '                          WHERE 1 = 1'  
        , '                              AND trd.trdd = tr40005.trdd'  
        , '                      ) suf'  
        , '                      , ('  
        , '                          SELECT'  
        , '                              trd.trdd_no'  
        , '                              , trd.trdd'  
        , '                              , tr40005.item_code'  
        , '                              , tr40005.etf_cp'  
        , '                          FROM'  
        , '                              t_etf_trdd trd'  
        , '                              , t_kw_tr40005 tr40005'  
        , '                          WHERE 1 = 1'  
        , '                              AND trd.trdd = tr40005.trdd'  
        , '                      ) pre'  
        , '                  WHERE 1 = 1'  
        , '                      AND std.item_code = suf.item_code'  
        , '                      AND std.item_code = pre.item_code'  
        , '                      AND suf.trdd_no = pre.trdd_no + 20'  
        , '                      AND (std.trdd_no - suf.trdd_no) < 20 * 12'  
        , '                      AND (std.trdd_no - suf.trdd_no) % 20 = 0'  
        , '                  ) grp'  
        , '              GROUP BY grp.item_code'  
        , '              ) avg_12m'  
        , '          ) calc_avg_12m'  
        , '          ON 1 = 1'  
        , '          AND items.item_code = calc_avg_12m.item_code'  
        );
    
    query_text := CONCAT(
        query_text
        , '      , t_kw_tr10001 tr10001'  
        , '      , t_kw_tr40005 tr40005'  
        , '  WHERE 1 = 1'  
        , '      AND items.item_code = tr10001.item_code'  
        , '      AND items.item_code = tr40005.item_code'  
        , '      AND tr40005.trdd = v_std_trdd'  
        --, '      AND tr10001.etf_mc >= v_etf_mc_min'  
        --, '      AND tr40005.etf_volaccu >= v_etf_volaccu_min'  
        --, '      AND tr40005.etf_etfd >= v_etf_etfd_from'  
        --, '      AND tr40005.etf_etfd <= v_etf_etfd_to'  
        --, '      AND tr40005.etf_ter >= v_etf_ter_from'  
        --, '      AND tr40005.etf_ter <= v_etf_ter_to'  
        --, '      AND COALESCE(calc_mmnt.mmnt, -1) >= v_mmnt_threshold'  
        , '      v_threshold'  
        --, '  ORDER BY COALESCE(calc_mmnt.mmnt, -1) DESC'  
        , '  v_sort'  
        , '  ;'  
        );
    
    --query_text := REPLACE(query_text, 'v_std_trdd_no', COALESCE(v_std_trdd_no, 1)::varchar);
    query_text := REPLACE(query_text, 'v_std_trdd', CONCAT('''', v_std_trdd, ''''));
    --query_text := REPLACE(query_text, 'v_etf_mc_min', COALESCE(v_etf_mc_min, 150)::varchar);
    --query_text := REPLACE(query_text, 'v_etf_volaccu_min', COALESCE(v_etf_volaccu_min, 100)::varchar);
    --query_text := REPLACE(query_text, 'v_etf_etfd_from', COALESCE(v_etf_etfd_from, -5)::varchar);
    --query_text := REPLACE(query_text, 'v_etf_etfd_to', COALESCE(v_etf_etfd_to, 0)::varchar);
    --query_text := REPLACE(query_text, 'v_etf_ter_from', COALESCE(v_etf_ter_from, -5)::varchar);
    --query_text := REPLACE(query_text, 'v_etf_ter_to', COALESCE(v_etf_ter_to, 5)::varchar);
    query_text := REPLACE(query_text, 'v_mmnt_date', CONCAT('''', v_mmnt_date, ''''));
    query_text := REPLACE(query_text, 'v_mmnt_unit', COALESCE(v_mmnt_unit, 20)::varchar);
    query_text := REPLACE(query_text, 'v_mmnt_scope', COALESCE(v_mmnt_scope, 12)::varchar);
    query_text := REPLACE(query_text, 'v_mmnt_min_max', v_mmnt_min_max);
    --query_text := REPLACE(query_text, 'v_mmnt_threshold', COALESCE(v_mmnt_threshold, 1)::varchar);
    query_text := REPLACE(query_text, 'v_threshold', v_threshold);
    query_text := REPLACE(query_text, 'v_sort', v_sort);
    --query_text := REPLACE(query_text, 'v_rules, v_rules);
    
    RETURN QUERY EXECUTE query_text;
    --RETURN query_text;
    
END;
$$
LANGUAGE plpgsql;

COMMIT;
