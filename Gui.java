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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Gui extends Application implements Constants{

    public Stage primaryStage;
    public static Text score;
    public static Text score_text;
    public static Text lose_text;
    public static Tile[][] tileBoard;
    public static boolean gameInPlay = false;

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
        gameInPlay = true;
        primaryStage.setTitle(TITLE);
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, DIMENSIONS[0], DIMENSIONS[1]);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // These cases are KeyCodes, not from Constants interface
                if(gameInPlay){
                    switch (event.getCode()) {
                        case UP:
                            Main.swipe(Constants.UP);
                            break;
                        case DOWN:
                            Main.swipe(Constants.DOWN);
                            break;
                        case LEFT:
                            Main.swipe(Constants.LEFT);
                            break;
                        case RIGHT:
                            Main.swipe(Constants.RIGHT);
                            break;
                    }
                }
                refreshGui();
            }
        });

        grid.setAlignment(Pos.CENTER);
        score_text = new Text("Score: ");
        score_text.setFont(Font.font(30));
        score_text.setFill(Color.BLACK);
        grid.add(score_text, 0, 0);

        score = new Text("0");
        score.setFont(Font.font(30));
        score.setFill(Color.BLACK);
        grid.add(score, 1, 0);

        tileBoard = new Tile[Main.getRows()][Main.getCols()];
        for(int row=0; row<Main.getRows(); row++){
            for(int col=0; col<Main.getCols(); col++){
                Tile tile = new Tile(Integer.toString(Main.getSpace(row, col).getValue()));
                //tile.setTranslateX(col * 100);
                //tile.setTranslateY(row * 100);
                tileBoard[row][col] = tile;
                grid.add(tile, col, row+1);
            }
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void refreshGui(){
        for(int row=0; row<Main.getRows(); row++){
            for(int col=0; col<Main.getCols(); col++){
                tileBoard[row][col].setNewText(Integer.toString(Main.getSpace(row, col).getValue()));
                score.setText(Integer.toString(Main.getPoints()));
                //tileBoard[row][col] = new Tile(Integer.toString(Main.getSpace(row, col).getValue()));
            }
        }
    }

    public static void lose(){
        gameInPlay = false;

    }
}
