package service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TaskRepository<T> {
    private final List<T> taskList = new ArrayList<>();

    public void add(T task) {
        taskList.add(task);
    }

    public boolean remove(Predicate<T> predicate) {
        return taskList.removeIf(predicate);
    }

    public T find(Predicate<T> predicate) {
        return taskList.stream().filter(predicate).findFirst().orElse(null);
    }

    public List<T> getAll() {
        return new ArrayList<>(taskList);
    }
}
