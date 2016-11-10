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
                    break;
                case 4:
                    border.setFill(Color.WHEAT);
                    break;
                case 8:
                    break;
                case 16:
                    break;
                case 32:
                    break;
                case 64:
                    break;
                case 128:
                    break;
                case 256:
                    break;
                case 512:
                    break;
                case 1024:
                    break;
                case 2048:
                    break;
                default:
                    border.setFill(Color.BISQUE);
                    break;
            }

        }
    }
}
