package sample;

import java.util.ArrayList;
import java.util.Collections;

public class SJFPreemptive extends Schedulers {

    public SJFPreemptive(Scheduler scheduler) {
        super(scheduler);
    }


    public int searchMinRemainingTime(ArrayList<Process> processes, int end) {
        float min = Float.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i <= end; ++i) {
            if (processes.get(i).getRemainingTime() < min && processes.get(i).getRemainingTime() != 0f) {
                min = processes.get(i).getRemainingTime();
                minIndex = i;
            }

        }
        return minIndex;
    }

    public void run() {
        outProcesses = new ArrayList<>();
        Collections.sort(scheduler.processes);
        Process currentProcess;
        currentProcess = scheduler.processes.get(0);
        outProcesses.add(currentProcess);
        scheduler.contextSwitchTime.add((float) 0);
        int index;
        float remTime;
        for (int i = 1; i < scheduler.processes.size(); ++i) {
            remTime = currentProcess.getBurstTime() + currentProcess.newStart - scheduler.processes.get(i).newStart;
            if (scheduler.processes.get(i).getBurstTime() < remTime && remTime > 0) {
                currentProcess.setRemainingTime(remTime);
                scheduler.time += (currentProcess.getBurstTime() - currentProcess.getRemainingTime());
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess = scheduler.processes.get(i);
                outProcesses.add(currentProcess);
            } else if (remTime <= 0) {
                scheduler.time += currentProcess.getRemainingTime();
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess.setRemainingTime(0f);
                if (remTime == 0)
                    index = searchMinRemainingTime(scheduler.processes, i);
                else {
                    index = searchMinRemainingTime(scheduler.processes, i - 1);
                    i--;
                }
                currentProcess = scheduler.processes.get(index);
                currentProcess.newStart = scheduler.time;
                outProcesses.add(currentProcess);
            }
        }
        scheduler.time += currentProcess.getRemainingTime();
        scheduler.contextSwitchTime.add(scheduler.time);
        currentProcess.setRemainingTime(0f);
        while (true) {
            index = searchMinRemainingTime(scheduler.processes, scheduler.processes.size() - 1);
            if (index == -1) {
                break;
            } else {
                currentProcess = scheduler.processes.get(index);
                outProcesses.add(currentProcess);
                scheduler.time += currentProcess.getRemainingTime();
                scheduler.contextSwitchTime.add(scheduler.time);
                currentProcess.setRemainingTime(0f);
            }
        }
    }
}
