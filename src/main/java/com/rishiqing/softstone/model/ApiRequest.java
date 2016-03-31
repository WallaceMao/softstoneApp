package com.rishiqing.softstone.model;

import java.util.Map;

/**
 * 传递给RKAClient请求日事清api的数据model
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class ApiRequest {

    private String method;
    //  请求的地址
    private String targetURL;
    private Map<String, String> params;

    public ApiRequest(String method, String targetURL){
        this.targetURL = targetURL;
        this.method = method;
    }

    public ApiRequest(String method, String targetURL, Map<String, String> params){
        this(method, targetURL);
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
