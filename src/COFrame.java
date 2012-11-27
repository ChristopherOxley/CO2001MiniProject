import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;



public class COFrame extends JFrame implements ActionListener {

	//------------------------------------------------------------------------------
	// variables
	//------------------------------------------------------------------------------

	private JLabel image;
	private JTextArea txtDescription;
	private JButton btnNorth, btnEast, btnSouth, btnWest, btnUp, btnDown;
	private JMenuItem itemSave, itemLoad, itemNewGame;
	private JLabel lblExploration;
	private COHighScoresTable HSTable;
	private final int startPosition = 0;
	private  JPanel mainView;
	private int currentPosition; // Initial player position
	
	// List of rooms stored in memory as CORoom objects
	private ArrayList<CORoom> rooms; 
	private HashSet<Integer> exploration;
	
	
	//------------------------------------------------------------------------------
	// constructors
	//------------------------------------------------------------------------------
	
	public COFrame(ArrayList<CORoom> rooms, int pos) {
	this.rooms = rooms;
	this.setBounds(new Rectangle(0,0,700,700));
	Container cp = getContentPane();

	setupLayoutmanagerAndPanels(cp);
	setupJMenuBar();					
	exploration = setupExplorationTracker(rooms);
	move(pos);
	}
	
	
	
	private void setupJMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		 itemLoad = new JMenuItem("Load");
		 itemSave = new JMenuItem("Save");
		 itemNewGame = new JMenuItem("New Game...");
		 
		 itemNewGame.addActionListener(this);
		 
		menuBar.add(fileMenu);
		
		fileMenu.add(itemLoad);
		fileMenu.add(itemSave);
		fileMenu.add(itemNewGame);
		
		this.setJMenuBar(menuBar);
		
	}
	
	
	private void move(int roomNumber){
		
		currentPosition = roomNumber;
		
		CORoom room = rooms.get(roomNumber);
		
		setImageWithFilename(room.getImageFileName());
		setDescription(room.getDescription());
		
		this.lblExploration.setText(exploreRoom(roomNumber) + "% explored");
		System.out.println(exploreRoom(roomNumber));
		
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
		
		
	}
	
	//------------------------------------------------------------------------------
	// UISetup
	//------------------------------------------------------------------------------
	
	private void setupLayoutmanagerAndPanels(Container cp){
		
		cp.setLayout(new BorderLayout());
		// Create panels
		
		int btnHeight = 50;
		int btnWidth = 50;

		// Add navigation buttons
		btnNorth = new JButton(this.loadScaledImageWithName("btnNorth.png", btnWidth, btnHeight));
		btnEast = new JButton(this.loadScaledImageWithName("btnEast.png", btnWidth, btnHeight));
		btnSouth = new JButton(this.loadScaledImageWithName("btnSouth.png", btnWidth, btnHeight));
		btnWest = new JButton(this.loadScaledImageWithName("btnWest.png", btnWidth, btnHeight));
		btnUp = new JButton(this.loadScaledImageWithName("btnUp.png", btnWidth, btnHeight));
		btnDown = new JButton(this.loadScaledImageWithName("btnDown.png", btnWidth, btnHeight));
		
		mainView = new JPanel();
		mainView.setLayout(null);
		cp.add(mainView);
	

		
		JPanel compass = new JPanel();
		compass.setLayout(new BorderLayout());
		mainView.add(compass);
		compass.add(btnNorth, BorderLayout.NORTH);
		compass.add(btnEast, BorderLayout.EAST);
		compass.add(btnSouth, BorderLayout.SOUTH);
		compass.add(btnWest, BorderLayout.WEST);
		compass.setBounds(300,300,btnWidth * 3,btnHeight * 3);
		
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
		mainView.add(elevation);
		elevation.add(btnUp, BorderLayout.NORTH);
		elevation.add(btnDown, BorderLayout.SOUTH);
		elevation.setBounds(500, 300, btnWidth, btnHeight * 3);
		
		// Adjust all buttons on the elevation panel
		for(Component button: elevation.getComponents()){
			JButton btn = (JButton)button;
			btn.setSize(btnWidth, btnHeight);
			btn.addActionListener(this);
			btn.setBorder(null);
		}
		

		
		
		// create a GUI holder for the image
		image = new JLabel();
		image.setBounds(200, 200, 400, 400);
		mainView.add(image);
		
		// create a text area for the description
		txtDescription = new JTextArea();
		mainView.add(txtDescription);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setEditable(false);
		txtDescription.setBounds(20,20,200,50);
		

		
		// Add exploration label
		lblExploration = new JLabel("0% Explored");
		cp.add(lblExploration, BorderLayout.SOUTH);
		

		/*
		HSTable = new COHighScoresTable();
		HSTable.setSize(700, 700);
		HSTable.setLayout(null);
		mainView.add(HSTable, 0);
		 */
	}	
	
	
	
//------------------------------------------------------------------------------
// 	Action Listener Delegate Method
//------------------------------------------------------------------------------
	
	public void actionPerformed(ActionEvent e) {
		
	// User selected the menu item "New Game"
	if(e.getSource() == itemNewGame ){
		if(currentPosition != 0){
			resetExploration(this.exploration);
			move(this.startPosition);
		}
	}
		
	
	
	if(e.getSource() == btnNorth){
		//HSTable.animateOn();

		move(this.rooms.get(currentPosition).getDirectionNorth());
	}
	if(e.getSource() == btnEast){
		move(this.rooms.get(currentPosition).getDirectionEast());

	}
	if(e.getSource() == btnSouth){
		move(this.rooms.get(currentPosition).getDirectionSouth());

	}
	if(e.getSource() == btnWest){
		move(this.rooms.get(currentPosition).getDirectionWest());

	}
	if(e.getSource() == btnUp){
		move(this.rooms.get(currentPosition).getDirectionUp());

	}
	if(e.getSource() == btnDown){
		move(this.rooms.get(currentPosition).getDirectionDown());

	}

		
	}
	
	
	public void setImageWithFilename(String fn){
		this.image.setIcon(loadImageWithName(fn));
	}
	
	public void setDescription(String text){
		this.txtDescription.setText(text);

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
	
	private ImageIcon loadScaledImageWithName(String name,int newWidth,int newHeight){
		
		
		Image scaledImage = loadImageWithName(name).getImage();
		scaledImage = scaledImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		ImageIcon icon = new ImageIcon(scaledImage);
		return icon;
		
	}
	
	private void resetExploration(HashSet<Integer> exploration ) {
		exploration = new HashSet<Integer>();
		
	}
	
	private int exploreRoom(int roomNumber){
		
		exploration.remove((Integer)roomNumber);
		
		for(Integer i: exploration){
			System.out.print(i+",");
		}
		System.out.print("\n");
		
		return (int)( 100 - ((  (float)(exploration.size()) / (float)(rooms.size()-1)) * 100));
		
	}

	private HashSet<Integer> setupExplorationTracker(ArrayList<CORoom> rms){
		HashSet <Integer> set = new HashSet<Integer> ();

		for (int i = 1; i < rms.size(); i++){
			set.add((Integer)i);
		}
		
		System.out.println("Set Size: "+set.size());
		
		return set;
		
	}
	

}
