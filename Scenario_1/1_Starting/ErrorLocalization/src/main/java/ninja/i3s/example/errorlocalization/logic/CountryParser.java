package ninja.i3s.example.errorlocalization.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ninja.i3s.example.errorlocalization.bean.Country;

public class CountryParser {

    public static List<Country> parse() 
     {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Country> countries;
        try
        {            
            File file = new File("./src/main/resources/countries.json");
            
            countries = objectMapper.readValue(file, new TypeReference<List<Country>>(){});
            return countries;
            
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();            
        } catch (IOException e ){
            e.printStackTrace();
        }

        return new ArrayList<Country>();
    }
}