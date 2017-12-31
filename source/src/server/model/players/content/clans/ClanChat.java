package server.model.players.content.clans;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Stopwatch;

public class ClanChat {

	public ClanChat(Player owner, String name, int index) {
		this.owner = owner;
		this.name = name;
		this.index = index;
		this.ownerName = owner.getPlayerName();
	}

	public ClanChat(String ownerName, String name, int index) {
		Player p = PlayerHandler.getPlayerByName(ownerName);
		if (p == null) {
			return;
		}
		this.owner = p;
		this.ownerName = ownerName;
		this.name = name;
		this.index = index;
	}

	Player owner;
	String ownerName, name;
	private boolean lootShare;
	int index;
	private Stopwatch lastAction = new Stopwatch();
	
	private ClanChatRank[] rankRequirement = new ClanChatRank[3];
	private CopyOnWriteArrayList<Player> members = new CopyOnWriteArrayList<Player>();
	private CopyOnWriteArrayList<BannedMember> bannedMembers = new CopyOnWriteArrayList<BannedMember>();
	private Map<String, ClanChatRank> rankedNames = new HashMap<String, ClanChatRank>();

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLootShare() {
		return lootShare;
	}

	public void setLootShare(boolean lootShare) {
		this.lootShare = lootShare;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Stopwatch getLastAction() {
		return lastAction;
	}

	public void setLastAction(Stopwatch lastAction) {
		this.lastAction = lastAction;
	}

	public ClanChat addMember(Player member) {
		members.add(member);
		return this;
	}

	public ClanChat removeMember(String name) {
		for(int i = 0; i < members.size(); i++) {
			Player member = members.get(i);
			if(member == null)
				continue;
			if(member.getPlayerName().equals(name)) {
				members.remove(i);
				break;
			}
		}
		return this;
	}

	public ClanChatRank getRank(Player player) {
		return rankedNames.get(player.getPlayerName());
	}

	public ClanChat giveRank(Player player, ClanChatRank rank) {
		rankedNames.put(player.getPlayerName(), rank);
		return this;
	}

	public CopyOnWriteArrayList<Player> getMembers() {
		return members;
	}

	public Map<String, ClanChatRank> getRankedNames() {
		return rankedNames;
	}

	public CopyOnWriteArrayList<BannedMember> getBannedNames() {
		return bannedMembers;
	}

	public void addBannedName(String name) {
		bannedMembers.add(new BannedMember(name, 1800)); //30 mins
	}

	public boolean isBanned(String name) {
		for(BannedMember b : bannedMembers) {
			if(b == null) {
				continue;
			}
			if(b.getName().equals(name) && 
					!b.getTimer().finished()) {
				return true;
			}
		}
		return false;
	}

	public ClanChatRank[] getRankRequirement() {
		return rankRequirement;
	}

	public ClanChat setRankRequirements(int index, ClanChatRank rankRequirement) {
		this.rankRequirement[index] = rankRequirement;
		return this;
	}

	public static final int RANK_REQUIRED_TO_ENTER = 0, RANK_REQUIRED_TO_KICK = 1, RANK_REQUIRED_TO_TALK = 2;
	
}
