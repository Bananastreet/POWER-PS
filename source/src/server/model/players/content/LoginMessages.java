package server.model.players.content;

import server.Server;
import server.model.players.*;

/**
* Handles all Login Messages
* @author Gabbe
*/
public class LoginMessages
{
	public static void handleAllLoginMessages(Client c)
	{
		for (int j = 0; j < Server.playerHandler.players.length; j++)
		{
			if (Server.playerHandler.players[j] != null)
			{
				Client c2 = (Client)Server.playerHandler.players[j];
	
				c2.sendMessage("<shad=519160>[" + handleText(c) + "] <shad=800000000>" + c.playerName.toLowerCase()+"</shad><shad=519160> has just logged in!");
			}
		}
	}
	public static void handleOwner(Client c) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
	if (Server.playerHandler.players[j] != null) {
	Client c2 = (Client)Server.playerHandler.players[j];
	if (c.playerName.equalsIgnoreCase("harryl") && c.playerRights == 3) {
	c2.sendMessage("<test.IMG>[Developer] Harryl has just logged in.");
	}
		if (c.playerName.equalsIgnoreCase("") && c.playerRights == 2) {
	c2.sendMessage("<shad=800000000>[Administrator] oh the shah has just logged in.");
	}
	}
	}
	}			
	public static String handleText(Client c)
	{
		switch (c.playerRights)
		{
			case 0: 
				return "Player";
			case 1:
				return "Moderator";
			case 2:
				return "Administrator";
			case 3:
				return "Owner";
			case 4:
				return "Donator";
			case 5:
				return "Super Donator";
			case 6:
				return "Sponsor";
			case 7:
				return "Helper";
			case 8:
				return "Youtuber";
			case 9:
				return "Forum Moderator";
			case 10:
				return "Trusted Dicer";
			case 11:
				return "Head Administrator";
			case 12:
				return "Head Moderator";
			case 13:
				return "Elite Donator";
			case 14:
				return "VIP";
			case 15:
				return "Gfx Manager";	
			case 16:
				return "Youtuber/Helper";	
			case 17:
				return "God Donator";	
			case 18:
				return "Security";	
			case 19:
				return "LTU";					
		}
		return "Player";
	}
}