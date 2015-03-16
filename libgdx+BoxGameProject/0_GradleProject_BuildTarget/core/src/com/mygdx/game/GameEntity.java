/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: GameEntity.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: David Kim
 * Contributors:
 * 
 */
 
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


import com.badlogic.gdx.math.Vector2;


public abstract class GameEntity extends InputEntity
{
	Vector2 position;
	
	Rectangle hitbox;
	ShapeRenderer sprite;
	

	int id;
	
	public GameEntity(){}
	public void destroy()
	{
		sprite.dispose();
	}
	
	
	abstract void render();
	
	abstract void collided( GameEntity other );
	public void registerCollision()
	{
		CollisionManager.registerCollision(this);
	}
	public void deregisterCollision()
	{
		CollisionManager.deregisterCollision(this);
	}
	
	
}
