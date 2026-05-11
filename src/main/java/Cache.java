package main.java;

public interface Cache<K, V> {
    V get(K key);
    void put(K key, V value);
    boolean containsKey(K key);
    int size();
}
