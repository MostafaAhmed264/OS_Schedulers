package sample;

public class Process {
    private final float arrivalTime;
    private float burstTime;
    private static int counter = 0;
    private final int ID;
    private int priority;
    private float remainingTime;

    public Process(float arrivalTime, float burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.ID = counter++;
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
}
