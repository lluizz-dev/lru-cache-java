package main.java;

public interface Cache<K, V> {
    V get(K key);
    V put(K key, V value);
    boolean containsKey(K key);
    int size();
}
