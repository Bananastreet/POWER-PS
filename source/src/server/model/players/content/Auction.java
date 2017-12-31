package server.model.players.content;

import server.Config;
import server.Server;
import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerSave;
import server.util.Misc;

/**
 * Auction System
 * 
 * @author 3clipse
 */

public class Auction
{	
	public void startAuction(Client c, int itemId, int amount, int time, int startBid)
	{	
		Server.auctioneer = c.playerName;
		Server.currentBidder = Server.auctioneer;
		
		if (!c.getItems().playerHasItem(itemId))
		{
			c.sendMessage("[<col=16711680>SERVER<col=0>] You need to have the item in your inventory!");
			return;
		}
		
		if (time < 60)
		{
			c.sendMessage("[<col=16711680>SERVER<col=0>] Your auction's time must be greater than or equal to 60 seconds!");
			return;
		}
		
		if (time > 180)
		{
			c.sendMessage("[<col=16711680>SERVER<col=0>] Your auction's time must be less than or equal to 180 seconds!");
			return;
		}
		
		if (itemId == 4067)
		{
			c.sendMessage("[<col=16711680>SERVER<col=0>] You're not allowed to auction off in-game currency!");
			return;
		}
		
		Server.itemID = itemId;
		Server.itemAmount = amount;
		Server.activeAuction = true;
		Server.auctionTime = time;
		Server.startTime = System.currentTimeMillis();

		for (Player p : Server.playerHandler.players)
		{
			if (p != null)
			{
				Client c2 = (Client) p;
				c2.sendMessage("<col=16711680>" + c.playerName + " <col=0>has started an auction for (<col=16711680>" + amount + "<col=0>) <col=16711680>" + ItemAssistant.getItemName(itemId).toLowerCase() + (!ItemAssistant.getItemName(itemId).toLowerCase().endsWith("s") && amount > 1 ? "s<col=0>!" : "<col=0>!"));
				c2.sendMessage("If you wish to bid, type \"::bid (price)\" - Ex. \"::bid ammount 2b tickets\".");
				c2.sendMessage("This auction is starting at <col=16711680>" + Misc.insertCommas(Integer.toString(startBid)) + " 2B ticks <col=0>and is lasting <col=16711680>" + time + " <col=0>seconds!");
			
			}
		}
	}
	
	public void endAuction(String winningBidder, int endBid)
	{
		if (!Server.canAuctionBeCompleted)
		{
			for (Player p : Server.playerHandler.players)
			{
				if (p == null)
					continue;
				
				Client c = (Client) p;
				
				c.sendMessage("[<col=16711680>SERVER<col=0>] No one bid on <col=16711680>" + winningBidder + "'s <col=0>item, so the auction is over!");
			}

			Server.activeAuction = false;
			Server.canAuctionBeCompleted = false;
			return;
		}
		
		for (Player p : Server.playerHandler.players)
		{	
			if (p == null)
				continue;
			
			Client winner = (Client) p;
			
			if (winner.playerName == Server.auctioneer)
			{
				winner.getItems().deleteItem(Server.itemID, Server.itemAmount);
				winner.sendMessage("[<col=16711680>SERVER<col=0>] The item you auctioned has been given to <col=16711680>" + winningBidder + "<col=0>!");
				winner.getItems().addItem(4067, endBid);
				winner.sendMessage("[<col=16711680>SERVER<col=0>] You have been given <col=16711680>" + endBid + " <col=0>2B tickets!");
			}
			else if (winner.playerName == winningBidder)
			{
				winner.sendMessage("[<col=16711680>SERVER<col=0>] Congratulations, you have won the auction!");
				
				for (Player q : Server.playerHandler.players)
				{
					if (q == null)
						continue;
					
					Client c = (Client) q;
					
					c.sendMessage("[<col=16711680>SERVER<col=0>] <col=16711680>" + winner.playerName + " <col=0>has won the auction, congratulations!");
				}
				
				if ((ItemAssistant.getItemName(Server.itemID).toLowerCase().contains("noted") && winner.getItems().freeSlots() > 0) || winner.getItems().freeSlots() >= Server.itemAmount)
				{
					if (winner.getItems().playerHasItem(4067, endBid))
					{
						winner.getItems().deleteItem(4067, endBid);
						winner.sendMessage("[<col=16711680>SERVER<col=0>] Your final bid has been removed from your inventory!");
					}
					else
					{
						winner.sendMessage("[<col=16711680>SERVER<col=0>] Congratulations! You've successfully fucked up the auction.");
						Server.activeAuction = false;
						return;
					}
						
					winner.getItems().addItem(Server.itemID, Server.itemAmount);
					winner.sendMessage("[<col=16711680>SERVER<col=0>] Your item" + (Server.itemAmount > 1 ? "s" : "") + " " + (Server.itemAmount > 1 ? "have" : "has") + " been placed in your inventory!");
				}
				else
				{
					winner.getItems().createGroundItem(Server.itemID, winner.absX, winner.absY, Server.itemAmount);
					winner.sendMessage("[<col=16711680>SERVER<col=0>] You have no inventory space, your item" + (Server.itemAmount > 1 ? "s" : "") + " " + (Server.itemAmount > 1 ? "are" : "is") + "on the floor!");
				}
			}
			
			PlayerSave.saveGame(winner);
		}
		Server.activeAuction = false;
		Server.canAuctionBeCompleted = false;
	}
}