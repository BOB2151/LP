import java.util.*;

public class SJFPreemptive {

    // Inner class to store process details
    static class Process {
        int id, at, bt, rt, ct, tat, wt;

        Process(int id, int at, int bt) {
            this.id = id;   // Process ID
            this.at = at;   // Arrival Time
            this.bt = bt;   // Burst Time
            this.rt = bt;   // Remaining Time (initially = Burst Time)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // List to store process details
        List<Process> processes = new ArrayList<>();

        System.out.println("\nEnter Process ID, Arrival Time, and Burst Time:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(id, at, bt));
        }

        // Sort processes by Arrival Time
        processes.sort(Comparator.comparingInt(p -> p.at));

        int time = 0;                // Current CPU time
        int completed = 0;           // Count of completed processes
        double totalWT = 0, totalTAT = 0; // Totals for averages

        // Scheduling loop: runs until all processes are completed
        while (completed != n) {
            Process curr = null;
            int minTime = Integer.MAX_VALUE;

            // Select the process with minimum remaining time that has arrived
            for (Process p : processes) {
                if (p.at <= time && p.rt > 0 && p.rt < minTime) {
                    curr = p;
                    minTime = p.rt;
                }
            }

            // If no process is ready, CPU stays idle for 1 unit
            if (curr == null) {
                time++;
                continue;
            }

            // Execute process for 1 time unit (Preemptive)
            curr.rt--;
            time++;

            // If process finishes, record stats
            if (curr.rt == 0) {
                completed++;
                curr.ct = time;                   // Completion Time
                curr.tat = curr.ct - curr.at;     // Turnaround Time
                curr.wt = curr.tat - curr.bt;     // Waiting Time

                totalTAT += curr.tat;
                totalWT += curr.wt;
            }
        }

        // Print results
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("------------------------------------------------");

        for (Process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                    p.id, p.at, p.bt, p.ct, p.tat, p.wt);
        }

        // Print averages
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
    }
}
