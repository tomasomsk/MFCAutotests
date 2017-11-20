package com.luxoft.mfcautotests.helpers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class ServicesHelper extends BaseHelper {

    XMLGregorianCalendar convertToXmlGregorianCalendarDate(Date date) {
        log.info("Converting date to XML Gregorian Calendar");
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            xmlGregorianCalendar.setTimezone(0);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xmlGregorianCalendar;
    }

    StringWriter marshalAsString(Class marshallerClass, JAXBElement request) {
        log.info("Marshaling request as StringWriter");
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(marshallerClass);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(request, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter;
    }

    HttpResponse sendPostRequestToService(StringWriter request, String serviceUrl, String mimeType) {
        log.info("Sending request to service " + serviceUrl);
        HttpResponse response = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(serviceUrl);
            //Create body of post request
            StringEntity input = new StringEntity(request.toString());
            input.setContentType(mimeType);
            postRequest.setEntity(input);
            //Get response from service
            response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed: Http code = " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    StringBuilder getResponseAsStringBuilder(HttpResponse response) {
        log.info("Getting response from service as StringBuilder");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String output;
            while ((output = bufferedReader.readLine()) != null) {
                stringBuilder.append(output);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    Object unmarshalResponse(StringBuilder responseAsStringBuilder, Class unmarshallerClass) {
        log.info("Unmarshalling response from service");
        StringReader stringReader = new StringReader(responseAsStringBuilder.toString());
        Object unmarshalledResponse = null;
        try {
            JAXBContext jaxbContextResponse = JAXBContext.newInstance(unmarshallerClass);
            Unmarshaller unmarshaller = jaxbContextResponse.createUnmarshaller();
            unmarshalledResponse = unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return unmarshalledResponse;
    }
}
