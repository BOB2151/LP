import java.util.Scanner;

public class Next_Fit{

    public static int[] next_fit(int[] blocks,int[] process)
    {
        int []alllocation = new int[process.length];
        for(int i=0;i<alllocation.length;i++)
        {
            alllocation[i] = -1;
        }

        int last_place_index = 0;
        for(int i=0;i<process.length;i++)
        {
            int count =0;
            
            int j = last_place_index;
            while(count<blocks.length)
            {
                if(blocks[j]>=process[i])
                {
                    alllocation[i] = j;
                    blocks[j] -= process[i];
                    last_place_index = j;
                    break;
                }
            

            j = (j+1)%blocks.length;
            count++;
            }
        }
        return alllocation;
    }
        static void printallocation(int [] allocation,int[] processes)
    {
        for(int i=0;i<allocation.length;i++)
        {
            if(allocation[i] != -1)
            {
                System.out.println("The process "+(i+1)+" of size "+processes[i]+" is allocated to the block "+(allocation[i]+1));
            }
            else
            {
                System.out.println("The process "+(i+1)+" is not allocated.");
            }
        }
    }
    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter the numbers of blocks:-");
       int block_count = sc.nextInt();

       System.out.println("Enter the number of sizes of block");
       int []blocks = new int[block_count];
       for(int i=0;i<block_count;i++)
       {
         blocks[i] = sc.nextInt();
       }

       System.out.println("Enter the number of processes");
       int processes_count = sc.nextInt();

       System.out.println("Enter the number of sizes of processes blocks");
       int[] processes = new int[processes_count];
       for(int j=0;j<processes_count;j++)
       {
        processes[j] = sc.nextInt();
       }

       int []allocation = next_fit(blocks,processes);
       printallocation(allocation, processes);
    }
}