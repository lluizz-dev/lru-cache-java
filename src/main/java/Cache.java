package main.java;

/**
 * Interface genérica que define as operações básicas de um cache.
 *
 * <p>Um cache é uma estrutura de armazenamento temporário que permite
 * acesso rápido a dados previamente buscados, evitando operações custosas
 * como consultas ao banco de dados.</p>
 *
 * @param <K> o tipo da chave usada para identificar os valores no cache
 * @param <V> o tipo do valor armazenado no cache
 */
public interface Cache<K, V> {

    /**
     * Retorna o valor associado à chave informada.
     *
     * @param key a chave cujo valor deve ser retornado
     * @return o valor associado à chave, ou {@code null} se não existir
     */
    V get(K key);

    /**
     * Insere ou atualiza um par chave-valor no cache.
     *
     * @param key   a chave que identifica o valor
     * @param value o valor a ser armazenado
     * @return o valor anterior associado à chave, ou {@code null} se não havia
     */
    V put(K key, V value);

    /**
     * Verifica se o cache contém um valor associado à chave informada.
     *
     * @param key a chave a ser verificada
     * @return {@code true} se a chave existir no cache, {@code false} caso contrário
     */
    boolean containsKey(K key);

    /**
     * Retorna a quantidade de elementos atualmente armazenados no cache.
     *
     * @return o número de entradas no cache
     */
    int size();
}