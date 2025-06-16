package repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) { items.add(item); }

    public void remove(Predicate<T> predicate) { items.removeIf(predicate); }

    public List<T> getAll() { return items; }

    public List<T> filter(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }
}
