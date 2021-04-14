package ninja.i3s.example.errorlocalization.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import ninja.i3s.example.errorlocalization.bean.Country;

public class CountryParser {

    public static List<Country> parse() {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Country> countries;
        try {
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");

            File file = new File("./src/main/resources/countries.json");

            countries = objectMapper.readValue(inputStream, typeReference);
            return countries;

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Country>();
    }
}