import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class ProductivityCheck extends Application {
    private static final double SCENE_WIDTH = 300;
    private static final double SCENE_HIGHT = 200;
    //Slider productivity = new Slider(0,10, 5);

    final Label productivityCaption = new Label("How productive did you feel during the last task?");

    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Productivity Check");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(70);

        scene.setRoot(grid);
        GridPane.setConstraints(productivityCaption, 0, 1);

        Slider productivity = new Slider(0, 10, 5);


        productivity.setBlockIncrement(1);
        productivity.setShowTickLabels(true);
        productivity.setShowTickMarks(true);
        productivity.setSnapToTicks(true);



        root.getChildren().add(productivity);
        //root.getChildren().add(submit);



        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


    }



    /*
    @Override

    public void start(final Stage primaryStage){
        Button btn = new Button();
        btn.setText("Open Dialog");
        btn.setOnAction(
                new EventHandler<ActiveEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVBox = new VBox(20);
                        dialogVBox.getChildren().add(new Text("this is a test"));
                        Scene dialogScene = new Scene(dialogVBox, 300, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                }
        );
    }
    */

