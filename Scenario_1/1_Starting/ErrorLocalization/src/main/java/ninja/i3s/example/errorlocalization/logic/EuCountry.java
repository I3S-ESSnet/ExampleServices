package ninja.i3s.example.errorlocalization.logic;

import java.util.stream.Collectors;

import ninja.i3s.example.errorlocalization.service.CountryService;

public class EuCountry {

    private String country;
    private CountryService countryService;

    public EuCountry(String country, CountryService countryService) {
        this.country = country;
        this.countryService = countryService;
    }    

    public boolean isValid() {
        var validCountries = countryService.GetValidCountries();

        var validCountryCodes = validCountries.stream().map(x -> x.getCode()).collect((Collectors.toList()));

        return validCountryCodes.contains(country);        
    }
}
