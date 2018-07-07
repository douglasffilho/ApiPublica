package br.com.douglasffilho.CarServices.services.impl;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.repositories.CarRepository;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.services.CarService;
import br.com.douglasffilho.CarServices.utils.factories.CarFactory;
import br.com.douglasffilho.CarServices.utils.loaders.TemplateImageLoader;
import br.com.douglasffilho.CarServices.vos.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMakerService carMakerService;

    @Autowired
    private CarFactory carFactory;

    @Autowired
    private TemplateImageLoader templateImageLoader;

    @Override
    public Car register(CarVo car) throws ServiceException {
        log.info("M=CarServiceImpl.register, I=Cadastrando carro: {}", car);
        try {
            return carRepository.saveAndFlush(carFactory.createCar(null, car));
        } catch (Exception e) {
            log.error("M=CarServiceImpl.register, E=Erro ao tentar cadastrar carro: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Car updateInfo(Long id, CarVo car) throws ServiceException {
        Car carModel = carFactory.createCar(id, car);
        log.info("M=CarServiceImpl.updateInfo, I=Atualizando carro: {}", carModel);
        try {
            return carRepository.saveAndFlush(carModel);
        } catch (Exception e) {
            log.error("M=CarServiceImpl.updateInfo, E=Erro ao tentar atualizar carro: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            log.info("M=CarServiceImpl.delete, I=Procurando carro por id: {}", id);

            carRepository.findById(id).ifPresent(car -> {
                log.info("M=CarServiceImpl.delete, I=Removendo carro por id: {}", car.getId());
                carRepository.delete(car);
                log.info("M=CarServiceImpl.delete, I=Carro removido com exito");
            });
        } catch (Exception e) {
            log.error("M=CarServiceImpl.delete, E=Erro ao tentar remover carro por id: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> list(Integer fromThisYear, Integer toThisYear) throws ServiceException {
        try {
            if (fromThisYear != null) {
                if (toThisYear != null && toThisYear > 0) {
                    if (fromThisYear.equals(toThisYear)) {
                        log.info("M=CarServiceImpl.list, I=Listando carros fabricados em {}", fromThisYear);
                        return carRepository.findByBuildYear(fromThisYear);
                    } else {
                        log.info("M=CarServiceImpl.list, I=Listando carros fabricados entre {} e {}", fromThisYear, toThisYear);
                        return carRepository.findByBuildYearBetween(fromThisYear, toThisYear);
                    }
                } else {
                    log.info("M=CarServiceImpl.list, I=Listando carros fabricados a partir de {}", fromThisYear);
                    return carRepository.findByBuildYearGreaterThanEqual(fromThisYear);
                }
            } else {
                if (toThisYear != null && toThisYear > 0) {
                    log.info("M=CarServiceImpl.list, I=Listando carros fabricados ate {}", toThisYear);
                    return carRepository.findByBuildYearLessThanEqual(toThisYear);
                } else {
                    log.info("M=CarServiceImpl.list, I=Listando carros fabricados");
                    return carRepository.findAll();
                }
            }
        } catch (Exception e) {
            log.error("M=CarServiceImpl.list, E=Erro ao tentar listar carros fabricados: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Car findByName(String name) throws ServiceException {
        try {
            log.info("M=CarServiceImpl.findByName, I=Obtendo carro por nome {}", name);
            return carRepository.findByName(name);
        } catch (Exception e) {
            log.error("M=CarServiceImpl.findByName, E=Erro ao tentar obter carro por nome: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Car> findByMaker(CarMaker maker) throws ServiceException {
        try {
            log.info("M=CarServiceImpl.findByMaker, I=Obtendo carro por fabricante {}", maker);
            return carRepository.findByMaker(maker);
        } catch (Exception e) {
            log.error("M=CarServiceImpl.findByMaker, E=Erro ao tentar obter carro por fabricante: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void createDefaults() throws ServiceException {
        log.info("M=CarServiceImpl.createDefaults, I=criando padrao de carros.");
        try {
            List<Car> cars = new ArrayList<>();

            cars.add(Car
                    .builder()
                    .name("Ferrari 458 Italia")
                    .maker(carMakerService.findByName("Ferrari"))
                    .buildYear(2011)
                    .image(templateImageLoader.getImage("cars", "Ferrari"))
                    .description("4.5 V8 32v Gasolina 2p Automática")
                    .build());

            cars.add(Car
                    .builder()
                    .name("Fiat 147")
                    .maker(carMakerService.findByName("Fiat"))
                    .buildYear(1976)
                    .image(templateImageLoader.getImage("cars", "Fiat"))
                    .description("Primeiro Carro da FIAT produzido no Brasil")
                    .build());

            cars.add(Car
                    .builder()
                    .name("Ranger")
                    .maker(carMakerService.findByName("Ford"))
                    .buildYear(2017)
                    .image(templateImageLoader.getImage("cars", "Ford"))
                    .description("Ford Ranger XLT 3.2 Diesel 4X4 AT")
                    .build());

            cars.add(Car
                    .builder()
                    .name("Atlas")
                    .maker(carMakerService.findByName("Volkswagen"))
                    .buildYear(2018)
                    .image(templateImageLoader.getImage("cars", "Volkswagen"))
                    .description("Atlas 2.0T SE with 4 usb ports, Composition Media touchscreen sound system and Blind Spot Monitor and Rear Traffic Alert")
                    .build());

            cars.add(Car
                    .builder()
                    .name("The Motiv")
                    .maker(carMakerService.findByName("Yamaha"))
                    .buildYear(2015)
                    .image(templateImageLoader.getImage("cars", "Yamaha"))
                    .description("Gordon Murray's radical new carbonfibre manufacturing system")
                    .build());

            carRepository.saveAll(cars);
        } catch (Exception e) {
            log.error("M=CarServiceImpl.createDefaults, E=Erro ao tentar criar padroes: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
