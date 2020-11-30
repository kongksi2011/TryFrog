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
import java.util.Scanner;

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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label; 

public class GameView {
	AnimationTimer timer;
	Animal animal;
	MyStage background;
	int stage = 1;
	File myObj = new File("C:\\Users\\User\\Frogger\\score.txt");
	//String data;
	ArrayList<String> data = new ArrayList<>();
	
	
	
	GameView() {
	    background = new MyStage();
	   
	    stage1();
	}
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	
            	if (animal.getStop()) {
            		System.out.print("STOPP:");
            		background.stopMusic();
            		stop();
            		background.stop();
            		
            		
            		
            		
            		try {
            		      FileWriter myWriter = new FileWriter("score.txt");
            		      myWriter.write("\n" + stage +"." + animal.getPoints());
            		      myWriter.close();
            		      System.out.println("Successfully wrote to the file.");
            		    } catch (IOException e) {
            		      System.out.println("An error occurred.");
            		      e.printStackTrace();
            		    }
            		
            		/*try {
            		      //File myObj = new File("score.txt");
            			  BufferedReader bufReader = new BufferedReader(new FileReader("score.txt"));
            		      //Scanner myReader = new Scanner(myObj);
            		      while (myReader.hasNextLine()) {
            		        data.add(e) = myReader.nextLine();
            		        System.out.println(data);
            		      }
            		      myReader.close();
            		    } catch (FileNotFoundException e) {
            		      System.out.println("An error occurred.");
            		      e.printStackTrace();
            		    }
            		*/
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
            		
            		Text recordLabel = new Text("Record:");
            		
            		
            		
            		VBox scoreRecord = new VBox();
            		
            		for(int i = 0; i<data.size(); i ++) {
            			//VBox scoreRecord = new VBox();
            			String Text = data.get(i);
            			Text text = new Text(Text);
            			scoreRecord.getChildren().add(text);
            			
            			//secondaryLayout.getChildren().add(scoreRecord);
            		}
            		
            		VBox vbox2 = new VBox(recordLabel, scoreRecord);
            		vbox2.setTranslateX(350);
            		vbox2.setTranslateY(10);
            		vbox2.setSpacing(0);
            		
            		Text text1 = new Text("Your Score: "+animal.getPoints()+"!");
    	            Text text2 = new Text("Highest Possible Score: 800 \nSelect 'OK' to proceed to next level.");
    	            //Text text3 = new Text(data);
    	            VBox vbox = new VBox(text1, text2);
    	    		vbox.setTranslateX(10);
    	    		vbox.setTranslateY(10);
    	    		vbox.setSpacing(10);
    	    		
    	    		Button button1 = new Button("OK");
    	    		Button button2 = new Button("QUIT");
    	    		HBox hbox = new HBox(button1, button2);
    	    		hbox.setTranslateX(50);
    	    		hbox.setTranslateY(100);
    	    		hbox.setSpacing(30);
    	    		
    	    		
					//Label label = new Label(data);
            		
            		StackPane secondaryLayout = new StackPane();
    	            secondaryLayout.getChildren().addAll(vbox,vbox2, hbox);
    	 
    	            Scene secondScene = new Scene(secondaryLayout, 400, 300);
    	 
    	            // New window (Stage)
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
            		/*Alert alert = new Alert(AlertType.CONFIRMATION);
            		alert.setTitle("You Have Won The First Stage!");
            		alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800\n Select OK to proceed to second stage.");
            		Optional<ButtonType> result = alert.showAndWait();
            		if(!result.isPresent()) {
            			// alert is exited, no button has been pressed.
            		}else if(result.get() == ButtonType.OK) {
            		     //oke button is pressed
            		}else if(result.get() == ButtonType.CANCEL) {
            		    // cancel button is pressed
            		}
            		*/
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
	public MyStage getBackground() {
		// TODO Auto-generated method stub
		return background;
	}
	
	public void setOnAction(EventHandler<ActionEvent> eventHandler) {
		// TODO Auto-generated method stub
		
	}
	
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
	
	private void stage1() {
		BackgroundImage froggerback = new BackgroundImage("file:src/p4_group_8_repo/Screen Shot 2017-05-29 at 10.02.14 PM.png");
	    Text textStage = new Text("Stage" + stage);
	    textStage.setTranslateX(10);
	    textStage.setTranslateY(20);
	    textStage.setStyle("-fx-font: 24 arial;");
		background.add(froggerback);
		background.getChildren().add(textStage);
		
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.75));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2));
		//background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 800, 276, -2));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.75));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 570, 329, 0.75));
		
		background.add(new Turtle(500, 376, -1, 130, 130));
		background.add(new Turtle(300, 376, -1, 130, 130));
		background.add(new WetTurtle(700, 376, -1, 130, 130));
		background.add(new WetTurtle(600, 217, -1, 130, 130));
		background.add(new WetTurtle(400, 217, -1, 130, 130));
		background.add(new WetTurtle(200, 217, -1, 130, 130));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 100, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 0, 100, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 120, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 120, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 140, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 140, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 160, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 300, 160, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 180, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 180, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 200, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 200, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 220, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 220, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 400, 220, 1));
		//End end2 = new End();
		//End end3 = new End();
		//End end4 = new End();
		//End end5 = new End();
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
		//background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 720, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -5, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		//background.add(obstacle);
		//background.add(obstacle1);
		//background.add(obstacle2);
		background.start();
		//primaryStage.setScene(scene);
		//primaryStage.show();
		start();  
	}
	
	
	private void stage2() {
		BackgroundImage froggerback = new BackgroundImage("file:src/p4_group_8_repo/Screen Shot 2017-05-29 at 10.02.14 PM.png");
	    
		background.add(froggerback);
		Text textStage = new Text("Stage" + stage);
	    textStage.setTranslateX(10);
	    textStage.setTranslateY(20);
	    textStage.setStyle("-fx-font: 24 arial;");
	    background.getChildren().add(textStage);
	    
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 220, 166, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 440, 166, 0.85));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 0, 276, -2.1));
		background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 400, 276, -2.1));
		//background.add(new Log("file:src/p4_group_8_repo/logs.png", 300, 800, 276, -2));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 50, 329, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 270, 329, 0.85));
		background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 490, 329, 0.85));
		//background.add(new Log("file:src/p4_group_8_repo/log3.png", 150, 570, 329, 0.75));
		
		background.add(new Turtle(500, 376, -1.1, 130, 130));
		background.add(new Turtle(300, 376, -1.1, 130, 130));
		background.add(new WetTurtle(700, 376, -1.1, 130, 130));
		background.add(new WetTurtle(600, 217, -1.1, 130, 130));
		background.add(new WetTurtle(400, 217, -1.1, 130, 130));
		background.add(new WetTurtle(200, 217, -1.1, 130, 130));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 100, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 0, 100, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 120, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 120, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 140, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 140, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 160, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 300, 160, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 180, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 180, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 200, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 200, -1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 100, 220, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 200, 220, 1));
		//background.add(new Log("file:src/p4_group_8_repo/log2.png", 400, 220, 1));
		//End end2 = new End();
		//End end3 = new End();
		//End end4 = new End();
		//End end5 = new End();
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
		//background.add(new Obstacle("file:src/p4_group_8_repo/truck1"+"Right.png", 720, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 100, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 250, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 400, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 550, 597, -1.1, 50, 50));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 0, 540, 1.1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/truck2Right.png", 500, 540, 1.1, 200, 200));
		background.add(new Obstacle("file:src/p4_group_8_repo/car1Left.png", 500, 490, -6, 50, 50));
		background.add(new Digit(0, 30, 360, 25));
		//background.add(obstacle);
		//background.add(obstacle1);
		//background.add(obstacle2);
		background.start();
		//primaryStage.setScene(scene);
		//primaryStage.show();
		start();  
		
	}
	
	private void stage3() {
		// TODO Auto-generated method stub
		
	}
	
	private void stage4() {
		// TODO Auto-generated method stub
		
	}
	
	private void stage5() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}