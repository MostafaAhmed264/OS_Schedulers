package sample;

import java.util.Collections;

public class FCFS {
    private Scheduler scheduler;

    public FCFS(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void Run()
    {
        scheduler.contextSwitchTime.add((float)0);
        Collections.sort(scheduler.processes);
        for(int i = 0; i < scheduler.processes.size(); i++)
        {
            if(scheduler.time < scheduler.processes.get(i).getArrivalTime())
            {
                scheduler.time = scheduler.processes.get(i).getArrivalTime();
                scheduler.contextSwitchTime.add(scheduler.processes.get(i).getArrivalTime());
            }
            scheduler.time += scheduler.processes.get(i).getBurstTime();
            scheduler.contextSwitchTime.add(scheduler.time);
        }
        System.out.println(scheduler.contextSwitchTime.toString());
    }
}
