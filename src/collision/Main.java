package collision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import engine.Collision;
import engine.Game;
import engine.Sprite;

public class Main extends Game implements MouseMotionListener{
	private int mouseX, mouseY;
	
	private CircleSprite circle, myCircle;
	private RectangleSprite rectangle, myRectangle, rahmen;
	private Sprite redStar, greenStar;
	private int option;
	
	private Sprite flappy;
	public Main(){
		this.start();
	}
	
	@Override
	public void init() {
		this.addMouseMotionListener(this);
		this.circle = new CircleSprite(250, 400, 50, this);
		this.rectangle = new RectangleSprite(450, 300, 60, 120, this);
		this.myCircle = new CircleSprite(0, 0, 50, this);
		this.myRectangle = new RectangleSprite(0, 0, 50, 80, this);
		this.rahmen = new RectangleSprite(-1000, -1000, 216, 207, this);
		this.getImageLib().loadImage("redStar", "collision/pics/redStar.png");
		this.getImageLib().loadImage("greenStar", "collision/pics/greenStar.png");
		this.redStar = new Sprite(600, 500, getImageLib().getImage("redStar"), this);
		this.greenStar = new Sprite(0, 0, getImageLib().getImage("greenStar"), this);
		this.option = 1;
	}

	@Override
	public void logic(float tpf) {
		super.logic(tpf);
		if(option == 1 && myCircle != null){
			this.myCircle.setX(mouseX);
			this.myCircle.setY(mouseY);
			if(Collision.CircleCircleCollision(myCircle, circle) || Collision.RectangleCircleCollision(rectangle, myCircle))
				myCircle.setColor(Color.RED);
			else
				myCircle.setColor(Color.BLACK);
		}
		if(option == 2 && myRectangle != null){
			this.myRectangle.setX(mouseX);
			this.myRectangle.setY(mouseY);
			if(Collision.rectangleRectangleCollision(myRectangle, rectangle) || Collision.RectangleCircleCollision(myRectangle, circle))
				myRectangle.setColor(Color.RED);
			else
				myRectangle.setColor(Color.BLACK);
		}
		if(option == 3 && greenStar != null){
			this.greenStar.setX(mouseX);
			this.greenStar.setY(mouseY);
			this.rahmen.setX(-1000);
			this.rahmen.setY(-1000);
			if(Collision.colorCollision(greenStar, redStar)){
				this.rahmen.setX(600);
				this.rahmen.setY(500);
			}
				//				myRectangle.setColor(Color.RED);
//			else
//				myRectangle.setColor(Color.BLACK);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("PRESS 1 OR 2 OR 3", 200, 20);
		if(circle != null)
			this.circle.render(g);
		if(rectangle != null)
			this.rectangle.render(g);
		if(rahmen != null)
			rahmen.render(g);
		if(redStar != null)
			this.redStar.render(g);
		if(option == 1 && myCircle != null)
			this.myCircle.render(g);
		if(option == 2 && myRectangle != null)
			this.myRectangle.render(g);
		if(option == 3 && greenStar != null)
			this.greenStar.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1)
			option = 1;
		else if(e.getKeyCode() == KeyEvent.VK_2)
			option = 2;
		else if(e.getKeyCode() == KeyEvent.VK_3)
			option = 3;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public static void main(String[] args){
		new Main();
	}
}
