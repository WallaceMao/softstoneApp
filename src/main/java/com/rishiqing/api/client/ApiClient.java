package com.rishiqing.api.client;

import com.rishiqing.softstone.model.ApiRequest;
import com.rishiqing.softstone.model.ApiResponse;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/31
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ApiClient {
      ApiResponse send(ApiRequest request);
}
