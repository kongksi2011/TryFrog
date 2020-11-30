package p4_group_8_repo;

import javafx.scene.image.Image;

public class Obstacle extends Actor {
	private double speed;
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}
	
	public Obstacle(String imageLink, int xpos, int ypos, double d, int w, int h) {
		setImage(new Image(imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
		speed = d;
	}

}
