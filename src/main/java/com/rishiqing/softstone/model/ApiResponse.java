package com.rishiqing.softstone.model;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class ApiResponse {

    private int statusCode;   //  http状态码
    private JSONObject responseJSON;

    public ApiResponse(int statusCode){
        this.statusCode = statusCode;
    }
    public ApiResponse(int statusCode, JSONObject responseJSON){
        this(statusCode);
        this.responseJSON = responseJSON;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public JSONObject getResponseJSON() {
        return responseJSON;
    }

    public void setResponseJSON(JSONObject responseJSON) {
        this.responseJSON = responseJSON;
    }
}
