package sample;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Controller3.schedulers;
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
    @FXML
    private TextField priorityField;


    @FXML
    public void initialize() {
        if (schedulers instanceof PriorityNonPreemptive || schedulers instanceof PriorityPreemptive) {
            BooleanBinding booleanBinding = new BooleanBinding() {
                {
                    super.bind(arrivalTimeField.textProperty(),
                            burstField.textProperty(),
                            priorityField.textProperty());
                }

                protected boolean computeValue() {
                    return ((arrivalTimeField.getText().isEmpty()) || (burstField.getText().isEmpty() || (priorityField.getText().isEmpty())));
                }
            };
            addProcessBtn.disableProperty().bind(booleanBinding);
        } else {
            BooleanBinding booleanBinding = new BooleanBinding() {
                {
                    super.bind(arrivalTimeField.textProperty(),
                            burstField.textProperty());
                }

                protected boolean computeValue() {
                    return ((arrivalTimeField.getText().isEmpty()) || (burstField.getText().isEmpty()));
                }
            };
            addProcessBtn.disableProperty().bind(booleanBinding);
        }
    }

    public void addProcess(ActionEvent event) {
        try {
            Process process = new Process(Float.parseFloat(arrivalTimeField.getText()), Float.parseFloat(burstField.getText()));
            if (schedulers instanceof PriorityPreemptive || schedulers instanceof PriorityNonPreemptive) {
                process.setPriority(Integer.parseInt(priorityField.getText()));
                priorityField.clear();
            }
            myText.appendText("\n" + process.getID() + "               " + process.getArrivalTime());
            myText.appendText("                 " + process.getBurstTime());
            scheduler.processes.add(process);
            genChartBtn.setDisable(false);
            arrivalTimeField.clear();
            burstField.clear();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void Action(ActionEvent event) throws IOException {
        schedulers.run();
        switchToScene2(event);
    }

}
