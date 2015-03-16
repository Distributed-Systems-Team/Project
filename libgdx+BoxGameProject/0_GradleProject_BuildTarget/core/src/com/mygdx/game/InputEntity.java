/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: InputEntity.java
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

public abstract class InputEntity 
{
	
	abstract void keyPressed( int key );
	abstract void keyReleased( int key );
	abstract void keyDown( int key );
	
	
	public void registerInput( int key )
	{
		InputManager.registerInputEntity(this, key);
	}
	public void deregisterInput( int key )
	{
		InputManager.deregisterInputEntity(this, key);
	}
	
}
