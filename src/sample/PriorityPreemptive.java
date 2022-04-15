package sample;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityPreemptive extends Schedulers{

    public PriorityPreemptive(Scheduler scheduler) {
        super(scheduler);
    }
    public int searchMinPriority(ArrayList<Process> processes, int end) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i <= end; ++i) {
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
        float remTime;
        for (int i = 1; i < scheduler.processes.size(); ++i) {
            remTime = currentProcess.getBurstTime() + currentProcess.newStart - scheduler.processes.get(i).newStart;
            if (scheduler.processes.get(i).getPriority() < currentProcess.getPriority() && remTime > 0) {
                currentProcess.setRemainingTime(remTime);
                scheduler.time += (currentProcess.getBurstTime() - currentProcess.getRemainingTime());
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess = scheduler.processes.get(i);
                outProcesses.add(currentProcess);
            } else if (remTime <= 0) {
                scheduler.time += currentProcess.getRemainingTime();
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess.setRemainingTime(0);
                if (remTime == 0)
                    index = searchMinPriority(scheduler.processes, i);
                else {
                    index = searchMinPriority(scheduler.processes, i - 1);
                    i--;
                }
                currentProcess = scheduler.processes.get(index);
                currentProcess.newStart = scheduler.time;
                outProcesses.add(currentProcess);
            }
        }
        scheduler.time += currentProcess.getRemainingTime();
        scheduler.contextSwitchTime.add(scheduler.time);
        currentProcess.setRemainingTime(0);
        while (true) {
            index = searchMinPriority(scheduler.processes, scheduler.processes.size() - 1);
            if (index == -1) {
                break;
            } else {
                currentProcess = scheduler.processes.get(index);
                outProcesses.add(currentProcess);
                scheduler.time += currentProcess.getRemainingTime();
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess.setRemainingTime(0);
            }
        }
    }
}
