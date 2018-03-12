package jri.agile.game;

public class Item {

	private String name;
	private int range;
	private int dieNumber;
	private int attack;
	private int damage;
	public boolean breakDoors;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRange() {
		return range;
	}
	public int getDieNumber() {
		return dieNumber;
	}
	public int getAttack() {
		return attack;
	}
	public int getDamage() {
		return damage;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public void setDieNumber(int dieNumber) {
		this.dieNumber = dieNumber;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean canBreakDoors() {
		return breakDoors;
	}
	public void setBreakDoors(boolean breakDoors) {
		this.breakDoors = breakDoors;
	}
	
}
