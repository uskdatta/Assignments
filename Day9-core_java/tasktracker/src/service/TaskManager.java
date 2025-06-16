package service;

import exception.TaskNotFoundException;
import model.Employee;
import model.Task;
import model.TaskStatus;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {

    private final Map<Employee, List<Task>> assignments = new HashMap<>();

    public void assignTask(Employee emp, Task task) {
        assignments.computeIfAbsent(emp, e -> new ArrayList<>()).add(task);
    }

    public List<Task> getTasks(Employee emp) {
        return assignments.getOrDefault(emp, Collections.emptyList());
    }

    public void updateTaskStatus(Employee emp, int taskId, TaskStatus status) {
        Task task = getTasks(emp).stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task ID not found: " + taskId));
        task.setStatus(status);
    }

    public List<Task> allTasks() {
        return assignments.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Task> sortByPriority() {
        return allTasks().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Task> sortByDueDate() {
        return allTasks().stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public List<Task> searchByDescription(String keyword) {
        return allTasks().stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> tasksDueOn(java.time.LocalDate date) {
        return allTasks().stream()
                .filter(t -> t.getDueDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Employee> employeesWithPendingCountGreaterThan(int n) {
        return assignments.entrySet().stream()
                .filter(e -> e.getValue().stream().filter(t -> t.getStatus() == TaskStatus.PENDING).count() > n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
