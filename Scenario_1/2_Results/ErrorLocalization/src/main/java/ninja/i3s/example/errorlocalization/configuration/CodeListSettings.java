package ninja.i3s.example.errorlocalization.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("codelist")
public class CodeListSettings {

    
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
}
