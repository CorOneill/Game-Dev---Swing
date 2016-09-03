package game;

import java.awt.image.BufferedImage;

import engine.Game;
import engine.Sprite;

public class AlienSprite extends Sprite{

	private float shotTimer;
	
	public AlienSprite(int pX, int pY, BufferedImage pImage, Game pGame) {
		super(pX, pY, pImage, pGame);
		shotTimer = 0;
	}

	@Override
	public void logic(float tpf) {
		
		shotTimer+=tpf;
		
		
		if(Math.abs(getX() - ((MyGame) getGame()).getPlayer().getX()) >= 25){
			if(getX() > ((MyGame) getGame()).getPlayer().getX()){
				setX(getX()-(int)(100*tpf));
			}
			else{
				setX(getX()+(int)(100*tpf));
			}
		}
		else{
			if(shotTimer >= 2){
				((MyGame) getGame()).getEnemyFire().add(new EnemyFireSprite(this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2, ((MyGame) getGame()).getImageLib().getImage("enemyFire"), ((MyGame) getGame())));
				((MyGame) getGame()).getSoundLib().playSound("laser");
				shotTimer = 0;
			}
		}
		
		setY(getY() + (int) (100*tpf));
		
		if(getY() >= 1000){
			((MyGame) getGame()).getAlienSprites().remove(this);
		}
		
	}
	
}
