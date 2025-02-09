package ru.turing.courses.lesson3;

import java.security.KeyStore;
import java.util.*;

//todo Задание со звездочкой - сделать Registry через generics.
// Для этого почитать, что такое дженерики и о том, как заставить класс с ними работать
// Сделать абстрактный доменный класс (похожий на Animal из прошлого урока для этого)
public class Registry<T, R> {
    private final Map<T, R> storage = new HashMap<>(); //todo поменять Object, Object на то, что будет у вашего класса в примере
    private final List<T> deletedKeys = new ArrayList<>();
    public void add(T key, R value) {
        storage.put(key, value);
    }
    public R removeByKey(T key) {
        R val = storage.get(key);
        storage.remove(key);
        return val;
    }

    public List<T> removeByValue (R value) {
        for (Map.Entry<T, R> entry : storage.entrySet()) {
            if (entry.getValue().equals(value)) {
                deletedKeys.add(entry.getKey());
            }
        }
        for (T key: deletedKeys) {
            storage.remove(key);
        }
        return deletedKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry<?, ?> registry = (Registry<?, ?>) o;
        return Objects.equals(storage, registry.storage) && Objects.equals(deletedKeys, registry.deletedKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage, deletedKeys);
    }

    public void clear() {storage.clear();}
    public R getByKey(T key) {
        return storage.get(key);
    }
    public void printAllItems() {
        List<T> keys = new ArrayList<>(storage.keySet());
        for (T key : keys) {
            System.out.println(storage.get(key));
        }
    }
}