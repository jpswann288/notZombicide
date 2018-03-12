package jri.agile.game;

import java.util.LinkedList;

public class GameEntity {
	
	private BoardPosition position;
	private boolean isAlive;
	protected LinkedList<String> actionLog;
	protected Game game;
	private boolean hasWall;
	private boolean hasDoor;
	private Room room;
	private boolean hasExit;
	
	public GameEntity (Game game, int yPos, int XPos) {
		isAlive = true;
		position = new BoardPosition(yPos, XPos);
		this.game = game;
		
		actionLog = new LinkedList<>();
	}
	
	public LinkedList<String> getActionLog () {
		return actionLog;
	}

	public BoardPosition getCurrentPosition () {
		return position;
	}
	

public boolean move (char direction) {
		boolean moved = false;
		if (canMoveEast(direction)) {
			if (doesEastHaveDoor()) {
				if (canBreakDownDoor()) {
					if (game.isPlayerInRoomWithRick()) {
						int numOfRicks = game.getNumberOfRicks();
						if (game.getPlayer().getActionPoints() >= numOfRicks + 1) {
							moved = true;
							position.setXPos(position.getXPos() + 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos(), position.getXPos() + 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - (numOfRicks + 1));
						} else {
							moved = false;
						}
					} else {
						if (game.getPlayer().getActionPoints() >= 1) {
							moved = true;
							position.setXPos(position.getXPos() + 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos(), position.getXPos() + 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
						} else {
							moved = false;
						}
					}
				} else {
					moved = false;
				}
			} else if (doesEastHaveExit()) {
				if (canPlayerExit()) {
					if (game.isPlayerInRoomWithRick()) {
						int numOfRicks = game.getNumberOfRicks();
						if (game.getPlayer().getActionPoints() >= numOfRicks) {
							moved = true;
							position.setXPos(position.getXPos() + 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - numOfRicks);
							game.win();
						} else {
							moved = false;
						}
					} else {
						if (game.getPlayer().getActionPoints() >= 0) {
							moved = true;
							position.setXPos(position.getXPos() + 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
							game.win();
						} else {
							moved = false;
						}
					}
				} else {
					moved = false;
				}
			} else {
				if (game.isPlayerInRoomWithRick()) {
					int numOfRicks = game.getNumberOfRicks();
					if (game.getPlayer().getActionPoints() >= numOfRicks) {
						moved = true;
						position.setXPos(position.getXPos() + 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - numOfRicks);
					} else {
						moved = false;
					}
				} else {
					if (game.getPlayer().getActionPoints() >= 0) {
						moved = true;
						position.setXPos(position.getXPos() + 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
					} else {
						moved = false;
					}
				}
			}
		} else if (canMoveWest(direction)) {
			if (doesWestHaveDoor()) {
				if (canBreakDownDoor()) {
					if (game.isPlayerInRoomWithRick()) {
						int numOfRicks = game.getNumberOfRicks();
						if (game.getPlayer().getActionPoints() >= numOfRicks + 1) {
							moved = true;
							position.setXPos(position.getXPos() - 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos(), position.getXPos() - 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - (numOfRicks + 1));
						} else {
							moved = false;
						}
					} else {
						if (game.getPlayer().getActionPoints() >= 1) {
							moved = true;
							position.setXPos(position.getXPos() - 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos(), position.getXPos() - 1);
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
						} else {
							moved = false;
						}
					}
				} else {
					moved = false;
				}
			} else {
				if (game.isPlayerInRoomWithRick()) {
					int numOfRicks = game.getNumberOfRicks();
					if (game.getPlayer().getActionPoints() >= numOfRicks) {
						moved = true;
						position.setXPos(position.getXPos() - 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - numOfRicks);
					} else {
						moved = false;
					}
				} else {
					if (game.getPlayer().getActionPoints() >= 0) {
						moved = true;
						position.setXPos(position.getXPos() - 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
					} else {
						moved = false;
					}
				}
			}
		} else if (canMoveNorth(direction)) {
			if (doesNorthHaveDoor()) {
				if (canBreakDownDoor()) {
					if (game.isPlayerInRoomWithRick()) {
						int numOfRicks = game.getNumberOfRicks();
						if (game.getPlayer().getActionPoints() >= numOfRicks + 1) {
							moved = true;
							position.setYPos(position.getYPos() - 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos() - 1, position.getXPos());
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - (numOfRicks + 1));
						} else {
							moved = false;
						}
					} else {
						if (game.getPlayer().getActionPoints() >= 2) {
							moved = true;
							position.setYPos(position.getYPos() - 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos() - 1, position.getXPos());
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
						} else {
							moved = false;
						}
					}
				} else {
					moved = false;
				}
			} else {
				if (game.isPlayerInRoomWithRick()) {
					int numOfRicks = game.getNumberOfRicks();
					if (game.getPlayer().getActionPoints() >= numOfRicks) {
						moved = true;
						position.setYPos(position.getYPos() - 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - numOfRicks);
					} else {
						moved = false;
					}
				} else {
					if (game.getPlayer().getActionPoints() >= 0) {
						moved = true;
						position.setYPos(position.getYPos() - 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
					} else {
						moved = false;
					}
				}
			}
		} else if (canMoveSouth(direction)) {
			if (doesSouthHaveDoor()) {
				if (canBreakDownDoor()) {
					if (game.isPlayerInRoomWithRick()) {
						int numOfRicks = game.getNumberOfRicks();
						if (game.getPlayer().getActionPoints() >= numOfRicks + 1) {
							moved = true;
							position.setYPos(position.getYPos() + 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos() + 1, position.getXPos());
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - (numOfRicks + 1));
						} else {
							moved = false;
						}
					} else {
						if (game.getPlayer().getActionPoints() >= 2) {
							moved = true;
							position.setYPos(position.getYPos() + 1);
							game.setRoom(position.getYPos(), position.getXPos(), new Room (position.getYPos(), position.getXPos(), Room.RoomType.Normal, 0, true));
							game.spawnBuilding(position.getYPos() + 1, position.getXPos());
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
						} else {
							moved = false;
						}
					}
				} else {
					moved = false;
				}
			} else {
				if (game.isPlayerInRoomWithRick()) {
					int numOfRicks = game.getNumberOfRicks();
					if (game.getPlayer().getActionPoints() >= numOfRicks) {
						moved = true;
						position.setYPos(position.getYPos() + 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - numOfRicks);
					} else {
						moved = false;
					}
				} else {
					if (game.getPlayer().getActionPoints() >= 0) {
						moved = true;
						position.setYPos(position.getYPos() + 1);
						game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() - 1);
					} else {
						moved = false;
					}
				}
			}
		} 
		return moved; 
	}

	private boolean canMoveNorth(char direction) {
		return direction == 'N' && position.getYPos() > 0 && !doesNorthHaveWall();
	}
	
	private boolean canMoveSouth(char direction) {
		return direction == 'S' && position.getYPos() < game.getHeight() - 1 && !doesSouthHaveWall();
	}

	private boolean canMoveEast(char direction) {
		return direction == 'E' && position.getXPos() < game.getWidth() - 1 && !doesEastHaveWall();
	}
	
	private boolean canMoveWest(char direction) {
		return direction == 'W' && position.getXPos() > 0 && !doesWestHaveWall();
	}

	@SuppressWarnings("unused")
	private boolean playerCanEscape(boolean rickPresent) {
		if(rickPresent){
			Rick rick;
			int ap = game.getPlayer().getActionPoints();
			for(int i = 0; i < game.getRicks().size(); i++) {
				rick = game.getRicks().get(i);
				if (game.getPlayer().getCurrentPosition().equals(rick.getCurrentPosition())){	
						int rickNumber = rick.getRandNumberOfRicks();
						if (ap - rickNumber >= 0) {
							game.getPlayer().setActionPoints(ap - rickNumber);
							return true;
						}
				}
			}
		}
		return false;
	}

	private boolean canBreakDownDoor () {
		boolean breakDoors = false;
		if(!game.getPlayer().getInvintory().isEmpty()) {
			if (game.getPlayer().getInvintory().get(0).canBreakDoors()) {
				breakDoors = true;
			}
		}
		return breakDoors;
	}
	
	private boolean canPlayerExit () {
		boolean exit = false;
		if(game.getObjectives() == 3) {
			exit = true;
		}
		return exit;
	}
	
	public boolean doesEastHaveExit () {
		if (position.getXPos() != game.getWidth()) {
			room = game.getRoom(position.getYPos(), position.getXPos() + 1);
			hasExit = room.isHasExit();
		}
		return hasExit;
	}

	private boolean doesSouthHaveDoor () {
		if (position.getYPos() != game.getHeight()) {
			room = game.getRoom(position.getYPos() + 1, position.getXPos());
			hasDoor = room.hasDoor();
		}
		return hasDoor;
	}
	
	private boolean doesNorthHaveDoor () {
		if (position.getYPos() != 0) {
			room = game.getRoom(position.getYPos() - 1, position.getXPos());
			hasDoor = room.hasDoor();
		}
		return hasDoor;
	}
	
	public boolean doesEastHaveDoor () {
		if (position.getXPos() != game.getWidth()) {
			room = game.getRoom(position.getYPos(), position.getXPos() + 1);
			hasDoor = room.hasDoor();
		}
		return hasDoor;
	}
	
	public boolean doesWestHaveDoor () {
		if (position.getXPos() != game.getWidth()) {
			room = game.getRoom(position.getYPos(), position.getXPos() - 1);
			hasDoor = room.hasDoor();
		}
		return hasDoor;
	}

	public boolean doesNorthHaveWall () {
		if (position.getYPos() != 0) {
			room = game.getRoom(position.getYPos() - 1, position.getXPos());
			hasWall = room.hasWall();
		}
		return hasWall;
	}
	
	public boolean doesSouthHaveWall () {
		if (position.getYPos() != game.getHeight()) {
			room = game.getRoom(position.getYPos() + 1, position.getXPos());
			hasWall = room.hasWall();
		}
		return hasWall;
	}
	
	public boolean doesEastHaveWall () {
		if (position.getXPos() != game.getWidth()) {
			room = game.getRoom(position.getYPos(), position.getXPos() + 1);
			hasWall = room.hasWall();
		}
		return hasWall;
	}
	
	public boolean doesWestHaveWall () {
		if (position.getXPos() != game.getWidth()) {
			room = game.getRoom(position.getYPos(), position.getXPos() - 1);
			hasWall = room.hasWall();
		}
		return hasWall;
	}
	
	public boolean isAlive () {
		return isAlive;
	}
	
	public void die () {
		isAlive = false;
	}
	


}
