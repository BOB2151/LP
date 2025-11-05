import java.util.*;

public class FIFO
{
    public static int fifo(int []pages,int framesize)
    {
        Queue<Integer> frames = new LinkedList<>();
        int page_fault = 0;

        System.out.println("\npage\tFrames\t\tpage fault");
        for(int page:pages)
        {
            if(!frames.contains(page))
            {
               if(frames.size() == framesize)
               {
                  frames.poll();
               }
               frames.add(page);
               page_fault++;
               System.out.println(page + "\t" + frames + "\tYes");
            }
            else
            {
                System.out.println(page + "\t" + frames + "\tNo");
            }
        }
        return page_fault;
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
        int faults = fifo(pages, frameSize);
        System.out.println("\nTotal Page Faults = " + faults);
    }
}