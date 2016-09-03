package game;

import java.awt.image.BufferedImage;

import engine.Game;
import engine.Sprite;

public class AsteroidSprite extends Sprite{

	public AsteroidSprite(int pX, int pY, BufferedImage pImage, Game pGame) {
		super(pX, pY, pImage, pGame);
		
	}

	@Override
	public void logic(float tpf) {
		if(getY() >= 1000){
			setY(-500 + (int)(Math.random() * 400));
			setX((int)(Math.random()*1300));
		}
		
		
		setY(getY() + (int) (200*tpf));
	
	}
	
}
