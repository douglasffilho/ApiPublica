package br.com.douglasffilho.CarServices.utils.factories;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.vos.CarMakerVo;
import org.springframework.stereotype.Component;

@Component
public class CarMakerFactory {

    public CarMaker create(Long id, CarMakerVo carMakerVo) {
        return CarMaker
                .builder()
                .id(id)
                .name(carMakerVo.getName())
                .image(carMakerVo.getImage())
                .build();
    }

}
