package server.util;

import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;

public class ShutDownHook extends Thread {

	@Override
	public void run() {
		System.out.println("Shutdown thread run.");
		for (int j = 0; j < PlayerHandler.getPlayers().length; j++) {
			if (PlayerHandler.getPlayers()[j] != null) {
				Client c = (Client) PlayerHandler.getPlayers()[j];
				PlayerSave.saveGame(c);
			}
		}
		System.out.println("Shutting down...");
	}

}