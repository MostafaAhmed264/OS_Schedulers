package sample;

import java.util.Collections;

public class FCFS extends Schedulers {


    public FCFS(Scheduler scheduler) {
        super(scheduler);
    }

    public void run() {
        scheduler.contextSwitchTime.add((float) 0);
        Collections.sort(scheduler.processes);
        outProcesses.addAll(scheduler.processes);
        for (int i = 0; i < scheduler.processes.size(); i++) {
            if (scheduler.time < scheduler.processes.get(i).getArrivalTime()) {
                scheduler.time = scheduler.processes.get(i).getArrivalTime();
                scheduler.contextSwitchTime.add(scheduler.processes.get(i).getArrivalTime());
            }
            scheduler.time += scheduler.processes.get(i).getBurstTime();
            scheduler.contextSwitchTime.add(scheduler.time);
        }
        System.out.println(scheduler.contextSwitchTime.toString());
    }
}
