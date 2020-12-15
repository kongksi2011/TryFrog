package p4_group_8_repo;

import javafx.scene.image.Image;

public class Log extends Actor {

	private double speed;
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}
	
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		setImage(new Image(imageLink, size,size, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
		
	}
	public boolean s1_getLeft() {
		return speed == -2;
	}
	public boolean s1_getRight() {
		return speed == 0.75;
	}
	
	public boolean s2_getLeft() {
		return speed == -2.1;
	}
	public boolean s2_getRight() {
		return speed == 0.85;
	}
	
	public boolean s3_getLeft() {
		return speed == -2.2;
	}
	public boolean s3_getRight() {
		return speed == 0.95;
	}
	
	public boolean s4_getLeft() {
		return speed == -2.3;
	}
	public boolean s4_getRight() {
		return speed == 1.05;
	}
	
	public boolean s5_getLeft() {
		return speed == -2.4;
	}
	public boolean s5_getRight() {
		return speed == 1.15;
	}
}
