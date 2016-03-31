package com.rishiqing.softstone.handler;

import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;
import com.rishiqing.softstone.model.ServiceRequest;
import com.rishiqing.softstone.model.ServiceResponse;
import com.rishiqing.softstone.util.GlobalConfig;
import com.rishiqing.softstone.util.HandlerUtil;

import java.util.HashMap;

/**
 * 处理业务相关
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class TeamGroupHandler implements ServiceHandler {

    private static final String METHOD_CREATE_NEW = GlobalConfig.queryConfig("apiMethodSyncTeamGroup");
    private static final String TARGET_URL_CREATE_NEW = GlobalConfig.queryConfig("apiTargetUrlSyncTeamGroup");

    private static final String APPID = GlobalConfig.queryConfig("softStoneAppId");
    //  操作码，不同的handler不一样
    private static final String TYPEID = "APP00004";

    private ServiceRequest request;

    /**
     * 实现ServiceHandler接口的方法，用于准备数据发送请求
     * @param request
     * @return
     */
    public ApiRequest prepareRequest(ServiceRequest request) {

        this.request = request;

        HashMap<String, String> m = new HashMap<String, String>();
        //  软通动力记录的企业id
        m.put("outerId", request.getBodyValue("EcOrderInfo/EcID"));
        //  企业名称
        m.put("teamName", request.getBodyValue("EcOrderInfo/EcInfo/EcName"));
        //  企业管理员帐号
        m.put("teamAdminAccount", request.getBodyValue("EcOrderInfo/EcInfo/AdminAccount"));
        //  企业管理员姓名
        m.put("teamAdminName", request.getBodyValue("EcOrderInfo/EcInfo/AdminName"));
        //  企业管理员性别
        m.put("teamAdminSex", request.getBodyValue("EcOrderInfo/EcInfo/AdminSex"));
        //  企业管理员手机
        m.put("teamAdminPhone", request.getBodyValue("EcOrderInfo/EcInfo/AdminPhone"));
        //  企业管理员邮箱
        m.put("teamAdminEmail", request.getBodyValue("EcOrderInfo/EcInfo/AdminEmail"));
        //  企业管理员地址
        m.put("teamAdminAddr", request.getBodyValue("EcOrderInfo/EcInfo/AdminAddr"));
        //  企业联系人姓名
        m.put("teamLinkmanName", request.getBodyValue("EcOrderInfo/EcInfo/LinkmanName"));
        m.put("teamLinkmanFax", request.getBodyValue("EcOrderInfo/EcInfo/LinkmanFax"));
        m.put("teamLinkmanEmail", request.getBodyValue("EcOrderInfo/EcInfo/LinkmanEmail"));
        m.put("teamLinkmanTel", request.getBodyValue("EcOrderInfo/EcInfo/LinkmanTel"));

        return new ApiRequest(METHOD_CREATE_NEW, TARGET_URL_CREATE_NEW, m);
    }


    /**
     * 实现ServiceHandler接口的方法，用于准备数据返回
     * @param response
     * @return
     */
    public ServiceResponse prepareResponse(ApiResponse response) {

        StringBuffer headString = new StringBuffer("<Code>");
        headString.append(TYPEID)
                .append("</Code><CTID>")
                .append(this.request.getHeadValue("CTID"))
                .append("</CTID><STID>")
                .append(HandlerUtil.generateSTID())
                .append("</STID><AppId>")
                .append(APPID)
                .append("</AppId><RequestTime>")
                .append(this.request.getHeadValue("SubmitTime"))
                .append("</RequestTime><ResponseTime>")
                .append(HandlerUtil.getTimeString())
                .append("</ResponseTime><Version>1")
                .append("</Version><Priority>10")
                .append("</Priority><Status>0")
                .append("</Status>");


        String resultCode = "0000";
        String resultDesc = "ok";
        int statusCode = response.getStatusCode();

        if(statusCode < 200 || statusCode > 299){
            resultCode = "9999";
            resultDesc = "response code:" + statusCode;
        }

        StringBuffer bodyString = new StringBuffer();
        bodyString.append("<ResultCode>")
                .append(resultCode)
                .append("</ResultCode><ResultDesc>")
                .append(resultDesc)
                .append("</ResultDesc>");

        return new ServiceResponse(headString.toString(), bodyString.toString());
    }
}
