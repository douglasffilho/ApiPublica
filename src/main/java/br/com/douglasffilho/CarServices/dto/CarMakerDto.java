package br.com.douglasffilho.CarServices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CarMakerDto {

    @NotNull(message = "O nome do fabricante não pode ser branco ou nulo")
    private String name;

}
