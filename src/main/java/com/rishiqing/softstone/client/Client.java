package com.rishiqing.softstone.client;

import com.iss.saas.server.SaaS2AppService;
import com.iss.saas.server.SaaS2AppServiceService;
import com.rishiqing.softstone.server.BusinessService;
import com.rishiqing.softstone.server.TeamMemberSyncService;

import javax.xml.ws.soap.SOAPBinding;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Created with IntelliJ IDEA.
 * User: user 毛文强
 * Date: 2016/3/24
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    private static final String KEY = "DGx8IgQC";

    private static final String NAMESPACE = "http://server.softstone.rishiqing.com/";
    private static final String ROOTURL = "http://localhost:8080/softstoneApp/services/";

    /**
     * 业务接口
     */
    public static void testBusinessService(){
        String singleService = "BusinessService";
        String singleServicePort = "BusinessServicePort";
        QName serviceName = new QName(NAMESPACE, singleService);
        QName portName = new QName(NAMESPACE, singleServicePort);

        Service service = Service.create(serviceName);
        String endpointAddress = ROOTURL + singleService;
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        BusinessService businessService = service.getPort(BusinessService.class);

        System.out.println("------teamMemberSyncService:" + businessService);
        String requestXML = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "  <Msg>\n" +
                "    <Head>\n" +
                "      <Code>APP00001</Code>\n" +
                "      <CTID>7604_20160330160800013_5CFB</CTID>\n" +
                "      <AppID>ff80808148bbb4390148bf866eb201a1</AppID>\n" +
                "      <SubmitTime>20160330160800013</SubmitTime>\n" +
                "      <Version>1</Version>\n" +
                "      <Priority>10</Priority>\n" +
                "    </Head>\n" +
                "    <Body>jxUrnixf3wmjo4ZTyvU7EYibVf8Y7rqfbTiZOr+dkBzNwCp6StiAYqyW+mIUFB/0mgAPMA+ZQV5E\n" +
                "t/PmiH8ms6xAWd2Ue7W83OTkjR8+bBcbrhEOFlSuM79OSsRMKfIExF+VTReFJ6aPykwn9itLSkdn\n" +
                "ird6JEa+vDi1uX38+w+ZvlmzF2FuxKkY+V7GpWRGmT0x9zITIzEL4Z3RH6qXniO6xgJoMg0v9fTb\n" +
                "6ukYyhyK9kILDHyGWG3D3B2QtRhVjNEk2Gt4WQpZHi1MVh8iiV3aPWOZKF5rjRMqCA+BBTmlhRds\n" +
                "VgZdoiWV9ro/CtbpSLp+gXSrf9ZSr0KgRh56KE4nAtvOHA6XSNVPMmco4FKgHf9NBuU0aQM8FYPV\n" +
                "ymReiJe3I9DK2DCJJyzVgzMM7rF/+EgOMSABz6vcqsGUuNuGPyf00IbVLd3AHrTF1BCNA8vHLHjh\n" +
                "4wnLd6egoecu7xTNWb1sglQqycC0rHj6GWIDQetCy6ulq6yOl6zAr65RKjvWN8Pl+mNvFeMz9NHT\n" +
                "ehhBUpRmwfPLk8EEkPFoXjb6Djq9lMuKHIdsIUFULIzMzV/0Vk18FDIdAGVa/LbTujmz5ePmNo84\n" +
                "Byrm+9OpJyqCXczOHsefJDS1KXluvw6B1OJPJNBwMB/IUxrKg8vasR+A4RbXi3J3lXQvhav09/qW\n" +
                "kPHTTOVPP7o2eJSWV+1jIOQS580RGKhjAMIhNWR3/6ynwyYsBn8AVOa/zHsjeQH/PRlwTRq0i5rC\n" +
                "je3c9r21cOUjK9X/WI1DX7OVrUWaWC/WUC/KaNpb1jIgZLDlxqgzYV3Vtg4j1vkleJ+tz5INXLwa\n" +
                "FUMA1XS04Gjg+10GK3x+dS4h8Ih1TmfWtG4kXry7i1+ucxLhw60FjKzM9MdtkChR3Sw9MgGS97zu\n" +
                "IRo/OQbA+Kr663CsPsea/574vHrd2vwqHxca5QHb3qzRLEN9j0VJGXsNInlNxFgNqUCzXspwqDiJ\n" +
                "JxtvQyWTrkMt2e5MIctOzXUdXnmZeQ3Fa7C34MtDLZk0LciccxwPHzdRe3ZZNJnehFCPs2Ya353F\n" +
                "TsU0OFEeRAQPanRisETn/Y2HOH+xVr8AZbqGiaUAsmhrVz3aiYrHlOjgf7iozfvCpdWqE4qujPAG\n" +
                "sQ//EZJh/1vM7OKA7ZvXIY0e1QTFzZCwDB0ijgGtExnpFFY+j2rd4aFA54D+y3jfFhcWJ8cXC8Jb\n" +
                "WzSa/iwqfvZBQ4mX9b68iFyzW11zYkE1H5enUi1VzT/2ctUN1bdPGYjR6aeljPVdam4z+wue5oev\n" +
                "gICnkFIfkoLz8TTE5OzlMKkIHFzHkqs=</Body>\n" +
                "  </Msg>\n";
        String str = businessService.execute(requestXML);

        System.out.println("-------client console-----");
        System.out.println(str);
    }
    /**
     *  团队成员信息同步
     */
    public static void testTeamMemberService(){
        String singleService = "TeamMemberSyncService";
        String singleServicePort = "TeamMemberSyncServicePort";
        QName serviceName = new QName(NAMESPACE, singleService);
        QName portName = new QName(NAMESPACE, singleServicePort);

        Service service = Service.create(serviceName);
        String endpointAddress = ROOTURL + singleService;
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        TeamMemberSyncService teamMemberService = service.getPort(TeamMemberSyncService.class);

        System.out.println("------teamMemberSyncService:" + teamMemberService);
        String requestXML = "";
        String str = teamMemberService.execute(requestXML);

        System.out.println("-------client console-----");
        System.out.println(str);
    }

    /**
     * 团队群组信息同步
     */
    public static void testTeamGroupService(){

    }

    /**
     * 测试web端登录
     */
    public static void testWebLogin(){


        SaaS2AppServiceService mgr = new SaaS2AppServiceService();
        SaaS2AppService service = mgr.getSaaS2AppService();

//        service.execute()
    }


    public static void main(String[] args){
        testWebLogin();
    }
}
