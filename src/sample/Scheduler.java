package sample;

import java.util.ArrayList;

public class Scheduler {
    public ArrayList<Process> processes;
    private float time;
    private ArrayList<Float> contextSwitchTime;

    public Scheduler() {
        this.processes = new ArrayList<>();
        this.time = 0;
    }
    
}
