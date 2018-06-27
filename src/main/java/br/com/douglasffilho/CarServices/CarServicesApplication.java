package br.com.douglasffilho.CarServices;

import br.com.douglasffilho.CarServices.rest.api.SwaggerConfig;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@Import({SwaggerConfig.class})
public class CarServicesApplication {

    @Autowired
    private CarMakerService carMakerService;

    @PostConstruct
    private void init() {
        try {
            carMakerService.createDefaults();
        } catch(ServiceException sex) {
            log.error("M=CarServicesApplication.init, E=Erro ao tentar criar padrao: {}", sex.getMessage(), sex);
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(CarServicesApplication.class, args);
    }
}
