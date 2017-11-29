package com.luxoft.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.services.dashboard.jsonresponse.MainInfo;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Test3Jackson extends BaseTest {

    @Test
    @NonDriver
    public void testJacksonToArray() throws IOException, JAXBException {

        String inline = "";

        URL url = new URL("http://zln-mfc-dev1.luxoft.com/dashboard/dashboardSupportService/getMonthMmcStats/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Http Response Code = " + responseCode);
        } else {
//            JAXBContext jaxbContext = JAXBContext.newInstance(MainInfo[].class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
//            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
//            StreamSource streamSource = new StreamSource(connection.getInputStream());
//            MainInfo[] mainInfos = unmarshaller.unmarshal(streamSource, MainInfo[].class).getValue();
            ObjectMapper mapper = new ObjectMapper();
            Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8");
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            MainInfo[] mainInfos = mapper.readValue(inline, MainInfo[].class);
//            Object mainInfos = mapper.readValue(inline, MainInfo[].class);
            String result = (mainInfos[0].getIndicators().get(1).getName());
            System.out.println(result);
            assertEquals(result, "Доля поступлений за месяц в налоговом потенциале");
        }
    }
}
