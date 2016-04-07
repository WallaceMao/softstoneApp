package com.rishiqing.softstone.model;

import com.rishiqing.softstone.util.GlobalConfig;
import com.rishiqing.softstone.util.HandlerUtil;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/4/5
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class SerializeUtil {

    public static String defaultRequestHeader(String operationCode){
        StringBuffer sb = new StringBuffer();

        sb.append("<Code>")
                .append(operationCode)
                .append("</Code><CTID>")
                .append(HandlerUtil.generateCTID())
                .append("</CTID><AppID>")
                .append(GlobalConfig.queryConfig("softStoneAppId"))
                .append("</AppID><SubmitTime>")
                .append(HandlerUtil.getTimeString())
                .append("</SubmitTime>")
                .append("<Version>1</Version>")
                .append("<Priority>10</Priority>");

        return sb.toString();
    }
}
