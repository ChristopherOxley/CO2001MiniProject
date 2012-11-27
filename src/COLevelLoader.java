import java.io.*;
import java.util.*;


// TODO: load levels from file

public class COLevelLoader {

	private BufferedReader bufferedReader;
	private ArrayList<CORoom> rooms;
	final static int levelLines = 8;
	
	public COLevelLoader(){
		
	}
	
	public COLevelLoader(String fileName) {
		
		try {
		    bufferedReader = new BufferedReader(new FileReader("src/rooms.txt"));
		}
		catch(Exception e){
		try{
		    bufferedReader = new BufferedReader(new FileReader("rooms.txt"));
		}catch(Exception ex){}
		finally{}

			e.printStackTrace();
		}
		finally{
		}
		rooms = parseFileForRooms(bufferedReader);
		
	}
	
	public ArrayList<CORoom> getRooms(){
		return this.rooms;
	}
	
	private ArrayList<CORoom> parseFileForRooms(BufferedReader br){
		
		ArrayList <CORoom> rooms = new ArrayList<CORoom>();
		
	    try {
	        String line = br.readLine();

	        int currentLine = 0;
	        int count = 0;
        	CORoom room = new CORoom();


	        while (line != null) {
	        	switch(count){
	        	
	        	case 0:
	        		room = new CORoom();
	        		room.setDescription(line);
	        		break;
	        		
	        	case 1:
	        		room.setImageFileName(line);
	        		break;
	  
	        	case 2:
	        		room.setdirectionNorth(Integer.valueOf(line));
	        		break;
	        		
	        	case 3:
	        		room.setdirectionEast(Integer.valueOf(line));

	        		break;
	        		
	        	case 4:
	        		room.setdirectionSouth(Integer.valueOf(line));

	        		break;
	        		
	        	case 5:
	        		room.setdirectionWest(Integer.valueOf(line));

	        		break;
	        		
	        	case 6:
	        		room.setdirectionUp(Integer.valueOf(line));

	        		break;
	        		
	        	case 7:
	        		room.setdirectionDown(Integer.valueOf(line));
		        	rooms.add(room);

	        		break;
	        		
	        	case 8:

	        		break;
	        		
	        	}
	            currentLine++;
	            count++;
	            if (currentLine%levelLines == 0){
	            	count = 0;
	            }
	            line = br.readLine();

	        }
	    }
	    catch(Exception e){
	    }
	    finally {
	       try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }		
		
	    return rooms;
	}
	
	public void printLevelFile(){
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = this.bufferedReader.readLine();

	        while (line != null) {
	            sb.append(line);
	            System.out.println(line);		            
	            line = bufferedReader.readLine();
	        }
	    }
	    catch(Exception e){
	    }
	    finally {
	       try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		
		
	}
	


}
