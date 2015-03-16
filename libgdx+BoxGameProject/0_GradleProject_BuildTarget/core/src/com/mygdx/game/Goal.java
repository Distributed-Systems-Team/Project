/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: Goal.java
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

package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Goal extends Obstacle{
	
	public Goal(boolean setCollision, Color color, Vector2 size, Vector2 pos) {
		super(setCollision, color, size, pos);
		id = GameID.ID_goal;
	}
	
}
