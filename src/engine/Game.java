package engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener{
	private Frame frame;
	private long delta, last, fps;
	
	private ImageLib imageLib;
	private SoundLib soundLib;
	
	private boolean isRunning;
	
	public void start(){
		last = System.nanoTime();
		
		this.frame = new Frame(200, 100, 1300, 850);
		this.frame.addKeyListener(this);
		this.frame.add(this);
		this.setBounds(0, 0, 1300, 850);
		
		this.imageLib = new ImageLib();
		this.soundLib = new SoundLib();
		
		this.init();
		
		new Thread(){
			public void run(){
				while(true){
					
					computeDelta();
					logic((float) (delta/1e9));
					repaint();
					
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void init(){
		
	}
	
	public void logic(float tpf){
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("FPS: " + this.fps, 20, 20);
	}
	
	public void computeDelta(){
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9)/delta;
	}

	public ImageLib getImageLib() {
		return imageLib;
	}

	public SoundLib getSoundLib() {
		return soundLib;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
