package simulator;

import java.util.*;

public class Scheduler {
    private List<Process> processList = new ArrayList<>();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        System.out.print("Enter the Round Robin time quantum: ");
        int timeQuantum = scanner.nextInt();

        System.out.print("Enter the context switching time: ");
        int contextSwitchingTime = scanner.nextInt();

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Process Name: ");
            String name = scanner.next();

            System.out.print("Process Color: ");
            String color = scanner.next();

            System.out.print("Process Arrival Time: ");
            int arrivalTime = scanner.nextInt();

            System.out.print("Process Burst Time: ");
            int burstTime = scanner.nextInt();

            System.out.print("Process Priority: ");
            int priority = scanner.nextInt();

            processList.add(new Process(name, color, arrivalTime, burstTime, priority));
        }

        // Display scheduling options
        System.out.println("Select Scheduling Algorithm:");
        System.out.println("1. Non-preemptive Priority Scheduling");
        System.out.println("2. Non-preemptive Shortest Job First (SJF)");
        System.out.println("3. Shortest Remaining Time First (SRTF)");
        System.out.println("4. FCAI Scheduling");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> runPriorityScheduling();
            case 2 -> runSJFScheduling();
            case 3 -> runSRTFScheduling();
            case 4 -> runFCAIScheduling(timeQuantum, contextSwitchingTime);
            default -> System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private void runPriorityScheduling() {
        System.out.println("=== Non-preemptive Priority Scheduling ===");

        processList.sort(Comparator.comparingInt((Process p) -> p.arrivalTime)
                .thenComparingInt(p -> p.priority));

        int currentTime = 0;

        for (Process process : processList) {
            if (process.arrivalTime > currentTime) {
                currentTime = process.arrivalTime;
            }

            currentTime += process.burstTime;
            process.calculateMetrics(currentTime);
        }

        for (Process process : processList) {
            System.out.println(process);
        }

        double avgWaitingTime = processList.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        double avgTurnaroundTime = processList.stream().mapToInt(p -> p.turnaroundTime).average().orElse(0);

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }

    private void runSJFScheduling() {
        System.out.println("Non-preemptive Shortest Job First Scheduling is not yet implemented.");
    }

    private void runSRTFScheduling() {
        System.out.println("Shortest Remaining Time First Scheduling is not yet implemented.");
    }

    private void runFCAIScheduling(int timeQuantum, int contextSwitchingTime) {
        System.out.println("FCAI Scheduling is not yet implemented.");
    }
}
