package server.model.players;

public interface PacketType {
	void processPacket(Client c, int packetType, int packetSize);
}
