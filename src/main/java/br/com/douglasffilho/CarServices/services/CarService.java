package br.com.douglasffilho.CarServices.services;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.vos.CarVo;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface CarService {

    Car register(CarVo car) throws ServiceException;

    Car updateInfo(Long id, CarVo car) throws ServiceException;

    void delete(Long id) throws ServiceException;

    List<Car> list(Integer fromThisYear, Integer toThisYear) throws ServiceException;

    Car findByName(String name) throws ServiceException;

    List<Car> findByMaker(CarMaker maker) throws ServiceException;

    void createDefaults() throws ServiceException;

}
