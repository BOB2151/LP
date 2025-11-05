import java.util.*;

public class LRUPageReplacement {

    public static int lru(int[] pages, int frameSize) {
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("\nPage\tFrames\t\tPage Fault");

        for (int page : pages) {
            boolean pageFault = false; // to track if a fault happened

            if (!frames.contains(page)) {
                // if page not present → page fault
                pageFault = true;
                if (frames.size() == frameSize) {
                    frames.removeFirst(); // remove least recently used page
                }
                pageFaults++;
            } else {
                // if page already in frame, remove it and re-add at end (most recently used)
                frames.remove((Integer) page);
            }

            frames.addLast(page); // add as most recently used
            System.out.println(page + "\t" + frames + "\t\t" + (pageFault ? "Yes" : "No"));
        }

        return pageFaults;
    }

       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter the number of frames: ");
        int frameSize = sc.nextInt();

        System.out.println("\n--- FIFO Page Replacement ---");
        int faults = lru(pages, frameSize);
        System.out.println("\nTotal Page Faults = " + faults);
    }
}
