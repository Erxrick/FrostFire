package games.indie.frostfire.multiplayer.packets;


import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;
import games.indie.frostfire.world.Direction;

public class Packet02Move extends Packet {

    private long username;
    private float x, y;

    private ActionType action;
    private Direction movingDir;

    public Packet02Move(byte[] data) {
        super(02);
        String[] dataArray = readData(data).split(",");
        this.username = Long.parseLong(dataArray[0]);
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
        this.action = getActionType(Integer.parseInt(dataArray[3]));
        this.movingDir = getDirectionType(Double.parseDouble(dataArray[4]));
 
    }

    private Direction getDirectionType(double parseDouble) {
    	for (Direction dir : Direction.values()) {
			if(dir.getAngle() == parseDouble) {
				return dir;
			}
		}
		return null;
	}

	private ActionType getActionType(int parseInt) {
		for (ActionType action : ActionType.values()) {
			if(action.getNum() == parseInt) {
				return action;
			}
		}
		return null;
	}

	public Packet02Move(long username, float x, float y, ActionType action, Direction movingDir) {
        super(02);
        this.username = username;
        this.x = x;
        this.y = y;
        this.action = action;
        this.movingDir = movingDir;
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
        return ("02" + this.username + "," + this.x + "," + this.y + "," + this.action.getNum() + "," + this.movingDir.getAngle()).getBytes();

    }

    public long getUsername() {
        return username;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }


    public ActionType getAction() {
        return action;
    }

    public Direction getMovingDir() {
        return movingDir;
    }
}