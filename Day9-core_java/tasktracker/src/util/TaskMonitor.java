package util;

import model.Task;
import model.TaskStatus;
import service.TaskManager;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMonitor extends Thread {
    private final TaskManager manager;

    public TaskMonitor(TaskManager manager) {
        this.manager = manager;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            List<Task> overdue = manager.allTasks().stream()
                    .filter(t -> t.getStatus() != TaskStatus.COMPLETED &&
                            t.getDueDate().isBefore(LocalDate.now()))
                    .collect(Collectors.toList());

            if (!overdue.isEmpty()) {
                System.out.println("\n⚠️ Overdue Tasks (" + LocalDate.now() + "):");
                overdue.forEach(System.out::println);
            }

            try {
                Thread.sleep(60000); // 1 minute
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
