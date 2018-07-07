package br.com.douglasffilho.CarServices.repositories;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBuildYear(Integer buildYear);

    List<Car> findByBuildYearGreaterThanEqual(Integer buildYear);

    List<Car> findByBuildYearLessThanEqual(Integer buildYear);

    List<Car> findByBuildYearBetween(Integer startBuildYear, Integer endBuildYear);

    Car findByName(String name);

    List<Car> findByMaker(CarMaker maker);

}
