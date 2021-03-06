package com.rishiqing.softstone.util;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class GlobalConfig {

    //  日事清RKA验证服务器地址
    public static final String RKAServerLocation = "http://localhost:3000";
    //  日事清RKA验证服务器accessId
    public static final String accessKeyId = "hello";
    //  日事清RKA服务器验证accessKeySecret
    public static final String accessKeySecret = "world";

    //  软通动力提供的web登录wsdl访问接口
    private static final String saasWSDLLocation = "http://10.9.80.24:9081/csop-apiserver/services/SaaS2AppService?wsdl";
    //  软通动力提供的appId
    private static final String softStoneAppId = "2c90dab253501466015397d8f74f01fb";

    //  新增用户
    private static final String apiMethodCreateNewOrder = "POST";
    private static final String apiTargetUrlCreateNewOrder = "/user/create";

    //  团队成员同步
    private static final String apiMethodSyncTeamMember = "POST";
    private static final String apiTargetUrlSyncTeamMember = "/team/create";

    //  团队部门同步
    private static final String apiMethodSyncTeamGroup = "POST";
    private static final String apiTargetUrlSyncTeamGroup = "/team/create";

    public static String queryConfig(String configName){
        String res = "";
        try {
            Field f =  GlobalConfig.class.getField(configName);
            res = (String)f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            return res;
        }
    }
}
