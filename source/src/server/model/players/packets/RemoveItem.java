package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.*;

/**
 * Remove Item
 **/
public class RemoveItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int interfaceId = c.getInStream().readUnsignedWordA();
		int removeSlot = c.getInStream().readUnsignedWordA();
		int removeId = c.getInStream().readUnsignedWordA();
		int shop = 0;
		int value = 0;
		String name = "null";
	
		switch(interfaceId) {
case 2700:
if(c.occupied[removeSlot] == true && c.storeditems[removeSlot] == removeId) 
{
c.getPA().Frame34(2702, -1, removeSlot, 1);
c.getItems().addItem(removeId, 1);
c.occupied[removeSlot] = false;
c.storeditems[removeSlot] = 0;
						c.getItems().resetTempItems();
						c.getItems().resetBank();
c.totalstored -= 1;
}



break;
			case 1688:
			if(c.inTrade) {
				c.sendMessage("You can't do this while in trade.");
				return;
			}
			c.getItems().removeItem(removeId, removeSlot);
			break;
		
			case 5064:
			if(c.inTrade) {
				c.sendMessage("You can't do this while in trade.");
					return;
			}
			c.getItems().bankItem(removeId, removeSlot, 1);
			/*if(c.storing){
			} else {
					if (removeId == 995) {
						c.sendMessage("You cannot deposit coins");
							return;
					}
						PartyRoom.depositItem(c, removeId, 1);
					}*/
			break;
						case 2274:
			if (removeId == 995) {
				c.sendMessage("You cannt deposit coins");
					return;
			}
//				PartyRoom.withdrawItem(c, removeSlot);
	                break;
			
						case 5382:
			if(c.inTrade) {
				c.sendMessage("You can't do this while in trade.");
				return;
			}
			c.getItems().fromBank(removeId, removeSlot, 1);
			break;
			
			case 3900:
			if(c.inTrade) {
				c.sendMessage("You can't do this while in trade.");
				return;
			}
			c.getShops().buyFromShopPrice(removeId, removeSlot);
			break;
			
			case 3823:
			if(c.inTrade) {
				c.sendMessage("You can't do this while in trade.");
				return;
			}
			c.getShops().sellToShopPrice(removeId, removeSlot);
			break;
			
			case 3322:
			if (!c.canOffer) {
				return;
			}
			if(c.duelStatus <= 0) { 
                c.getTradeAndDuel().tradeItem(removeId, removeSlot, 1);
           	} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 1);
			}
			break;
			
			case 3415:
			if (!c.canOffer) {
				return;
			}
			if(c.duelStatus <= 0) { 
				c.getTradeAndDuel().fromTrade(removeId, removeSlot, 1);
           	} 
			break;
			
			case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 1);
			break;
			
			case 1119:
			case 1120:
			case 1121:
			case 1122:
			case 1123:
				c.getSmithing().readInput(c.playerLevel[c.playerSmithing], Integer.toString(removeId), c, 1);
			break;
		}
	}
			
}
