package com.bzj.java.adReport.admob_adx;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

/**
 * admob数据拉取
 *
 * @author aaronbai
 * @create 2018-02-28 17:07
 **/
public class Admob {

    /**
     * google认证请求
     *
     * @return
     */
    public String certification() {
        //可以传递加密参数
        GoogleClientSecrets googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets("");
        return null;
    }

    public String googleCallBack(String code, String state) {

        return null;
    }
}
