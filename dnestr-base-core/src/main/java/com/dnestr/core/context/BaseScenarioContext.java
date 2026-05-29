package com.dnestr.core.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseScenarioContext<K> {

    private final Map<K, Object> context = new ConcurrentHashMap<>();

    public <V> void set(K key, V value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(K key) {
        return (V) context.get(key);
    }

    public boolean contains(K key) {
        return context.containsKey(key);
    }

    public void remove(K key) {
        context.remove(key);
    }

    public void clear() {
        context.clear();
    }
}
