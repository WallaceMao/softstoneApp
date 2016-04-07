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
    static public String getTimeString(String f){
        SimpleDateFormat format = new SimpleDateFormat(f);
        return format.format(new Date());
    }
    static public String getTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
        return format.format(new Date());
    }
    //  先产生暂时的STID
    static public String generateSTID(){
        return "OAOC" + getTimeString("YYMMDDHHmmssSSS") + UUID.randomUUID().toString().substring(0, 4);
    }

    static public String generateCTID(){
        return getTimeString("YYYYMMDDHHmmssSSS").substring(0, 16);
    }
}
