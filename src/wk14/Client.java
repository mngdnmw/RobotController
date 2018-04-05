package wk14;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class Client extends JFrame {
	private final JButton left;
	private final JButton right;
	private final JButton up;
	private final JButton down;
	private final JButton stop;
	private DataOutputStream dos;
	public static void main(String[] args) {

		System.out.println("Connecting to EV3...");
		Client client = new Client();
		client.setSize(500, 500);
		client.setVisible(true);

			
	}
	public Client() {
		try {
			Socket socket = new Socket("192.168.43.174", 5969);
//			Socket socket = new Socket("127.0.0.1", 19231);//for mocking
			DataInputStream dis = new DataInputStream(socket.getInputStream());
		    dos = new DataOutputStream(socket.getOutputStream());
		    
			//pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		setLayout(new BorderLayout());
 
 
 
		left = new JButton("LEFT");
		this.getContentPane().add(left, BorderLayout.WEST);
		left.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				leftRelease();
//				System.out.println("LEFT-RELEASE\n");pw.flush();
			}
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				leftPress();
//				System.out.println("LEFT-PRESS\n");pw.flush();
			}
 
 
		});
		right = new JButton("RIGHT");
		this.getContentPane().add(right, BorderLayout.EAST);
		right.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				rightRelease();
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				rightPress();
//				System.out.println("RIGHT-PRESS\n");pw.flush();
			}
 
 
		});
		up = new JButton("UP");
		this.getContentPane().add(up, BorderLayout.NORTH);
		up.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				upRelease();
//				System.out.println("UP-RELEASE\n");pw.flush();
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				upPress();
//				System.out.println("UP-PRESS\n");pw.flush();
			}
 
 
		});
		down = new JButton("DOWN");
		this.getContentPane().add(down, BorderLayout.SOUTH);
		down.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				downRelease();
//				System.out.println("DOWN-RELEASE\n");
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				downPress();
//				System.out.println("DOWN-PRESS\n");
			}
 
 
		});
		stop = new JButton("STOP\n");
		this.getContentPane().add(stop, BorderLayout.CENTER);
		stop.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mousePressed(MouseEvent e) {
				sendCommand("escape");
			}
		});
 
		
 
		pack();
	}
 

	private void sendCommand(String command) {
		try {

			dos.writeUTF(command);dos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void downPress() {
		sendCommand("back");
	}
 
	private void downRelease() {
		sendCommand("stop");
	}
 
	private void leftPress() {
		sendCommand("left");
	}
	
	private void leftRelease() {
		sendCommand("stop");
	}
	
	private void upPress() {
		sendCommand("forward");
	}
 	
	private void upRelease() {
		sendCommand("stop");
	}
 

	private void rightPress() {
		sendCommand("right");
	}

	private void rightRelease() {
		sendCommand("stop");
	}
 }
	



