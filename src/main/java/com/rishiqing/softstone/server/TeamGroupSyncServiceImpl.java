package com.rishiqing.softstone.server;

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
@WebService(endpointInterface = "com.rishiqing.softstone.server.TeamGroupSyncService",
            serviceName = "TeamGroupSyncService")
public class TeamGroupSyncServiceImpl implements TeamGroupSyncService {

    public static final String KEY = "DGx8IgQC";

    public String execute(@WebParam(name = "requestXML") String requestXML) {
        System.out.println("-----execute in TeamGroupSyncService: " + requestXML);
        try {
            Document d = DocumentHelper.parseText(requestXML);

            Node node = d.selectSingleNode("Msg/Body");
            String body = node.getText();
            System.out.println("body value:" + body);

            String result = Encrypt.decryptSSOPlain(body, KEY);

            System.out.println("----result: " + result);
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
