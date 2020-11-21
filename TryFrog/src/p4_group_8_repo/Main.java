package p4_group_8_repo;

import java.awt.Graphics;
import java.awt.Canvas;
import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
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
	AnimationTimer timer;
	MyStage background;
	Animal animal;
	
	private Parent createContent() {
		Pane root = new Pane();
		
		root.setPrefSize(1050, 600);
		
		try(InputStream is = Files.newInputStream(Paths.get("C:\\Users\\User\\Pictures", "frog.jpg"))){
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(1050);
			img.setFitHeight(600);
			root.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
		}
		
		Title title = new Title ("F R O G G E R");
		title.setTranslateX(50);
		title.setTranslateY(200);
		
		
		
		MenuBox vbox = new MenuBox(createStart(), createGuide(), createQuit());
		vbox.setTranslateX(100);
		vbox.setTranslateY(300);
		
		root.getChildren().addAll(title,vbox);
		
		return root;
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Frogger");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
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
	
	private static class MenuBox extends VBox{
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
			
			}

		public void setOnAction(EventHandler<ActionEvent> eventHandler) {
			// TODO Auto-generated method stub
			
		}
		}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	

	

	
    
    public MenuItem createStart() {
    	MenuItem menuitem1 = new MenuItem("START");
    	menuitem1.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			GameView game = new GameView();
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
    }

}
