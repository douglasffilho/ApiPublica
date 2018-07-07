package br.com.douglasffilho.CarServices.services;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.vos.CarMakerVo;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface CarMakerService {

    CarMaker register(CarMakerVo carMaker) throws ServiceException;

    CarMaker updateInfo(Long id, CarMakerVo carMaker) throws ServiceException;

    List<CarMaker> list() throws ServiceException;

    CarMaker findByName(String name) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void createDefaults() throws ServiceException;

}
