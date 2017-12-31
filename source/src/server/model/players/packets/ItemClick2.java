package server.model.players.packets;

import server.model.players.Client;
import server.model.players.skills.RuneCraft;
import server.model.players.PacketType;
import server.util.Misc;
import server.Config;
import server.Server;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		
		if (!c.getItems().playerHasItem(itemId,1))
			return;

		switch (itemId) {
                                           case 4155:
                                           c.sendMessage("Your current assignment is " + Server.npcHandler.getNpcListName(c.slayerTask) + "... only " + c.taskAmount + " left to go!");
                                           break;
		case 1438:// Air Talisman
				RuneCraft.locate(c, 2985, 3292);
				break;
			case 1440:// Earth Talisman
				RuneCraft.locate(c, 3306, 3474);
				break;
			case 1442:// Fire Talisman
				RuneCraft.locate(c, 3313, 3255);
				break;
			case 1444:// Water Talisman
				RuneCraft.locate(c, 3185, 3165);
				break;

			case 14096:
			c.getItems().deleteItem(14096, 1);
			c.getItems().addItem(14116, 1);
			break;
			case 14097:
			c.getItems().deleteItem(14097, 1);
			c.getItems().addItem(14117, 1);
			break;
			case 14094:
			c.getItems().deleteItem(14094, 1);
			c.getItems().addItem(14114, 1);
			break;
			case 14095:
			c.getItems().deleteItem(14095, 1);
			c.getItems().addItem(14115, 1);
			break;

			case 14116:
			c.getItems().deleteItem(14116, 1);
			c.getItems().addItem(14120, 1);
			break;
			case 14114:
			c.getItems().deleteItem(14114, 1);
			c.getItems().addItem(14118, 1);
			break;
			case 14115:
			c.getItems().deleteItem(14115, 1);
			c.getItems().addItem(14119, 1);
			break;
			case 14117:
			c.getItems().deleteItem(14117, 1);
			c.getItems().addItem(14121, 1);
			break;

			case 14120:
			c.getItems().deleteItem(14120, 1);
			c.getItems().addItem(14096, 1);
			break;
			case 14118:
			c.getItems().deleteItem(14118, 1);
			c.getItems().addItem(14094, 1);
			break;
			case 14119:
			c.getItems().deleteItem(14119, 1);
			c.getItems().addItem(14095, 1);
			break;
			case 14121:
			c.getItems().deleteItem(14121, 1);
			c.getItems().addItem(14097, 1);
			break;



				
			case 962:
				c.crackcracker();
				c.sendMessage("You have cracked your christmas cracker and received a reward!");
			break;
			case 1446:// Body Talisman
				RuneCraft.locate(c, 3053, 3445);
				break;
			case 1448:// Mind Talisman
				RuneCraft.locate(c, 2982, 3514);
				break;
			case 11283:
			case 11284:
			case 11285:
			c.sendMessage("Your shield has "+c.dfsCount+" charges");
			break;
			case 19476:
			c.sendMessage("You have " + c.ppsLeft + " charges left!");
			break;
 
 
 
 case 11256:
 c.Lootimpjar(11256, c.playerId);
                break;
 case 11254:
 c.Lootimpjar(11254, c.playerId);
                break;
 case 11252:
 c.Lootimpjar(11252, c.playerId);
                break;
 case 11250:
 c.Lootimpjar(11250, c.playerId);
                break;
 case 11246:
 c.Lootimpjar(11246, c.playerId);
                break;
 case 11248:
 c.Lootimpjar(11248, c.playerId);
                break;
 case 11244:
 c.Lootimpjar(11244, c.playerId);
                break;
 case 11240:
 c.Lootimpjar(11240, c.playerId);
                break;
 case 11238:
        c.Lootimpjar(11238, c.playerId);
                break;
		case 15707:
		case 18817:
		case 18823:
		case 18821:
		c.getPA().showInterface(29799);
		break;

			case 11694:

				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11696:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11698:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
			case 11700:
				c.sendMessage("Dismantling has been disabled due to duping");
			break;
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId);
			break;
		}

	}

}
