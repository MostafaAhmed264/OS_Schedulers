package sample;

public class Process {
    private final int arrivalTime;
    private int burstTime;
    private static int counter = 0;
    private final int ID;
    private int priority;

    public Process(int arrivalTime, int burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.ID = counter++;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public static int getCounter() {
        return counter;
    }

    public int getID() {
        return ID;
    }
}
