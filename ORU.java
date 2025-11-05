import java.util.*;

public class ORU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of pages
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        // Input the reference string
        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        // Input frame size
        System.out.print("Enter frame size: ");
        int frameSize = sc.nextInt();

        // Create a list for frames (representing memory)
        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\n--- Optimal Page Replacement Execution ---");

        // Process each page reference one by one
        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If the page is not already in frames → Page Fault occurs
            if (!frames.contains(page)) {
                if (frames.size() < frameSize) {
                    // Space available, just add it
                    frames.add(page);
                } else {
                    // Need to replace one page using Optimal logic
                    int indexToReplace = -1;
                    int farthest = i + 1;

                    // Check which page in frames will not be used for the longest time
                    for (int j = 0; j < frames.size(); j++) {
                        int framePage = frames.get(j);
                        int k;

                        // Look ahead in future references
                        for (k = i + 1; k < n; k++) {
                            if (pages[k] == framePage)
                                break;
                        }

                        // If page not found again → Replace it immediately
                        if (k == n) {
                            indexToReplace = j;
                            break;
                        }

                        // Track the page that will be used farthest in future
                        if (k > farthest) {
                            farthest = k;
                            indexToReplace = j;
                        }
                    }

                    // If all are used again, replace the first one by default
                    if (indexToReplace == -1)
                        indexToReplace = 0;

                    // Replace the chosen page
                    frames.set(indexToReplace, page);
                }

                pageFaults++;
                System.out.println("Page " + page + " → Fault | Frames: " + frames);
            } else {
                System.out.println("Page " + page + " → No Fault | Frames: " + frames);
            }
        }

        // Final output
        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
