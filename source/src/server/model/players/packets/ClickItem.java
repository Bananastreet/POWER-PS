package server.model.players.packets;
import server.util.Misc;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerAssistant;
import server.model.players.content.MysteryBox;
import server.model.players.content.Present;
import server.model.players.content.SystemRandomizer;
import server.model.players.content.cheart;
import server.model.players.content.eventbox;
import server.model.players.content.heart;
import server.model.players.content.heartt;
import server.model.players.content.weaponbox;
import server.Server;
import server.Config;
import server.model.minigames.*;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {
                      
public static int flower[] = {2980,2981,2982,2983,2984,2985,2986,2987};
            public int randomflower() {
                return flower[(int)(Math.random()*flower.length)];
            }
	public static int flowerX = 0;
	public static int flowerY = 0;
	public static int flowerTime = -1;
	public static int flowers = 0;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		c.setLastClicked(itemId);
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
			
			
			
			
			
			
			
			
		}
		if(itemId == 6542 ) {
		c.isDonator = 1; 
        c.playerRights = 4; 
		c.getItems().deleteItem(6542, 1);
        c.logout(); 
		}
		
		if(itemId == 19039 ) {
		c.issDonator = 1; 
		c.isDonator = 1; 
        c.playerRights = 6;
		c.Dicer = 1;
		c.getItems().addItemToBank(15098, 1);		
		c.getItems().deleteItem(19039, 1);
        c.logout(); 
		}
		if(itemId == 13663 ) {
		c.issDonator = 1; 
		c.isDonator = 1; 
        c.playerRights = 5;
		c.Dicer = 1;
		c.getItems().addItemToBank(15098, 1);		
		c.getItems().deleteItem(13663, 1);
        c.logout(); 		
		
}

		if(itemId == 15098) {

		if(c.inWild()) {
		        c.sendMessage("Er, it's not to smart to do this in the Wilderness.");
			return;
                }
                if (c.Dicer == 0) {
                	c.sendMessage("You need a dicer rank to dice, This Is A Scam Precaution.");
					return;
                }
				if (System.currentTimeMillis() - c.diceDelay >= 1200) {
                c.forcedText = "["+ c.playerName +"] Just Rolled "+ Misc.random(100) +" On The Dice!";
                c.forcedChatUpdateRequired = true;
            	c.updateRequired = true;
				c.diceDelay = System.currentTimeMillis();
}
		}
												if(itemId == 15262) {
                   c.getItems().deleteItem(15262, 1);
                   c.getItems().addItem(18016, 15000);
                }   // SUMMON SHARDS
		if (itemId == 15084){
			c.getDH().sendDialogues(106, 4289);
		}
                                           if (itemId == 6722){
			c.sendMessage("You Redeem Your Spin Token For one spin!");
                                                                c.spinsLe += 1;
		}

if (itemId == 18778){
c.getDH().sendDialogues(33, 0);
}
		if (itemId == 18779){
c.getDH().sendDialogues(33, 0);
		}
		if (itemId >= 5070 && itemId <= 5074){
			c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId), 1);
			c.getItems().addItem(5075, 1);
			c.getItems().addItem(c.getPA().randomBirdNests(), 10);
		}
		if (itemId == 18780){
			c.getDH().sendDialogues(33, 0);
	}
		if (itemId == 18781){
					c.getDH().sendDialogues(33, 0);
			}
			
		if (itemId == 18782){
			c.getPA().showInterface(2808);
			c.sendMessage("Once you click the skill desired your experience will be given. Choose carefully.");
		}

		
			
		if (itemId == 7498){
			c.getPA().showInterface(2808);
			c.sendMessage("Once you click the skill desired your experience will be given. Choose carefully.");
		}

		if (itemId == 2714) { // Easy Clue Scroll Casket
			c.getItems().deleteItem(itemId, 1);
			TreasureTrails.addClueReward(c, 0);
		}
		if (itemId == 2802) { // Medium Clue Scroll Casket
			c.getItems().deleteItem(itemId, 1);
			TreasureTrails.addClueReward(c, 1);
		}
		if (itemId == 2775) { // Hard Clue Scroll Casket
			c.getItems().deleteItem(itemId, 1);
			TreasureTrails.addClueReward(c, 2);
		}
		if(itemId == 2713) {
			c.getPA().showInterface(17537);
		}
		if(itemId == 2712) {
			c.getPA().showInterface(9043);
		}
		if(itemId == 2711) {
			c.getPA().showInterface(7271);
		}
		if(itemId == 2710) {
			c.getPA().showInterface(7045);
		}
		if(itemId == 2709) {
		c.getPA().showInterface(9275);
		}
		if(itemId == 2708) {
		c.getPA().showInterface(7113);
		}
		if(itemId == 2707) {
		c.getPA().showInterface(17634);
		}
		if(itemId == 2706) {
		c.getItems().deleteItem(2706, c.getItems().getItemSlot(2706), 1);
				c.getItems().addItem(2802,1);
				c.sendMessage("You recieve a MEDIUM Casket!");
		}
		if(itemId == 2705) {
		c.getPA().showInterface(4305);
		}
		if(itemId == 2704) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("In a liar of a Boss lies", 6971);
			c.getPA().sendFrame126("the next clue scroll!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2703) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("I seek another cluse just", 6971);
			c.getPA().sendFrame126("west of the fountain, at the origin!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2702) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("We are here lying to protect", 6971);
			c.getPA().sendFrame126("the castle that we truely love!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2701) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("This has to be bob's favorite", 6971);
			c.getPA().sendFrame126("training spot in-game.", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2700) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("We all love water, especially", 6971);
			c.getPA().sendFrame126("from big, clean, fountains!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2699) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("I love to eat cake, maybe", 6971);
			c.getPA().sendFrame126("you want to seal some?", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2698) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("We stall seek history within", 6971);
			c.getPA().sendFrame126("the ancient museum.", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2697) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("We pay to Pk, especially", 6971);
			c.getPA().sendFrame126("within a city named Falador.", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2696) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("Rats! Rats! Rats!", 6971);
			c.getPA().sendFrame126("The sewers are full of them!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2695) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("These fish must be hot!", 6971);
			c.getPA().sendFrame126("We shall call this, Lava Fishing", 6972);
			c.getPA().showInterface(6965);
		}
		//if(itemId == 2694) {
		//c.sendMessage("My loved one..Once murdered in front of my eyes..I couldn't save her..");
		//}
		if(itemId == 2693) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("This village contains torches,", 6971);
			c.getPA().sendFrame126("rocks, and some kind of stronghold.", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2692) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("We shall thieve Master Farmers!", 6971);
			c.getPA().sendFrame126("I wonder where I can find them...", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2691) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("arggggghhh mate,", 6971);
			c.getPA().sendFrame126("Would you like some beer?", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2690) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("Cabbage!", 6971);
			c.getPA().sendFrame126("Lots, and lots of Cabbage!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2689) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("Ew, a scorpian.", 6971);
			c.getPA().sendFrame126("Why are these mines so messed up!", 6972);
			c.getPA().showInterface(6965);
		}
		if(itemId == 2688) {
		for(int i = 6968; i < 6976; i++) {
			c.getPA().sendFrame126("", i);
		}
			c.getPA().sendFrame126("I seek many, many, banana trees.", 6971);
			c.getPA().sendFrame126("Do you know where it is?", 6972);
			c.getPA().showInterface(6965);
		}
				/**Hard Clue Scroll**/
		if(c.safeAreas(2969, 3411, 2974, 3415) & (c.getItems().playerHasItem(2713,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2713, c.getItems().getItemSlot(2713), 1);
				c.getItems().addItem(2712,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(2613, 3075, 2619, 3080) & (c.getItems().playerHasItem(2712,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2712, c.getItems().getItemSlot(2712), 1);
				c.getItems().addItem(2711,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3030, 3394, 3049, 3401) & (c.getItems().playerHasItem(2711,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2711, c.getItems().getItemSlot(2711), 1);
				c.getItems().addItem(2775,1);
				c.sendMessage("You recieve a HARD Casket!");
			} else if(c.safeAreas(3285, 3371, 3291, 3375) & (c.getItems().playerHasItem(2710,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2710, c.getItems().getItemSlot(2710), 1);
				c.getItems().addItem(2709,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3106, 3148, 3113, 3154) & (c.getItems().playerHasItem(2709,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2709, c.getItems().getItemSlot(2709), 1);
				c.getItems().addItem(2708,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3092, 3213, 3104, 3225) & (c.getItems().playerHasItem(2708,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2708, c.getItems().getItemSlot(2708), 1);
				c.getItems().addItem(2775,1);
				c.sendMessage("You recieve a HARD Casket!");
			} else if(c.safeAreas(2719, 3336, 2725, 3339) & (c.getItems().playerHasItem(2707,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2707, c.getItems().getItemSlot(2707), 1);
				c.getItems().addItem(2706,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3301, 3684, 3313, 3698) & (c.getItems().playerHasItem(2706,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2706, c.getItems().getItemSlot(2706), 1);
				c.getItems().addItem(2705,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(2903, 3287, 2909, 3300) & (c.getItems().playerHasItem(2705,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2705, c.getItems().getItemSlot(2705), 1);
				c.getItems().addItem(2775,1);
				c.sendMessage("You recieve a HARD Casket!");
			/**Easy Clue Scrolls**/
			} else if(c.safeAreas(2259, 4680, 2287, 4711) & (c.getItems().playerHasItem(2704,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2704, c.getItems().getItemSlot(2704), 1);
				c.getItems().addItem(2703,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3217, 3207, 3225, 3213) & (c.getItems().playerHasItem(2703,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2703, c.getItems().getItemSlot(2703), 1);
				c.getItems().addItem(2702,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(2962, 3331, 2987, 3351) & (c.getItems().playerHasItem(2702,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2702, c.getItems().getItemSlot(2702), 1);
				c.getItems().addItem(2714,1);
				c.sendMessage("You recieve a EASY Casket!");
			} else if(c.safeAreas(3253, 3256, 3265, 3296) & (c.getItems().playerHasItem(2701,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2701, c.getItems().getItemSlot(2701), 1);
				c.getItems().addItem(2700,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3208, 3421, 3220, 3435) & (c.getItems().playerHasItem(2700,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2700, c.getItems().getItemSlot(2700), 1);
				c.getItems().addItem(2699,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3084, 3486, 3086, 3488) & (c.getItems().playerHasItem(2699,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2699, c.getItems().getItemSlot(2699), 1);
				c.getItems().addItem(2698,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3253, 3445, 3261, 3453) & (c.getItems().playerHasItem(2698,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2698, c.getItems().getItemSlot(2698), 1);
				c.getItems().addItem(2714,1);
				c.sendMessage("You recieve a EASY Casket!");
			/**Medium Clue Scrolls**/
			} else if(c.safeAreas(2953, 3365, 2977, 3392) & (c.getItems().playerHasItem(2697,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2697, c.getItems().getItemSlot(2697), 1);
				c.getItems().addItem(2696,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3228, 9860, 3259, 9873) & (c.getItems().playerHasItem(2696,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2696, c.getItems().getItemSlot(2696), 1);
				c.getItems().addItem(2695,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(2875, 9763, 2904, 9776) & (c.getItems().playerHasItem(2695,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2695, c.getItems().getItemSlot(2695), 1);
				c.getItems().addItem(2802,1);
				c.sendMessage("You recieve a MEDIUM Casket!");
			} else if(c.safeAreas(3074, 3407, 3085, 3436) & (c.getItems().playerHasItem(2693,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2693, c.getItems().getItemSlot(2693), 1);
				c.getItems().addItem(2692,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3074, 3245, 3085, 3255) & (c.getItems().playerHasItem(2692,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2692, c.getItems().getItemSlot(2692), 1);
				c.getItems().addItem(2691,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3044, 3255, 3055, 3259) & (c.getItems().playerHasItem(2691,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2691, c.getItems().getItemSlot(2691), 1);
				c.getItems().addItem(2802,1);
				c.sendMessage("You recieve a MEDIUM Casket!");
			} else if(c.safeAreas(3041, 3284, 3067, 3298) & (c.getItems().playerHasItem(2690,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2690, c.getItems().getItemSlot(2690), 1);
				c.getItems().addItem(2689,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(3032, 9756, 3056, 9804) & (c.getItems().playerHasItem(2689,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2689, c.getItems().getItemSlot(2689), 1);
				c.getItems().addItem(2688,1);
				c.sendMessage("You recieve another scroll.");
			} else if(c.safeAreas(2906, 3155, 2926, 3175) & (c.getItems().playerHasItem(2688,1))) {
				c.getPA().removeAllWindows();
				c.getItems().deleteItem(2688, c.getItems().getItemSlot(2688), 1);
				c.getItems().addItem(2802,1);
				c.sendMessage("You recieve a MEDIUM Casket!");
			}


		if(itemId == 11949) {
		c.makesnow();
		c.stopMovement();
                c.sendMessage("<shad=838383>You Have "+c.present+"/15 Presents!");
                c.sendMessage("<shad=838383>There is a Ghost at every lodestone Area Hidden in houses!");
		}

                if(itemId == 13495) {
                if(c.AuraEquiped > 1){
                c.getItems().addItem(c.AuraEquiped, 1);
                }
                c.AuraEquiped = 13495;
                c.getPA().sendFrame34(c.AuraEquiped, 0, 10794, 1);
                c.AuraEffect = 1;
                c.getItems().deleteItem(13495,c.getItems().getItemSlot(13495),1);
                }
         
				
				if (itemId == 6199) {
				MysteryBox.openBox(c);
				}
if (itemId == 10521) {
SystemRandomizer.openBox(c);
}
				if (itemId == 6183) {
				eventbox.openBox(c);
				}						        				
				if (itemId == 15420) {
				Present.openBox(c);
				}
				if (itemId == 744) {
				cheart.openBox(c);
				}
				if (itemId == 745) {
				heart.openBox(c);
				}	
				if (itemId == 6833) {
				heartt.openBox(c);
				}					
				if (itemId == 15668) {
				weaponbox.openBox(c);
				}					
	if (itemId == 9721) {
c.getPA().showInterface(6965);
c.getPA().sendFrame126("@red@~ Overload Instruction Manual ~",6968);
c.getPA().sendFrame126("",6969);
c.getPA().sendFrame126("@gre@Step 1: @cya@Get a Herblore level of 99.",6970);
c.getPA().sendFrame126("@gre@Step 2: @cya@Have all Extreme Potions ",6971);
c.getPA().sendFrame126("@cya@In your inventory, along with a",6972);
c.getPA().sendFrame126("@gre@Step 3: @cya@Cleaned torstol ,use the",6973);
c.getPA().sendFrame126("@cya@ Torstol on an extreme potion.",6974);
c.getPA().sendFrame126("@gre@Step 4: @cya@You now have an overload!",6975);
c.getPA().sendFrame126("",6976);
c.getPA().sendFrame126("",6977);
c.getPA().sendFrame126("",6978);
c.getPA().sendFrame126("",6979);
c.getPA().sendFrame126("",6980);
}

if(itemId == 299) {
flowers = randomflower();
flowerX += c.absX;
flowerY += c.absY;
c.getPA().object(flowers, c.absX, c.absY, 0, 10);
c.sendMessage("You plant the seed...");
c.getItems().deleteItem(299, 1);
c.getPA().walkTo(-1,0); 
c.getDH().sendDialogues(9999, -1);   
}

if (itemId == 1856) {
c.getPA().showInterface(6965);
c.getPA().sendFrame126("@red@~ Duel Arena - Known Bugs ~",6968);
c.getPA().sendFrame126("",6969);
c.getPA().sendFrame126("@gre@1.@cya@Dueling is at your own risk.",6970);
c.getPA().sendFrame126("@cya@If you loose items to players/bugs",6971);
c.getPA().sendFrame126("@cya@ YOU WILL NOT GET YOUR ITEMS BACK.",6972);
c.getPA().sendFrame126("@gre@2.@cya@FORFEITING DOES NOT WORK!!",6973);
c.getPA().sendFrame126("@gre@3.@cya@FUN WEAPONS ARE DISABLED",6974);
c.getPA().sendFrame126("@gre@4.@cya@ROCKTAILS COUNTS AS FOOD! BE CAREFUL",6975);
c.getPA().sendFrame126("",6976);
c.getPA().sendFrame126("",6977);
c.getPA().sendFrame126("",6978);
c.getPA().sendFrame126("",6979);
c.getPA().sendFrame126("",6980);
}

if (itemId == 9719) {
c.getPA().showInterface(6965);
c.getPA().sendFrame126("@red@~ Extremes Instruction Manual ~",6968);
c.getPA().sendFrame126("",6969);
c.getPA().sendFrame126("@gre@Step 1: @cya@Get a Herblore level of 80.",6970);
c.getPA().sendFrame126("@gre@Step 2: @cya@Have a super potion (3) and ",6971);
c.getPA().sendFrame126("@cya@ the required cleaned herb.",6972);
c.getPA().sendFrame126("@gre@Step 3: @cya@Use the herb on the super potion",6973);
c.getPA().sendFrame126("@cya@ You will now get a extreme potion (3)",6974);
c.getPA().sendFrame126("@gre@Step 4: @cya@Use RS Wiki for herbs req's",6975);
c.getPA().sendFrame126("",6976);
c.getPA().sendFrame126("",6977);
c.getPA().sendFrame126("",6978);
c.getPA().sendFrame126("",6979);
c.getPA().sendFrame126("",6980);
}

				if(itemId == 8013) {
						if (c.inWarriorG()) {
		return;
		}
				                if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
		                   c.getPA().teleTabTeleport(2059, 3143, 4, "house");
						   c.sendMessage("You teleport into you're custom place..");
                   c.getItems().deleteItem(8013,c.getItems().getItemSlot(8013),1);
                }

				if(itemId == 15707) {
                  c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				   c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				    //c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
                }   
								if(itemId == 18821) {
                   c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				   c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				   // c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
                } 
								if(itemId == 18817) {
                  c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				   //c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				   // c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
                }  
								if(itemId == 18823) {
                    c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				   //c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				    //c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
                }  
				
				if(itemId == 8007) {
				                if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if (c.inWarriorG()) {
		return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
		                c.getPA().teleTabTeleport(3216,3424,0, "teleTab");
						c.getItems().deleteItem(8007,c.getItems().getItemSlot(8007),1);
                } 
				if(itemId == 8008) {
						if (c.inWarriorG()) {
		return;
		}
				                if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
		                   c.getPA().teleTabTeleport(3221, 3217, 0, "teleTab");
                   c.getItems().deleteItem(8008,c.getItems().getItemSlot(8008),1);
                }
              if(itemId == 8009) {
			  		if (c.inWarriorG()) {
		return;
		}
			                  if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
			                     c.getPA().teleTabTeleport(2964, 3380, 0, "teleTab");
                   c.getItems().deleteItem(8009,c.getItems().getItemSlot(8009),1);
                }
            if(itemId == 8010) {
					if (c.inWarriorG()) {
		return;
		}
			                if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
			c.getPA().teleTabTeleport(2756, 3479, 0, "teleTab");
                  c.getItems().deleteItem(8010,c.getItems().getItemSlot(8010),1);
                }
          if(itemId == 8011) {
		  		if (c.inWarriorG()) {
		return;
		}
		                  if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
		  c.getPA().teleTabTeleport(2661, 3306, 0, "teleTab");
		  c.foodDelay = System.currentTimeMillis();
                   c.getItems().deleteItem(8011,c.getItems().getItemSlot(8011),1);
				}
          if(itemId == 8012) {
		                  if(c.inPits) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
                if(c.getPA().inPitsWait()) {
                        c.sendMessage("You can't teleport during Fight Pits.");
                        return;
                }
        if(c.Jail == true){
                c.sendMessage("You can't teleport out of Jail!");
        } 
	if(c.inJail() && c.Jail == true) {
                c.sendMessage("You can't teleport out oaf Jail!");
 	} 
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.InDung) {
			c.sendMessage("You can't teleport from here moron");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(c.inNomad()) {
			c.sendMessage("You can't teleport during Nomad Minigame");
			return;
		}
		if(c.inGoblin()) {
			c.sendMessage("You can't teleport during Goblin Minigame");
			return;
		}
		          c.getPA().teleTabTeleport(2549, 3113, 0, "teleTab");
                  c.getItems().deleteItem(8012,c.getItems().getItemSlot(8012),1);
                }
		if(itemId == 4447) {	
			c.getPA().addSkillXP(3000, 24);
			c.sendMessage("You rub the lamp and feel yourself further in the arts of dungeoneering.");
			c.getItems().deleteItem(4447, 1);	
		}
		if(itemId == 15262) {
			c.getItems().addItem(18016, 10000);
			c.getItems().deleteItem(15262, 1);
		}
		if(itemId == 8002) {
			if (c.playerLevel[15] >= 100) { 
				if (System.currentTimeMillis() - c.buryDelay > 3000) {
						c.getItems().deleteItem(8002, 1);
						c.getItems().addItem(8003, 1);
						c.startAnimation(885);
						c.sendMessage("You Clean the Grimy Lavender.");
						c.getPA().addSkillXP(50000, c.playerHerblore);
						c.buryDelay = System.currentTimeMillis();
				} else {
					return;
				}
			} else {
				c.sendMessage("You need 100 Herblore to do this.");
				return;
			}
		}
		if(itemId == 8005) {
			if (c.playerLevel[15] >= 110) { 
				if (System.currentTimeMillis() - c.buryDelay > 3000) {
						c.getItems().deleteItem(8005, 1);
						c.getItems().addItem(8006, 1);
						c.startAnimation(885);
						c.sendMessage("You Clean the Grimy Rose.");
						c.getPA().addSkillXP(80000, c.playerHerblore);
						c.buryDelay = System.currentTimeMillis();
				} else {
					return;
				}
			} else {
				c.sendMessage("You need 110 Herblore to do this.");
				return;
			}
		}
		if(itemId == 18751) {
			if (c.playerLevel[15] >= 115) { 
				if (System.currentTimeMillis() - c.buryDelay > 3000) {
						c.getItems().deleteItem(18751, 1);
						c.getItems().addItem(18750, 1);
						c.startAnimation(885);
						c.sendMessage("You Clean the Grimy Orchid.");
						c.getPA().addSkillXP(120000, c.playerHerblore);
						c.buryDelay = System.currentTimeMillis();
				} else {
					return;
				}
			} else {
				c.sendMessage("You need 115 Herblore to do this.");
				return;
			}
		}
		if(itemId == 19940) {
			if (c.playerLevel[15] >= 125) { 
				if (System.currentTimeMillis() - c.buryDelay > 3000) {
						c.getItems().deleteItem(19940, 1);
						c.getItems().addItem(19941, 1);
						c.startAnimation(885);
						c.sendMessage("You Clean the Grimy Tulip.");
						c.getPA().addSkillXP(150000, c.playerHerblore);
						c.buryDelay = System.currentTimeMillis();
				} else {
					return;
				}
			} else {
				c.sendMessage("You need 125 Herblore to do this.");
				return;
			}
		}

		
			
		if(itemId == 19775) {
			c.playerLevel[0] = 99;
			c.playerLevel[2] = 99;
			c.playerLevel[3] = 99;
			c.playerLevel[4] = 99;
			c.playerLevel[6] = 99;
			c.playerXP[0] = c.getPA().getXPForLevel(100);
			c.playerXP[2] = c.getPA().getXPForLevel(100);
			c.playerXP[3] = c.getPA().getXPForLevel(100);
			c.playerXP[4] = c.getPA().getXPForLevel(100);
			c.playerXP[6] = c.getPA().getXPForLevel(100);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			c.getItems().deleteItem(19775, 1);
			c.sendMessage("You use the MASTER combat Ring");
			}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}

		if (itemId == 3265) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(3265, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 1;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
			c.sendMessage("TROLOLOLOLOLOLL.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}

if (itemId == 7992) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(7992, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 19;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Big Swordfish.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 7994) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(7994, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 25;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Big Shark.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 3301) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(3301, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 27;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Whale.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 3303) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(3303, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 30;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Stingray.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}



		if (itemId == 2528) {
                                           c.lampfix = 5;
		c.sendMessage("You rub the 750K xp Lamp.");
                c.getPA().showInterface(35000);
		}
		if (itemId == 11137) {
                                           c.lampfix = 10;
		c.sendMessage("You rub the xp Lamp.");
                c.getPA().showInterface(35000);
		}
		if (itemId == 19750) {
                                           c.lampfix = 15;
		c.sendMessage("You rub the xp Lamp.");
                c.getPA().showInterface(35000);
		}
		if (itemId == 11850) {
		c.getItems().deleteItem(11850,1);
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		}
		if (itemId == 11852) {
		c.getItems().deleteItem(11852,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11854) {
		c.getItems().deleteItem(11854,1);
		c.getItems().addItem(4745,1);
		c.getItems().addItem(4747,1);
		c.getItems().addItem(4749,1);
		c.getItems().addItem(4751,1);
		}
		if (itemId == 11856) {
		c.getItems().deleteItem(11856,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11848) {
		c.getItems().deleteItem(11848,1);
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		}
		if (itemId == 11846) {
		c.getItems().deleteItem(11846,1);
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		}
		if (itemId == 5070) { // BIRD'S NEST
				c.getItems().addItem(995, 100000);
				c.getItems().deleteItem(5070, 1);
				c.sendMessage("The nest contains 100k coins!");
				}
		/*if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		*/
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().checkGrimy(itemId, 0))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if (c.inArea(3553, 3301, 3561, 3294) && c.playerName == "h20") {
					c.sendMessage("hello");
		}
		}
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
		
			}
		}
	}

}
