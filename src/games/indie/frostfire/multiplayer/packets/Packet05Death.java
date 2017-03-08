package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet05Death extends Packet{

	private int ID;
	
	public Packet05Death(int ID) {
		super(05);
		this.ID = ID;
	}
	
	 public Packet05Death(byte[] data) {
	     super(05);
	      String[] dataArray = readData(data).split(",");
		  this.ID = Integer.parseInt(dataArray[0]);
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
	        return ("05" + this.ID).getBytes();
	    }
	    public int getDeadEntity() {
	    	return this.ID;
	    }
}
