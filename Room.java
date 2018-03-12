package jri.agile.game;

public class Room {

	private int row;
	private int col;
	private int buildingNumber;
	private RoomType roomType;
	private BoardPosition roomPosition;
	private boolean hasBats = false;
	private boolean hasPit = false;
	private boolean hasWall = false;
	private boolean hasExit = false;
	private boolean hasSpawn = false;
	private boolean hasDoor = false;
	private boolean hasObjective = false;
	private boolean empty = false;
	private boolean visited = false;
	private boolean isBuildingRoom = false;
	private int numArrows = 0;
	
	public enum RoomType {
		Normal, BatRoom, PitRoom, NoRoom, SpawnRoom, Door, BuildingRoom, ObjectiveRoom, ExitRoom
	}
	
	public Room (int row, int col, RoomType roomType, int buildingNumber, boolean empty) {
		this.row = row;
		this.col = col;
		this.buildingNumber = buildingNumber;
		this.empty = empty;
		this.visited = visited;
		roomPosition = new BoardPosition(row, col);
		
		switch (roomType) {
			case BatRoom:
				hasBats = true;
				break;
			case PitRoom:
				hasPit = true;
				break;
			case NoRoom:
				hasWall = true;
				break;
			case SpawnRoom:
				hasSpawn = true;
				break;
			case Door:
				hasDoor = true;
				break;
			case BuildingRoom:
				isBuildingRoom = true;
				break;
			case ObjectiveRoom:
				hasObjective = true;
				break;
			case ExitRoom:
				hasExit = true;
				break;
			default:
				break;
		}
	}
	
	
	public int getRow () {
		return row;
	}
	
	public int getColumn () {
		return col;
	}
	
	public BoardPosition getPosition () {
		return roomPosition;
	}
	
	public int getNumArrows () {
		return numArrows;
	}
	
	public void addArrow () {
		numArrows++;
	}
	
	public void removeArrows () {
		numArrows = 0;
	}
	
	public boolean hasBats () {
		return hasBats;
	}
	
	public boolean hasPit () {
		return hasPit;
	}
	
	public boolean hasWall () {
		return hasWall;
	}

	public boolean hasSpawn() {
		return hasSpawn;
	}
	
	public boolean hasDoor() {
		return hasDoor;
	}

	public boolean isBuildingRoom() {
		return isBuildingRoom;
	}

	public void setBuildingRoom(boolean isBuildingRoom) {
		this.isBuildingRoom = isBuildingRoom;
	}


	public int getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}


	public RoomType getRoomType() {
		return roomType;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}


	public boolean isEmpty() {
		return empty;
	}


	public void setEmpty(boolean empty) {
		this.empty = empty;
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	public boolean hasObjective() {
		return hasObjective;
	}


	public void setHasObjective(boolean hasObjective) {
		this.hasObjective = hasObjective;
	}


	public boolean isHasExit() {
		return hasExit;
	}


	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}	
}
