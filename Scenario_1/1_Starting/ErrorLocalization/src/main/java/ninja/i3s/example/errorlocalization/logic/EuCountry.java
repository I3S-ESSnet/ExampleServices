package ninja.i3s.example.errorlocalization.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.i3s.example.errorlocalization.bean.Country;

public class EuCountry {

    private String country;

    public EuCountry(String country) {
        this.country = country;
    }

    public static List<Country> parse() {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Country> countries;
        try {
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() { };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");

            countries = objectMapper.readValue(inputStream, typeReference);
            return countries;

        } catch (JsonProcessingException e) {            
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Country>();
    }

    public boolean isValid() {
        var validCountries = parse();

        var validCountryCodes = validCountries.stream().map(x -> x.getCode()).collect((Collectors.toList()));

        return validCountryCodes.contains(country);        
    }
}