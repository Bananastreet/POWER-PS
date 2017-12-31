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
public class eventbox {
       
        public static int PresentID = 6183;//Just incase I want a different box id.
        public static boolean deleteBox = true;//Use this for reward testing.
        //Using arrays for item, and amount.
        private static int[][] commonRewards = {
                {15420, 2},//					
			
        };
        private static int[][] uncommonRewards = {
                {19939, 1},//	
                {11310, 1},//
		{19930, 1},//
		{6500, 1},//
		{6501, 1},//
		{6502, 1},//
		{6503, 1},//
		{6504, 1},//
		{6505, 1},//
		{6506, 1},//
		{4448, 1},//
		{4449, 1},//
		{4450, 1},//
		{4451, 1},//
		{4452, 1},//
		{4453, 1},//
		{4454, 1},//
		{3808, 1},//
		{3814, 1},//
		{3815, 1},//
		{3816, 1},//
		{3818, 1},//
		{3820, 1},//
		{3822, 1},//
		{4761, 1},//
		{4762, 1},//
		{4763, 1},//
		{4764, 1},//
		{4765, 1},//
		{4766, 1},//
		{4767, 1},//
		{18477, 1},//
		{18381, 1},//
		{18475, 1},//
		{18481, 1},//
		{18479, 1},//
		{20470, 1},//
		{20469, 1},//
		{20468, 1},//
		{20302, 1},//
		{20305, 1},//
		{20304, 1},//
        };
        private static int[][] rareRewards = {
                {14445, 1},//
                {4646, 1},//
                {11312, 1},//
   		{4785, 1},//		
		{4786, 1},//		
		{4787, 1},//
		{4789, 1},//
		{4790, 1},//
		{4791, 1},//
		{4792, 1},//
		{4794, 1},//
		{4795, 1},//
		{4796, 1},//
		{4797, 1},//
		{4799, 1},//
		{4800, 1},//
		{4801, 1},//
		{4776, 1},//
		{4777, 1},//
		{4779, 1},//
		{4780, 1},//
		{4781, 1},//
		{4782, 1},//
		{4784, 1},//
		{4768, 1},//
		{4769, 1},//
		{4770, 1},//
		{4771, 1},//
		{4772, 1},//
		{4774, 1},//
		{4775, 1},//
		{20489, 1},//
		{20490, 1},//
		{20491, 1},//
		{20492, 1},//
		{20493, 1},//
		{20494, 1},//
		{19122, 1},//
		{19123, 1},//
		{19124, 1},//
        };
        private static int[][] veryRareRewards = {
				{20471, 1},//
				{20482, 1},//
				{20483, 1},//
				{20484, 1},//
				{20485, 1},//
				{20487, 1},//
 				{20486, 1},//				
				{14731, 1},//
				{14730, 1},//
				{14729, 1},//
				{18447, 1},//
				{18449, 1},//
				{18451, 1},//
				{18453, 1},//
				{18455, 1},//
        };
		  private static int[][] UltraRareRewards = {
                {3084, 1},// 
                {3077, 1},//				
                {3070, 1},//
                {3079, 1},//
                {3080, 1},//
                {3081, 1},//
                {3082, 1},//
                {3083, 1},//
		{3085, 1},//
		{3065, 1},//
		{3066, 1},//
		{3068, 1},//
		{3067, 1},//
		{3069, 1},//
		{3071, 1},//
		{3072, 1},//
		{3073, 1},//
		{3074, 1},//
		{3075, 1},//
		{3076, 1},//
		{3078, 1},//				
        };
       
        public static void openBox(Client c){
                ///This part was just guesstimated, seeing as common would be used more, it has the highest chance of showing.
                int randomReward = Misc.random(61);//Picking the random number
                int itemToGive = 0, amountToGive = 0, rewardRoll;
                if(randomReward >= 0 && randomReward <= 30){//For all common items..
                        rewardRoll = Misc.random(commonRewards.length-1);
                        itemToGive = commonRewards[rewardRoll][0];
                        amountToGive = commonRewards[rewardRoll][1];
                }else if(randomReward >= 31 && randomReward <= 40){//Uncommon items.
                        rewardRoll = Misc.random(uncommonRewards.length-1);
                        itemToGive = uncommonRewards[rewardRoll][0];
                        amountToGive = uncommonRewards[rewardRoll][1];
                }else if(randomReward >= 41 && randomReward <= 50){//Rare items
                        rewardRoll = Misc.random(rareRewards.length-1);
                        itemToGive = rareRewards[rewardRoll][0];
                        amountToGive = rareRewards[rewardRoll][1];
                }else if(randomReward >= 51 && randomReward <= 60){//Very Rare items
                        rewardRoll = Misc.random(veryRareRewards.length-1);
                        itemToGive = veryRareRewards[rewardRoll][0];
                        amountToGive = veryRareRewards[rewardRoll][1];
                }else if(randomReward >= 61){//Very Rare items
                        rewardRoll = Misc.random(UltraRareRewards.length-1);
                        itemToGive = UltraRareRewards[rewardRoll][0];
                        amountToGive = UltraRareRewards[rewardRoll][1];
								for (int z = 0; z < Server.playerHandler.players.length; z++) {
							if (Server.playerHandler.players[z] != null) {
								Client o = (Client) Server.playerHandler.players[z];
								o.sendMessage("<col=9182198>["+Misc.optimizeText(c.playerName)+"]</col> <col=9182198>has just received a Ultra very rare loot from Crystal Heart!");
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
