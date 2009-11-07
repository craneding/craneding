
package com.webxml.translator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HelloWebXmlResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "helloWebXmlResult"
})
@XmlRootElement(name = "HelloWebXmlResponse")
public class HelloWebXmlResponse {

    @XmlElement(name = "HelloWebXmlResult")
    protected String helloWebXmlResult;

    /**
     * Gets the value of the helloWebXmlResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHelloWebXmlResult() {
        return helloWebXmlResult;
    }

    /**
     * Sets the value of the helloWebXmlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHelloWebXmlResult(String value) {
        this.helloWebXmlResult = value;
    }

}
