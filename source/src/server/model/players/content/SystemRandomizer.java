package server.model.players.content;

import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.util.Misc;
import server.model.players.PlayerHandler;
import server.Server;

/*
 * Author: Sk8rdude461
 * Purpose: To create a 'runescape like' mystery box
 * See: http://runescape.wikia.com/wiki/Mystery_box for more info on rewards.
 * Date: 09/15/2013
 */
public class SystemRandomizer {

	public static int SystemRandomizerId = 10521;// Just incase I want a different box id.
	public static boolean deleteBox = true;// Use this for reward testing.
	// Using arrays for item, and amount.
	private static int[][] commonRewards = { { 5223, 1 }, //
			{ 5224, 1 }, //
			{ 5225, 1 }, //
			{ 5226, 1 }, //
			{ 5227, 1 }, //
			{ 5228, 1 }, //
			{ 5229, 1 }, //
			{ 5219, 1 }, //
			{ 4067, 300 }, //
			{ 3748, 1 }, //
			{ 10705, 1 },//
	};
	private static int[][] uncommonRewards = { { 14551, 1 }, //
			{ 19618, 1 }, //
			{ 20081, 1 }, //
			{ 20091, 1 }, //
			{ 20092, 1 }, //
			{ 20094, 1 }, //
			{ 19931, 1 }, //
			{ 14552, 1 }, //
			{ 14553, 1 }, //
			{ 14554, 1 }, //
			{ 14555, 1 }, //
			{ 4639, 1 }, //
			{ 13347, 1 }, //
			{ 4063, 1 }, //
			{ 939, 1 }, //
			{ 19203, 1 }, //
			{ 19200, 1 }, //
			{ 14390, 1 }, //
			{ 4067, 500 },//
	};
	private static int[][] rareRewards = { { 19058, 1 }, //
			{ 19059, 1 }, //
			{ 19061, 1 }, //
			{ 19062, 1 }, //
			{ 19063, 1 }, //
			{ 19939, 1 }, //
			{ 19107, 1 }, //
			{ 19816, 1 }, //
			{ 19817, 1 }, //
			{ 14456, 1 }, //
			{ 19815, 1 }, //
			{ 19830, 1 }, //
			{ 19832, 1 }, //
			{ 19841, 1 }, //
			{ 13196, 1 }, //
			{ 17861, 1 }, //
			{ 19058, 1 }, //
			{ 3075, 1 }, //
			{ 3077, 1 }, //
			{ 3078, 1 }, //
			{ 3079, 1 }, //
			{ 3080, 1 }, //
			{ 15001, 1 }, //
			{ 6931, 1 },//

	};
	private static int[][] veryRareRewards = { { 14012, 1 }, //
			{ 14013, 1 }, //
			{ 14014, 1 }, //
			{ 14015, 1 }, //
			{ 14016, 1 }, //
			{ 14017, 1 }, //
			{ 14021, 1 }, //
			{ 14020, 1 }, //
			{ 14022, 1 }, //
			{ 3081, 1 }, //
			{ 3074, 1 }, //
			{ 3085, 1 }, //
			{ 18379, 1 }, //
			{ 3078, 1 }, //
			{ 15420, 1 }, //
			{ 6542, 1 }, //
			{ 19088, 1 }, //
			{ 19089, 1 }, //
			{ 18384, 1 }, //
			{ 19087, 1 }, //
			{ 19090, 1 }, //
			{ 4649, 1 }, //
			{ 14466, 1 }, //
			{ 20096, 1 }, //
			{ 3087, 1 }, //
			{ 19816, 1 }, //
			{ 19817, 1 }, //
			{ 19822, 1 }, //
			{ 19823, 1 }, //
			{ 19839, 1 }, //
			{ 19840, 1 }, //
			{ 19832, 1 }, //
			{ 19841, 1 }, //
			{ 19834, 1 }, //
			{ 19835, 1 }, //
			{ 19838, 1 }, //
			{ 19837, 1 },//
	};
	private static int[][] UltraRareRewards = { { 4761, 1 }, //
			{ 13997, 1 }, //
			{ 14018, 1 }, //
			{ 14023, 1 }, //
			{ 13997, 1 }, //
			{ 14018, 1 }, //
			{ 14023, 1 }, //
			{ 17877, 1 }, //
			{ 14450, 1 }, //
			{ 20079, 1 }, //
			{ 14457, 1 }, //
			{ 4761, 1 }, //
			{ 17877, 1 }, //
			{ 14450, 1 }, //
			{ 20079, 1 }, //
			{ 14457, 1 }, //
			{ 4761, 1 }, //
			{ 17877, 1 }, //
			{ 14450, 1 }, //
			{ 20079, 1 }, //
			{ 14457, 1 },//

	};

	public static void openBox(Client c) {
		/// This part was just guesstimated, seeing as common would be used more, it has
		/// the highest chance of showing.
		int randomReward = Misc.random(100);// Picking the random number
		int itemToGive = 0, amountToGive = 0, rewardRoll;
		if (randomReward >= 0 && randomReward <= 60) {// For all common items..
			rewardRoll = Misc.random(commonRewards.length - 1);
			itemToGive = commonRewards[rewardRoll][0];
			amountToGive = commonRewards[rewardRoll][1];
		} else if (randomReward >= 61 && randomReward <= 80) {// Uncommon items.
			rewardRoll = Misc.random(uncommonRewards.length - 1);
			itemToGive = uncommonRewards[rewardRoll][0];
			amountToGive = uncommonRewards[rewardRoll][1];
		} else if (randomReward >= 81 && randomReward <= 95) {// Rare items
			rewardRoll = Misc.random(rareRewards.length - 1);
			itemToGive = rareRewards[rewardRoll][0];
			amountToGive = rareRewards[rewardRoll][1];
		} else if (randomReward >= 96 && randomReward <= 99) {// Very Rare items
			rewardRoll = Misc.random(veryRareRewards.length - 1);
			itemToGive = veryRareRewards[rewardRoll][0];
			amountToGive = veryRareRewards[rewardRoll][1];
		} else if (randomReward >= 100) {// Very Rare items
			rewardRoll = Misc.random(UltraRareRewards.length - 1);
			itemToGive = UltraRareRewards[rewardRoll][0];
			amountToGive = UltraRareRewards[rewardRoll][1];
			for (int z = 0; z < PlayerHandler.players.length; z++) {
				if (PlayerHandler.players[z] != null) {
					Client o = (Client) PlayerHandler.players[z];
				}
			}
		}
		if (deleteBox)// Removing the box
			c.getItems().deleteItem(SystemRandomizerId, 1);
		c.getItems().addItem(itemToGive, amountToGive);// Adding reward
		if (itemToGive == 0)
			c.sendMessage("You open the box to find nothing. What bad luck.");// If you got 0, it says you find nothing.
		else {
			c.getItems();
			c.sendMessage("You open the box and find " + (amountToGive > 1 ? "some " : "a ")
					+ ItemAssistant.getItemName(itemToGive) + ".");// If more than one item, use some, otherwise use a.
		}
	}
}