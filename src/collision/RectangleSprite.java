package collision;

import java.awt.Color;
import java.awt.Graphics;

import engine.Game;
import engine.Sprite;

public class RectangleSprite extends Sprite{
	private int width, height;
	private Color color;
	public RectangleSprite(int pX, int pY, int pWidth, int pHeight, Game pGame) {
		super(pX, pY, pGame);
		this.setWidth(pWidth);
		this.setHeight(pHeight);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	public void setColor(Color pColor){
		this.color = pColor;
	}
}
