package View;

import Model.Interpreter.Commands.*;
import Model.Interpreter.Interpret.AutoPilotParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Model.MainModel;
import Model.SimulatorModel;

public class TheMainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//openFlightGear();
    	System.out.println("Welcome to Flight Gear Simulator Controller !");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Flight.fxml"));
        Parent root = loader.load();
        
        FlightController ctrl = loader.getController();
        ViewModel viewModel=new ViewModel();
        SimulatorModel simulator=new SimulatorModel();
        MainModel model=new MainModel();
        
        model.addObserver(viewModel);
        viewModel.setModels(model,simulator);
        viewModel.addObserver(ctrl);
        ctrl.setViewModel(viewModel);
        
        primaryStage.setTitle("Flight Gear Simulator Controller");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("View/pics/logo.png")));
	    primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            DisconnectCommand command=new DisconnectCommand();
            String[] disconnect={""};
            command.executeCommand(disconnect);
            AutoPilotParser.thread1.interrupt();
            viewModel.stopAll();
            System.out.println("Exit Flight Gear Simulator Controller");
        });

    }
    public static void main(String[] args) {
        launch(args);
		Platform.exit();// safe exit
		System.exit(0);
    }
}
