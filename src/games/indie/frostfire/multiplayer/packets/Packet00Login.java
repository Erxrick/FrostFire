package games.indie.frostfire.multiplayer.packets;



import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;

public class Packet00Login extends Packet {

    private long username;
    private float x, y;


    public Packet00Login(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.username = Long.parseLong(dataArray[0]);
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
    }

    public Packet00Login(long username, float x, float y) {
        super(00);
        this.username = username;
        this.x = x;
        this.y = y;
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
        return ("00" + this.username + "," + getX() + "," + getY()).getBytes();
    }

    public long getUsername() {
        return username;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}