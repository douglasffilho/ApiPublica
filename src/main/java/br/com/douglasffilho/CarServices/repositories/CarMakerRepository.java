package br.com.douglasffilho.CarServices.repositories;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMakerRepository extends JpaRepository<CarMaker, Long> {

    CarMaker findByName(String name);

}
