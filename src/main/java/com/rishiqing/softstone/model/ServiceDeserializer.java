package com.rishiqing.softstone.model;

import com.rishiqing.softstone.util.CryptoHelper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

/**
 * webService request请求
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class ServiceDeserializer {
    //  初始请求时的XML String
    private String requestXML;

    private Node headNode;
    private Node bodyNode;

    public ServiceDeserializer(String xml) {
        this.requestXML = xml;

        Document d = null;
        try {
            d = DocumentHelper.parseText(requestXML);
            this.headNode = d.selectSingleNode("Msg/Head");

            String plainText = CryptoHelper.decode(d.selectSingleNode("Msg/Body").getText());

            this.bodyNode = DocumentHelper.parseText("<Body>" + plainText + "</Body>");

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据指定xpath获取元素的value值,xpath中不包括最外层的Head
     * @param xPath
     * @return
     */
    public String getHeadValue(String xPath){
        if(null == this.headNode){
             return null;
        }
        Node n = this.headNode.selectSingleNode(xPath);
        return n.getText();
    }

    /**
     * 根据指定的xpath获取元素的value值，xpath中不包括最外层的Body
     * @param xPath
     * @return
     */
    public String getBodyValue(String xPath){
        if(null == this.bodyNode){
            return null;
        }
        Node n = this.bodyNode.selectSingleNode("Body/" + xPath);
        return n.getText();
    }
}
