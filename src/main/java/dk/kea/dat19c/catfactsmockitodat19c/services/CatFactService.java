package dk.kea.dat19c.catfactsmockitodat19c.services;

import dk.kea.dat19c.catfactsmockitodat19c.models.CatFact;
import dk.kea.dat19c.catfactsmockitodat19c.repositories.CatFactFetcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CatFactService {
    CatFactFetcher fetcher;

    public CatFactService(CatFactFetcher fetcher){
        this.fetcher = fetcher;
    }

    //Fetching a single cat fact
    public CatFact getSingleCatFact() throws IOException {
        return fetcher.fetchSingleCatFact();
    }
    //Calling the method 10 times
    public ArrayList<CatFact> getTen() throws IOException {
        ArrayList<CatFact> tenCatFacts = new ArrayList<CatFact>();
        for(int i = 0 ; i < 10 ; i++){
            tenCatFacts.add(getSingleCatFact());
        }
        return tenCatFacts;
    }
    //Sorting collection of cat facts
    public ArrayList<CatFact> getTenSortedByDate() throws IOException {
        ArrayList<CatFact> catFactsToReturn = getTen();
        Collections.sort(catFactsToReturn);
        return catFactsToReturn;
    }
    //Only return fact if character is contained in string more than n times
    public String getCatIfContainsLetter(char c, int n) throws IOException {
        String catFactText = getSingleCatFact().getText();
        int count = catFactText.length() - catFactText.replaceAll(String.valueOf(c),"").length();
        if(count > n){
            return catFactText;
        }
        else return "Too bad";
    }
}
