/**
 * Created by Apache CXF WadlToJava code generator
**/
package com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.application;

import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsRequestType;
import com.luxoft.mfcautotests.services.dashboard.dashboardsupportservice.ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getMainMmcStats")
    public MainMmcStatsResponseType postGetMainMmcStats(MainMmcStatsRequestType mainmmcstatsrequesttype) {
        //TODO: implement
        return null;
    }

    @POST
    @Produces("application/json;charset=UTF-8")
    @Path("/getMonthMmcStats/")
    public Response postGetMonthMmcStats(@QueryParam("periodStart") String periodStart) {
        //TODO: implement
        return null;
    }

    @POST
    @Produces("application/json;charset=UTF-8")
    @Path("/getMonthMmcTableStats/")
    public Response postGetMonthMmcTableStats(@QueryParam("periodStart") String periodStart) {
        //TODO: implement
        return null;
    }

}