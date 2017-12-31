package server.model.players;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public enum PlayerRights {
	
	PLAYER(0),
	MODERATOR(1),
	ADMINISTRATOR(2),
	OWNER(3),
	DEVELOPER(4),
	;
	
	
	PlayerRights(int playerRights) {
		this.playerRights = playerRights;
	}
	
	int playerRights;
	
	private static final ImmutableSet<PlayerRights> STAFF = Sets.immutableEnumSet(MODERATOR, ADMINISTRATOR, OWNER, DEVELOPER);
	
	public boolean isStaff() {
		return STAFF.contains(this);
	}
}
