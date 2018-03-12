package jri.agile.game;

import java.util.ArrayList;
import java.util.List;

public class Game {	
	private Room[][] gameBoard;
	private int width;
	private int height;
	private int randItem;
	private int objectives;
	private Player player;
	private Rick rick;
	private Items items = new Items();
	private RickVideoPlayer videoPlayer;
	private List<Rick> ricks = new ArrayList<Rick>();
	private List<Item> itemList = new ArrayList<Item>();
	private Rick rick_J;
	
	public Game (int height, int width, RickVideoPlayer videoPlayer) {
		this.height = height;
		this.width = width;
		this.videoPlayer = videoPlayer;
		
		this.player = new Player(this, 0, 0);
		
		generateBoard();
		buildItemList();
	}
	
	public void setStartingWeapon() {
		List<Item> startingWeaponsList = items.getStartingWeaponList();
		List<Item> invintory = player.getInvintory();
		if (!startingWeaponsList.isEmpty()) {
			randItem = (int) Math.floor(Math.random() * startingWeaponsList.size());
			invintory.add(startingWeaponsList.get(randItem));
			startingWeaponsList.remove(randItem);
		}
	}

	private void buildItemList() {
		items.buildItemList();
	}

	public void searchRandomWeapon() {
		itemList = items.getItemList();
		randItem = (int) Math.floor(Math.random() * itemList.size());
		Item item = itemList.get(randItem);
		if (player.getInvintory().size() < 5) {
			player.addToInvintory(item);
			System.out.println("\n" + printMap());
			System.out.println(GameText.SEARCH_ROOM + item.getName());			
			if (player.getInvintory().size() < 2) {
				System.out.println(GameText.EQUIPED_ITEM + item.getName());
			}
		} else {
			System.out.println(GameText.INVENTORY_FULL);
			System.out.println(player.getActionPoints() + 1);
		}
	}
	
	public boolean isOver () {
		return !player.isAlive();
	}
	
	private void generateBoard () {
		gameBoard = new Room[height][width];
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				setRoom(row, col, new Room(row, col, Room.RoomType.Normal, 0, true));
			}
		}
		
		//Building #1
		//doors
		generateDoorAtLocation(1, 1, 1, true);
		generateDoorAtLocation(5, 1, 1, true);
		//walls
		generateWallAtLocation(1, 0, 0, true);
		generateWallAtLocation(1, 2, 0, true);
		generateWallAtLocation(2, 2, 0, true);
		generateWallAtLocation(3, 2, 0, true);
		generateWallAtLocation(4, 2, 0, true);
		generateWallAtLocation(5, 2, 0, true);
		generateWallAtLocation(5, 0, 0, true);
		//rooms
		generateBuildingRoomAtLocation(2, 0, 1, true);
		generateBuildingRoomAtLocation(2, 1, 1, true);
		generateObjectiveRoomAtLocation(3, 0, 1, true);
		generateBuildingRoomAtLocation(3, 1, 1, true);
		generateBuildingRoomAtLocation(4, 0, 1, true);
		generateBuildingRoomAtLocation(4, 1, 1, true);
		
		//Building #2
		//door
		generateDoorAtLocation(2, 5, 2, true);
		//walls
		generateWallAtLocation(0, 4, 0, true);
		generateWallAtLocation(0, 6, 0, true);
		generateWallAtLocation(1, 4, 0, true);
		generateWallAtLocation(1, 6, 0, true);
		generateWallAtLocation(2, 4, 0, true);
		generateWallAtLocation(2, 6, 0, true);
		//rooms
		generateObjectiveRoomAtLocation(0, 5, 2, true);
		generateBuildingRoomAtLocation(1, 5, 2, true);
		
		
		//Building #3
		//doors
		generateDoorAtLocation(4, 5, 3, true);
		//walls
		generateWallAtLocation(4, 4, 0, true);
		generateWallAtLocation(4, 6, 0, true);
		generateWallAtLocation(5, 4, 0, true);
		generateWallAtLocation(5, 6, 0, true);
		generateWallAtLocation(6, 4, 0, true);
		generateWallAtLocation(6, 6, 0, true);
		//rooms
		generateBuildingRoomAtLocation(5, 5, 3, true);
		generateObjectiveRoomAtLocation(6, 5, 3, true);
		
		//Spawn Points
		generateSpawnRoomAtLocation(0, 7, 0, true);
		generateSpawnRoomAtLocation(6, 0, 0, true);
		generateSpawnRoomAtLocation(6, 7, 0, true);
		
		//Exit
		generateExitRoomAtLocation(3, 8, 0, true);
		
		//walls
		generateWallAtLocation(0, 8, 0, true);
		generateWallAtLocation(1, 8, 0, true);
		generateWallAtLocation(2, 8, 0, true);
		generateWallAtLocation(4, 8, 0, true);
		generateWallAtLocation(5, 8, 0, true);
		generateWallAtLocation(6, 8, 0, true);
	}
	
	private void generateSpawnRoomAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.SpawnRoom, buildingNumber, empty));
	}
	
	private void generateObjectiveRoomAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.ObjectiveRoom, buildingNumber, empty));
	}
	
	private void generateBuildingRoomAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.BuildingRoom, buildingNumber, true));
	}
	
	private void generateExitRoomAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.ExitRoom, buildingNumber, true));
	}
	
	private void generateDoorAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.Door, buildingNumber, true));
	}
	
	private void generateWallAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.NoRoom, buildingNumber, true));
	}
	
	private void generateNormalRoomAtLocation(int row, int col, int buildingNumber, boolean empty) {
		setRoom(row, col, new Room(row, col, Room.RoomType.Normal, buildingNumber, true));
	}
	
	private void generateRandomRooms (int numRoomsToGenerate, Room.RoomType roomType, int buildingNumber, boolean empty) {
		BoardPosition playerPos = player.getCurrentPosition();
		int numRoomsGenerated = 0;
		
		while (numRoomsGenerated < numRoomsToGenerate) {
			int randX = (int)(Math.random() * width);
			int randY = (int)(Math.random() * height);
			BoardPosition randPos = new BoardPosition(randY, randX);
			
			if (!playerPos.equals(randPos) && !getRoom(randY, randX).hasWall() && !getRoom(randY, randX).hasSpawn()) {
				setRoom(randY, randX, new Room(randY, randX, roomType, buildingNumber, empty));
				numRoomsGenerated++;
			}
		}
	}
	
	public int getObjectives() {
		return objectives;
	}

	public void setObjectives(int objectives) {
		this.objectives = objectives;
	}

	public Room[][] getRooms () {
		return gameBoard;
	}
	
	public Room getRoom (int row, int col) {
		return gameBoard[row][col];
	}
	
	public void setRoom (int row, int col, Room room) {
		if (row < height && col < width) {
			gameBoard[row][col] = room;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Player getPlayer () {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Rick getRick () {
		return rick;
	}
	
	public void moveRick(){
		for(int i = 0; i < ricks.size(); i++) {
			rick = ricks.get(i);
			rick.moveRick(rick);	
		}
	}

	private void isPlayerDead(int hp) {
		if (hp <= 0) {
			player.die();
			player.actionLog.addLast(GameText.KILLED_BY_RICK);
			videoPlayer.play();
		}
	}
	
	public boolean isPlayerInRoomWithRick () {
		for(int i = 0; i < ricks.size(); i++) {
			rick = ricks.get(i);
			if (getPlayer().getCurrentPosition().equals(rick.getCurrentPosition())){	
					return true;
			}
		}
		return false;
	}
	
	public int getNumberOfRicks () {
		for(int i = 0; i < ricks.size(); i++) {
			rick = ricks.get(i);
			if (getPlayer().getCurrentPosition().equals(rick.getCurrentPosition())){	
					return rick.getRandNumberOfRicks();
			}
		}
		return 0;
	}
	
	public boolean isRickInRoomWithPlayer (Rick rick) {
		int playerHP = player.getHp();
		if (player.getCurrentPosition().equals(rick.getCurrentPosition())) {	
			player.setHp(playerHP - rick.getRandNumberOfRicks());
			playerHP = player.getHp();
			isPlayerDead(playerHP);
			return true;
		}
		return false;
	}
	
	public boolean isPlayerInRoomWithPit () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.hasPit();
	}
	
	public boolean isPlayerInRoomWithBats () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.hasBats();
	}
	
	public boolean isPlayerInRoomWithObjective () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.hasObjective();
	}
	
	public boolean isPlayerInRoomWithExit () {
		Room room = gameBoard[player.getCurrentPosition().getYPos()][player.getCurrentPosition().getXPos()];
		return room.isHasExit();
	}
	
	public String printInvintory() {
		String invintory = "";
		List<Item> playerInvintory = player.getInvintory();
		for (int i = 0; i < playerInvintory.size(); i++) {
			invintory += "[" + playerInvintory.get(i).getName() + " " + playerInvintory.get(i).getRange() + "," +
					playerInvintory.get(i).getDieNumber() + "," + playerInvintory.get(i).getAttack() + 
					"," +playerInvintory.get(i).getDamage() + "]";
		}
		return invintory;
	}
	
	public String toString () {
		return player.toString();
	}

	public void spawnRicks() {		
		Integer randNumberOfRicks = (int)(Math.random() * 4);
		List<Room> spawnRooms = findSpawnRooms();
		if(randNumberOfRicks != 0){
			for (int i = 0; i < spawnRooms.size(); i++){	
				this.rick = new Rick(this, spawnRooms.get(i).getRow(), spawnRooms.get(i).getColumn());
				this.rick.setRandNumberOfRicks(randNumberOfRicks);
				ricks.add(rick);
			}
		}
	}
	
	public List<Room> findSpawnRooms(){
		boolean hasSpawn = false;
		List<Room> spawnRooms = new ArrayList<Room>();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Room room = gameBoard[row][col];
				hasSpawn = room.hasSpawn();
				if (hasSpawn) {
					spawnRooms.add(room);
				}
			}
		}
		return spawnRooms;
	}

	public List<Rick> getRicks() {
		return ricks;
	}

	public void spawnBuilding(int yPos, int xPos) {
		List<Room> buildingRooms = findBuildingRooms();
		int buildingNumber = getRoom(yPos, xPos).getBuildingNumber();
		for (int i = 0; i < buildingRooms.size(); i++) {
			Integer randNumberOfRicks = (int)(Math.random() * 4);
			if(randNumberOfRicks != 0){
				if (buildingRooms.get(i).getBuildingNumber() == buildingNumber) {
					this.rick = new Rick(this, buildingRooms.get(i).getRow(), buildingRooms.get(i).getColumn());
					this.rick.setRandNumberOfRicks(randNumberOfRicks);
					ricks.add(rick);
				}
			}
		}
	}

	private List<Room> findBuildingRooms() {
		boolean isBuildingRoom = false;
		List<Room> buildingRooms = new ArrayList<Room>();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Room room = gameBoard[row][col];
				isBuildingRoom = room.isBuildingRoom();
				if (isBuildingRoom) {
					buildingRooms.add(room);
				}
			}
		}
		return buildingRooms;
	}
	
	public String printMap () {
		String res = "\n--------------------------------------------------------\n|";
		int playerX = player.getCurrentPosition().getXPos();
		int playerY = player.getCurrentPosition().getYPos();
		
		for (int rowY = 0; rowY < height; rowY++) {
				for (int colX = 0; colX < width; colX++) {
					String inner = "";
					for (int i =  0; i < ricks.size(); i++) {		
						rick = ricks.get(i);
						int rickX = rick.getCurrentPosition().getXPos();
						int rickY = rick.getCurrentPosition().getYPos();
						Integer numOfRicks = rick.getRandNumberOfRicks();
	//					inner += getRoom(row, col).hasBats()? "B" : "" ;
	//					inner += getRoom(row, col).hasPit()? "O" : "" ;
						for (int j = i+1; j < ricks.size(); j++) {
							rick_J = ricks.get(j);
							int rick_JX = rick_J.getCurrentPosition().getXPos();
							int rick_JY = rick_J.getCurrentPosition().getYPos();
							Integer numOfRicks_J = rick_J.getRandNumberOfRicks();
							if (rickX == rick_JX && rickX == colX && rick_JX == colX && rickY == rick_JY && rickY == rowY && rick_JY == rowY) {
								numOfRicks = numOfRicks + numOfRicks_J;
								ricks.remove(j);
								rick.randNumberOfRicks = numOfRicks;
							}
						}
						String spawn = numOfRicks.toString();
						inner += rowY == rickY && colX == rickX ? spawn+" " : "";
					}
					inner += getRoom(rowY, colX).hasWall() ? "====" : "" ;
					inner += getRoom(rowY, colX).hasDoor() ? "----" : "" ;
					inner += getRoom(rowY, colX).hasSpawn() ? "S" : "" ;
					inner += getRoom(rowY, colX).isHasExit() ? "Exit" : "" ;
					inner += getRoom(rowY, colX).hasObjective() ? "X" : "" ;
					inner += rowY == playerY && colX == playerX ? "P" : "";
					
					res += String.format("|%-4s|", inner);
				}
			res += "|\n--------------------------------------------------------\n|";
		}
		res += "\nHP: " + player.getHp() + "    Inv: " + printInvintory() + "\nAP: " + player.getActionPoints() 
				+ "    Exp: " + player.getExp() + "T";

		return res.substring(0, res.length() - 1);
	}

	public void win() {
		System.out.println(GameText.WIN);
		player.die();	
	}
}
