package br.com.douglasffilho.CarServices.rest.api.v1;

import br.com.douglasffilho.CarServices.entities.CarMaker;
import br.com.douglasffilho.CarServices.rest.api.publicEndpoints.PublicCarMakerRestApiV1Endpoints;
import br.com.douglasffilho.CarServices.services.CarMakerService;
import br.com.douglasffilho.CarServices.vos.CarMakerVo;
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
@Api(value = "CarMakerRestApi", description = "API para consultar e modificar fabricantes de carros.")
@RequestMapping(PublicCarMakerRestApiV1Endpoints.PUBLIC_API_V1_CAR_MAKERS_ROOT_ENDPOINT)
public class CarMakerRestApi {

    @Autowired
    private CarMakerService carMakerService;

    @ApiOperation(value = "Listar fabricantes de carros cadastrados")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Lista de fabricantes recuperada com êxito.",
                    response = CarMaker.class,
                    responseContainer = "List"
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter fabricantes no serviço.",
                    response = Collections.class
            )
    })
    @GetMapping
    public List<CarMaker> list(HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarMakerRestApi.list, I=Obtendo fabricantes registrados.");
            return carMakerService.list();
        } catch (ServiceException sex) {
            log.error("M=CarMakerRestApi.list, E=Erro ao tentar obter fabricantes no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter fabricantes no serviço: " + sex.getMessage());
            return Collections.emptyList();
        }
    }

    @ApiOperation(value = "Obter fabricante de carros por nome")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Fabricante obtido com êxito",
                    response = CarMaker.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar obter fabricante no serviço.",
                    response = Null.class
            )
    })
    @GetMapping(value = "/{name}")
    public CarMaker findByName(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarMakerRestApi.findByName, I=Obtendo fabricante registrado, por nome: {}", name);
            return carMakerService.findByName(name);
        } catch (ServiceException sex) {
            log.error("M=CarMakerRestApi.findByName, E=Erro ao tentar obter fabricantes no serviço: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar obter fabricante no serviço: " + sex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Registrar novo fabricante")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Fabricante registrado com êxito.",
                    response = CarMaker.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar registrar novo fabricante de carros.",
                    response = Null.class
            )
    })
    @PostMapping()
    public CarMaker register(@Valid @RequestBody CarMakerVo carMaker, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarMakerRestApi.register, I=Tentando registrar fabricante: {}", carMaker);
            return carMakerService.register(carMaker);
        } catch (ServiceException sex) {
            log.error("M=CarMakerRestApi.register, E=Erro ao tentar registrar novo fabricante de carros: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar registrar novo fabricante de carros: " + sex.getMessage());

            return null;
        }
    }

    @ApiOperation(value = "Atualizar fabricante")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Fabricante atualizado com êxito.",
                    response = CarMaker.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar atualizar fabricante de carros.",
                    response = Null.class
            )
    })
    @PutMapping(value = "/{carMakerId}")
    public CarMaker update(@PathVariable("carMakerId") Long id, @Valid @RequestBody CarMakerVo carMaker, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarMakerRestApi.update, I=Tentando atualizar fabricante com id {}: {}", id, carMaker);
            return carMakerService.updateInfo(id, carMaker);
        } catch (ServiceException sex) {
            log.error("M=CarMakerRestApi.register, E=Erro ao tentar atualizar fabricante de carros: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar atualizar fabricante de carros: " + sex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Remover fabricante")
    @ApiResponses({
            @ApiResponse(
                    code = HttpServletResponse.SC_OK,
                    message = "Fabricante removido com êxito.",
                    response = String.class
            ),
            @ApiResponse(
                    code = HttpServletResponse.SC_BAD_REQUEST,
                    message = "Erro ao tentar remover fabricante de carros.",
                    response = String.class
            )
    })
    @DeleteMapping(value = "/{carMakerId}")
    public String delete(@PathVariable("carMakerId") Long id, HttpServletResponse response) throws IOException {
        try {
            log.info("M=CarMakerRestApi.delete, I=Tentando remover fabricante com id {}", id);
            carMakerService.delete(id);
            return "Fabricante removido com exito.";
        } catch (ServiceException sex) {
            log.error("M=CarMakerRestApi.delete, E=Erro ao tentar remover fabricante de carros: {}", sex.getMessage(), sex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao tentar remover fabricante de carros: " + sex.getMessage());
            return sex.getMessage();
        }
    }

}
