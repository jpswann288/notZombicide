package jri.agile.game;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameEntity {
	
	private int actionPoints;
	private int hp;
	private int exp;
	private boolean searched;
	private List<Item> playerInventory = new ArrayList<Item>(); 
	private List<Rick> ricks = game.getRicks();

	public Player (Game game,  int yPos, int xPos) {
		super(game, yPos, xPos);
		this.actionPoints = 3;
		this.hp = 3;
		this.exp = 0;
	}
	
	public boolean move (char direction) {
		boolean moved = super.move(direction);
		return moved;
	}
	
	public Integer diceRoll (Item item) {
		int hit = 0;
		int miss = 0;
		List<Integer> rolls = new ArrayList<Integer>();
		for (int i = 0; i < item.getDieNumber(); i++) {
			int diceRoll = ((int)Math.floor(Math.random() * 6) + 1);
			rolls.add(diceRoll);
			if (diceRoll >= item.getAttack()) {
				hit++;
			} else {
				miss++;
				actionLog.addLast(GameText.MISSED);
			}
		}
		actionLog.addLast("Rolls: " + rolls);
		actionLog.addLast("Hit: " + hit + "\nMiss:  " + miss);
		return hit;
	}

	public void shoot (char direction) {
		BoardPosition position = getCurrentPosition();
		
		if (direction == 'E') {
			if (!playerInventory.isEmpty()) {
				int range = playerInventory.get(0).getRange();
				int initialCol = position.getXPos();
				for (int col = position.getXPos(); col <= (initialCol + range); col++) {
					findRickTakeShotEW(position, col);
				}
			}
		} else if (direction == 'W') {
			if (!playerInventory.isEmpty()) {
				int range = playerInventory.get(0).getRange();
				int initialCol = position.getXPos();
				for (int col = position.getXPos(); col >= (initialCol - range); col--) {
					findRickTakeShotEW(position, col);
				}
			}
			
		} else if (direction == 'S') {
			if (!playerInventory.isEmpty()) {
				int range = playerInventory.get(0).getRange();
				int initialRow = position.getYPos();
				for (int row = position.getYPos(); row <= (initialRow + range); row++) {
					findRickTakeShotNS(position, row);
				}
			}
		} else if (direction == 'N') {
			if (!playerInventory.isEmpty()) {
				int range = playerInventory.get(0).getRange();
				int initialRow = position.getYPos();
				for (int row = position.getYPos(); row >= (initialRow - range); row--) {
					findRickTakeShotNS(position, row);
				}
			}
		}
		System.out.println(game.printMap());
	}
	
	public void attack() {
		//does not matter direction if in same cell
		BoardPosition position = getCurrentPosition();
		int row = position.getYPos();
		findRickTakeShotNS(position, row);
		System.out.println(game.printMap());
	}

	private void findRickTakeShotEW(BoardPosition position, int col) {
		Rick rick;
		if (!game.getRicks().isEmpty()){
			for (int i = 0; i < game.getRicks().size(); i++) {
				rick = ricks.get(i);
				takeShotEW(position, rick, col, i);
			}
		}
	}

	private void findRickTakeShotNS(BoardPosition position, int row) {
		Rick rick;
		if (!game.getRicks().isEmpty()){
			for (int i = 0; i < game.getRicks().size(); i++){
				rick = ricks.get(i);
				takeShotNS(position, rick, row, i);
			}
		}
	}

	private void takeShotNS(BoardPosition position, Rick rick, int row, int i) {
		if (isRickInRangNS(position, rick, row)) {
			takeShot(rick, i);	
		}
	}

	private void takeShotEW(BoardPosition position, Rick rick, int col, int i) {
		if (isRickInRangeEW(position, rick, col)) {
			takeShot(rick, i);	
		}
	}

	private boolean isRickInRangNS(BoardPosition position, Rick rick, int row) {
		return rick.getCurrentPosition().getXPos() == position.getXPos() && rick.getCurrentPosition().getYPos() == row;
	}

	private boolean isRickInRangeEW(BoardPosition position, Rick rick, int col) {
		return rick.getCurrentPosition().getYPos() == position.getYPos() && rick.getCurrentPosition().getXPos() == col;
	}

	private void takeShot(Rick rick, int i) {
		actionPoints--;
		int hit = diceRoll(playerInventory.get(0));
		int numberOfRicks = rick.getRandNumberOfRicks();
		int killed = 0;
		if (hit != 0) {	
			if (numberOfRicks - hit <= 0) {
				exp = exp + numberOfRicks;
				killed = numberOfRicks;
				game.getRoom(rick.getCurrentPosition().getYPos(), rick.getCurrentPosition().getXPos()).setEmpty(true);
				ricks.remove(i);
			} else {
				exp = exp + hit;
				rick.setRandNumberOfRicks(numberOfRicks - hit);
				killed = numberOfRicks - hit;
			}
			actionLog.addLast("Ricks Killed: " + killed);
		}
	}
	
	public void rest () {
		if(actionPoints > 0) {	
			actionPoints--;
			actionLog.addLast(GameText.RESTING);
		}
	}
	
	public String toString () {
		String res = "";
		
		while(actionLog.size() > 0) {
			res += "\n" + actionLog.removeFirst();
		}
		
		return res;
	}

	public void addToInvintory(Item item) {
		playerInventory.add(item);
	}

	public int getHp() {
		return hp;
	}

	public List<Item> getInvintory() {
		return playerInventory;
	}
	
	public int getActionPoints() {
		return actionPoints;
	}

	public int getExp() {
		return exp;
	}

	public List<Rick> getRicks() {
		return ricks;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setInvintory(List<Item> playerInvintory) {
		this.playerInventory = playerInvintory;
	}

	public void setRicks(List<Rick> ricks) {
		this.ricks = ricks;
	}

	public boolean isSearched() {
		return searched;
	}

	public void setSearched(boolean searched) {
		this.searched = searched;
	}

	public void abilities() {
		if (game.getPlayer().getExp() <= 6) {
			game.getPlayer().setActionPoints(3);
		} else if (game.getPlayer().getExp() >= 7 && game.getPlayer().getExp() <= 18) {
			game.getPlayer().setActionPoints(4);
		} else if (game.getPlayer().getExp() >=19 && game.getPlayer().getExp() <= 42) {
			//create special abilities
			game.getPlayer().setActionPoints(4);
		} else {
			//create special abilities
			game.getPlayer().setActionPoints(4);
		}
	}

	
}

