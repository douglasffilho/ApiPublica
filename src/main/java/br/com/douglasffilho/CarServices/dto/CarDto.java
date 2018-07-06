package br.com.douglasffilho.CarServices.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CarDto {

    @ApiModelProperty(value = "Nome do Carro")
    @NotNull(message = "O nome do carro não pode ser branco ou nulo")
    private String name;

    @ApiModelProperty(value = "Fabricante do Carro")
    @NotNull(message = "O Fabricante do Carro não pode ser nulo ou inválido")
    private String maker;

    @ApiModelProperty(value = "Ano de Fabricacao do Carro")
    @NotNull(message = "O ano de fabricação do carro não pode ser nulo ou inválido")
    private Integer buildYear;

    @ApiModelProperty(value = "Imagem em formato base64")
    @Size(max = 15000000, message = "A imagem não pode ultrapassar 15Mb")
    private String image;

    @ApiModelProperty(value = "Descrição do Carro")
    @NotNull(message = "A descrição não pode ser nula ou deixada em branco")
    private String description;

}
