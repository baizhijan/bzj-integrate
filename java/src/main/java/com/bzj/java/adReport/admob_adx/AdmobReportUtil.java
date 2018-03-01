package com.bzj.java.adReport.admob_adx;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.adexchangeseller.AdExchangeSeller;
import com.google.api.services.adsense.AdSense;
import org.apache.commons.lang3.StringUtils;

/**
 * Admob 数据拉取工具类
 * <p/>
 * User:aaronbai@tcl.com
 * Date:2016-12-02
 * Time:11:03
 */
public class AdmobReportUtil {

    /**
     * 构建AdSense Client
     *
     * @param applicationName
     * @param credential
     * @return
     */
    public static AdSense buildAdsense(String applicationName, Credential credential) {
        return new AdSense.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), credential)
                .setApplicationName(applicationName)
                .build();
    }

    /**
     * 处理广告位ID，Admob规则，需要将广告位'/'替换为':'
     *
     * @param adUnitId
     * @return
     */
    public static String processAdUnitId(String adUnitId) {
        return StringUtils.replaceChars(adUnitId, '/', ':');
    }

    /**
     * Escape special characters for a parameter being used in a filter.
     *
     * @param parameter the parameter to be escaped.
     * @return the escaped parameter.
     */
    public static String escapeFilterParameter(String parameter) {
        return parameter.replace("\\", "\\\\").replace(",", "\\,");
    }

    /**
     * report Convert input
     * @param parameter
     * @return
     */
    public static String parameterConvert(String parameter){
        return parameter.replace(":", "/");
    }

    /**
     * 获取adx 拉取工具
     * @param applicationName
     * @param credential
     * @return
     */
    public static AdExchangeSeller getAdExchange(String applicationName,Credential credential){
        return new AdExchangeSeller.Builder(Utils.getDefaultTransport(),
                Utils.getDefaultJsonFactory(), credential).setApplicationName(applicationName).build();
    }
}
