package com.rishiqing.softstone.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class TestCryptoHelper {

    @Test
    public void testEncodeAndDecode(){
        String orgText = "<MsgSN>APP00001</MsgSN><EcOrderInfo><OPType>01</OPType><OPNote>2c90dab24cc0f2270</OPNote><EcID>2c90dab24cc0f227014cc10f3bda0013</EcID><EcInfo><OprType>01</OprType><EcName>赋琛</EcName><AdminAccount>18562226222</AdminAccount><AdminName>18562226222</AdminName><AdminSex>1</AdminSex><AdminPhone>18562226222</AdminPhone><AdminEmail>cyhan@163.com</AdminEmail><AdminAddr>山东省烟台市牟平区新区大街</AdminAddr><LinkmanName>赋琛</LinkmanName><LinkmanFax>85622</LinkmanFax><LinkmanEmail>cyhan@163.com</LinkmanEmail><LinkmanTel>18562226222</LinkmanTel></EcInfo><OrderInfo><OrderID>2c90dab24cc539a4014cc544741b0002</OrderID><OrderCode>2c90dab24cc539a4014cc544741b0002</OrderCode><ProdID>2c90dab24bdd965d014be392ee030030</ProdID><AppID>ff80808148bbb4390148bf866eb201a1</AppID><FeeType>1</FeeType><OrderStatus>2</OrderStatus><EffectDate>20150417</EffectDate><ExpiryDate>20250416</ExpiryDate><Memo>试用</Memo></OrderInfo><OpenInfoList/></EcOrderInfo>";
        String newText = CryptoHelper.encode(orgText);
        assertEquals("encode and decode", orgText, CryptoHelper.decode(newText));
    }
}
