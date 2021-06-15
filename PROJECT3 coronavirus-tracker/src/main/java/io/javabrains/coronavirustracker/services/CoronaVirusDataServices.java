package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

//marking this as a service...
@Service

public class CoronaVirusDataServices {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<>();//instance of LocationStats is created...

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct //this tells to execute the method as soon as the application starts....
    @Scheduled(cron = "* * 1 * * *")//this will make the method run every day...


    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();//this is created because if a person access the data while the new stats are created it will still show the old stats..
        //this will make an http call to the raw data url....
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL)) //convert string to uri
                .build();

        HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
        //here we are sending  a request and then telling what to do with the body..

        System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());//for reading the data...
        //header auto detection
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
           // locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));//here since we have a string we convert it into an integer and since we have to find the last column we do size -1...

            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size()-2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            //System.out.println(locationStat);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
}
