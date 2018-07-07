package br.com.douglasffilho.CarServices.rest.api.v1;

import br.com.douglasffilho.CarServices.entities.Car;
import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.rest.api.publicEndpoints.PublicCarMakerRestApiV1Endpoints;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.services.CarService;
import br.com.douglasffilho.CarServices.vos.CarVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@Api(value = "CarRestApi", description = "API para consultar e modificar carros.")
@RequestMapping(PublicCarMakerRestApiV1Endpoints.PUBLIC_API_V1_CARS_ROOT_ENDPOINT)
public class CarRestApi {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMakerService carMakerService;

    @ApiOperation(value = "Listar carros cadastrados")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Lista de carros recuperada com êxito.",
                    response = Car.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carros registrados no serviço.",
                    response = Collections.class
            )
    })
    @GetMapping
    public List<Car> listAll(HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.listAll, I=Obtendo carros registrados.");
            return carService.list(null, null);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.listAll, E=Erro ao tentar obter carros registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carros registrados no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Listar carros cadastrados a partir de determinado ano")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Lista de carros recuperada com êxito.",
                    response = Car.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carros registrados no serviço.",
                    response = Collections.class
            )
    })
    @GetMapping(value = "/from-this-year/{fromThisYear}")
    public List<Car> listFromThisYear(@PathVariable("fromThisYear") Integer fromThisYear, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.listFromThisYear, I=Obtendo carros registrados.");
            return carService.list(fromThisYear, null);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.v, E=Erro ao tentar obter carros registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carros registrados no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Listar carros cadastrados até determinado ano")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Lista de carros recuperada com êxito.",
                    response = Car.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carros registrados no serviço.",
                    response = Collections.class
            )
    })
    @GetMapping(value = "/to-this-year/{toThisYear}")
    public List<Car> listToThisYear(@PathVariable("toThisYear") Integer toThisYear, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.listToThisYear, I=Obtendo carros registrados.");
            return carService.list(null, toThisYear);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.listToThisYear, E=Erro ao tentar obter carros registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carros registrados no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Listar carros cadastrados entre determinado periodo de anos")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Lista de carros recuperada com êxito.",
                    response = Car.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carros registrados no serviço.",
                    response = Collections.class
            )
    })
    @GetMapping(value = "from-this-year/{fromThisYear}/to-this-year/{toThisYear}")
    public List<Car> listFromThisYearToThisYear(@PathVariable("fromThisYear") Integer fromThisYear, @PathVariable("toThisYear") Integer toThisYear, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.listFromThisYearToThisYear, I=Obtendo carros registrados.");
            return carService.list(fromThisYear, toThisYear);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.listFromThisYearToThisYear, E=Erro ao tentar obter carros registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carros registrados no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Obter carros por fabricante")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Carros recuperados com êxito.",
                    response = Car.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carros registrados no serviço.",
                    response = Collections.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_NOT_FOUND,
                    message = "Fabricante não encontrado",
                    response = Collections.class
            )
    })
    @GetMapping(value = "/by-car-maker/{carMakerName}")
    public List<Car> listByCarMaker(@PathVariable("carMakerName") String carMakerName, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.listByCarMaker, I=Obtendo carros registrados pelo fabricante com nome: {}" + carMakerName);

            CarMaker maker = carMakerService.findByName(carMakerName);

            if (maker == null) {
                log.error("M=listByCarMaker, E=Erro ao tentar recuperar carro: Fabricante não encontrado com o nome: {}", carMakerName);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fabricante de carros não encontrado.");
                return Collections.emptyList();
            }

            return carService.findByMaker(maker);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.findCarByName, E=Erro ao tentar obter carro registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carro registrado no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Obter carro por nome")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Carro recuperado com êxito.",
                    response = Car.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter carro registrado no serviço.",
                    response = Null.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_NOT_FOUND,
                    message = "Carro não encontrado.",
                    response = Null.class
            )
    })
    @GetMapping(value = "name/{name}")
    public Car findCarByName(@PathVariable(value = "name") String name, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.findCarByName, I=Obtendo carro registrado com nome: {}" + name);
            Car found = carService.findByName(name);
            if (found == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Carro não encontrado.");
            }
            return found;
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.findCarByName, E=Erro ao tentar obter carro registrados no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter carro registrado no serviço: " + sex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Cadastrar carro")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Carro cadastrado com êxito.",
                    response = Car.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar cadastrar carro.",
                    response = Null.class
            )
    })
    @PostMapping
    public Car register(@Valid @RequestBody CarVo car, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.register, I=Registrando carro: {}" + car);
            return carService.register(car);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.register, E=Erro ao tentar cadastrar carro: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar cadastrar carro: " + sex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Atualizar informações do carro")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Carro atualizado com êxito.",
                    response = Car.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar atualizar informações do carro.",
                    response = Null.class
            )
    })
    @PutMapping(value = "/{carId}")
    public Car update(@PathVariable(value = "carId") Long carId, @Valid @RequestBody CarVo car, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.update, I=Atualizando carro registrado com id: {}" + carId);
            return carService.updateInfo(carId, car);
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.update, E=Erro ao tentar atualizar informações do carro: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar atualizar informações do carro: " + sex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Remover carro")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Carro removido com exito.",
                    response = String.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar remover carro.",
                    response = String.class
            )
    })
    @DeleteMapping(value = "/{carId}")
    public String delete(@PathVariable(value = "carId") Long carId, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarRestApi.delete, I=Removendo carro registrado com id: {}" + carId);
            carService.delete(carId);
            return "Carro removido com exito.";
        } catch (ServiceException sex) {
            log.error("M=CarRestApi.delete, E=Erro ao tentar remover carro: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar remover carro: " + sex.getMessage());
            return sex.getMessage();
        }
    }

}
