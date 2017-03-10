package games.indie.frostfire.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import games.indie.frostfire.multiplayer.packets.Packet00Login;

public class PacketParsing {

	@Test
	public void packetCreation() {
		Packet00Login loginPacket = new Packet00Login(123456l, 0f, 0f);
		
		byte[] expected = loginPacket.getData();
		
		
		byte[] actual = ("00" + 123456l + "," + 0f  + "," + 0f).getBytes();
		
		assertArrayEquals(expected, actual);
	}

	
	@Test
	public void packetBreakage() {
		Packet00Login loginPacket = new Packet00Login(Long.MAX_VALUE, 0f, 0f);
		
		byte[] expected = loginPacket.getData();
		
		
		byte[] actual = ("00" + Long.MAX_VALUE + "," + 0f  + "," + 0f).getBytes();
		
		assertArrayEquals(expected, actual);
	}
}
