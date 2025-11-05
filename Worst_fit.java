 import java.util.Scanner;

public class Worst_fit{
    
    static int[] worst_fit(int[] b,int [] p)
    {
        int []allocation = new int[p.length];
        for(int i=0;i<p.length;i++)
        {
            allocation[i] = -1;
        }

        for(int i=0;i<p.length;i++)
        {
            int worst_indx = -1;
            for(int j=0;j<b.length;j++)
            {
                if(b[j] >= p[i])
                {
                    if(worst_indx == -1 || b[j]> b[worst_indx])
                    {
                        worst_indx = j;
                    }
                }
            }

            if(worst_indx != -1)
            {
                allocation[i] = worst_indx;
                b[worst_indx] -= p[i];
            }
        }

        return allocation;
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
        System.out.println("Enter the number of blocks:-");
        int blockcount = sc.nextInt();
        System.out.println("Enter the sizes of the blocks:-");
        int []blocks = new int[blockcount];

        for(int i=0;i<blockcount;i++)
        {
            blocks[i] = sc.nextInt();
        }

        System.out.println("Enter the number of process");
        int process_count = sc.nextInt();

        System.out.println("Enter the size of the processes");
        int [] processes = new int[process_count];
        for(int i=0;i<process_count;i++)
        {
            processes[i] = sc.nextInt();
        }

        int []alllocation = worst_fit(blocks,processes);
        printallocation(alllocation,processes);

    }
}