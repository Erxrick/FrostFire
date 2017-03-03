package games.indie.frostfire.multiplayer;

import java.net.InetAddress;

import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.user.Player;
import games.indie.frostfire.world.Direction;

public class PlayerMP extends Player {

    public InetAddress ipAddress;
    public int port;
    private long username;
    private GameClient socketClient;


    public long getUsername() {
		return username;
	}

	public PlayerMP(float x, float y, long username, InetAddress ipAddress, int port) {
  //      super(username);
        this.username = username;
        this.ipAddress = ipAddress;
        this.port = port;

        this.setLocation(x,y);
    }
	
//	public float getX() {
//		return location.getX();
//	}

	public void setClient(GameClient socketClient) {
        this.socketClient = socketClient;
	}
//	public float getY() {
//		return location.getY();
//	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public ActionType getAction() {
		return	this.currentAction.getType();
	}
	public Direction getDirection() {
		return this.direction;
	}
	
	
	
	
//	@Override
//	public void setLocation(Coord location) {
//		this.location = location;
//	//	Camera.setCenter(center());
//	}

}
