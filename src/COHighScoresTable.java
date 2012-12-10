import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;




@SuppressWarnings("serial")
public  class COHighScoresTable extends JComponent implements ActionListener, MouseListener {
	Timer t;
	private JLabel title;
	private JPanel p;
	private ImageIcon image;
	private boolean animationComplete = false;
	private boolean animationReversing = false;
	private COFrame containerView;
	
	private Map<String, Integer> table;
	
	float scale = 0;
	

	
	public COHighScoresTable(COFrame cv){
		super.setLayout(null);
		image = loadImageWithName("highScores.png");
	    title = new JLabel("High Scores");
	 
	    p = new JPanel();
	    
	    containerView = cv;
	    
	    this.add(p);
	    p.add(title);
	    repaint();
	}

	public void animateOn(Map<String, Integer> tab){
		table = tab;
		t = new Timer(1, null);
		t.addActionListener(this);
	    this.addMouseListener(this);
		t.start();
		
	}
	
	public void animateOff(){
		this.removeMouseListener(this);
		t.start();
	}
	
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(animationComplete){
			scale -= 0.1;
		}else{
			scale += 0.1;
		}
		if(scale > 1){
			t.stop();
			animationComplete = true;
			animationReversing = false;
			scale = 1; 
		}
		if(scale < 0){
			t.stop();
			animationComplete=false;
			animationReversing=true;
			scale = 0;
			this.containerView.showSpeechBubble();
		}
		this.repaint();
		
		
	}
	

	
	   public void paint(Graphics g){
           super.paint(g);
           
           int selfWidth = this.getWidth();
           int selfHeight = this.getHeight();
           
           int bgWidth = (int)((image.getIconWidth()) * scale);
           int bgHeight = (int)((image.getIconHeight()) * scale);
           
           System.out.println("Painting");
           
           
           g.setColor(new Color(0,0,0,(int)(128 * scale)));
           g.fillRect(0, 0, this.getWidth(), this.getHeight());
           
           g.drawImage(image.getImage(), (int)(selfWidth/2 - bgWidth/2), (int)(selfHeight/2 - bgHeight/2), bgWidth, bgHeight, null);
		   
           String title = "High Scores";

           Font titleFont = new Font("Helvetica",Font.PLAIN,(int)(20 * scale));
           FontMetrics fontMetrics = this.getFontMetrics(titleFont);
           int titleWidth = fontMetrics.stringWidth(title);
		   int titleHeight = fontMetrics.getHeight();
		   

		   g.setFont(titleFont);
		   
		   g.setColor(Color.white);
	       g.drawString("High Scrores", selfWidth/2 - titleWidth/2, selfHeight/2 - bgHeight/2 + titleHeight + 30);
		   
		   int i=0;
		   if(table!=null){
	       for(String s: table.keySet()){
		       g.drawString(s, selfWidth/2 - titleWidth/2 - 20,(int)( selfHeight/2 - bgHeight/2 +titleHeight + 90 + (i * titleHeight) * scale));
		       g.drawString(""+table.get(s), selfWidth/2 + 50,(int)( selfHeight/2 - bgHeight/2 +titleHeight + 90 + (i * titleHeight) * scale));
		       i++;
	       }
		   } 
	       
	       
           
		   p.paint(g);
		   
	}
	   



	 
		private ImageIcon loadImageWithName(String name){
			
			java.io.InputStream in = getClass().getResourceAsStream(name); 
			ImageIcon icon = null;
			try {
				icon = new ImageIcon(ImageIO.read(in));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return icon;
			
		}
		
//------------------------------------------------------------------------------
// 		Mouse Listeners
//------------------------------------------------------------------------------
		@Override
		public void mouseClicked(MouseEvent me) {
			// TODO Auto-generated method stub
			animateOff();
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}




}
