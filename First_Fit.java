import java.util.Scanner;

public class First_Fit
{
    static int[] first_fit(int []blocks,int []process)
    {
            int []allocation = new int[process.length];

            for(int i=0;i<allocation.length;i++)
            {
                allocation[i] = -1;
            }

            for(int i=0;i<process.length;i++)
            {
                for(int j=0;j<blocks.length;j++)
                {
                    if(blocks[j]>=process[i])
                    {
                        allocation[i] = j;
                        blocks[j] -= process[i];
                        break;
                    }
                }
            }

        return allocation;
    }

    static void printallocation(int []allocation,int []process)
    {
        for(int i=0;i<allocation.length;i++)
        {
            if(allocation[i] != -1)
            {
                System.out.println("process "+(i+1)+"of the size "+process[i]+ "is allocated to the block number "+(allocation[i]+1));
            }
            else
            {
                System.out.println("Process "+(i+1)+"of the size "+process[i]+"is not allocated");
            }
        }
    }
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of block");
        int blockcount = sc.nextInt();

        int []block = new int[blockcount];

        System.out.println("Enter the number of block sizes");
        for(int i=0;i<blockcount;i++)
        {
            block[i] = sc.nextInt();
        }

        System.out.print("Enter the number of process");
        int processcount = sc.nextInt();

        int []process = new int[processcount];
        System.out.println("Enter the number of process sizes");
        for(int i=0;i<processcount;i++)
        {
            process[i] = sc.nextInt();
        }

        System.out.println("First fit allocation");
        int []allocation = first_fit(block,process);
        printallocation(allocation, process);

    }
}