package com.bzj.java.adReport.admob_adx;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.adsense.AdSense;
import com.google.api.services.adsense.model.Account;

import java.sql.Timestamp;
import java.util.Map;

/**
 * 第三方认证服务接口实现
 * <p/>
 * User: aaronbai
 * Date: 2016-12-21
 * Time: 11:26
 */
public class BizAuth3rdpServiceImpl {


//    private static final String ADSENSE_APP_NAME = "MIGADS-GetToken";
//
//    private ReportConfig reportConfig;
//
//
//    /**
//     * 加密解密工具
//     */
//    private AESEncryptorUtil encryptorUtil;
//
//    @Inject
//    public BizAuth3rdpServiceImpl(@Named(GlobalKeys.APP_GLOBAL_CONF_KEY_NAME) Map<String, String> conf, ReportConfig reportConfig
//            , IDataGoogleTokenService polyGoogleTokenService, IData3rdpPolyAccountInfoService data3rdpPolyAccountService) {
//        this.reportConfig = reportConfig;
//        this.polyGoogleTokenService = polyGoogleTokenService;
//        this.data3rdpPolyAccountService = data3rdpPolyAccountService;
//
//        String passphrase = MapConfig.getString(TokenConstants.ENCRYPT_CONFIG, conf, TokenConstants.PASSPHRASE);
//        encryptorUtil = new AESEncryptorUtil(passphrase);
//    }
//
//    @Override
//    public ServiceResponse<String> genGoogleAuthRedirect(long userId, long account3rdpId) {
//        Poly3rdpAccountInfoEntity poly3rdpAccountEntity = data3rdpPolyAccountService.get(account3rdpId);
//        if (poly3rdpAccountEntity == null ||
//                (poly3rdpAccountEntity.getPlatformId() != PolyAdPlatformEnum.ADMOB.getIndex() &&
//                        poly3rdpAccountEntity.getPlatformId() != PolyAdPlatformEnum.ADX.getIndex()
//                )) {
//            LOGGER.error("Poly account 3rpd verify fail. id:{}", account3rdpId);
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//
//        GoogleTokenEntity gTokenEntity = polyGoogleTokenService.getByAccount3rdpId(account3rdpId);
//
//        // 验证是否已认证
//        if (gTokenEntity != null && gTokenEntity.getStatus() == PolyGoogleTokenStatusEnum.AUTH_PASS.getIndex()) {
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_AUTH_PASS);
//        }
//
//        // 生成验证Code
//        AdmobApp3rdp admobApp3rdpInfo = JSONUtil.json2Obj(poly3rdpAccountEntity.getAccount(), AdmobApp3rdp.class);
//        String bizCode = encryptCode(userId, account3rdpId, admobApp3rdpInfo.getPublisherId());
//
//        GoogleClientSecrets googleClientSecrets = null;
//        String url;
//        if (poly3rdpAccountEntity.getPlatformId() == PolyAdPlatformEnum.ADMOB.getIndex()) {
//            googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(reportConfig.getReportElement().getAdmob().getJsonStr());
//            url = GoogleOAuthUtil.creatAdmobUrl(googleClientSecrets, bizCode);
//        } else {
//            googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(reportConfig.getReportElement().getAdx().getJsonStr());
//            url = GoogleOAuthUtil.creatAdxUrl(googleClientSecrets, bizCode);
//        }
//        return new ServiceResponse<String>(url);
//    }
//
//    @Override
//    public ServiceResponse authGoogleCode(long userId, String code, String bizCode) {
//        // 解密数据
//        GoogleAuthCodeInfo codeInfo = decryptCode(bizCode);
//        if (codeInfo == null) {
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//        long account3rdpId = codeInfo.getAccount3rdpId();
//
//        if (userId != codeInfo.getUserId()) {
//            // 登录用户不匹配
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//
//        Poly3rdpAccountInfoEntity poly3rdpAccountEntity = data3rdpPolyAccountService.get(account3rdpId);
//        if (poly3rdpAccountEntity == null || (poly3rdpAccountEntity.getPlatformId() != PolyAdPlatformEnum.ADMOB.getIndex() &&
//                poly3rdpAccountEntity.getPlatformId() != PolyAdPlatformEnum.ADX.getIndex())) {
//            LOGGER.error("Poly account 3rpd verify fail. id:{}, code:{}", account3rdpId, code);
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//        AdmobApp3rdp admobApp3rdpInfo = JSONUtil.json2Obj(poly3rdpAccountEntity.getAccount(), AdmobApp3rdp.class);
//
//        if (!StringUtils.equals(admobApp3rdpInfo.getPublisherId(), codeInfo.getAdmobPublisherId())) {
//            // Admob发布商ID不匹配
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//
//        if (CustomDateUtil.nowMillis() - codeInfo.getCreateTime() > 1800000) {
//            // 过期验证, 过期时长半小时
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_VERIFY);
//        }
//
//        // 获取Google认证Token
//        GoogleClientSecrets googleClientSecrets;
//        GoogleTokenResponse token = null;
//        long plantId = poly3rdpAccountEntity.getPlatformId();
//        try {
//            if (plantId == PolyAdPlatformEnum.ADX.getIndex()) {
//                googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(reportConfig.getReportElement().getAdx().getJsonStr());
//                token = GoogleOAuthUtil.getAdxToken(googleClientSecrets, code);
//            } else {
//                googleClientSecrets = GoogleOAuthUtil.getGoogleClientSecrets(reportConfig.getReportElement().getAdmob().getJsonStr());
//                token = GoogleOAuthUtil.getAdmbToken(googleClientSecrets, code);
//            }
//        } catch (Exception e) {
//            LOGGER.warn("Google auth get token fail. id:{}, code:{}, emsg:{}", account3rdpId, code, e.getMessage());
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_EXCEPTION);
//        }
//
//        if (StringUtils.isBlank(token.getRefreshToken())) {
//            LOGGER.error("Google token is not refresh_token. id:{}, code:{}", account3rdpId, code);
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_NO_REFRESHTOKEN);
//        }
//
//        // 验证Adsense应用商ID
//        try {
//            if (plantId == PolyAdPlatformEnum.ADMOB.getIndex()) {
//                Credential credential = GoogleOAuthUtil.buildCredential(googleClientSecrets, token, null, GoogleOAuthUtil.HTTP_REQUEST_INITIALIZER);
//                AdSense adSense = AdmobReportUtil.buildAdsense(ADSENSE_APP_NAME, credential);
//                AdSense.Accounts.Get request = adSense.accounts().get(admobApp3rdpInfo.getPublisherId());
//                Account account = request.execute();
//                if (LOGGER.isDebugEnabled()) {
//                    LOGGER.debug("Admob account id:{}, code:{}, admobPublisherId:{}, info:{}", account.toString());
//                }
//            }
//        } catch (Exception e) {
//            LOGGER.warn("Adsense acount verify fail. id:{}, admobPublisherId:{}, error:{}", account3rdpId, admobApp3rdpInfo.getPublisherId(), e.getMessage());
//            // 存在异常，统一定义为授权失败
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_POLY_REPORTAPI_ACCOUNT_NO_MATCH);
//        }
//
//        String tokenJson = JSONUtil.obj2Json(token);
//        try {
//            // 更新同一Google账号的认证信息
//            polyGoogleTokenService.updateAuth(admobApp3rdpInfo.getPublisherId(), tokenJson, PolyGoogleTokenStatusEnum.AUTH_PASS);
//
//            // 判断是否存在记录
//            GoogleTokenEntity googleTokenEntity = polyGoogleTokenService.getByAccount3rdpId(account3rdpId);
//            if (googleTokenEntity == null) {
//                // 不存在新增
//                googleTokenEntity = new GoogleTokenEntity();
//                googleTokenEntity.setAccount3rdpId(account3rdpId);
//                googleTokenEntity.setAdmobPublisherId(admobApp3rdpInfo.getPublisherId());
//                googleTokenEntity.setGoogleToken(tokenJson);
//                googleTokenEntity.setStatus(PolyGoogleTokenStatusEnum.AUTH_PASS.getIndex());
//                googleTokenEntity.setCreateTime(new Timestamp(CustomDateUtil.now().getTime()));
//                polyGoogleTokenService.add(googleTokenEntity);
//            } else {
//                // 当发布商不一致时需要更新
//                if (!StringUtils.equals(admobApp3rdpInfo.getPublisherId(), googleTokenEntity.getAdmobPublisherId())) {
//                    googleTokenEntity.setAdmobPublisherId(admobApp3rdpInfo.getPublisherId());
//                    googleTokenEntity.setGoogleToken(tokenJson);
//                    googleTokenEntity.setStatus(PolyGoogleTokenStatusEnum.AUTH_PASS.getIndex());
//                    googleTokenEntity.setUpdateTime(new Timestamp(CustomDateUtil.now().getTime()));
//                    polyGoogleTokenService.update(googleTokenEntity);
//                }
//            }
//        } catch (Exception e) {
//            LOGGER.error(String.format("Google token db fail. id:%s, token:%s", account3rdpId, token.toString()), e);
//            return ServiceResponse.genFailResponse(ErrorKey.ERROR_DAL);
//        }
//        return ServiceResponse.genSuccResponseWithoutData();
//    }
//
//    /**
//     * 加密数据格式：{userId}_{account3rdpId}_{nowMillis}_{admobPublisherId}
//     * 加密方式：AES + BASE64
//     *
//     * @param userId
//     * @param account3rdpId
//     * @param admobPublisherId
//     * @return
//     */
//    private String encryptCode(long userId, long account3rdpId, String admobPublisherId) {
//        String codeInfoStr = String.format("%s_%s_%s_%s", userId, account3rdpId, CustomDateUtil.nowMillis(), admobPublisherId);
//        return CodecUtils.encodeBase64(encryptorUtil.encrypt(codeInfoStr));
//    }
//
//    /**
//     * 解密
//     *
//     * @param bizCode
//     * @return
//     */
//    private GoogleAuthCodeInfo decryptCode(String bizCode) {
//        if (StringUtils.isBlank(bizCode)) {
//            return null;
//        }
//        String codeInfoStr = encryptorUtil.decrypt(CodecUtils.decodeBase64(bizCode));
//        String[] array = StringUtils.split(codeInfoStr, '_');
//        GoogleAuthCodeInfo codeInfo = new GoogleAuthCodeInfo();
//        codeInfo.setUserId(Long.parseLong(array[0]));
//        codeInfo.setAccount3rdpId(Long.parseLong(array[1]));
//        codeInfo.setCreateTime(Long.parseLong(array[2]));
//        codeInfo.setAdmobPublisherId(array[3]);
//        return codeInfo;
//    }

}
