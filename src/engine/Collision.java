package engine;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import collision.CircleSprite;
import collision.RectangleSprite;

public class Collision {
	
	public static boolean rectangleRectangleCollision(Sprite s1, Sprite s2){
		return (s1.getX() <= s2.getX() + s2.getWidth() &&
				s1.getX() + s1.getWidth() >= s2.getX() &&
				s1.getY() <= s2.getY() + s2.getHeight() &&
				s1.getY() + s1.getHeight() >= s2.getY());
	}	
	
	/*
	public static boolean rectangleRectangleCollision(Sprite pSprite1, Sprite pSprite2){
		if(pSprite1.getX() <= pSprite2.getX() + pSprite2.getWidth()){
			if(pSprite1.getX() + pSprite1.getWidth() >= pSprite2.getX()){
				if(pSprite1.getY() <= pSprite2.getY() + pSprite2.getHeight()){
					if(pSprite1.getY() + pSprite1.getHeight() >= pSprite2.getY()){
						return true;
					}
				}
			}
		}
		return false;
	}	
	*/
	
	public static boolean colorCollision(Sprite s1, Sprite s2){
		Rectangle2D.Double r1 = new Rectangle2D.Double(s1.getX(), s1.getY(), s1.getWidth(), s1.getHeight());
		Rectangle2D.Double r2 = new Rectangle2D.Double(s2.getX(), s2.getY(), s2.getWidth(), s2.getHeight());
		
		Rectangle2D.Double intersection = (Double) r1.createIntersection(r2);
		
		if(intersection.width < 1 || intersection.height < 1){
			return false;
		}
		
		r1 = getSubRec(r1, intersection);
		r2 = getSubRec(r2, intersection);
		
		BufferedImage subImg1 = s1.getImage().getSubimage((int) r1.x, (int) r1.y, (int) r1.width, (int) r1.height);
		BufferedImage subImg2 = s2.getImage().getSubimage((int) r2.x, (int) r2.y, (int) r2.width, (int) r2.height);
		
		for(int i = 0; i < subImg1.getWidth(); i++){
			for(int i2 = 0; i2 < subImg2.getHeight(); i2++){
				int rgb1 = subImg1.getRGB(i, i2);
				int rgb2 = subImg2.getRGB(i, i2);
				
				if(isOpaque(rgb1) && isOpaque(rgb2)){
					return true;
				}
				
			}
		}
		return false;
	}
	
	public static Rectangle2D.Double getSubRec(Rectangle2D.Double rec, Rectangle2D.Double intersection){
		Rectangle2D.Double subRec = new Rectangle2D.Double();
		if(rec.x > intersection.x)
			subRec.x = 0;
		else
			subRec.x = intersection.x - rec.x;
		
		if(rec.y > intersection.y)
			subRec.y = 0;
		else
			subRec.y = intersection.y - rec.y;
		
		subRec.height = intersection.height;
		subRec.width = intersection.width;
		
		return subRec;	
	}
	
	public static boolean isOpaque(int rgb){
		
		System.out.println(rgb);
		System.out.println(Integer.toBinaryString(rgb));
		
		int alpha = (rgb >> 24);
		
		if(alpha == 0){
			return false;
		}
		
		return true;
	}
	
	//--------------------------------------------------------
	
	
	public static boolean CircleCircleCollision(CircleSprite pC1, CircleSprite pC2){
		int x = pC2.getX() - pC1.getX();
		int y = pC2.getY() - pC1.getY();
		int radiusSum = pC1.getRadius() + pC2.getRadius();
		
		int distCirclesSqr = x*x+y*y;
		int radiusSumSqr = radiusSum * radiusSum;
		return (distCirclesSqr <= radiusSumSqr);
	}
	
	
	public static boolean RectangleCircleCollision(RectangleSprite pRectangle, CircleSprite pCircle){		
		int circleDistX = Math.abs(pCircle.getX() - (pRectangle.getX() + pRectangle.getWidth()/2));
		int circleDistY = Math.abs(pCircle.getY() - (pRectangle.getY() + pRectangle.getHeight()/2));
		
		if(circleDistX > (pRectangle.getWidth()/2 + pCircle.getRadius())) return false;
		if(circleDistY > (pRectangle.getHeight()/2 + pCircle.getRadius())) return false;
		
		if(circleDistX <= (pRectangle.getWidth()/2)) return true;
		if(circleDistY <= (pRectangle.getHeight()/2)) return true;
		
		double cornerDistSqr = Math.pow(circleDistX-pRectangle.getWidth()/2, 2) + Math.pow(circleDistY-pRectangle.getHeight()/2, 2);
		return (cornerDistSqr <= pCircle.getRadius()*pCircle.getRadius());
	}
	/**/
}
