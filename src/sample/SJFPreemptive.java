package sample;

import java.util.Collections;

public class SJFPreemptive {
    private final Scheduler scheduler;

    public SJFPreemptive(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void Run() {
        Collections.sort(scheduler.processes, new shortestJobComparator());
        System.out.println(scheduler.processes);
    }
}
