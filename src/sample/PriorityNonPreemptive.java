package sample;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityNonPreemptive extends Schedulers {
    public PriorityNonPreemptive(Scheduler scheduler) {
        super(scheduler);
    }
    public int searchMinPriority(ArrayList<Process> processes, float time) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i <scheduler.processes.size(); ++i) {
            if(processes.get(i).getArrivalTime()>time){
                break;
            }
            if (processes.get(i).getPriority() < min && processes.get(i).getRemainingTime() != 0) {
                min = processes.get(i).getPriority();
                minIndex = i;
            }

        }
        return minIndex;
    }

    @Override
    void run() {
        outProcesses = new ArrayList<>();
        Collections.sort(scheduler.processes); // In sorting we need to give higher priority according to priority number instead of process id when all have same arrival time
        Process currentProcess;
        currentProcess = scheduler.processes.get(0);
        outProcesses.add(currentProcess);
        scheduler.contextSwitchTime.add((float) 0);
        int index;
        scheduler.time += currentProcess.getBurstTime();
        scheduler.contextSwitchTime.add(scheduler.time);
        currentProcess.setRemainingTime(0f);
       while (true){
           index = searchMinPriority(scheduler.processes, scheduler.time);
           if (index ==-1){
               break;
           }
            currentProcess=scheduler.processes.get(index);
           outProcesses.add(currentProcess);
           scheduler.time += currentProcess.getBurstTime();
           scheduler.contextSwitchTime.add(scheduler.time);
           currentProcess.setRemainingTime(0f);
        }

    }
}
