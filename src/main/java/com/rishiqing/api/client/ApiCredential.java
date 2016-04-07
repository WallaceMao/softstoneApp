package com.rishiqing.api.client;

import com.rishiqing.softstone.util.GlobalConfig;

/**
 * api客户端的id、key等信息
 * User: user 毛文强
 * Date: 2016/4/7
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class ApiCredential {

    private String accessKeyId;
    private String accessKeySecret;
    private String serverLocation;

    //  use default location
    public ApiCredential(){
        this.serverLocation = GlobalConfig.RKAServerLocation;
        this.accessKeyId = GlobalConfig.accessKeyId;
        this.accessKeySecret = GlobalConfig.accessKeySecret;
    }

    public ApiCredential(String appKeyId, String appKeySecret, String serverLocation){
        this.accessKeyId = appKeyId;
        this.accessKeySecret = appKeySecret;
        this.serverLocation = serverLocation;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getServerLocation() {
        return serverLocation;
    }

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }
}
