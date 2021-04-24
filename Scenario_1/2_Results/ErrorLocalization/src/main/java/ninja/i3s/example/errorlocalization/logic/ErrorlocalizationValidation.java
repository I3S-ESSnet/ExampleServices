package ninja.i3s.example.errorlocalization.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ninja.i3s.example.errorlocalization.bean.Country;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;
import ninja.i3s.example.errorlocalization.controller.CodeListSettings;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate(String country, String weather, CodeListSettings settings) {

        ErrorlocalizationValidationError error;
        //Pack response
        ArrayList<ErrorlocalizationValidationError> errors = new ArrayList<ErrorlocalizationValidationError>();

        // final HttpClient client = HttpClient.newBuilder()
        //         .version(HttpClient.Version.HTTP_1_1)
        //         .connectTimeout(Duration.ofSeconds(10))
        //         .build();

        // HttpRequest request1 = HttpRequest.newBuilder()
        //         .uri(URI.create("http://localhost:5010/countries"))
        //         .build();

        // ObjectMapper mapper = new ObjectMapper();
        // Country[] countries = null;

        // HttpResponse<String> response1 = null;

        // try {

        //     response1 = (HttpResponse<String>) client.newBuilder()
        //             .build()
        //             .send(request1, BodyHandlers.ofString());
            
        //       String response = response1.body();

        //       countries = mapper.readValue(response, Country[].class);     

        // } catch (Exception ex) {
        //     error = new ErrorlocalizationValidationError("Codelist service not available", "000", ex.getLocalizedMessage());
        //     errors.add(error);
        // }
        
        // //Rule 1 : check if the country is in the allowed list   
        // boolean found = false;        
        // for (int i=1;i<countries.length;i++) {
        //    if (country.equalsIgnoreCase(countries[i].country)) {
        //        found = true;
        //        break;
        //    }
        // }
        // if (!found) {
        //  error = new ErrorlocalizationValidationError("country not allowed", "001", "Country not in the list");
        //  errors.add(error);
        // }

        // //Rule 2 : check if the weather allowed for the counrty
        // error = new ErrorlocalizationValidationError(weather + " weather not allowed for " + country, "002", "weather not allowed");
        // if (weather.equals("sunny") && country.equalsIgnoreCase("sweden") ||
        //     weather.equals("sunny") && country.equalsIgnoreCase("france") ||
        //     weather.equals("snowy") && country.equalsIgnoreCase("italy")  ||  
        //     weather.equals("snowy") && country.equalsIgnoreCase("portugal")     
        //         ) 
        //  errors.add(error);

        // return errors;

        var euCountry = new EuCountry(country, settings);
        if (!euCountry.isValid()) {
            error = new ErrorlocalizationValidationError("country not allowed", "001", "Country not in the list");
            errors.add(error);

            System.out.println(country +" is an invalid EU country");
        }
        else{
            System.out.println(country +" is a valid EU country");
        }

        //Rule 2 : check if the weather allowed for the counrty
        var countryWeather = new CountryWeather(weather, country);

        if (!countryWeather.isValid()) {
            error = new ErrorlocalizationValidationError("weather not allowed", "002", "weather not allowed");
            errors.add(error);

            System.out.println(weather +" is an invalid weather within " +country);
        }
        else{
            System.out.println(weather +" is a valid weather within " +country);
        }

        return errors;
    }

}
