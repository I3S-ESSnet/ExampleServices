package ninja.i3s.example.errorlocalization.logic;

import java.util.ArrayList;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;
import ninja.i3s.example.errorlocalization.configuration.CodeListSettings;
import ninja.i3s.example.errorlocalization.service.CountryService;

public class ErrorlocalizationValidation {

    public static ArrayList<ErrorlocalizationValidationError> validate(String country, String weather, CodeListSettings settings) {

        ErrorlocalizationValidationError error;
        //Pack response
        ArrayList<ErrorlocalizationValidationError> errors = new ArrayList<ErrorlocalizationValidationError>();

        var countryService = new CountryService(settings);
        var euCountry = new EuCountry(country, countryService);

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
