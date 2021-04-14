package ninja.i3s.example.errorlocalization.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ninja.i3s.example.errorlocalization.bean.Country;
import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate(String country, String weather) {

        ErrorlocalizationValidationError error;
        //Pack response
        ArrayList<ErrorlocalizationValidationError> errors = new ArrayList<ErrorlocalizationValidationError>();

        // Read countries from file
        

        
        // List<Country> countries = EuCountry.parse();

        // List<String> countryCodes = countries.stream().map(x -> x.getCode()).collect((Collectors.toList()));

        //Rule 1 : check if the country is in the allowed list              
        
        // if (!countryCodes.contains(country)) {
        //     error = new ErrorlocalizationValidationError("country not allowed", "001", "Country not in the list");
        //     errors.add(error);
        // }

        var euCountry = new EuCountry(country);
        if (!euCountry.isValid()) {
            error = new ErrorlocalizationValidationError("country not allowed", "001", "Country not in the list");
            errors.add(error);
        }

        //Rule 2 : check if the weather allowed for the counrty
        var countryWeather = new CountryWeather(weather, country);

        if (!countryWeather.isValid()) {
            error = new ErrorlocalizationValidationError("weather not allowed", "002", "weather not allowed");
            errors.add(error);
        }        

        return errors;
    }
}
