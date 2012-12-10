import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.TabExpander;




@SuppressWarnings("serial")
public class COFrame extends JFrame implements ActionListener, MenuListener {

	//------------------------------------------------------------------------------
	// variables
	//------------------------------------------------------------------------------

	private JLabel image;
	private JButton btnNorth, btnEast, btnSouth, btnWest, btnUp, btnDown, btnHighScores;
	private JMenuItem itemSave, itemLoad, itemNewGame;
	private JLabel lblExploration;
	private COHighScoresTable HSTable;
	private  JPanel mainView;
	private final static int BORDER_SIZE = 5;
	private CORootController controller;
	private COSpeechBubble speech;
	private JMenu fileMenu;
	// List of rooms stored in memory as CORoom objects
	
	
	//------------------------------------------------------------------------------
	// constructors
	//------------------------------------------------------------------------------
	
	public COFrame(CORootController controller) {
	
		this.setTitle("Adventure Game by Christopher Oxley");
		this.setBounds(new Rectangle(0,0,700,800));
		this.setResizable(false);
		Container cp = getContentPane();
		setController(controller);
		setupLayoutmanagerAndPanels(cp);
		setupJMenuBar();
		refreshView();
	}
	
	
	
	private void setupJMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		itemLoad = new JMenuItem("Load");
		itemSave = new JMenuItem("Save");
		itemNewGame = new JMenuItem("New Game...");
		itemNewGame.addActionListener(this);
		menuBar.add(fileMenu);
		fileMenu.addMenuListener(this);
		fileMenu.add(itemLoad);
		fileMenu.add(itemSave);
		fileMenu.add(itemNewGame);
		this.setJMenuBar(menuBar);
	}
	
	private void refreshView(){
		
		CORoom room  = controller.getRooms().get(controller.getPlayerPosition());
		
		if(room.getDirectionNorth() == -1){
			btnNorth.setEnabled(false);
		}else{
			btnNorth.setEnabled(true);
		}
		if(room.getDirectionEast() == -1){
			btnEast.setEnabled(false);
		}else{
			btnEast.setEnabled(true);
		}
		if(room.getDirectionSouth() == -1){
			btnSouth.setEnabled(false);
		}else{
			btnSouth.setEnabled(true);
		}
		if(room.getDirectionWest() == -1){
			btnWest.setEnabled(false);
		}else{
			btnWest.setEnabled(true);
		}
		if(room.getDirectionUp() == -1){
			btnUp.setEnabled(false);
		}else{
			btnUp.setEnabled(true);
		}
		if(room.getDirectionDown() == -1){
			btnDown.setEnabled(false);
		}else{
			btnDown.setEnabled(true);
		}
		
		
		setImageWithFilename(room.getImageFileName());
		setDescription(room.getDescription());
		
		
		
		this.lblExploration.setText(controller.getPercentageExplored() + "% explored");
		
	}

	//------------------------------------------------------------------------------
	// UISetup
	//------------------------------------------------------------------------------
	
	private void setupLayoutmanagerAndPanels(Container cp){
		
		int frameWidth = this.getWidth();
		//int frameHeight = this.getHeight();
		
		cp.setLayout(new BorderLayout());

		mainView = new JPanel();
		mainView.setLayout(null);
		cp.add(mainView);

		
		
		//Speech Bubble
		
		speech = new COSpeechBubble();
		speech.setBounds(new Rectangle(BORDER_SIZE - 600/2 + this.getWidth()/2 - BORDER_SIZE/2, this.getHeight() - 260, 600, 193));
		speech.setText("This Is Some Text");
		ImageIcon bg = COImageProcessor.loadImageWithName("Comment.png");
		speech.setBackgroundImage(bg);
		mainView.add(speech);
		
		
		

		
	
		
		// create a GUI holder for the image
		image = new JLabel();
		
		int imageWidth = 480;
		int imageHeight = 360;
		image.setBounds(BORDER_SIZE - imageWidth/2 + this.getWidth()/2 - BORDER_SIZE/2, BORDER_SIZE, imageWidth, imageHeight);
		mainView.add(image);
		
		int btnHeight = 50;
		int btnWidth = 50;

		// Add navigation buttons
		btnNorth = new JButton(COImageProcessor.loadScaledImageWithName("btnNorth.png", btnWidth, btnHeight));
		btnEast = new JButton(COImageProcessor.loadScaledImageWithName("btnEast.png", btnWidth, btnHeight));
		btnSouth = new JButton(COImageProcessor.loadScaledImageWithName("btnSouth.png", btnWidth, btnHeight));
		btnWest = new JButton(COImageProcessor.loadScaledImageWithName("btnWest.png", btnWidth, btnHeight));
		btnUp = new JButton(COImageProcessor.loadScaledImageWithName("btnUp.png", btnWidth, btnHeight));
		btnDown = new JButton(COImageProcessor.loadScaledImageWithName("btnDown.png", btnWidth, btnHeight));
		
		JPanel compass = new JPanel();
		compass.setLayout(new BorderLayout());
		compass.add(btnNorth, BorderLayout.NORTH);
		compass.add(btnEast, BorderLayout.EAST);
		compass.add(btnSouth, BorderLayout.SOUTH);
		compass.add(btnWest, BorderLayout.WEST);
		compass.setBounds(0,0,btnWidth * 3,btnHeight * 3);
		
		// Adjust all buttons on the compass panel
		for(Component button: compass.getComponents()){
			JButton btn = (JButton)button;
			btn.setSize(btnWidth, btnHeight);
			btn.addActionListener(this);
			btn.setBorder(null);
		}
		
		// Set up down controls
		JPanel elevation = new JPanel();
		elevation.setLayout(new BorderLayout());
		elevation.add(btnUp, BorderLayout.NORTH);
		elevation.add(btnDown, BorderLayout.SOUTH);
		elevation.setBounds(0, 0, btnWidth, btnHeight * 3);
		
		
		// Adjust all buttons on the elevation panel
		for(Component button: elevation.getComponents()){
			JButton btn = (JButton)button;
			btn.setSize(btnWidth, btnHeight);
			btn.addActionListener(this);
			btn.setBorder(null);
		}
		
		
		JPanel controls = new JPanel();
		controls.setLayout(new BorderLayout());
		controls.add(compass, BorderLayout.WEST);
		controls.add(elevation, BorderLayout.EAST);
		int controlsX = image.getX();
		int controlsWidth = imageWidth;
		controls.setBounds(controlsX, image.getY() + image.getHeight() + 10, controlsWidth, elevation.getHeight());
		mainView.add(controls);
		
		
		// High Scores Button
		JPanel controlsCenterPanel = new JPanel();
		controls.add(controlsCenterPanel);
		btnHighScores = new JButton("High Scores");
		btnHighScores.setPreferredSize(new Dimension(100, 50));
		btnHighScores.addActionListener(this);
		controlsCenterPanel.add(btnHighScores);

		// Add exploration label
		lblExploration = new JLabel("0% Explored");
		JPanel expPanel = new JPanel();
		expPanel.setLayout(new FlowLayout());
		expPanel.add(lblExploration);
		cp.add(expPanel, BorderLayout.SOUTH);

		

		
		HSTable = new COHighScoresTable(this);
		HSTable.setSize(this.getWidth(), this.getHeight()-50);
		HSTable.setLayout(null);
		mainView.add(HSTable, 0);
		 
	}	
	
	
	
//------------------------------------------------------------------------------
// 	Action Listener Delegate Method
//------------------------------------------------------------------------------
	
	public void actionPerformed(ActionEvent e) {
		
		// User selected the menu item "New Game"
		if(e.getSource() == itemNewGame ){
			if(controller.getPlayerPosition() != 0){
				controller.resetPlayerPosition();
				controller.resetPlayerExploration();
			}
		}
		
		if (e.getSource() == fileMenu) {
			HSTable.animateOff();
			System.out.println("Selected");
		}
		
		if (e.getSource() == btnHighScores) {
			this.hideSpeechBubble();
			
			Map<String, Integer> table = new HashMap<String, Integer>();
			table.put("aaa", new Integer(23));
			table.put("bbb", new Integer(29));
			table.put("ccc", new Integer(42));
			table.put("ddd", new Integer(64));
			
			table.put("eee", new Integer(23));
			table.put("fff", new Integer(29));
			table.put("ggg", new Integer(42));
			table.put("hhh", new Integer(64));
			
			table.put("iii", new Integer(23));
			table.put("jjj", new Integer(29));
			table.put("kkk", new Integer(42));
			table.put("lll", new Integer(64));
			
			table.put("mmm", new Integer(23));
			table.put("nnn", new Integer(29));
			table.put("ooo", new Integer(42));
			table.put("ppp", new Integer(64));
			
			table.put("qqq", new Integer(23));
			table.put("rrr", new Integer(29));
			table.put("sss", new Integer(42));
			table.put("ttt", new Integer(64));
			
			HSTable.animateOn(table);
		}
	
		if(e.getSource() == btnNorth){
			controller.movePlayer("N");
		}
		if(e.getSource() == btnEast){
			controller.movePlayer("E");
		}
		if(e.getSource() == btnSouth){
			controller.movePlayer("S");
		}
		if(e.getSource() == btnWest){
			controller.movePlayer("W");
		}
		if(e.getSource() == btnUp){
			controller.movePlayer("U");
		}
		if(e.getSource() == btnDown){
			controller.movePlayer("D");
		}
		
		refreshView();
	}
	
	
	public void setImageWithFilename(String fn){
		this.image.setIcon(COImageProcessor.loadImageWithName(fn));
	}
	

	
	
	public void setDescription(String text){
		//this.txtDescription.setText(text);
		speech.setText(text);
		this.repaint();
	}



	public CORootController getController() {
		return controller;
	}



	public void setController(CORootController controller) {
		this.controller = controller;
	}
	

	public void showSpeechBubble(){
		this.speech.setVisible(true);
	}
	
	public void hideSpeechBubble(){
		this.speech.setVisible(false);
	}



	@Override
	public void menuCanceled(MenuEvent arg0) {
		
	}



	@Override
	public void menuDeselected(MenuEvent arg0) {
		
	}



	@Override
	public void menuSelected(MenuEvent arg0) {
		
		HSTable.repaint();
	//	HSTable.animateOff();
	}






}
