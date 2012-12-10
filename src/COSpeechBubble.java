import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class COSpeechBubble extends JComponent{

	private ImageIcon backgroundImage;
	private String text;
	private JTextPane textArea;
	
	public COSpeechBubble() {

		this.setBackground(new Color(0, 0, 0, 0));
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		g.drawImage(this.backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		textArea.repaint();		
	}
	
	
	
	//*********************************** 
	// Getters / Setters
	//***********************************

	public String getText() {
		return text;
	}

	public void setText(String text) {
		

		this.text = text;
		
		if(this.textArea!=null){
			this.remove(textArea);
			this.textArea = null;
		}
		this.textArea = new JTextPane();
				
		this.textArea.setBounds(new Rectangle(30, 50, (int)(this.getWidth() -60), 50));
		this.add(textArea);
		textArea.setEditable(false);
		textArea.setBackground(new Color(0,0,0,0));

		// Boilerplate to create centered text in a JTextPane3
		StyledDocument style = textArea.getStyledDocument();
		SimpleAttributeSet cent = new SimpleAttributeSet();
		StyleConstants.setAlignment(cent, StyleConstants.ALIGN_CENTER);
		style.setParagraphAttributes(0, style.getLength(), cent, false);
		
		textArea.setText(text);

		
		repaint();
	}

	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(ImageIcon backgroundImage) {
		this.backgroundImage = backgroundImage;
		repaint();
	}

	
	
}
