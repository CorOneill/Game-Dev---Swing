package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.Collision;
import engine.Game;
import engine.Sprite;

public class MyGame extends Game{
	
	private Sprite player;
	
	private boolean right, left, fire;
	private boolean dead;
	private float spawnTimer, shotTimer, timer, deadTimer;
	private int kills;
	
	private ArrayList<Sprite> asteroidSprites;
	private ArrayList<Sprite> alienSprites;
	private ArrayList<Sprite> friendlyFire;
	private ArrayList<Sprite> enemyFire;
	private ArrayList<Sprite> explosions;
	
	public MyGame(){
		this.start();
	}
	
	@Override
	public void init() {
		this.getImageLib().loadImage("background", "pics/background.png");
		this.getImageLib().loadImage("player", "pics/spaceship.png");
		this.getImageLib().loadImage("asteroid1", "pics/asteroid1.png");
		this.getImageLib().loadImage("asteroid2", "pics/asteroid2.png");
		this.getImageLib().loadImage("asteroid3", "pics/asteroid3.png");
		this.getImageLib().loadImage("ufo", "pics/ufo.png");
		this.getImageLib().loadImage("friendlyFire", "pics/friendlyFire.png");
		this.getImageLib().loadImage("enemyFire", "pics/alienFire.png");
		
		for(int i = 1; i < 9; i++){
			this.getImageLib().loadImage("explosion" + i, "pics/explosion" + i + ".png");
		}
		
		this.getSoundLib().loadSound("laser", "sounds/laser.wav");
		this.getSoundLib().loadSound("explosion", "sounds/explosion.wav");
		
		
		this.player = new Sprite(650, 600, getImageLib().getImage("player"), this);
	
		this.right = false;
		this.left = false;
		this.fire = false;
		
		this.spawnTimer = 0;
		this.timer = 0;
		this.kills = 0;
		
		
		this.asteroidSprites = new ArrayList<Sprite>();
		this.alienSprites = new ArrayList<Sprite>();
		this.friendlyFire = new ArrayList<Sprite>();
		this.enemyFire = new ArrayList<Sprite>();
		this.explosions = new ArrayList<Sprite>();
		
		this.dead = false; 
		this.setRunning(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(!isRunning())return;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1300, 850);
		g.drawImage(this.getImageLib().getImage("background"), 0, 0, null);
		
		for(int i = 0; i < asteroidSprites.size(); i++){
			asteroidSprites.get(i).render(g);
		}
		
		for(int i = 0; i < alienSprites.size(); i++){
			alienSprites.get(i).render(g);
		}
		
		for(int i = 0; i < friendlyFire.size(); i++){
			friendlyFire.get(i).render(g);
		}
		
		for(int i = 0; i < enemyFire.size(); i++){
			enemyFire.get(i).render(g);
		}
		
		for(int i = 0; i < explosions.size(); i++){
			explosions.get(i).render(g);
		}
		
		if(player != null)
			player.render(g);
		
		g.setColor(Color.WHITE);
		g.drawString("Timer: " + timer, 20, 20);
		g.drawString("Kills: " + kills, 1200, 20);
	}
	
	
	
	
	@Override
	public void logic(float tpf) {
		if(!isRunning())return;
		
		if(dead){
			deadTimer += tpf;
			if(deadTimer >= 3){
				init();
			}
			else{
				for(int i = 0; i < 15; i++){
					explosions.add(new Explosion((int) (Math.random()*1300)-50, (int) (Math.random()*800)-50, getImageLib().getImages("explosion", 9), this));
				}
			}
		}
		
		timer += tpf;
		
		
		spawnTimer += tpf;
		if(spawnTimer >= 5){System.out.println((int) (Math.random()*3) + 1);
			AlienSprite als = new AlienSprite((int)(Math.random()*1300), -500 + (int)(Math.random() * 400), getImageLib().getImage("ufo"), this);
			alienSprites.add(als);		
			
			AsteroidSprite as = new AsteroidSprite((int)(Math.random()*1300), -500 + (int)(Math.random() * 400), getImageLib().getImage("asteroid" + (int) ((Math.random() * 3) + 1)), this);
			asteroidSprites.add(as);
			spawnTimer = 0;
		}
		
		
		for(int i = 0; i < asteroidSprites.size(); i++){
			asteroidSprites.get(i).logic(tpf);
		}
		
		for(int i = 0; i < alienSprites.size(); i++){
			alienSprites.get(i).logic(tpf);
		}
		
		for(int i = 0; i < friendlyFire.size(); i++){
			friendlyFire.get(i).logic(tpf);
		}
		
		for(int i = 0; i < enemyFire.size(); i++){
			enemyFire.get(i).logic(tpf);
		}
		
		for(int i = 0; i < explosions.size(); i++){
			explosions.get(i).logic(tpf);
		}
		
		if(left && player.getX() >= getX())
			player.setX(player.getX() - (int) (200*tpf));
		if(right && player.getX() + player.getWidth() <= getWidth())
			player.setX(player.getX() + (int) (200*tpf));
		
		
		shotTimer+=tpf;
		if(shotTimer >= 0.5){
			if(fire){
				FriendlyFireSprite ff = new FriendlyFireSprite(player.getX() + player.getWidth()/2, player.getY() - player.getHeight()/2, getImageLib().getImage("friendlyFire"), this);
				friendlyFire.add(ff);
				getSoundLib().playSound("laser");
				shotTimer = 0;
			}
		}
		
		ArrayList<Sprite> objects = new ArrayList<Sprite>();
		objects.addAll(asteroidSprites);
		objects.addAll(alienSprites);
		objects.addAll(enemyFire);
		
		
		for(int i = 0; i < objects.size(); i++){
			if(Collision.colorCollision(player, objects.get(i))){
				if(dead)return;
				dead = true;
			}
		}
		
	}

	public Sprite getPlayer(){
		return this.player;
	}
	
	public ArrayList<Sprite> getAlienSprites() {
		return alienSprites;
	}

	public ArrayList<Sprite> getAsteroidSprites() {
		return asteroidSprites;
	}

	public ArrayList<Sprite> getFriendlyFire() {
		return friendlyFire;
	}

	public ArrayList<Sprite> getEnemyFire() {
		return enemyFire;
	}

	public ArrayList<Sprite> getExplosions() {
		return explosions;
	}

	public void incKills(){
		kills++;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if(e.getKeyCode() == KeyEvent.VK_D)
			right = true;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			fire = true;
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			setRunning(!isRunning());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if(e.getKeyCode() == KeyEvent.VK_D)
			right = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			fire = false;
	}

	public static void main(String[] args){
		new MyGame();
	}
	
}
