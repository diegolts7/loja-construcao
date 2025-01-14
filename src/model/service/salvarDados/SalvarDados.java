package model.service.salvarDados;

import java.util.Collection;

public interface SalvarDados {
    <T> void save(String nomeArquivo, String cabecalho, Collection<T> array);
}
