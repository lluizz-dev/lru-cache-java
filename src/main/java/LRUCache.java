package main.java;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementação de um cache LRU (Least Recently Used) com capacidade limitada.
 *
 * <p>O LRU Cache armazena os itens acessados mais recentemente e descarta
 * automaticamente o item menos utilizado quando a capacidade máxima é atingida.
 * Essa estratégia é amplamente utilizada em navegadores, servidores web e
 * sistemas de cache como o Redis.</p>
 *
 * <p>A implementação utiliza um {@link LinkedHashMap} configurado com
 * {@code accessOrder = true}, o que faz com que os elementos sejam reordenados
 * a cada acesso — mantendo o menos recentemente usado sempre no início da estrutura,
 * pronto para ser removido quando necessário.</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 * Cache<String, String> cache = new LRUCache<>(3);
 * cache.put("pagina_home", "<html>Home</html>");
 * cache.get("pagina_home"); // cache hit
 * }</pre>
 *
 * @param <K> o tipo da chave
 * @param <V> o tipo do valor
 * @see Cache
 * @see java.util.LinkedHashMap
 */
public class LRUCache<K, V> implements Cache<K, V> {

    /** Estrutura interna que mantém os dados ordenados por acesso recente. */
    private Map<K, V> cache;

    /** Número máximo de entradas permitidas no cache. */
    private int capacidade;

    /**
     * Cria um novo LRUCache com a capacidade máxima informada.
     *
     * <p>Internamente, utiliza um {@link LinkedHashMap} com {@code accessOrder = true}
     * e sobrescreve {@code removeEldestEntry} para remover automaticamente
     * o item menos recentemente usado quando a capacidade é excedida.</p>
     *
     * @param capacidade o número máximo de entradas que o cache pode armazenar
     */
    public LRUCache(int capacidade) {
        this.capacidade = capacidade;
        this.cache = new LinkedHashMap<>(capacidade, 0.75F, true) {
            /**
             * Remove automaticamente o elemento mais antigo quando o cache excede a capacidade.
             *
             * <p>Este método é chamado pelo {@link LinkedHashMap} após cada {@code put}.
             * Retorna {@code true} quando o tamanho atual supera a capacidade definida,
             * sinalizando que o elemento menos recentemente usado deve ser removido.</p>
             *
             * @param eldest o elemento mais antigo (menos recentemente usado)
             * @return {@code true} se o elemento mais antigo deve ser removido
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.capacidade;
            }
        };
    }

    /**
     * Retorna o valor associado à chave e marca o item como recentemente acessado.
     *
     * @param key a chave a ser buscada
     * @return o valor associado, ou {@code null} se não existir
     */
    @Override
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * Insere ou atualiza um par chave-valor no cache.
     *
     * <p>Se a inserção fizer o cache exceder a capacidade, o item menos
     * recentemente usado é removido automaticamente.</p>
     *
     * @param key   a chave que identifica o valor
     * @param value o valor a ser armazenado
     * @return o valor anterior associado à chave, ou {@code null} se não havia
     */
    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }

    /**
     * Verifica se a chave informada está presente no cache.
     *
     * @param key a chave a ser verificada
     * @return {@code true} se a chave existir, {@code false} caso contrário
     */
    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    /**
     * Retorna a quantidade de itens atualmente armazenados no cache.
     *
     * @return o número de entradas no cache
     */
    @Override
    public int size() {
        return cache.size();
    }
}