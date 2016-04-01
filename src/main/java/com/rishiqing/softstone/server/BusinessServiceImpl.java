package com.rishiqing.softstone.server;

import com.rishiqing.api.client.RKAClient;
import com.rishiqing.softstone.ServiceManager;
import com.rishiqing.softstone.handler.BusinessServiceHandler;
import com.rishiqing.softstone.model.ServiceDeserializer;
import com.rishiqing.softstone.model.ServiceSerializer;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.rishiqing.softstone.server.BusinessService",
            serviceName = "BusinessService")
public class BusinessServiceImpl implements BusinessService {

    public String execute(@WebParam(name = "requestXML") String requestXML) {
        System.out.println("-----execute in TeamGroupSyncService: " + requestXML);

        ServiceManager mgr = new ServiceManager(new BusinessServiceHandler(), new RKAClient());

        ServiceDeserializer request = new ServiceDeserializer(requestXML);

        ServiceSerializer response = mgr.executeBusiness(request);

        return response.getReturnXML();
    }
}
