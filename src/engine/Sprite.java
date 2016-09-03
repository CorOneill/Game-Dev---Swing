package engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	private int x, y, width, height;
	private BufferedImage image;
	private BufferedImage[] animation;
	private Game game;
	private int index;
	
	public Sprite(int pX, int pY, Game pGame){
		this.x = pX;
		this.y = pY;
		this.game = pGame;
	}
	
	public Sprite(int pX, int pY, BufferedImage pImage, Game pGame){
		this.x = pX;
		this.y = pY;
		this.width = pImage.getWidth();
		this.height = pImage.getHeight();
		this.image = pImage;
		this.game = pGame;
	}
	
	public Sprite(int pX, int pY, BufferedImage[] pAnimation, Game pGame){
		this.x = pX;
		this.y = pY;
		System.out.println(pAnimation[0].getWidth());
		//this.width = pAnimation[0].getWidth();
		//this.height = pAnimation[0].getHeight();
		this.animation = pAnimation;
		this.game = pGame;
		this.index = 0;
	}
	
	public void logic(float tpf){
		
	}
	
	public void render(Graphics g){
		if(image != null)
			g.drawImage(this.image, x, y, null);
		if(animation != null)
			g.drawImage(this.animation[index], x, y, null);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage[] getAnimation() {
		return animation;
	}

	public void setAnimation(BufferedImage[] animation) {
		this.animation = animation;
	}

	public int getIndex() {
		return index;
	}
	
	public void nextIndex(){
		index++;
		if(index >= animation.length){
			index = 0;
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
