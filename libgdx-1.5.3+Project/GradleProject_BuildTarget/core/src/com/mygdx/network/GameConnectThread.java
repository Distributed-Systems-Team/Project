package com.mygdx.network;


import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.BoxGame;
import com.mygdx.game.ConnectionJoinScreen;
import com.mygdx.game.ConnectionWaitScreen;
import com.mygdx.game.GameScreen;
import com.mygdx.game.NetworkProtocols;

public class GameConnectThread implements Runnable
{

	Vector2 toSend;
	Vector2 lastRecieved;
	Vector2 recieved;
	
	
	private boolean isServer;

	private int port_number;
	private ServerSocket server;
	private Socket client;
	private InetAddress host;
	
	private DataInputStream is;
	private DataOutputStream os;
	
	
	public GameConnectThread( boolean threadIsServer )
	{
		port_number = NetworkProtocols.portNum;
		isServer = threadIsServer;
	}
	
	public void run()
	{
		System.out.println("SERVER thread start");
		if( isServer )
		{
			try
			{
				port_number = NetworkProtocols.portNum;
				server = new ServerSocket(port_number);
				server.setSoTimeout(10000);
				
				client = server.accept();
				
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				
				ConnectionWaitScreen.gameConnected = true;
				BoxGame.gameON = true;
				
				while( BoxGame.gameON  )
				{
					System.out.println("IM SERVER");
					
					send();
					receive();
				}
				
				boolean isCallbackReceived = false;
				int callback;
				while( !isCallbackReceived )
				{
					os.writeInt( NetworkProtocols.gameEnded);
					
					callback = is.readInt();
					
					if( callback == NetworkProtocols.gameEnded )
						isCallbackReceived = true;
				}
				
				client.close();
				server.close();
			}
			catch( Exception e )
			{
				System.out.println( e );
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ConnectionWaitScreen.gameConnectFailed = true;
			}
		}
		//IF CLIENT
		else
		{
			try
			{
				host = InetAddress.getLoopbackAddress();
				
				client = new Socket( host, port_number );
				
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				
				ConnectionJoinScreen.gameConnected = true;
				BoxGame.gameON = true;
				
				while( BoxGame.gameON )
				{
					System.out.println("IM Client");
					
					send();
					receive();
				}

				boolean isCallbackReceived = false;
				int callback;
				while( !isCallbackReceived )
				{
					os.writeInt( NetworkProtocols.gameEnded);
					
					callback = is.readInt();
					
					if( callback == NetworkProtocols.gameEnded )
						isCallbackReceived = true;
				}
				client.close();
			}
			catch( Exception e )
			{
				System.out.println( e );
				
				ConnectionJoinScreen.gameConnectFailed = true;
			}
			
		}
	}
	

	private void send(  )
	{
		try{
		 internalSend();}
		catch(Exception e){
			System.out.println(e);
		}
	}
		private void internalSend( ) throws IOException
		{
			toSend = NetworkManager.getNextData();
			
			System.out.println("SENDING: " + toSend.x + " " + toSend.y);
		
			byte b[] = NetworkSerializer.serialize(toSend);
			
			try {
				Vector2 deser = (Vector2) NetworkSerializer.deserialize(b);
				System.out.println("Deserialized as " + deser.x + " " + deser.y);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			os.writeInt( b.length );
			os.write( b );
			os.flush();
			
			System.out.println("Sent");
		}
		
		
	private void receive(  )
	{
		try{
		  internalreceive();}
		catch(Exception e){
			System.out.println(e);
		}
	}
		private void internalreceive( ) throws IOException, ClassNotFoundException
		{
			System.out.println("RECEIVING: ");
			
			int size = is.readInt();
			
			if( size == NetworkProtocols.gameEnded )
			{
				GameScreen.gameWon = false;
				GameScreen.gameLost = true;
				
				os.writeInt( NetworkProtocols.gameEnded );
				return;
			}
			
			
			byte[] b = new byte[ size ];
			int i = 0;
			while ( i < size )
			{
				b[i] = is.readByte();
				i++;
			}
			
			recieved = (Vector2) NetworkSerializer.deserialize(b);
			
			System.out.println("Recieved: " + recieved.x + " " + recieved.y);
			
			NetworkManager.sendNextData(recieved);
		}	
	
}
