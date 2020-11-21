package p4_group_8_repo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class Menu {
	
	public void render(Graphics g) {
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Frogger Game", 100, 100);
		
	}
	
}

