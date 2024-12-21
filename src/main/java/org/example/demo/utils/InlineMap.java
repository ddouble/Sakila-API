package org.example.demo.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility class for creating inline maps which preserves the order of insertion.
 * NOTE: Map.of() in Java 9+ does not preserve the order of insertion.
 */
public class InlineMap {

    @SafeVarargs
    public static <K, V> Map<K, V> of(K key1, V value1, Object... otherPairs) {
        if (otherPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Other pairs must be in key-value format");
        }

        Map<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);

        for (int i = 0; i < otherPairs.length; i += 2) {
            @SuppressWarnings("unchecked")
            K key = (K) otherPairs[i];
            @SuppressWarnings("unchecked")
            V value = (V) otherPairs[i + 1];
            map.put(key, value);
        }

        return map;
    }
}

