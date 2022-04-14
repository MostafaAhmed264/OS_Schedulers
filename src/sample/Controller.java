package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Main.scheduler;

public class Controller {

    private Scene scene;
    private Stage stage;
    private Parent root;
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

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    }

    public void Action(ActionEvent event) {
        System.out.println("Hello");
        SJFPreemptive sjfPreemptive = new SJFPreemptive(scheduler);
        sjfPreemptive.run();
    }
}
