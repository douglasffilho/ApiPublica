package br.com.douglasffilho.CarServices.services.impl;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.repositories.CarMakerRepository;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.utils.factories.CarMakerFactory;
import br.com.douglasffilho.CarServices.utils.loaders.TemplateImageLoader;
import br.com.douglasffilho.CarServices.vos.CarMakerVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CarMakerServiceImpl implements CarMakerService {

    @Autowired
    private CarMakerRepository carMakerRepository;

    @Autowired
    private CarMakerFactory carMakerFactory;

    @Autowired
    private TemplateImageLoader templateImageLoader;

    @Override
    public CarMaker register(CarMakerVo carMaker) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.register, I=Registrando fabricante: {}", carMaker);
            return carMakerRepository.saveAndFlush(carMakerFactory.create(0L, carMaker));
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.register, E=Erro ao tentar registrar fabricante: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public CarMaker updateInfo(Long id, CarMakerVo carMaker) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.updateInfo, I=Atualizando informações de fabricante: {}", carMaker);
            return carMakerRepository.saveAndFlush(carMakerFactory.create(id, carMaker));
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.updateInfo, E=Erro ao tentar atualizar informações de fabricante: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<CarMaker> list() throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.list, I=Listando fabricantes.");
            return carMakerRepository.findAll();
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.list, E=Erro ao tentar listar fabricantes: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public CarMaker findByName(String name) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.findByName, I=Obtendo fabricante por nome: {}", name);
            return carMakerRepository.findByName(name);
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.findByName, E=Erro ao tentar obter fabricante por nome: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.delete, I=Procurando fabricante por id: {}", id);

            carMakerRepository.findById(id).ifPresent(carMaker -> {
                log.info("M=CarMakerServiceImpl.delete, I=Removendo fabricante por id: {}", carMaker.getId());
                carMakerRepository.delete(carMaker);
                log.info("M=CarMakerServiceImpl.delete, I=Fabricante removido com exito");
            });
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.delete, E=Erro ao tentar remover fabricante por id: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void createDefaults() throws ServiceException {
        log.info("M=CarMakerServiceImpl.createDefaults, I=criando padrao de fabricantes de carros.");
        List<CarMaker> carMakers = new ArrayList<>();

        carMakers.add(CarMaker.builder().name("Ferrari").image(templateImageLoader.getImage("cars_makers", "Ferrari")).build());
        carMakers.add(CarMaker.builder().name("Yamaha").image(templateImageLoader.getImage("cars_makers", "Yamaha")).build());
        carMakers.add(CarMaker.builder().name("Volkswagen").image(templateImageLoader.getImage("cars_makers", "Volkswagen")).build());
        carMakers.add(CarMaker.builder().name("Ford").image(templateImageLoader.getImage("cars_makers", "Ford")).build());
        carMakers.add(CarMaker.builder().name("Fiat").image(templateImageLoader.getImage("cars_makers", "Fiat")).build());

        try {
            carMakerRepository.saveAll(carMakers);
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.createDefaults, E=Erro ao tentar criar padroes: {}", e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
