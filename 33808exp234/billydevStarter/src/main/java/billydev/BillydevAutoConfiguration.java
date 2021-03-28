package billydev;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class BillydevAutoConfiguration {
    @Autowired
    TcpDumpBean tcpDumpBean;

    @Bean
    public BillydevBean getBillydevBean(){
        return new BillydevBean();
    }

    @Bean
    public TcpDumpBean  getTcpDumpBean(){
        return new TcpDumpBean();
    }


    @GetMapping("/oneclick")
    public String getOneClickInfo(){
        return tcpDumpBean.triggerTcpDump(10);

    }
}
