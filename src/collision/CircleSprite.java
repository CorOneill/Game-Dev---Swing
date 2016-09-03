package collision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Game;
import engine.Sprite;

public class CircleSprite extends Sprite{
	private int radius;

	private Color color;
	public CircleSprite(int pX, int pY, int pRadius, Game pGame) {
		super(pX, pY, pGame);
		this.radius = pRadius;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillOval(getX()-radius, getY()-radius, radius*2, radius*2);
	}
	
	public void setColor(Color pColor){
		this.color = pColor;
	}
	
	public int getRadius() {
		return radius;
	}

}