# catfacts-mockito
practical example + motivation for mockito

# Motivation
This  example will showcase basic mockito.  
* This spring boot app calls a REST-service (https://cat-fact.herokuapp.com/#/)
- Maps the returned json output to a POJO
- Provides some basic output to the browser.

- The service we want to test is dependant on another object that fetches data from a REST service.
- The objective is to  to isolate and limit the testing scope (we do not want to test the online service)
- This is done by mocking (creating a fake object) that returns local test-data and injecting that into the service that is undergoing tests
- Note: The example is using manual dependency injection for clarity & underlining the synergy between DI, testing & mocking.
