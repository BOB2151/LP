import java.util.*;

public class RoundRobin {

    // Inner class to represent each process
    static class Process {
        int id, at, bt, rt, ct, tat, wt;

        Process(int id, int at, int bt) {
            this.id = id;  // Process ID
            this.at = at;  // Arrival Time
            this.bt = bt;  // Burst Time
            this.rt = bt;  // Remaining Time
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Input time quantum
        System.out.print("Enter the Time Quantum: ");
        int tq = sc.nextInt();

        // List to store process details
        List<Process> processes = new ArrayList<>();

        System.out.println("\nEnter Process ID, Arrival Time, and Burst Time:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(id, at, bt));
        }

        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.at));

        Queue<Process> queue = new LinkedList<>();  // Ready queue
        Set<Process> added = new HashSet<>();       // Track which processes are added to queue

        // Add first process (earliest arrival)
        queue.add(processes.get(0));
        added.add(processes.get(0));

        int time = 0;             // Current CPU time
        double totalTAT = 0;      // Total Turnaround Time
        double totalWT = 0;       // Total Waiting Time

        // Round Robin scheduling loop
        while (!queue.isEmpty()) {
            Process curr = queue.remove();

            // If CPU is idle, fast-forward to the process's arrival time
            if (time < curr.at)
                time = curr.at;

            // Execute for min(remaining_time, time_quantum)
            int execTime = Math.min(curr.rt, tq);
            curr.rt -= execTime;
            time += execTime;

            // Check for newly arrived processes and add them to queue
            for (Process p : processes) {
                if (p.at <= time && p.rt > 0 && !added.contains(p)) {
                    queue.add(p);
                    added.add(p);
                }
            }

            // If process not finished, re-add it to the queue
            if (curr.rt > 0) {
                queue.add(curr);
            } else {
                // Process completed
                curr.ct = time;              // Completion Time
                curr.tat = curr.ct - curr.at; // Turnaround Time
                curr.wt = curr.tat - curr.bt; // Waiting Time

                totalTAT += curr.tat;
                totalWT += curr.wt;
            }
        }

        // Print process table
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("----------------------------------------");

        for (Process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                    p.id, p.at, p.bt, p.ct, p.tat, p.wt);
        }

        // Print averages
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
    }
}
