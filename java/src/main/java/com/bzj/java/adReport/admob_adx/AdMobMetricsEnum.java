package com.bzj.java.adReport.admob_adx;

/**
 * mopub统计指标枚举
 * 把google 提供的所有类型都支持,具体代码中用到的自己选择使用
 * <p/>
 * User:aaronbai@tcl.com
 * Date:2016-11-30
 * Time:19:57
 */
public enum AdMobMetricsEnum {
    //请求数(单位整数)
    AD_REQUESTS,
    //请求覆盖率
    AD_REQUESTS_COVERAGE,
    //点击率
    AD_REQUESTS_CTR,
    //广告请求收入
    AD_REQUESTS_RPM,
    //点击数
    CLICKS,
    //每次点击获取的收益
    COST_PER_CLICK,
    //预估收入
    EARNINGS,
    INDIVIDUAL_AD_IMPRESSIONS,
    INDIVIDUAL_AD_IMPRESSIONS_CTR,
    INDIVIDUAL_AD_IMPRESSIONS_RPM,
    //添充数
    MATCHED_AD_REQUESTS,
    MATCHED_AD_REQUESTS_CTR,
    MATCHED_AD_REQUESTS_RPM,
    PAGE_VIEWS,
    PAGE_VIEWS_CTR,
    PAGE_VIEWS_RPM;

}
