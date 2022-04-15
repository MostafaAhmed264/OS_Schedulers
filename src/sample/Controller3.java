package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Main.scheduler;

public class Controller3 {
    public static Schedulers schedulers;

    public void createFCFS(ActionEvent event) throws IOException {
        schedulers = new FCFS(scheduler);
        switchToSample(event);
    }

    public void createSJFPreemptive(ActionEvent event) throws IOException {
        schedulers = new SJFPreemptive(scheduler);
        switchToSample(event);
    }

    public void createSJFNonPreemptive(ActionEvent event) throws IOException {
        schedulers = new SJFNonPreemptive(scheduler);
        switchToSample(event);
    }

    public void createPriorityPreemptive(ActionEvent event) throws IOException {
        schedulers = new PriorityPreemptive(scheduler);
        switchToScene4(event);
    }

    public void createPriorityNonPreemptive(ActionEvent event) throws IOException {
        schedulers = new PriorityNonPreemptive(scheduler);
        switchToScene4(event);
    }

    public void switchToSample(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene4(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene4.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
