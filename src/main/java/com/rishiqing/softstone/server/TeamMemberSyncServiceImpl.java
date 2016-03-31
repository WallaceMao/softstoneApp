package com.rishiqing.softstone.server;

import com.rishiqing.api.client.RKAClient;
import com.rishiqing.softstone.ServiceManager;
import com.rishiqing.softstone.handler.TeamMemberHandler;
import com.rishiqing.softstone.model.ServiceRequest;
import com.rishiqing.softstone.model.ServiceResponse;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.rishiqing.softstone.server.TeamMemberSyncService",
        serviceName = "TeamMemberSyncService")
public class TeamMemberSyncServiceImpl implements TeamMemberSyncService {

    public static final String KEY = "DGx8IgQC";

    public String execute(@WebParam(name = "requestXML") String requestXML) {
        System.out.println("-----execute in TeamMemberSyncService: " + requestXML);
        ServiceManager mgr = new ServiceManager(new TeamMemberHandler(), new RKAClient());

        ServiceRequest request = new ServiceRequest(requestXML);

        ServiceResponse response = mgr.executeBusiness(request);

        return response.getReturnXML();
    }
}
