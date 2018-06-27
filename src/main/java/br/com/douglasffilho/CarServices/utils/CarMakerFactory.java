package br.com.douglasffilho.CarServices.utils;

import br.com.douglasffilho.CarServices.dto.CarMakerDto;
import br.com.douglasffilho.CarServices.entities.CarMaker;

public class CarMakerFactory {

    public static CarMaker create(Long id, CarMakerDto carMakerDto) {
        return CarMaker
                .builder()
                .id(id)
                .name(carMakerDto.getName())
                .image(carMakerDto.getImage())
                .build();
    }

}
