package ninja.i3s.example.errorlocalization.logic;

import java.util.Arrays;
import java.util.List;

public class CountryWeather {

    private String weather;
    private String country;

    public CountryWeather(String weather, String country) {
        this.weather = weather;
        this.country = country;
    }

    public boolean isValid() {
        List<String> validWeather;
        switch (country) {
        case "SE":
            validWeather = Arrays.asList(new String[] { "Snow", "Hail" });
            break;
        case "GB":
            validWeather = Arrays.asList(new String[] { "Rain", "Fog" });
            break;

        case "IT":
            validWeather = Arrays.asList(new String[] { "Sun" });
            break;

        default:
            validWeather = Arrays.asList(new String[] { "Snow", "Rain", "Sun", "Fog", "Hail", "Cloud" });

            break;
        }
        return validWeather.contains(weather);

    }

}
