package p4_group_8_repo;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MyButton extends Button {
	public MyButton() {
		
	}
	
	public MyButton(String text, String font, int fontSize, String backgroundColor) {
		setPrefWidth(200);
		setText(text);
		setFont(Font.font(font, fontSize));
		setStyle("-fx-background-color:" + backgroundColor);
		
	}
}
