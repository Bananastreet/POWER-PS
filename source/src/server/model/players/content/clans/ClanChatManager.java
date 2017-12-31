package server.model.players.content.clans;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;

import server.model.players.Player;

/**
 * Borrowed his clan system, but changing it to make it work for me.
 * 
 * @author Professor Oak.
 *
 */
public class ClanChatManager {

	private static final String DIRECTORY = "./Data/clans/";

	private static ClanChat[] clans = new ClanChat[3000];

	public static ClanChat[] getClans() {
		return clans;
	}

	public static ClanChat getClanChat(int index) {
		return clans[index];
	}

	public static ClanChat getClanChatChannel(Player player) {
		for (ClanChat clan : clans) {
			if (clan == null || clan.getOwnerName() == null) {
				continue;
			}
			if (clan.getOwnerName().equals(player.getPlayerName())) {
				return clan;
			}
		}
		return null;
	}

	public static void init() {
		try {
			for (File file : (new File(DIRECTORY)).listFiles()) {
				if (!file.exists())
					continue;
				DataInputStream input = new DataInputStream(new FileInputStream(file));
				String name = input.readUTF();
				String owner = input.readUTF();
				int index = input.readShort();
				ClanChat clan = new ClanChat(owner, name, index);
				clan.setRankRequirements(ClanChat.RANK_REQUIRED_TO_ENTER, ClanChatRank.forId(input.read()));
				clan.setRankRequirements(ClanChat.RANK_REQUIRED_TO_KICK, ClanChatRank.forId(input.read()));
				clan.setRankRequirements(ClanChat.RANK_REQUIRED_TO_TALK, ClanChatRank.forId(input.read()));
				int totalRanks = input.readShort();
				for (int i = 0; i < totalRanks; i++) {
					clan.getRankedNames().put(input.readUTF(), ClanChatRank.forId(input.read()));
				}
				int totalBans = input.readShort();
				for (int i = 0; i < totalBans; i++) {
					clan.addBannedName(input.readUTF());
				}
				clans[index] = clan;
				input.close();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void writeFile(ClanChat clan) {
		try {
			File file = new File(DIRECTORY + clan.getName());
			if (file.exists())
				file.createNewFile();
			DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
			output.writeUTF(clan.getName());
			output.writeUTF(clan.getOwnerName());
			output.writeShort(clan.getIndex());
			output.write(clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_ENTER] != null
					? clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_ENTER].ordinal()
					: -1);
			output.write(clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_KICK] != null
					? clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_KICK].ordinal()
					: -1);
			output.write(clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_TALK] != null
					? clan.getRankRequirement()[ClanChat.RANK_REQUIRED_TO_TALK].ordinal()
					: -1);
			output.writeShort(clan.getRankedNames().size());
			for (Entry<String, ClanChatRank> iterator : clan.getRankedNames().entrySet()) {
				String name = iterator.getKey();
				int rank = iterator.getValue().ordinal();
				output.writeUTF(name);
				output.write(rank);
			}
			output.writeShort(clan.getBannedNames().size());
			for (BannedMember ban : clan.getBannedNames()) {
				output.writeUTF(ban.getName());
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		for (ClanChat clan : clans) {
			if (clan != null) {
				writeFile(clan);
			}
		}
	}

}
