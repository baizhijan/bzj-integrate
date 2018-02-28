package com.bzj.java.adReport.admob_adx;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.adexchangeseller.AdExchangeSellerScopes;
import com.google.api.services.adsense.AdSenseScopes;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;

/**
 * google  授权认证工具类
 * User:aaronbai
 * Date:2016-12-09
 * Time:13:18
 */
public class GoogleOAuthUtil {

    /**
     * 默认Http request init
     */
    public static final HttpRequestInitializer HTTP_REQUEST_INITIALIZER = new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) throws IOException {
            request.setConnectTimeout(5 * 1000);
            request.setReadTimeout(10 * 1000);
        }
    };

    /**
     * 获取google认证对象
     *
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static GoogleClientSecrets getGoogleClientSecrets(String jsonStr) {
        GoogleClientSecrets googleClientSecrets = null;
        Reader goauthReader = null;
        try {
            goauthReader = new StringReader(jsonStr);
            googleClientSecrets = GoogleClientSecrets.load(Utils.getDefaultJsonFactory(), goauthReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(goauthReader);
        }
        return googleClientSecrets;
    }

    /**
     * 构建Credential
     *
     * @param googleClientSecrets
     * @param tokenResponse
     * @param refreshListener
     * @return
     */
    public static Credential buildCredential(GoogleClientSecrets googleClientSecrets,
                                             TokenResponse tokenResponse,
                                             CredentialRefreshListener refreshListener,
                                             HttpRequestInitializer httpRequestInitializer) {
        GoogleCredential.Builder builder = new GoogleCredential.Builder();
        builder.setJsonFactory(Utils.getDefaultJsonFactory());
        builder.setTransport(Utils.getDefaultTransport());
        builder.setClientSecrets(googleClientSecrets);
        if (refreshListener != null) {
            builder.addRefreshListener(refreshListener);
        }
        if (httpRequestInitializer != null) {
            builder.setRequestInitializer(httpRequestInitializer);
        }
        return builder.build().setFromTokenResponse(tokenResponse);
    }

//--------------------------------admob-----------------------------------------------------------------------------

    /**
     * 创建回调url
     *
     * @param googleClientSecrets
     * @param state
     * @return
     */
    public static String creatAdmobUrl(GoogleClientSecrets googleClientSecrets, String state) {
        GoogleClientSecrets.Details goClientdetails = googleClientSecrets.getDetails();
        String url = new GoogleBrowserClientRequestUrl(googleClientSecrets,
                goClientdetails.getRedirectUris().get(0)
                , Collections.singletonList(AdSenseScopes.ADSENSE_READONLY)
        )
                .set("access_type", "offline")
                .set("approval_prompt", "force")
                .setResponseTypes(Collections.singleton("code"))
                .setState(state)
                .build();
        return url;
    }

    /**
     * 获取token
     *
     * @param googleClientSecrets
     * @param code
     * @return
     * @throws IOException
     */
    public static GoogleTokenResponse getAdmbToken(GoogleClientSecrets googleClientSecrets, String code) {
        GoogleClientSecrets.Details goClientdetails = googleClientSecrets.getDetails();
        GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
                Utils.getDefaultTransport()
                , Utils.getDefaultJsonFactory()
                , googleClientSecrets
                , Collections.singletonList(AdSenseScopes.ADSENSE_READONLY))
                .build();

        try {
            return googleAuthorizationCodeFlow
                    .newTokenRequest(code)
                    .setRedirectUri(goClientdetails.getRedirectUris().get(0))
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//---------------------adx----------------------------------------------------------------------------------------------

    /**
     * 获取token
     *
     * @param googleClientSecrets
     * @param code
     * @return
     * @throws IOException
     */
    public static GoogleTokenResponse getAdxToken(GoogleClientSecrets googleClientSecrets, String code) {
        GoogleClientSecrets.Details goClientdetails = googleClientSecrets.getDetails();
        GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
                Utils.getDefaultTransport()
                , Utils.getDefaultJsonFactory()
                , googleClientSecrets
                , Collections.singleton(AdExchangeSellerScopes.ADEXCHANGE_SELLER_READONLY))
                .build();

        try {
            return googleAuthorizationCodeFlow
                    .newTokenRequest(code)
                    .setRedirectUri(goClientdetails.getRedirectUris().get(0))
                    .execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建回调url
     *
     * @param googleClientSecrets
     * @param state
     * @return
     */
    public static String creatAdxUrl(GoogleClientSecrets googleClientSecrets, String state) {
        GoogleClientSecrets.Details goClientdetails = googleClientSecrets.getDetails();
        String url = new GoogleBrowserClientRequestUrl(googleClientSecrets,
                goClientdetails.getRedirectUris().get(0)
                , Collections.singletonList(AdExchangeSellerScopes.ADEXCHANGE_SELLER_READONLY)
        )
                .set("access_type", "offline")
                .set("approval_prompt", "force")
                .setResponseTypes(Collections.singleton("code"))
                .setState(state)
                .build();
        return url;
    }

    /**
     * 获取证书
     * @param googleClientSecrets
     * @param dbTokenResponse
     * @return
     * @throws Exception
     */
    public static Credential getGoorleClent(GoogleClientSecrets googleClientSecrets,
                                            TokenResponse dbTokenResponse) throws Exception {
        return GoogleOAuthUtil.buildCredential(googleClientSecrets, dbTokenResponse
                , new CredentialRefreshListener() {
                    @Override
                    public void onTokenResponse(Credential credential, TokenResponse tokenResponse) throws IOException {
                        tokenResponse.setRefreshToken(credential.getRefreshToken());
                        Utils.getDefaultJsonFactory().toString(tokenResponse);
                    }

                    @Override
                    public void onTokenErrorResponse(Credential credential,
                                                     TokenErrorResponse tokenErrorResponse) throws IOException {
                        if ("invalid_grant".equalsIgnoreCase(tokenErrorResponse.getError())) {
                            // 处理当用户取消认证后情况

                        }
                    }
                }
                , GoogleOAuthUtil.HTTP_REQUEST_INITIALIZER);
    }
}