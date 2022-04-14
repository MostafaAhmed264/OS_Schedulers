package sample;

import java.util.Comparator;

public class shortestJobComparator implements Comparator<Process> {

    public int compare(Process o1, Process o2) {
        if (o1.getBurstTime() == o2.getBurstTime())
            if (o1.getID() > o2.getID())
                return 1;
            else
                return -1;
        if (o1.getBurstTime() > o2.getBurstTime())
            return 1;
        else
            return -1;
    }
}
