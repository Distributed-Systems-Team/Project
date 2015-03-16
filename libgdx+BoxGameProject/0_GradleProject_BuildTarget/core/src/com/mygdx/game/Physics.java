/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: Physics.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: 
 * Contributors: 
 * 
 */

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
