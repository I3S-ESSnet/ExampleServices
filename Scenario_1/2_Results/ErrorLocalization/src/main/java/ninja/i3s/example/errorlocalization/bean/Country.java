package ninja.i3s.example.errorlocalization.bean;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Country {
    public String country;
    public String code;

    public Country() {
    }

    public Country(String country, String code) {
        this.country = country;
        this.code = code;
    }
    
    
   @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }    

}
