import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class COImageProcessor {
	
	public static ImageIcon loadImageWithName(String name){
	
		java.io.InputStream in = COImageProcessor.class.getResourceAsStream(name); 
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(in));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return icon;
		
	}
	
	public static ImageIcon loadScaledImageWithName(String name,int newWidth,int newHeight){
	
		Image scaledImage = loadImageWithName(name).getImage();
		scaledImage = scaledImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImage);
		return icon;
	}
}
