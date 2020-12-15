package p4_group_8_repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label; 
import javafx.animation.TranslateTransition;


public class GameView {
	AnimationTimer timer;
	Animal animal;
	MyStage background;
	int stage = 1;
	int life;
	int score;
	int total=0;
	File myObj = new File("C:\\Users\\User\\Frogger\\score.txt");
	
	ArrayList<String> data = new ArrayList<>();
	
	/**
	 * initialize start game
	 */	
	GameView() {
	    background = new MyStage();
	    
	    stage1();
	}
	
	/**
	 * Life count, score, and score record
	 */
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
            	//update score
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	
            	//update life
            	if (animal.changeLife()) {
            		setNumber1(animal.getLife());
            	}
            	
            	life = animal.getLife();
            	
            	if (animal.getStop()) {
            		System.out.print("STOPP:");
            		background.stopMusic();
            		stop();
            		background.stop();
            		
            		
            		
            		/**
            		 * record score for each stage
            		 */
            		
            		//write
            		try {
            		      FileWriter myWriter = new FileWriter("score.txt");
            		      myWriter.write("\n" + stage +"." + animal.getPoints());
            		      myWriter.close();
            		      System.out.println("Successfully wrote to the file.");
            		    } catch (IOException e) {
            		      System.out.println("An error occurred.");
            		      e.printStackTrace();
            		    }
            		
            		//read
            		try {
            		BufferedReader bufReader = new BufferedReader(new FileReader("score.txt"));
            		String line = bufReader.readLine(); 
            		while (line != null) { 
            			data.add(line); 
            			line = bufReader.readLine(); 
            			} 
            		bufReader.close();
            		} catch (IOException e) {
          		      System.out.println("An error occurred.");
          		      e.printStackTrace();
            		
            		}
            		
            		Text recordLabel = new Text("Record for each stages:");
            		recordLabel.setStyle("-fx-text-fill: red");
            		recordLabel.setFill(Color.RED);
            		
            		
            		
            		VBox scoreRecord = new VBox();
            		scoreRecord.setSpacing(0);
            		
            		for(int i = 0; i<data.size(); i ++) {
            			
            			String Text = data.get(i);
            			Text text = new Text(Text);
            			scoreRecord.getChildren().add(text);
            			//score=Integer.valueOf(Text);
            			//total += score;
            			
            		}
            		
            		VBox vbox2 = new VBox(recordLabel, scoreRecord);
            		vbox2.setTranslateX(250);
            		vbox2.setTranslateY(10);
            		vbox2.setSpacing(0);
            		
            		Text text1 = new Text("Your Score: "+animal.getPoints()+"!");
    	            Text text2 = new Text("Highest Possible Score: 800 ");
    	            Text text3 = new Text("Select 'OK' to proceed to next level.");
    	            
    	            VBox vbox = new VBox(text1, text2,text3);
    	    		vbox.setTranslateX(10);
    	    		vbox.setTranslateY(10);
    	    		vbox.setSpacing(10);
    	    		
    	    		Button button1 = new Button("OK");
    	    		Button button2 = new Button("QUIT");
    	    		HBox hbox = new HBox(button1, button2);
    	    		hbox.setTranslateX(50);
    	    		hbox.setTranslateY(100);
    	    		hbox.setSpacing(30);
    	    		
    	    		Line line = new Line();
    	    		line.setStartX(300.0); 
    	    		line.setStartY(0); 
    	    		line.setEndX(300.0); 
    	    		line.setEndY(300.0);
    	    		
    	    		
					
            		
            		StackPane secondaryLayout = new StackPane();
    	            secondaryLayout.getChildren().addAll(vbox,vbox2, hbox, line);
    	 
    	            Scene secondScene = new Scene(secondaryLayout, 400, 300);
    	 
    	            
    	            Stage newWindow = new Stage();
    	            newWindow.setTitle("You Have Won Stage" + stage +"!");
    	            newWindow.setScene(secondScene);
    	 
    	            // Specifies the modality for new window.
    	            newWindow.initModality(Modality.WINDOW_MODAL);
    	            newWindow.show();
    	            
    	            
    	            
    	            button1.setOnAction(new EventHandler<ActionEvent>() {
       	   			 
       	   	         @Override
       	   	         public void handle(ActionEvent event) {
       	   	        	clearbackground();
       	   	        	Stage stage = (Stage) button2.getScene().getWindow(); 
       	   	        	stage.close();
       	   	            
       	   	         }

					
       	   	      });
    	            
    	            button2.setOnAction(new EventHandler<ActionEvent>() {
    	   			 
    	   	         @Override
    	   	         public void handle(ActionEvent event) {
    	   	        	Stage stage = (Stage) button2.getScene().getWindow(); 
    					stage.close();
    	   	            
    	   	         }
    	   	      });
            		
    	            
    	            if(stage==5) {
    	            	Text endText = new Text("Congratulations! You have finished all the stages.");
    	            	
    	            	endText.setStyle("-fx-font: 18 arial;");
    	            	endText.setFill(Color.RED);
    	            	secondaryLayout.getChildren().add(endText);
    	            	
    	            	button1.setOnAction(new EventHandler<ActionEvent>() {
    	       	   			 
    	       	   	         @Override
    	       	   	         public void handle(ActionEvent event) {   	       	   	        	
    	       	   	        	Stage stage = (Stage) button2.getScene().getWindow(); 
    	       	   	        	stage.close();
    	       	   	            
    	       	   	         }

    						
    	       	   	      });
    	            }
            	}
            	
            	/**
            	 * Stop the game when life count = 0
            	 */
            	if (animal.getStop2()) {
            		System.out.print("STOPP:");
            		background.stopMusic();
            		stop();
            		background.stop();
            		
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Have Lost The Game!");
            		alert.setHeaderText("Your Current Score: "+animal.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800");
            		alert.show();
            	}
            	
            	
            }
           
        };
    }
	public void start() {
		background.playMusic();
    	createTimer();
        timer.start();
        
    }

    public void stop() {
        timer.stop();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  background.add(new Digit(k, 30, 360 - shift, 25));
    		  shift+=30;
    		}
    }
    
    //Set Life Count
    public void setNumber1(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  background.add(new Digit(k, 30, 220 - shift, 25));
    		  shift+=30;
    		}
    }
    
	public MyStage getBackground() {
		// TODO Auto-generated method stub
		return background;
	}
	
	public void setOnAction(EventHandler<ActionEvent> eventHandler) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Levels
	 */
	private void clearbackground() {
		stage++;
		background.getChildren().clear();
		if(stage==2) {
			stage2();
		} else if (stage==3) {
			stage3();
		} else if (stage==4) {
			stage4();
		} else if (stage==5) {
			stage5();
		}
		
		
	}
	
	/**
	 * background design
	 */
	private void lane() {
		Line grass = new Line(0, 130, 600, 130);
	    grass.setStroke(Color.FORESTGREEN);
	    grass.setStrokeWidth(80);
	    
	    Line lnStreet10 = new Line(0, 300, 600, 300);
	    lnStreet10.setStroke(Color.AQUA);
	    lnStreet10.setStrokeWidth(260);
	    
	    Line lnStreet8 = new Line(0, 475, 600, 475);
	    lnStreet8.setStrokeWidth(5);
	    
	    Line lnStreet9 = new Line(0, 502.5, 600, 502.5);
	    lnStreet9.setStrokeLineCap(StrokeLineCap.BUTT);
	    lnStreet9.getStrokeDashArray().addAll(20d, 40d);
	    lnStreet9.setStrokeDashOffset(5);
	    
	    Line lnStreet4 = new Line(0, 530, 600, 530);
	    lnStreet4.setStrokeWidth(5);
	    
	    Line lnStreet5 = new Line(0, 557.5, 600, 557.5);
	    lnStreet5.setStrokeLineCap(StrokeLineCap.BUTT);
	    lnStreet5.getStrokeDashArray().addAll(20d, 40d);
	    lnStreet5.setStrokeDashOffset(5);
	    
	    Line lnStreet3 = new Line(0, 585, 600, 585);
	    lnStreet3.setStrokeWidth(5);
	    
	    Line lnStreet6 = new Line(0, 612.5, 600, 612.5);
	    lnStreet6.setStrokeLineCap(StrokeLineCap.BUTT);
	    lnStreet6.getStrokeDashArray().addAll(20d, 40d);
	    lnStreet6.setStrokeDashOffset(5);
	    
	    Line lnStreet1 = new Line(0, 640, 600, 640);
	    lnStreet1.setStrokeWidth(5);
	    
	    Line lnStreet7 = new Line(0, 667.5, 600, 667.5);
	    lnStreet7.setStrokeLineCap(StrokeLineCap.BUTT);
	    lnStreet7.getStrokeDashArray().addAll(20d, 40d);
	    lnStreet7.setStrokeDashOffset(5);
	    
	    Line lnStreet2 = new Line(0, 695, 600, 695);
	    lnStreet2.setStrokeWidth(5);
	    
	    background.getChildren().addAll(lnStreet1, lnStreet2, lnStreet3, lnStreet4,lnStreet5, lnStreet6, lnStreet7, lnStreet8, lnStreet9, lnStreet10, grass);
	}
	
	/**
	 * background design(top screen)
	 */
	private void top_screen() {
		BackgroundImage froggerback = new BackgroundImage("file:src/p4_group_8_repo/Screen Shot 2017-05-29 at 10.02.14 PM.png");
	    Text textStage = new Text("Stage" + stage);
	    textStage.setTranslateX(10);
	    textStage.setTranslateY(20);
	    textStage.setStyle("-fx-font: 24 arial;");
	    Text lifeText = new Text("LIVES:");
	    lifeText.setTranslateX(220);
	    lifeText.setTranslateY(20);
	    lifeText.setStyle("-fx-font: 15 arial;");
	    Text scoreText = new Text("SCORE:");
	    scoreText.setTranslateX(310);
	    scoreText.setTranslateY(20);
	    scoreText.setStyle("-fx-font: 15 arial;");
	    background.add(froggerback);
		background.getChildren().addAll(textStage, lifeText, scoreText);
	}
	
	private void stage1() {
		
		top_screen();
	    lane();

		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.75));
		
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2));
		
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.75));
		
		
		background.add(new Turtle(500, 376, -1, 130, 130));
		background.add(new Turtle(300, 376, -1, 130, 130));
		background.add(new WetTurtle(700, 376, -1, 130, 130));
		background.add(new WetTurtle(600, 217, -1, 130, 130));
		background.add(new WetTurtle(400, 217, -1, 130, 130));
		background.add(new WetTurtle(200, 217, -1, 130, 130));
		
		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		background.add(animal);
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 300, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1, 120, 120));
		
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -5, 50, 50));
		
		background.add(new Digit(0, 30, 360, 25));
		background.add(new Digit(5, 30, 220, 25));
		
		background.start();
		
		start();  
	}
	
	
	
	
	private void stage2() {
		top_screen();
		lane();
	    
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2.1));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2.1));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.85));
		
		background.add(new Turtle(500, 376, -1.1, 130, 130));
		background.add(new Turtle(300, 376, -1.1, 130, 130));
		background.add(new WetTurtle(700, 376, -1.1, 130, 130));
		background.add(new WetTurtle(600, 217, -1.1, 130, 130));
		background.add(new WetTurtle(400, 217, -1.1, 130, 130));
		background.add(new WetTurtle(200, 217, -1.1, 130, 130));

		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		background.add(animal);
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1.1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 300, 649, 1.1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1.1, 120, 120));

		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1.1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1.1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -6, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		background.add(new Digit(5, 30, 220, 25));

		background.start();

		start();  
		
	}
	
	private void stage3() {
		top_screen();
		lane();
	    
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.95));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.95));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.95));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2.2));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2.2));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.95));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.95));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.95));
		
		background.add(new Turtle(500, 376, -1.2, 130, 130));
		background.add(new Turtle(300, 376, -1.2, 130, 130));
		background.add(new WetTurtle(700, 376, -1.2, 130, 130));
		background.add(new WetTurtle(600, 217, -1.2, 130, 130));
		background.add(new WetTurtle(400, 217, -1.2, 130, 130));
		background.add(new WetTurtle(200, 217, -1.2, 130, 130));

		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		background.add(animal);
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1.2, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 300, 649, 1.2, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1.2, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1.2, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1.2, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1.2, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1.2, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1.2, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1.2, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -7, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		background.add(new Digit(5, 30, 220, 25));

		background.start();

		start();  
		
	}
	
	private void stage4() {
		top_screen();
		lane();
	    
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 1.05));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 1.05));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 1.05));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2.3));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2.3));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 1.05));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 1.05));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 1.05));
		
		background.add(new Turtle(500, 376, -1.3, 130, 130));
		background.add(new Turtle(300, 376, -1.3, 130, 130));
		background.add(new WetTurtle(700, 376, -1.3, 130, 130));
		background.add(new WetTurtle(600, 217, -1.3, 130, 130));
		background.add(new WetTurtle(400, 217, -1.3, 130, 130));
		background.add(new WetTurtle(200, 217, -1.3, 130, 130));

		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		background.add(animal);
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1.3, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 300, 649, 1.3, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1.3, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1.3, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1.3, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1.3, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1.3, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1.3, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1.3, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -8, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		background.add(new Digit(5, 30, 220, 25));

		background.start();

		start();  
		
	}
	
	private void stage5() {
		top_screen();
		lane();
	    
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 1.15));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 1.15));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 1.15));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2.4));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2.4));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 1.15));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 1.15));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 1.15));
		
		background.add(new Turtle(500, 376, -1.4, 130, 130));
		background.add(new Turtle(300, 376, -1.4, 130, 130));
		background.add(new WetTurtle(700, 376, -1.4, 130, 130));
		background.add(new WetTurtle(600, 217, -1.4, 130, 130));
		background.add(new WetTurtle(400, 217, -1.4, 130, 130));
		background.add(new WetTurtle(200, 217, -1.4, 130, 130));

		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/p4_group_8_repo/froggerUp.png");
		background.add(animal);
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 0, 649, 1.4, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 300, 649, 1.4, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 600, 649, 1.4, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1.4, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1.4, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1.4, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1.4, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1.4, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1.4, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -9, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 800, 490, -9, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		background.add(new Digit(5, 30, 220, 25));

		background.start();

		start();  
		
	}
	
	
	
	
}