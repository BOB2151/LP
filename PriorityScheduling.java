import java.util.*;

public class PriorityScheduling {

    // Inner class to represent each process
    static class Process {
        int id, at, bt, pt, ct, tat, wt;
        boolean taken = false;

        Process(int id, int at, int bt, int pt) {
            this.id = id;  // Process ID
            this.at = at;  // Arrival Time
            this.bt = bt;  // Burst Time
            this.pt = pt;  // Priority (smaller value = higher priority)
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // List to store processes
        List<Process> processes = new ArrayList<>();

        System.out.println("\nEnter the Process ID, Arrival Time, Burst Time, and Priority:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int pt = sc.nextInt();
            processes.add(new Process(id, at, bt, pt));
        }

        // Sort processes by arrival time first
        processes.sort(Comparator.comparingInt(p -> p.at));

        int time = 0, completed = 0;
        double totalWT = 0, totalTAT = 0;

        // Main scheduling loop
        while (completed != n) {
            Process curr = null;
            int minPT = Integer.MAX_VALUE;

            // Find process with the highest priority (lowest number)
            for (Process p : processes) {
                if (p.at <= time && !p.taken && p.pt < minPT) {
                    curr = p;
                    minPT = p.pt;
                }
            }

            // If no process has arrived yet, increment time
            if (curr == null) {
                time++;
                continue;
            }

            // Execute the selected process
            time += curr.bt;
            curr.ct = time;
            curr.taken = true;
            completed++;

            // Calculate turnaround and waiting time
            curr.tat = curr.ct - curr.at;
            curr.wt = curr.tat - curr.bt;

            totalTAT += curr.tat;
            totalWT += curr.wt;
        }

        // Display results
        System.out.println("\nPID\tAT\tBT\tPR\tCT\tTAT\tWT");
        System.out.println("------------------------------------------------------");
        for (Process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\n",
                    p.id, p.at, p.bt, p.pt, p.ct, p.tat, p.wt);
        }

        // Display averages
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
    }
}
