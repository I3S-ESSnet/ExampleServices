package ninja.i3s.example.errorlocalization.logic;

import java.util.List;
import java.util.stream.Collectors;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.i3s.example.errorlocalization.bean.Country;
import ninja.i3s.example.errorlocalization.controller.CodeListSettings;

public class EuCountry {

    private String country;    
    private CodeListSettings settings;

    public EuCountry(String country, CodeListSettings settings) {
        this.country = country;
        this.settings = settings;
    }
    
    private List<Country> GetValidCountries() {
        
        var url = settings.getHost() + "/countries";
        final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10)).build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        ObjectMapper mapper = new ObjectMapper();        

        HttpResponse<String> response = null;
        List<Country> countries = null;
    
        try {
            
            response = (HttpResponse<String>) client.send(request, BodyHandlers.ofString());

            String body = response.body();
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() { };

            countries = mapper.readValue(body, typeReference);
            return countries;

        } catch (Exception ex) {            
            ex.printStackTrace();
        }
        return countries;
    }

    public boolean isValid() {
        
        var validCountries = GetValidCountries();

        var validCountryCodes = validCountries.stream().map(x -> x.getCode()).collect((Collectors.toList()));

        return validCountryCodes.contains(country);
    }
}
