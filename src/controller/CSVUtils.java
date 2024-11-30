package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVUtils {
    public static void salvarEmCsv(String nomeArquivo, String cabecalho, Object[] array){
        try(FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.append(cabecalho).append("\n");
            Arrays.stream(array)
                    .filter(item -> item != null)
                    .forEach(item -> {
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
    public static ArrayList<String> lerCSV(String nomeArquivo){
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
