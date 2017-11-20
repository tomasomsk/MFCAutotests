
package ru.mos.mmc.mmc.dashboard;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.mos.mmc.mmc.dashboard package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MainMmcStatsRequest_QNAME = new QName("http://mmc.mos.ru/mmc/dashboard", "mainMmcStatsRequest");
    private final static QName _MainMmcStatsResponse_QNAME = new QName("http://mmc.mos.ru/mmc/dashboard", "mainMmcStatsResponse");
    private final static QName _MainMmcStatsResponseType_QNAME = new QName("http://mmc.mos.ru/mmc/dashboard", "mainMmcStatsResponseType");
    private final static QName _MainMmcStatsRequestType_QNAME = new QName("http://mmc.mos.ru/mmc/dashboard", "MainMmcStatsRequestType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.mos.mmc.mmc.dashboard
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MainMmcStatsResponseType }
     * 
     */
    public MainMmcStatsResponseType createMainMmcStatsResponseType() {
        return new MainMmcStatsResponseType();
    }

    /**
     * Create an instance of {@link MainMmcStatsRequestType }
     * 
     */
    public MainMmcStatsRequestType createMainMmcStatsRequestType() {
        return new MainMmcStatsRequestType();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link MainMmcStatsResponseType.Item }
     * 
     */
    public MainMmcStatsResponseType.Item createMainMmcStatsResponseTypeItem() {
        return new MainMmcStatsResponseType.Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MainMmcStatsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmc.mos.ru/mmc/dashboard", name = "mainMmcStatsRequest")
    public JAXBElement<MainMmcStatsRequestType> createMainMmcStatsRequest(MainMmcStatsRequestType value) {
        return new JAXBElement<MainMmcStatsRequestType>(_MainMmcStatsRequest_QNAME, MainMmcStatsRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MainMmcStatsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmc.mos.ru/mmc/dashboard", name = "mainMmcStatsResponse")
    public JAXBElement<MainMmcStatsResponseType> createMainMmcStatsResponse(MainMmcStatsResponseType value) {
        return new JAXBElement<MainMmcStatsResponseType>(_MainMmcStatsResponse_QNAME, MainMmcStatsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MainMmcStatsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmc.mos.ru/mmc/dashboard", name = "mainMmcStatsResponseType")
    public JAXBElement<MainMmcStatsResponseType> createMainMmcStatsResponseType(MainMmcStatsResponseType value) {
        return new JAXBElement<MainMmcStatsResponseType>(_MainMmcStatsResponseType_QNAME, MainMmcStatsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MainMmcStatsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mmc.mos.ru/mmc/dashboard", name = "MainMmcStatsRequestType")
    public JAXBElement<MainMmcStatsRequestType> createMainMmcStatsRequestType(MainMmcStatsRequestType value) {
        return new JAXBElement<MainMmcStatsRequestType>(_MainMmcStatsRequestType_QNAME, MainMmcStatsRequestType.class, null, value);
    }

}
