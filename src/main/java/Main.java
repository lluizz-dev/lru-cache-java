package main.java;

import java.util.Scanner;

public class Main {
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