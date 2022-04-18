package sample;

import java.util.ArrayList;

public abstract class Schedulers {
    protected Scheduler scheduler;
    public ArrayList<Process> outProcesses;

    public Schedulers(Scheduler scheduler) {
        this.scheduler = scheduler;
        outProcesses = new ArrayList<>();
    }

    abstract void run();
}
