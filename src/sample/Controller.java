package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Main.scheduler;

public class Controller {

    @FXML
    private TextField arrivalTimeField;
    @FXML
    private TextField burstField;
    @FXML
    private Button addProcessBtn;
    @FXML
    private TextArea myText;
    @FXML
    private Button genChartBtn;

    public void addProcess(ActionEvent event) {
        try {
            Process process = new Process(Float.parseFloat(arrivalTimeField.getText()), Float.parseFloat(burstField.getText()));
            myText.appendText("\n" + process.getID() + "               " + process.getArrivalTime());
            myText.appendText("                 " + process.getBurstTime());
            scheduler.processes.add(process);
            arrivalTimeField.clear();
            burstField.clear();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Run(ActionEvent event) {
        System.out.println("Hello");
        SJFPreemptive sjfPreemptive = new SJFPreemptive(scheduler);
        sjfPreemptive.Run();
    }
}
