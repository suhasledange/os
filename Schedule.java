import java.util.*;

class Schedule
{
static Scanner sc=new Scanner(System.in);
	public static void main(String[] args)
	{
		
		int choice=0;
		do{
			System.out.println("\n*--*--*--*--*--*--*--*--*--*--*--*--*--*--*");
			System.out.println("Choose the Scheduling Algorithm: --->");
			System.out.println("1. Priority Based Scheduling");
			System.out.println("2. Round Robin");
			System.out.println("3. Shortest-Job-First Preemtive ");
			System.out.println("4. FCFS");		
			System.out.println("5. Exit");
			System.out.print("Enter Your Choice : ");
			 choice= sc.nextInt();			
			System.out.println("*--*--*--*--*--*--*--*--*--*--*--*--*--*--*");
			switch(choice)
			{
				case 1: priority();
					break;

				case 2: rr();
					break;

				case 3: sjfp();
					break;

				case 4: fcfs();
					break;

			}
		}while(choice!=5);
	}


	static void priority()
	{
		
            Scanner s = new Scanner(System.in);
            int x,n,p[],pp[],bt[],w[],t[],awt,atat,i;

                                                        //n is number of process
                                                        //p is process
                                                        //pp is process priority
                                                        //bt is process burst time
                                                        //w is wait time
                                                        // t is turnaround time
                                                        //awt is average waiting time
                                                        //atat is average turnaround time

            p = new int[10];
            pp = new int[10];
            bt = new int[10];
            w = new int[10];
            t = new int[10];

   System.out.print("Enter the number of process : ");
   n = s.nextInt();

   for(i=0;i<n;i++)
    {
       System.out.print("\nProcess["+(i+1)+"]:");
       System.out.print("\t Enter burst time : ");
       bt[i] = s.nextInt();
       System.out.print("\t time priorities :");
       pp[i] = s.nextInt();
       p[i]=i+1;
    }
 
//sorting on the basis of priority
  for(i=0;i<n-1;i++)
   {
     for(int j=i+1;j<n;j++)
     {
       if(pp[i]>pp[j])
       {
     x=pp[i];
     pp[i]=pp[j];
     pp[j]=x;
     x=bt[i];
     bt[i]=bt[j];
     bt[j]=x;
     x=p[i];
     p[i]=p[j];
     p[j]=x;
      }
   }
}
    w[0]=0;
    awt=0;
    t[0]=bt[0];
    atat=t[0];
    for(i=1;i<n;i++)
    {
        w[i]=t[i-1];
        awt+=w[i];
        t[i]=w[i]+bt[i];
        atat+=t[i];
    }
 
//Displaying the process
 
  System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
for(i=0;i<n;i++)
  System.out.print("\n   "+p[i]+"\t\t   "+bt[i]+"\t\t     "+w[i]+"\t\t     "+t[i]+"\t\t     "+pp[i]+"\n");
awt/=n;
atat/=n;
  System.out.print("\n Average Wait Time : "+awt);
  System.out.print("\n Average Turn Around Time : "+atat);
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void rr()
	{
         Scanner s = new Scanner(System.in);
 
          int wtime[],btime[],rtime[],num,quantum,total;
 
          wtime = new int[10];
          btime = new int[10];
          rtime = new int[10];
 
System.out.print("Enter number of processes(MAX 10): ");
num = s.nextInt();
System.out.print("Enter burst time");
for(int i=0;i<num;i++) { System.out.print("\nP["+(i+1)+"]: "); btime[i] = s.nextInt(); rtime[i] = btime[i]; wtime[i]=0; } System.out.print("\n\nEnter quantum: "); quantum = s.nextInt(); int rp = num; int i=0; int time=0; System.out.print("0"); wtime[0]=0; while(rp!=0) { if(rtime[i]>quantum)
 {
   rtime[i]=rtime[i]-quantum;
   System.out.print(" | P["+(i+1)+"] | ");
   time+=quantum;
   System.out.print(time);
   }
 else if(rtime[i]<=quantum && rtime[i]>0)
 {time+=rtime[i];
  rtime[i]=rtime[i]-rtime[i];
  System.out.print(" | P["+(i+1)+"] | ");
  rp--;
System.out.print(time);
 }


 
i++;
if(i==num)
{
i=0;
}
 
}
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	


	static void sjfp()
	{
Scanner sc=new Scanner(System.in);
System.out.println ("enter no of process:");
int n= sc.nextInt();
int pid[] = new int[n]; // it takes pid of process
int at[] = new int[n]; // at means arrival time
int bt[] = new int[n]; // bt means burst time
int ct[] = new int[n]; // ct means complete time
int ta[] = new int[n];// ta means turn around time
int wt[] = new int[n];  // wt means waiting time
int f[] = new int[n];  // f means it is flag it checks process is completed or not
int k[]= new int[n];   // it is also stores brust time
    int i, st=0, tot=0;
    float avgwt=0, avgta=0;
 
    for (i=0;i<n;i++)
    {
     pid[i]= i+1;
     System.out.println ("enter process " +(i+1)+ " arrival time:");
     at[i]= sc.nextInt();
     System.out.println("enter process " +(i+1)+ " burst time:");
     bt[i]= sc.nextInt();
     k[i]= bt[i];
     f[i]= 0;
    }
    
    while(true){
     int min=99,c=n;
     if (tot==n)
     break;
    
     for ( i=0;i<n;i++)
     {
     if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
     {	
     min=bt[i];
     c=i;
     }
     }
    
     if (c==n)
     st++;
     else
     {
     bt[c]--;
     st++;
     if (bt[c]==0)
     {
     ct[c]= st;
     f[c]=1;
     tot++;
     }
     }
    }
    
    for(i=0;i<n;i++)
    {
     ta[i] = ct[i] - at[i];
     wt[i] = ta[i] - k[i];
     avgwt+= wt[i];
     avgta+= ta[i];
    }
    
    System.out.println("pid  arrival  burst  complete turn waiting");
    for(i=0;i<n;i++)
    {
     System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
    }
    
    System.out.println("\naverage tat is "+ (float)(avgta/n));
    System.out.println("average wt is "+ (float)(avgwt/n));
  


	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void fcfs()
	{

		System.out.println("Enter the no. of processes :");
		int n= sc.nextInt();
		int[] burst=new int[n];
		int[] order=new int[n];
		int[] wait=new int[n];

		for(int i=1;i<=n;i++)
		{
			System.out.print("Enter Burst Time of Process P"+i+" :");
			burst[i-1]=sc.nextInt();

		}

		System.out.println("Enter Order of Arrival :");
		for(int i=0;i<n;i++)
		{
			order[i]=sc.nextInt();

		}
		
		int temp=0;
		int total=0;
		System.out.println("Execution Order:");
		System.out.println("Process \t Burst Time \t Waiting Time");
		for(int i=0;i<n;i++)
		{
			wait[i]=temp;
			System.out.println("P"+order[i]+"\t\t"+burst[order[i]-1]+"\t\t"+temp);
			temp += burst[order[i]-1];
			total += wait[i];
		}
        
		System.out.print("Average waiting Time : "+(total/n));

	}

}

