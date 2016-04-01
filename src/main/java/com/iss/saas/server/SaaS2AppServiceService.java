package com.iss.saas.server;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.1.5
 * 2016-03-31T21:56:11.057+08:00
 * Generated source version: 3.1.5
 * 
 */
@WebServiceClient(name = "SaaS2AppServiceService", 
                  wsdlLocation = "http://10.9.80.24:9081/csop-apiserver/services/SaaS2AppService?wsdl",
                  targetNamespace = "http://server.saas.iss.com") 
public class SaaS2AppServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://server.saas.iss.com", "SaaS2AppServiceService");
    public final static QName SaaS2AppService = new QName("http://server.saas.iss.com", "SaaS2AppService");
    static {
        URL url = null;
        try {
            url = new URL("http://10.9.80.24:9081/csop-apiserver/services/SaaS2AppService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SaaS2AppServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.9.80.24:9081/csop-apiserver/services/SaaS2AppService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SaaS2AppServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SaaS2AppServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SaaS2AppServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public SaaS2AppServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SaaS2AppServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SaaS2AppServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns SaaS2AppService
     */
    @WebEndpoint(name = "SaaS2AppService")
    public SaaS2AppService getSaaS2AppService() {
        return super.getPort(SaaS2AppService, SaaS2AppService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SaaS2AppService
     */
    @WebEndpoint(name = "SaaS2AppService")
    public SaaS2AppService getSaaS2AppService(WebServiceFeature... features) {
        return super.getPort(SaaS2AppService, SaaS2AppService.class, features);
    }

}
