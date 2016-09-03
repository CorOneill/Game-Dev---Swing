package game;

import java.awt.image.BufferedImage;

import engine.Game;
import engine.Sprite;

public class Explosion extends Sprite{

	private float counter;
	
	public Explosion(int pX, int pY, BufferedImage[] pAnimation, Game pGame) {
		super(pX, pY, pAnimation, pGame);
		this.counter = 0;
		
	}

	@Override
	public void logic(float tpf) {
		counter += tpf;
		if(counter >= 0.05){
			counter = 0;
			if(getIndex() == getAnimation().length-1){
				((MyGame)getGame()).getExplosions().remove(this);
			}
			else{
				nextIndex();
			}
		}
	}

	
	
}
