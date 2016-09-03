package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.Collision;
import engine.Game;
import engine.Sprite;

public class EnemyFireSprite extends Sprite{

	public EnemyFireSprite(int pX, int pY, BufferedImage pImage, Game pGame) {
		super(pX, pY, pImage, pGame);
		
	}

	@Override
	public void logic(float tpf) {
		setY(getY() + (int)(350*tpf));
		
		if(getY() >= 1000)
			((MyGame) getGame()).getEnemyFire().remove(this);
		
		ArrayList a = ((MyGame) getGame()).getAsteroidSprites();
		for(int i = 0; i < a.size(); i++){
			if(Collision.colorCollision(this, (Sprite) a.get(i))){
				((MyGame) getGame()).getEnemyFire().remove(this);
			}
		}
	}
	
}
