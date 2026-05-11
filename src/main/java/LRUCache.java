package main.java;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V>{

    private Map<K, V> cache;
    private int capacidade;

    public LRUCache(int capacidade) {
        this.capacidade = capacidade;
        this.cache = new LinkedHashMap<>(capacidade, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.capacidade;
            }
        };
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        return cache.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
