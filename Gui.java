import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Gui extends Application implements Constants{
    public Gui(){}

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle(TITLE);
        Button btn = new Button();
        btn.setText("The best game you've ever never played... reminiscent of a certain power of two...");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, DIMENSIONS[0], DIMENSIONS[1]));
        primaryStage.show();
    }
}
