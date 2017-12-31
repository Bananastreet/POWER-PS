package server.model.players.content;
 
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
public class smyster {
       
        public static int PresentID = 6833;//Just incase I want a different box id.
        public static boolean deleteBox = true;//Use this for reward testing.
        //Using arrays for item, and amount.
        private static int[][] commonRewards = {
				{20302, 1},//	
				{20305, 1},//	
				{20304, 1},//	
				{19960, 1},//				
			
        };
        private static int[][] uncommonRewards = {
                {17867, 1},//
				{7808, 1},//
				{8810, 1},//	
				{8811, 1},//	
				{8812, 1},//
				{12268, 1},//
				{12269, 1},//	
				{12256, 1},//
				{12257, 1},//
				{12258, 1},//	
				{12289, 1},//	
				{12290, 1},//
				{12291, 1},//	
				{12262, 1},//					
				{12263, 1},//					
				{12264, 1},//	
				{939, 1},//					
        };
        private static int[][] rareRewards = {
                {20300, 1},//
                {20489, 1},//
                {20490, 1},//
                {20491, 1},//
                {20492, 1},//
                {20493, 1},//
                {20494, 1},//	
                {20482, 1},//
                {20483, 1},//
                {20484, 1},//
                {20485, 1},//
                {20486, 1},//
                {20487, 1},//	
                {4646, 1},//
                {14445, 1},//	
                {12265, 1},//	
                {12266, 1},//
                {12267, 1},//	
                {20496, 1},//
                {20495, 1},//				
				
							
        };
        private static int[][] veryRareRewards = {
				{11014, 1},// 
				{19930, 1},//
				{12286, 1},//	
				{12287, 1},//
				{12288, 1},//	
				{12274, 1},//	
				{12275, 1},//
				{12276, 1},//
                {6542, 1},//				
			
        };
		  private static int[][] UltraRareRewards = {
                {20481, 1},//
				{13663, 1},// 				
                {20480, 1},//				
                {20479, 1},//
                {20478, 1},//
                {20477, 1},//
                {20476, 1},//
                {20472, 1},//
                {20471, 1},//
                {20472, 1},//
                {20471, 1},//					
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
								for (int z = 0; z < Server.playerHandler.players.length; z++) {
							if (Server.playerHandler.players[z] != null) {
								Client o = (Client) Server.playerHandler.players[z];
								o.sendMessage("<col=16711680>["+Misc.optimizeText(c.playerName)+"]</col> <col=16713983>has just received a very rare loot from Super Mysterious Box!");
							}
						}
                }
                if(deleteBox)//Removing the box
                        c.getItems().deleteItem(PresentID, 1);
                c.getItems().addItem(itemToGive, amountToGive);//Adding reward
                if(itemToGive == 0)
                        c.sendMessage("You open the box to find nothing. What bad luck.");//If you got 0, it says you find nothing.
                else
                        c.sendMessage("You open the box and find " + (amountToGive > 1 ? "some " : "a ")
                                        + c.getItems().getItemName(itemToGive) + ".");//If more than one item, use some, otherwise use a.
        }
}
