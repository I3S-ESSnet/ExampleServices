package ninja.i3s.example.errorlocalization.logic;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import java.util.ArrayList;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate(String country, String weather) {

        ErrorlocalizationValidationError error;
        //Pack response
        ArrayList<ErrorlocalizationValidationError> errors = new ArrayList<ErrorlocalizationValidationError>();

        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5010"))
                .build();

        try {
            HttpResponse<String> response1;
            response1 = (HttpResponse<String>) client.newBuilder()
                    .build()
                    .send(request1, BodyHandlers.ofString());
            System.out.println(response1.body());
        } catch (Exception ex) {
            error = new ErrorlocalizationValidationError("Codelist service not available", "000", ex.getLocalizedMessage());
            errors.add(error);
        }

        //Rule 1 : check if the country is in the allowed list      
        error = new ErrorlocalizationValidationError("country not allowed", "001", "Country not in the list");
        errors.add(error);

        //Rule 2 : check if the weather allowed for the counrty
        error = new ErrorlocalizationValidationError("weather not allowed", "002", "weather not allowed");
        errors.add(error);

        return errors;
    }

}
