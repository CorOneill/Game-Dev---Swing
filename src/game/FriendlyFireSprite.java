package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.Collision;
import engine.Game;
import engine.Sprite;

public class FriendlyFireSprite extends Sprite{

	public FriendlyFireSprite(int pX, int pY, BufferedImage pImage, Game pGame) {
		super(pX, pY, pImage, pGame);
		
	}

	@Override
	public void logic(float tpf) {
		setY(getY() - (int)(350*tpf));
		
		if(getY() <= -20)
			((MyGame) getGame()).getFriendlyFire().remove(this);
		
		ArrayList as = ((MyGame) getGame()).getAlienSprites();
		for(int i = 0; i < as.size(); i++){
			if(Collision.colorCollision(this, (Sprite) as.get(i))){
				Sprite s = (Sprite) as.get(i);
				((MyGame) getGame()).getFriendlyFire().remove(this);
				((MyGame) getGame()).getSoundLib().playSound("explosion");
				((MyGame) getGame()).getExplosions().add(new Explosion(s.getX()-s.getWidth()/2, s.getY()-s.getHeight()/2, ((MyGame) getGame()).getImageLib().getImages("explosion", 9), ((MyGame) getGame())));
				as.remove(i);
				((MyGame) getGame()).incKills();
			}
		}
		
		ArrayList a = ((MyGame) getGame()).getAsteroidSprites();
		for(int i = 0; i < a.size(); i++){
			if(Collision.colorCollision(this, (Sprite) a.get(i))){
				((MyGame) getGame()).getFriendlyFire().remove(this);
			}
		}
		
	}
	
}
