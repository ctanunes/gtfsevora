import org.onebusaway.gtfs.model.*;
import org.onebusaway.gtfs.serialization.GtfsWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GtfsWriterExample {

    public File directory;

    public GtfsWriterExample() throws IOException {
        setup();
    }

    public void setup() throws IOException {
        directory = new File("/Users/catarina/Desktop/GTFSEvora/src/main/resources/files");
        if (directory.exists())
            deleteFileRecursively(directory);
        directory.mkdirs();
    }

    public void writeToFile() throws IOException {

        GtfsWriter writer = new GtfsWriter();
        writer.setOutputLocation(directory);

        Agency agency = new Agency();
        agency.setName("Transportes de Évora");
        agency.setId("TE");
        agency.setUrl("http://www.trevo.com.pt/");
        agency.setTimezone("Europe/Lisbon");
        agency.setLang("pt");

        writer.handleEntity(agency);

        createStops(writer);

        createRoutes(writer, agency);

        ServiceCalendar serviceCalendar = new ServiceCalendar();


        writer.close();
    }

    List<Stop> listStops = new ArrayList<>();

    private void createStops(GtfsWriter writer) {

        int id = 0;

        Stop stop = new Stop();
        AgencyAndId agencyAndId = new AgencyAndId("TE",""+ id++ +"");
        stop.setId(agencyAndId);
        stop.setName("Granito");
        //0

        listStops.add(stop);
        writer.handleEntity(stop);

        agencyAndId.setId(""+ id++ +"");
        stop.setId(agencyAndId);
        stop.setName("Louredo");
        //1

        writer.handleEntity(stop);

        agencyAndId.setId(""+ id++ +"");
        stop.setId(agencyAndId);
        stop.setName("Sr.Aflitos");

        writer.handleEntity(stop);

        agencyAndId.setId(""+ id++ +"");
        stop.setId(agencyAndId);
        stop.setName("Esc.EB 2,3 Pites");

        writer.handleEntity(stop);

        agencyAndId.setId(""+ id++ +"");
        stop.setId(agencyAndId);
        stop.setName("Lg. Luís de Camões");

        writer.handleEntity(stop);
    }

    private void createRoutes(GtfsWriter writer, Agency agency) {

        int idTrip = 0;

        Route route = new Route();
        AgencyAndId agencyAndId = new AgencyAndId("TE","21");
        route.setId(agencyAndId);
        route.setShortName("21");
        route.setAgency(agency);
        route.setLongName("Louredo-Luís de Camões");

        writer.handleEntity(route);

        Trip trip = new Trip();
        trip.setServiceId(new AgencyAndId("TE",""+ idTrip++ +""));
        trip.setRoute(route);
        trip.setTripShortName("21A");
        trip.setId(new AgencyAndId("TE",""+ idTrip +""));

        writer.handleEntity(trip);

        StopTime stopTime = new StopTime();
        stopTime.setTrip(trip);
        stopTime.setStop(listStops.get(0));
        stopTime.setArrivalTime(000620);
        stopTime.setDepartureTime(000620);
        writer.handleEntity(stopTime);

        String id = "22";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Canaviais-Parque Industrial (via Malagueira)");

        writer.handleEntity(route);

        id = "23";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Garraia-Almeirim");

        writer.handleEntity(route);

        id = "24";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Canaviais-Parque Industrial (via centro histórico)");

        writer.handleEntity(route);

        id = "25";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Canaviais-Luís de Camões");

        writer.handleEntity(route);

        id = "31";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("25 de Abril-Malagueira");

        writer.handleEntity(route);

        id = "32";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("25 de Abril-Malagueira");

        writer.handleEntity(route);

        id = "33";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Sra.Saúde-Fontanas");

        writer.handleEntity(route);

        id = "34";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Cruz Picada-Sra.Saúde");

        writer.handleEntity(route);

        id = "41";
        agencyAndId.setId(id);
        route.setId(agencyAndId);
        route.setShortName(id);
        route.setAgency(agency);
        route.setLongName("Gabriel Pereira-Casinha");

        writer.handleEntity(route);
    }


    public static void deleteFileRecursively(File file) {

        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files)
                    deleteFileRecursively(child);
            }
        }

        file.delete();
    }

    public static void main(String[] args) throws IOException {

        GtfsWriterExample gtfsWriterExample = new GtfsWriterExample();

        gtfsWriterExample.writeToFile();
    }
}