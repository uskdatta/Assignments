package model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable<Task> {
    private static final AtomicInteger SEQ = new AtomicInteger(1);

    private final int id;
    private final String description;
    private TaskStatus status;
    private final LocalDate dueDate;
    private final TaskPriority priority;

    public Task(String description, LocalDate dueDate, TaskPriority priority) {
        this.id = SEQ.getAndIncrement();
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
    public TaskPriority getPriority() { return priority; }

    public void setStatus(TaskStatus status) { this.status = status; }

    @Override
    public int compareTo(Task other) {
        int byPriority = Integer.compare(other.priority.getWeight(), this.priority.getWeight());
        return (byPriority != 0) ? byPriority : this.dueDate.compareTo(other.dueDate);
    }

    @Override
    public String toString() {
        return String.format("Task[%d | %s | %s | due: %s | %s]",
                id, priority, status, dueDate, description);
    }
}
