package server.model.players;

import server.Config;
import server.Server;
import server.task.Task;

/**
 * Handles the teleporting using magic spells.
 * @author Bananastreet
 *
 */
public class TeleportHandler {
	
	public static boolean canTeleport(Client c) {
		if (c.inTrade) {
			c.sendMessage("Please finish the trade first.");
			return false;
		}
		if (c.selectStarter == true) {
			c.sendMessage(" You can't teleport while choosing starter!");
			return false;
		}
		if (c.inBarbDef == true) {
			c.sendMessage("Teleporting will make you loose points! Type ::endgame instead!");
			return false;
		}
		if (c.selectStarterr == true) {
			c.sendMessage(" You can't teleport while choosing starter!");
			return false;
		}
		if (c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return false;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return false;
		}
		if (c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return false;
		}
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level " + Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return false;
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking fool!");
			return false;
		}
		if (c.inGWD()) {
			c.getPA().ResetGWKC();
		}
		if (c.InDung()) {
			c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
			return false;
		}
		if (c.inPits || c.viewingOrb || c.getPA().inPitsWait()) {
			c.sendMessage("You can't teleport in here!");
			return false;
		}
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return false;
		}
		return true;
	}
	
	public static void cancelActions(Client c) {
		c.resetWalkingQueue();
		c.stopMovement();
		c.getPA().removeAllWindows();
	}
	
	public static void teleport(final Client c, final int x, final int y, final int z, final int spellBookId) {
		if (!canTeleport(c)) {
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(1, true) {
			int tick = 0;
			@Override
			protected void execute() {
				switch (tick) {
				case 0:
					cancelActions(c);
					switch (spellBookId) {
					case 0:
						c.startAnimation(8939);
						c.gfx0(1576);
						break;
					case 1:
						c.startAnimation(9599);
						c.gfx0(1681);
						break;
					case 2:
						break;
					}
					break;
					
				case 3:
					c.getPA().movePlayer(x, y, z);
					break;
					
				case 4:
					switch (spellBookId) {
					case 0:
						c.startAnimation(8941);
						break;
					case 1:
						break;
					case 2:
						break;
					}
					break;
					
				case 5:
					stop();
					break;
					
			}
				tick++;
			}
			@Override
			public void stop() {
				setEventRunning(false);
			}
		});
	}
}
