package com.bzj.java.adReport.admob_adx;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.json.Json;
import com.google.api.services.adexchangeseller.AdExchangeSeller;
import com.google.api.services.adsense.AdSense;
import com.google.api.services.adsense.model.AdsenseReportsGenerateResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * admob数据拉取
 *
 * @author aaronbai
 * @create 2018-02-28 17:07
 **/
public class Admob {

    private static final String ADSENSE_APP_NAME = "my-ApplicationName";
    /**
     * google认证请求,生成请求url
     * admob和adx都支持使用,差别在于json文件
     *
     * @return
     */
    public String certification() throws IOException {
        //可以传递加密参数
        URL path = Admob.class.getResource("/");
        String json = FileUtils.readFileToString(new File(path.getPath() + "json/admob.json"), "UTF-8");
        GoogleClientSecrets googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(json);
        String url = GoogleOAuthUtil.creatAdmobUrl(googleClientSecrets, "001");
        return url;
    }

    /**
     * google  认证成功后回调改接口将code 和传递的参数返回到该接口
     * 通过code 获取token
     *
     * @param code
     * @param state
     * @return
     */
    public GoogleTokenResponse googleCallBack(String code, String state) throws IOException {
        URL path = Admob.class.getResource("/");
        String json = FileUtils.readFileToString(new File(path.getPath() + "json/admob.json"), "UTF-8");
        GoogleClientSecrets googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(json);
        //admob token  获取方式
        GoogleTokenResponse token = GoogleOAuthUtil.getAdmbToken(googleClientSecrets, code);
        //adx token 获取方式
        token = GoogleOAuthUtil.getAdxToken(googleClientSecrets, code);
        return token;
    }

    public void pullData(GoogleTokenResponse token) throws Exception {
        URL path = Admob.class.getResource("/");
        String json = FileUtils.readFileToString(new File(path.getPath() + "json/admob.json"), "UTF-8");
        GoogleClientSecrets googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(json);
        Credential credential = GoogleOAuthUtil.getGoorleClent(googleClientSecrets, token);

        //导出日期
        String reportDate = "yyyy-MM-dd";

        //admob数据是交给AdSense管理的,获取AdSense 对象
        AdSense adSense = AdmobReportUtil.buildAdsense(ADSENSE_APP_NAME, credential);

        // 获取Report
        AdSense.Accounts.Reports.Generate admobRequest = adSense.accounts().reports()
                .generate("admobPublisherId", reportDate, reportDate);

        {
            /***
             * 目前只是吧adx和admob 写在了一起正式使用可以根据业务逻辑分离
             */
            //adx 数据是交给Exchange管理的 所以获取AdExchangeSeller对象
            AdExchangeSeller adExchangeSeller = AdmobReportUtil.getAdExchange(ADSENSE_APP_NAME, credential);

            // 获取Report
            AdExchangeSeller.Accounts.Reports.Generate adxRequest = adExchangeSeller.accounts().reports()
                    .generate("adxPublisherId", reportDate, reportDate);

        }

        admobRequest.setLocale("en_US");

        //指定国家货币类型(只支持admob)
        admobRequest.setCurrency(MoneyUnitEnum.USD.name());

        // 设置使用用户本地时区报告，与产品/运营确认强制用户自己设置为UTC时区(只支持admob)
        admobRequest.setUseTimezoneReporting(true);

        // 展示字段
        admobRequest.setMetric(Arrays.asList(
                AdMobMetricsEnum.AD_REQUESTS.toString(),
                AdMobMetricsEnum.INDIVIDUAL_AD_IMPRESSIONS.toString(),
                AdMobMetricsEnum.CLICKS.toString(),
                AdMobMetricsEnum.EARNINGS.toString(),
                AdMobMetricsEnum.INDIVIDUAL_AD_IMPRESSIONS_RPM.toString(),
                AdMobMetricsEnum.MATCHED_AD_REQUESTS.toString()));
        // 统计维度
        admobRequest.setDimension(Arrays.asList(
                AdMobDimensionEnum.DATE.toString(),
                AdMobDimensionEnum.COUNTRY_CODE.toString(),
                AdMobDimensionEnum.AD_UNIT_ID.toString()));

        // 发送请求
        AdsenseReportsGenerateResponse reportStr = admobRequest.execute();
    }

}
