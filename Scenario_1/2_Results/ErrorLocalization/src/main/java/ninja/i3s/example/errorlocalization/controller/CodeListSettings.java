package ninja.i3s.example.errorlocalization.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("codelist")
public class CodeListSettings {

    
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost(){
        return host;
    }
}
