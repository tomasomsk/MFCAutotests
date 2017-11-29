package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsRequestType;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.ObjectFactory;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.util.*;

import static com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType.Item;
import static org.testng.Assert.assertEquals;

@Helper
public class DashboardHelper extends ServicesHelper {

    public MainMmcStatsResponseType getMainMmcStatsResponse(Date date) {
        MainMmcStatsRequestType requestType = new MainMmcStatsRequestType();
        XMLGregorianCalendar xmlGregorianCalendar = convertToXmlGregorianCalendarDate(date);
        requestType.setPeriodStart(xmlGregorianCalendar);

        log.info("Creating request to Dashboard service");
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement request = objectFactory.createMainMmcStatsRequest(requestType);


        StringWriter stringWriter = marshalAsString(MainMmcStatsRequestType.class, request);


        HttpResponse response = sendPostRequestToService(stringWriter, env.baseUrlWs + env.dashboardUrl + "getMainMmcStats", "application/xml");


        StringBuilder responseAsStringBuilder = getResponseAsStringBuilder(response);


        return (MainMmcStatsResponseType) unmarshalResponse(responseAsStringBuilder, MainMmcStatsResponseType.class);
    }

    public void assertThatResponseContainsItemsOfMainStats(List<Item> item) {
        String[] namesMainStatsItems = new String[]{"HealthCheckAmount", "HealthCheckAmountTotalYear",
                "SvcMMCPaymentAmountAccepted", "SvcMMCPaymentAmountAcceptedTotalYear", "SingleWindowQueueAvgTime",
                "DeliveryQueueAvgTime", "AcceptedDocuments", "AcceptedDocumentsTotalYear", "MMCHandledPatentAmount",
                "MMCHandledPatentAmountTotalYear", "AvgAcceptedDocuments", "InitialIssueAcceptedDocuments",
                "InitialIssueAcceptedDocumentsTotalYear", "ReissueAcceptedDocuments", "ReissueAcceptedDocumentsTotalYear",
                "PersonAddTestAmountPctTotalYear", "SingleWindowCapacityPct", "DeliveryDeskCapacityPct", "OldInitialIssueAcceptedDocuments",
                "LaborExchangeApplicantsAmount", "LaborExchangeOpportunitiesAmount", "LaborExchangeEmployersAmount",
                "SvcMMCPaymentFinancialPlan", "PerformanceSvcMMCPaymentPct", "DMSPaymentFinancialPlan", "DMSIncomeAmount",
                "PerformanceDMSPaymentPct", "30DaysAvgMedResultTime", "OldInitialIssueAcceptedDocumentsTotalYear", "AvgMedResultTimeTotalYear",
                "PatentIssuanceAvgTimeTotalYear", "LaborExchangeApplicantsAmountTotalYear", "LaborExchangeInterviewApplicantsPct",
                "LaborExchangeOpportunitiesAmountTotalYear", "SvcMMCPaymentFinancialPlanTotalYear", "PerformanceSvcMMCPaymentPctTotalYear",
                "DMSPaymentFinancialPlanTotalYear", "DMSIncomeAmountTotalYear", "PerformanceDMSPaymentPctTotalYear",
                "FailedPersonsPctTotalYear", "TestAmount", "TestAmountTotalYear"};

        Set<String> itemsOfMainStats = new HashSet<>(Arrays.asList(namesMainStatsItems));

        Set<String> valuesFromResponse = new HashSet<>();
        for (Item temp : item) {
            valuesFromResponse.add(temp.getName());
        }
        assertEquals(valuesFromResponse, itemsOfMainStats);
    }
}
