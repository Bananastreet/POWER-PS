package server.model.players;

public class PlayerConstants {
	
	public static boolean isPlayer(Player player) {
		if (player.getPlayerRights() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isStaff(Player player) {
		if (player.getPlayerRights() == 1 || player.getPlayerRights() == 2 || player.getPlayerRights() == 3 || player.getPlayerRights() == 4) {
			return true;
		}
		return false;
	}
	
	public static boolean isModerator(Player player) {
		if (player.getPlayerRights() == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean isAdministrator(Player player) {
		if (player.getPlayerRights() == 2) {
			return true;
		}
		return false;
	}
	
}
