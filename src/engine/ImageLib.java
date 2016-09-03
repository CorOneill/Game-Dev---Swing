package engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLib {
	private HashMap<String, BufferedImage> images;
	
	public ImageLib(){
		this.images = new HashMap<String, BufferedImage>();
	}
	
	public void loadImage(String pName, String pPath){
		BufferedImage image = null;
		URL pic_url = getClass().getClassLoader().getResource(pPath);
		
		try {
			image = ImageIO.read(pic_url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.images.put(pName, image);
	}
	
	public BufferedImage getImage(String pName){
		return this.images.get(pName);
	}
	
	public BufferedImage[] getImages(String pName, int pCount){
		BufferedImage[] imgs = new BufferedImage[pCount];
		for(int i = 1; i < pCount; i++){
			imgs[i-1] = this.getImage(pName + i);
		}
		return imgs;
	}
	
}
