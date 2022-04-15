package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static sample.Main.scheduler;

public class Controller2 {
    @FXML
    private Canvas myCanvas;


    public void draw() {
        int width = 0;
        int start = 70;
        int lower = 300;
        int upper = 250;
        for (int i = 0; i < scheduler.processes.size(); ++i) {
            width += scheduler.processes.get(i).getBurstTime();
        }
        double scale = ((myCanvas.getWidth() - 100) / (width));
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        gc.strokeLine(start, lower,
                start, upper);
        gc.strokeLine((start + width * scale), lower,
                (start + width * scale), upper);
        gc.strokeLine(start, lower,
                (start + width * scale), lower);
        gc.strokeLine(start, upper,
                (start + width * scale), upper);
        for (int i = 0; i < scheduler.contextSwitchTime.size(); ++i) {
            System.out.println(start + scheduler.contextSwitchTime.get(i) * scale);
            gc.strokeText(String.valueOf(scheduler.contextSwitchTime.get(i)), (start + scheduler.contextSwitchTime.get(i) * scale), lower + 20);
        }
        for (int i = 0; i < scheduler.contextSwitchTime.size(); ++i) {
            gc.strokeLine((start + scheduler.contextSwitchTime.get(i) * scale), lower,
                    (start + scheduler.contextSwitchTime.get(i) * scale), upper);
        }
        for (int i = 0; i < Controller.schedulers.outProcesses.size(); ++i) {
            gc.strokeText("P" + Controller.schedulers.outProcesses.get(i),
                    (((start + scheduler.contextSwitchTime.get(i) * scale) + (start + scheduler.contextSwitchTime.get(i + 1) * scale)) / 2.0) - 4,
                    lower - 20);
        }

    }

}
