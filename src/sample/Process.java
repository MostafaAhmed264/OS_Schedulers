package sample;

import static sample.Controller3.schedulers;

public class Process implements Comparable<Process> {
    private final float arrivalTime;
    private float burstTime;
    private static int counter = 0;
    private final int ID;
    private int priority;
    public float remainingTime;
    public float newStart;

    public Process(float arrivalTime, float burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.ID = counter++;
        this.newStart = arrivalTime;
        this.remainingTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public float getRemainingTime() {
        return remainingTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setRemainingTime(float remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public float getBurstTime() {
        return burstTime;
    }

    public static int getCounter() {
        return counter;
    }

    public int getID() {
        return ID;
    }

    public int compareTo(Process o) {
        if (this.getArrivalTime() == o.getArrivalTime()) {
            if (schedulers instanceof PriorityPreemptive || schedulers instanceof PriorityNonPreemptive) {
                if (this.getPriority() > o.getPriority())
                    return 1;
                else
                    return -1;
            } else {
                if (this.getID() > o.getID())
                    return 1;
                else
                    return -1;
            }
        }
        if (this.getArrivalTime() > o.getArrivalTime())
            return 1;
        else
            return -1;
    }


    @Override
    public String toString() {
        return String.valueOf(this.ID);
    }

}
