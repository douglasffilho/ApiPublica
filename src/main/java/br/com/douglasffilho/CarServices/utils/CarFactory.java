package br.com.douglasffilho.CarServices.utils;

import br.com.douglasffilho.CarServices.dto.CarDto;
import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    @Autowired
    private CarMakerService carMakerService;

    public Car createCar(Long id, CarDto carDto) throws ServiceException {
        return Car
                .builder()
                .id(id)
                .name(carDto.getName())
                .description(carDto.getDescription())
                .buildYear(carDto.getBuildYear())
                .image(carDto.getImage())
                .maker(carMakerService.findByName(carDto.getMaker()))
                .build();
    }

}
