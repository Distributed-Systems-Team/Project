package com.mygdx.game;

//import java.util.LinkedList;

public class Physics 
{
	private static final float grav = -1.0f; 
	
	//Singleton Setup START//
	private static Physics physicsInstance;
		
	private Physics()
	{
	}
	private static Physics instance()
	{
		if( physicsInstance == null )
		{
			physicsInstance = new Physics();
		}
		
		return physicsInstance;
	}
	//Singleton Setup   END//
		
	
	public static void runGravity( BoxCharacter toAffect )
	{
		instance().internalRunGravity(toAffect);
	}
		private void internalRunGravity( BoxCharacter toAffect )
		{
			toAffect.characterImpulse.y += grav;
		}
}
