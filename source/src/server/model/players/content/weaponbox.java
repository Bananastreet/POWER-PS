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
public class weaponbox {
       
        public static int mysteryBoxID = 15668;//Just incase I want a different box id.
        public static boolean deleteBox = true;//Use this for reward testing.
        //Using arrays for item, and amount.
        private static int[][] commonRewards = {
				{17877, 1},//
                {17837, 1},//
                {19939, 1},//				
        };
        private static int[][] uncommonRewards = {
                {20500, 1},//
                {19930, 1},//				
        };
        private static int[][] rareRewards = {
                {3892, 1},//
				
        };
        private static int[][] veryRareRewards = {
				{12272, 1},// 
				{4646, 1},//
 				{934, 1},// 
				{12280, 1},// 
				{11530, 1},//
                {14445, 1},//				
 				{20498, 1},//
				{20496, 1},// 
                {17867, 1},//
                {14445, 1},//	
                {7808, 1},//
                {20300, 1},//	
                {20497, 1},// 				
        };
		  private static int[][] UltraRareRewards = {
                {20471, 1},//
                {19468, 1},//				
        };
       
        public static void openBox(Client c){
                ///This part was just guesstimated, seeing as common would be used more, it has the highest chance of showing.
                int randomReward = Misc.random(100);//Picking the random number
                int itemToGive = 0, amountToGive = 0, rewardRoll;
                if(randomReward >= 0 && randomReward <= 60){//For all common items..
                        rewardRoll = Misc.random(commonRewards.length-1);
                        itemToGive = commonRewards[rewardRoll][0];
                        amountToGive = commonRewards[rewardRoll][1];
                }else if(randomReward >= 61 && randomReward <= 80){//Uncommon items.
                        rewardRoll = Misc.random(uncommonRewards.length-1);
                        itemToGive = uncommonRewards[rewardRoll][0];
                        amountToGive = uncommonRewards[rewardRoll][1];
                }else if(randomReward >= 81 && randomReward <= 95){//Rare items
                        rewardRoll = Misc.random(rareRewards.length-1);
                        itemToGive = rareRewards[rewardRoll][0];
                        amountToGive = rareRewards[rewardRoll][1];
                }else if(randomReward >= 96 && randomReward <= 99){//Very Rare items
                        rewardRoll = Misc.random(veryRareRewards.length-1);
                        itemToGive = veryRareRewards[rewardRoll][0];
                        amountToGive = veryRareRewards[rewardRoll][1];
                }else if(randomReward >= 100){//Very Rare items
                        rewardRoll = Misc.random(UltraRareRewards.length-1);
                        itemToGive = UltraRareRewards[rewardRoll][0];
                        amountToGive = UltraRareRewards[rewardRoll][1];
						PlayerHandler.sendGlobalMessage("<col=16711680>["+Misc.optimizeText(c.playerName)+"]</col> <col=15015938>has just received a very rare item from weapon box!");
                }
                if(deleteBox)//Removing the box
                        c.getItems().deleteItem(mysteryBoxID, 1);
                c.getItems().addItem(itemToGive, amountToGive);//Adding reward
                if(itemToGive == 0)
                        c.sendMessage("You open the box to find nothing. What bad luck.");//If you got 0, it says you find nothing.
                else
                        c.sendMessage("You open the box and find " + (amountToGive > 1 ? "some " : "a ")
                                        + ItemAssistant.getItemName(itemToGive) + ".");//If more than one item, use some, otherwise use a.
        }
}
