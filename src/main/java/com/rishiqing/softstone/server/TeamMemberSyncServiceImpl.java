package com.rishiqing.softstone.server;

import com.troyjj.crypt.Encrypt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.rishiqing.softstone.server.TeamMemberSyncService",
            serviceName = "TeamMemberSyncService")
public class TeamMemberSyncServiceImpl implements TeamMemberSyncService {

    public String execute(@WebParam(name = "requestXML") String requestXML) {
        System.out.println("-----execute in TeamMemberSyncService: " + requestXML);
        try {
            Document d = DocumentHelper.parseText(requestXML);

            Node node = d.selectSingleNode("Msg/Body");
            String body = node.getText();
            System.out.println("body value:" + body);

            String result = Encrypt.decryptSSOPlain(body, "DGx8IgQC");

            System.out.println("----result: " + result);
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    public static void main(String[] args){
        Document d = DocumentHelper.createDocument();
        Element msg = d.addElement("Msg");
        Element head = msg.addElement("Head")
                .addText("hello");
        Element body = msg.addElement("Body")
                .addText("world");

        System.out.println("xml: " + d.asXML());
    }
}
