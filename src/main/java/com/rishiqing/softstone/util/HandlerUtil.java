package com.rishiqing.softstone.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class HandlerUtil {
    static public String getTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("YYMMDDHHmmssSSS");
        return format.format(new Date());
    }
    //  先产生暂时的STID
    static public String generateSTID(){
        return "OAOC" + getTimeString() + UUID.randomUUID().toString().substring(0, 4);
    }
}
