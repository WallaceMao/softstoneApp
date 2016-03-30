package com.rishiqing.softstone.server;

import com.rishiqing.softstone.util.CryptoHelper;
import com.troyjj.crypt.Encrypt;
import org.dom4j.*;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.rishiqing.softstone.server.BusinessService",
            serviceName = "BusinessService")
public class BusinessServiceImpl implements BusinessService {

    public static final String APPID = "2c90dab253501466015397d8f74f01fb";

    public String execute(@WebParam(name = "requestXML") String requestXML) {
        System.out.println("-----execute in TeamGroupSyncService: " + requestXML);

        try {
            Document d = DocumentHelper.parseText(requestXML);

            Node node = d.selectSingleNode("Msg/Body");
            String body = node.getText();

            String result = CryptoHelper.decode(body);

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

            String bodyContent = CryptoHelper.encode("<ResultCode>" + resultCode + "</ResultCode><ResultDesc>" + resultDesc + "</ResultDesc>");

            Document doc = DocumentHelper.createDocument();
            Element msg = doc.addElement("Msg");
            Element head = msg.addElement("Head");
            head.addElement("Code").addText(code);
            head.addElement("CTID").addText(ctid);
            head.addElement("STID").addText(stid);
            head.addElement("AppId").addText(appId);
            head.addElement("RequestTime").addText(requestTime);
            head.addElement("ResponseTime").addText(responseTime);
            head.addElement("Version").addText(version);
            head.addElement("Priority").addText(priority);
            head.addElement("Status").addText(status);


           msg.addElement("Body").addText(bodyContent);

            System.out.println("xml: " + doc.asXML());

            return doc.asXML();

        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
