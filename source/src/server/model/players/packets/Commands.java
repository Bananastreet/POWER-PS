package server.model.players.packets;

import server.Server;
import server.model.items.ItemList;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;
import server.model.players.TeleportHandler;
import server.task.Task;

/**
 * Properly written commands, none of that shit.
 * 
 * @author Bananastreet
 *
 */
public class Commands implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String command = c.getInStream().readString();
		String[] parts = command.toLowerCase().split(" ");
		if (!command.startsWith("/")) {
			c.getPA().writeCommandLog(command);
		}

		if (command.startsWith("/") && command.length() >= 1) {
			if (c.clanId >= 0) {
				Server.clanChat.playerMessageToClan(c.playerId, command.substring(1, command.length()), c.clanId);
			} else {
				if (c.clanId != -1) {
					c.clanId = -1;
				}
				c.sendMessage("You are not in a clan.");
			}
			return;
		}

		try {
			command = command.toLowerCase();
			switch (c.getPlayerRights()) {
			case 0:
				playerCommands(c, command, parts);
				break;
			case 1:
				playerCommands(c, command, parts);
				donatorCommands(c, command, parts);
				moderatorCommands(c, command, parts);
				break;
			case 2:
				playerCommands(c, command, parts);
				donatorCommands(c, command, parts);
				moderatorCommands(c, command, parts);
				administratorCommands(c, command, parts);
				break;
			case 3:
			case 4:
				playerCommands(c, command, parts);
				donatorCommands(c, command, parts);
				moderatorCommands(c, command, parts);
				administratorCommands(c, command, parts);
				developerCommands(c, command, parts);
				ownerCommands(c, command, parts);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			c.sendMessage("Error sending command.");
		}
	}

	private void playerCommands(Client c, String command, String... parts) {
		if (command.startsWith("players")) {
			c.getPA().showInterface(19350);
			c.getPA().refreshPlayersInterface();
		} else if (command.startsWith("rest")) {
			c.startAnimation(5713);
		} else if (command.equalsIgnoreCase("empty") && (System.currentTimeMillis() - c.emptyDelay >= 15000)) {
			c.getPA().sendFrame171(1, 2465);
			c.getPA().sendFrame171(0, 2468);
			c.getPA().sendFrame126("Empty Inventory?", 2460);
			c.getPA().sendFrame126("Yes, please!", 2461);
			c.getPA().sendFrame126("No, Thank you.", 2462);
			c.getPA().sendFrame164(2459);
			c.getDH().sendDialogues(1340, 1);
			c.dialogueAction = 1340;
		} else if (command.startsWith("home")) {
			TeleportHandler.teleport(c, 2965, 3379, 0, c.playerMagicBook);
		}
	} 

	private void moderatorCommands(Client c, String command, String... parts) {

	}

	private void administratorCommands(Client c, String command, String... parts) {

	}

	private void developerCommands(Client c, String command, String... parts) {
		if (command.startsWith("bank")) {
			c.getPA().openUpBank();
		} else if (command.startsWith("item")) {
			int amount = 1;
			if (parts.length > 2) {
				try {
					amount = Integer.parseInt(parts[2].toLowerCase().replace("k", "000").replace("m", "000000").replace("b", "000000000"));
				} catch (Exception e) {
					amount = 1;
				}
			}
			c.getItems().addItem(Integer.parseInt(parts[1]), amount);
			c.sendMessage("You have spawned " + amount + " x " + Server.itemHandler.getItemList(Integer.parseInt(parts[1])).getItemName());
		} else if (command.startsWith("update")) {
			int time = Integer.parseInt(parts[1]);
			if (time > 0) {
				Server.UpdateServer = true;
				for (Player p : PlayerHandler.getPlayers()) {
					if (p != null) {
						Client c2 = (Client) p;
						c2.getPA().sendSystemUpdate(time);
					}
				}
				 Server.getTaskScheduler().addEvent(new Task(time * 50 / 30, false) {
					 
					public void execute() {
						for (Player p : PlayerHandler.getPlayers()) {
							if (p != null) {
								Client c2 = (Client) p;
								PlayerSave.saveGame(c2); //This is just precautionary.
								System.out.println("[Saved game]" + p.playerName + ".");
								c2.logout();
							}
						}
						stop();
					}					
				});
			}
		} else if (command.startsWith("getid")) {
			String search = "";
			int results = 0;
			search += command.substring(parts[0].length() + 1);
			c.sendMessage("Searching for: <col=ff0000>" + search + "</col>");
			for (ItemList itemList : Server.itemHandler.itemList.clone()) {
				if (itemList == null) {
					continue;
				}
				if (itemList.getItemName().toLowerCase().contains(search.toLowerCase())) {
					c.sendMessage("<col=ff0000>" + itemList.getItemName() + "</col> - <col=ff0000>" + itemList.getItemId() + "</col>");
					results++;
				}
			}
			c.sendMessage("<col=ff0000>" + results + "</col> results found.");
		} else if (command.startsWith("pos") || command.startsWith("coords")) {
			c.sendMessage("Your current location is: " + c.getX() +  ", " + c.getY()  + ", " + c.heightLevel);
			System.out.println(c.getX() +  ", " + c.getY()  + ", " + c.heightLevel);
		} else if (command.startsWith("npc")) {
			int npcId = Integer.parseInt(parts[1]);
			//TODO: This needs fixed, probably gonna need to change the whole npc loader. 
		} else if (command.startsWith("master")) {
			if (c.inWild()) {
				return;
			}
			for (int i = 0; i < c.playerLevel.length; i++) {
				c.playerXP[i] = c.getPA().getXPForLevel(99) + 5;
				c.playerLevel[i] = c.getPA().getLevelForXP(c.playerXP[i]);
				c.getPA().refreshSkill(i);
			}
		} else if (command.startsWith("interface") || (command.startsWith("int"))) {
			int interfaceId = Integer.parseInt(parts[1]);
			c.getPA().showInterface(interfaceId);
		}
	}

	private void ownerCommands(Client c, String command, String... parts) {

	}

	private void donatorCommands(Client c, String command, String... parts) {

	}

}
