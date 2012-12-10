import java.util.*;


public class CORootController {

	private ArrayList<CORoom> rooms; 
	private COPLayer player;

	
	public CORootController() {
		
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		COLevelLoader loader = new COLevelLoader("rooms.txt");
		
		// Load the list of rooms from the text file
		ArrayList<CORoom> rooms = loader.getRooms();
		
		CORootController controller = new CORootController();
		controller.setRooms(rooms);
		controller.createNewPlayer();
		
		COFrame frame = new COFrame(controller);
		frame.setVisible(true);
	}

	public void createNewPlayer(){
		setPlayer(new COPLayer(this));
	}
	
	// Test method
	public static void printLoadedRooms(ArrayList<CORoom> rooms, Boolean spaced){
		for(CORoom rm: rooms){
			if(spaced){
				System.out.print("\n");
			}
			rm.print();
		}
		
	}
	
	public int getPercentageExplored(){
		
		float roomsRemaining = player.getExploration().size();
	
		float totalRooms = this.rooms.size()-1;
	
	
		
		int percentage = 100 - (int)((roomsRemaining/totalRooms) * 100);
		
		return percentage;
	
	}
	
	public void resetPlayerPosition(){
		player.setCurrentPosition(0);
	}
	
	public void resetPlayerExploration(){
		 player.resetExploration(rooms);
	}
	
	public int getPlayerPosition(){
		return this.getPlayer().getCurrentPosition();
	}
	
	public int exploreRoom(int n){
		return this.getPlayer().exploreRoom(n, rooms);
	}

	public COPLayer getPlayer() {
		return player;
	}


	public void setPlayer(COPLayer player) {
		this.player = player;
	}


	public ArrayList<CORoom> getRooms() {
		return rooms;
	}


	public void setRooms(ArrayList<CORoom> rooms) {
		this.rooms = rooms;
	}
	
	public void movePlayer(String direction){
		
		int nextRoom = 0;
		
		if(direction.equals("N")){
			//HSTable.animateOn();
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionNorth();
		}
		if(direction.equals("E")){
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionEast();
		}
		if(direction.equals("S")){
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionSouth();
		}
		if(direction.equals("W")){
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionWest();
		}
		if(direction.equals("U")){
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionUp();
		}
		if(direction.equals("D")){
			nextRoom = this.getRooms().get(this.getPlayerPosition()).getDirectionDown();
		}	
		this.getPlayer().setCurrentPosition(nextRoom);
		this.getPlayer().exploreRoom(nextRoom, rooms);
	}
}
