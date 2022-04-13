package sample;

import java.util.ArrayList;

public class Scheduler {
    public  ArrayList<Process> processes;
    public float time;
    public ArrayList<Float> contextSwitchTime;

    public Scheduler() {
        this.contextSwitchTime = new ArrayList<>();
        this.processes = new ArrayList<>();
        this.time = 0;
    }
    public void setTime(float time) {
        this.time = time;
    }

    public void setContextSwitchTime(ArrayList<Float> contextSwitchTime) {
        this.contextSwitchTime = contextSwitchTime;
    }

    public float getTime() {
        return time;
    }

    public ArrayList<Float> getContextSwitchTime() {
        return contextSwitchTime;
    }



}
