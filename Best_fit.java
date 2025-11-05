import java.util.Scanner;

public class Best_fit{
    
    static int [] best_fit(int[] blocks,int []processes)
    {
          int[] allocation = new int[processes.length];
          for(int i=0;i<processes.length;i++)
          {
            allocation[i] = -1;
          }
          
          for(int i=0;i<processes.length;i++)
          {
            int bestindx = -1;
            for(int j=0;j<blocks.length;j++)
            {
                if(blocks[j] >= processes[i])
             {
                if(bestindx == -1 || blocks[j] < blocks[bestindx])
                {
                    bestindx = j;
                }
             }
            
            }

            if(bestindx != -1)
            {
                allocation[i] = bestindx;
                blocks[bestindx] -= processes[i];
            }
          }
          return allocation;
    }

    static void printallocation(int[] allocation, int[] process) {
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i + 1) + " of size " + process[i] +
                        " is allocated to Block " + (allocation[i] + 1));
            } else {
                System.out.println("Process " + (i + 1) + " of size " + process[i] +
                        " is not allocated");
            }
        }
    }
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of blocks:-");
        int blockcount = sc.nextInt();

        int []block = new int[blockcount];
        System.out.println("Enter the block sizes:-");
        for(int i=0;i<blockcount;i++)
        {
            block[i] = sc.nextInt();
        }

        System.out.println("Enter the number of process:-");
        int process_count = sc.nextInt();

        int []processes = new int[process_count];
        System.out.println("Enter the number of size of processes");
        for(int i=0;i<process_count;i++)
        {
            processes[i] = sc.nextInt();
        }

        int []allocation = best_fit(block,processes);
        printallocation(allocation, processes);
    }
}