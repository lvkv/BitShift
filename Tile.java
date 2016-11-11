import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends GridPane{

    Rectangle border;
    Text text;

    public Tile(String value){
        border = new Rectangle(100, 100);
        border.setFill(Color.BISQUE);
        border.setStroke(Color.WHITE);

        text = value.equals("0")?new Text():new Text(value);
        text.setFont(Font.font(72));
        text.setFill(Color.GRAY);

        //setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
    }

    public void setNewText(String newValue) {
        if (newValue.equals("0")) {
            border.setFill(Color.BISQUE);
            text.setText("");
        }
        else{
            text.setText(newValue);
            int intValue = Integer.parseInt(newValue);

            switch(intValue){
                case 2:
                    border.setFill(Color.BISQUE);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
                case 4:
                    border.setFill(Color.WHEAT);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
                case 8:
                    border.setFill(Color.TAN);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
                case 16:
                    border.setFill(Color.LIGHTSALMON);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
                case 32:
                    border.setFill(Color.DARKSALMON);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
                case 64:
                    border.setFill(Color.SALMON);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(72));
                    break;
                case 128:
                    border.setFill(Color.INDIANRED);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(60));
                    break;
                case 256:
                    border.setFill(Color.INDIANRED);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(60));
                    break;
                case 512:
                    border.setFill(Color.INDIANRED);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(60));
                    break;
                case 1024:
                    border.setFill(Color.INDIANRED);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(45));
                    break;
                case 2048:
                    border.setFill(Color.INDIANRED);
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(45));
                    break;
                default:
                    border.setFill(Color.BISQUE);
                    text.setFill(Color.GRAY);
                    text.setFont(Font.font(72));
                    break;
            }

        }
    }
}
