

public class Scheduling {

    public static void main(String[] args) {

        FCFS fcfs=new FCFS();
        SJF sjf=new SJF();

        PriorityNonPreemptive pr=new PriorityNonPreemptive();

        RoundRobin rr=new RoundRobin();
        rr.execute();

    }

}
