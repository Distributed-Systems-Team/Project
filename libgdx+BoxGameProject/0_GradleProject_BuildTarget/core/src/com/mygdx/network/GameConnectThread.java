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
		if( isServer )
		{
			try
			{
				port_number = NetworkProtocols.portNum;
				
				server = new ServerSocket(port_number);
				server.setSoTimeout(20000);
				
				NetworkProtocols.ipAddress = InetAddress.getLocalHost().toString();	
				
				client = server.accept();
				
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				
				ConnectionWaitScreen.gameConnected = true;
				BoxGame.gameON = true;
				
				while( BoxGame.gameON  )
				{
					send();
					receive();
				}
				
				boolean isCallbackReceived = false;
				int callback;
				while( !isCallbackReceived )
				{
					if( GameScreen.gameWon )
						os.writeInt(NetworkProtocols.youLose );
					else
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
				BoxGame.gameON = false;
			}
		}
		//IF CLIENT
		else
		{
			try
			{

				while( !NetworkProtocols.joinIPSet )
				{
					//Pausing thread until ip inputed
					System.out.println("asdfasdf");
				}
				
				host = InetAddress.getByName(NetworkProtocols.ipAddress);
				
				client = new Socket( host, port_number );
				
				is = new DataInputStream(client.getInputStream());
				os = new DataOutputStream(client.getOutputStream());
				
				ConnectionJoinScreen.gameConnected = true;
				BoxGame.gameON = true;
				
				while( BoxGame.gameON )
				{
					send();
					receive();
				}

				boolean isCallbackReceived = false;
				int callback;
				while( !isCallbackReceived )
				{
					if( GameScreen.gameWon )
						os.writeInt(NetworkProtocols.youLose );
					else
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
				
				BoxGame.gameON = false;
			}
			
		}
	}
	

	private void send(  ) throws IOException
	{
		 internalSend();
	}
		private void internalSend( ) throws IOException
		{
			toSend = NetworkManager.getNextData();
			
			byte b[] = NetworkSerializer.serialize(toSend);
			
			os.writeInt( b.length );
			os.write( b );
			os.flush();
		}
		
		
	private void receive(  ) throws IOException, ClassNotFoundException
	{
		internalreceive();
	}
		private void internalreceive( ) throws IOException, ClassNotFoundException
		{
			int size = is.readInt();
			
			if( size == NetworkProtocols.youLose )
			{
				GameScreen.gameLost = true;
				GameScreen.gameWon = false;
				
				os.writeInt( NetworkProtocols.gameEnded );
				return;
			}
			else if( size == NetworkProtocols.gameEnded )
			{
				BoxGame.gameON = false;
				
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
			
			NetworkManager.sendNextData(recieved);
		}	
	
}
