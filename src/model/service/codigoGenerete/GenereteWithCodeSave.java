package model.service.codigoGenerete;

public class GenereteWithCodeSave implements CodigoGenerator {
    private final String saveCode;
    public GenereteWithCodeSave(String saveCode){
        this.saveCode = saveCode;
    }
    @Override
    public String gerarCodigo() {
        return this.saveCode;
    }
}
