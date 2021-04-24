package ninja.i3s.example.errorlocalization.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("codelist")
public class CodeListSettings {
    
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
