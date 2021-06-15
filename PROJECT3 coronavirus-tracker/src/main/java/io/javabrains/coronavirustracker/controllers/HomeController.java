package io.javabrains.coronavirustracker.controllers;


import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


//this will be used for rendering in a HTML-UI format....
@Controller
public class HomeController {

    @Autowired//this is one of the feature that a service can be autowired to controller...
    CoronaVirusDataServices coronaVirusDataService;

    @GetMapping("/")//this will go to the root...
    public String home(Model model){
        //here model is used so that we can access elements from model and construct html directly..
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats",allStats);//first argument is string second is object
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
