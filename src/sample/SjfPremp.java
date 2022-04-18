package sample;

import java.util.ArrayList;
import java.util.Collections;

public class SjfPremp {
    private Scheduler scheduler;
    public ArrayList<Process> SjfProcesses ;
    public SjfPremp(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public int searchMinRemainingTime(ArrayList<Process> processes){
        float min =Float.MAX_VALUE;
        int minIndex=-1;
        for (int i=0 ;i<processes.size();++i){
            if (processes.get(i).getRemainingTime()<min && processes.get(i).getRemainingTime()!=0){
                min =processes.get(i).getRemainingTime();
                minIndex = i;
            }

        }
        return minIndex;
    }
    public void run() {
        SjfProcesses = new ArrayList<>();
        Collections.sort(scheduler.processes);
        Process currentProcess;
        currentProcess = scheduler.processes.get(0);
        SjfProcesses.add(currentProcess);
        scheduler.contextSwitchTime.add((float) 0);
        int index;
        float remTime;
        for (int i =1 ; i<scheduler.processes.size();++i) {
            remTime= (currentProcess.getBurstTime()+currentProcess.getArrivalTime())-scheduler.processes.get(i).getArrivalTime();
            //currentProcess.setRemainingTime(remTime);

            if (scheduler.processes.get(i).getBurstTime() < currentProcess.getRemainingTime() && remTime >0) {
                currentProcess.setRemainingTime(remTime);
                currentProcess = scheduler.processes.get(i);
                SjfProcesses.add(currentProcess);
                scheduler.contextSwitchTime.add(currentProcess.getArrivalTime());
                scheduler.time = scheduler.processes.get(i).getArrivalTime();
            }
            else if (remTime<=0){
                index = searchMinRemainingTime(scheduler.processes);
                scheduler.time=scheduler.processes.get(index).getArrivalTime();
                scheduler.contextSwitchTime.add(currentProcess.getArrivalTime());
                currentProcess.setRemainingTime(0f);
                    currentProcess = scheduler.processes.get(index);
                    SjfProcesses.add(currentProcess);
            }



        }
        while (true){
            index =searchMinRemainingTime(scheduler.processes);
            if (index ==-1){
                break;
            }
            else {
                scheduler.time+=currentProcess.getRemainingTime();
                currentProcess.setRemainingTime(0f);
                currentProcess=scheduler.processes.get(index);
                SjfProcesses.add(currentProcess);

            }
        }
    }
}
