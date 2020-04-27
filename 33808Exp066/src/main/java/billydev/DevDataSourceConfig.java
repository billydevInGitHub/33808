package billydev;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Dev")
public class DevDataSourceConfig implements DataSourceConfig {
    public void setup() {
        System.out.println("setting up dev datasource config!");
    }
}
