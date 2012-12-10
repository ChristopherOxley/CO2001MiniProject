import java.util.ArrayList;
import java.util.HashSet;


public class COPLayer {

	private int currentPosition;
	private HashSet<Integer> exploration;
	private CORootController controller;
	private int moves;
	
	public COPLayer(CORootController controller) {
		this.setController(controller);
		exploration = setupExplorationTracker(controller.getRooms());
	}

	public int getCurrentPosition() {
		return currentPosition;
	}


	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}


	public HashSet<Integer> getExploration() {
		return exploration;
	}


	public void setExploration(HashSet<Integer> exploration) {
		this.exploration = exploration;
	}

	
	public void resetExploration(ArrayList<CORoom> rms) {
		this.exploration = setupExplorationTracker(rms);
	}
	
	// Returns the percentage explored
	public int exploreRoom(int roomNumber, ArrayList<CORoom> rooms){
	
		System.out.println("Removing Room: " +roomNumber);
		exploration.remove((Integer)roomNumber);
		System.out.println(exploration);
		return (int)( 100 - ((  (float)(exploration.size()) / (float)(rooms.size()-1)) * 100));
	}

	// creates a new HashSet containing all the room numbers to track the 
	// rooms left to explore.
	public HashSet<Integer> setupExplorationTracker(ArrayList<CORoom> rms){
		HashSet <Integer> set = new HashSet<Integer> ();
		for (int i = 1; i < rms.size(); i++){
			set.add((Integer)i);
		}		
		return set;
	}

	public CORootController getController() {
		return controller;
	}

	public void setController(CORootController controller) {
		this.controller = controller;
	}
	
	public void print(){
		System.out.println("Current Room: " + this.currentPosition);
		System.out.println("Rooms Remaining: " + this.exploration);
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

}
