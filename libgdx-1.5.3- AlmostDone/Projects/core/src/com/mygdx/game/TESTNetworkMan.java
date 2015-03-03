package com.mygdx.game;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.Input;

public class TESTNetworkMan 
{
	//Singleton Setup START//
	private static TESTNetworkMan inputThrough;
	
	private TESTNetworkMan()
	{
		outputQueue = new LinkedList< Integer >();
		outputQueue.add( NetworkProtocols.IDLE );
	}
	private static TESTNetworkMan instance()
	{
		if( inputThrough == null )
		{
			inputThrough = new TESTNetworkMan();
		}
		
		return inputThrough;
	}
//Singleton Setup   END//
	
	
	LinkedList<Integer> outputQueue;
//	LinkedList<Integer> inputQueue;
	
	int InputGotten;
	
	BoxCharacter toControl;
	
	public static void register( BoxCharacter in )
	{
		instance().toControl = in;
	}
	
	
	public static void pushToNetwork( int key )
	{
		if( instance().outputQueue.size() > 3 )
			instance().outputQueue.clear();
				
		instance().outputQueue.add(key);
	}
			
	public static void pushToNPC()
	{
		instance().internalPushToNPC();
	}
	private void internalPushToNPC()
	{
		System.out.println(InputGotten);
		
		if( InputGotten == NetworkProtocols.IDLE )
		{
			
		}
		
		else if( InputGotten == NetworkProtocols.KEY_SPACE_pressed )
		{
			toControl.keyPressed( Input.Keys.SPACE );
		}
		else if( InputGotten == NetworkProtocols.KEY_SPACE_down )
		{
			toControl.keyDown(Input.Keys.SPACE);
		}
		else if( InputGotten == NetworkProtocols.KEY_SPACE_release )
		{
			toControl.keyReleased(Input.Keys.SPACE);
		}
		
		else if( InputGotten == NetworkProtocols.KEY_L_ARROW_pressed )
		{
			toControl.keyPressed( Input.Keys.LEFT );
		}
		else if( InputGotten == NetworkProtocols.KEY_L_ARROW_down )
		{
			toControl.keyDown(Input.Keys.LEFT);
		}
		else if( InputGotten == NetworkProtocols.KEY_L_ARROW_release )
		{
			toControl.keyReleased(Input.Keys.LEFT);
		}
		
		else if( InputGotten == NetworkProtocols.KEY_R_ARROW_pressed )
		{
			toControl.keyPressed( Input.Keys.RIGHT );
		}
		else if( InputGotten == NetworkProtocols.KEY_R_ARROW_down )
		{
			toControl.keyDown(Input.Keys.RIGHT);
		}
		else if( InputGotten == NetworkProtocols.KEY_R_ARROW_release )
		{
			toControl.keyReleased(Input.Keys.RIGHT);
		}
	}

	
	public static void send(  )
	{
		instance().internalSend();
	}
		private void internalSend( )
		{
			//============================================================//
			//SOCKET PROGRAMMING WITH DATA STREAM TO SEND "toOutput"
			//THROUGH SOCKET TO OTHER SIDE HERE
			
			//temporary thing to test if it works at least in game
			FAKESocket.socketOut(outputQueue.remove());
			
			//============================================================//
			
			
			//clear output
			if( outputQueue.isEmpty() )
				outputQueue.add( NetworkProtocols.IDLE );
		}
		
		
	public static void receive(  )
	{
		instance().internalreceive();
	}
		private void internalreceive( )
		{
			//============================================================//
			//SOCKET PROGRAMMING WITH DATA STREAM TO GET an INT to store
			//TO "inputGotten" FROM SOCKET FROM OTHER SIDE
			
			//temporary thing to test if it works at least in game
			InputGotten = FAKESocket.socketIn();
			
			//============================================================//
			
			//As soon as data is received, push data to relevant box
		//	pushToNPC();
		}	
		
}
