package dk.kea.dat19c.catfactsmockitodat19c.services;

import dk.kea.dat19c.catfactsmockitodat19c.models.CatFact;
import dk.kea.dat19c.catfactsmockitodat19c.repositories.CatFactFetcher;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class CatFactServiceTest {
    CatFactFetcher mockedFetcher;   //Mocked fetcher-object that will return local data
    CatFactService service;         //Service we want to test


    @BeforeEach
    public void instantiateWithMockObject(){
        //Instantiating the fetcher with the mocked fetcher object
        mockedFetcher = Mockito.mock(CatFactFetcher.class);

        //Instantiating the service with the mocked fetcher object
        service = new CatFactService(mockedFetcher);
    }


    @Test
    public void getSingleCatFact() throws IOException {
        //When the mocked fetcher object is called, we are returning test data defined in test.resources.catfact.json
        Mockito.when(mockedFetcher.fetchSingleCatFact()).thenReturn(fetchSingleLocalCatFact());

        //Making assertion(s) - one for brevity
        assertEquals("The cat's footpads absorb the shocks of the landing when the cat jumps." , service.getSingleCatFact().getText());
        verify(mockedFetcher, times(1)).fetchSingleCatFact();
    }

    @Test
    public void getTen() throws IOException {
        //Verify that the getSingleCatFact() method calls the fetcher 10 times exactly by the service .getTen() Method
        service.getTen();
        verify(mockedFetcher, times(10)).fetchSingleCatFact();
    }

    private CatFact fetchSingleLocalCatFact() throws FileNotFoundException {
        //Reading a local json test-file (instead of fetching)
        JsonReader catfactReader = new JsonReader(new FileReader(new File("src/test/resources/catfact.json")));
        return new Gson().fromJson(catfactReader,CatFact.class);
    }
}