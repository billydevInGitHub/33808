package com.pd.examples.examples;


import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class TestBeanMetrics {
    @Autowired
    MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        Gauge.builder("TestMBeanMetrics", this, TestBeanMetrics::getInformation)
                .baseUnit("time")
                .description("TestBean")
                .register(meterRegistry);

    }

    public Double getInformation() {

        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("PD:name=testBean,category=MBeans");
            AttributeList list = mbs.getAttributes(name, new String[]{"Message"});

            System.out.println("getInformation called");
            return Optional.ofNullable(list)
                    .map(l -> l.isEmpty() ? null : l)
                    .map(List::iterator)
                    .map(Iterator::next)
                    .map(Attribute.class::cast)
                    .map(Attribute::getValue)
                    .map(str->{return Double.valueOf((String)str);})
                    .orElse(null);

        } catch (Exception ex) {
            return null;
        }
    }

}
