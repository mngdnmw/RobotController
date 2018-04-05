package wk14;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;

public class Server {

	private static EV3 brick = (EV3) BrickFinder.getDefault();
	public static RegulatedMotor left = new EV3LargeRegulatedMotor(brick.getPort(("B")));
	public static RegulatedMotor right = new EV3LargeRegulatedMotor(brick.getPort(("D")));

	public static void main(String[] args)
    {
	try
	{
	    ServerSocket ss = new ServerSocket(5969);
	    
//	    LCD.clear();
//	    LCD.drawString("Waiting for client connection...", 0, 0);
	    System.out.println("Waiting for client connection...");
	    Socket s = ss.accept();
	    
//	    LCD.clear();
//	    LCD.drawString("Client connected", 0, 0);
	    System.out.println("Client connected");
	    DataInputStream dis = new DataInputStream(s.getInputStream());
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    
	    boolean done = false;
	    while (! done)
	    {
		String message = dis.readUTF();
		LCD.clear();
		LCD.drawString("Client says: " + message, 0, 2);
		System.out.println("Client says: " + message);
		
		if (message.equalsIgnoreCase("quit"))
		{
		    done = true;
		}
		
		
		switch (message) {
		case ("forward"):
			// handle up
			right.forward();
			left.forward();
			break;
		case ("back"):
			// handle down
			right.backward();
			left.backward();
			break;
		case ("left"):
			// handle left
			right.forward();
			break;
		case ("right"):
			// handle right
			left.forward();
			break;
		case("stop"):
			if(left.isMoving()) {
				left.stop();
			}
			if(right.isMoving()) {
				right.stop();
			}
			break;
		case ("escape"):
			// handle exit
			System.exit(0);
			break;
		}
		
		dos.writeUTF(message.toUpperCase());
		dos.flush();
		
	
////	    LCD.clear();
////	    LCD.drawString("EV3 terminating", 0, 1);
//	    System.out.println("EV3 terminating");
	    }
	    } 
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    
    }
}
