package main.java;

import java.util.HashMap;
import java.util.Map;

public class FakeDataBase {
    private Map<String, String> dados = new HashMap<>();

    public FakeDataBase() {
        this.dados.put("Page 01", "<html> Page 01 <html>");
        this.dados.put("Page 02", "<html> Page 02 <html>");
        this.dados.put("Page 03", "<html> Page 03 <html>");
        this.dados.put("Page 04", "<html> Page 04 <html>");
    }

    public String buscar(String chave) throws InterruptedException {
        System.out.println("Buscando...");
        Thread.sleep(2000);
        return dados.get(chave);
    }
}
