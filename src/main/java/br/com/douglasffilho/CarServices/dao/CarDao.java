package br.com.douglasffilho.CarServices.dao;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {

    List<Car> findByBuildYear(Integer buildYear);

    Car findByName(String name);

    List<Car> findByMaker(CarMaker maker);

}
