package com.rishiqing.softstone.handler;

import com.troyjj.crypt.Encrypt;
import org.dom4j.*;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class BusinessServiceHandler implements ServiceHandler {

    public static final String KEY = "DGx8IgQC";
    public static final String APPID = "2c90dab253501466015397d8f74f01fb";

    public String handle(String requestXML) {
        try {
            Document d = DocumentHelper.parseText(requestXML);

            Node node = d.selectSingleNode("Msg/Body");
            String body = node.getText();
            String result = Encrypt.decryptSSOPlain(body, KEY);




        }catch (DocumentException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String makeResponse(){
        String resultCode = "0000";
        String resultDesc = "ok";
        String code = "APP00001";
        String ctid = "7604_20160330160800013_5CFB";
        String stid = "7604_20160330160800013_5CFB";
        String appId = APPID;
        String requestTime = "20160330160800013";
        String responseTime = "20160330160800013";
        String version = "1";
        String priority = "10";
        String status = "0";

        String bodyContent = Encrypt.encryptSSOPlain("<ResultCode>"+resultCode+"</ResultCode><ResultDesc>"+resultDesc+"</ResultDesc>", KEY);

        Document d = DocumentHelper.createDocument();
        Element msg = d.addElement("Msg");
        Element head = msg.addElement("Head");
        head.addElement("Code").addText(code)
                .addElement("CTID").addText(ctid)
                .addElement("STID").addText(stid)
                .addElement("AppId").addText(appId)
                .addElement("RequestTime").addText(requestTime)
                .addElement("ResponseTime").addText(responseTime)
                .addElement("Version").addText(version)
                .addElement("Priority").addText(priority)
                .addElement("Status").addText(status);


        Element body = msg.addElement("Body")
                .addText(bodyContent);

        System.out.println("xml: " + d.asXML());
        return d.asXML();
    }

}
