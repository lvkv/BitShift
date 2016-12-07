import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static java.lang.Math.random;

public class Gui extends Application implements Constants{

    public Stage primaryStage;
    public static GridPane gridp;
    public static Text score;
    public static Text score_text;
    public static Text bestScore;
    public static Text bestScore_text;
    public static Text bitShiftsLeft;
    public static Tile[][] tileBoard;
    public static Button bitShiftButton;
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
        btn.setOnAction(event -> {
            Main.setRows((int)row_options.getSelectionModel().getSelectedItem());
            Main.setCols((int)col_options.getSelectionModel().getSelectedItem());
            primaryStage.hide();
            Main.init();
            setGameGui();
        });
        grid.add(btn, 0, 3);

        primaryStage.setScene(new Scene(grid, 300, 300));
        primaryStage.show();
    }

    public void setGameGui(){
        gameInPlay = true;
        primaryStage.setTitle(TITLE);
        gridp = new GridPane();
        Scene scene = new Scene(gridp, DIMENSIONS[0], DIMENSIONS[1]);
        scene.setOnKeyPressed(event -> {
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
        });

        gridp.setAlignment(Pos.CENTER);
        score_text = new Text("Score: ");
        score_text.setFont(Font.font(30));
        score_text.setFill(Color.BLACK);
        gridp.add(score_text, 0, 0);
        score = new Text("0");
        score.setFont(Font.font(30));
        score.setFill(Color.BLACK);
        gridp.add(score, 1, 0);

        bestScore_text = new Text("High: ");
        bestScore_text.setFont(Font.font(30));
        bestScore_text.setFill(Color.BLACK);
        gridp.add(bestScore_text, 2, 0);
        bestScore = new Text("0");
        bestScore.setFont(Font.font(30));
        bestScore.setFill(Color.BLACK);
        gridp.add(bestScore, 3, 0);


        tileBoard = new Tile[Main.getRows()][Main.getCols()];
        for(int row=0; row<Main.getRows(); row++){
            for(int col=0; col<Main.getCols(); col++){
                Tile tile = new Tile(Integer.toString(Main.getSpace(row, col).getValue()));
                tileBoard[row][col] = tile;
                gridp.add(tile, col, row+1);
            }
        }

        Button restartButton = new Button();
        restartButton.setText("  Restart Game  ");
        restartButton.setOnAction(event -> {
            Main.init();
            refreshGui();
        });
        gridp.add(restartButton, 0, Main.getRows()+1);

        bitShiftButton = new Button();
        bitShiftButton.setText("\tBitShift\t ");
        bitShiftButton.setOnAction(event -> {
            if(Main.getBitshifts() > 0) {
                Main.bitShift();
            }
            refreshGui();
        });
        gridp.add(bitShiftButton, 1, Main.getRows()+1);


        bitShiftsLeft = new Text("x"+Integer.toString(Main.getBitshifts()));
        bitShiftsLeft.setFont(Font.font(20));
        gridp.add(bitShiftsLeft, 2, Main.getRows()+1);



        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void refreshGui(){
        for(int row=0; row<Main.getRows(); row++){
            for(int col=0; col<Main.getCols(); col++){
                tileBoard[row][col].setNewText(Integer.toString(Main.getSpace(row, col).getValue()));
            }
        }
        score.setText(Integer.toString(Main.getPoints()));
        bestScore.setText(Integer.toString(Main.getBestPoints()));
        if(Main.getBitshifts() == 0){
            bitShiftButton.setDisable(true);
        }
        bitShiftsLeft.setText("x"+Integer.toString(Main.getBitshifts()));
    }

    public static void tileAnimation(Tile movingTile, int direction){
        Timeline timeline = new Timeline();
        switch (direction){
            case LEFT:
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(movingTile.translateXProperty(), 0),
                                new KeyValue(movingTile.translateYProperty(), 0)
                        ),
                        new KeyFrame(new Duration(200),
                                new KeyValue(movingTile.translateXProperty(), -100),
                                new KeyValue(movingTile.translateYProperty(), 0))
                );
                break;
            case RIGHT:
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(movingTile.translateXProperty(), 0),
                                new KeyValue(movingTile.translateYProperty(), 0)
                        ),
                        new KeyFrame(new Duration(200),
                                new KeyValue(movingTile.translateXProperty(), 100),
                                new KeyValue(movingTile.translateYProperty(), 0))
                );
                break;
            case UP:
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(movingTile.translateXProperty(), 0),
                                new KeyValue(movingTile.translateYProperty(), 0)
                        ),
                        new KeyFrame(new Duration(200),
                                new KeyValue(movingTile.translateXProperty(), 0),
                                new KeyValue(movingTile.translateYProperty(), -100))
                );
                break;
            case DOWN:
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(movingTile.translateXProperty(), 0),
                                new KeyValue(movingTile.translateYProperty(), 0)
                        ),
                        new KeyFrame(new Duration(200),
                                new KeyValue(movingTile.translateXProperty(), 100),
                                new KeyValue(movingTile.translateYProperty(), 0))
                );
                break;
        }

        // play 40s of animation
        timeline.play();
    }

    public static void lose(){

    }
}
