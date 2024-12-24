package simulator;

public class Process {
    String name;
    String color;
    int arrivalTime;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int remainingTime;

    public Process(String name, String color, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.remainingTime = burstTime;
    }

    @Override
    public String toString() {
        return String.format("Process{name='%s', color='%s', arrivalTime=%d, burstTime=%d, priority=%d, waitingTime=%d, turnaroundTime=%d}",
                name, color, arrivalTime, burstTime, priority, waitingTime, turnaroundTime);
    }

    public void calculateMetrics(int currentTime) {
        waitingTime = currentTime - arrivalTime - burstTime;
        turnaroundTime = currentTime - arrivalTime;
    }
}
