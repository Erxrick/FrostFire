package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet01Disconnect extends Packet {

    private long username;

    public Packet01Disconnect(byte[] data) {
        super(01);
        this.username = Long.parseLong(readData(data));
    }

    public Packet01Disconnect(long username) {
        super(01);
        this.username = username;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }

    public long getUsername() {
        return username;
    }

}