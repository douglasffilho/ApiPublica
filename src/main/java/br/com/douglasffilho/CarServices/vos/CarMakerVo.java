package br.com.douglasffilho.CarServices.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CarMakerVo {

    @ApiModelProperty(value = "Nome do Fabricante")
    @NotEmpty(message = "O nome do fabricante não pode ser branco ou nulo")
    private String name;

    @ApiModelProperty(value = "Imagem em formato base64")
    @Size(max = 15000000, message = "A imagem não pode ultrapassar 15Mb")
    private String image;

}
