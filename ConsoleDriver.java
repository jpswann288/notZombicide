package jri.agile.driver;

import java.time.chrono.IsoEra;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import jri.agile.game.Game;
import jri.agile.game.GameText;
import jri.agile.game.Player;
import jri.agile.game.RickVideoPlayerImpl;
import jri.agile.game.Room;
import jri.agile.game.Room.RoomType;
import jri.agile.game.Item;

public class ConsoleDriver {
	private static Scanner inputChar;
	private static Scanner inputInt;
	
	public static void main (String[] args) {
		String userInput = "Y";
		inputChar = new Scanner(System.in);
		inputInt = new Scanner(System.in);
		
		while (userInput.length() > 0 && userInput.toUpperCase().charAt(0) == GameText.YES_COMMAND) {
			playGame();
			
			System.out.print(GameText.PLAY_AGAIN);
			
			userInput = inputChar.nextLine();
		}
		
		System.out.println(GameText.GOODBYE);
		
		System.exit(0);
		
	}
	
	private static void playGame () {
		Game game = new Game(7, 9, new RickVideoPlayerImpl());
		Player player = game.getPlayer();
		String userInputChar;
		char command = ' ';
		boolean gameStart = false;
		printWelcome();
		gameStart = true;

		while (!game.isOver()) {
			gameStart = gameStart(game, gameStart);
			printPointer();
			
			userInputChar = inputChar.nextLine().toUpperCase();
			if (!userInputChar.isEmpty()) {
				command = userInputChar.charAt(0);				
			}

			if (userInputChar.length() > 0) {
				if (command == GameText.HELP_COMMAND) {
					printHelp(game);
				} else if (player.getActionPoints() == 0) {
					outOfAP(game, player, command);
				} else if (command == GameText.END_TURN_COMMAND) {
					endTurn(game);
				} else if (command == GameText.INVENTORY_COMMAND) {
					manageInvintoryAction(game, player, userInputChar);
				} else if (command == GameText.SEARCH_COMMAND && player.getActionPoints() > 0) {
					searchRoomAction(game, player);
				} else if (command == GameText.MOVE_COMMAND && player.getActionPoints() > 0) {
					moveAction(game, player, userInputChar);	
				} else if (command == GameText.ATTACK_COMMAND && player.getActionPoints() > 0) {
					shootAction(game, player, userInputChar);
				} else if (command == GameText.QUIT) {
					quitAction(game, command, userInputChar);
				} else if (command == GameText.PICK_UP_OBJECTIVE) {
					pickUpObjectiveAction(game, player);
				} else if (command != GameText.END_TURN_COMMAND) {
					printMap(game);
					printInvalidCommand();
				} 
			} else {
				printMap(game);
				printInvalidCommand();
			}
		}
	}

	private static void pickUpObjectiveAction(Game game, Player player) {
		if (game.isPlayerInRoomWithObjective() && player.getActionPoints() > 0) {
			int exp = player.getExp();
			int objectives = game.getObjectives();
			objectives++;
			player.setExp(exp + 5);
			player.setActionPoints(player.getActionPoints() - 1);
			game.setObjectives(objectives);
			game.setRoom(player.getCurrentPosition().getYPos(), player.getCurrentPosition().getXPos(), new Room (player.getCurrentPosition().getYPos(), player.getCurrentPosition().getXPos(), Room.RoomType.Normal, 0, true));
			printMap(game);
			System.out.println(GameText.GOT_OBJECTIVE);
		} else if (game.isPlayerInRoomWithObjective() && player.getActionPoints() == 0){
			System.out.println(GameText.OUT_OF_AP);
		} else {
			System.out.println(GameText.CANT_PICK_UP_HERE);
		}
		
	}

	private static void quitAction(Game game, char command, String userInputChar) {
		System.out.println(GameText.CONFIRMATION_QUIT);
		userInputChar = inputChar.nextLine().toUpperCase();
		command = userInputChar.charAt(0);
		if (command == GameText.YES_COMMAND) {
			game.getPlayer().die();
		} else {
			printMap(game);
		}
	}

	private static void printInvalidCommand() {
		System.out.println(GameText.INVALID);
	}

	private static void printPointer() {
		System.out.print("\n> ");
	}

	private static boolean gameStart(Game game, boolean gameStart) {
		if(gameStart){
			game.setStartingWeapon();
			printMap(game);
			gameStart = false;
		}
		return gameStart;
	}

	private static void shootAction(Game game, Player player, String userInputChar) {
		String[] shootDirection = userInputChar.split("");
		
		if (shootDirection.length != 2) {
			player.attack();
		} else {
			char direction = shootDirection[1].charAt(0);
			player.shoot(direction);
		}
		System.out.println(game.toString());
	}

	private static void moveAction(Game game, Player player, String userInputChar) {
		String[] moveDirection = userInputChar.split("");
		if (moveDirection.length != 2) {
			player.getActionLog().addLast("\n"+game.printMap());
			player.getActionLog().addLast(GameText.INVALID);
		} else {
			char direction = moveDirection[1].charAt(0);
			if (player.move(direction)) {		
				player.getActionLog().addLast("\n"+game.printMap());
				player.getActionLog().addLast(GameText.MOVED_TO_NEW_ROOM);
			} else {
				player.getActionLog().addLast("\n"+game.printMap());
				player.getActionLog().addLast(GameText.DID_NOT_MOVE);
			}
		}
		System.out.println(game.toString());
	}

	private static void searchRoomAction(Game game, Player player) {
		boolean isBuildingRoom = game.getRoom(player.getCurrentPosition().getYPos(), player.getCurrentPosition().getXPos()).isBuildingRoom();
		boolean isObjectiveRoom = game.getRoom(player.getCurrentPosition().getYPos(), player.getCurrentPosition().getXPos()).hasObjective();
		boolean isRoomEmpty = game.getRoom(player.getCurrentPosition().getYPos(), player.getCurrentPosition().getXPos()).isEmpty();
		if ((isBuildingRoom || isObjectiveRoom) && !player.isSearched() && isRoomEmpty) {
			player.setSearched(true);
			player.setActionPoints(player.getActionPoints() - 1);
			game.searchRandomWeapon();
		} else if (player.isSearched()){
			printMap(game);
			System.out.println(GameText.ALREADY_SEARCHED);
		} else if (!isRoomEmpty){
			printMap(game);
			System.out.println(GameText.CANT_SEARCH_WITH_RICK);
		} else {
			printMap(game);
			System.out.println(GameText.CANT_SEARCH);
		}
	}

	private static void endTurn(Game game) {
		game.getPlayer().abilities();
		game.getPlayer().setSearched(false);
		game.moveRick();
		game.spawnRicks();
		printMap(game);
	}

	private static void outOfAP(Game game, Player player, char command) {
		player.getActionLog().addLast("\n"+game.printMap());
		player.getActionLog().addLast(GameText.OUT_OF_AP);
		if (command == GameText.END_TURN_COMMAND) {
			endTurn(game);
		}
	}

	private static void manageInvintoryAction(Game game, Player player, String userInputChar) {
		if (game.getPlayer().getActionPoints() > 0) {
			char command;
			int selection;
			System.out.println(GameText.OPENED_INVENTORY);
			selection = inputInt.nextInt();
			List<Item> invintory = game.getPlayer().getInvintory();
			if (selection == 1) {
				int item = 0;
				System.out.println(GameText.SELECT_ITEM);
				selection = inputInt.nextInt();
				if (selection >= 1 && selection <= 5){
					item = selection - 1;
				} else {
					printInvalidCommand();
				}
				Collections.swap(invintory, 0, item);
				printMap(game);
				System.out.println(GameText.CONTINUE);
				userInputChar = inputChar.nextLine().toUpperCase();
				command = userInputChar.charAt(0);
				if (command == GameText.YES_COMMAND) {
					manageInvintoryAction(game, player, userInputChar);
				} else {
					game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() -  1);
					printMap(game);
				}
			} else if (selection == 2) {
				int item1 = 0;
				System.out.println(GameText.SELECT_ITEM_DISCARD);
				selection = inputInt.nextInt();
				if (selection >= 1 && selection <= 5){
					item1 = selection - 1;
					System.out.println(GameText.CONFIRMATION_DISCARD + invintory.get(item1).getName());
					userInputChar = inputChar.nextLine().toUpperCase();
					command = userInputChar.charAt(0);
					if (command == GameText.YES_COMMAND){
						invintory.remove(item1);
						printMap(game);
						System.out.println(GameText.CONTINUE);
						userInputChar = inputChar.nextLine().toUpperCase();
						command = userInputChar.charAt(0);
						if (command == GameText.YES_COMMAND) {
							manageInvintoryAction(game, player, userInputChar);
						} else {
							game.getPlayer().setActionPoints(game.getPlayer().getActionPoints() -  1);
							printMap(game);
						}
					}
				} else {
					printInvalidCommand();
				}
			}
		} else {
			System.out.println(GameText.NOT_ENOUGH_AP);
			printMap(game);
		}
	}

	private static void printMap(Game game) {
		System.out.println("\n"+game.printMap());
	}
	
	private static void printWelcome () {
		System.out.println(
			GameText.WELCOME
		);
	}
	
	private static void printHelp (Game game) {
		printMap(game);
		System.out.println(
				GameText.CONTROLS
		);
	}

}
