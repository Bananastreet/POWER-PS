package server.model.players;

import server.CycleEvent;
import server.CycleEventContainer;
import server.CycleEventHandler;
import server.model.players.Client;
import server.model.players.PlayerSave;

public class AchievementManager {

public static final int MAX_ACHIEVEMENTS = 100;

private static final int[] REQUIRED_AMOUNT = { 100, 1, 1, 1, 10, 1, 500, 10, 100, 1, 50, 1, 1, 1, 1, 1, 300, 1, 1, 1, 1, 100, 100, 200, 30, 50, 1, 1, 10, 1000, 1, 50, 15, 200, 150, 500, 1, 250, 1, 1, 100, 90, 80, 70, 60, 50, 1}; //40

private static final int[] ACHIEVEMENT_POINTS = { 10, 25, 5, 5, 10, 20, 100, 50, 50, 5, 50, 5, 5, 5, 5, 5, 30, 5, 15, 65, 10, 30, 30, 70, 200, 30, 25, 10, 10, 150, 50, 30, 70, 70, 70, 70, 70, 65, 20, 20, 50, 60, 70, 80, 90, 100, 200}; //40

private static final String[] ACHIEVEMENT_NAME = { "Hungry?", "PokeMaster", "Not Afraid", "Banking", "Catch Em All", "Treasure", "Mad Treasure", "Dedication", "Veteran", "Prayer", "PrayerMaster", "Cursed", "Purity", "Modern", "Ancient", "Lunar", "Priest", "Help Me", "Guitar", "Donor Tab", "Home Tele", "Tele Home", "Yak Hunter", "Rockcrab Hunter", "Vesbeast Slayer", "Patience", "Summon Me", "Splashing Out", "Pika", "Pika Pika", "Venturing Out", "Tormentor", "Pokemon Trainer", "Pokemon Killer", "Woodcutter", "Fire Starter", "Nex's Boss", "Fishy Feelin'", "Kill Wrecker", "Ah, That Arena", "Blue torva", "Toxic torva", "Wolverine Torva", "Zombie Torva", "Strip Torva", "Ice Torva", "Chaotic whip"}; //40

private static final String[] ACHIEVEMENT = { "Eat any food 100 times", "Kill Zapdos", "Tele to PNBeast", "Visit The Bank", "Tele to Pokemini x10", "Open The C Chest", "Open The C Chest x 500", "Play 10 Hours", "Play Enough to Be A Vet", "Recharge Your Prayer", "Recharge Your Prayer x50", "Switch to curses", "Switch to normal prayer", "Switch to modern magic", "Switch to ancient magic", "Switch to Lunar", "Use 300 Bones on altar", "Call for help", "Perform the Air Guitar Emote", "Open The Donor Tab", "Teleport Home", "Teleport Home x 100", "Kill 100 Yaks", "Kill 200 Rockcrabs", "Slay PNBeast x 30", "Finish Gnome Course x 50", "Create A Pouch", "Open the Customs shop", "Kill Pikachu x 10", "Kill Pikachus x1000", "Uknown? Leggo.", "Tormented Demons x 50", "Finish pokemon mini x15", "Kill 200 Pokemon", "Cut 150 Trees", "Make 500 Fires", "Kill Nex", "Catch 200 Fish", "Kill Wrecker", "Duel someone", "Kill blue torva x100", "Kill toxic torva x90", "Kill wolwerine torva x80", "Kill zombie torva x70", "Kill strip torva x60", "Kill ice torvas x50", "Get a chaotic whip"};//40
			
			
	public static void increase(final Client c, int achievement) {
		c.achievement[achievement]++;
		
		if (c.achievement[achievement] == REQUIRED_AMOUNT[achievement]) {
			c.achieved[achievement] = true;
			c.sendMessage("<col=176>Congratulations! You've completed the achievement <col=129>" + ACHIEVEMENT_NAME[achievement] + "!");
			c.achievementPoints += ACHIEVEMENT_POINTS[achievement];
			c.sendMessage("<col=176>You recieve " + ACHIEVEMENT_POINTS[achievement]	+ " points! <col=146>You now have the total of " + c.achievementPoints + " achievement points.");
			c.getPA().sendFrame126("You completed the achievement\\n" + ACHIEVEMENT_NAME[achievement] + "!", 25136);
			c.hasAchieved = true;
			//c.getPA().showInterface(25133);
			
			AchievementExtra.addExtra(c, achievement);
			PlayerSave.saveGame(c);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					c.hasAchieved = false;
					container.stop();
				}
				@Override
				public void stop() {

				}
			}, 5);
		}
	}

	public static void writeInterface(Client c) {
		for (int i = 0; i < ACHIEVEMENT.length; i++) {
			c.getPA().sendFrame126("" + (c.achieved[i] ? "@gre@" : "@red@") + "" + ACHIEVEMENT[i] + "", 39295 + i);
		}
		c.setSidebarInterface(1, 39265);
	}
}