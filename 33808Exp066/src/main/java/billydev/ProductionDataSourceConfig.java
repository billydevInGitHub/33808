package billydev;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Production")
public class ProductionDataSourceConfig implements DataSourceConfig {
    public void setup() {
        System.out.println("setting up production data source!");
    }
}
