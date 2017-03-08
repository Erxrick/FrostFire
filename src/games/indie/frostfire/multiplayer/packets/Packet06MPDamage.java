package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet06MPDamage extends Packet {
	private long username;
	private double Ehealth;
	
	public Packet06MPDamage(long username, double Ehealth) {
		super(06);
		this.username = username;
		this.Ehealth = Ehealth;
	}
	
	
	 public Packet06MPDamage(byte[] data) {
	     super(06);
	      String[] dataArray = readData(data).split(",");
		  this.username = Long.parseLong(dataArray[0]);
		  this.Ehealth = Double.parseDouble(dataArray[1]);
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
	        return ("06" + this.username + "," + this.Ehealth).getBytes();
	    }
	    public long getMPDamaged() {
	    	return this.username;
	    }
	    public double entityHealth() {
	    	return this.Ehealth;
	    }
}
