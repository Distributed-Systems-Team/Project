/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: NetworkManager.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Matt Wylder
 * Contributors: David Kim
 * 
 */


package com.mygdx.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class NetworkManager{

	Vector2 toSend;
	Vector2 recieved;
	
	private GameConnectThread gameConnection;
	private List<Thread> threadList;
	private int numberOfThreads;
	
	private static NetworkManager instance = null;
	
	public static NetworkManager getInstance(){
		if( instance == null )
			instance = new NetworkManager();
		return instance;
	}
	
	private NetworkManager(){
		threadList = new ArrayList<Thread>();
		numberOfThreads = 0;
		
		toSend = new Vector2( 0,0 );
		recieved = new Vector2( 0,0 );
	}
	
	public static void cleanup() throws IOException{
		try{
			for( int i = 0; i < getInstance().numberOfThreads; i++ ){
				System.out.println( "thread: " + i );
				getInstance().threadList.get( i ).join();
			}
			getInstance().threadList.clear();
			getInstance().numberOfThreads = 0;
		} catch ( Exception e ){System.out.println(e);}
	}
	
	public static void createRoom(){
		getInstance().tryCreate();
	
	}
	private void tryCreate(){
		gameConnection = new GameConnectThread( true );
		threadList.add( new Thread( gameConnection ) );
		threadList.get( numberOfThreads ).start();
		numberOfThreads++;
	}
	
	public static void joinRoom(){
		getInstance().tryJoin();
	}
	private void tryJoin(){
		gameConnection = new GameConnectThread( false );
		threadList.add(new Thread( gameConnection ));
		threadList.get( numberOfThreads ).start();
		
		numberOfThreads++;
	}
	
	public static void sendNextData( Vector2 pos ){
		getInstance().recieved = pos;
	}
	
	public static Vector2 getNextData(){
		return getInstance().toSend;
	}
	public static void pushToNetwork( Vector2 pos ){
		getInstance().toSend = pos;
	}
	
	public static Vector2 pullFromNetwork(){
		return getInstance().recieved;
	}
		
}
