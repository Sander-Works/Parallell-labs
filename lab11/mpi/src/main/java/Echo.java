import java.util.Random;

import mpi.*;

public class Echo
{
	public static void main(String args[]) throws Exception 
	{
		int me,size;

       		args = MPI.Init(args);
       		me = MPI.COMM_WORLD.Rank();
	       	size = MPI.COMM_WORLD.Size();

		if(size!=2)
		{
			System.out.println("Run the Echo example with 2 processes!");
			MPI.Finalize();
			System.exit(1);
		}
		
		Random random = new Random(20210404);
		

		int data[]=new int[1];
		
		for(int i = 0; i <= 10; i++)
		{
			if(me==0)
			{
				data[0]=random.nextInt(100);				
				MPI.COMM_WORLD.Send(data,0,1,MPI.INT,1,10);
				System.out.println("Process "+me+" sends number "+data[0]+" to Process 1");
			}
			else
			{
				MPI.COMM_WORLD.Recv(data,0,1,MPI.INT,0,10);
				System.out.println("Process "+me+" receives number "+data[0]+" from Process 0");
			}
		}

		

		MPI.Finalize();
	}
}
