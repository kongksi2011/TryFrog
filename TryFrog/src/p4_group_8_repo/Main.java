package p4_group_8_repo;

import java.awt.Graphics;
import java.awt.Canvas;
import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;
import javafx.scene.control.MenuButton;
/**
 * Main Menu
 */
public class Main extends Application {
	
	
	MyStage mainBackground;
	
	/**
	 * Create content of main menu
	 * @return
	 */
	private MyStage createContent() {
		mainBackground = new MyStage();
		
		mainBackground.setPrefSize(600, 800);
		
		try(InputStream is = Files.newInputStream(Paths.get("C:\\Users\\User\\Pictures", "frog.jpg"))){
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(600);
			img.setFitHeight(800);
			mainBackground.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
		}
		
		Title title = new Title ("F R O G G E R");
		title.setTranslateX(50);
		title.setTranslateY(200);
		
		MyButton button1 = new MyButton("START","Times New Roman", 20, "WHITE");
		MyButton button2 = new MyButton("GUIDE","Times New Roman", 20, "WHITE");
		MyButton button3 = new MyButton("QUIT", "Times New Roman", 20, "WHITE");
		
		
		VBox vbox = new VBox(button1, button2, button3);
		vbox.setTranslateX(100);
		vbox.setTranslateY(300);
		vbox.setSpacing(30);
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent e) {
				 GameView gameview = new GameView();
	    			mainBackground.getScene().setRoot(gameview.getBackground());
			 }
		 });
		
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	 
	            Text text0 = new Text("Welcome to MyFrogger Game!\nThere are 10 levels total which difficulty is increased by level.\nYou are given 5 lifes in each level.\nIn order to win a level, u need to fill each empty place with frog(Total of 5).\nYou will lose a life if u get hit by cars or fall into river so be careful!\nGood Luck!");
	            Text text1 = new Text("W = Move Up");
	            Text text2 = new Text("A = Move Left");
	            Text text3 = new Text("S = Move Right");
	            Text text4 = new Text("D = Move Down");
	            
	            VBox vbox = new VBox(text0, text1, text2, text3, text4);
	    		vbox.setTranslateX(10);
	    		vbox.setTranslateY(10);
	    		vbox.setSpacing(10);
	 
	            StackPane secondaryLayout = new StackPane();
	            secondaryLayout.getChildren().addAll(vbox);
	 
	            Scene secondScene = new Scene(secondaryLayout, 500, 300);
	 
	            // New window (Stage)
	            Stage newWindow = new Stage();
	            newWindow.setTitle("Guide");
	            newWindow.setScene(secondScene);
	 
	            // Specifies the modality for new window.
	            newWindow.initModality(Modality.WINDOW_MODAL);
	 
	           
	 
	            newWindow.show();
	         }
	      });
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent e) {
				 Stage stage = (Stage) button3.getScene().getWindow(); 
				 stage.close();
			 }
		 });
		
		
		
		

		
		mainBackground.getChildren().addAll(title, vbox);
		
		return mainBackground;
		
	}
	@Override
	public void start(Stage stage){
		
		Scene scene = new Scene(createContent());
		stage.setTitle("Frogger");
		stage.setScene(scene);
		stage.show();

	}
	
	

	private static class Title extends StackPane{
		public Title(String name) {
			Rectangle bg = new Rectangle(375, 60);
			bg.setStroke(Color.WHITE);
			bg.setStrokeWidth(2);
			bg.setFill(null);
			
			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
		}
	}
	


		public void setOnAction(EventHandler<ActionEvent> eventHandler) {
			// TODO Auto-generated method stub
			
		}
		
	    
	    
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public MyStage getBackground() {
		// TODO Auto-generated method stub
		return mainBackground;
	}


}
