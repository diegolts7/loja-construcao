package model.service.lerDados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LerDadosCsv implements LerDados{
    @Override
    public ArrayList<String> ler(String nomeArquivo) {
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            ArrayList<String> valores = new ArrayList<>();
            br.readLine();
            while ((linha = br.readLine()) != null){
                valores.add(linha);
            }
            return valores;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
