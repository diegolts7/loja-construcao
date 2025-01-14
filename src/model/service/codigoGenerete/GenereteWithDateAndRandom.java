package model.service.codigoGenerete;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenereteWithDateAndRandom implements CodigoGenerator {
    @Override
    public String gerarCodigo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyHHmmssSSS") ;
        return  LocalDateTime.now().format(formatter) + new Random().nextInt(100);
    }
}
