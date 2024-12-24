package simulator;

import java.util.*;

public class PriorityScheduler {
    private final List<Process> processList;
    private final int contextSwitchingTime;

    public PriorityScheduler(List<Process> processList, int contextSwitchingTime) {
        this.processList = processList;
        this.contextSwitchingTime = contextSwitchingTime;
    }

    public void execute() {
        List<Process> readyQueue = new ArrayList<>(processList);
        readyQueue.sort(Comparator.comparingInt((Process p) -> p.priority)
                .thenComparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        System.out.println("=== Non-preemptive Priority Scheduling ===");

        for (Process process : readyQueue) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }

            process.waitingTime = currentTime - process.arrivalTime;
            currentTime += process.burstTime; // Process execution
            process.turnaroundTime = currentTime - process.arrivalTime;

            if (!process.equals(readyQueue.get(readyQueue.size() - 1))) {
                currentTime += contextSwitchingTime;
            }

            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;

            System.out.println(process);
        }

        System.out.printf("Average Waiting Time: %.2f\n", (double) totalWaitingTime / processList.size());
        System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTurnaroundTime / processList.size());
    }
}
