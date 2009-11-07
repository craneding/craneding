
package com.webxml.translator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="getEnCnTwoWayTranslatorResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
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
    "getEnCnTwoWayTranslatorResult"
})
@XmlRootElement(name = "getEnCnTwoWayTranslatorResponse")
public class GetEnCnTwoWayTranslatorResponse {

    protected ArrayOfString getEnCnTwoWayTranslatorResult;

    /**
     * Gets the value of the getEnCnTwoWayTranslatorResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetEnCnTwoWayTranslatorResult() {
        return getEnCnTwoWayTranslatorResult;
    }

    /**
     * Sets the value of the getEnCnTwoWayTranslatorResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetEnCnTwoWayTranslatorResult(ArrayOfString value) {
        this.getEnCnTwoWayTranslatorResult = value;
    }

}
