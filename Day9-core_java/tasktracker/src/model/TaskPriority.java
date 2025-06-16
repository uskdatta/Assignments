package model;

public enum TaskPriority {
    LOW(1), MEDIUM(2), HIGH(3);

    private final int weight;
    TaskPriority(int w) { this.weight = w; }

    public int getWeight() { return weight; }
}
