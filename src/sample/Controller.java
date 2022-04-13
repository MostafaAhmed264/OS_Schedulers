package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public void addProcess(ActionEvent event)
    {
        try
        {
            Process process = new Process(Float.parseFloat(arrivalTimeField.getText()),Float.parseFloat(burstField.getText()));
            myText.appendText("\n" + process.getID() + "               " +  process.getArrivalTime());
            myText.appendText("                 " + process.getBurstTime());
            scheduler.processes.add(process);
            arrivalTimeField.clear();
            burstField.clear();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
