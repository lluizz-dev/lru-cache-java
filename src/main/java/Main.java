package main.java;

import java.util.Scanner;

/**
 * Ponto de entrada da aplicação de demonstração do LRU Cache.
 *
 * <p>Simula o comportamento de um sistema de cache de páginas web,
 * onde a primeira busca consulta o banco de dados (lenta) e as
 * seguintes retornam instantaneamente pelo cache.</p>
 *
 * <p>O cache é configurado com capacidade 2 para facilitar a visualização
 * do comportamento LRU: ao inserir um terceiro item, o menos recentemente
 * usado é removido automaticamente.</p>
 */
public class Main {

    /**
     * Inicializa o banco de dados simulado, o cache LRU e o menu interativo.
     *
     * @param args argumentos de linha de comando (não utilizados)
     * @throws InterruptedException se a thread for interrompida durante a simulação de latência
     */
    public static void main(String[] args) throws InterruptedException {
        FakeDataBase fakeDB = new FakeDataBase();
        Cache<String, String> cache = new LRUCache<>(2);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n== MENU ==");
            System.out.println("1 - Page 01");
            System.out.println("2 - Page 02");
            System.out.println("3 - Page 03");
            System.out.println("4 - Page 04");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("0")) break;

            String chave = switch (opcao) {
                case "1" -> "Page 01";
                case "2" -> "Page 02";
                case "3" -> "Page 03";
                case "4" -> "Page 04";
                default -> null;
            };

            if (chave != null) buscar(chave, cache, fakeDB);
        }
    }

    /**
     * Realiza a busca de uma página, priorizando o cache antes de consultar o banco.
     *
     * <p>Se a página estiver no cache (cache hit), retorna imediatamente.
     * Caso contrário (cache miss), consulta o banco de dados simulado,
     * armazena o resultado no cache e exibe o conteúdo.</p>
     *
     * @param chave  a identificação da página a ser buscada
     * @param cache  a instância do cache LRU
     * @param fakeDB a instância do banco de dados simulado
     * @throws InterruptedException se a thread for interrompida durante a consulta ao banco
     */
    static void buscar(String chave, Cache<String, String> cache, FakeDataBase fakeDB) throws InterruptedException {
        System.out.println("\nBuscando: " + chave);
        if (cache.containsKey(chave)) {
            System.out.println("[CACHE HIT] " + cache.get(chave));
        } else {
            String pagina = fakeDB.buscar(chave);
            cache.put(chave, pagina);
            System.out.println("[CACHE MISS] Salvo no cache: " + pagina);
        }
    }
}