package sample;

import java.util.ArrayList;
import java.util.Collections;

public class SJFNonPreemptive extends Schedulers {


    public SJFNonPreemptive(Scheduler scheduler) {
        super(scheduler);
    }

    void run() {
        ArrayList<Process> buffer = new ArrayList<>();
        Collections.sort(scheduler.processes);
        int totalTime = 0;
        for (int i = 0; i < scheduler.processes.size(); i++) {
            totalTime += scheduler.processes.get(i).getBurstTime();
        }
        scheduler.contextSwitchTime.add((float) 0);
        Collections.sort(scheduler.processes, new shortestJobComparator());
        while (scheduler.time < totalTime) {
            for (int i = 0; i < scheduler.processes.size(); i++) {
                if (!buffer.isEmpty()) {
                    for (int j = 0; j < buffer.size(); j++) {
                        if (buffer.get(j).getArrivalTime() > scheduler.time) {
                            continue;
                        }
                        scheduler.time += buffer.get(j).getBurstTime();
                        scheduler.contextSwitchTime.add(scheduler.time);
                        outProcesses.add(buffer.get(j));
                    }
                }
                if (scheduler.processes.get(i).getArrivalTime() > scheduler.time) {
                    buffer.add(scheduler.processes.get(i));
                    continue;
                }
                scheduler.time += scheduler.processes.get(i).getBurstTime();
                scheduler.contextSwitchTime.add(scheduler.time);
                outProcesses.add(scheduler.processes.get(i));
            }
        }
        System.out.println(scheduler.contextSwitchTime.toString());
    }
}
