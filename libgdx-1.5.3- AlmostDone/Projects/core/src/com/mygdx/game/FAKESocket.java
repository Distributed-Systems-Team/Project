package com.mygdx.game;

public class FAKESocket 
{
	//Singleton Setup START//
	private static FAKESocket TESTSocketInstance;
	
	private FAKESocket()
	{
	}
	private static FAKESocket instance()
	{
		if( TESTSocketInstance == null )
		{
			TESTSocketInstance = new FAKESocket();
		}
		
		return TESTSocketInstance;
	}
//Singleton Setup   END//
	
	int data;
	
	public static void socketOut( int key )
	{
		instance().data = key;
	}
	
	public static int socketIn()
	{
		return instance().data;
	}
}
