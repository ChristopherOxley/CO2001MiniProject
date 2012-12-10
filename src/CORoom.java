
public class CORoom {

	private String description;
	private String imageFileName;
	private int directionNorth,directionEast, directionSouth, directionWest, directionUp, directionDown;
	private int roomNumber;
	
	public CORoom() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDescription(String d){
		this.description = d;
	}
	public String getDescription(){
		return this.description;
	}
	public void setImageFileName(String iName){
		this.imageFileName = iName;
	}
	public String getImageFileName(){
		return this.imageFileName;
	}
	public void setdirectionNorth(int direction){
		this.directionNorth = direction;
	}
	public int getDirectionNorth(){
		return this.directionNorth;
	}
	
	public void setdirectionSouth(int direction){
		this.directionSouth = direction;
	}
	public int getDirectionSouth(){
		return this.directionSouth;
	}
	
	public void setdirectionEast(int direction){
		this.directionEast = direction;
	}
	public int getDirectionEast(){
		return this.directionEast;
	}
	
	public void setdirectionWest(int direction){
		this.directionWest = direction;
	}
	public int getDirectionWest(){
		return this.directionWest;
	}
	public void setdirectionUp(int direction){
		this.directionUp = direction;
	}
	public int getDirectionUp(){
		return this.directionUp;
	}
	
	
	public void setdirectionDown(int direction){
		this.directionDown = direction;
	}
	public int getDirectionDown(){
		return this.directionDown;
	}


	
	public void print(){
		System.out.println("Description: " + this.description);
		System.out.println("Image File Name: " + this.imageFileName);
		System.out.println("North: " + this.directionNorth);
		System.out.println("East: " + this.directionEast);
		System.out.println("South: " + this.directionSouth);
		System.out.println("West: " + this.directionWest);
		System.out.println("Up: " + this.directionUp);
		System.out.println("Down: " + this.directionDown);
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

}
