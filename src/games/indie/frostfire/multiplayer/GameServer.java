package games.indie.frostfire.multiplayer;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import games.indie.frostfire.multiplayer.packets.Packet;
import games.indie.frostfire.multiplayer.packets.Packet.PacketTypes;
import games.indie.frostfire.multiplayer.packets.Packet00Login;
import games.indie.frostfire.multiplayer.packets.Packet01Disconnect;
import games.indie.frostfire.multiplayer.packets.Packet02Move;
import games.indie.frostfire.states.Gameplay;


public class GameServer extends Thread {

    private DatagramSocket socket;
    private Gameplay game;
    private ArrayList<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

    public GameServer(Gameplay game) {
        this.game = game;
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
            break;
        case LOGIN:
          Packet00Login  packet1 = new Packet00Login(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] " + (packet1).getUsername() + " has connected...");
           PlayerMP player = new PlayerMP(10, 10, (packet1).getUsername(), address, port);
            this.addConnection(player, packet1);
            break;
        case DISCONNECT:
           Packet01Disconnect  packet2 = new Packet01Disconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] " + (packet2).getUsername() + " has left...");
            this.removeConnection(packet2);
            break;
        case MOVE:
          Packet02Move  packet3 = new Packet02Move(data);
            this.handleMove((packet3));
        }
    }

    public void addConnection(PlayerMP player, Packet00Login packet) {
        boolean alreadyConnected = false;
        for (PlayerMP p : this.connectedPlayers) {
            if ( player != null && player.getUsername() == p.getUsername()) {
                if (p.ipAddress == null) {
                    p.ipAddress = player.ipAddress;
                }
                if (p.port == -1) {
                    p.port = player.port;
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), p.ipAddress, p.port);
                System.out.println(player.getUsername());
                // relay to the new player that the currently connect player
                // exists
                packet = new Packet00Login(p.getUsername(), p.getX(), p.getY());
                sendData(packet.getData(), player.ipAddress, player.port);
            }
        }
        if (!alreadyConnected) {
        	this.connectedPlayers.add(player);
        }
    }

    public void removeConnection(Packet01Disconnect packet) {
        this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
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
            
            packet.writeData(this);
        }
    }

}