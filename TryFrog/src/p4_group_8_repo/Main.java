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

public class Main extends Application {
	
	//Pane root = new Pane();
	MyStage mainBackground;
	
	
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
		MyButton button4 = new MyButton("SCOREBOARD", "Times New Roman", 20, "WHITE");
		
		VBox vbox = new VBox(button1, button2, button4, button3);
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
	 
	            
	            Text text1 = new Text("W = ^");
	            Text text2 = new Text("A = <");
	            Text text3 = new Text("S = >");
	            Text text4 = new Text("D = v");
	            
	            VBox vbox = new VBox(text1, text2, text3, text4);
	    		vbox.setTranslateX(10);
	    		vbox.setTranslateY(10);
	    		vbox.setSpacing(10);
	 
	            StackPane secondaryLayout = new StackPane();
	            secondaryLayout.getChildren().addAll(vbox);
	 
	            Scene secondScene = new Scene(secondaryLayout, 300, 200);
	 
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
		
		button4.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	 
	            
	            Text text1 = new Text("No records!");
	           
	            
	            
	            VBox vbox = new VBox(text1);
	    		vbox.setTranslateX(10);
	    		vbox.setTranslateY(10);
	    		vbox.setSpacing(10);
	 
	            StackPane secondaryLayout = new StackPane();
	            secondaryLayout.getChildren().addAll(vbox);
	 
	            Scene secondScene = new Scene(secondaryLayout, 300, 200);
	 
	            // New window (Stage)
	            Stage newWindow = new Stage();
	            newWindow.setTitle("SCOREBOARD");
	            newWindow.setScene(secondScene);
	 
	            // Specifies the modality for new window.
	            newWindow.initModality(Modality.WINDOW_MODAL);
	 
	           
	 
	            newWindow.show();
	         }
	      });
		
		
		/**MenuBox vbox = new MenuBox(createStart(), createGuide(), createQuit());
		vbox.setTranslateX(100);
		vbox.setTranslateY(300);*/
		
		mainBackground.getChildren().addAll(title, vbox);
		
		return mainBackground;
		
	}
	@Override
	public void start(Stage stage){
		
		Scene scene = new Scene(createContent());
		stage.setTitle("Frogger");
		stage.setScene(scene);
		stage.show();
		//stage.setMaximized(true);
		//createStart();
		
		
		
		//createStartButton();
		//createGuideButton();
		//createQuitButton();
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
	
	/**private static class MenuBox extends VBox{
		public MenuBox(MenuItem...items) {
			getChildren().add(createSeperator());
			
			for(MenuItem item : items) {
				getChildren().addAll(item, createSeperator());
			}
		}
		
		private Line createSeperator() {
			Line sep = new Line();
			sep.setEndX(210);
			sep.setStroke(Color.DARKGREY);
			return sep;
		}
		
	}
	

	private static class MenuItem extends StackPane{
		public MenuItem(String name) {
			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] { 
				new Stop(0, Color.DARKBLUE),
				new Stop(0.1, Color.BLACK),
				new Stop(0.9, Color.BLACK),
				new Stop(1, Color.DARKBLUE)
				
			});
			
			Rectangle bg = new Rectangle(200,30);
			bg.setOpacity(0.4);
			
			Text text = new Text(name);
			text.setFill(Color.DARKGREY);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
			setOnMouseEntered(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);
				
			});
			
			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
				text.setFill(Color.DARKGREY);
			});
			setOnMousePressed(event -> {
				bg.setFill(Color.DARKVIOLET);
				
			});
			
			setOnMouseReleased(event -> {
				bg.setFill(gradient);
			});
			
			}*/

		public void setOnAction(EventHandler<ActionEvent> eventHandler) {
			// TODO Auto-generated method stub
			
		}
		
	
	/** public void createStartButton() {
		 MyButton  btnStart = new MyButton("START", "Berlin Sans FB", 25, "#8FBC8F");
		 
		 btnStart.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent e) {
				 GameView gameview = new GameView();
	    			mainBackground.getScene().setRoot(gameview.getBackground());
			 }
		 });
		 addButton(btnStart);
	 }
	 
	 private void createQuitButton() {
			Button buttonQuit = new Button("Quit");
			
			 addButton(buttonQuit);
		}
		private void createGuideButton() {
			Button buttonGuide = new Button("Guide");
			 addButton(buttonGuide);
		}
	
	
	  private void addButton(Button button) {
		//button.setLayoutX(200);
		//mainMenuButtons.add(button);
		mainBackground.getChildren().add(button);
		
		
	}
	*/
	
	/**public MenuItem createStart() {
	    	MenuItem menuitem1 = new MenuItem("START");
	    	menuitem1.setOnAction(new EventHandler<ActionEvent>() {
	    		public void handle(ActionEvent event) {
	    			
	    			GameView gameview = new GameView();
	    			mainBackground.getScene().setRoot(gameview.getBackground());
	    			
	    		}
	    	});
			return menuitem1;
	    }
	    
	    public MenuItem createGuide() {
	    	MenuItem menuitem2 = new MenuItem("GUIDE");
			return menuitem2;
	    }
	    
	    public MenuItem createQuit() {
	    	MenuItem menuitem3 = new MenuItem("QUIT");
			return menuitem3;
	    }*/
	    
	    
	
	public static void main(String[] args) {
		launch(args);
	}
	public MyStage getBackground() {
		// TODO Auto-generated method stub
		return mainBackground;
	}

	
	

	

	
    
  

}
