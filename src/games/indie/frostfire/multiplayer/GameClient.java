package games.indie.frostfire.multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import games.indie.frostfire.multiplayer.packets.Packet;
import games.indie.frostfire.multiplayer.packets.Packet.PacketTypes;
import games.indie.frostfire.multiplayer.packets.Packet00Login;
import games.indie.frostfire.multiplayer.packets.Packet01Disconnect;
import games.indie.frostfire.multiplayer.packets.Packet02Move;
import games.indie.frostfire.states.Gameplay;



public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private Gameplay game;
    

    public GameClient(Gameplay game, String ipAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
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
        System.out.println(message);
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        switch (type) {
        default:
        case INVALID:
            break;
        case LOGIN:
            Packet00Login packet = new Packet00Login(data);
            handleLogin(packet, address, port);
            break;
        case DISCONNECT:
        	Packet01Disconnect packet1 = new Packet01Disconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "+ (packet1).getUsername() + " has left the world...");
           
  //           game.world.removePlayerMP((packet1).getUsername());
            break;
        case MOVE:
        	Packet02Move packet2 = new Packet02Move(data);
            handleMove(packet2);
        }
    }

    public void sendData(byte[] data) {
    
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    private void handleLogin(Packet00Login packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
                + " has joined the game...");
      PlayerMP player = new PlayerMP(packet.getX(), packet.getY(), packet.getUsername(), address, port);
//      packet.getX(), packet.getY(), 
      if(!(packet.getUsername() == (game.getPlayer().getUsername()))) {
      	game.world.place(player);
      }
      System.out.println("Handled Login");
    }

    private void handleMove(Packet02Move packet) {
        this.game.world.movePlayer(packet.getUsername(), packet.getX(), packet.getY(), packet.getAction(), packet.getMovingDir());
    }
}
