package billydev;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "billydev")
public class OneClickProperties {
    String dbquery="select *";

    public String getDbquery() {
        return dbquery;
    }

    public void setDbquery(String dbquery) {
        this.dbquery = dbquery;
    }
}
