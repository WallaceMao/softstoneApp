package com.rishiqing.softstone.handler;

import com.rishiqing.softstone.util.CryptoHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class ServiceRequestTest {

    ServiceRequest request;

    @Before
    public void setUp() throws Exception {

        String requestXML = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "  <Msg>\n" +
                "    <Head>\n" +
                "      <Code>APP00001</Code>\n" +
                "      <CTID>7604_20160330160800013_5CFB</CTID>\n" +
                "      <AppID>ff80808148bbb4390148bf866eb201a1</AppID>\n" +
                "      <SubmitTime>20160330160800013</SubmitTime>\n" +
                "      <Version>1</Version>\n" +
                "      <Priority>10</Priority>\n" +
                "    </Head>\n" +
                "    <Body>" +
                CryptoHelper.encode("<MsgSN>APP00001</MsgSN><EcOrderInfo><OPType>01</OPType><OPNote>2c90dab24cc0f2270</OPNote><EcID>2c90dab24cc0f227014cc10f3bda0013</EcID><EcInfo><OprType>01</OprType><EcName>赋琛</EcName><AdminAccount>18562226222</AdminAccount><AdminName>18562226222</AdminName><AdminSex>1</AdminSex><AdminPhone>18562226222</AdminPhone><AdminEmail>cyhan@163.com</AdminEmail><AdminAddr>山东省烟台市牟平区新区大街</AdminAddr><LinkmanName>赋琛</LinkmanName><LinkmanFax>85622</LinkmanFax><LinkmanEmail>cyhan@163.com</LinkmanEmail><LinkmanTel>18562226222</LinkmanTel></EcInfo><OrderInfo><OrderID>2c90dab24cc539a4014cc544741b0002</OrderID><OrderCode>2c90dab24cc539a4014cc544741b0002</OrderCode><ProdID>2c90dab24bdd965d014be392ee030030</ProdID><AppID>ff80808148bbb4390148bf866eb201a1</AppID><FeeType>1</FeeType><OrderStatus>2</OrderStatus><EffectDate>20150417</EffectDate><ExpiryDate>20250416</ExpiryDate><Memo>试用</Memo></OrderInfo><OpenInfoList/></EcOrderInfo>") +
                "</Body>\n" +
                "  </Msg>\n";

         request = new ServiceRequest(requestXML);
    }

    @Test
    public void testGetHeadValue() throws Exception {
        assertEquals("Code value", request.getHeadValue("Code"), "APP00001");
        assertEquals("CTID value", request.getHeadValue("CTID"), "7604_20160330160800013_5CFB");
        assertEquals("AppID value", request.getHeadValue("AppID"), "ff80808148bbb4390148bf866eb201a1");
        assertEquals("SubmitTime value", request.getHeadValue("SubmitTime"), "20160330160800013");
        assertEquals("Version value", request.getHeadValue("Version"), "1");
        assertEquals("Priority value", request.getHeadValue("Priority"), "10");
    }

    @Test
    public void testGetBodyValue() throws Exception {
         assertEquals("body MsgSN value", request.getBodyValue("MsgSN"), "APP00001");
         assertEquals("EcOrderInfo.OPType MsgSN value", request.getBodyValue("EcOrderInfo/OPType"), "01");
    }
}