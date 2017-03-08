package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet07MPDeath extends Packet{

	private long username;
	
	public Packet07MPDeath(long username) {
		super(07);
		this.username = username;
	}
	
	 public Packet07MPDeath(byte[] data) {
	     super(07);
	      String[] dataArray = readData(data).split(",");
		  this.username = Long.parseLong(dataArray[0]);
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
	        return ("07" + this.username).getBytes();
	    }
	    public long getDeadMP() {
	    	return this.username;
	    }
}
