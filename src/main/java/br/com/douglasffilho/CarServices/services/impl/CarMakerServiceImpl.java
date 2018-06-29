package br.com.douglasffilho.CarServices.services.impl;

import br.com.douglasffilho.CarServices.dao.CarMakerDao;
import br.com.douglasffilho.CarServices.dto.CarMakerDto;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.utils.CarMakerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CarMakerServiceImpl implements CarMakerService {

    @Autowired
    private CarMakerDao carMakerDao;

    @Override
    public CarMaker register(CarMakerDto carMaker) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.register, I=Registrando fabricante: {}", carMaker);
            return carMakerDao.saveAndFlush(CarMakerFactory.create(0L, carMaker));
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.register, E=Erro ao tentar registrar fabricante: {}", e.getMessage(), e);
            throw new ServiceException("Erro ao tentar registrar fabricante.", e);
        }
    }

    @Override
    public CarMaker updateInfo(Long id, CarMakerDto carMaker) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.updateInfo, I=Atualizando informações de fabricante: {}", carMaker);
            return carMakerDao.saveAndFlush(CarMakerFactory.create(id, carMaker));
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.updateInfo, E=Erro ao tentar atualizar informações de fabricante: {}", e.getMessage(), e);
            throw new ServiceException("Erro ao tentar atualizar informações de fabricante.", e);
        }
    }

    @Override
    public List<CarMaker> list() throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.list, I=Listando fabricantes.");
            return carMakerDao.findAll();
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.list, E=Erro ao tentar listar fabricantes: {}", e.getMessage(), e);
            throw new ServiceException("Erro ao tentar listar fabricantes.", e);
        }
    }

    @Override
    public CarMaker findByName(String name) throws ServiceException {
        try {
            log.info("M=CarMakerServiceImpl.findByName, I=Obtendo fabricante por nome: {}", name);
            return carMakerDao.findByName(name);
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.findByName, E=Erro ao tentar obter fabricante por nome: {}", e.getMessage(), e);
            throw new ServiceException("Erro ao tentar obter fabricante por nome.", e);
        }
    }

    private String readImageFile(String fileName) {
        StringBuilder imageContent = new StringBuilder();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("default_images/" + fileName)));
            String line = br.readLine();

            while (line != null) {
                imageContent.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            log.error("M=CarMakerServiceImpl.readImageFile, E=Erro ao tentar carregar imagem: {}", e.getMessage(), e);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                log.error("M=CarMakerServiceImpl.readImageFile, E=Erro ao tentar fechar arquivo de imagem: {}", e.getMessage(), e);
            }
        }

        return imageContent.toString();
    }

    @Override
    public void createDefaults() throws ServiceException {
        List<CarMaker> carMakers = new ArrayList<>();


        carMakers.add(CarMaker.builder().name("Ferrari").image(readImageFile("Ferrari")).build());
        carMakers.add(CarMaker.builder().name("Yamaha").image(readImageFile("Yamaha")).build());
        carMakers.add(CarMaker.builder().name("Volkswagen").image(readImageFile("Volkswagen")).build());
        carMakers.add(CarMaker.builder().name("Ford").image(readImageFile("Ford")).build());
        carMakers.add(CarMaker.builder().name("Fiat").image(readImageFile("Fiat")).build());

        try {
            carMakerDao.saveAll(carMakers);
        } catch (Exception e) {
            log.error("M=CarMakerServiceImpl.createDefaults, E=Erro ao tentar criar padroes: {}", e.getMessage(), e);
            throw new ServiceException("Erro ao tentar criar padroes.", e);
        }

    }
}
