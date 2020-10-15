package billydev.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Map;

@Repository
public class AuditEventRepository extends InMemoryAuditEventRepository {

    private static final Logger log = LoggerFactory.getLogger(AuditEventRepository.class);

    public AuditEventRepository() {
        super.setCapacity(200);
    }

    @Override
    public void add(AuditEvent event) {
        super.add(event);
        System.out.println("event is"+event.toString());

    }
}
