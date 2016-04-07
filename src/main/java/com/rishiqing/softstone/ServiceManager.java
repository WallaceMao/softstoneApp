package com.rishiqing.softstone;

import com.rishiqing.api.client.ApiClient;
import com.rishiqing.softstone.handler.ServiceHandler;
import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;
import com.rishiqing.softstone.model.ServiceDeserializer;
import com.rishiqing.softstone.model.ServiceSerializer;

/**
 * 通用Manager，负责根据传入的handler进行业务处理
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class ServiceManager {

    private ServiceHandler handler;
    private ApiClient client;
    public ServiceManager(ServiceHandler handler, ApiClient client){
        this.handler = handler;
        this.client = client;
    }

    /**
     * 执行handler中的业务逻辑，并封装到ServiceResponse
     * @param request
     * @return
     */
    public ServiceSerializer executeBusiness(ServiceDeserializer request){
        ApiRequest req = this.handler.prepareRequest(request);
        ApiResponse res = this.client.send(req);
        return this.handler.prepareResponse(res);
    }
}
