package br.com.douglasffilho.CarServices.utils.factories;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.vos.CarVo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    @Autowired
    private CarMakerService carMakerService;

    public Car createCar(Long id, CarVo carVo) throws ServiceException {
        return Car
                .builder()
                .id(id)
                .name(carVo.getName())
                .description(carVo.getDescription())
                .buildYear(carVo.getBuildYear())
                .image(carVo.getImage())
                .maker(carMakerService.findByName(carVo.getMaker()))
                .build();
    }

}
