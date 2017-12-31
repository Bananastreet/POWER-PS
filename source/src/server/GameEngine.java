package server;

import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;
import server.model.players.content.TriviaBot;
import server.world.PublicEvent;

public class GameEngine extends Server {
	public GameEngine() {

	}

	public void run() {
		try {
			if (sleepTime > 0)
				Thread.sleep(sleepTime);
			PublicEvent.process();
			engineTimer.reset();
			itemHandler.process();
			playerHandler.process();
			CycleEventHandler.getSingleton().process();
			npcHandler.process();
			shopHandler.process();
			objectManager.process();
			TriviaBot.process();
			Server.pestControl.gameProcess();
			cycleTime = engineTimer.elapsed();
			if (cycleTime < 575)
				sleepTime = cycleRate - cycleTime;
			else
				sleepTime = 0;
			totalCycleTime += cycleTime;
			cycles++;

			if (activeAuction) {
				timePassed = (System.currentTimeMillis() - startTime) / 1000;

				if (timePassed != lastTime) {
					lastTime = timePassed;
					long timeLeft = auctionTime - timePassed;

					if (timeLeft <= 0)
						getAuction().endAuction(currentBidder, currentBid);
					else {
						if (timeLeft % 30 == 0 || (timeLeft < 30 && timeLeft % 15 == 0)
								|| (timeLeft < 11 && timeLeft % 5 == 0) || timeLeft == 4 || timeLeft == 3
								|| timeLeft == 2 || timeLeft == 1) {
							for (Player p : PlayerHandler.getPlayers()) {
								if (p != null) {
									Client c2 = (Client) p;
									c2.sendMessage("There's only <col=16711680>" + timeLeft + " <col=0>more second" + (timeLeft > 1 ? "s" : "") + " left to bid! \"::bid (price)\"");
								}
							}
						}
					}
				} else {
					Thread.sleep(500);
				}
			}

			debug();
			if (Config.SERVER_DEBUG)
				secundes++;
			if (secundes == 120) {
				minutes++;
				secundes = 0;
			}
			if (minutes == 60) {
				hours++;
				minutes = 0;
			}
			if (hours == 24) {
				days++;
				hours = 0;
			}
			if (hours == 2 && minutes == 0 && secundes == 20) {
				PlayerHandler.updateSeconds = 60;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			for (Player p : PlayerHandler.players) {
				if (p == null)
					continue;
				PlayerSave.saveGame((Client) p);
				System.out.println("[Saved game]" + p.playerName + ".");
			}
		}
	}

}
