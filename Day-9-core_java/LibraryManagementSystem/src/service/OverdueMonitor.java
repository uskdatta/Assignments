package service;

public class OverdueMonitor extends Thread {
    private final LibraryService library;

    public OverdueMonitor(LibraryService library) {
        this.library = library;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Checking for overdue books...");
            library.getOverdueRecords().forEach(record ->
                    System.out.println("OVERDUE: " + record));
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
