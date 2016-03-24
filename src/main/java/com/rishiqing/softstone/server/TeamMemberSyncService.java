package com.rishiqing.softstone.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
@WebService
public interface TeamMemberSyncService {

    @WebMethod
    @WebResult(name = "executeReturn")
    String execute(@WebParam(name = "requestXML") String requestXML);
}
