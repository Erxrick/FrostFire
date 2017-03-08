package games.indie.frostfire.multiplayer.packets;

import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet04Damage extends Packet{

	private int ID;
	private double Ehealth;
	
	public Packet04Damage(int ID, double Ehealth) {
		super(04);
		this.ID = ID;
		this.Ehealth = Ehealth;
	}
	
	
	 public Packet04Damage(byte[] data) {
	     super(04);
	      String[] dataArray = readData(data).split(",");
		  this.ID = Integer.parseInt(dataArray[0]);
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
	        return ("04" + this.ID + "," + this.Ehealth).getBytes();
	    }
	    public int getEntityDamaged() {
	    	return this.ID;
	    }
	    public double entityHealth() {
	    	return this.Ehealth;
	    }
	    
}
