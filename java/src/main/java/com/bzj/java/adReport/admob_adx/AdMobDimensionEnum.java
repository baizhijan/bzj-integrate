package com.bzj.java.adReport.admob_adx;

/**
 * 统计维度枚举
 * 把google 提供的所有类型都支持,具体代码中用到的自己选择使用
 * <p/>
 * User:aaronbai@tcl.com
 * Date:2016-11-30
 * Time:19:09
 */
public enum AdMobDimensionEnum {
    AD_CLIENT_ID(1, "广告客户的ID"),
    AD_FORMAT_CODE(2, "广告格式的代码"),
    AD_FORMAT_NAME(3, "广告格式的名称"),
    AD_UNIT_CODE(4, "广告单元的代码"),
    AD_UNIT_ID(5, "广告单元的唯一ID"),
    AD_UNIT_NAME(6, "广告单元的名称"),
    AD_UNIT_SIZE_CODE(7, "广告单元的尺寸代码"),
    AD_UNIT_SIZE_NAME(8, "广告单元的尺寸名称"),
    BID_TYPE_CODE(9, "出价类型"),
    BID_TYPE_NAME(10, "出价类型名称"),
    BUYER_NETWORK_ID(11, "向您网站发送广告的广告联盟的ID"),
    BUYER_NETWORK_NAME(12, "向您网站发送广告的广告联盟的名称"),
    COUNTRY_CODE(13, "CLDR地区代码"),
    COUNTRY_NAME(14, "地区名称"),
    CUSTOM_CHANNEL_CODE(15, "自定义渠道的代码"),
    CUSTOM_CHANNEL_ID(16, "自定义渠道的ID"),
    CUSTOM_CHANNEL_NAME(17, "自定义渠道的名称"),
    DATE(18, "日期"),
    DOMAIN_NAME(19, "域名"),
    MONTH(20, "月份"),
    PLATFORM_TYPE_CODE(21, "广告平台类型的代码"),
    PLATFORM_TYPE_NAME(22, "广告平台类型的名称"),
    PRODUCT_CODE(23, "产品代码"),
    PRODUCT_NAME(24, "产品名称"),
    TARGETING_TYPE_CODE(25, "定位类型代码"),
    TARGETING_TYPE_NAME(26, "定位类型名称"),
    URL_CHANNEL_ID(27, "网址渠道的ID"),
    URL_CHANNEL_NAME(28, "网址渠道的名称"),
    WEEK(29, "一周第一天的日期"),

    AD_TAG_NAME(30, "");

    private String describe;
    private int index;

    AdMobDimensionEnum(int index, String describe) {
        this.describe = describe;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
