package server.world;

import server.Connection;
import server.Server;
import server.model.items.Item;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * @author Sanity
 */

public class ClanChatHandler {

	public ClanChatHandler() {

	}

	public Clan[] clans = new Clan[400];

	public void handleClanChat(Client c, String name) {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (clans[j].name.equalsIgnoreCase(name)) {
					addToClan(c.playerId, j);
					return;
				}
			}
		}
		makeClan(c, name);
	}

	public void makeClan(Client c, String name) {
		if (openClan() >= 0) {
			if (validName(name)) {
				c.clanId = openClan();
				clans[c.clanId] = new Clan(c, name);
				addToClan(c.playerId, c.clanId);
				c.inAclan = true;
				c.CSLS = 0;
				clans[c.clanId].CS = 0;
				clans[c.clanId].playerz = 1;

			} else {
				c.sendMessage("A clan with this name already exists.");
			}
		} else {
			c.sendMessage("This clan name has been taken.");
		}
	}

	public void updateClanChat(int clanId) {
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.getPA().sendFrame126("Talking in: @whi@" + clans[clanId].name, 18139);
				c.getPA().sendFrame126("Owner: " + clans[clanId].owner, 18140);
				int slotToFill = 18144;
				/*
				 * if (clans[clanId].members[j] >= 1) { c.CSLS = 0;
				 * Server.clanChat.clans[c.clanId].CS = 0;
				 * Server.clanChat.updateClanChat(c.clanId); return; }
				 */
				if (clans[clanId].CS == 3) {
					c.CSLS = 3;
				}
				if (clans[clanId].CS == 2) {
					c.CSLS = 2;
				}
				if (clans[clanId].CS == 1) {
					c.CSLS = 1;
				}
				if (clans[clanId].CS == 0) {
					c.CSLS = 0;
				}
				for (int i = 0; i < clans[clanId].members.length; i++) {
					if (clans[clanId].members[i] > 0) {
						if (PlayerHandler.players[clans[clanId].members[i]] != null) {
							c.getPA().sendFrame126(PlayerHandler.players[clans[clanId].members[i]].playerName,
									slotToFill);
							slotToFill++;
						}
					}
				}
				for (int k = slotToFill; k < 18244; k++)
					c.getPA().sendFrame126("", k);
			}
		}
	}

	public int openClan() {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] == null || clans[j].owner == "")
				return j;
		}
		return -1;
	}

	public boolean validName(String name) {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (clans[j].name.equalsIgnoreCase(name))
					return false;
			}
		}
		return true;
	}

	public void addToClan(int playerId, int clanId) {
		Client c = (Client) PlayerHandler.players[playerId];
		if (c.inAclan == true) {
			c.sendMessage("You are already in a clan chat!");
			return;
		}
		if (clans[clanId] != null) {
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] <= 0) {
					clans[clanId].members[j] = playerId;
					PlayerHandler.players[playerId].clanId = clanId;
					// c.sendMessage("You have joined the Crib Noob: " + clans[clanId].name);
					/*
					 * if(Server.clanChat.clans[c.clanId].playerz <= 1) {
					 * Server.clanChat.updateClanChat(c.clanId); return; }
					 */
					if (clans[clanId].CS == 3) {
						c.CSLS = 3;
					}
					if (clans[clanId].CS == 2) {
						c.CSLS = 2;
					}
					if (clans[clanId].CS == 1) {
						c.CSLS = 1;
					}
					if (clans[clanId].CS == 0) {
						c.CSLS = 0;
					}
					if (clans[clanId].name.equalsIgnoreCase("Help")) {
						clans[clanId].owner = "@gre@Server";
						// clans[clanId].lootshare = true;
					}
					c.inAclan = true;
					clans[clanId].playerz += 1;
					messageToClan(PlayerHandler.players[playerId].playerName + " has joined the clan, be friendly!",
							clanId);
					updateClanChat(clanId);
					return;
				}
			}
		}
	}

	public void leaveClan(int playerId, int clanId) {
		if (clanId < 0) {
			Client c = (Client) PlayerHandler.players[playerId];
			c.sendMessage("You are not in a clan.");
			return;
		}
		if (clans[clanId] != null) {
			if (PlayerHandler.players[playerId].playerName.equalsIgnoreCase(clans[clanId].owner)) {
				messageToClan("Sorry, The Clan Chat has been ended by the owner.", clanId);
				destructClan(PlayerHandler.players[playerId].clanId);
				return;
			}
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] == playerId) {
					clans[clanId].members[j] = -1;
				}
			}
			if (PlayerHandler.players[playerId] != null) {
				if (clans[clanId].playerz == 2) {
					Server.clanChat.clans[clanId].CS = 0;
					Server.clanChat.clans[clanId].coinshare = false;
				}
				Client c = (Client) PlayerHandler.players[playerId];
				PlayerHandler.players[playerId].clanId = -1;
				c.sendMessage("You have left the clan.");
				c.getPA().clearClanChat();
				c.inAclan = false;
				clans[clanId].playerz -= 1;
				c.CSLS = 0;
			}
			updateClanChat(clanId);
		} else {
			Client c = (Client) PlayerHandler.players[playerId];
			PlayerHandler.players[playerId].clanId = -1;
			c.sendMessage("You are not in a clan.");
		}
	}

	public void destructClan(int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clanId < 0)
				continue;
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				clans[clanId].playerz = 0;
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.clanId = -1;
				c.inAclan = false;
				c.CSLS = 0;
				clans[clanId].CS = 0;
				c.getPA().clearClanChat();
			}
		}
		clans[clanId].members = new int[50];
		clans[clanId].owner = "none";
		clans[clanId].name = "none";
	}

	public void messageToClan(String message, int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] < 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.sendMessage("<img=2>" + message);
			}
		}
	}

	public void playerMessageToClan(int playerId, String message, int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				if (Connection.isMuted(c))
					return; 
				// c.sendMessage("[<col=597105>Clan-Chat</col>] " +
				// Misc.optimizeText(PlayerHandler.players[playerId].playerName) + ":
				// <col=12654363>" + message);

				String newMessage = "[<col=597105>" + clans[clanId].name + "</col>] " + Misc.optimizeText(PlayerHandler.players[playerId].playerName) + ": <col=12654363>" + Misc.ucFirst(message);
				// sendClan(String name, String message, String clan, int rights)
				c.sendClan(PlayerHandler.players[playerId].playerName, newMessage, clans[clanId].name, PlayerHandler.players[playerId].playerRights);
			}
		}
	}

	public void sendLootShareMessage(int clanId, String message) {
		if (clanId >= 0) {
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] <= 0)
					continue;
				if (PlayerHandler.players[clans[clanId].members[j]] != null) {
					Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
					c.sendClan("Lootshare", message, clans[clanId].name, 2);
				}
			}
		}
	}

	public void sendCoinShareMessage(int clanId, String message) {
		if (clanId >= 0) {
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] <= 0)
					continue;
				if (PlayerHandler.players[clans[clanId].members[j]] != null) {
					Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
					c.sendClan("Coinshare", message, clans[clanId].name, 2);
				}
			}
		}
	}

	public void handleLootShare(Client c, int item, int amount) {
		sendLootShareMessage(c.clanId, c.playerName + " has received " + amount + "x " + Item.getItemName(item) + ".");
	}

	public void handleCoinShare(Client c, int item, int amount) {
		try {
			if (c.clanId >= 0) {
				for (int j = 0; j < clans[c.clanId].members.length; j++) {
					if (clans[c.clanId].members[j] <= 0)
						continue;
					if (PlayerHandler.players[clans[c.clanId].members[j]] != null) {
						Client x = (Client) PlayerHandler.players[clans[c.clanId].members[j]];
						int total = c.getShops().getItemShopValue(item) / clans[c.clanId].playerz;
						x.getItems().addItem(995, total);
						x.sendMessage("<col=1532693>You received " + total + " gold as your split of this drop: "
								+ amount + " x " + Item.getItemName(item) + ".</col>");

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}