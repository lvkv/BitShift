import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Gui extends Application implements Constants{

    public Stage primaryStage;

    public Gui(){}

    @Override
    public void start(Stage ps){
        primaryStage = ps;
        primaryStage.setTitle(TITLE+" Launcher");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text(TITLE);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label lbl_rows = new Label("Rows: ");
        grid.add(lbl_rows, 0, 1);

        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                        4,
                        5,
                        6,
                        7,
                        8
                );

        final ComboBox row_options = new ComboBox(options);
        row_options.getSelectionModel().selectFirst();
        grid.add(row_options, 1, 1);

        Label lbl_cols = new Label("Columns: ");
        grid.add(lbl_cols, 0, 2);

        final ComboBox col_options = new ComboBox(options);
        col_options.getSelectionModel().selectFirst();
        grid.add(col_options, 1, 2);

        Button btn = new Button();
        btn.setText("Launch");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Launching App...");
                Main.setRows((int)row_options.getSelectionModel().getSelectedItem());
                Main.setCols((int)col_options.getSelectionModel().getSelectedItem());
                primaryStage.hide();
                Main.init();
                setGameGui();
            }
        });
        grid.add(btn, 0, 3);

        primaryStage.setScene(new Scene(grid, 300, 300));
        primaryStage.show();
    }

    public void setGameGui(){
        GridPane grid = new GridPane();

        //for()
        primaryStage.setScene(new Scene(grid, DIMENSIONS[0], DIMENSIONS[1]));
        primaryStage.show();
    }
}
