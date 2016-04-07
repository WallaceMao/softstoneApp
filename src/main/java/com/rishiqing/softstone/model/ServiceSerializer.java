package com.rishiqing.softstone.model;

import com.rishiqing.softstone.util.CryptoHelper;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class ServiceSerializer {

    private String returnHead;
    private String returnBody;

    public ServiceSerializer(String xmlHead, String xmlBody){
        this.returnHead = xmlHead;
        this.returnBody = xmlBody;
    }

    public String getReturnXML(){
        StringBuffer result = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?>");
        result.append("<Msg><Head>")
                .append(returnHead)
                .append("</Head><Body>")
                .append(CryptoHelper.encode(returnBody))
                .append("</Body></Msg>");

        return result.toString();

    }
}
