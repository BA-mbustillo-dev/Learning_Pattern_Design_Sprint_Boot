package banco.me.solicitud;

import banco.me.generalValidation.configuration.EnableValidacionGeneralModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableValidacionGeneralModule

public class Ms012TrfSolicitudPlanillaApplication {

    @Bean
    public RestTemplate getresttample(){
        return new RestTemplate();
    }
	public static void main(String[] args) {
		SpringApplication.run(Ms012TrfSolicitudPlanillaApplication.class, args);
	}

}
