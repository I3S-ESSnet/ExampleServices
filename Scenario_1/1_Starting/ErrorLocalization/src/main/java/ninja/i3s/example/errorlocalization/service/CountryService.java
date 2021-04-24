package ninja.i3s.example.errorlocalization.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.i3s.example.errorlocalization.bean.Country;
import ninja.i3s.example.errorlocalization.configuration.CodeListSettings;

public class CountryService {

    private CodeListSettings codeListSettings;

    public CountryService(CodeListSettings settings) {
        this.codeListSettings = settings;
    }

    public List<Country> GetValidCountries() {

        var filePath = codeListSettings.getPath();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Country> countries = null;
        try {
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() { };
            InputStream inputStream = TypeReference.class.getResourceAsStream(filePath);

            countries = objectMapper.readValue(inputStream, typeReference);
            return countries;

        } catch (JsonProcessingException e) {            
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }

}
