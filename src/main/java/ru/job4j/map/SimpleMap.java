package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hashValue = hash(key.hashCode());
        int index = indexFor(hashValue);
        if (LOAD_FACTOR * capacity == count) {
            expand();
        }
        if (index >= capacity || (table[index] != null && !table[index].key.equals(key))) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] tempMap = table;
        capacity *= 2;
        count = 0;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> item : tempMap) {
            if (item != null) {
                put(item.key, item.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int hashValue = hash(key.hashCode());
        int index = indexFor(hashValue);
        return table[index] == null ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int hashValue = hash(key.hashCode());
        int index = indexFor(hashValue);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            modCount++;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int exceptedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                while (cursor < table.length - 1 && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length - 1 && exceptedModCount != 0;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
