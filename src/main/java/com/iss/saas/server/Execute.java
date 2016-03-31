
package com.iss.saas.server;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type?? Java ??
 * 
 * <p>??????????????????????е?????????
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestXML" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestXML"
})
@XmlRootElement(name = "execute")
public class Execute {

    @XmlElement(required = true)
    protected String requestXML;

    /**
     * ???requestXML????????
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestXML() {
        return requestXML;
    }

    /**
     * ????requestXML????????
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestXML(String value) {
        this.requestXML = value;
    }

}
