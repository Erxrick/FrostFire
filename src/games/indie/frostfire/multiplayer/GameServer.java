package games.indie.frostfire.multiplayer;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.multiplayer.packets.Packet;
import games.indie.frostfire.multiplayer.packets.Packet.PacketTypes;
import games.indie.frostfire.multiplayer.packets.Packet00Login;
import games.indie.frostfire.multiplayer.packets.Packet01Disconnect;
import games.indie.frostfire.multiplayer.packets.Packet02Move;
import games.indie.frostfire.multiplayer.packets.Packet03Seed;
import games.indie.frostfire.multiplayer.packets.Packet04Damage;
import games.indie.frostfire.multiplayer.packets.Packet05Death;
import games.indie.frostfire.multiplayer.packets.Packet06MPDamage;
import games.indie.frostfire.multiplayer.packets.Packet07MPDeath;
import games.indie.frostfire.states.Gameplay;
import games.indie.frostfire.world.ProceduralGeneration;
import games.indie.frostfire.world.World;


public class GameServer extends Thread {

    private DatagramSocket socket;
    private ArrayList<PlayerMP> connectedPlayers;
    private ArrayList<PlayerMP> deadpeople;
    private int seed;
    private World world;
    private Packet03Seed seedpacket;

    public GameServer(String seed) {
       
        this.seed = ProceduralGeneration.seedValidation(seed);   	
    	seedpacket = new Packet03Seed(this.seed);
        world = new World(this.seed);
        connectedPlayers = new ArrayList<PlayerMP>();
        deadpeople = new ArrayList<>();
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }
    
    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
   //     Packet packet = null;
        switch (type) {
        default:
        case INVALID:
        	System.out.println("You done goofed");
            break;
        case LOGIN:
        	Packet00Login  packet1 = new Packet00Login(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] " + (packet1).getUsername() + " has connected...");
            PlayerMP player = new PlayerMP(10, 10, (packet1).getUsername(), address, port);
            sendData(seedpacket.getData(), address, port);
            this.addConnection(player, packet1);
            break;
        case DISCONNECT:
           Packet07MPDeath  packet2 = new Packet07MPDeath(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet2.getDeadMP() + " has left...");
            this.killEntity(packet2);
            break;
        case MOVE:
        	Packet02Move  packet3 = new Packet02Move(data);
            this.handleMove((packet3));
            break;
        case DAMAGE:
        	Packet04Damage dmgpacket = new Packet04Damage(data);
        	dmgEntity(dmgpacket);
        	break;
        case DEATH:
        	Packet05Death deathpacket = new Packet05Death(data);
        	killEntity(deathpacket);
        	break;
        case DMGMP:
        	Packet06MPDamage mpdamage = new Packet06MPDamage(data);
        	dmgEntity(mpdamage);
        	break;
        case DTHMP:
        	Packet07MPDeath mpdeath = new Packet07MPDeath(data);
        	killEntity(mpdeath);
        	break;
        }
    }

    private void killEntity(Packet05Death deathpacket) {
    	  for (Entity entity : world.getEntities()) {
    		  if(entity.getID() == deathpacket.getDeadEntity()) {
  				world.remove(entity);
  				sendDataToAllClients(deathpacket.getData());
  			}
    	  }
	}
    private void killEntity(Packet07MPDeath deathpacket) {
  	  for (Entity entity : world.getEntities()) {
  		  System.out.println("part 1");
  		  if(entity instanceof PlayerMP) {
  			  PlayerMP player = (PlayerMP) entity;
	  		  if(player.getUsername() == deathpacket.getDeadMP()) {
					world.removePlayerMP(deathpacket.getDeadMP());
					System.out.println("Success");
					removeConnection(new Packet01Disconnect(player.getUsername()));
		//			sendDataToAllClients(deathpacket.getData());
					deadpeople.add(player);
				}
  		  }
  	  	}
	}

	private void dmgEntity(Packet04Damage dmgpacket) {
		for (Entity entity : world.getEntities()) {
			if(entity.getID() == dmgpacket.getEntityDamaged()) {
				entity.setHealth(dmgpacket.entityHealth());
				if(entity.getHealth() < 0) {
					killEntity(new Packet05Death(entity.getID()));
				} else {
				sendDataToAllClients(dmgpacket.getData());
				}
			}
		}
	}		
		private void dmgEntity(Packet06MPDamage dmgpacket) {
			for (Entity entity : world.getEntities()) {
		  		  if(entity instanceof PlayerMP) {
		  			  PlayerMP player = (PlayerMP) entity;
			  		  if(player.getUsername() == dmgpacket.getMPDamaged()) {
			  			  entity.setHealth(dmgpacket.entityHealth());
			  			if(player.getHealth() < 0) {
							killEntity(new Packet07MPDeath(player.getUsername()));
						} else {
						sendDataToAllClients(dmgpacket.getData());
						}
			  		  }
			}
		}		
	}

	public void addConnection(PlayerMP player, Packet00Login packet) {
       if(deadpeople.contains(player)) {
       } else {
		boolean alreadyConnected = false;
        if(connectedPlayers.contains(player)) {
        	alreadyConnected = true;
        } else {
         	this.connectedPlayers.add(player);        
         	
         	//migrate this to the client because fuckall
	        for (PlayerMP p : this.connectedPlayers) {	
	            if ( player != null && player.getUsername() == p.getUsername()) {
	                if (p.ipAddress == null) {
	                    p.ipAddress = player.ipAddress;
	                }
	                if (p.port == 0) {
	                    p.port = player.port;
	                }
	            } else if (player.getUsername() != p.getUsername()) {
	                // relay to the current connected player that there is a new player
//	                sendData(packet.getData(), p.ipAddress, p.port);
	                // relay to the new player that the currently connect player exists
	                
	            	sendDataToAllClients(packet.getData());
	                //this one works just fine
	                packet = new Packet00Login(p.getUsername(), p.getX(), p.getY());
	                sendData(packet.getData(), player.ipAddress, player.port);
	            }
        	}
        }
        world.place(player, player.getX(), player.getY());
       }
    }

    public void removeConnection(Packet01Disconnect packet) {
    //    this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
        packet.writeData(this);
    }

    public PlayerMP getPlayerMP(long username) {
        for (PlayerMP player : this.connectedPlayers) {
            if (player.getUsername() == username) {
                return player;
            }
        }
        return null;
    }

    public int getPlayerMPIndex(long username) {
        int index = 0;
        for (PlayerMP player : this.connectedPlayers) {
            if (player.getUsername() == username) {
                break;
            }
            index++;
        }
        return index;
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {

        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToAllClients(byte[] data) {
        for (PlayerMP p : connectedPlayers) {
            sendData(data, p.ipAddress, p.port);
        }
    }

    private void handleMove(Packet02Move packet) {
        if (getPlayerMP(packet.getUsername()) != null) {
            int index = getPlayerMPIndex(packet.getUsername());
            PlayerMP player = this.connectedPlayers.get(index);    
            if(!deadpeople.contains(player)) {
            	packet.writeData(this);
            }
        }
    }

}