package com.rishiqing.softstone.handler;

import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;
import com.rishiqing.softstone.model.ServiceRequest;
import com.rishiqing.softstone.model.ServiceResponse;

/**
 * 对于webService请求的处理接口
 * User: user 毛文强
 * Date: 2016/3/30
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceHandler {
    ApiRequest prepareRequest(ServiceRequest request);
    ServiceResponse prepareResponse(ApiResponse response);
}
