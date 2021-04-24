package ninja.i3s.example.errorlocalization.service;

import ninja.i3s.example.errorlocalization.configuration.CodeListSettings;
import java.util.List;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.i3s.example.errorlocalization.bean.Country;

public class CountryService {

    private CodeListSettings codeListSettings;

    public CountryService(CodeListSettings settings) {
        this.codeListSettings = settings;
    }

    public List<Country> GetValidCountries() {

        var url = codeListSettings.getUrl();
        final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10)).build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        ObjectMapper mapper = new ObjectMapper();

        HttpResponse<String> response = null;
        List<Country> countries = null;

        try {

            response = (HttpResponse<String>) client.send(request, BodyHandlers.ofString());

            String body = response.body();
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {
            };

            countries = mapper.readValue(body, typeReference);
            return countries;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countries;
    }

}
