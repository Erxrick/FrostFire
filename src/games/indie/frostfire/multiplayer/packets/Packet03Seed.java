package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet03Seed extends Packet{

	private int seed;
	
    public Packet03Seed(byte[] data) {
        super(03);
        String[] dataArray = readData(data).split(",");
        this.seed = Integer.parseInt(dataArray[0]);

    }

    public Packet03Seed(int seed) {
        super(03);
        this.seed = seed;
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
        return ("03" + this.seed).getBytes();
    }
    public int getSeed() {
    	return this.seed;
    }
}
