package main.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulação de um banco de dados com latência artificial.
 *
 * <p>Esta classe tem fins didáticos: representa um banco de dados lento
 * para demonstrar o ganho de desempenho obtido com o uso de cache.
 * Cada consulta aguarda 2 segundos antes de retornar o resultado,
 * simulando o tempo real de uma query em um banco de dados remoto.</p>
 */
public class FakeDataBase {

    /** Mapa interno que armazena as páginas HTML disponíveis. */
    private Map<String, String> dados = new HashMap<>();

    /**
     * Inicializa o banco de dados com páginas HTML pré-cadastradas.
     */
    public FakeDataBase() {
        this.dados.put("Page 01", "<html> Page 01 <html>");
        this.dados.put("Page 02", "<html> Page 02 <html>");
        this.dados.put("Page 03", "<html> Page 03 <html>");
        this.dados.put("Page 04", "<html> Page 04 <html>");
    }

    /**
     * Busca uma página HTML pela chave informada, simulando latência de rede.
     *
     * <p>Aguarda 2 segundos antes de retornar o resultado para simular
     * o tempo de resposta de um banco de dados real.</p>
     *
     * @param chave a identificação da página a ser buscada
     * @return o conteúdo HTML associado à chave, ou {@code null} se não encontrado
     * @throws InterruptedException se a thread for interrompida durante a espera
     */
    public String buscar(String chave) throws InterruptedException {
        System.out.println("Buscando...");
        Thread.sleep(2000);
        return dados.get(chave);
    }
}