import java.util.*;  // for Scanner and ArrayList

public class FCFS {

    // 🔹 Step 1: Create a class to store process data together
    static class Process {
        int id;   // Process ID
        int at;   // Arrival Time
        int bt;   // Burst Time
        int ct;   // Completion Time
        int tat;  // Turnaround Time
        int wt;   // Waiting Time

        // 🔹 Constructor — runs automatically when we create a Process
        Process(int id, int at, int bt) {
            this.id = id;  // Assign process ID
            this.at = at;  // Assign arrival time
            this.bt = bt;  // Assign burst time
        }
    }

    // 🔹 Main function
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 2: Take number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Step 3: Create a list to hold all process objects
        List<Process> processes = new ArrayList<>();

        // Step 4: Take input for each process
        System.out.println("\nEnter Process ID, Arrival Time, and Burst Time:");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(id, at, bt)); // Create & store process
        }

        // Step 5: Sort processes by Arrival Time (FCFS rule)
        processes.sort(Comparator.comparingInt(p -> p.at));

        // Step 6: Initialize CPU time and averages
        int time = 0;
        double totalTAT = 0;
        double totalWT = 0;

        // Step 7: Calculate CT, TAT, and WT for each process
        for (Process p : processes) {

            // If CPU is idle (no process has arrived yet)
            if (time < p.at)
                time = p.at;

            time += p.bt;     // CPU runs process for its burst time
            p.ct = time;      // Completion time = when it finishes

            p.tat = p.ct - p.at;  // Turnaround time = CT - AT
            p.wt = p.tat - p.bt;  // Waiting time = TAT - BT

            totalTAT += p.tat;
            totalWT += p.wt;
        }

        // Step 8: Display result table
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("------------------------------------------------");
        for (Process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n",
                    p.id, p.at, p.bt, p.ct, p.tat, p.wt);
        }

        // Step 9: Show averages
        System.out.printf("\nAverage Turnaround Time: %.2f\n", totalTAT / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWT / n);
    }
}
