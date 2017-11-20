/**
 * Created by Apache CXF WadlToJava code generator
**/
package application;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ru.mos.mmc.mmc.dashboard.MainMmcStatsRequestType;
import ru.mos.mmc.mmc.dashboard.MainMmcStatsResponseType;

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