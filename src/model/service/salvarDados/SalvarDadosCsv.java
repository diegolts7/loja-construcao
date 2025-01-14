package model.service.salvarDados;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class SalvarDadosCsv implements SalvarDados{
    @Override
    public <T> void save(String nomeArquivo, String cabecalho, Collection<T> array) {
        try(FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.append(cabecalho).append("\n");
            array.forEach(item -> {
                try{
                    writer.append(item.toString()).append("\n");
                } catch (IOException e) {
                    throw new NullPointerException("Elemento do array é nulo!");
                }
            });
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
