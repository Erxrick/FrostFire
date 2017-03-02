package games.indie.frostfire.multiplayer;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.multiplayer.packets.Packet00Login;


public class Multiplayer {

	private GameServer server;
	private GameClient client;
	
	
	//this is for initializing the server
	public Multiplayer(GameServer server, GameClient client) {
		this.server = server;
		this.client = client;
		server.start();
		client.start();
	   Packet00Login loginPacket = new Packet00Login(FrostFire.gameplay.getPlayer().getUsername(), FrostFire.gameplay.getPlayer().getX(), FrostFire.gameplay.getPlayer().getY());
       server.addConnection(FrostFire.gameplay.getPlayer(), loginPacket);
       loginPacket.writeData(client);
	}
	
	
	
	//this is for initializing a client
	public Multiplayer(GameClient client) {
		this.server = null;
		this.client = client;
		client.start();
		   Packet00Login loginPacket = new Packet00Login(FrostFire.gameplay.getPlayer().getUsername(), FrostFire.gameplay.getPlayer().getX(), FrostFire.gameplay.getPlayer().getY());
	       loginPacket.writeData(client);
		
	}
	
}
