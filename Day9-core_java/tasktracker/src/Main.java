import model.*;
import service.TaskManager;
import util.TaskMonitor;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Employee emp1 = new Employee("Alice", "Engineering");
        Employee emp2 = new Employee("Bob", "QA");

        manager.assignTask(emp1, new Task("Implement login", LocalDate.now().plusDays(2), TaskPriority.HIGH));
        manager.assignTask(emp1, new Task("Fix UI bug", LocalDate.now().plusDays(1), TaskPriority.MEDIUM));
        manager.assignTask(emp2, new Task("Write test cases", LocalDate.now().minusDays(1), TaskPriority.HIGH)); // overdue

        new TaskMonitor(manager).start();

        System.out.println("\nTasks by Priority ");
        manager.sortByPriority().forEach(System.out::println);

        System.out.println("\nTasks due tomorrow ");
        manager.tasksDueOn(LocalDate.now().plusDays(1)).forEach(System.out::println);

        System.out.println("\nEmployees with >3 Pending Tasks");
        manager.employeesWithPendingCountGreaterThan(3).forEach(System.out::println);
    }
}
