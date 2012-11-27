import java.util.*;


public class RootController {

	public RootController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		COLevelLoader loader = new COLevelLoader("rooms.txt");
		
		// Load the list of rooms from the text file
		ArrayList<CORoom> rooms = loader.getRooms();
		
		COFrame frame = new COFrame(rooms, 0);
		frame.setVisible(true);

		
		// Just a test to check the rooms
		//printLoadedRooms(rooms, true);

	}

	public static void printLoadedRooms(ArrayList<CORoom> rooms, Boolean spaced){
		for(CORoom rm: rooms){
			if(spaced){
				System.out.print("\n");
			}
			rm.print();
		}
		
	}
	
}
