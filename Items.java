package jri.agile.game;

import java.util.ArrayList;
import java.util.List;

public class Items extends Item {
	
	private List<Item> itemList = new ArrayList<Item>();
	List<Item> startingWeaponList = new ArrayList<Item>();
	private Item fireball = new Item();
	private Item axe = new Item();
	private Item longbow = new Item();
	private Item hammer = new Item();
	private Item greatSword = new Item();
	private Item manaBlast = new Item();
	private Item lightningBolt = new Item();
	private Item sword = new Item();
	private Item dagger = new Item();
	private Item handCrossbow = new Item();
	private Item inferno = new Item();;
	private Item orcishCrossbow = new Item();;
	private Item shortSword = new Item();
	private Item shortBow = new Item();
	private Item deathStrike = new Item();
	private Item crossbow = new Item();
	private Item repeatingCrossbow = new Item();
	private Item rock = new Item();
	
	public void buildItemList() {
//		buildAxe();
//		buildCrossbow();
//		buildDagger();
//		buildDeathStrike();
//		buildFireball();
//		buildGreatSword();
//		buildHammer();
//		buildHandCrossbow();
//		buildInferno();
//		buildLightningBolt();
//		buildLongbow();
//		buildManaBlast();
//		buildOrcishCrossbow();
//		buildRepeatingCrossbow();
//		buildShortBow();
//		buildShortSword();
//		buildSword();
		buildRock();
	}
	
	private void buildRock() {
		rock .setName("Rock");
		rock.setRange(0);
		rock.setDieNumber(6);
		rock.setAttack(1);
		rock.setDamage(3);
		rock.breakDoors = true;
		itemList.add(rock);
		startingWeaponList.add(rock);
	}
	
	private void buildFireball() {
		fireball.setName("Fireball");
		fireball.setRange(1);
		fireball.setDieNumber(3);
		fireball.setAttack(4);
		fireball.setDamage(1);
		fireball.breakDoors = false;
		itemList.add(fireball);
	}
	
	private void buildAxe() {
		axe.setName("Axe");
		axe.setRange(0);
		axe.setDieNumber(1);
		axe.setAttack(4);
		axe.setDamage(1);
		axe.breakDoors = true;
		itemList.add(axe);
		startingWeaponList.add(axe);
	}
	
	private void buildLongbow() {
		longbow.setName("Longbow");
		longbow.setRange(3);
		longbow.setDieNumber(1);
		longbow.setAttack(3);
		longbow.setDamage(1);
		longbow.breakDoors = false;
		itemList.add(longbow);
	}
	
	private void buildHammer() {
		hammer.setName("Hammer");
		hammer.setRange(0);
		hammer.setDieNumber(1);
		hammer.setAttack(4);
		hammer.setDamage(2);
		hammer.breakDoors = true;
		itemList.add(hammer);
		startingWeaponList.add(hammer);
	}
	
	private void buildGreatSword() {
		greatSword.setName("Great Sword");
		greatSword.setRange(0);
		greatSword.setDieNumber(5);
		greatSword.setAttack(5);
		greatSword.setDamage(1);
		greatSword.breakDoors = true;
		itemList.add(greatSword);
		startingWeaponList.add(greatSword);
	}
	
	private void buildLightningBolt() {
		lightningBolt.setName("Lightning Bolt");
		lightningBolt.setRange(3);
		lightningBolt.setDieNumber(1);
		lightningBolt.setAttack(4);
		lightningBolt.setDamage(1);
		lightningBolt.breakDoors = false;
		itemList.add(lightningBolt);
	}
	
	private void buildSword() {
		sword.setName("Sword");
		sword.setRange(0);
		sword.setDieNumber(2);
		sword.setAttack(4);
		sword.setDamage(1);
		sword.breakDoors = true;
		itemList.add(sword);
		startingWeaponList.add(sword);
	}
	
	private void buildManaBlast() {
		manaBlast.setName("Mana Blast");
		manaBlast.setRange(1);
		manaBlast.setDieNumber(1);
		manaBlast.setAttack(4);
		manaBlast.setDamage(1);
		manaBlast.breakDoors = false;
		itemList.add(manaBlast);
	}
	
	private void buildDagger() {
		dagger.setName("Dagger");
		dagger.setRange(0);
		dagger.setDieNumber(1);
		dagger.setAttack(4);
		dagger.setDamage(1);
		dagger.breakDoors = true;
		itemList.add(dagger);
		startingWeaponList.add(dagger);
	}
	
	private void buildHandCrossbow() {
		handCrossbow.setName("Hand Crossbow");
		handCrossbow.setRange(1);
		handCrossbow.setDieNumber(2);
		handCrossbow.setAttack(3);
		handCrossbow.setDamage(1);
		handCrossbow.breakDoors = false;
		itemList.add(handCrossbow);
	}
	
	private void buildInferno() {
		inferno.setName("Inferno");
		inferno.setRange(1);
		inferno.setDieNumber(4);
		inferno.setAttack(4);
		inferno.setDamage(2);
		inferno.breakDoors = false;
		itemList.add(inferno);
	}
	
	private void buildOrcishCrossbow() {
		orcishCrossbow.setName("Orcish Crossbow");
		orcishCrossbow.setRange(2);
		orcishCrossbow.setDieNumber(2);
		orcishCrossbow.setAttack(3);
		orcishCrossbow.setDamage(2);
		orcishCrossbow.breakDoors = false;
		itemList.add(orcishCrossbow);
	}
	
	private void buildShortSword() {
		shortSword.setName("Short Sword");
		shortSword.setRange(0);
		shortSword.setDieNumber(1);
		shortSword.setAttack(4);
		shortSword.setDamage(1);
		shortSword.breakDoors = true;
		itemList.add(shortSword);
		startingWeaponList.add(shortSword);
	}
	
	private void buildShortBow() {
		shortBow.setName("Short Bow");
		shortBow.setRange(1);
		shortBow.setDieNumber(1);
		shortBow.setAttack(3);
		shortBow.setDamage(1);
		shortBow.breakDoors = false;
		itemList.add(shortBow);
	}
	
	private void buildDeathStrike() {
		deathStrike.setName("Death Strike");
		deathStrike.setRange(1);
		deathStrike.setDieNumber(1);
		deathStrike.setAttack(4);
		deathStrike.setDamage(2);
		deathStrike.breakDoors = false;
		itemList.add(deathStrike);
	}
	
	private void buildCrossbow() {
		crossbow.setName("Crossbow");
		crossbow.setRange(1);
		crossbow.setDieNumber(1);
		crossbow.setAttack(4);
		crossbow.setDamage(2);
		crossbow.breakDoors = false;
		itemList.add(crossbow);
	}
	
	private void buildRepeatingCrossbow() {
		repeatingCrossbow.setName("RepeatingCrossbow");
		repeatingCrossbow.setRange(1);
		repeatingCrossbow.setDieNumber(3);
		repeatingCrossbow.setAttack(5);
		repeatingCrossbow.setDamage(1);
		repeatingCrossbow.breakDoors = false;
		itemList.add(repeatingCrossbow);
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> weaponList) {
		this.itemList = weaponList;
	}

	public List<Item> getStartingWeaponList() {
		return startingWeaponList;
	}

	public void setStartingWeaponList(List<Item> startingWeaponList) {
		this.startingWeaponList = startingWeaponList;
	}
	
	

	
}
