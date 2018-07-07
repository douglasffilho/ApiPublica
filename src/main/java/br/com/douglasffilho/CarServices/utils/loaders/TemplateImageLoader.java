package br.com.douglasffilho.CarServices.utils.loaders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class TemplateImageLoader {

    public String getImage(String type, String model) {
        log.error("M=TemplateImageLoader.getImage, I=Obtendo imagem template em: default_{}_images/{}", type, model);

        StringBuilder imageContent = new StringBuilder();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("default_" + type + "_images/" + model)));
            String line = br.readLine();

            while (line != null) {
                imageContent.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            log.error("M=TemplateImageLoader.getImage, E=Erro ao tentar carregar imagem: {}", e.getMessage(), e);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                log.error("M=TemplateImageLoader.getImage, E=Erro ao tentar fechar arquivo de imagem: {}", e.getMessage(), e);
            }
        }

        return imageContent.toString();
    }

}
