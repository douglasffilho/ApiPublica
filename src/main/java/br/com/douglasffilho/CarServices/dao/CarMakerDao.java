package br.com.douglasffilho.CarServices.dao;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakerDao extends JpaRepository<CarMaker, Long> {

    CarMaker findByName(String name);

}
