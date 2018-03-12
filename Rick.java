package jri.agile.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Rick extends GameEntity {
	private boolean frozen;
	public Integer randNumberOfRicks;
	
	public Rick (Game game, int yPos, int xPos) {
		super(game, yPos, xPos);
		frozen = false;
	}
	
	public void freeze () {
		frozen = true;
	}
	
	public void moveRick (Rick rick) {
		Stack<Room> path = getPath(game, rick);
		int xPos = rick.getCurrentPosition().getXPos();
		int yPos = rick.getCurrentPosition().getYPos();
		int newXPosition = 0;
		int newYPosition = 0;
		if (game.isRickInRoomWithPlayer(rick)) {
			newXPosition = xPos;
			newYPosition = yPos;
		} else {	
			newXPosition = path.get(1).getPosition().getXPos();
			newYPosition = path.get(1).getPosition().getYPos();
		}
		game.getRoom(yPos, xPos).setEmpty(true);
		rick.getCurrentPosition().setXPos(newXPosition);
		rick.getCurrentPosition().setYPos(newYPosition);
		game.getRoom(newYPosition, newXPosition).setEmpty(false);
	}
	
	public Stack<Room> getPath(Game game, Rick rick) {
		Player player = game.getPlayer();
		Stack<Room> path = new Stack<Room>();
		Room current = game.getRoom(rick.getCurrentPosition().getYPos(), rick.getCurrentPosition().getXPos());
		path.push(current);
		while(!path.isEmpty()) {
			int currentY = current.getPosition().getYPos();
			int currentX = current.getPosition().getXPos();
			int playerY = player.getCurrentPosition().getYPos();
			int playerX = player.getCurrentPosition().getXPos();
			
			if (currentY == playerY && currentX == playerX) {
				resetVisitedRooms(game);
				break;
			}
			
			current.setVisited(true);
			current = getNeighboringRoom(game, current, path, player);
		}
		return path;
	}

	private void resetVisitedRooms(Game game) {
		for (int row = 0; row < game.getHeight(); row++) {
			for (int col = 0; col < game.getWidth(); col++) {
				game.getRoom(row, col).setVisited(false);
			}
		}
	}
	
	private Room getNeighboringRoom(Game game, Room current, Stack<Room> path, Player player) {
		int playerY = player.getCurrentPosition().getYPos();
		int playerX = player.getCurrentPosition().getXPos();
		int rickY = current.getPosition().getYPos();
		int rickX = current.getPosition().getXPos();
		
		if (playerY > rickY && playerX < rickX) {			
			current = takeSWPath(game, current, path);
		} else if (playerY < rickY && playerX < rickX) {
			current = takeNWPath(game, current, path);
		} else if (playerY < rickY && playerX > rickX) {
			current = takeNEPath(game, current, path);
		} else if (playerY > rickY && playerX > rickX) {
			current = takeSEPath(game, current, path);
		} else if (playerY < rickY && playerX == rickX) {
			current = takeNPath(game, current, path);
		} else if (playerY > rickY && playerX == rickX) {
			current = takeSPath(game, current, path);
		} else if (playerY == rickY && playerX > rickX) {
			current = takeEPath(game, current, path);
		} else if (playerY == rickY && playerX < rickX) {
			current = takeWPath(game, current, path);
		} else {
			current = current;
		}
		return current;
	}

	private Room takeWPath(Game game, Room current, Stack<Room> path) {
		if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeEPath(Game game, Room current, Stack<Room> path) {
		if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeSPath(Game game, Room current, Stack<Room> path) {
		if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeNPath(Game game, Room current, Stack<Room> path) {
		if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeSEPath(Game game, Room current, Stack<Room> path) {
		if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeNEPath(Game game, Room current, Stack<Room> path) {
		if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeNWPath(Game game, Room current, Stack<Room> path) {
		if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private Room takeSWPath(Game game, Room current, Stack<Room> path) {
		if (isSouthRoomValid(game, current)) {
			current = getSouthRoom(game, current);
			path.add(current);
		} else if (isWestRoomValid(game, current)){
			current = getWestRoom(game, current);
			path.add(current);
		} else if (isNorthRoomValid(game, current)) {
			current = getNorthRoom(game, current);
			path.add(current);
		} else if (isEastRoomValid(game, current)) {
			current = getEastRoom(game, current);
			path.add(current);
		} else {
			path.pop();
			current = path.lastElement();
		}
		return current;
	}

	private boolean isNorthRoomValid(Game game, Room current) {
		boolean isValid = false;
		if (current.getRow() > 0) {			
			Room northRoom = getNorthRoom(game, current);
			if (!northRoom.hasDoor() && !northRoom.hasWall() && !northRoom.isVisited()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	private boolean isSouthRoomValid(Game game, Room current) {
		boolean isValid = false;
		if (current.getRow() < game.getHeight() - 1){
			Room southRoom = getSouthRoom(game, current);
			if (!southRoom.hasDoor() && !southRoom.hasWall() && !southRoom.isVisited()) {
				isValid = true;
			}
		}
		return isValid;
	}

	private boolean isEastRoomValid(Game game, Room current) {
		boolean isValid = false;
		if (current.getColumn() < game.getWidth() - 1){
			Room eastRoom = getEastRoom(game, current);
			if(!eastRoom.hasDoor() && !eastRoom.hasWall() && !eastRoom.isVisited()) {
				isValid = true;
			}	
		}
		return isValid;
	}
	
	private boolean isWestRoomValid(Game game, Room current) {
		boolean isValid = false;
		if (current.getColumn() > 0) {
			Room westRoom = getWestRoom(game, current);
			if(!westRoom.hasDoor() && !westRoom.hasWall() && !westRoom.isVisited()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	private Room getNorthRoom(Game game, Room current) {
		return game.getRoom(current.getRow() - 1, current.getColumn());
	}
	
	private Room getSouthRoom(Game game, Room current) {
		return game.getRoom(current.getRow() + 1, current.getColumn());
	}
	
	private Room getEastRoom(Game game, Room current) {
		return game.getRoom(current.getRow(), current.getColumn() + 1);
	}
	
	private Room getWestRoom(Game game, Room current) {
		return game.getRoom(current.getRow(), current.getColumn() - 1);
	}

	public boolean isFrozen () {
		return frozen;
	}


	public Integer getRandNumberOfRicks() {
		return randNumberOfRicks;
	}


	public void setRandNumberOfRicks(Integer randNumberOfRicks) {
		this.randNumberOfRicks = randNumberOfRicks;
	}
	
	

}
