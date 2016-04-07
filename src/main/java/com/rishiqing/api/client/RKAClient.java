package com.rishiqing.api.client;

import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;
import com.rishiqing.softstone.util.GlobalConfig;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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
public class RKAClient implements ApiClient {

    private ApiCredential credential;

    public RKAClient(){
        this.credential = new ApiCredential();
    }

    public RKAClient(ApiCredential credential){
        this.credential = credential;
    }

    private static String getCanonicalizedRKAHeaders(){
        //return "x-rka-magic:abracadabra\nx-rka-meta-author:foo@bar.com\n";
        return "";
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

    public static String sign(String before, ApiCredential credential){
        SecretKeySpec signingKey = new SecretKeySpec(credential.getAccessKeySecret().getBytes(), "HmacSHA1");
        Mac mac;
        String str = "";
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            str = Base64.encode(mac.doFinal(before.getBytes()));
            System.out.println(str);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "RKA " + credential.getAccessKeyId() + ":" + str;
    }

    public ApiResponse post(ApiRequest request){

        String result = null;
        int statusCode = 0;
        ApiResponse response;
        String contentMd5 = "";
        String targetUrl = request.getTargetURL();
        Map<String, String> params = request.getParams();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost( this.credential.getServerLocation() + targetUrl);

            if(params != null){

                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                Iterator it = params.entrySet().iterator();

                while (it.hasNext()){
                    Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
                    formparams.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                httpPost.setEntity(entity);

                contentMd5 = signMd5(EntityUtils.toByteArray(entity));
                httpPost.addHeader("Content-MD5", contentMd5);

            }

            String signature;
            String verb = "POST";
            String contentType = "application/x-www-form-urlencoded;charset=utf-8";
            String date = getGMTString();
            String RKAHeaders = getCanonicalizedRKAHeaders();
            String resource = targetUrl;

            String beforeSig = verb + "\n"
                    + contentMd5 + "\n"
                    + contentType + "\n"
                    + date + "\n"
                    + RKAHeaders
                    + resource;

            signature = sign(beforeSig, this.credential);

            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Date", date);
            httpPost.addHeader("Authorization", signature);
            CloseableHttpResponse response1 = httpClient.execute(httpPost);

            try {
                StatusLine sl = response1.getStatusLine();
                statusCode = sl.getStatusCode();

                HttpEntity entity1 = response1.getEntity();
                result = EntityUtils.toString(entity1);
                EntityUtils.consume(entity1);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response1.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(statusCode > 200 || null == result){
                return new ApiResponse(statusCode);
            }else{
                return new ApiResponse(statusCode, new JSONObject(result));
            }

        }
    }

    // TODO to implement get method of this client
    public ApiResponse get(ApiRequest request){
        return null;
    }

    public ApiResponse send(ApiRequest request) {
        String method = request.getMethod();

        if("POST".equals(method)){
             return this.post(request);
        }else if("GET".equals(method)) {
            return this.get(request);
        }else{
            throw new MethodNotSupportedException(method + " is not supported in RKAClient");
        }
    }
}
