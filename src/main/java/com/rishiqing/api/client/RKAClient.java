package com.rishiqing.api.client;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日事清RKA方式的调用客户端
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class RKAClient {

    static String accessKeyId = "hello";
    static String accessKeySecret = "world";

    public String post(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost("http://192.168.3.129:3000/users");

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("param1", "value1"));
            formparams.add(new BasicNameValuePair("param2", "value2"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            System.out.println(EntityUtils.toString(entity));

            httpPost.setEntity(entity);

            String signature;
            String verb = "POST";
            String contentMd5 = signMd5(EntityUtils.toByteArray(entity));
            String contentType = "text/html";
            String date = getGMTString();
            String RKAHeaders = getCanonicalizedRKAHeaders();
            String resource = getCanonicalizedResource();

            String beforeSig = verb + "\n"
                    + contentMd5 + "\n"
                    + contentType + "\n"
                    + date + "\n"
                    + RKAHeaders
                    + resource;

            signature = sign(beforeSig);

            httpPost.addHeader("Content-MD5", contentMd5);
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Date", date);
            httpPost.addHeader("x-rka-magic", "abracadabra");
            httpPost.addHeader("x-rka-meta-author", "foo@bar.com");
            httpPost.addHeader("Authorization", signature);
            CloseableHttpResponse response1 = httpClient.execute(httpPost);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                result = EntityUtils.toString(entity1);
                EntityUtils.consume(entity1);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response1.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getCanonicalizedRKAHeaders(){
        return "x-rka-magic:abracadabra\nx-rka-meta-author:foo@bar.com\n";
    }

    public static String getCanonicalizedResource(){
        return "/users";
    }

    public static String getGMTString(){
        final Date currentTime = new Date();

        final SimpleDateFormat sdf =
                new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String gmtDate = sdf.format(currentTime);
        System.out.println("GMT time: " + gmtDate);
        return gmtDate;
    }

    public static String signMd5(byte[] bytes){

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bt = md.digest(bytes);
        System.out.println();
        String str = Base64.encode(bt);

        return str;
    }

    public static String sign(String before){
//        String data = "PUT\nODBGOERFMDMzQTczRUY3NUE3NzA5QzdFNUYzMDQxNEM=\ntext/html\nThu, 17 Nov 2005 18:49:58 GMT\nx-oss-magic:abracadabra\nx-oss-meta-author:foo@bar.com\n/oss-example/nelson";
//        String key = "OtxrzxIsfpFjA7SwPzILwy8Bw21TLhquhboDYROV";
        System.out.println("before:" + before);
        SecretKeySpec signingKey = new SecretKeySpec(accessKeySecret.getBytes(), "HmacSHA1");
        Mac mac = null;
        String str = "";
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            str = Base64.encode(mac.doFinal(before.getBytes()));
            System.out.println(str);

//            byte[] bytesOfMessage = "0123456789".getBytes("UTF-8");
//
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] bt = md.digest(bytesOfMessage);
//            System.out.println();
//            String str = Base64.encode(bt);
//            System.out.println(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "RKA " + accessKeyId + ":" + str;
    }
}
