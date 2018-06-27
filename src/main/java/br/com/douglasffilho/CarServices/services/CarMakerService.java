package br.com.douglasffilho.CarServices.services;

import br.com.douglasffilho.CarServices.dto.CarMakerDto;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface CarMakerService {

    CarMaker register(CarMakerDto carMaker) throws ServiceException;

    CarMaker updateInfo(Long id, CarMakerDto carMaker) throws ServiceException;

    List<CarMaker> list() throws ServiceException;

    CarMaker findByName(String name) throws ServiceException;

    void createDeafults() throws ServiceException;

}
