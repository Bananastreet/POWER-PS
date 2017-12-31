package server.model.players.content;

import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class TriviaBot {

	public static boolean firstEventInProgress = false;
	public static String firstString = "";
	public static int rewardId;
	public static int rewardAmount;
	public static int lastEventTimer = 0;
	public static boolean forceFirst = false;
	public static int index = -1;
	public static int randomNum1 = Misc.random(100);
	public static int randomNum2 = Misc.random(1000);
	public static String QUESTIONS[] = { "Regular Donor $10 - Super $25 - Sponsor - $75!",
			"Want to donate? Speak with Seth!", "Website - Www.Power-Ps.Org", "Updating Daily to Improve Gameplay!",
			"Make sure you do ::client to be on the latest client!" };
	public static String ANSWERS[] = { "nike", "" + (randomNum1 + randomNum2), " Power-Ps.", "Paris", "1983", "green",
			"Akio Morita", "Berlin" };
	public static int Question = -1;

	public static void forceFirst() {
		if (firstEventInProgress)
			return;
		forceFirst = true;
	}

	public static void messageToAll(String prefix, String s) {
		for (Player p : PlayerHandler.players) {
			if (p == null)
				continue;

			Client c = (Client) p;

			c.sendMessage("[<col=255>" + prefix + "<col=0>] " + s);
		}
	}

	public static void processEntry(Client player, String command) {
		if (!firstEventInProgress)
			return;

		if (command.equalsIgnoreCase(firstString)) {
			firstEventInProgress = false;
			lastEventTimer = 0;
			player.getItems().addItem(rewardId, rewardAmount);

			if (rewardAmount == 1) {
				messageToAll("Power-Ps",
						"<col=255>" + player.playerName.substring(0, 1).toUpperCase() + player.playerName.substring(1)
								+ " <col=0>has won a <col=255>" + ItemAssistant.getItemName2(rewardId) + "<col=0>!");
			} else {
				String extra = "s";
				if (ItemAssistant.getItemName2(rewardId).endsWith("s"))
					extra = "";
				messageToAll("Power-Ps",
						"<col=255>" + player.playerName.substring(0, 1).toUpperCase() + player.playerName.substring(1)
								+ " <col=0>has won <col=255>" + rewardAmount + " "
								+ ItemAssistant.getItemName2(rewardId) + extra + "<col=0>!");
			}
			rewardId = 0;
			rewardAmount = 0;
			index = -1;
			randomNum1 = Misc.random(1000);
			randomNum2 = Misc.random(1000);
			QUESTIONS[1] = "What is <col=255>" + randomNum1 + "<col=0> + <col=255>" + randomNum2 + "<col=0>?";
			ANSWERS[1] = "" + (randomNum1 + randomNum2);
		}
	}

	public static String constructFirstYell() {
		index = Misc.random(QUESTIONS.length);
		return QUESTIONS[index];
	}

	public static void generateReward() {
		int rewardLevel = Misc.random(50);
		if (rewardLevel < 40) {
			rewardLevel = 1;
		} else if (rewardLevel < 49) {
			rewardLevel = 2;
		} else if (rewardLevel == 50) {
			rewardLevel = 3;
		} else {
			rewardLevel = 1;
		}
		switch (rewardLevel) {
		case 1:
			switch (Misc.random(3)) {
			case 0:
				rewardId = 995;
				rewardAmount = (Misc.random(3) + 1) * 50000;
				break;
			case 1:
				rewardId = 533;
				rewardAmount = (Misc.random(14) + 1) * 10;
				break;
			case 2:
				rewardId = 1624;
				rewardAmount = (Misc.random(9) + 1) * 10;
				break;
			case 3:
				rewardId = 1740;
				rewardAmount = (Misc.random(19) + 1) * 10;
				break;

			}
			break;
		case 2:
			switch (Misc.random(3)) {
			case 0:
				rewardId = 995;
				rewardAmount = (Misc.random(15) + 5) * 50000;
				break;
			case 1:
				rewardId = 537;
				rewardAmount = (Misc.random(14) + 6) * 10;
				break;
			case 2:
				rewardId = 1622;
				rewardAmount = (Misc.random(10) + 10) * 10;
				break;
			case 3:
				rewardId = 386;
				rewardAmount = (Misc.random(80) + 20) * 10;
				break;

			}
			break;
		case 3:
			switch (Misc.random(5)) {
			case 0:
				rewardId = 995;
				rewardAmount = (Misc.random(50) + 10) * 100000;
				break;
			case 1:
				rewardId = 537;
				rewardAmount = (Misc.random(100) + 30) * 10;
				break;
			case 2:
				rewardId = 6199;
				rewardAmount = 1;
				break;
			case 3:
				rewardId = 6739;
				rewardAmount = 1;
				break;
			case 4:
				rewardId = 6585;
				rewardAmount = 1;
				break;
			case 5:
				rewardId = 4718;
				rewardAmount = 1;
				break;
			}
			break;
		}
	}

	public static void executeFirstEvent() {
		forceFirst = true;
		generateReward();
		firstEventInProgress = true;
		lastEventTimer = 600;
		messageToAll("Power-Ps", constructFirstYell());
		firstString = ANSWERS[index];
	}

	public static void process() {
		if ((Misc.random(10000) == 0 || forceFirst) && !firstEventInProgress)
			executeFirstEvent();
		if (lastEventTimer > 0)
			lastEventTimer--;
		if (lastEventTimer == 0)
			firstEventInProgress = false;
	}
}