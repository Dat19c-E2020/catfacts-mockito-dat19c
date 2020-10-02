package dk.kea.dat19c.catfactsmockitodat19c.controllers;

import dk.kea.dat19c.catfactsmockitodat19c.models.CatFact;
import dk.kea.dat19c.catfactsmockitodat19c.repositories.CatFactFetcher;
import dk.kea.dat19c.catfactsmockitodat19c.services.CatFactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class CatFactController {
    //Instantiating as an attribute to the controller - all mappings can use the service
    CatFactService catFactService = new CatFactService(new CatFactFetcher());

    //Welcome message
    @GetMapping("/")
    @ResponseBody
    public String welcome(){
        return "Welcome to catfacts! Facts about cats";
    }

    @GetMapping("/getSingle")
    @ResponseBody
    public String getSingle() throws IOException {
        return catFactService.getSingleCatFact().getText();
    }

    @GetMapping("/getTen")
    @ResponseBody
    public ArrayList<CatFact> getTen() throws IOException {
        return catFactService.getTen();
    }

    @GetMapping("/getTenSortedByDate")
    @ResponseBody
    public ArrayList<CatFact> getTenSortedByDate() throws IOException {
        return catFactService.getTenSortedByDate();
    }

    @GetMapping("/contains")
    @ResponseBody
    public String contains(@RequestParam char c, int amount) throws IOException {
        return catFactService.getCatIfContainsLetter(c,amount);
    }
}
