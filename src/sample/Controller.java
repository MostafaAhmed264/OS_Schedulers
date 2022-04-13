package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private TextField arrivalTimeField;
    @FXML
    private TextField burstField;
    @FXML
    private Button addProcessBtn;
    @FXML
    private ListView<Process> list;

    public void addProcess(ActionEvent event)
    {
        try
        {
            Process process = new Process(Integer.parseInt(arrivalTimeField.getText()),Integer.parseInt(burstField.getText()));
            list.
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
